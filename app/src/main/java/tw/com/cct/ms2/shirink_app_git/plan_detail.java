package tw.com.cct.ms2.shirink_app_git;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class plan_detail extends Base_activity {
    EditText[] red_light = new EditText[8];
    EditText[] green_light = new EditText[8];
    EditText[] yellow_light = new EditText[8];
    EditText[] ped_red = new EditText[8];
    EditText[] ped_flash = new EditText[8];
    EditText plan_offset_edit;
    TextView plan_cycle_value;
    EditText segment_name_value;
    EditText[] maxgreen=new EditText[8];
    EditText[] mingreen=new EditText[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_detail_layout);
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Log.d("plan_setting", "onCreate: ");
        floating_button_function(setting_button_group, plan_detail.this);
        Spinner plan_select = findViewById(R.id.plan_select);

        ArrayAdapter<CharSequence> arrayAdapter_plan_select_spinner = ArrayAdapter.createFromResource(this, R.array.plan, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        plan_select.setAdapter(arrayAdapter_plan_select_spinner);


        Button link_to_step_setting = findViewById(R.id.link_to_step_setting);
        Intent step_setting = new Intent(plan_detail.this, tw.com.cct.ms2.shirink_app_git.step_setting.class);
        Button_goto_where(link_to_step_setting, step_setting);
        V3_tc_data A = V3_tc_data.getV3_tc_data();
        final JSONObject jsonObject = A.getV3_json_data();
        final JSONObject []plancontext=new JSONObject[49];
        plan_cycle_value=findViewById(R.id.plan_cycle_value);
        plan_offset_edit=findViewById(R.id.plan_offset_edit);
        for(int i=0;i<49;i++) {
            try {
                plancontext[i]=jsonObject.getJSONArray("plancontext").optJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Edittext_findview();
        plan_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plan_select_chain_context(plancontext[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        segment_name_value = findViewById(R.id.segment_name_value);

    }

    private void plan_select_chain_context(JSONObject jsonObject) {
        try {

            segment_name_value.setText(
                    Integer.toHexString(
                            Integer.parseInt(
                    jsonObject.getString("phase_order")))
                    );
            Log.d("!!!", "plan_select_chain_context:green "+ jsonObject
                    .getJSONArray("subphase_green").get(0));
            for(int i=0;i<8;i++)
            {
                green_light[i].setText(jsonObject
                                .getJSONArray("subphase_green").get(i).toString());
                red_light[i].setText(jsonObject
                        .getJSONArray("subphase_allred").get(i).toString());
                yellow_light[i].setText(jsonObject
                        .getJSONArray("subphase_yellow").get(i).toString());
                ped_flash[i].setText(jsonObject
                        .getJSONArray("subphase_pedgreen_flash").get(i).toString());
                ped_red[i].setText(jsonObject
                        .getJSONArray("subphase_pedred").get(i).toString());
                maxgreen[i].setText(jsonObject
                    .getJSONArray("subphase_max_green").get(i).toString());
                mingreen[i].setText(jsonObject
                        .getJSONArray("subphase_min_green").get(i).toString());

            }
            plan_cycle_value.setText(jsonObject.getString("cycle_time"));
            plan_offset_edit.setText(jsonObject.getString("offset"));


            //Log.d("!!!", "plan_select_chain_context: "+jsonObject.getJSONArray("plancontext").optJSONObject(plan).toString());

            } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }

    private void Edittext_findview() {
        for (int i = 1; i < 9; i++) {

            String idname_g = "green_light_" + String.format("%d", i);
            String idname_r = "red_light_" + String.format("%d", i);
            String idname_y = "yellow_light_" + String.format("%d", i);
            String idname_pg = "ped_flash_" + String.format("%d", i);
            String idname_pr = "ped_red_" + String.format("%d", i);
            String idname_mxg="maxgreen_"+ String.format("%d", i);
            String idname_mig="mingreen_"+ String.format("%d", i);
           int resID_g = getResources().getIdentifier(idname_g, "id", getPackageName());
            green_light[i - 1] = findViewById(resID_g);
            int resID_r = getResources().getIdentifier(idname_r, "id", getPackageName());
            red_light[i - 1] = findViewById(resID_r);
            int resID_y = getResources().getIdentifier(idname_y, "id", getPackageName());
            yellow_light[i - 1] = findViewById(resID_y);
            int resID_pg = getResources().getIdentifier(idname_pg, "id", getPackageName());
            ped_flash[i - 1] = findViewById(resID_pg);
            int resID_pr = getResources().getIdentifier(idname_pr, "id", getPackageName());
            ped_red[i - 1] = findViewById(resID_pr);
            int resID_mxg= getResources().getIdentifier(idname_mxg, "id", getPackageName());
            maxgreen[i - 1] = findViewById(resID_mxg);
            int resID_mig= getResources().getIdentifier(idname_mig, "id", getPackageName());
            mingreen[i - 1] = findViewById(resID_mig);


        }
    }


}