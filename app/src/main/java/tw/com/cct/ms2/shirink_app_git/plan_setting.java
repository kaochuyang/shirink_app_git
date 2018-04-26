package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class plan_setting extends Base_activity {
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

//    private plan_setting.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_setting);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//         Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        Log.d("test1", "onCreate: ");
        Button link_to_plan_detail = findViewById(R.id.link_to_plan_detail);
        Intent plan_detail = new Intent(plan_setting.this, plan_detail.class);
        Button_goto_where(link_to_plan_detail, plan_detail);
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Log.d("test2", "onCreate: ");

        Spinner segmenttype_select=findViewById(R.id.segmenttype_select);


        ArrayAdapter<CharSequence> arrayAdapter_segmenttype_select_spinner=ArrayAdapter.createFromResource(this,R.array.segmenttype,R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        segmenttype_select.setAdapter(arrayAdapter_segmenttype_select_spinner);



        floating_button_function(setting_button_group, plan_setting.this);


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
                case 2:
                    plan_setting_fragment2 tab2 = new plan_setting_fragment2();
                    return tab2;

                default:
                    return null;


            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }


}
