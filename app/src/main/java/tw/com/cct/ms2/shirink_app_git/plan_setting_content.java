package tw.com.cct.ms2.shirink_app_git;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2018/4/17.
 */


public class plan_setting_content extends android.support.v4.app.Fragment {
    final EditText[] plan_start_num = new EditText[16];
    JSONObject[] segcontext = new JSONObject[16];



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_content, container, false);
        V3_tc_data A = V3_tc_data.getV3_tc_data();
        JSONObject jsonObject = A.getV3_json_data();
        Spinner plan_spin_num[] = new Spinner[16];
        ArrayAdapter<CharSequence> arrayAdapter_plan_spinner = ArrayAdapter.createFromResource(this.getActivity(), R.array.plan, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        plan_spin_num_view_init(rootView, plan_spin_num);
        plan_spin_adapter_init(plan_spin_num, arrayAdapter_plan_spinner);

        init_segcontext(jsonObject, segcontext);
        plan_start_time_link_view(rootView, plan_start_num);

        plan_start_num_set_init_text(plan_start_num, segcontext,1);
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
        plan_start_num_set_init_text(plan_start_num, segcontext,index);
    }

    private void plan_start_num_set_init_text(EditText[] plan_start_num, JSONObject[] segcontext,int segmenttype) {
        try {
            plan_start_num[0].setText(getText_plan_start_time(segcontext[segmenttype], 0));
            plan_start_num[1].setText(getText_plan_start_time(segcontext[segmenttype], 1));
            plan_start_num[2].setText(getText_plan_start_time(segcontext[segmenttype],2));
            plan_start_num[3].setText(getText_plan_start_time(segcontext[segmenttype],3));
            plan_start_num[4].setText(getText_plan_start_time(segcontext[segmenttype],4));
            plan_start_num[5].setText(getText_plan_start_time(segcontext[segmenttype],5));
            plan_start_num[6].setText(getText_plan_start_time(segcontext[segmenttype],6));
            plan_start_num[7].setText(getText_plan_start_time(segcontext[segmenttype],7));
            plan_start_num[8].setText(getText_plan_start_time(segcontext[segmenttype],8));
            plan_start_num[9].setText(getText_plan_start_time(segcontext[segmenttype],9));
            plan_start_num[10].setText(getText_plan_start_time(segcontext[segmenttype],10));
            plan_start_num[11].setText(getText_plan_start_time(segcontext[segmenttype],11));
            plan_start_num[12].setText(getText_plan_start_time(segcontext[segmenttype],12));
            plan_start_num[13].setText(getText_plan_start_time(segcontext[segmenttype],13));
            plan_start_num[14].setText(getText_plan_start_time(segcontext[segmenttype],14));
            plan_start_num[15].setText(getText_plan_start_time(segcontext[segmenttype],15));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String getText_plan_start_time(JSONObject jsonObject, int array_index) throws JSONException {
                return jsonObject.getJSONArray("hour").getString(array_index) + ":" + jsonObject.getJSONArray("minute").getString(array_index);
        }

    private void init_segcontext(JSONObject jsonObject, JSONObject[] segcontext) {
        try {
            segcontext[0] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(0);
            segcontext[1] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(1);
            segcontext[2] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(2);
            segcontext[3] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(3);
            segcontext[4] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(4);
            segcontext[5] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(5);
            segcontext[6] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(6);
            segcontext[7] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(7);
            segcontext[8] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(8);
            segcontext[9] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(9);
            segcontext[10] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(10);
            segcontext[11] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(11);
            segcontext[12] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(12);
            segcontext[13] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(13);
            segcontext[14] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(14);
            segcontext[15] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(15);
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
        plan_start_num[0] = rootView.findViewById(R.id.plan_start_num_1);
        plan_start_num[1] = rootView.findViewById(R.id.plan_start_num_2);
        plan_start_num[2] = rootView.findViewById(R.id.plan_start_num_3);
        plan_start_num[3] = rootView.findViewById(R.id.plan_start_num_4);
        plan_start_num[4] = rootView.findViewById(R.id.plan_start_num_5);
        plan_start_num[5] = rootView.findViewById(R.id.plan_start_num_6);
        plan_start_num[6] = rootView.findViewById(R.id.plan_start_num_7);
        plan_start_num[7] = rootView.findViewById(R.id.plan_start_num_8);
        plan_start_num[8] = rootView.findViewById(R.id.plan_start_num_9);
        plan_start_num[9] = rootView.findViewById(R.id.plan_start_num_10);
        plan_start_num[10] = rootView.findViewById(R.id.plan_start_num_11);
        plan_start_num[11] = rootView.findViewById(R.id.plan_start_num_12);
        plan_start_num[12] = rootView.findViewById(R.id.plan_start_num_13);
        plan_start_num[13] = rootView.findViewById(R.id.plan_start_num_14);
        plan_start_num[14] = rootView.findViewById(R.id.plan_start_num_15);
        plan_start_num[15] = rootView.findViewById(R.id.plan_start_num_16);
    }


    private void plan_spin_num_view_init(View rootView, Spinner[] plan_spin_num) {
        plan_spin_num[0] = rootView.findViewById(R.id.plan_spin_num_1);
        plan_spin_num[1] = rootView.findViewById(R.id.plan_spin_num_2);
        plan_spin_num[2] = rootView.findViewById(R.id.plan_spin_num_3);
        plan_spin_num[3] = rootView.findViewById(R.id.plan_spin_num_4);
        plan_spin_num[4] = rootView.findViewById(R.id.plan_spin_num_5);
        plan_spin_num[5] = rootView.findViewById(R.id.plan_spin_num_6);
        plan_spin_num[6] = rootView.findViewById(R.id.plan_spin_num_7);
        plan_spin_num[7] = rootView.findViewById(R.id.plan_spin_num_8);
        plan_spin_num[8] = rootView.findViewById(R.id.plan_spin_num_9);
        plan_spin_num[9] = rootView.findViewById(R.id.plan_spin_num_10);
        plan_spin_num[10] = rootView.findViewById(R.id.plan_spin_num_11);
        plan_spin_num[11] = rootView.findViewById(R.id.plan_spin_num_12);
        plan_spin_num[12] = rootView.findViewById(R.id.plan_spin_num_13);
        plan_spin_num[13] = rootView.findViewById(R.id.plan_spin_num_14);
        plan_spin_num[14] = rootView.findViewById(R.id.plan_spin_num_15);
        plan_spin_num[15] = rootView.findViewById(R.id.plan_spin_num_16);
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

