package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hamsa.twosteppickerdialog.OnStepDataRequestedListener;
import com.hamsa.twosteppickerdialog.OnStepPickListener;
import com.hamsa.twosteppickerdialog.TwoStepPickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class plan_detail extends Base_activity {
    EditText[] red_light = new EditText[8];
    EditText[] green_light = new EditText[8];
    EditText[] yellow_light = new EditText[8];
    EditText[] ped_red = new EditText[8];
    EditText[] ped_flash = new EditText[8];
    EditText plan_offset_edit;
    TextView plan_cycle_value;
    EditText phase_order_value;
    EditText[] maxgreen = new EditText[8];
    EditText[] mingreen = new EditText[8];
    Switch edittext_switch;
    TextView[]subphase_name=new TextView[8];
    int plan_num;//目前plan
    V3_tc_data A = V3_tc_data.getV3_tc_data();
    PlanContext planContext= V3_tc_data.getPlanContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_detail_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Log.d("plan_setting", "onCreate: ");
        //floating_button_function(setting_button_group, plan_detail.this);

        final Spinner plan_select = getSpinner_plan();
        link_to_step_setting();
//
//        final JSONObject jsonObject = A.getV3_json_data();
//        final JSONObject[] plancontext = new JSONObject[49];
//

//        plancontext_init(jsonObject, plancontext);
        plan_spinner_setting(plan_select);

        plan_cycle_value = findViewById(R.id.plan_cycle_value);
        plan_offset_edit = findViewById(R.id.plan_offset_edit);
        edittext_switch = findViewById(R.id.edittext_switch);
        phase_order_value = findViewById(R.id.phase_order_value);

        Edittext_findview();
        light_edit_setting();
        edittext_switch_group(edittext_switch.isChecked());
        edittext_visible_control(planContext.subphase_count[plan_num]);
        edittext_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_switch_group(edittext_switch.isChecked());
                refresh_cycle_value();
            }
        });
        FloatingButtonGroup();


        tcpClass.connect();

    }
    private void FloatingButtonGroup() {
        FloatingActionButton setting_button_group =  findViewById(R.id.setting_button_group);
        setting_button_group.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
//應該要全頁面拿來共用此按鈕
                PopupMenu button_popmenu = new PopupMenu(plan_detail.this, view);
                button_popmenu.getMenuInflater().inflate(R.menu.button_popmenu, button_popmenu.getMenu());
                button_popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                         Do something...
                        switch (item.getItemId()) {
                            case R.id.enter:
                                Snackbar.make(view, "設定", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
//                                try {
//                                    plancontext[plan_num].put("cycle_time",getCycle_value);
//                                    plancontext[plan_num].put("offset",getOffset[0]);
//                                    plancontext[plan_num].put("phase_order",phase_order_value.getText());
//                                    for(int i=0;i<8;i++) {
//                                        plancontext[plan_num].optJSONArray("subphase_allred").put(i,red[i]);
//                                        plancontext[plan_num].optJSONArray("subphase_green").put(i,green[i]);
//                                        plancontext[plan_num].optJSONArray("subphase_yellow").put(i,yellow[i]);
//                                        plancontext[plan_num].optJSONArray("subphase_pedgreen_flash").put(i,pedF[i]);
//                                        plancontext[plan_num].optJSONArray("subphase_pedred").put(i,pedR[i]);
//                                        plancontext[plan_num].optJSONArray("subphase_min_green").put(i,minG[i]);
//                                        plancontext[plan_num].optJSONArray("subphase_max_green").put(i,maxG[i]);
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
                                V3_tc_data.setPlanContext(planContext);


                                Log.d("123321123", "onMenuItemClick: "+A.sendPlanContextToTc(plan_num));
                                tcpClass.send(A.sendPlanContextToTc(plan_num));
                                return true;
                            case R.id.cancel:
                                Snackbar.make(view, "取消", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();


//                                Log.d("!!!", "onMenuItemClick: "+plancontext[plan_num].toString());
                                return true;
                            case R.id.reset:
                                A.refreshdata();
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
    }
    private void edittext_visible_control(int subphasecount) {
        for(int i=subphasecount;i<8;i++) {
            green_light[i].setVisibility(View.GONE);
            red_light[i].setVisibility(View.GONE);
            yellow_light[i].setVisibility(View.GONE);
            ped_flash[i].setVisibility(View.GONE);
            ped_red[i].setVisibility(View.GONE);
            maxgreen[i].setVisibility(View.GONE);
            mingreen[i].setVisibility(View.GONE);
            subphase_name[i].setVisibility(View.GONE);

        }
        for(int i=0;i<subphasecount;i++) {
            green_light[i].setVisibility(View.VISIBLE);
            red_light[i].setVisibility(View.VISIBLE);
            yellow_light[i].setVisibility(View.VISIBLE);
            ped_flash[i].setVisibility(View.VISIBLE);
            ped_red[i].setVisibility(View.VISIBLE);
            maxgreen[i].setVisibility(View.VISIBLE);
            mingreen[i].setVisibility(View.VISIBLE);
            subphase_name[i].setVisibility(View.VISIBLE);

        }
    }
    private void light_edit_setting() {
        for(int LightBoard = 0; LightBoard <8; LightBoard++)
        {
            edittext_method(green_light[LightBoard], LightBoard,planContext.green[plan_num]);
            edittext_method(red_light[LightBoard], LightBoard,planContext.red[plan_num]);
            edittext_method(yellow_light[LightBoard], LightBoard,planContext.yellow[plan_num]);
            edittext_method(ped_flash[LightBoard], LightBoard,planContext.pedF[plan_num]);
            edittext_method(ped_red[LightBoard], LightBoard,planContext.pedR[plan_num]);
            edittext_method(maxgreen[LightBoard], LightBoard,planContext.maxG[plan_num]);
            edittext_method(mingreen[LightBoard], LightBoard,planContext.minG[plan_num]);
        }
        edittext_method(plan_offset_edit,plan_num,planContext.Offset);
    }
    private void edittext_method(final EditText editText, final int i,final int[] value_record) {

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {   // 按下完成按钮，这里和上面imeOptions对应
                    if (editText.getText().toString().equals("")) {
                        editText.setText("0");
                        value_record[i]=0;
                    } else value_record[i] = Integer.valueOf(editText.getText().toString());
                    Log.d("value_record", "onClick: " + value_record[i]);
                    refresh_cycle_value();
                }
                return false;
            }//返回true，保留软键盘。false，隐藏软键盘

        });
editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (editText.getText().toString().equals("")) {
            editText.setText("0");
            value_record[i]=0;
        } else value_record[i] = Integer.valueOf(editText.getText().toString());
        Log.d("value_record", "onClick: " + value_record[i]);
        refresh_cycle_value();
    }
});


    }
    private void refresh_cycle_value() {
                plan_cycle_value.setText(String.valueOf(planContext.getCycle_value(plan_num)));
        Log.d("!!!", "refresh_cycle_value: " + planContext.getCycle_value(plan_num));
    }
    private void edittext_switch_group(boolean editbla_flag) {

        for (int i = 0; i < 8; i++) {
            red_light[i].setEnabled(editbla_flag);
            green_light[i].setEnabled(editbla_flag);
            yellow_light[i].setEnabled(editbla_flag);
            ped_red[i].setEnabled(editbla_flag);
            ped_flash[i].setEnabled(editbla_flag);
            maxgreen[i].setEnabled(editbla_flag);
            mingreen[i].setEnabled(editbla_flag);

        }
        plan_offset_edit.setEnabled(editbla_flag);
        phase_order_value.setEnabled(editbla_flag);


    }
    private void plan_spinner_setting(Spinner plan_select) {
        plan_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plan_num=position;
                plan_select_chain_context(plan_num);
//                try {
//                    temp_value_init(plancontext[position]);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                edittext_visible_control(planContext.subphase_count[plan_num]);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void link_to_step_setting() {
        Button link_to_step_setting = findViewById(R.id.link_to_step_setting);
        Intent step_setting = new Intent(plan_detail.this, tw.com.cct.ms2.shirink_app_git.step_setting.class);
        Button_goto_where(link_to_step_setting, step_setting);
    }
    @NonNull
    private Spinner getSpinner_plan() {
        Spinner plan_select = findViewById(R.id.plan_select);

        ArrayAdapter<CharSequence> arrayAdapter_plan_select_spinner = ArrayAdapter.createFromResource(this, R.array.plan, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        plan_select.setAdapter(arrayAdapter_plan_select_spinner);
        return plan_select;
    }
    private void plan_select_chain_context(int PlanNum) {
//  phase_order_value.setText(Integer.toHexString(Integer.parseInt(String.valueOf(planContext.PhaseOrderOfPlan[PlanNum]))));
        PhaseOrderSelect_init(PlanNum);
//            Log.d("!!!", "plan_select_chain_context:green " + jsonObject.getJSONArray("subphase_green").get(0));
            for (int LightBoard = 0; LightBoard < 8; LightBoard++) {
                green_light[LightBoard].setText(String.valueOf(planContext.green[PlanNum][LightBoard]));
                red_light[LightBoard].setText(String.valueOf(planContext.red[PlanNum][LightBoard]));
                yellow_light[LightBoard].setText(String.valueOf(planContext.yellow[PlanNum][LightBoard]));
                ped_flash[LightBoard].setText(String.valueOf(planContext.pedF[PlanNum][LightBoard]));
                ped_red[LightBoard].setText(String.valueOf(planContext.pedR[PlanNum][LightBoard]));
                maxgreen[LightBoard].setText(String.valueOf(planContext.maxG[PlanNum][LightBoard]));
                mingreen[LightBoard].setText(String.valueOf(planContext.minG[PlanNum][LightBoard]));

            }
            plan_cycle_value.setText(String.valueOf(planContext.getCycle_value(PlanNum)));
            plan_offset_edit.setText(String.valueOf(planContext.Offset[PlanNum]));
            //Log.d("!!!", "plan_select_chain_context: "+jsonObject.getJSONArray("plancontext").optJSONObject(plan).toString());

    }
    private void Edittext_findview() {
        for (int i = 1; i < 9; i++) {
            String idname_g = "green_light_" + String.format("%d", i);
            String idname_r = "red_light_" + String.format("%d", i);
            String idname_y = "yellow_light_" + String.format("%d", i);
            String idname_pg = "ped_flash_" + String.format("%d", i);
            String idname_pr = "ped_red_" + String.format("%d", i);
            String idname_mxg = "maxgreen_" + String.format("%d", i);
            String idname_mig = "mingreen_" + String.format("%d", i);
            String idname_subphase="subphase_"+String.format("%d", i);
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
            int resID_mxg = getResources().getIdentifier(idname_mxg, "id", getPackageName());
            maxgreen[i - 1] = findViewById(resID_mxg);
            int resID_mig = getResources().getIdentifier(idname_mig, "id", getPackageName());
            mingreen[i - 1] = findViewById(resID_mig);
            int resID_subphase = getResources().getIdentifier(idname_subphase, "id", getPackageName());
            subphase_name[i - 1] = findViewById(resID_subphase);

        }
    }
    @Override
    protected void onResume() {
        //change_Editable(false);
        super.onResume();
        tcpClass.connect();
    }

    @Override
    protected void onDestroy() {
        //change_Editable(false);
        tcpClass.SocketDestroy();
        super.onDestroy();

    }

    @Override
    public void onStop() {
        tcpClass.SocketDestroy();
        //change_Editable(false);
        super.onStop();

    }
    public void PhaseOrderSelect_init(final int PlanNum) {
//參考自  https://github.com/aliab/Two-Step-Picker-Dialog
        //phase_order_value = findViewById(R.id.phase_order_value);
        phase_order_value.setText(Integer.toHexString(planContext.PhaseOrderOfPlan[PlanNum]));
        final List<String> hexData = getHexStrings();
        final TwoStepPickerDialog pickPhaseOrder = new TwoStepPickerDialog.Builder(this).withBaseData(hexData).withOkButton("確定").withCancelButton("取消").withInitialBaseSelected(planContext.PhaseOrderOfPlan[PlanNum] % 16)//右邊
                .withInitialStepSelected(planContext.PhaseOrderOfPlan[PlanNum] / 16)//左邊
                .withOnStepDataRequested(new OnStepDataRequestedListener() {
                    @Override
                    public List<String> onStepDataRequest(int useless) {//不用參數 當void 用
                        return hexData;
                    }
                }).withDialogListener(new OnStepPickListener() {
                    @Override
                    public void onStepPicked(int firstWord, int secondWord) {//左邊 右邊
                        int position = secondWord + firstWord * 16;
                                             planContext.PhaseOrderOfPlan[PlanNum] = position;
                        planContext.PhaseOrderOfPlan[PlanNum]=position;
                        planContext.subphase_count[PlanNum]=A.getTotalSubphaseCount(position);
                        /*要重載一次頁面，因為分相數會變*/
                        edittext_visible_control(planContext.subphase_count[PlanNum]);
                        Log.d("6546", "onStepPicked: count="+planContext.subphase_count[PlanNum]);
                        phase_order_value.setText(Integer.toHexString(position));

                    }

                    @Override
                    public void onDismissed() {
                        Toast.makeText(getApplicationContext(), "Dismised!", Toast.LENGTH_SHORT).show();
                    }
                }).build();


        phase_order_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickPhaseOrder.setDefaultBaseSelected(planContext.PhaseOrderOfPlan[PlanNum] / 16);//左邊
                pickPhaseOrder.setDefaultStepSelected(planContext.PhaseOrderOfPlan[PlanNum] % 16);//右邊
                pickPhaseOrder.show();
            }
        });
    }
    @NonNull
    private List<String> getHexStrings() {
        final List<String> Hex = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            Hex.add(Integer.toHexString(i));
        return Hex;
    }

}



