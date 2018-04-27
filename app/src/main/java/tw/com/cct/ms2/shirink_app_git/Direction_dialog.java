package tw.com.cct.ms2.shirink_app_git;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
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

public class Direction_dialog extends DialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_direction_setting, container, false);
     
//    
//    public Direction_dialog(Context context){
//        super(context,android.R.style.Theme_Light);
//        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        setContentView(R.layout.content_direction_setting);
        EditText edit_dir[]=new EditText[8];
        EditText edit_output_board[]=new EditText[8];


        Edit_view_init(v, edit_dir, edit_output_board);

        Button OK=v.findViewById(R.id.ok);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               dismiss();
            }
        });


        edit_dir[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//要可控再丟spinner的adapter進來
            }
        });

        return v;
    }

    private void Edit_view_init(View v, EditText[] edit_dir, EditText[] edit_output_board) {
        edit_dir[0]= v.findViewById(R.id.edit_dir_n);
        edit_dir[1]=v.findViewById(R.id.edit_dir_en);
        edit_dir[2]=v.findViewById(R.id.edit_dir_e);
        edit_dir[3]=v.findViewById(R.id.edit_dir_es);
        edit_dir[4]=v.findViewById(R.id.edit_dir_s);
        edit_dir[5]=v.findViewById(R.id.edit_dir_ws);
        edit_dir[6]=v.findViewById(R.id.edit_dir_w);
        edit_dir[7]=v.findViewById(R.id.edit_dir_wn);

        edit_output_board[0]=v.findViewById(R.id.edit_output_board_0);
        edit_output_board[1]=v.findViewById(R.id.edit_output_board_1);
        edit_output_board[2]=v.findViewById(R.id.edit_output_board_2);
        edit_output_board[3]=v.findViewById(R.id.edit_output_board_3);
        edit_output_board[4]=v.findViewById(R.id.edit_output_board_4);
        edit_output_board[5]=v.findViewById(R.id.edit_output_board_5);
        edit_output_board[6]=v.findViewById(R.id.edit_output_board_6);
        edit_output_board[7]=v.findViewById(R.id.edit_output_board_7);
    }


}
