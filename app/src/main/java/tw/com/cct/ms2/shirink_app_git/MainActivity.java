package tw.com.cct.ms2.shirink_app_git;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Base_activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    ;
    private final CharSequence[] item = {"", "item2", "item3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goto_tod_setting = (Button) findViewById(R.id.go_to_tod_setting);
        Intent intent_tod_setting_xml = new Intent(this, tod_setting.class);
        Button_goto_where(goto_tod_setting, intent_tod_setting_xml);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final Button password_setting = findViewById(R.id.password_setting);

        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Log.d("test2", "onCreate: ");
        floating_button_function(setting_button_group, MainActivity.this);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//         final AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setMessage("hello").show();
        password_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyDialog(MainActivity.this).show();
                password_edit();
            }

        });


        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
    private void password_edit() {
        final View password_item = LayoutInflater.from(MainActivity.this).inflate(R.layout.password_setting, null);
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.password_old)
                .setView(password_item)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) password_item.findViewById(R.id.password_old);
                        EditText editText1 = password_item.findViewById(R.id.password_new);
                        EditText editText2 = password_item.findViewById(R.id.password_new_check);
                        String password_origin = "0";
                        String name = editText.getText().toString();

                        int flag = editText1.getText().toString().compareTo(editText2.getText().toString());

                        if (flag == 0) {
                            Toast.makeText(getApplicationContext(), "密碼更改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("1", "onClick: flag=" + flag);
                            Toast.makeText(getApplicationContext(), "密碼更改失敗", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();

    }

}
