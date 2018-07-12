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
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import static org.greenrobot.eventbus.EventBus.TAG;
import static tw.com.cct.ms2.shirink_app_git.V3_tc_data.jsonObject;


/**
 * Created by user on 2018/4/17.
 */

public class step_setting_fragment extends android.support.v4.app.Fragment {

    private int page;
    public View rootView;
    Button[] Button_red_ = new Button[6];
    Button[] Button_yellow_ = new Button[6];
    Button[] Button_left_ = new Button[6];
    Button[] Button_green_ = new Button[6];
    Button[] Button_straight_ = new Button[6];
    Button[] Button_right_ = new Button[6];
    Button[] Button_ped_red_ = new Button[6];
    Button[] Button_ped_flash_ = new Button[6];

    V3_tc_data A = V3_tc_data.getV3_tc_data();

    TextView []board_=new TextView[6];

    static int phaseorder=0;
    static int subphase=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView page=" + page);
        rootView = inflater.inflate(R.layout.step_setting_fragment1, container, false);

        Button_findview();//連結xml
        Button_color_init(phaseorder,subphase,page-1);//給按鈕變色功能

        view_state_init();//初始化按鈕狀態和顏色



        Log.d("!!!!!!!", "onCreateView: PAGE_NUM="+page+"  phaseorder="+phaseorder+" subphase="+subphase);

        return getView(inflater, container);
    }

    private void view_state_init() {
        //light_state_get(jsonObject, step_object,light_board_decide(phaseorder),page-1,subphase,phaseorder);
        Log.d("!!!!!!!", "view_state_init: PAGE_NUM="+page+"  phaseorder="+phaseorder+" subphase="+subphase);
        button_color_start_state(phaseorder,subphase,page-1);//初始化按鈕顏色
        button_visible_control(light_board_decide(phaseorder));//決定按鈕隱藏與否
    }

    private int  light_board_decide(int phaseorder) {

    return A.getTotalLightBoardCount(phaseorder);     
    }
    private void button_visible_control(int light_board_count) {
        for(int i=light_board_count;i<6;i++) {
            Button_red_[i].setVisibility(View.GONE);
            Button_green_[i].setVisibility(View.GONE);
            Button_left_[i].setVisibility(View.GONE);
            Button_right_[i].setVisibility(View.GONE);
            Button_straight_[i].setVisibility(View.GONE);
            Button_yellow_[i].setVisibility(View.GONE);
            Button_ped_flash_[i].setVisibility(View.GONE);
            Button_ped_red_[i].setVisibility(View.GONE);
            board_[i].setVisibility(View.GONE);
        }
        for(int i=0;i<light_board_count;i++) {
            Button_red_[i].setVisibility(View.VISIBLE);
            Button_green_[i].setVisibility(View.VISIBLE);
            Button_left_[i].setVisibility(View.VISIBLE);
            Button_right_[i].setVisibility(View.VISIBLE);
            Button_straight_[i].setVisibility(View.VISIBLE);
            Button_yellow_[i].setVisibility(View.VISIBLE);
            Button_ped_flash_[i].setVisibility(View.VISIBLE);
            Button_ped_red_[i].setVisibility(View.VISIBLE);
            board_[i].setVisibility(View.VISIBLE);
        }
    }

    private void button_color_start_state(int PhaseOrder, int SubphaseCount, int StepNum) {
        for(int LightBoard=0;LightBoard<6;LightBoard++) {
            red_color_state(Button_red_, V3_tc_data.red_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            green_color_state(Button_green_, V3_tc_data.green_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            green_color_state(Button_left_, V3_tc_data.left_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            green_color_state(Button_straight_, V3_tc_data.straight_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            green_color_state(Button_ped_flash_, V3_tc_data.ped_green_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            red_color_state(Button_ped_red_, V3_tc_data.ped_red_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            green_color_state(Button_right_, V3_tc_data.right_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
            yellow_color_state(Button_yellow_, V3_tc_data.yellow_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard);
        }
    }

    @Override
    public  void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void step_set_select(MessageEvent_subphase messageEvent)
    {
        //Log.d(TAG, "step_set_select: ");
        subphase=messageEvent.getsubphase();
        //light_state_get(jsonObject, step_object,light_board_decide(phaseorder),step_num,subphase,phaseorder);
        //button_color_start_state(PhaseOrder,SubphaseCount,StepNum);
        view_state_init();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void phaseorder_set_select(MessageEvent_phaseorder messageEvent)
    {
        phaseorder=messageEvent.getphaseorder();
        view_state_init();
    }



    private void Button_color_init(int PhaseOrder, int SubphaseCount, int StepNum) {
        for(int LightBoardNum=0;LightBoardNum<6;LightBoardNum++) {
            red_button(Button_red_, V3_tc_data.red_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
            green_button(Button_green_, V3_tc_data.green_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
            green_button(Button_left_, V3_tc_data.left_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
            green_button(Button_straight_, V3_tc_data.straight_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
            green_button(Button_ped_flash_, V3_tc_data.ped_green_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
           red_button(Button_ped_red_, V3_tc_data.ped_red_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
            green_button(Button_right_, V3_tc_data.right_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);
            yellow_button(Button_yellow_, V3_tc_data.yellow_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum);


        }
    }
    private void red_button(final Button []Button_red_,final int[]red_state_,final int LightBoardNum ) {
        Button_red_[LightBoardNum].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_state(red_state_,LightBoardNum);
                if(red_state_[LightBoardNum]==0)Button_red_[LightBoardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(red_state_[LightBoardNum]==2)Button_red_[LightBoardNum].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_OUT);
                if(red_state_[LightBoardNum]==1)Button_red_[LightBoardNum].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    private void green_button(final Button []Button_green_,final int[]green_state_,final int LightBoardNum ) {
        Button_green_[LightBoardNum].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_state(green_state_,LightBoardNum);
                if(green_state_[LightBoardNum]==0)Button_green_[LightBoardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(green_state_[LightBoardNum]==2)Button_green_[LightBoardNum].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OUT);
                if(green_state_[LightBoardNum]==1)Button_green_[LightBoardNum].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    private void yellow_button(final Button []Button_yellow_,final int[]yellow_state_,final int LightBoardNum ) {
        Button_yellow_[LightBoardNum].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_state(yellow_state_,LightBoardNum);
                if(yellow_state_[LightBoardNum]==0)Button_yellow_[LightBoardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(yellow_state_[LightBoardNum]==2)Button_yellow_[LightBoardNum].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_OUT);
                if(yellow_state_[LightBoardNum]==1)Button_yellow_[LightBoardNum].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    private void button_state(final int []state,int LightBoardNum) {
        state[LightBoardNum]++;
        if(state[LightBoardNum]==3)state[LightBoardNum]=0;
//        return state[LightBoardNum];
    }

    private void green_color_state(final Button []Button_green_,final int green_state_,final int i ) {
          

                if(green_state_==0)Button_green_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(green_state_==2)Button_green_[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OUT);
                if(green_state_==1)Button_green_[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);

    }
    private void yellow_color_state(final Button []Button_yellow_,final int yellow_state_,final int LightboardNum ) {


                if(yellow_state_==0)Button_yellow_[LightboardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(yellow_state_==2)Button_yellow_[LightboardNum].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_OUT);
                if(yellow_state_==1)Button_yellow_[LightboardNum].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
     
    }
    private void red_color_state(final Button []Button_red_,int red_state_,final int LightboardNum ) {


        if(red_state_==1)Button_red_[LightboardNum].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        if(red_state_==0)Button_red_[LightboardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        if(red_state_==2)Button_red_[LightboardNum].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_OUT);
           
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

         String idname_borad = "board_" + String.format("%d", i);
            int resID_board= getResources().getIdentifier(idname_borad, "id", getActivity().getPackageName());
            board_[i-1]=rootView.findViewById(resID_board);}

    }
    @Nullable
    private View getView(LayoutInflater inflater, ViewGroup container) {

//        switch (page) {
//            case 1:
//
//                System.out.println("hello  2");
//
//                return rootView;
////            case 2:
////                rootView = inflater.inflate(R.layout.step_setting_fragment2, container, false);
////                return rootView;
////            case 3:
////                rootView = inflater.inflate(R.layout.step_setting_fragment3, container, false);
////                return rootView;
////            case 4:
////                rootView = inflater.inflate(R.layout.step_setting_fragment4, container, false);
////                return rootView;
////            case 5:
////                rootView = inflater.inflate(R.layout.step_setting_fragment5, container, false);
////                return rootView;
////            case 6:
////                rootView = inflater.inflate(R.layout.step_setting_fragment6, container, false);
////                return rootView;
////            case 7:
////                rootView = inflater.inflate(R.layout.step_setting_fragment7, container, false);
////                return rootView;
////            case 8:
////                rootView = inflater.inflate(R.layout.step_setting_fragment8, container, false);
////                System.out.println("hello  3");
////                return rootView;
//            default:
                return rootView;

//        }
    }
    public int getPagenum(int page_num) {
        page = page_num;
        System.out.println("hello page"+page);
        return page;

    }

}

