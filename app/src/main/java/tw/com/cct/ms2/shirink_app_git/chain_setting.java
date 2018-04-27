package tw.com.cct.ms2.shirink_app_git;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by user on 2018/4/20.
 */

public class chain_setting extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chain_setting_dialog, container, false);

//    
//    public Direction_dialog(Context context){
//        super(context,android.R.style.Theme_Light);
//        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        setContentView(R.layout.content_direction_setting);
        Button parent_tc_setting = v.findViewById(R.id.parent_tc_setting);
        Button child_tc_settig = v.findViewById(R.id.child_tc_setting);
        Button train_chain_setting = v.findViewById(R.id.train_chain_setting);

        Button cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });


        return v;
    }


}
