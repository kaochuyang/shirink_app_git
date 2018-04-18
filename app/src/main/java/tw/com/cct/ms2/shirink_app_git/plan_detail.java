package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class plan_detail extends Base_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_detail_layout);
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Log.d("plan_setting", "onCreate: ");
        floating_button_function(setting_button_group,plan_detail.this );

        Button link_to_step_setting=findViewById(R.id.link_to_step_setting);
        Intent step_setting=new Intent(plan_detail.this, tw.com.cct.ms2.shirink_app_git.step_setting.class);
        Button_goto_where(link_to_step_setting,step_setting);




    }
}
