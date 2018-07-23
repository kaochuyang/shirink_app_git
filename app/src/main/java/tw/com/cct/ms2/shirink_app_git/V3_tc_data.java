package tw.com.cct.ms2.shirink_app_git;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.greenrobot.eventbus.EventBus.TAG;

class V3_tc_data {


    private V3_tc_data() {
    }

    private static V3_tc_data tc_data = new V3_tc_data();
    /*一般日、特別日時段型態設定*/
    private static int[] start_year = new int[13];
    private static int[] start_month = new int[13];
    private static int[] start_day = new int[13];
    private static int[] end_year = new int[13];
    private static int[] end_month = new int[13];
    private static int[] end_day = new int[13];
    private static int[] weekday = new int[14];//一般日時段型態
    private JSONArray SpecialdaySegmentArray = new JSONArray();
    private JSONArray WeekdaySegmentArray = new JSONArray();
    private JSONObject[] specialdaycontext = new JSONObject[13];

    private void TodInfo_init() {
        try {
            SpecialdaySegmentArray = jsonObject.getJSONArray("specialdaycontext");
            WeekdaySegmentArray = jsonObject.getJSONArray("weekdaysegment");
            SpecialdayContent_init(SpecialdaySegmentArray, specialdaycontext);
            WeekDaySegment_init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String sendSegmentInfoToTc() {
        JSONObject json = new JSONObject();


        for (int i = 0; i < 13; i++) {
            try {
                SpecialdaySegmentArray.optJSONObject(i).put("start_year", start_year[i]);
                SpecialdaySegmentArray.optJSONObject(i).put("start_month", start_month[i]);
                SpecialdaySegmentArray.optJSONObject(i).put("start_day", start_day[i]);
                SpecialdaySegmentArray.optJSONObject(i).put("end_year", end_year[i]);
                SpecialdaySegmentArray.optJSONObject(i).put("end_month", end_month[i]);
                SpecialdaySegmentArray.optJSONObject(i).put("end_day", end_day[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        try {
            json.put("specialdaycontext", SpecialdaySegmentArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public String sendTODinfoToTc() {
        for (int i = 0; i < 7; i++) {
            try {
                WeekdaySegmentArray.put(i, weekday[i]);
                WeekdaySegmentArray.put(i + 7, weekday[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject json = new JSONObject();
        try {
            json.put("weekdaysegment", WeekdaySegmentArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private void WeekDaySegment_init() {
        for (int segmenttype = 0; segmenttype < 7; segmenttype++) {
            try {
                weekday[segmenttype] = (Integer) WeekdaySegmentArray.get(segmenttype);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getWeekday(int Weekday) {
        return weekday[Weekday];
    }

    public static void setWeekday(int Weekday, int Segmenttype) {
        weekday[Weekday] = Segmenttype;
    }

    private void SpecialdayContent_init(JSONArray jsonArray, final JSONObject[] specialdaycontext) {
        try {
            for (int i = 0; i < 13; i++) {
                specialdaycontext[i] = jsonArray.getJSONObject(i);
                start_year[i] = Integer.valueOf(specialdaycontext[i].getString("start_year"));
                start_month[i] = Integer.valueOf(specialdaycontext[i].getString("start_month"));
                start_day[i] = Integer.valueOf(specialdaycontext[i].getString("start_day"));
                end_year[i] = Integer.valueOf(specialdaycontext[i].getString("end_year"));
                end_month[i] = Integer.valueOf(specialdaycontext[i].getString("end_month"));
                end_day[i] = Integer.valueOf(specialdaycontext[i].getString("end_day"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getSpecialDay(int Segmenttype, boolean StartOrEnd) {
        Segmenttype = Segmenttype - 8;
        int day;
        if (StartOrEnd)//Start ==true ,End==False
            day = start_day[Segmenttype];
        else day = end_day[Segmenttype];
        return day;
    }

    public void setSpecialDay(int Segmenttype, int NewDay, boolean StartOrEnd) {
        Segmenttype = Segmenttype - 8;
        if (StartOrEnd) start_day[Segmenttype] = NewDay;
        else end_day[Segmenttype] = NewDay;
    }

    public int getSpecialMonth(int Segmenttype, boolean StartOrEnd) {
        Segmenttype = Segmenttype - 8;
        int month;
        if (StartOrEnd)//Start ==true ,End==False
            month = start_month[Segmenttype];
        else month = end_month[Segmenttype];
        return month;
    }

    public void setSpecialMonth(int Segmenttype, int Newmonth, boolean StartOrEnd) {
        Segmenttype = Segmenttype - 8;
        if (StartOrEnd) start_month[Segmenttype] = Newmonth;
        else end_month[Segmenttype] = Newmonth;
    }

    public int getSpecialYear(int Segmenttype, boolean StartOrEnd) {
        Segmenttype = Segmenttype - 8;
        int year;
        if (StartOrEnd)//Start ==true ,End==False
            year = start_year[Segmenttype];
        else year = end_year[Segmenttype];
        return year;
    }

    public void setSpecialYear(int Segmenttype, int Newyear, boolean StartOrEnd) {
        Segmenttype = Segmenttype - 8;
        if (StartOrEnd) start_year[Segmenttype] = Newyear;
        else end_year[Segmenttype] = Newyear;
    }


    /* 時段型態的時制計畫設定*/
    private static int[][] hour = new int[21][32];//segment,segment_count
    private static int[][] minute = new int[21][32];//segment,segment_count
    private static int[][] plan_num_record = new int[21][32];//segment,segment_count
    /*-------------------------------------------------------------------*/
    /*時制計畫中的時間設定*/
    static private PlanContext planContext=new PlanContext();
  private   JSONObject[] plancontext = new JSONObject[49];
    private void plancontext_init(JSONObject[] plancontext) {
        for (int i = 0; i < 49; i++) {
            try {
                plancontext[i] = jsonObject.getJSONArray("plancontext").optJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        PlanDetailValue_init(plancontext);
    }
    private void PlanDetailValue_init(JSONObject[] plancontext) {
        for (int PlanNum = 0; PlanNum < 49; PlanNum++)
        {
            try {
                Log.d("!!!!!", "PlanDetailValue_init: num="+PlanNum+" "+plancontext[PlanNum].toString());
                planContext.Cycle_value[PlanNum] = Integer.valueOf(plancontext[PlanNum].getString("cycle_time"));
                planContext.Offset[PlanNum] = Integer.valueOf(plancontext[PlanNum].getString("offset"));
                planContext.PhaseOrderOfPlan[PlanNum]= Integer.valueOf(plancontext[PlanNum].getString("phase_order"));
                planContext.subphase_count[PlanNum] =total_subphasecount[planContext.PhaseOrderOfPlan[PlanNum]];
                //Integer.valueOf(plancontext[PlanNum].getString("subphase_count"));
                //這個參數要和step_setting的total_subphasecount[phaseorder]相等
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int subphase = 0; subphase < 8; subphase++) {

                try {
                    planContext.green[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_green").get(subphase).toString());
                    planContext.red[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_allred").get(subphase).toString());
                    planContext.yellow[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_yellow").get(subphase).toString());
                    planContext.pedF[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_pedgreen_flash").get(subphase).toString());
                    planContext.pedR[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_pedred").get(subphase).toString());
                    planContext.minG[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_min_green").get(subphase).toString());
                    planContext.maxG[PlanNum][subphase] = Integer.valueOf(plancontext[PlanNum]
                            .getJSONArray("subphase_max_green").get(subphase).toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public String sendPlanContextToTc(int PlanNum)
    {
        try {


            plancontext[PlanNum].put("cycle_time",planContext.Cycle_value[PlanNum]);
            plancontext[PlanNum].put("offset",planContext.Offset[PlanNum]);
            plancontext[PlanNum].put("subphase_count",planContext.subphase_count[PlanNum]);
            plancontext[PlanNum].put("phase_order",planContext.PhaseOrderOfPlan[PlanNum]);
            for(int Subphase=0;Subphase<planContext.subphase_count[PlanNum];Subphase++) {
                plancontext[PlanNum].optJSONArray("subphase_allred").put(Subphase,planContext.red[PlanNum][Subphase]);
                plancontext[PlanNum].optJSONArray("subphase_green").put(Subphase,planContext.green[PlanNum][Subphase]);
                plancontext[PlanNum].optJSONArray("subphase_yellow").put(Subphase,planContext.yellow[PlanNum][Subphase]);
                plancontext[PlanNum].optJSONArray("subphase_pedgreen_flash").put(Subphase,planContext.pedF[PlanNum][Subphase]);
                plancontext[PlanNum].optJSONArray("subphase_pedred").put(Subphase,planContext.pedR[PlanNum][Subphase]);
                plancontext[PlanNum].optJSONArray("subphase_min_green").put(Subphase,planContext.minG[PlanNum][Subphase]);
                plancontext[PlanNum].optJSONArray("subphase_max_green").put(Subphase,planContext.maxG[PlanNum][Subphase]);
            }
            for(int Subphase=planContext.subphase_count[PlanNum];Subphase<8;Subphase++) {
                plancontext[PlanNum].optJSONArray("subphase_allred").put(Subphase,0);
                planContext.red[PlanNum][Subphase]=0;
                plancontext[PlanNum].optJSONArray("subphase_green").put(Subphase,0);
                planContext.green[PlanNum][Subphase]=0;
                plancontext[PlanNum].optJSONArray("subphase_yellow").put(Subphase,0);
                planContext.yellow[PlanNum][Subphase]=0;
                plancontext[PlanNum].optJSONArray("subphase_pedgreen_flash").put(Subphase,0);
                planContext.pedF[PlanNum][Subphase]=0;
                plancontext[PlanNum].optJSONArray("subphase_pedred").put(Subphase,0);
                planContext.pedR[PlanNum][Subphase]=0;
                plancontext[PlanNum].optJSONArray("subphase_min_green").put(Subphase,0);
                planContext.minG[PlanNum][Subphase]=0;
                plancontext[PlanNum].optJSONArray("subphase_max_green").put(Subphase,999);
                planContext.maxG[PlanNum][Subphase]=0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject json=new JSONObject();
        try {
            json.put("plancontext",plancontext[PlanNum]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    public static PlanContext getPlanContext() {
        return planContext;
    }
    public static void setPlanContext(PlanContext planContext) {
        V3_tc_data.planContext = planContext;
    }

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
            JSONArray light = null;
            JSONObject o_light = null;
            JSONArray stepdetail = null;
            JSONObject o_stepdetail = null;
            JSONArray sub_stepcount = new JSONArray();
            for (int SPN = 0; SPN < total_subphasecount[PhaseOrder]; SPN++) {//SPN = SubPhaseNum
                stepdetail = new JSONArray();
                sub_stepcount.put(SPN, 5);
                for (int step = 0; step < 5; step++) {
                    light = new JSONArray();
                    for (int LBN = 0; LBN < light_board_count[PhaseOrder]; LBN++) {//LBN = Light Board Num
                        light.put(LBN, CombineLightState(PhaseOrder, SPN, step, LBN));
                    }
                    o_light = new JSONObject();
                    o_light.put("light", light);
                    //Log.d(TAG, "sendStepSettingToTc: step"+step+" ="+light.toString());
                    stepdetail.put(step, o_light);
                }
                o_stepdetail = new JSONObject();
                o_stepdetail.put("stepdetail", stepdetail);
                subphase.put(SPN, o_stepdetail);
            }
            step_object[PhaseOrder].optJSONObject("stepcontext").put("subphase", subphase);

            for (int SPN = total_subphasecount[PhaseOrder]; SPN < 8; SPN++)
                sub_stepcount.put(SPN, 0);
            step_object[PhaseOrder].put("sub_stepcount", sub_stepcount);


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
       refreshdata();
    }

    public void refreshdata()//只是把jsonObject 的內容重新讀取過
    {
        init_segcontext();
        init_setp_object();
        TodInfo_init();
        plancontext_init(plancontext);
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

    public String SendSegcontextToTc(int segmenttype) {
        for (int SegmentCount = 0; SegmentCount < 32; SegmentCount++) {
            try {
                segcontext[segmenttype].optJSONArray("hour").put(SegmentCount, hour[segmenttype][SegmentCount]);
                segcontext[segmenttype].optJSONArray("minute").put(SegmentCount, minute[segmenttype][SegmentCount]);
                segcontext[segmenttype].optJSONArray("plan").put(SegmentCount, plan_num_record[segmenttype][SegmentCount]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject json = new JSONObject();
        try {
            json.put("segmentinfo", segcontext[segmenttype]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
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
