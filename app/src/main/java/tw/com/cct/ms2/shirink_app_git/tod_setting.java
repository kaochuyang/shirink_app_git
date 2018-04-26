package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class tod_setting extends Base_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("1", "onCreate: ");

        setContentView(R.layout.tod_setting);

        Button goto_plan_button= (Button) findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml=new Intent(tod_setting.this,plan_setting.class);
        Button_goto_where(goto_plan_button,plan_setting_xml);


        Spinner spin_common_day[]=new Spinner[7];
        Spinner_init(spin_common_day);
        ArrayAdapter<CharSequence>arrayAdapter_tod_spinner=ArrayAdapter.createFromResource(this,R.array.tod_segment,R.layout.myspinner_style);
      //  arrayAdapter_tod_spinner
        tod_spin_adapter_init(spin_common_day, arrayAdapter_tod_spinner);



        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Log.d("test2", "onCreate: ");
        floating_button_function(setting_button_group,tod_setting.this );

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        Log.d("2", "onCreate: ");
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


    private Spinner.OnItemSelectedListener spin_common_day=
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
        spin_common_day[1]=findViewById(R.id.Spin_common_day_1);
        spin_common_day[2]=findViewById(R.id.Spin_common_day_2);
        spin_common_day[3]=findViewById(R.id.Spin_common_day_3);
        spin_common_day[4]=findViewById(R.id.Spin_common_day_4);
        spin_common_day[5]=findViewById(R.id.Spin_common_day_5);
        spin_common_day[6]=findViewById(R.id.Spin_common_day_6);
        spin_common_day[0]=findViewById(R.id.Spin_common_day_7);
    }
}
