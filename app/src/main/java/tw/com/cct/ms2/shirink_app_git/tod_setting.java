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

import java.util.Calendar;

public class tod_setting extends Base_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("1", "onCreate: ");

        setContentView(R.layout.tod_setting);

        Button goto_plan_button = (Button) findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml = new Intent(tod_setting.this, plan_setting.class);
        Button_goto_where(goto_plan_button, plan_setting_xml);


        Spinner spin_common_day[] = new Spinner[7];
        Spinner_init(spin_common_day);
        ArrayAdapter<CharSequence> arrayAdapter_tod_spinner = ArrayAdapter.createFromResource(this, R.array.tod_segment, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        tod_spin_adapter_init(spin_common_day, arrayAdapter_tod_spinner);
        special_day_init();


        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Log.d("test2", "onCreate: ");
        floating_button_function(setting_button_group, tod_setting.this);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    private void special_day_init() {
        final EditText specialday_end_date[] = new EditText[13];
        final EditText specialday_start_date[] = new EditText[13];
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
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                new TimePickerDialog(tod_setting.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        specialday_date_edit.setText("日期:" + specialday_date_edit.getText() + " 時間:" + String.valueOf(view.getHour()) + ":" + String.valueOf(view.getMinute()));
                    }
                }, hour, minute, false).show();
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
        spin_common_day[1] = findViewById(R.id.Spin_common_day_1);
        spin_common_day[2] = findViewById(R.id.Spin_common_day_2);
        spin_common_day[3] = findViewById(R.id.Spin_common_day_3);
        spin_common_day[4] = findViewById(R.id.Spin_common_day_4);
        spin_common_day[5] = findViewById(R.id.Spin_common_day_5);
        spin_common_day[6] = findViewById(R.id.Spin_common_day_6);
        spin_common_day[0] = findViewById(R.id.Spin_common_day_7);
    }


    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }

}
