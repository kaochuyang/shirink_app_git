package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tod_setting extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("1", "onCreate: ");

        setContentView(R.layout.tod_setting);

        Button goto_plan_button= (Button) findViewById(R.id.goto_plan_button);
        Intent plan_setting_xml=new Intent(tod_setting.this,Main2Activity.class);
        Button_goto_where(goto_plan_button,plan_setting_xml);

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
