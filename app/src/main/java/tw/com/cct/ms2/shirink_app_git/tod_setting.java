package tw.com.cct.ms2.shirink_app_git;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class tod_setting extends Base_activity {
    final EditText specialday_end_date[] = new EditText[13];
    final EditText specialday_start_date[] = new EditText[13];
    int[] start_year = new int[13];
    int[] start_month = new int[13];
    int[] start_day = new int[13];
    int[] end_year = new int[13];
    int[] end_month = new int[13];
    int[] end_day = new int[13];
    int[] weekday = new int[14];
    JSONArray jsonArray=new JSONArray();
    JSONArray weekdayArray=new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tod_setting);
        Button goto_plan_button = (Button) findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml = new Intent(tod_setting.this, plan_setting.class);
        Button_goto_where(goto_plan_button, plan_setting_xml);
        V3_tc_data A = V3_tc_data.getV3_tc_data();
        final JSONObject jsonObject = A.getV3_json_data();
        JSONObject[] specialdaycontext = new JSONObject[13];
        Spinner spin_common_day[] = new Spinner[7];
        Spinner_init(spin_common_day);
        ArrayAdapter<CharSequence> arrayAdapter_tod_spinner = ArrayAdapter.createFromResource(this, R.array.tod_segment, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        tod_spin_adapter_init(spin_common_day, arrayAdapter_tod_spinner);

        special_day_init();
        try {
            jsonArray=jsonObject.getJSONArray("specialdaycontext");
            weekdayArray=jsonObject.getJSONArray("weekdaysegment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        init_spiner_value(weekdayArray, spin_common_day);
        specialday_init_value(jsonArray, specialdaycontext);
///////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        setting_button_group.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
//應該要全頁面拿來共用此按鈕
                PopupMenu button_popmenu = new PopupMenu(tod_setting.this, view);
                button_popmenu.getMenuInflater().inflate(R.menu.button_popmenu, button_popmenu.getMenu());
                button_popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                         Do something...
                        switch (item.getItemId()) {
                            case R.id.enter:
                                boolean toast_flag=false;
                                //ArrayList<String> myList = new ArrayList<String>();  //指定是String的型態
                                ArrayList errorList = new ArrayList();
                                Snackbar.make(view, "設定", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                for(int i=0;i<13;i++) {
                                    if(date_rational_checked(start_year[i],start_month[i],start_day[i],end_year[i],end_month[i],end_day[i]))        
                                    {try {
                                        jsonArray.optJSONObject(i).put("start_year",start_year[i]);
                                        jsonArray.optJSONObject(i).put("start_month",start_month[i]);
                                        jsonArray.optJSONObject(i).put("start_day",start_day[i]);
                                        jsonArray.optJSONObject(i).put("end_year",end_year[i]);
                                        jsonArray.optJSONObject(i).put("end_month",end_month[i]);
                                        jsonArray.optJSONObject(i).put("end_day",end_day[i]);
                                                  if(i<7) {
                                                      weekdayArray.put(i, weekday[i]);
                                                      weekdayArray.put(i+7, weekday[i]);
                                                  }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else {toast_flag=true;
                                     errorList.add(i+8);
                                    }


                                }
                                if(toast_flag) {
                                    Toast toast = Toast.makeText(tod_setting.this,
                                            "時段型態日期錯誤 請確認下列時段型態"+errorList.toString(), Toast.LENGTH_LONG);
                                    toast.show();

                                }
                                
                                
                                
                                
                                Log.d("!!!", "onMenuItemClick: " + jsonArray.toString());
                                Log.d("!!!!", "onMenuItemClick: "+weekdayArray.toString());

                                return true;
                            case R.id.cancel:
                                Snackbar.make(view, "取消", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                try {
                                    Log.d("!!!", "onMenuItemClick: " + jsonObject.getJSONArray("specialdaycontext").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            case R.id.reset:
                                Snackbar.make(view, "重新整理", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                return true;
                            default:
                                return false;
                        }
                    }

                });

                button_popmenu.show();
            }


        });
///////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void specialday_init_value(JSONArray jsonArray, final JSONObject[] specialdaycontext) {
        try {
            for (int i = 0; i < 13; i++) {
                specialdaycontext[i] = jsonArray.getJSONObject(i);
                start_year[i] = Integer.valueOf(specialdaycontext[i].getString("start_year"));
                start_month[i] = Integer.valueOf(specialdaycontext[i].getString("start_month"));
                start_day[i] = Integer.valueOf(specialdaycontext[i].getString("start_day"));
                end_year[i] = Integer.valueOf(specialdaycontext[i].getString("end_year"));
                end_month[i] = Integer.valueOf(specialdaycontext[i].getString("end_month"));
                end_day[i] = Integer.valueOf(specialdaycontext[i].getString("end_day"));
                specialday_start_date[i].setText(setDateFormat(start_year[i], start_month[i], start_day[i],i,true));
                specialday_end_date[i].setText(setDateFormat(end_year[i], end_month[i], end_day[i],i,false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init_spiner_value(JSONArray weekdayArray, Spinner[] spin_common_day) {
        try {


            for (int i = 0; i < 7; i++)
            {weekday[i]=(Integer) weekdayArray.get(i);
                spin_common_day[i].setSelection(weekday[i]);}
//            spin_common_day[1].setSelection((Integer) jsonArray.get(1));
//            spin_common_day[2].setSelection((Integer) jsonArray.get(2));
//            spin_common_day[3].setSelection((Integer) jsonArray.get(3));
//            spin_common_day[4].setSelection((Integer) jsonArray.get(4));
//            spin_common_day[5].setSelection((Integer) jsonArray.get(5));
//            spin_common_day[6].setSelection((Integer) jsonArray.get(6));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void special_day_init() {
        special_day_view_init(specialday_end_date, specialday_start_date);
        Special_day_setting_init(specialday_end_date, specialday_start_date);
    }

    private void Special_day_setting_init(EditText[] specialday_end_date, EditText[] specialday_start_date) {
        for (int i = 0; i < 13; i++) {
            Specialday_date_setting_function(specialday_end_date[i], i,false);

            Specialday_date_setting_function(specialday_start_date[i], i, true);
        }
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

    private void Specialday_date_setting_function(final EditText specialday_date_edit, final int special_segmenttype, final boolean start_or_end) {
        specialday_date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear, mMonth, mDay;
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(tod_setting.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        //String format = getString(R.string.set_date) + setDateFormat(year,month,day);
                        String format = setDateFormat(year, month+1, day,special_segmenttype,start_or_end);
//                        Log.d("!!!", "onDateSet: "+year);
                        specialday_date_edit.setText(format);
                    }
                }, mYear, mMonth, mDay).show();

            }
        });
    }

    private void tod_spin_adapter_init(Spinner[] spin_common_day, ArrayAdapter<CharSequence> arrayAdapter_tod_spinner) {
        for(int i=0;i<7;i++) {
            spin_common_day[i].setAdapter(arrayAdapter_tod_spinner);
            tod_spinner_setting(spin_common_day[i],weekday,i);
        }
    }




private void tod_spinner_setting(final Spinner spinner, final int[]weekday, final int weekdayNum) {
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      weekday[weekdayNum]=  Integer.valueOf(spinner.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}
    private void Spinner_init(Spinner[] spin_common_day) {
        spin_common_day[0] = findViewById(R.id.Spin_common_day_1);
        spin_common_day[1] = findViewById(R.id.Spin_common_day_2);
        spin_common_day[2] = findViewById(R.id.Spin_common_day_3);
        spin_common_day[3] = findViewById(R.id.Spin_common_day_4);
        spin_common_day[4] = findViewById(R.id.Spin_common_day_5);
        spin_common_day[5] = findViewById(R.id.Spin_common_day_6);
        spin_common_day[6] = findViewById(R.id.Spin_common_day_7);
    }


    private String setDateFormat(int year, int monthOfYear, int dayOfMonth,int special_segmenttype,boolean start_or_end) {

        if(start_or_end) {
            start_year[special_segmenttype] = year;
            start_month[special_segmenttype] = monthOfYear ;
            start_day[special_segmenttype] = dayOfMonth;
        }else {
            end_year[special_segmenttype] = year;
            end_month[special_segmenttype] = monthOfYear;
            end_day[special_segmenttype] = dayOfMonth;
        }

//        Log.d("!!!", "setDateFormat: "+year+" "+start_year[special_segmenttype]);
        return String.valueOf(year) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth);
    }
private boolean date_rational_checked(int start_year,int start_month,int start_day,int end_year,int end_month,int end_day)
{
    boolean flag=true;
    if(end_year<start_year)flag=false;
    else{
        if((end_year==start_year)&&(end_month<start_month))flag=false;
        if((end_year==start_year)&&(end_month==start_month)&&(end_day<start_day))flag=false;
       
    }
return flag;    
}

}
