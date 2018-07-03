package tw.com.cct.ms2.shirink_app_git;

import android.app.DialogFragment;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Base_activity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }


    private final CharSequence[] items = {"允許現場設定", "不允許現場設定", "只允許現場查看"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Uart_select = findViewById(R.id.Uart_select);
        Button goto_tod_setting = (Button) findViewById(R.id.go_to_tod_setting);
   //     Intent intent_tod_setting_xml = new Intent(this, step_setting.class);
        Intent intent_tod_setting_xml = new Intent(this, plan_detail.class);



        Button_goto_where(goto_tod_setting, intent_tod_setting_xml);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Button password_setting = findViewById(R.id.password_setting);

        Button datatbase_setting = findViewById(R.id.database_setting);
        FloatingActionButton setting_button_group = (FloatingActionButton) findViewById(R.id.setting_button_group);

        final Button ped_button_setting = findViewById(R.id.ped_button_setting);

        Button light_report_cycle_setting = findViewById(R.id.light_report_cycle_setting);
        Button step_report_cycle_setting = findViewById(R.id.step_report_cycle_setting);
        Button hardware_state_report_cycle_set = findViewById(R.id.hardware_state_report_cycle_set);
        floating_button_function(setting_button_group, MainActivity.this);
        Button light_direction_setting = findViewById(R.id.light_direction_setting);

        Button GPS_check_time = findViewById(R.id.GPS_check_time);
        Button firmware_update = findViewById(R.id.firmware_update);
        Button Reboot = findViewById(R.id.Reboot);
        Button ask_for_center_transfer_plan = findViewById(R.id.ask_for_center_transfer_plan);
        Button check_v3_packet = findViewById(R.id.check_v3_packet);
        Intent V3_connection_activity = new Intent(this, V3_connection_activity.class);
        Button_goto_where(check_v3_packet, V3_connection_activity);

        Button redcount_setting = findViewById(R.id.redcount_setting);
        final Button chain_setting = findViewById(R.id.chain_setting);


        light_direction_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment editNameDialog = new Direction_dialog();
                editNameDialog
                        .show(getFragmentManager(), "Direction_dialog");


            }
        });


        redcount_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment editNameDialog = new Redcount_setting_dialog();
                editNameDialog
                        .show(getFragmentManager(), "redcount_dialog");


            }
        });


        GPS_check_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyDialog(MainActivity.this).show();
                vGPS_check_time();
            }

        });

        firmware_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyDialog(MainActivity.this).show();
                vFirmware_update();
            }

        });

        Reboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyDialog(MainActivity.this).show();
                vReboot();
            }

        });


        ask_for_center_transfer_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyDialog(MainActivity.this).show();
                vAsk_for_center_transfer_plan();
            }

        });

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

                ped_button_setting();
            }
        });
        datatbase_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database_setting();
            }
        });
        hardware_state_report_cycle_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_hardware_Report_cycle();
            }
        });
        light_report_cycle_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_light_Report_cycle();
            }
        });
        step_report_cycle_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_step_Report_cycle();
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
        new AlertDialog.Builder(MainActivity.this).setTitle("密碼設定")
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
                            Log.d("10", "0000000onClick: " + editText1.getText().toString());
                        } else {
                            Log.d("1", "onClick: flag=" + flag);
                            Toast.makeText(getApplicationContext(), "密碼更改失敗", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setTitle("密碼設定")
                .show();

    }

    int iPed_plan_num = 1;
    boolean bPed_switch = false;
    int iPed_plan_view = 0;

    private void ped_button_setting() {

        final View ped_button_setting_layout = LayoutInflater.from(MainActivity.this).inflate(R.layout.ped_button_setting, null);
        final Switch ped_button_switch = ped_button_setting_layout.findViewById(R.id.ped_button_switch);
        TextView ped_plan_num_view = ped_button_setting_layout.findViewById(R.id.ped_plan_num_view);

        final EditText ped_plan_num = ped_button_setting_layout.findViewById(R.id.ped_plan_num_edit);
        ped_button_switch.setChecked(bPed_switch);
        ped_plan_num_view.setText(String.valueOf(iPed_plan_view));
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("行人觸動設定")
                //.setMessage("是否要離開?")
                .setView(ped_button_setting_layout)
                //  .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (ped_button_switch.isChecked())
                            Toast.makeText(getApplicationContext(), "行人觸動保持運行，時制 " + iPed_plan_view, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "行人觸動無運行，時制 " + iPed_plan_view, Toast.LENGTH_SHORT).show();
                    }
                })
//                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (ped_plan_num.getText().toString().trim().length() > 0) {


                            if ((Integer.parseInt(ped_plan_num.getText().toString()) > 48) || (Integer.parseInt(ped_plan_num.getText().toString()) < 0)) {

                                Toast.makeText(getApplicationContext(), "時制設定錯誤(超過範圍)", Toast.LENGTH_SHORT).show();
                            } else {
                                if (ped_button_switch.isChecked()) {


                                    Log.d("2", "onClick:2");

                                    iPed_plan_num = Integer.parseInt(ped_plan_num.getText().toString());
                                    iPed_plan_view = iPed_plan_num;


                                    Toast.makeText(getApplicationContext(), "行人觸動啟動，時制" + iPed_plan_view, Toast.LENGTH_SHORT).show();
                                    bPed_switch = true;


                                } else {

                                    bPed_switch = false;
                                    iPed_plan_num = Integer.parseInt(ped_plan_num.getText().toString());

                                    iPed_plan_view = iPed_plan_num;

                                    Toast.makeText(getApplicationContext(), "行人觸動停止，時制" + iPed_plan_view, Toast.LENGTH_SHORT).show();

                                }

                            }
                        } else {

                            if (ped_button_switch.isChecked()) {
                                bPed_switch = true;
                                Toast.makeText(getApplicationContext(), "行人觸動保持運行，時制 " + iPed_plan_view, Toast.LENGTH_SHORT).show();
                            } else {
                                bPed_switch = false;
                                Toast.makeText(getApplicationContext(), "行人觸動無運行，時制 " + iPed_plan_view, Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                }).create();
        dialog.show();


    }


    private void vFirmware_update() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("韌體更新")
                .setMessage("確定更新?")
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
//                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();


    }


    private void vReboot() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("重新開機")
                .setMessage("確定主機重啟?")
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
//                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();


    }


    private void vGPS_check_time() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("GPS對時")
                .setMessage("確定對時?")
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
//                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();


    }


    private void vAsk_for_center_transfer_plan() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("像中心要求目前新的時制")
                .setMessage("確定要求?")
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
//                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();


    }


    int database_manual_ChoiceIndex = 1;//把database_manual_ChiceIndex改成讀出來的內容

    private void database_setting()//資料庫操作設定
    {
        final List<String> database_manual = new ArrayList<>();
        database_manual.add(getString(R.string.allow_construction_setting));
        database_manual.add(getString(R.string.refuse_construction_setting));
        database_manual.add(getString(R.string.allow_construction_view));

        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(database_manual.toArray(new String[database_manual.size()]), database_manual_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database_manual_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + database_manual.get(database_manual_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setTitle("資料庫操作設定")
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
                }).setTitle("選擇com2連接裝置")
                .show();

    }

    int hardware_report_cycle_ChoiceIndex = 5;

    private void set_hardware_Report_cycle() {
        final List<String> Report_cycle = new ArrayList<>();
        Report_cycle.add(getString(R.string.stop_report_hardcore_state));
        Report_cycle.add(getString(R.string.second_1));
        Report_cycle.add(getString(R.string.second_2));
        Report_cycle.add(getString(R.string.second_5));
        Report_cycle.add(getString(R.string.minute_1));
        Report_cycle.add(getString(R.string.minute_5));
        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(Report_cycle.toArray(new String[Report_cycle.size()]), hardware_report_cycle_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hardware_report_cycle_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + Report_cycle.get(hardware_report_cycle_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setTitle("硬體回報週期設定")
                .show();

    }


    int report_step_cycle_ChoiceIndex = 5;

    private void set_step_Report_cycle() {
        final List<String> step_report_cycle = new ArrayList<>();
        step_report_cycle.add(getString(R.string.stop_report));
        step_report_cycle.add(getString(R.string.second_1));
        step_report_cycle.add(getString(R.string.second_2));
        step_report_cycle.add(getString(R.string.second_5));
        step_report_cycle.add(getString(R.string.minute_1));
        step_report_cycle.add(getString(R.string.minute_5));
        step_report_cycle.add(getString(R.string.transfer_change_state));
        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(step_report_cycle.toArray(new String[step_report_cycle.size()]), hardware_report_cycle_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hardware_report_cycle_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + step_report_cycle.get(hardware_report_cycle_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setTitle("步階回報週期設定")
                .show();

    }


    int report_light_cycle_ChoiceIndex = 5;

    private void set_light_Report_cycle() {
        final List<String> light_report_cycle = new ArrayList<>();
        light_report_cycle.add(getString(R.string.stop_report));
        light_report_cycle.add(getString(R.string.second_1));
        light_report_cycle.add(getString(R.string.second_2));
        light_report_cycle.add(getString(R.string.second_5));
        light_report_cycle.add(getString(R.string.minute_1));
        light_report_cycle.add(getString(R.string.minute_5));
        light_report_cycle.add(getString(R.string.transfer_change_state));
        new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(light_report_cycle.toArray(new String[light_report_cycle.size()]), hardware_report_cycle_ChoiceIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hardware_report_cycle_ChoiceIndex = which;
                                Toast.makeText(MainActivity.this, "你選擇的是" + light_report_cycle.get(hardware_report_cycle_ChoiceIndex), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setTitle("燈態回報週期設定")
                .show();

    }


//////////////////////////////////主程式底部///////////////////////////////
}

