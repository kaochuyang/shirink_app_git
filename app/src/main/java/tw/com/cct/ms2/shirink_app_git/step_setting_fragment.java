package tw.com.cct.ms2.shirink_app_git;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.ColorInt;
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
   static boolean EditableFlag=false;
    static int phaseorder=0;
    static int subphase=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView page=" + page);
        rootView = inflater.inflate(R.layout.step_setting_fragment1, container, false);

        Button_findview();//連結xml
        Button_init(phaseorder,subphase,page-1);//給按鈕變色功能
        view_state_init();//初始化按鈕狀態和顏色

        Log.d("!!!!!!!", "onCreateView: PAGE_NUM="+page+"  phaseorder="+phaseorder+" subphase="+subphase);

        return getView(inflater, container);
    }

    private void view_state_init() {
        //light_state_get(jsonObject, step_object,light_board_decide(phaseorder),page-1,subphase,phaseorder);
        Log.d("!!!!!!!", "view_state_init: PAGE_NUM="+page+"  phaseorder="+phaseorder+" subphase="+subphase);
        button_color_start_state(phaseorder,subphase,page-1);//初始化按鈕顏色
        button_visible_control(light_board_decide(phaseorder));//決定按鈕隱藏與否
        edittext_switch_group(EditableFlag);
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

    private void edittext_switch_group(boolean editbla_flag) {
        for(int i=0;i<6;i++) {
            Button_red_[i].setClickable(editbla_flag);
            Button_green_[i].setClickable(editbla_flag);
            Button_left_[i].setClickable(editbla_flag);
            Button_right_[i].setClickable(editbla_flag);
            Button_straight_[i].setClickable(editbla_flag);
            Button_yellow_[i].setClickable(editbla_flag);
            Button_ped_flash_[i].setClickable(editbla_flag);
            Button_ped_red_[i].setClickable(editbla_flag);

        }


    }
    
    
    private void button_color_start_state(int PhaseOrder, int SubphaseCount, int StepNum) {
        for(int LightBoard=0;LightBoard<6;LightBoard++) {
            button_color_state(Button_red_, V3_tc_data.red_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.RED);
            button_color_state(Button_green_, V3_tc_data.green_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.GREEN);
            button_color_state(Button_left_, V3_tc_data.left_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.GREEN);
            button_color_state(Button_straight_, V3_tc_data.straight_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.GREEN);
            button_color_state(Button_ped_flash_, V3_tc_data.ped_green_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.GREEN);
            button_color_state(Button_ped_red_, V3_tc_data.ped_red_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.RED);
            button_color_state(Button_right_, V3_tc_data.right_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.GREEN);
            button_color_state(Button_yellow_, V3_tc_data.yellow_state[PhaseOrder][SubphaseCount][StepNum][LightBoard], LightBoard,Color.YELLOW);
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
    public void editable_select(MessageEvent_Editable messageEvent)
    {
  EditableFlag=messageEvent.getStepEditable();

           view_state_init();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void step_set_select(MessageEvent_subphase messageEvent)
    {

        subphase=messageEvent.getsubphase();

        view_state_init();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void phaseorder_set_select(MessageEvent_phaseorder messageEvent)
    {
        phaseorder=messageEvent.getphaseorder();
          Button_init(phaseorder,subphase,page-1);//給按鈕變色功能


    }


    private void Button_init(int PhaseOrder, int SubphaseCount, int StepNum) {
        for(int LightBoardNum=0;LightBoardNum<6;LightBoardNum++) {
            button_color_init(Button_red_, V3_tc_data.red_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.RED);
            button_color_init(Button_green_, V3_tc_data.green_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.GREEN);
            button_color_init(Button_left_, V3_tc_data.left_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.GREEN);
            button_color_init(Button_straight_, V3_tc_data.straight_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.GREEN);
            button_color_init(Button_ped_flash_, V3_tc_data.ped_green_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.GREEN);
            button_color_init(Button_ped_red_, V3_tc_data.ped_red_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.RED);
            button_color_init(Button_right_, V3_tc_data.right_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.GREEN);
            button_color_init(Button_yellow_, V3_tc_data.yellow_state[PhaseOrder][SubphaseCount][StepNum], LightBoardNum,Color.YELLOW);


        }
    }
    private void button_color_init(final Button []Button_color_,final int[]color_state_,final int LightBoardNum,@ColorInt final int color ) {
        Button_color_[LightBoardNum].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_state(color_state_,LightBoardNum);
                if(color_state_[LightBoardNum]==0)Button_color_[LightBoardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(color_state_[LightBoardNum]==2)Button_color_[LightBoardNum].getBackground().setColorFilter(color, PorterDuff.Mode.SRC_OUT);
                if(color_state_[LightBoardNum]==1)Button_color_[LightBoardNum].getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
        });
    }
    private void button_state(final int []state,int LightBoardNum) {
        state[LightBoardNum]++;
        if(state[LightBoardNum]==3)state[LightBoardNum]=0;

    }
    private void button_color_state(final Button []Button_,final int state,final int LightBoardNum,@ColorInt final int color  ) {


        if(state==0)Button_[LightBoardNum].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        if(state==2)Button_[LightBoardNum].getBackground().setColorFilter(color, PorterDuff.Mode.SRC_OUT);
        if(state==1)Button_[LightBoardNum].getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

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

