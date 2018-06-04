package tw.com.cct.ms2.shirink_app_git;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class tod_setting extends Base_activity {
    final EditText specialday_end_date[] = new EditText[13];
    final EditText specialday_start_date[] = new EditText[13];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("1", "onCreate: ");

        setContentView(R.layout.tod_setting);

        Button goto_plan_button = (Button) findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml = new Intent(tod_setting.this, plan_setting.class);
        Button_goto_where(goto_plan_button, plan_setting_xml);
        V3_tc_data A=V3_tc_data.getV3_tc_data();
        JSONObject jsonObject=A.getV3_json_data();
        JSONObject[] specialdaycontext=new JSONObject[13];
        Spinner spin_common_day[] = new Spinner[7];
        Spinner_init(spin_common_day);
        ArrayAdapter<CharSequence> arrayAdapter_tod_spinner = ArrayAdapter.createFromResource(this, R.array.tod_segment, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        tod_spin_adapter_init(spin_common_day, arrayAdapter_tod_spinner);
        init_spiner_value(jsonObject, spin_common_day);
        special_day_init();
        specialday_init_value(jsonObject, specialdaycontext);
///////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        floating_button_function(setting_button_group, tod_setting.this);
///////////////////////////////////////////////////////////////////////////////////////////////




    }

    private void specialday_init_value(JSONObject jsonObject, JSONObject[] specialdaycontext) {
               try {
            specialdaycontext[0]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(0);
            specialdaycontext[1]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(1);
            specialdaycontext[2]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(2);
            specialdaycontext[3]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(3);
            specialdaycontext[4]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(4);
            specialdaycontext[5]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(5);
            specialdaycontext[6]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(6);
            specialdaycontext[7]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(7);
            specialdaycontext[8]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(8);
            specialdaycontext[9]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(9);
            specialdaycontext[10]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(10);
            specialdaycontext[11]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(11);
            specialdaycontext[12]=jsonObject.getJSONArray("specialdaycontext").getJSONObject(12);
            specialday_start_date[0].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[0].getString("start_year")),
                    Integer.valueOf(specialdaycontext[0].getString("start_month")),
                    Integer.valueOf(specialdaycontext[0].getString("start_day"))));
                   Log.d("321123", "specialday_init_value: "+specialdaycontext[0].getString("start_month"));
            specialday_end_date[0].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[0].getString("end_year")),
                    Integer.valueOf(specialdaycontext[0].getString("end_month")),
                    Integer.valueOf(specialdaycontext[0].getString("end_day"))));
            specialday_start_date[1].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[1].getString("start_year")),
                    Integer.valueOf(specialdaycontext[1].getString("start_month")),
                    Integer.valueOf(specialdaycontext[1].getString("start_day"))));
            specialday_end_date[1].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[1].getString("end_year")),
                    Integer.valueOf(specialdaycontext[1].getString("end_month")),
                    Integer.valueOf(specialdaycontext[1].getString("end_day"))));
            specialday_start_date[2].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[2].getString("start_year")),
                    Integer.valueOf(specialdaycontext[2].getString("start_month")),
                    Integer.valueOf(specialdaycontext[2].getString("start_day"))));
            specialday_end_date[2].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[2].getString("end_year")),
                    Integer.valueOf(specialdaycontext[2].getString("end_month")),
                    Integer.valueOf(specialdaycontext[2].getString("end_day"))));
            specialday_start_date[3].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[3].getString("start_year")),
                    Integer.valueOf(specialdaycontext[3].getString("start_month")),
                    Integer.valueOf(specialdaycontext[3].getString("start_day"))));
            specialday_end_date[3].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[3].getString("end_year")),
                    Integer.valueOf(specialdaycontext[3].getString("end_month")),
                    Integer.valueOf(specialdaycontext[3].getString("end_day"))));
            specialday_start_date[4].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[4].getString("start_year")),
                    Integer.valueOf(specialdaycontext[4].getString("start_month")),
                    Integer.valueOf(specialdaycontext[4].getString("start_day"))));
            specialday_end_date[4].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[4].getString("end_year")),
                    Integer.valueOf(specialdaycontext[4].getString("end_month")),
                    Integer.valueOf(specialdaycontext[4].getString("end_day"))));
            specialday_start_date[5].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[5].getString("start_year")),
                    Integer.valueOf(specialdaycontext[5].getString("start_month")),
                    Integer.valueOf(specialdaycontext[5].getString("start_day"))));
            specialday_end_date[5].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[5].getString("end_year")),
                    Integer.valueOf(specialdaycontext[5].getString("end_month")),
                    Integer.valueOf(specialdaycontext[5].getString("end_day"))));
            specialday_start_date[6].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[6].getString("start_year")),
                    Integer.valueOf(specialdaycontext[6].getString("start_month")),
                    Integer.valueOf(specialdaycontext[6].getString("start_day"))));
            specialday_end_date[6].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[6].getString("end_year")),
                    Integer.valueOf(specialdaycontext[6].getString("end_month")),
                    Integer.valueOf(specialdaycontext[6].getString("end_day"))));
            specialday_start_date[7].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[7].getString("start_year")),
                    Integer.valueOf(specialdaycontext[7].getString("start_month")),
                    Integer.valueOf(specialdaycontext[7].getString("start_day"))));
            specialday_end_date[7].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[7].getString("end_year")),
                    Integer.valueOf(specialdaycontext[7].getString("end_month")),
                    Integer.valueOf(specialdaycontext[7].getString("end_day"))));
            specialday_start_date[8].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[8].getString("start_year")),
                    Integer.valueOf(specialdaycontext[8].getString("start_month")),
                    Integer.valueOf(specialdaycontext[8].getString("start_day"))));
            specialday_end_date[8].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[8].getString("end_year")),
                    Integer.valueOf(specialdaycontext[8].getString("end_month")),
                    Integer.valueOf(specialdaycontext[8].getString("end_day"))));
            specialday_start_date[9].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[9].getString("start_year")),
                    Integer.valueOf(specialdaycontext[9].getString("start_month")),
                    Integer.valueOf(specialdaycontext[9].getString("start_day"))));
            specialday_end_date[9].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[9].getString("end_year")),
                    Integer.valueOf(specialdaycontext[9].getString("end_month")),
                    Integer.valueOf(specialdaycontext[9].getString("end_day"))));
            specialday_start_date[10].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[10].getString("start_year")),
                    Integer.valueOf(specialdaycontext[10].getString("start_month")),
                    Integer.valueOf(specialdaycontext[10].getString("start_day"))));
            specialday_end_date[10].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[10].getString("end_year")),
                    Integer.valueOf(specialdaycontext[10].getString("end_month")),
                    Integer.valueOf(specialdaycontext[10].getString("end_day"))));
            specialday_start_date[11].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[11].getString("start_year")),
                    Integer.valueOf(specialdaycontext[11].getString("start_month")),
                    Integer.valueOf(specialdaycontext[11].getString("start_day"))));
            specialday_end_date[11].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[11].getString("end_year")),
                    Integer.valueOf(specialdaycontext[11].getString("end_month")),
                    Integer.valueOf(specialdaycontext[11].getString("end_day"))));
            specialday_start_date[12].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[12].getString("start_year")),
                    Integer.valueOf(specialdaycontext[12].getString("start_month")),
                    Integer.valueOf(specialdaycontext[12].getString("start_day"))));
            specialday_end_date[12].setText(setDateFormat(
                    Integer.valueOf(specialdaycontext[12].getString("end_year")),
                    Integer.valueOf(specialdaycontext[12].getString("end_month")),
                    Integer.valueOf(specialdaycontext[12].getString("end_day"))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init_spiner_value(JSONObject jsonObject, Spinner[] spin_common_day) {
        try {
            JSONArray jsonArray;
            jsonArray=jsonObject.getJSONArray("weekdaysegment");
            spin_common_day[0].setSelection((Integer) jsonArray.get(0));
            spin_common_day[1].setSelection((Integer) jsonArray.get(1));
            spin_common_day[2].setSelection((Integer) jsonArray.get(2));
            spin_common_day[3].setSelection((Integer) jsonArray.get(3));
            spin_common_day[4].setSelection((Integer) jsonArray.get(4));
            spin_common_day[5].setSelection((Integer) jsonArray.get(5));
            spin_common_day[6].setSelection((Integer) jsonArray.get(6));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void special_day_init() {

        special_day_view_init(specialday_end_date, specialday_start_date);
        Special_day_setting_init(specialday_end_date, specialday_start_date);



    }

    private void Special_day_setting_init(EditText[] specialday_end_date, EditText[] specialday_start_date) {
        Specialday_date_setting_function(specialday_end_date[0]);
        Specialday_date_setting_function(specialday_end_date[1]);
        Specialday_date_setting_function(specialday_end_date[2]);
        Specialday_date_setting_function(specialday_end_date[3]);
        Specialday_date_setting_function(specialday_end_date[4]);
        Specialday_date_setting_function(specialday_end_date[5]);
        Specialday_date_setting_function(specialday_end_date[6]);
        Specialday_date_setting_function(specialday_end_date[7]);
        Specialday_date_setting_function(specialday_end_date[8]);
        Specialday_date_setting_function(specialday_end_date[9]);
        Specialday_date_setting_function(specialday_end_date[10]);
        Specialday_date_setting_function(specialday_end_date[11]);
        Specialday_date_setting_function(specialday_end_date[12]);
///////////////////////////////////////////////////////////////////////////
        Specialday_date_setting_function(specialday_start_date[0]);
        Specialday_date_setting_function(specialday_start_date[1]);
        Specialday_date_setting_function(specialday_start_date[2]);
        Specialday_date_setting_function(specialday_start_date[3]);
        Specialday_date_setting_function(specialday_start_date[4]);
        Specialday_date_setting_function(specialday_start_date[5]);
        Specialday_date_setting_function(specialday_start_date[6]);
        Specialday_date_setting_function(specialday_start_date[7]);
        Specialday_date_setting_function(specialday_start_date[8]);
        Specialday_date_setting_function(specialday_start_date[9]);
        Specialday_date_setting_function(specialday_start_date[10]);
        Specialday_date_setting_function(specialday_start_date[11]);
        Specialday_date_setting_function(specialday_start_date[12]);
    }

    private void special_day_view_init(EditText[] specialday_end_date, EditText[] specialday_start_date) {
        specialday_end_date[0] = findViewById(R.id.specialday_end_date_8);
        specialday_end_date[1] = findViewById(R.id.specialday_end_date_9);
        specialday_end_date[2] = findViewById(R.id.specialday_end_date_10);
        specialday_end_date[3] = findViewById(R.id.specialday_end_date_11);
        specialday_end_date[4] = findViewById(R.id.specialday_end_date_12);
        specialday_end_date[5] = findViewById(R.id.specialday_end_date_13);
        specialday_end_date[6] = findViewById(R.id.specialday_end_date_14);
        specialday_end_date[7] = findViewById(R.id.specialday_end_date_15);
        specialday_end_date[8] = findViewById(R.id.specialday_end_date_16);
        specialday_end_date[9] = findViewById(R.id.specialday_end_date_17);
        specialday_end_date[10] = findViewById(R.id.specialday_end_date_18);
        specialday_end_date[11] = findViewById(R.id.specialday_end_date_19);
        specialday_end_date[12] = findViewById(R.id.specialday_end_date_20);

        specialday_start_date[0] = findViewById(R.id.specialday_start_date_8);
        specialday_start_date[1] = findViewById(R.id.specialday_start_date_9);
        specialday_start_date[2] = findViewById(R.id.specialday_start_date_10);
        specialday_start_date[3] = findViewById(R.id.specialday_start_date_11);
        specialday_start_date[4] = findViewById(R.id.specialday_start_date_12);
        specialday_start_date[5] = findViewById(R.id.specialday_start_date_13);
        specialday_start_date[6] = findViewById(R.id.specialday_start_date_14);
        specialday_start_date[7] = findViewById(R.id.specialday_start_date_15);
        specialday_start_date[8] = findViewById(R.id.specialday_start_date_16);
        specialday_start_date[9] = findViewById(R.id.specialday_start_date_17);
        specialday_start_date[10] = findViewById(R.id.specialday_start_date_18);
        specialday_start_date[11] = findViewById(R.id.specialday_start_date_19);
        specialday_start_date[12] = findViewById(R.id.specialday_start_date_20);
    }

    private void Specialday_date_setting_function(final EditText specialday_date_edit) {
        specialday_date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
            /*    int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(tod_setting.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        specialday_date_edit.setText( specialday_date_edit.getText() +  String.valueOf(view.getHour()) + ":" + String.valueOf(view.getMinute()));
                    }
                }, hour, minute, false).show();*/
                new DatePickerDialog(tod_setting.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        //String format = getString(R.string.set_date) + setDateFormat(year,month,day);
                        String format = setDateFormat(year, month, day);
                        specialday_date_edit.setText(format);
                    }
                }, mYear, mMonth, mDay).show();

            }
        });
    }

    private void tod_spin_adapter_init(Spinner[] spin_common_day, ArrayAdapter<CharSequence> arrayAdapter_tod_spinner) {
        spin_common_day[1].setAdapter(arrayAdapter_tod_spinner);
        spin_common_day[2].setAdapter(arrayAdapter_tod_spinner);
        spin_common_day[3].setAdapter(arrayAdapter_tod_spinner);
        spin_common_day[4].setAdapter(arrayAdapter_tod_spinner);
        spin_common_day[5].setAdapter(arrayAdapter_tod_spinner);
        spin_common_day[6].setAdapter(arrayAdapter_tod_spinner);
        spin_common_day[0].setAdapter(arrayAdapter_tod_spinner);
    }


    private Spinner.OnItemSelectedListener spin_common_day =
            new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String sel = parent.getSelectedItem().toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }

            };

    private void Spinner_init(Spinner[] spin_common_day) {
        spin_common_day[0] = findViewById(R.id.Spin_common_day_1);
        spin_common_day[1] = findViewById(R.id.Spin_common_day_2);
        spin_common_day[2] = findViewById(R.id.Spin_common_day_3);
        spin_common_day[3] = findViewById(R.id.Spin_common_day_4);
        spin_common_day[4] = findViewById(R.id.Spin_common_day_5);
        spin_common_day[5] = findViewById(R.id.Spin_common_day_6);
        spin_common_day[6] = findViewById(R.id.Spin_common_day_7);
    }




    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear ) + "-"
                + String.valueOf(dayOfMonth);
    }

  /*  private String setDateAndTimeFormat(int year, int monthOfYear, int dayOfMonth,int hour,int minute)
    {

        return   String.valueOf(year) + "-"
                + String.valueOf(monthOfYear) + "-"
                + String.valueOf(dayOfMonth)+" "
                +String.valueOf(hour)+":"
                +String.valueOf(minute);

    }*/

}
