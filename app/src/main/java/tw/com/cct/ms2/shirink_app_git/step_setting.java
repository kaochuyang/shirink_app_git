package tw.com.cct.ms2.shirink_app_git;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
Light_State lightState;
Spinner subphase_spin;
    V3_tc_data A = V3_tc_data.getV3_tc_data();
//    final JSONObject jsonObject = A.getV3_json_data();
//    JSONObject[] step_object=new JSONObject[256];
    static int phase_ID=0;
    static int totalsubphase=6;//init
    boolean isFragmentLoaded = false;
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
                                Snackbar.make(view, "設定", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
//                           A.print_step();
                                return true;
                            case R.id.cancel:
                                Snackbar.make(view, "取消", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                return true;
                            case R.id.reset:
                                Snackbar.make(view, "重新整理", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                return true;
                            default:
                                return false;
                        }

                    }

                });

                button_popmenu.show();
            }


        });



        subphase_spin=findViewById(R.id.subphase_spin);
        final TextView  total_subphase=findViewById(R.id.total_subphase);

        Spinner phaseorder_select=findViewById(R.id.phaseorder_select);
        ArrayAdapter<CharSequence> arrayAdapter_select_spinner=ArrayAdapter.createFromResource(this,R.array.plan,R.layout.myspinner_style);
        phaseorder_select.setAdapter(arrayAdapter_select_spinner);
        getTotalsubphase(total_subphase, phase_ID);

        phaseorder_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        change_phaseorder(position);
        getTotalsubphase(total_subphase,position);
        subphase_spin.setSelection(0);
     tabLayout.getTabAt(0).select();
        Log.d("!!!!!", "phaseorder_select onItemSelected: phase_order="+position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        subphase_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                send_page_num(position);
                tabLayout.getTabAt(0).select();
                Log.d("!!!!!", "subphase_spin onItemSelected: subphase="+position);
                          }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private int getTotalsubphase(TextView total_subphase, int PhaseOrder) {//include subphase spinner init
        totalsubphase = A.getTotalSubphaseCount(PhaseOrder);
        total_subphase.setText(String.valueOf(totalsubphase));
        spinner_init(totalsubphase);
        return totalsubphase;
    }

    private void spinner_init(int totalsubphase) {
        String adpter_name="total_subphase_"+ String.format("%d", totalsubphase);
        int resID = getResources().getIdentifier(adpter_name, "array", getPackageName());
        ArrayAdapter<CharSequence> arrayAdapter_select_spinner=ArrayAdapter.createFromResource(this,resID,R.layout.myspinner_style);
        subphase_spin.setAdapter(arrayAdapter_select_spinner);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    void send_page_num(int position) {
        Log.d("!!!!!!!", "send_page_num: page="+position+1);
        EventBus.getDefault().post(new MessageEvent_subphase(position));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    void change_phaseorder(int phaseID) {
        Log.d("!!!!", "change_phaseorder: ");
        EventBus.getDefault().post(new MessageEvent_phaseorder(phaseID));
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

            Log.d("!!!!getItem", "getItem:postion=PAGE ="+position);
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
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
