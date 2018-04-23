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
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Base_activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    ;
    private final CharSequence[] items = {"允許現場設定", "不允許現場設定", "只允許現場查看"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goto_tod_setting = (Button) findViewById(R.id.go_to_tod_setting);
        Intent intent_tod_setting_xml = new Intent(this, tod_setting.class);
        Button_goto_where(goto_tod_setting, intent_tod_setting_xml);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Button password_setting = findViewById(R.id.password_setting);
        final Button Uart_select = findViewById(R.id.Uart_select);
        Button datatbase_setting = findViewById(R.id.database_setting);
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);
        Button report_cycle_setting = findViewById(R.id.report_cycle_setting);
        Button ped_button_setting = findViewById(R.id.ped_button_setting);
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
        ped_button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ped_button_edit();
            }
        });
        datatbase_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database_setting();
            }
        });
        report_cycle_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReport_cycle();
            }
        });

        Uart_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com2_select();
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

    private void ped_button_edit() {
        final View ped_button_setting = LayoutInflater.from(MainActivity.this).inflate(R.layout.ped_button_setting, null);
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.ped_button_edit)
                .setView(ped_button_setting)

                .setPositiveButton("確定", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Switch ped_switch = (Switch) findViewById(R.id.ped_button_switch);
                                EditText plan_num_edit = (EditText) findViewById(R.id.plan_num_edit);
                                String plan_ped =plan_num_edit.getText().toString();
                                Log.d(plan_ped, "Editex: ");
                            //    Toast.makeText(getApplicationContext(),  plan_ped, Toast.LENGTH_SHORT).show();

                            }

                        }
                )
                .show();

    }

    int database_manual_manual_ChoiceIndex = 1;//把database_manual_ChiceIndex改成讀出來的內容

    private void database_setting()//資料庫操作設定
    {
        final List<String> database_manual = new ArrayList<>();
        database_manual.add(getString(R.string.allow_construction_setting));
        database_manual.add(getString(R.string.refuse_construction_setting));
        database_manual.add(getString(R.string.allow_construction_view));

        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(database_manual.toArray(new String[database_manual.size()]), database_manual_manual_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database_manual_manual_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + database_manual.get(database_manual_manual_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    int com2_select_ChoiceIndex = 0;

    private void com2_select() {
        final List<String> com2_select = new ArrayList<>();
        com2_select.add(getString(R.string.GPS_link));
        com2_select.add(getString(R.string.Red_countdown));
        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(com2_select.toArray(new String[com2_select.size()]), com2_select_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                com2_select_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + com2_select.get(com2_select_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    int report_cycle_ChoiceIndex = 5;

    private void setReport_cycle() {
        final List<String> Report_cycle = new ArrayList<>();
        Report_cycle.add(getString(R.string.stop_report_hardcore_state));
        Report_cycle.add(getString(R.string.second_1));
        Report_cycle.add(getString(R.string.second_2));
        Report_cycle.add(getString(R.string.second_5));
        Report_cycle.add(getString(R.string.minute_1));
        Report_cycle.add(getString(R.string.minute_5));
        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(Report_cycle.toArray(new String[Report_cycle.size()]), report_cycle_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                report_cycle_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + Report_cycle.get(report_cycle_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }


}
