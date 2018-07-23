package tw.com.cct.ms2.shirink_app_git;

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

import java.util.Calendar;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_content extends android.support.v4.app.Fragment {
    final EditText[] plan_start_num = new EditText[16];
    Spinner plan_spin_num[] = new Spinner[16];
    int record_segment;
    V3_tc_data A = V3_tc_data.getV3_tc_data();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_content, container, false);
        ArrayAdapter<CharSequence> arrayAdapter_plan_spinner = ArrayAdapter.createFromResource(this.getActivity(), R.array.plan, R.layout.myspinner_style);
        plan_spin_num_view_init(rootView, plan_spin_num);
        plan_spin_adapter_init(plan_spin_num, arrayAdapter_plan_spinner);
        plan_start_time_link_view(rootView, plan_start_num);
        plan_start_num_set_init_text(plan_start_num, 1);
        for (int i = 0; i < 16; i++)
            plan_start_time_setonclick(plan_start_num, i);
        return rootView;
    }

    private void plan_spin_init_value(Spinner[] plan_spin_num, int segmenttype) {
        for (int i = 0; i < 16; i++) {
            plan_spin_num[i].setSelection(A.getPlan_num_record(segmenttype, i));
            plan_spinner_setting(plan_spin_num[i], segmenttype, i);
        }
    }

    private String getText_plan_start_time(int segment, int segment_count) {
        return A.getHour(segment, segment_count) + ":" + A.getMinute(segment, segment_count);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void plan_set_select(MessageEvent messageEvent) {
        int index = messageEvent.getSegmenttype();
        record_segment = index;
        plan_start_num_set_init_text(plan_start_num, index);
        plan_spin_init_value(plan_spin_num, index);
    }

    private void plan_start_num_set_init_text(EditText[] plan_start_num, int segmenttype) {
        for (int i = 0; i < 16; i++)
            plan_start_num[i].setText(getText_plan_start_time(segmenttype, i));
    }

    private void plan_start_time_setonclick(final EditText[] plan_start_num, final int SegmentCount) {
        plan_start_num[SegmentCount].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog and return it
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String format = setTimeFormat(hourOfDay, minute, record_segment, SegmentCount);
                        plan_start_num[SegmentCount].setText(format);
                    }
                }, hour, minute, true).show();
            }
        });


    }

    private void plan_start_time_link_view(View rootView, EditText[] plan_start_num) {
        for (int i = 1; i < 17; i++) {
            String idname = "plan_start_num_" + String.format("%d", i);
            int resID = getResources().getIdentifier(idname, "id", getActivity().getPackageName());
            plan_start_num[i-1] = rootView.findViewById(resID);
        }
//        plan_start_num[0] = rootView.findViewById(R.id.plan_start_num_1);
//        plan_start_num[1] = rootView.findViewById(R.id.plan_start_num_2);
//        plan_start_num[2] = rootView.findViewById(R.id.plan_start_num_3);
//        plan_start_num[3] = rootView.findViewById(R.id.plan_start_num_4);
//        plan_start_num[4] = rootView.findViewById(R.id.plan_start_num_5);
//        plan_start_num[5] = rootView.findViewById(R.id.plan_start_num_6);
//        plan_start_num[6] = rootView.findViewById(R.id.plan_start_num_7);
//        plan_start_num[7] = rootView.findViewById(R.id.plan_start_num_8);
//        plan_start_num[8] = rootView.findViewById(R.id.plan_start_num_9);
//        plan_start_num[9] = rootView.findViewById(R.id.plan_start_num_10);
//        plan_start_num[10] = rootView.findViewById(R.id.plan_start_num_11);
//        plan_start_num[11] = rootView.findViewById(R.id.plan_start_num_12);
//        plan_start_num[12] = rootView.findViewById(R.id.plan_start_num_13);
//        plan_start_num[13] = rootView.findViewById(R.id.plan_start_num_14);
//        plan_start_num[14] = rootView.findViewById(R.id.plan_start_num_15);
//        plan_start_num[15] = rootView.findViewById(R.id.plan_start_num_16);
    }

    private void plan_spin_num_view_init(View rootView, Spinner[] plan_spin_num) {
        for (int i = 1; i < 17; i++) {
            String idname = "plan_spin_num_" + String.format("%d", i);
            int resID = getResources().getIdentifier(idname, "id", getActivity().getPackageName());
            plan_spin_num[i-1] = rootView.findViewById(resID);
        }
       
//        plan_spin_num[0] = rootView.findViewById(R.id.plan_spin_num_1);
//        plan_spin_num[1] = rootView.findViewById(R.id.plan_spin_num_2);
//        plan_spin_num[2] = rootView.findViewById(R.id.plan_spin_num_3);
//        plan_spin_num[3] = rootView.findViewById(R.id.plan_spin_num_4);
//        plan_spin_num[4] = rootView.findViewById(R.id.plan_spin_num_5);
//        plan_spin_num[5] = rootView.findViewById(R.id.plan_spin_num_6);
//        plan_spin_num[6] = rootView.findViewById(R.id.plan_spin_num_7);
//        plan_spin_num[7] = rootView.findViewById(R.id.plan_spin_num_8);
//        plan_spin_num[8] = rootView.findViewById(R.id.plan_spin_num_9);
//        plan_spin_num[9] = rootView.findViewById(R.id.plan_spin_num_10);
//        plan_spin_num[10] = rootView.findViewById(R.id.plan_spin_num_11);
//        plan_spin_num[11] = rootView.findViewById(R.id.plan_spin_num_12);
//        plan_spin_num[12] = rootView.findViewById(R.id.plan_spin_num_13);
//        plan_spin_num[13] = rootView.findViewById(R.id.plan_spin_num_14);
//        plan_spin_num[14] = rootView.findViewById(R.id.plan_spin_num_15);
//        plan_spin_num[15] = rootView.findViewById(R.id.plan_spin_num_16);
    }

    private void plan_spin_adapter_init(Spinner[] plan_spin_num, ArrayAdapter<CharSequence> arrayAdapter_plan_spinner) {
        for (int i = 0; i < 16; i++)
            plan_spin_num[i].setAdapter(arrayAdapter_plan_spinner);

    }

    public String setTimeFormat(int hour, int minute, int Segment, int SegmentCount) {


        A.setHour(Segment, SegmentCount, hour);
        A.setMinute(Segment, SegmentCount, minute);


        return String.valueOf(hour) + ":" + String.valueOf(minute);
    }

    private void plan_spinner_setting(final Spinner spinner, final int Segment, final int SegmentCount) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                A.setPlan_num_record(Segment, SegmentCount, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}

