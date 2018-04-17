package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.Button;

public class tod_setting extends Base_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("1", "onCreate: ");

        setContentView(R.layout.tod_setting);

        Button goto_plan_button= (Button) findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml=new Intent(tod_setting.this,plan_setting.class);
        Button_goto_where(goto_plan_button,plan_setting_xml);

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
}
