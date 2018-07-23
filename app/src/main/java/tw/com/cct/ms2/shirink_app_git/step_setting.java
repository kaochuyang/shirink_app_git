package tw.com.cct.ms2.shirink_app_git;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.hamsa.twosteppickerdialog.OnStepDataRequestedListener;
import com.hamsa.twosteppickerdialog.OnStepPickListener;
import com.hamsa.twosteppickerdialog.TwoStepPickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

//import android.support.v4.view.ViewPager;


public class step_setting extends Base_activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    Spinner subphase_spin;
    Spinner total_subphase;
    Spinner phaseorder_select;
    //    FrameLayout phaseorder_select;
    EditText wheelView;
    Spinner LightBoardNum;
    V3_tc_data A = V3_tc_data.getV3_tc_data();
    tcpClass tcp = new tcpClass();

    static int phase_ID = 0;
    //static int totalsubphase=6;//init
    boolean isFragmentLoaded = false;
    Button ViewMode ;
    Button EditMode ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_setting_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.

        mViewPager = findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mSectionsPagerAdapter.notifyDataSetChanged();


        //ViewPager 強制重載 Fragment  http://kaihgcode.blogspot.com/2013/09/viewpager-fragment.html
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        tcpClass.connect();


        FloatButton_init();


        SubphaseSpin_init(tabLayout);
        TotalSubphaseSpin_init();
        LightBoardSpin_init();
        PhaseOrderSelect_init();

        EditViewModeButtonGroup_init();//編輯和檢視模式的切換內容

    }

    private void SubphaseSpin_init(final TabLayout tabLayout) {
        subphase_spin = findViewById(R.id.subphase_spin);
        subphase_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                send_page_num(position);
                tabLayout.getTabAt(0).select();
                Log.d("!!!!!", "subphase_spin onItemSelected: subphase=" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void PhaseOrderSelect_init() {
//參考自  https://github.com/aliab/Two-Step-Picker-Dialog
        wheelView = findViewById(R.id.phaseorder_select);
        wheelView.setText(Integer.toHexString(phase_ID));
        final List<String> hexData = getHexStrings();
        final TwoStepPickerDialog pickPhaseOrder = new TwoStepPickerDialog.Builder(this).withBaseData(hexData).withOkButton("確定").withCancelButton("取消").withInitialBaseSelected(phase_ID % 16)//右邊
                .withInitialStepSelected(phase_ID / 16)//左邊
                .withOnStepDataRequested(new OnStepDataRequestedListener() {
                    @Override
                    public List<String> onStepDataRequest(int useless) {//不用參數 當void 用
                        return hexData;
                    }
                }).withDialogListener(new OnStepPickListener() {
                    @Override
                    public void onStepPicked(int firstWord, int secondWord) {//左邊 右邊
                        int position = secondWord + firstWord * 16;
                        change_phaseorder(position);

                        phase_ID = position;
                        //getTotalsubphase(total_subphase,position);
                        total_subphase.setSelection(A.getTotalSubphaseCount(phase_ID));
                        subphase_spin.setSelection(0);
                        LightBoardNum.setSelection(A.getTotalLightBoardCount(phase_ID));
                        //   tabLayout.getTabAt(0).select();
                        Log.d("!!!!!", "phaseorder_select onItemSelected: phase_order=" + position);
                        wheelView.setText(Integer.toHexString(position));

                        Toast.makeText(getApplicationContext(), "時相編號=" + hexData.get(firstWord) + " " + secondWord, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {
                        Toast.makeText(getApplicationContext(), "Dismised!", Toast.LENGTH_SHORT).show();
                    }
                }).build();


        wheelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickPhaseOrder.setDefaultBaseSelected(phase_ID / 16);//左邊
                pickPhaseOrder.setDefaultStepSelected(phase_ID % 16);//右邊
                pickPhaseOrder.show();
            }
        });
    }
    private void TotalSubphaseSpin_init() {
        total_subphase = findViewById(R.id.total_subphase);
        ArrayAdapter<CharSequence> adapterTotalSubphase = ArrayAdapter.createFromResource(this, R.array.totalsubphase, R.layout.myspinner_style);
        total_subphase.setAdapter(adapterTotalSubphase);
        total_subphase.setSelection(A.getTotalSubphaseCount(phase_ID));
        SubphaseAdapter_init(A.getTotalSubphaseCount(phase_ID));
        total_subphase.setEnabled(false);
        total_subphase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    A.setTotalSubphaseCount(1, phase_ID);
                    total_subphase.setSelection(1);
                } else A.setTotalSubphaseCount(position, phase_ID);
                SubphaseAdapter_init(A.getTotalSubphaseCount(phase_ID));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void LightBoardSpin_init() {
        LightBoardNum = findViewById(R.id.LightBoardNum);
        ArrayAdapter<CharSequence> AdapterLightBoard = ArrayAdapter.createFromResource(this, R.array.LightBoardArray, R.layout.myspinner_style);
        LightBoardNum.setAdapter(AdapterLightBoard);
        LightBoardNum.setSelection(A.getTotalLightBoardCount(phase_ID));
        LightBoardNum.setEnabled(false);
        LightBoardNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    LightBoardNum.setSelection(1);
                    A.setLightBoardCount(1, phase_ID);
                } else A.setLightBoardCount(position, phase_ID);
                change_phaseorder(phase_ID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void EditViewModeButtonGroup_init() {
        ViewMode = findViewById(R.id.ViewMode);
        EditMode = findViewById(R.id.EditMode);
        Editbutton_state_init(ViewMode, EditMode);
        ViewMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModeScript(ViewMode, EditMode);
            }
        });
        EditMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditModeScript(EditMode, ViewMode);
            }
        });
    }
    private void EditModeScript(Button editMode, Button viewMode) {
        LightBoardNum.setEnabled(true);
        total_subphase.setEnabled(true);
        change_Editable(true);
        Editbutton_state_init(editMode, viewMode);
    }
    private void ViewModeScript(Button viewMode, Button editMode) {
        change_Editable(false);
        LightBoardNum.setEnabled(false);
        total_subphase.setEnabled(false);
        Editbutton_state_init(viewMode, editMode);
    }

    private void FloatButton_init() {
        FloatingActionButton setting_button_group = findViewById(R.id.setting_button_group);
        setting_button_group.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                //應該要全頁面拿來共用此按鈕
                PopupMenu button_popmenu = new PopupMenu(step_setting.this, view);
                button_popmenu.getMenuInflater().inflate(R.menu.button_popmenu, button_popmenu.getMenu());
                button_popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //                         Do something...
                        switch (item.getItemId()) {
                            case R.id.enter:
                                Snackbar.make(view, "設定", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                Log.d("123321", "onMenuItemClick: "+A.sendStepSettingToTc(phase_ID));
                                tcpClass.send(A.sendStepSettingToTc(phase_ID));//下傳內容到控制器
                                return true;
                            case R.id.cancel:
                                Snackbar.make(view, "取消", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                return true;
                            case R.id.reset:

                                A.refreshdata();
                                change_phaseorder(phase_ID);
                                Snackbar.make(view, "重新整理", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                                return true;
                            default:
                                return false;
                        }

                    }

                });

                button_popmenu.show();
            }


        });
    }

    private void Editbutton_state_init(Button UseMode, Button NotUseMode) {
        NotUseMode.setClickable(true);
        NotUseMode.setBackgroundColor(Color.WHITE);
        UseMode.setClickable(false);
        UseMode.setBackgroundColor(Color.YELLOW);
    }


    private void SubphaseAdapter_init(int totalsubphase) {
        String adpter_name = "total_subphase_" + String.format("%d", totalsubphase);
        int resID = getResources().getIdentifier(adpter_name, "array", getPackageName());
        ArrayAdapter<CharSequence> arrayAdapter_select_spinner = ArrayAdapter.createFromResource(this, resID, R.layout.myspinner_style);
        subphase_spin.setAdapter(arrayAdapter_select_spinner);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void send_page_num(int position) {
        Log.d("!!!!!!!", "send_page_num: page=" + position + 1);
        EventBus.getDefault().post(new MessageEvent_subphase(position));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void change_phaseorder(int phaseID) {
        Log.d("!!!!", "change_phaseorder: ");
        EventBus.getDefault().post(new MessageEvent_phaseorder(phaseID));
        ViewModeScript(ViewMode,EditMode);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void change_Editable(boolean EditableFlag) {

        EventBus.getDefault().post(new MessageEvent_Editable(EditableFlag));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_step_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class belo
            step_setting_fragment step_page = new step_setting_fragment();

            Log.d("!!!!getItem", "getItem:postion=PAGE =" + position);
            step_page.getPagenum(position + 1);
//                send_page_num(position);
            return step_page;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }
    }

    @Override
    protected void onResume() {
        //change_Editable(false);
        super.onResume();
        EventBus.getDefault().register(this);
        tcpClass.connect();
    }

    @Override
    protected void onDestroy() {
        //change_Editable(false);
        tcpClass.SocketDestroy();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        tcpClass.SocketDestroy();
        //change_Editable(false);
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @NonNull
    private List<String> getHexStrings() {
        final List<String> Hex = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            Hex.add(Integer.toHexString(i));
        return Hex;
    }


}
