package tw.com.cct.ms2.shirink_app_git;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import static tw.com.cct.ms2.shirink_app_git.V3_tc_data.jsonObject;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_fragment extends android.support.v4.app.Fragment {
    final EditText[] plan_start_num = new EditText[16];
    JSONObject[] segcontext = new JSONObject[16];
    Spinner plan_spin_num[] = new Spinner[16];
    int []hour=new int[16];
    int []minute=new int[16];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_fragment, container, false);
         ArrayAdapter<CharSequence> arrayAdapter_plan_spinner=ArrayAdapter.createFromResource(this.getActivity(),R.array.plan,R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        V3_tc_data A = V3_tc_data.getV3_tc_data();
        JSONObject jsonObject = A.getV3_json_data();

        plan_spin_num_view_init(rootView, plan_spin_num);
        plan_spin_adapter_init(plan_spin_num, arrayAdapter_plan_spinner);
        init_segcontext(jsonObject, segcontext);
        plan_start_time_link_view(rootView, plan_start_num);
        try {
            plan_start_num_set_init_text(plan_start_num, segcontext,1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        plan_start_time_setonclick(plan_start_num);


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
        try {
            plan_start_num_set_init_text(plan_start_num, segcontext,index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        plan_spin_init_value(plan_spin_num,index);


    }
    private void plan_spin_init_value(Spinner[] plan_spin_num,int segmenttype)  {
        try {

            plan_spin_num[0].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(16));
            plan_spin_num[1].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(17));
            plan_spin_num[2].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(18));
            plan_spin_num[3].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(19));
            plan_spin_num[4].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(20));
            plan_spin_num[5].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(21));
            plan_spin_num[6].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(22));
            plan_spin_num[7].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(23));
            plan_spin_num[8].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(24));
            plan_spin_num[9].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(25));
            plan_spin_num[10].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(26));
            plan_spin_num[11].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(27));
            plan_spin_num[12].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(28));
            plan_spin_num[13].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(29));
            plan_spin_num[14].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(30));
            plan_spin_num[15].setSelection((Integer) segcontext[segmenttype].getJSONArray("plan").get(31));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void plan_start_num_set_init_text(EditText[] plan_start_num, JSONObject[] segcontext, int segmenttype) throws JSONException {
         plan_start_num[0].setText(getText_plan_start_time(segcontext[segmenttype],17));
            plan_start_num[1].setText(getText_plan_start_time(segcontext[segmenttype],18));
            plan_start_num[2].setText(getText_plan_start_time(segcontext[segmenttype],19));
            plan_start_num[3].setText(getText_plan_start_time(segcontext[segmenttype],20));
            plan_start_num[4].setText(getText_plan_start_time(segcontext[segmenttype],21));
            plan_start_num[5].setText(getText_plan_start_time(segcontext[segmenttype],22));
            plan_start_num[6].setText(getText_plan_start_time(segcontext[segmenttype],23));
            plan_start_num[7].setText(getText_plan_start_time(segcontext[segmenttype],24));
            plan_start_num[8].setText(getText_plan_start_time(segcontext[segmenttype],25));
            plan_start_num[9].setText(getText_plan_start_time(segcontext[segmenttype],26));
            plan_start_num[10].setText(getText_plan_start_time(segcontext[segmenttype],27));
            plan_start_num[11].setText(getText_plan_start_time(segcontext[segmenttype],28));
            plan_start_num[12].setText(getText_plan_start_time(segcontext[segmenttype],29));
            plan_start_num[13].setText(getText_plan_start_time(segcontext[segmenttype],30));
            plan_start_num[14].setText(getText_plan_start_time(segcontext[segmenttype],31));
            plan_start_num[15].setText(getText_plan_start_time(segcontext[segmenttype],32));

    }
    private String getText_plan_start_time(JSONObject jsonObject, int array_index) throws JSONException {
        return jsonObject.getJSONArray("hour").getString(array_index) + ":" + jsonObject.getJSONArray("minute").getString(array_index);
    }
    private void init_segcontext(JSONObject jsonObject, JSONObject[] segcontext) {
        try {
            segcontext[0] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(16);
            segcontext[1] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(17);
            segcontext[2] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(18);
            segcontext[3] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(19);
            segcontext[4] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(20);
            segcontext[5] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(21);
            segcontext[6] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(22);
            segcontext[7] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(23);
            segcontext[8] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(24);
            segcontext[9] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(25);
            segcontext[10] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(26);
            segcontext[11] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(27);
            segcontext[12] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(28);
            segcontext[13] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(29);
            segcontext[14] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(30);
            segcontext[15] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(31);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void plan_start_time_setonclick(final EditText[] plan_start_num) {
        plan_start_num[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[0]);
            }

        });
        plan_start_num[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[1]);
            }

        });
        plan_start_num[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[2]);
            }

        });
        plan_start_num[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[3]);
            }

        });
        plan_start_num[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[4]);
            }

        });
        plan_start_num[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[5]);
            }

        });
        plan_start_num[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[6]);
            }

        });
        plan_start_num[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[7]);
            }

        });
        plan_start_num[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[8]);
            }

        });
        plan_start_num[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[9]);
            }

        });
        plan_start_num[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[10]);
            }

        });
        plan_start_num[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[11]);
            }

        });
        plan_start_num[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[12]);
            }

        });
        plan_start_num[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[13]);
            }

        });
        plan_start_num[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[14]);
            }

        });
        plan_start_num[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimerSetter(plan_start_num[15]);
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
        plan_spin_num[0].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[1].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[2].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[3].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[4].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[5].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[6].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[7].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[8].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[9].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[10].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[11].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[12].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[13].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[14].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[15].setAdapter(arrayAdapter_plan_spinner);


    }

}
