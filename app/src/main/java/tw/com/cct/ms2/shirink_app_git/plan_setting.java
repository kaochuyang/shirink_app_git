package tw.com.cct.ms2.shirink_app_git;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class plan_setting extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.plan_setting);
        FloatingActionButton plan_setting_button=(FloatingActionButton)findViewById(R.id.setting_button_group);
        floating_button_function(plan_setting_button,plan_setting.this);



    }




}
