package tw.com.cct.ms2.shirink_app_git;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;

/**
 * Created by user on 2018/4/20.
 */

public class MyDialog extends Dialog {

    public MyDialog(Context context){
        super(context,android.R.style.Theme_Light);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);

    }

}
