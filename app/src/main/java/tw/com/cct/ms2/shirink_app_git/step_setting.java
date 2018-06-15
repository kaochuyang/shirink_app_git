package tw.com.cct.ms2.shirink_app_git;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static tw.com.cct.ms2.shirink_app_git.R.array.total_subphase_1;
import static tw.com.cct.ms2.shirink_app_git.R.array.total_subphase_2;
import static tw.com.cct.ms2.shirink_app_git.R.array.total_subphase_3;
import static tw.com.cct.ms2.shirink_app_git.R.array.total_subphase_4;
import static tw.com.cct.ms2.shirink_app_git.R.array.total_subphase_5;
import static tw.com.cct.ms2.shirink_app_git.R.array.total_subphase_8;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.green_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.left_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.ped_green_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.ped_red_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.red_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.right_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.straight_state_;
import static tw.com.cct.ms2.shirink_app_git.step_setting_fragment.yellow_state_;

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
//    static int[] red_state_=new int[6];
//    static int[] green_state_=new int[6];
//    static int[] yellow_state_=new int[6];
//    static int[] left_state_=new int[6];
//    static int[] right_state_=new int[6];
//    static int[] straight_state_=new int[6];
//    static int[] ped_red_state_=new int[6];
//    static int[] ped_green_state_=new int[6];
Light_State lightState;
Spinner subphase_spin;
    V3_tc_data A = V3_tc_data.getV3_tc_data();
    final JSONObject jsonObject = A.getV3_json_data();
    JSONObject[] step_object=new JSONObject[256];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_setting_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        floating_button_function(setting_button_group, step_setting.this);

        subphase_spin=findViewById(R.id.subphase_spin);
        TextView  total_subphase=findViewById(R.id.total_subphase);
        int totalsubphase=6;
        try {
            totalsubphase= Integer.parseInt(String.valueOf(jsonObject.getJSONArray("step").getJSONObject(0).get("subphase_count")));
            Log.d("subphase_spiner_", "subphase_spiner_init: "+jsonObject.getJSONArray("step").getJSONObject(0).getString("subphase_count").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

           total_subphase.setText(String.valueOf(totalsubphase));
        spinner_init(totalsubphase);

        subphase_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                send_page_num(position);

                //finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void spinner_init(int totalsubphase) {
        String adpter_name="total_subphase_"+ String.format("%d", totalsubphase);
        int resID = getResources().getIdentifier(adpter_name, "array", getPackageName());
        ArrayAdapter<CharSequence> arrayAdapter_select_spinner=ArrayAdapter.createFromResource(this,resID,R.layout.myspinner_style);
        subphase_spin.setAdapter(arrayAdapter_select_spinner);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void send_page_num(int position) {
        EventBus.getDefault().post(new MessageEvent_subphase(position));
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

            Log.d(String.valueOf(position), "getItem:postion ");
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


}
