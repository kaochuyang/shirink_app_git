package tw.com.cct.ms2.shirink_app_git;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

public class plan_setting extends Base_activity {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
//    int [][]hour=new int[21][32];//segment,segment_count
//    int [][]minute=new int[21][32];//segment,segment_count
//    int [][]plan_num_record=new int[21][32];//segment,segment_count
//    JSONObject[] segcontext = new JSONObject[21];////segment
    V3_tc_data A=V3_tc_data.getV3_tc_data();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    int segmenttype_record=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_setting);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//         Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //init_segcontext(A.getV3_json_data(), segcontext);


        Button link_to_plan_detail = findViewById(R.id.link_to_plan_detail);
        Intent plan_detail = new Intent(plan_setting.this, plan_detail.class);
        Button_goto_where(link_to_plan_detail, plan_detail);
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        setting_button_group.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
//應該要全頁面拿來共用此按鈕
                PopupMenu button_popmenu = new PopupMenu(plan_setting.this, view);
                button_popmenu.getMenuInflater().inflate(R.menu.button_popmenu, button_popmenu.getMenu());
                button_popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                         Do something...
                        switch (item.getItemId()) {
                            case R.id.enter:
                                Snackbar.make(view, "設定", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                A.plan_sort(segmenttype_record);
                                A.print_plan();
                                change_segmenttype(segmenttype_record);
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


        final Spinner segmenttype_select=findViewById(R.id.segmenttype_select);
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

      //  floating_button_function(setting_button_group, plan_setting.this);




    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void change_segmenttype(int segmenttype)
    {
        EventBus.getDefault().post(new MessageEvent(segmenttype));
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onStop() {
        super.onStop();

        //反注册
        EventBus.getDefault().unregister(this);
    }
/*

    private void init_segcontext(JSONObject jsonObject, JSONObject[] segcontext) {
        try {
            for (int i = 0; i < 21; i++)
            {       segcontext[i] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(i);
            for(int segment_count=0;segment_count<32;segment_count++) {
                hour[i][segment_count] = segcontext[i].getJSONArray("hour").getInt(segment_count);
                minute[i][segment_count] = segcontext[i].getJSONArray("minute").getInt(segment_count);
                plan_num_record[i][segment_count]=segcontext[i].getJSONArray("plan").getInt(i);
            
            }       
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void setHour(int segment,int segment_count, int input_hour)
    {
        hour[segment][segment_count]=input_hour;
    }

    public void setMinute(int segment,int segment_count,int input_minute)
    {
        minute[segment][segment_count]=input_minute;

    }

    public void setPlan_num_record(int segment,int segment_count,int input_plan_num) {
        this.plan_num_record[segment][segment_count] = input_plan_num;
    }

    public int getPlan_num_record(int segment,int segment_count) {
        return plan_num_record[segment][segment_count];
    }

    public int getHour(int segment,int segment_count) {
        return this.hour[segment][segment_count];
    }

    public int getMinute(int segment,int segment_count) {
        return this.minute[segment][segment_count];
    }
*/

}
