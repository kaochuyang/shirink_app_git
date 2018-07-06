package tw.com.cct.ms2.shirink_app_git;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static tw.com.cct.ms2.shirink_app_git.V3_tc_data.jsonObject;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_fragment extends android.support.v4.app.Fragment {
    final EditText[] plan_start_num = new EditText[16];
    JSONObject[] segcontext = new JSONObject[16];
    Spinner plan_spin_num[] = new Spinner[16];
    int record_segment;

    V3_tc_data A = V3_tc_data.getV3_tc_data();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_content, container, false);
        ArrayAdapter<CharSequence> arrayAdapter_plan_spinner = ArrayAdapter.createFromResource(this.getActivity(), R.array.plan, R.layout.myspinner_style);
        plan_spin_num_view_init(rootView, plan_spin_num);
        plan_spin_adapter_init(plan_spin_num, arrayAdapter_plan_spinner);
        plan_start_time_link_view(rootView, plan_start_num);
        plan_start_num_set_init_text(plan_start_num, 1);
        for(int i=0;i<16;i++)
            plan_start_time_setonclick(plan_start_num,i+16);
        return rootView;
    }

    @Override
    public  void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void plan_set_select(MessageEvent messageEvent)
    {
        int index=messageEvent.getSegmenttype();
        record_segment=index;
        plan_start_num_set_init_text(plan_start_num,index);
        plan_spin_init_value(plan_spin_num,index);


    }
    private void plan_spin_init_value(Spinner[] plan_spin_num,int segmenttype)  {

int j=0;
            for(int i=0;i<16;i++)
            {j=i+16;
                plan_spin_num[i].setSelection(A.getPlan_num_record(segmenttype, j));
                plan_spinner_setting(plan_spin_num[i],segmenttype,j);}

    }
    private void plan_spinner_setting(final Spinner spinner, final int Segment, final int SegmentCount) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                A.setPlan_num_record(Segment,SegmentCount,position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void plan_start_num_set_init_text(EditText[] plan_start_num, int segmenttype){
        int j=0;
        for(int i=0;i<16;i++)
        {j=i+16;
            plan_start_num[i].setText(getText_plan_start_time(segmenttype, j));}
//            plan_start_num[1].setText(getText_plan_start_time(segcontext[segmenttype],18));
//            plan_start_num[2].setText(getText_plan_start_time(segcontext[segmenttype],19));
//            plan_start_num[3].setText(getText_plan_start_time(segcontext[segmenttype],20));
//            plan_start_num[4].setText(getText_plan_start_time(segcontext[segmenttype],21));
//            plan_start_num[5].setText(getText_plan_start_time(segcontext[segmenttype],22));
//            plan_start_num[6].setText(getText_plan_start_time(segcontext[segmenttype],23));
//            plan_start_num[7].setText(getText_plan_start_time(segcontext[segmenttype],24));
//            plan_start_num[8].setText(getText_plan_start_time(segcontext[segmenttype],25));
//            plan_start_num[9].setText(getText_plan_start_time(segcontext[segmenttype],26));
//            plan_start_num[10].setText(getText_plan_start_time(segcontext[segmenttype],27));
//            plan_start_num[11].setText(getText_plan_start_time(segcontext[segmenttype],28));
//            plan_start_num[12].setText(getText_plan_start_time(segcontext[segmenttype],29));
//            plan_start_num[13].setText(getText_plan_start_time(segcontext[segmenttype],30));
//            plan_start_num[14].setText(getText_plan_start_time(segcontext[segmenttype],31));
//            plan_start_num[15].setText(getText_plan_start_time(segcontext[segmenttype],32));

    }
    private String getText_plan_start_time(int segment, int segment_count) {
        return  A.getHour(segment,segment_count)+ ":" + A.getMinute(segment,segment_count);
    }
    private void plan_start_time_setonclick(final EditText[] plan_start_num,final int SegmentCount) {
        plan_start_num[SegmentCount].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog and return it
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String format=setTimeFormat(hourOfDay,minute,record_segment,SegmentCount);
                        plan_start_num[SegmentCount].setText(format);
                    }
                }, hour, minute, true).show();
            }
        });


    }
    private void plan_start_time_link_view(View rootView, EditText[] plan_start_num) {
        plan_start_num[0] = rootView.findViewById(R.id.plan_start_num_17);
        plan_start_num[1] = rootView.findViewById(R.id.plan_start_num_18);
        plan_start_num[2] = rootView.findViewById(R.id.plan_start_num_19);
        plan_start_num[3] = rootView.findViewById(R.id.plan_start_num_20);
        plan_start_num[4] = rootView.findViewById(R.id.plan_start_num_21);
        plan_start_num[5] = rootView.findViewById(R.id.plan_start_num_22);
        plan_start_num[6] = rootView.findViewById(R.id.plan_start_num_23);
        plan_start_num[7] = rootView.findViewById(R.id.plan_start_num_24);
        plan_start_num[8] = rootView.findViewById(R.id.plan_start_num_25);
        plan_start_num[9] = rootView.findViewById(R.id.plan_start_num_26);
        plan_start_num[10] = rootView.findViewById(R.id.plan_start_num_27);
        plan_start_num[11] = rootView.findViewById(R.id.plan_start_num_28);
        plan_start_num[12] = rootView.findViewById(R.id.plan_start_num_29);
        plan_start_num[13] = rootView.findViewById(R.id.plan_start_num_30);
        plan_start_num[14] = rootView.findViewById(R.id.plan_start_num_31);
        plan_start_num[15] = rootView.findViewById(R.id.plan_start_num_32);
    }
    private void plan_spin_num_view_init(View rootView, Spinner[] plan_spin_num) {
        plan_spin_num[0]=rootView.findViewById(R.id.plan_spin_num_17);
        plan_spin_num[1]=rootView.findViewById(R.id.plan_spin_num_18);
        plan_spin_num[2]=rootView.findViewById(R.id.plan_spin_num_19);
        plan_spin_num[3]=rootView.findViewById(R.id.plan_spin_num_20);
        plan_spin_num[4]=rootView.findViewById(R.id.plan_spin_num_21);
        plan_spin_num[5]=rootView.findViewById(R.id.plan_spin_num_22);
        plan_spin_num[6]=rootView.findViewById(R.id.plan_spin_num_23);
        plan_spin_num[7]=rootView.findViewById(R.id.plan_spin_num_24);
        plan_spin_num[8]=rootView.findViewById(R.id.plan_spin_num_25);
        plan_spin_num[9]=rootView.findViewById(R.id.plan_spin_num_26);
        plan_spin_num[10]=rootView.findViewById(R.id.plan_spin_num_27);
        plan_spin_num[11]=rootView.findViewById(R.id.plan_spin_num_28);
        plan_spin_num[12]=rootView.findViewById(R.id.plan_spin_num_29);
        plan_spin_num[13]=rootView.findViewById(R.id.plan_spin_num_30);
        plan_spin_num[14]=rootView.findViewById(R.id.plan_spin_num_31);
        plan_spin_num[15]=rootView.findViewById(R.id.plan_spin_num_32);
    }
    private void plan_spin_adapter_init(Spinner[] plan_spin_num, ArrayAdapter<CharSequence> arrayAdapter_plan_spinner) {
        for(int i=0;i<16;i++)
        plan_spin_num[0].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[1].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[2].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[3].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[4].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[5].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[6].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[7].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[8].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[9].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[10].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[11].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[12].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[13].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[14].setAdapter(arrayAdapter_plan_spinner);
//        plan_spin_num[15].setAdapter(arrayAdapter_plan_spinner);


    }
    public String setTimeFormat(int hour, int minute,int Segment,int SegmentCount) {


        A.setHour(Segment,SegmentCount,hour);
        A.setMinute(Segment,SegmentCount,minute);


        return String.valueOf(hour) + ":" + String.valueOf(minute) ;
    }
}
