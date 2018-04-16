package tw.com.cct.ms2.shirink_app_git;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goto_tod_setting= (Button) findViewById(R.id.go_to_tod_setting);
        Intent intent_tod_setting_xml = new Intent(this, tod_setting.class);
      Button_goto_where(goto_tod_setting,intent_tod_setting_xml);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }


    protected void floating_button_function(FloatingActionButton floatingActionButton,final AppCompatActivity A) {//定義floating button 組的功能，要讓每個頁面都有
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//應該要全頁面拿來共用此按鈕
                PopupMenu button_popmenu=new PopupMenu(A,view);
                button_popmenu.getMenuInflater().inflate(R.menu.button_popmenu,button_popmenu.getMenu());


                button_popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Do something...
                        // YourActivity.this.someFunctionInYourActivity();
//                         switch (item.getItemId()) {
//                        case R.id.item1:
//            ... code ...
//                        return true;
//                        case R.id.item2:
//            ... code ...
//                        return true;
//                        default:
//                        return super.onOptionsItemSelected(item);
//                    }
                        return true;
                    }

                });

                button_popmenu.show();
            }


        });
    }

    public void Button_goto_where(Button A, final Intent B) {
        A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(B);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
}
