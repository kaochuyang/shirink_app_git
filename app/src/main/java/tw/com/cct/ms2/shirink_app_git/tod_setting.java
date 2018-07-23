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

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class tod_setting extends Base_activity {
    final EditText specialday_end_date[] = new EditText[13];
    final EditText specialday_start_date[] = new EditText[13];
    Spinner spin_common_day[] = new Spinner[7];
    V3_tc_data A = V3_tc_data.getV3_tc_data();
    Button goto_plan_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tod_setting);
        goto_plan_button = findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml = new Intent(tod_setting.this, plan_setting.class);
        Button_goto_where(goto_plan_button, plan_setting_xml);

        Spinner_init(spin_common_day);

        tcpClass.connect();

        ArrayAdapter<CharSequence> arrayAdapter_tod_spinner = ArrayAdapter.createFromResource(this, R.array.tod_segment, R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner
        tod_spin_adapter_init(spin_common_day, arrayAdapter_tod_spinner);
        special_day_init();
        init_spiner_value( spin_common_day);
        specialday_init_value();
///////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatingButtonGroup();
///////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void FloatingButtonGroup() {
        FloatingActionButton setting_button_group = findViewById(R.id.setting_button_group);
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
                                for(int segment=8;segment<21;segment++) {
                                    if(date_rational_checked(A.getSpecialYear(segment,true),A.getSpecialMonth(segment,true)
                                            ,A.getSpecialDay(segment,true),A.getSpecialYear(segment,false),
                                            A.getSpecialMonth(segment,false),A.getSpecialDay(segment,false)))
                                    {
                                }
                                else {toast_flag=true;
                                     errorList.add(segment);
                                    }
                                }
                                if(toast_flag) {
                                    Toast toast = Toast.makeText(tod_setting.this,
                                            "時段型態日期錯誤 請確認下列時段型態"+errorList.toString(), Toast.LENGTH_LONG);
                                    toast.show();
                                    Log.d("!!!", "onMenuItemClick: " + A.sendSegmentInfoToTc());
                                    Log.d("!!!!", "onMenuItemClick: "+A.sendTODinfoToTc());
                                }
                                else {tcpClass.send(A.sendSegmentInfoToTc());
                                    tcpClass.send(A.sendTODinfoToTc());}

                                return true;
                            case R.id.cancel:
                                Snackbar.make(view, "取消", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                return true;
                            case R.id.reset:
                                A.refreshdata();
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
    }

    private void specialday_init_value() {
        for (int Segmenttype = 8; Segmenttype < 21; Segmenttype++) {
//                specialdaycontext[i] = jsonArray.getJSONObject(i);
//                start_year[i] = Integer.valueOf(specialdaycontext[i].getString("start_year"));
//                start_month[i] = Integer.valueOf(specialdaycontext[i].getString("start_month"));
//                start_day[i] = Integer.valueOf(specialdaycontext[i].getString("start_day"));
//                end_year[i] = Integer.valueOf(specialdaycontext[i].getString("end_year"));
//                end_month[i] = Integer.valueOf(specialdaycontext[i].getString("end_month"));
//                end_day[i] = Integer.valueOf(specialdaycontext[i].getString("end_day"));
            specialday_start_date[Segmenttype-8].
                    setText(
                            setDateFormat(
                                    A.getSpecialYear(Segmenttype,true),
                                    A.getSpecialMonth(Segmenttype,true),
                                    A.getSpecialDay(Segmenttype,true),
                                    Segmenttype,
                                    true));
            specialday_end_date[Segmenttype-8].setText(   setDateFormat(
                    A.getSpecialYear(Segmenttype,false),
                    A.getSpecialMonth(Segmenttype,false),
                    A.getSpecialDay(Segmenttype,false),
                    Segmenttype,
                    false));
        }
    }

    private void init_spiner_value(Spinner[] spin_common_day) {


        for (int i = 0; i < 7; i++)
        {
            spin_common_day[i].setSelection(A.getWeekday(i));}

    }

    private void special_day_init() {
        special_day_view_init(specialday_end_date, specialday_start_date);
        Special_day_setting_init(specialday_end_date, specialday_start_date);
    }

    private void Special_day_setting_init(EditText[] specialday_end_date, EditText[] specialday_start_date) {
        for (int segmenttype = 8;segmenttype < 21;segmenttype++) {
            Specialday_date_setting_function(specialday_end_date[segmenttype-8],segmenttype,false);

            Specialday_date_setting_function(specialday_start_date[segmenttype-8],segmenttype, true);
        }
    }

    private void special_day_view_init(EditText[] specialday_end_date, EditText[] specialday_start_date) {

        for (int i = 8; i <21 ; i++) {
            String idname = "specialday_end_date_" + String.format("%d", i);
            int resID = getResources().getIdentifier(idname, "id", getPackageName());
            specialday_end_date[i - 8] = findViewById(resID);
            String idname1 = "specialday_start_date_" + String.format("%d", i);
            int resID1 = getResources().getIdentifier(idname1, "id", getPackageName());
            specialday_start_date[i - 8] = findViewById(resID1);
        }

//        specialday_end_date[0] = findViewById(R.id.specialday_end_date_8);
//        specialday_end_date[1] = findViewById(R.id.specialday_end_date_9);
//        specialday_end_date[2] = findViewById(R.id.specialday_end_date_10);
//        specialday_end_date[3] = findViewById(R.id.specialday_end_date_11);
//        specialday_end_date[4] = findViewById(R.id.specialday_end_date_12);
//        specialday_end_date[5] = findViewById(R.id.specialday_end_date_13);
//        specialday_end_date[6] = findViewById(R.id.specialday_end_date_14);
//        specialday_end_date[7] = findViewById(R.id.specialday_end_date_15);
//        specialday_end_date[8] = findViewById(R.id.specialday_end_date_16);
//        specialday_end_date[9] = findViewById(R.id.specialday_end_date_17);
//        specialday_end_date[10] = findViewById(R.id.specialday_end_date_18);
//        specialday_end_date[11] = findViewById(R.id.specialday_end_date_19);
//        specialday_end_date[12] = findViewById(R.id.specialday_end_date_20);
//
//       specialday_start_date[0] = findViewById(R.id.specialday_start_date_8);
//        specialday_start_date[1] = findViewById(R.id.specialday_start_date_9);
//        specialday_start_date[2] = findViewById(R.id.specialday_start_date_10);
//        specialday_start_date[3] = findViewById(R.id.specialday_start_date_11);
//        specialday_start_date[4] = findViewById(R.id.specialday_start_date_12);
//        specialday_start_date[5] = findViewById(R.id.specialday_start_date_13);
//        specialday_start_date[6] = findViewById(R.id.specialday_start_date_14);
//        specialday_start_date[7] = findViewById(R.id.specialday_start_date_15);
//        specialday_start_date[8] = findViewById(R.id.specialday_start_date_16);
//        specialday_start_date[9] = findViewById(R.id.specialday_start_date_17);
//        specialday_start_date[10] = findViewById(R.id.specialday_start_date_18);
//        specialday_start_date[11] = findViewById(R.id.specialday_start_date_19);
//        specialday_start_date[12] = findViewById(R.id.specialday_start_date_20);
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
            tod_spinner_setting(spin_common_day[i],i);
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



private void tod_spinner_setting(final Spinner spinner, final int weekdayNum) {
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position==0){position=1;}
            //weekday[weekdayNum]=  position;
            V3_tc_data.setWeekday(weekdayNum,position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}
    private void Spinner_init(Spinner[] spin_common_day) {

        for (int i = 1; i <8 ; i++) {
            String idname = "Spin_common_day_" + String.format("%d", i);
            int resID = getResources().getIdentifier(idname, "id", getPackageName());
            spin_common_day[i-1] = findViewById(resID);}
//        spin_common_day[0] = findViewById(R.id.Spin_common_day_1);
//        spin_common_day[1] = findViewById(R.id.Spin_common_day_2);
//        spin_common_day[2] = findViewById(R.id.Spin_common_day_3);
//        spin_common_day[3] = findViewById(R.id.Spin_common_day_4);
//        spin_common_day[4] = findViewById(R.id.Spin_common_day_5);
//        spin_common_day[5] = findViewById(R.id.Spin_common_day_6);
//        spin_common_day[6] = findViewById(R.id.Spin_common_day_7);
    }


    private String setDateFormat(int year, int monthOfYear, int dayOfMonth,int special_segmenttype,boolean start_or_end) {

      //  if(start_or_end) {
            A.setSpecialYear(special_segmenttype,year,start_or_end);
            A.setSpecialMonth(special_segmenttype,monthOfYear,start_or_end);
            A.setSpecialDay(special_segmenttype,dayOfMonth,start_or_end);

        Log.d("11233213", "setDateFormat: "+year+"/"+monthOfYear+"/"+dayOfMonth+" segment="+special_segmenttype+" SOE="+start_or_end);

//            start_month[special_segmenttype] = monthOfYear ;
//            start_day[special_segmenttype] = dayOfMonth;
//        }else {
//            end_year[special_segmenttype] = year;
//            end_month[special_segmenttype] = monthOfYear;
//            end_day[special_segmenttype] = dayOfMonth;
//        }

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
