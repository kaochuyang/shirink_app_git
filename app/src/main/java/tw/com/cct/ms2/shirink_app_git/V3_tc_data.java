package tw.com.cct.ms2.shirink_app_git;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.greenrobot.eventbus.EventBus.TAG;

import static tw.com.cct.ms2.shirink_app_git.V3_connection_activity.send;

class V3_tc_data {


    private V3_tc_data() {
    }

    private static V3_tc_data tc_data = new V3_tc_data();
    /* 時段型態的時制計畫設定*/
    private static int[][] hour = new int[21][32];//segment,segment_count
    private static int[][] minute = new int[21][32];//segment,segment_count
    private static int[][] plan_num_record = new int[21][32];//segment,segment_count
    /*-------------------------------------------------------------------*/
    /*時制計畫中的時間設定*/
    static int[][][][] red = new int[256][8][5][6];//phase_order,subphase,step,lightboard
    static int[][][][] green = new int[256][8][5][6];
    static int[][][][] yellow = new int[256][8][5][6];
    static int[][][][] pedR = new int[256][8][5][6];
    static int[][][][] pedF = new int[256][8][5][6];
    static int[][][][] maxG = new int[256][8][5][6];
    static int[][][][] vminG = new int[256][8][5][6];
    static int[] getCycle_value = new int[256];
    static int[] getOffset = new int[256];
    /*-----------------------------------------------*/
    /*點燈步階中的燈態狀態 0:不亮 1:亮 2:閃爍 因為用split拆解，懶得再轉成int 所以直接用string*/
    public static int[][][][] ped_green_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] ped_red_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] right_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] straight_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] green_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] left_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] yellow_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    public static int[][][][] red_state = new int[256][8][5][6];//phase_order,subphasecount,lightboard_num
    private static int[] total_subphasecount = new int[256];//phase_order
    private static int[] light_board_count = new int[256];//phase_order
    /*----------------------------------------------------------*/

    private int CombineLightState(int PhaseOrder, int SubPhaseCount, int Step, int LightBoardNum) {
        /*組裝順序  行人綠 行人紅 右 直 綠 左 黃 紅*/
        String s_result;
        s_result = TSTS(ped_green_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(ped_red_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(right_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(straight_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(green_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(left_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(yellow_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]) + TSTS(red_state[PhaseOrder][SubPhaseCount][Step][LightBoardNum]);

        int result = Integer.valueOf(s_result, 2);

        return result;
    }

    private String TSTS(int state)//Transfer State To String
    {
        String result = "00";
        switch (state) {
            case 0:
                result = "00";
                break;
            case 1:
                result = "11";
                break;
            case 2:
                result = "10";
                break;
        }
        return result;
    }


    public int getTotalSubphaseCount(int PhaseOrder) {
        return total_subphasecount[PhaseOrder];
    }

    public void setTotalSubphaseCount(int totalSubphaseCount, int PhaseOrder) {
        total_subphasecount[PhaseOrder] = totalSubphaseCount;
    }

    public int getTotalLightBoardCount(int PhaseOrder) {
        return light_board_count[PhaseOrder];
    }

    public void setLightBoardCount(int TotalLioghtBoardCount, int PhaseOrder) {
        light_board_count[PhaseOrder] = TotalLioghtBoardCount;
    }


    public String sendStepSettingToTc(int PhaseOrder) {
        try {
            Log.d(TAG, "sendStepSettingToTc: " + step_object[PhaseOrder].toString());
            step_object[PhaseOrder].put("subphase_count", total_subphasecount[PhaseOrder]);
            step_object[PhaseOrder].put("signal_count", light_board_count[PhaseOrder]);
            JSONArray subphase = new JSONArray();
            JSONArray light= null;
            JSONObject o_light= null;
            JSONArray stepdetail = null;
            JSONObject o_stepdetail= null;
            JSONArray sub_stepcount=new JSONArray();
            for (int SPN = 0; SPN < total_subphasecount[PhaseOrder]; SPN++) {//SPN = SubPhaseNum
                stepdetail=new JSONArray();
                sub_stepcount.put(SPN,5);
                for (int step = 0; step < 5; step++) {
                     light = new JSONArray();
                                         for (int LBN = 0; LBN < light_board_count[PhaseOrder]; LBN++) {//LBN = Light Board Num
                        light.put(LBN, CombineLightState(PhaseOrder, SPN, step, LBN));
                    }
                     o_light = new JSONObject();
                    o_light.put("light", light);
                    //Log.d(TAG, "sendStepSettingToTc: step"+step+" ="+light.toString());
                    stepdetail.put(step,o_light);
                }
                o_stepdetail=new JSONObject();
                o_stepdetail.put("stepdetail",stepdetail);
                subphase.put(SPN,o_stepdetail);
            }
            step_object[PhaseOrder].optJSONObject("stepcontext").put("subphase",subphase);

            for(int SPN=total_subphasecount[PhaseOrder];SPN<8;SPN++)
                sub_stepcount.put(SPN,0);
            step_object[PhaseOrder].put("sub_stepcount",sub_stepcount);


            Log.d(TAG, "sendStepSettingToTc:2222 " + step_object[PhaseOrder].toString());


//            JSONArray light = new JSONArray();
//            for (int LBN = 0; LBN < light_board_count[PhaseOrder]; LBN++) {//LBN = Light Board Num
//                light.put(LBN, CombineLightState(0, 0, 0, LBN));
//            }

//            Log.d(TAG, "sendStepSettingToTc: " + subphase.toString());

//            step_object[phaseorder]
//                    .getJSONObject("stepcontext")
//                    .getJSONArray("subphase")
//                    .getJSONObject(subphase_num)
//                    .getJSONArray("stepdetail")
//                    .getJSONObject(step_num)
//                    .getJSONArray("light").get(LightBoardNum).toString()))).split("");
//
//            step_object[phaseorder]
//                    .getJSONObject("stepcontext")
//                    .getJSONArray("subphase")
//                    .getJSONObject(0)
//                    .getJSONArray("stepdetail")
//                    .getJSONObject(0)
//                    .getJSONArray("light").put();





        } catch (JSONException e) {
            e.printStackTrace();
        }

return step_object[PhaseOrder].toString();
    }


    static JSONObject jsonObject = new JSONObject();
    private static JSONObject[] segcontext = new JSONObject[21];
    private static JSONObject[] step_object = new JSONObject[256];//for step_setting

    private void init_setp_object() {
        for (int phaseorder = 0; phaseorder < 256; phaseorder++) {
            try {
                step_object[phaseorder] = jsonObject.getJSONArray("step").getJSONObject(phaseorder);
                total_subphasecount[phaseorder] = Integer.parseInt(String.valueOf(jsonObject.getJSONArray("step").getJSONObject(phaseorder).get("subphase_count")));
                light_board_count[phaseorder] = jsonObject.getJSONArray("step").getJSONObject(phaseorder).getInt("signal_count");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//Log.d("SUBPHASE", "light_state_get: phaseorder="+phaseorder+" subphase="+subphase_num+" lightboard="+light_board_num+" step="+step_num);
        for (int phaseorder = 0; phaseorder < 256; phaseorder++) {
            for (int subphase_num = 0; subphase_num < total_subphasecount[phaseorder]; subphase_num++) {
//        int subphase_num=0;
//        int phaseorder=0;
                for (int step_num = 0; step_num < 5; step_num++) {

                    try {
                        for (int LightBoardNum = 0; LightBoardNum < light_board_count[phaseorder]; LightBoardNum++) {
                            /*light_state[]順序(每兩個byte為一個燈態狀態):行人綠 行人紅 → ↑ 綠燈 ← 黃燈 紅燈*/
                            String[] light_state = String.format("%16s", Integer.toBinaryString(Integer.parseInt(step_object[phaseorder].getJSONObject("stepcontext").getJSONArray("subphase").getJSONObject(subphase_num).getJSONArray("stepdetail").getJSONObject(step_num).getJSONArray("light").get(LightBoardNum).toString()))).split("");

                            ped_green_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 1);
                            ped_red_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 3);
                            right_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 5);
                            straight_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 7);
                            green_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 9);
                            left_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 11);
                            yellow_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 13);
                            red_state[phaseorder][subphase_num][step_num][LightBoardNum] = state_check(light_state, 15);
                        }

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }


    }


    /*for step state check*/
    private int state_check(String[] strings, int i) {
        int state;
        if (strings[i].equals(strings[i + 1])) {
            state = strings[i].equals("1") ? 1 : 0;
        } else state = 2;

        return state;
    }

    public static V3_tc_data getV3_tc_data() {
        return tc_data;
    }

    public void put_tc_data(JSONObject object) {
        jsonObject = object;
        init_segcontext();
        init_setp_object();

    }

    public void refreshdata()//只是把jsonObject 的內容重新放過
    {
        init_segcontext();
        init_setp_object();

    }

    public JSONObject getV3_json_data() {
        return jsonObject;
    }

    public boolean save_edit_data(JSONObject object) {
        jsonObject = object;
        return true;
    }


    public void copySegment(int SourceSegment, int TargetSegment)//幫plan_setting準備的
    {
        hour[TargetSegment] = hour[SourceSegment];
        minute[TargetSegment] = minute[SourceSegment];
        plan_num_record[TargetSegment] = plan_num_record[SourceSegment];

    }

    private void init_segcontext() {
        try {
            for (int i = 0; i < 21; i++) {
                segcontext[i] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(i);
                for (int segment_count = 0; segment_count < 32; segment_count++) {
                    hour[i][segment_count] = segcontext[i].getJSONArray("hour").getInt(segment_count);
                    minute[i][segment_count] = segcontext[i].getJSONArray("minute").getInt(segment_count);
                    plan_num_record[i][segment_count] = segcontext[i].getJSONArray("plan").getInt(segment_count);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    

    public void setHour(int segment, int segment_count, int input_hour) {
        hour[segment][segment_count] = input_hour;
    }

    public void setMinute(int segment, int segment_count, int input_minute) {
        minute[segment][segment_count] = input_minute;

    }

    public void setPlan_num_record(int segment, int segment_count, int input_plan_num) {
        plan_num_record[segment][segment_count] = input_plan_num;
    }

    public int getPlan_num_record(int segment, int segment_count) {
        return plan_num_record[segment][segment_count];
    }

    public int getHour(int segment, int segment_count) {
        return hour[segment][segment_count];
    }

    public int getMinute(int segment, int segment_count) {
        return minute[segment][segment_count];
    }

    public void print_plan() {
        for (int i = 0; i < 32; i++)
            //Log.d("!!!!", "print_hour: "+hour[1][i]);
            Log.d("!!!!", "print_plan: " + plan_num_record[1][i]);
    }

    public void print_step() {
        Log.d(TAG, "print_step: " + step_object[0].toString());
        for (int step = 0; step < 5; step++)
            for (int lightboard = 0; lightboard < 6; lightboard++) {
                Log.d(TAG, "print_step: red=" + ped_red_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: yelo=" + yellow_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: left=" + left_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: green=" + green_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: straigh=" + straight_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: right=" + right_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: ped_red=" + ped_red_state[0][0][step][lightboard] + " lightb=" + lightboard);
                Log.d(TAG, "print_step: pedflash=" + ped_green_state[0][0][step][lightboard] + " lightb=" + lightboard);
            }

    }


    public void plan_sort(int segment) {
        int total_SegmentCount = 1;
        ArrayList<V3_plan_struct> plan_structs = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            if ((hour[segment][i] > 0) || (minute[segment][i] > 0)) {
                total_SegmentCount++;
                plan_structs.add(new V3_plan_struct(hour[segment][i], minute[segment][i], plan_num_record[segment][i]));
                Log.d(TAG, "plan_sort: i=" + i + " hour=" + hour[segment][i] + " plan=" + plan_num_record[segment][i]);
            }
        }
        Collections.sort(plan_structs);

        Arrays.fill(hour[segment], total_SegmentCount, 31, 0);
        Arrays.fill(minute[segment], total_SegmentCount, 31, 0);
        Arrays.fill(plan_num_record[segment], total_SegmentCount, 31, 0);
        hour[segment][0] = 0;
        minute[segment][0] = 0;

        for (int i = 1; i < total_SegmentCount; i++) {
            hour[segment][i] = plan_structs.get(i - 1).hour;
            minute[segment][i] = plan_structs.get(i - 1).minute;
            plan_num_record[segment][i] = plan_structs.get(i - 1).planID;
        }
    }

}
