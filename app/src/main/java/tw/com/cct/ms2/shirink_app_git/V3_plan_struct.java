package tw.com.cct.ms2.shirink_app_git;

import android.support.annotation.NonNull;

public class V3_plan_struct implements Comparable<V3_plan_struct> {

    int hour;
    int minute;
    int planID;

    public int getHour() {
        return hour;
    }

    public int getPlanID() {
        return planID;
    }

    public int getMinute() {
        return minute;
    }

    V3_plan_struct(int hour,int minute,int planID){
        this.hour=hour;
        this.minute=minute;
        this.planID=planID;

    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    @Override
    public int compareTo(@NonNull V3_plan_struct other) {
        int result=0;
        if((this.hour-other.hour)==0)
            result=this.minute-other.minute;
        else result=this.hour-other.hour;

        return result;
    }
}
