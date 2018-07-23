package tw.com.cct.ms2.shirink_app_git;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class plan_setting extends Base_activity {

    V3_tc_data A=V3_tc_data.getV3_tc_data();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    int segmenttype_record=1;
    Intent exintent;//用來拿錢一頁的資料
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.plan_setting);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//         Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        SegmenttypeSelectSpin_init();

        Button link_to_plan_detail = findViewById(R.id.link_to_plan_detail);
        Intent plan_detail = new Intent(plan_setting.this, plan_detail.class);
        Button_goto_where(link_to_plan_detail, plan_detail);


        FloatingButtonGroup();



    }

    private void SegmenttypeSelectSpin_init() {
        final Spinner segmenttype_select=findViewById(R.id.segmenttype_select);
        exintent=getIntent();
        segmenttype_record=exintent.getIntExtra("segmenttype",1);
        ArrayAdapter<CharSequence> arrayAdapter_segmenttype_select_spinner=ArrayAdapter.createFromResource(this,R.array.segmenttype,R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        segmenttype_select.setAdapter(arrayAdapter_segmenttype_select_spinner);
        segmenttype_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            change_segmenttype(position+1);
            segmenttype_record=position+1;
                   }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     segmenttype_select.setSelection(segmenttype_record-1);

    }

    private void FloatingButtonGroup() {
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        setting_button_group.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
//應該要全頁面拿來共用此按鈕
                PopupMenu button_popmenu = new PopupMenu(plan_setting.this, view);
                button_popmenu.getMenuInflater().inflate(R.menu.button_popmenu_plan_setting, button_popmenu.getMenu());
                button_popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                         Do something...
                        switch (item.getItemId()) {
                            case R.id.enter:
                                Snackbar.make(view, "設定", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                A.plan_sort(segmenttype_record);
                      //          A.print_plan();

                                change_segmenttype(segmenttype_record);
                                Log.d("12323213213", "onMenuItemClick: "+A.SendSegcontextToTc(segmenttype_record));
tcpClass.send(A.SendSegcontextToTc(segmenttype_record));
                                return true;
                            case R.id.cancel:
                                Snackbar.make(view, "取消", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                return true;
                            case R.id.reset:
                                Snackbar.make(view, "重新整理", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            case R.id.copy:
                                Snackbar.make(view, "複製", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                CopySetting();
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

//    private void CopySegment(int segment)
//    {
//
//      A.copySegment(segmenttype_record,segment);
//
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void change_segmenttype(int segmenttype)
    {
        EventBus.getDefault().post(new MessageEvent(segmenttype));
    }






    private void CopySetting() {

        final View DialogLayout= LayoutInflater.from(plan_setting.this).inflate(R.layout.copysettingdialog_plan_setting, null);
         TextView ResourseSegmenttype= DialogLayout.findViewById(R.id.ResourceSegmenttype);
         ResourseSegmenttype.setText(String.valueOf(segmenttype_record));
        final EditText DestSegmenttype= DialogLayout.findViewById(R.id.DestSegmenttype);
                AlertDialog dialog = new AlertDialog.Builder(plan_setting.this)
                .setTitle("時段型態內容複製")
                //.setMessage("是否要離開?")
                .setView(DialogLayout)
                //  .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(getApplicationContext(), "無複製內容" , Toast.LENGTH_SHORT).show();

                    }
                })
//                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int destSegment=Integer.parseInt(String.valueOf(DestSegmenttype.getText()));
                        if((destSegment<1)||(destSegment>20))
                            Toast.makeText(getApplicationContext(), "輸入錯誤，時段型態範圍1~20",Toast.LENGTH_LONG).show();
                        else {                        A.copySegment(segmenttype_record,destSegment);
                        //可能要把下傳功能也放進這段
                        Toast.makeText(getApplicationContext(), "將時段型態"+segmenttype_record+"內容複製到時段型態"+DestSegmenttype.getText(), Toast.LENGTH_SHORT).show();}
                    }
                }).create();
        dialog.show();


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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    plan_setting_content tab0 = new plan_setting_content();


                                    return tab0;
                case 1:
                    plan_setting_fragment tab1 = new plan_setting_fragment();

                    return tab1;
//                case 2:
//                    plan_setting_fragment2 tab2 = new plan_setting_fragment2();
//
//                    return tab2;

                default:
                    return null;


            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        tcpClass.connect();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tcpClass.SocketDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        tcpClass.SocketDestroy();
        //反注册
        EventBus.getDefault().unregister(this);
    }




}
