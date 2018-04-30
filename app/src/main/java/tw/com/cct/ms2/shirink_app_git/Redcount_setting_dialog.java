package tw.com.cct.ms2.shirink_app_git;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 2018/4/20.
 */

public class Redcount_setting_dialog extends DialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_red_countdown__setting, container, false);
     
//    
//    public Direction_dialog(Context context){
//        super(context,android.R.style.Theme_Light);
//        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        setContentView(R.layout.content_direction_setting);
        EditText edit_red[]=new EditText[8];
        TextView red_count_state[]=new TextView[8];


        Edit_view_init(v, edit_red, red_count_state);

        Button OK=v.findViewById(R.id.cancel);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               dismiss();
            }
        });


        

        return v;
    }

    private void Edit_view_init(View v, EditText[] edit_red, TextView[] red_count_state) {
        edit_red[0]= v.findViewById(R.id.edit_red_0);
        edit_red[1]=v.findViewById(R.id.edit_red_1);
        edit_red[2]=v.findViewById(R.id.edit_red_2);
        edit_red[3]=v.findViewById(R.id.edit_red_3);
        edit_red[4]=v.findViewById(R.id.edit_red_4);
        edit_red[5]=v.findViewById(R.id.edit_red_5);
        edit_red[6]=v.findViewById(R.id.edit_red_6);
        edit_red[7]=v.findViewById(R.id.edit_red_7);

        red_count_state[0]=v.findViewById(R.id.red_count_state_0);
        red_count_state[1]=v.findViewById(R.id.red_count_state_1);
        red_count_state[2]=v.findViewById(R.id.red_count_state_2);
        red_count_state[3]=v.findViewById(R.id.red_count_state_3);
        red_count_state[4]=v.findViewById(R.id.red_count_state_4);
        red_count_state[5]=v.findViewById(R.id.red_count_state_5);
        red_count_state[6]=v.findViewById(R.id.red_count_state_6);
        red_count_state[7]=v.findViewById(R.id.red_count_state_7);
    }


}
