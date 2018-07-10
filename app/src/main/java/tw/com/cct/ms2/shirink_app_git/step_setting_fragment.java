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
    int step_num;
    V3_tc_data A = V3_tc_data.getV3_tc_data();
    final JSONObject jsonObject = A.getV3_json_data();
    JSONObject[] step_object=new JSONObject[256];
    TextView []board_=new TextView[6];

    static int phaseorder=0;
    static int subphase=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView page=" + page);
        rootView = inflater.inflate(R.layout.step_setting_fragment1, container, false);

        Button_findview();//連結xml
        Button_color_init();//給按鈕變色功能
        view_state_init();//初始化按鈕狀態和顏色
        Log.d("!!!!!!!", "onCreateView: PAGE_NUM="+page+"  phaseorder="+phaseorder+" subphase="+subphase);


        return getView(inflater, container);
    }

    private void view_state_init() {
        light_state_get(jsonObject, step_object,light_board_decide(phaseorder),page-1,subphase,phaseorder);
        Log.d("!!!!!!!", "view_state_init: PAGE_NUM="+page+"  phaseorder="+phaseorder+" subphase="+subphase);
        button_color_start_state();//初始化按鈕顏色
        button_visible_control(light_board_decide(phaseorder));//決定按鈕隱藏與否
    }

    private int  light_board_decide(int phaseorder) {
        int  count=0;
        try {//////決定幾張燈卡
           count=
                    jsonObject.getJSONArray("step").getJSONObject(phaseorder).getInt("signal_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return count;
    }
    private void button_visible_control(int light_board_count) {
        for(int i=light_board_count;i<6;i++) {
            Button_red_[i].setVisibility(rootView.GONE);
            Button_green_[i].setVisibility(rootView.GONE);
            Button_left_[i].setVisibility(rootView.GONE);
            Button_right_[i].setVisibility(rootView.GONE);
            Button_straight_[i].setVisibility(rootView.GONE);
            Button_yellow_[i].setVisibility(rootView.GONE);
            Button_ped_flash_[i].setVisibility(rootView.GONE);
            Button_ped_red_[i].setVisibility(rootView.GONE);
            board_[i].setVisibility(rootView.GONE);
        }
        for(int i=0;i<light_board_count;i++) {
            Button_red_[i].setVisibility(rootView.VISIBLE);
            Button_green_[i].setVisibility(rootView.VISIBLE);
            Button_left_[i].setVisibility(rootView.VISIBLE);
            Button_right_[i].setVisibility(rootView.VISIBLE);
            Button_straight_[i].setVisibility(rootView.VISIBLE);
            Button_yellow_[i].setVisibility(rootView.VISIBLE);
            Button_ped_flash_[i].setVisibility(rootView.VISIBLE);
            Button_ped_red_[i].setVisibility(rootView.VISIBLE);
            board_[i].setVisibility(rootView.VISIBLE);
        }
    }

    private void button_color_start_state() {
        for(int i=0;i<6;i++) {
                  red_color_state(Button_red_, red_state_, i);
            green_color_state(Button_green_, green_state_, i);
            green_color_state(Button_left_, left_state_, i);
            green_color_state(Button_straight_, straight_state_, i);
            green_color_state(Button_ped_flash_, ped_green_state_, i);
            red_color_state(Button_ped_red_, ped_red_state_, i);
            green_color_state(Button_right_, right_state_, i);
            yellow_color_state(Button_yellow_, yellow_state_, i);
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
        light_state_get(jsonObject, step_object,light_board_decide(phaseorder),step_num,subphase,phaseorder);
       // button_color_start_state();
        view_state_init();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void phaseorder_set_select(MessageEvent_phaseorder messageEvent)
    {
        phaseorder=messageEvent.getphaseorder();
        view_state_init();
    }

    private void light_state_get(JSONObject jsonObject, JSONObject[] step_object,
                                 int light_board_count,int step_num,
                                 int subphase_num,int phaseorder) {//i 代表第i+1排燈卡
        try {
            //subphase_num=1;
            Log.d("!!!!!!!!!!!!!!!!!!!!! ","---------: step="+step_num);
            step_object[phaseorder]=jsonObject.getJSONArray("step").getJSONObject(phaseorder);
            for (int light_board_num=0;light_board_num<light_board_count;light_board_num++)
            {    //light_state[]順序(每兩個byte為一個燈態狀態):行人綠 行人紅 → ↑ 綠燈 ← 黃燈 紅燈
                Log.d("SUBPHASE", "light_state_get: phaseorder="+phaseorder+" subphase="+subphase_num+" lightboard="+light_board_num+" step="+step_num);

                String[]light_state=String.format("%16s",Integer.toBinaryString(Integer.parseInt(
                    step_object[phaseorder].getJSONObject("stepcontext")
                    .getJSONArray("subphase")
                    .getJSONObject(subphase_num)
                    .getJSONArray("stepdetail")
                    .getJSONObject(step_num)
                    .getJSONArray("light")
                    .get(light_board_num)
                    .toString()))).split("");

             ped_green_state_[light_board_num]=state_check(light_state,1);
            ped_red_state_[light_board_num]=state_check(light_state,3);
            right_state_[light_board_num]=state_check(light_state,5);
            straight_state_[light_board_num]=state_check(light_state,7);
            green_state_[light_board_num]=state_check(light_state,9);
            left_state_[light_board_num]=state_check(light_state,11);
            yellow_state_[light_board_num]=state_check(light_state,13);
            red_state_[light_board_num]=state_check(light_state,15);}


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private int state_check(String[]strings,int i)
    {
        int state=0;
             if(strings[i].equals(strings[i+1])){
            state = strings[i].equals("1") ? 1 : 0;
       }
        else state=2;

        return state;
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
    private void green_color_state(final Button []Button_green_,final int[]green_state_,final int i ) {
          

                if(green_state_[i]==0)Button_green_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(green_state_[i]==2)Button_green_[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OUT);
                if(green_state_[i]==1)Button_green_[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);

    }
    private void yellow_color_state(final Button []Button_yellow_,final int[]yellow_state_,final int i ) {


                if(yellow_state_[i]==0)Button_yellow_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(yellow_state_[i]==2)Button_yellow_[i].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_OUT);
                if(yellow_state_[i]==1)Button_yellow_[i].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
     
    }
    private void red_color_state(final Button []Button_red_,final int[]red_state_,final int i ) {


                if(red_state_[i]==0)Button_red_[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                if(red_state_[i]==2)Button_red_[i].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_OUT);
                if(red_state_[i]==1)Button_red_[i].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
           
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

