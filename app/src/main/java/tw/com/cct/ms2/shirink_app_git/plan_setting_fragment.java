package tw.com.cct.ms2.shirink_app_git;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import static tw.com.cct.ms2.shirink_app_git.V3_tc_data.jsonObject;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_fragment extends plan_setting_content {
    final EditText[] plan_start_num = new EditText[16];
    JSONObject[] segcontext = new JSONObject[16];
    Spinner plan_spin_num[] = new Spinner[16];

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
        plan_start_num_set_init_text(plan_start_num, segcontext,1);
        plan_start_time_setonclick(plan_start_num);


        return rootView;
    }



    private void plan_start_num_set_init_text(EditText[] plan_start_num, JSONObject[] segcontext, int segmenttype) {
        try {int i=16;
            plan_start_num[0].setText(getText_plan_start_time(segcontext[segmenttype],i+0));
            plan_start_num[1].setText(getText_plan_start_time(segcontext[segmenttype],i+1));
            plan_start_num[2].setText(getText_plan_start_time(segcontext[segmenttype],i+2));
            plan_start_num[3].setText(getText_plan_start_time(segcontext[segmenttype],i+3));
            plan_start_num[4].setText(getText_plan_start_time(segcontext[segmenttype],i+4));
            plan_start_num[5].setText(getText_plan_start_time(segcontext[segmenttype],i+5));
            plan_start_num[6].setText(getText_plan_start_time(segcontext[segmenttype],i+6));
            plan_start_num[7].setText(getText_plan_start_time(segcontext[segmenttype],i+7));
            plan_start_num[8].setText(getText_plan_start_time(segcontext[segmenttype],i+8));
            plan_start_num[9].setText(getText_plan_start_time(segcontext[segmenttype],i+9));
            plan_start_num[10].setText(getText_plan_start_time(segcontext[segmenttype],i+10));
            plan_start_num[11].setText(getText_plan_start_time(segcontext[segmenttype],i+11));
            plan_start_num[12].setText(getText_plan_start_time(segcontext[segmenttype],i+12));
            plan_start_num[13].setText(getText_plan_start_time(segcontext[segmenttype],i+13));
            plan_start_num[14].setText(getText_plan_start_time(segcontext[segmenttype],i+14));
            plan_start_num[15].setText(getText_plan_start_time(segcontext[segmenttype],i+15));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String getText_plan_start_time(JSONObject jsonObject, int array_index) throws JSONException {
        return jsonObject.getJSONArray("hour").getString(array_index) + ":" + jsonObject.getJSONArray("minute").getString(array_index);
    }
    private void init_segcontext(JSONObject jsonObject, JSONObject[] segcontext) {
        try {int i=16;
            segcontext[0] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(0+i);
            segcontext[1] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(1+i);
            segcontext[2] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(2+i);
            segcontext[3] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(3+i);
            segcontext[4] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(4+i);
            segcontext[5] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(5+i);
            segcontext[6] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(6+i);
            segcontext[7] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(7+i);
            segcontext[8] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(8+i);
            segcontext[9] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(9+i);
            segcontext[10] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(10+i);
            segcontext[11] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(11+i);
            segcontext[12] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(12+i);
            segcontext[13] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(13+i);
            segcontext[14] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(14+i);
            segcontext[15] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(15+i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void plan_start_time_setonclick(final EditText[] plan_start_num) {
          plan_start_num[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[16]);
            }

        });
        plan_start_num[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[17]);
            }

        });
        plan_start_num[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[18]);
            }

        });
        plan_start_num[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[19]);
            }

        });
        plan_start_num[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[20]);
            }

        });
        plan_start_num[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[21]);
            }

        });
        plan_start_num[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[22]);
            }

        });
        plan_start_num[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[23]);
            }

        });
        plan_start_num[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[24]);
            }

        });
        plan_start_num[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[25]);
            }

        });
        plan_start_num[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[26]);
            }

        });
        plan_start_num[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[27]);
            }

        });
        plan_start_num[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[28]);
            }

        });
        plan_start_num[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[29]);
            }

        });
        plan_start_num[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[30]);
            }

        });
        plan_start_num[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TimerSetter(plan_start_num[31]);
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
