package tw.com.cct.ms2.shirink_app_git;

import android.util.Log;

class PlanContext {
    int[][] red = new int[49][8];//PlanNum,subphase
    int[][] green = new int[49][8];
    int[][] yellow = new int[49][8];
    int[][] pedR = new int[49][8];
    int[][] pedF = new int[49][8];
    int[][] maxG = new int[49][8];
    int[][] minG = new int[49][8];
     int[] Cycle_value = new int[49];
    int[] Offset = new int[49];
    int[] subphase_count = new int[49];//第 i plan有多少個分相
    int[]PhaseOrderOfPlan=new int[49];//用來記載 第i個plan用 哪一個phaseorder的步階組

    public int getCycle_value(int PlanNum)
    {
        Cycle_value[PlanNum] = 0;
        for (int LightBoard = 0; LightBoard < 8; LightBoard++) {
            Cycle_value[PlanNum] += green[PlanNum][LightBoard] + red[PlanNum][LightBoard] + yellow[PlanNum][LightBoard] + pedF[PlanNum][LightBoard] + pedR[PlanNum][LightBoard];

        }
        return Cycle_value[PlanNum];}





}
