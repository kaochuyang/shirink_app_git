package tw.com.cct.ms2.shirink_app_git;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by user on 2018/4/20.
 */

public class ped_button_setting extends android.support.v4.app.DialogFragment {

   // public boolean getPed_switch_state(boolean ped_switch_state){return ped_switch_state;}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setTitle("alert dialog")
                .setPositiveButton("確認",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }

}
