package tw.com.cct.ms2.shirink_app_git;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by user on 2018/4/17.
 */

public class step_setting_fragment extends android.support.v4.app.Fragment {

    private int page;
    public static View rootView;
    Button[] Button_red_ = new Button[6];
    Button[] Button_yellow_ = new Button[6];
    Button[] Button_left_ = new Button[6];
    Button[] Button_green_ = new Button[6];
    Button[] Button_straight_ = new Button[6];
    Button[] Button_right_ = new Button[6];
    Button[] Button_ped_red_ = new Button[6];
    Button[] Button_ped_flash_ = new Button[6];
    static int[] red_state_=new int[6];
    static int[] green_state_=new int[6];
    static int[] yellow_state_=new int[6];
    static int[] left_state_=new int[6];
    static int[] right_state_=new int[6];
    static int[] straight_state_=new int[6];
    static int[] ped_red_state_=new int[6];
    static int[] ped_green_state_=new int[6];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        System.out.println("hello page=" + page);
        rootView = inflater.inflate(R.layout.step_setting_fragment1, container, false);
        Button_findview();

        Button_color_init();

        return getView(inflater, container);
    }

    private void Button_color_init() {
        for(int i=0;i<6;i++) {
            red_button(Button_red_, red_state_, i);
            green_button(Button_green_, green_state_, i);
            green_button(Button_left_, left_state_, i);
            green_button(Button_straight_, straight_state_, i);
            green_button(Button_ped_flash_, ped_green_state_, i);
            red_button(Button_ped_red_, ped_red_state_, i);
            green_button(Button_right_, right_state_, i);
            yellow_button(Button_yellow_, yellow_state_, i);
        }
    }

    private void red_button(final Button []Button_red_,final int[]red_state_,final int i ) {
        Button_red_[i].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                red_state_[i]=button_state(red_state_[i]);

                if(red_state_[i]==0)Button_red_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(red_state_[i]==2)Button_red_[i].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_OUT);
                if(red_state_[i]==1)Button_red_[i].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    private void green_button(final Button []Button_green_,final int[]green_state_,final int i ) {
        Button_green_[i].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                green_state_[i]=button_state(green_state_[i]);

                if(green_state_[i]==0)Button_green_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(green_state_[i]==2)Button_green_[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OUT);
                if(green_state_[i]==1)Button_green_[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    private void yellow_button(final Button []Button_yellow_,final int[]yellow_state_,final int i ) {
        Button_yellow_[i].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                yellow_state_[i]=button_state(yellow_state_[i]);

                if(yellow_state_[i]==0)Button_yellow_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(yellow_state_[i]==2)Button_yellow_[i].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_OUT);
                if(yellow_state_[i]==1)Button_yellow_[i].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    


    private int button_state(int state) {
        state++;
        if(state==3)state=0;
        return state;
    }

    private void Button_findview() {
        for (int i = 1; i < 7; i++) {
            String idname_red = "Button_red_" + String.format("%d", i);
            int resID_red = getResources().getIdentifier(idname_red, "id", getActivity().getPackageName());
            Button_red_[i - 1] = rootView.findViewById(resID_red);
            String idname_yellow = "Button_yellow_" + String.format("%d", i);
            int resID_yellow = getResources().getIdentifier(idname_yellow, "id", getActivity().getPackageName());
            Button_yellow_[i - 1] = rootView.findViewById(resID_yellow);
            String idname_left = "Button_left_" + String.format("%d", i);
            int resID_left = getResources().getIdentifier(idname_left, "id", getActivity().getPackageName());
            Button_left_[i - 1] = rootView.findViewById(resID_left);
            String idname_green = "Button_green_" + String.format("%d", i);
            int resID_green = getResources().getIdentifier(idname_green, "id", getActivity().getPackageName());
            Button_green_[i - 1] = rootView.findViewById(resID_green);
            
            String idname_straight = "Button_straight_" + String.format("%d", i);
            int resID_straight = getResources().getIdentifier(idname_straight, "id", getActivity().getPackageName());
            Button_straight_[i-1] = rootView.findViewById(resID_straight);
            
            String idname_right = "Button_right_" + String.format("%d", i);
            int resID_right = getResources().getIdentifier(idname_right, "id", getActivity().getPackageName());
            Button_right_[i - 1] = rootView.findViewById(resID_right);
            String idname_ped_red = "Button_ped_red_" + String.format("%d", i);
            int resID_ped_red = getResources().getIdentifier(idname_ped_red, "id", getActivity().getPackageName());
            Button_ped_red_[i - 1] = rootView.findViewById(resID_ped_red);
            String idname_ped_flash = "Button_ped_flash_" + String.format("%d", i);
            int resID_ped_flash = getResources().getIdentifier(idname_ped_flash, "id", getActivity().getPackageName());
            Button_ped_flash_[i - 1] = rootView.findViewById(resID_ped_flash);


        }
    }

    @Nullable
    private View getView(LayoutInflater inflater, ViewGroup container) {

        switch (page) {
            case 1:

                System.out.println("hello  2");

                return rootView;
//            case 2:
//                rootView = inflater.inflate(R.layout.step_setting_fragment2, container, false);
//                return rootView;
//            case 3:
//                rootView = inflater.inflate(R.layout.step_setting_fragment3, container, false);
//                return rootView;
//            case 4:
//                rootView = inflater.inflate(R.layout.step_setting_fragment4, container, false);
//                return rootView;
//            case 5:
//                rootView = inflater.inflate(R.layout.step_setting_fragment5, container, false);
//                return rootView;
//            case 6:
//                rootView = inflater.inflate(R.layout.step_setting_fragment6, container, false);
//                return rootView;
//            case 7:
//                rootView = inflater.inflate(R.layout.step_setting_fragment7, container, false);
//                return rootView;
//            case 8:
//                rootView = inflater.inflate(R.layout.step_setting_fragment8, container, false);
//                System.out.println("hello  3");
//                return rootView;
            default:
                return rootView;

        }
    }

    public int getPagenum(int page_num) {
        page = page_num;
        System.out.println("hello  4");
        return page;

    }


}

