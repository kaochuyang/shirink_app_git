package tw.com.cct.ms2.shirink_app_git;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 2018/4/16.
 */

public class Button_function extends AppCompatActivity{

    public void Button_goto_where(Button A, final Intent B) {
        A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(B);
            }
        });
    }

}
