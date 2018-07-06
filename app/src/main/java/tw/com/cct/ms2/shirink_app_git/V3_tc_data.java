package tw.com.cct.ms2.shirink_app_git;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

class V3_tc_data {


     private  V3_tc_data(){}
     private  static V3_tc_data  tc_data=new V3_tc_data();

    static int [][]hour=new int[21][32];//segment,segment_count
    static int [][]minute=new int[21][32];//segment,segment_count
    static int [][]plan_num_record=new int[21][32];//segment,segment_count

    static JSONObject jsonObject=new JSONObject();
    static JSONObject[] segcontext=new JSONObject[21];
     public static V3_tc_data getV3_tc_data()
     {
         return tc_data;
     }

public boolean put_tc_data(JSONObject object)
{
    jsonObject=object;
init_segcontext();
    return true;
}
public JSONObject getV3_json_data()
{
    return jsonObject;
}

public boolean save_edit_data(JSONObject object)
{
    jsonObject=object;


    return true;
}



    private void init_segcontext( ) {
        try {
            for (int i = 0; i < 21; i++)
            {       segcontext[i] = jsonObject.getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(i);
                for(int segment_count=0;segment_count<32;segment_count++) {
                    hour[i][segment_count] = segcontext[i].getJSONArray("hour").getInt(segment_count);
                    minute[i][segment_count] = segcontext[i].getJSONArray("minute").getInt(segment_count);
                    plan_num_record[i][segment_count]=segcontext[i].getJSONArray("plan").getInt(segment_count);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setHour(int segment,int segment_count, int input_hour)
    {
        hour[segment][segment_count]=input_hour;
    }

    public void setMinute(int segment,int segment_count,int input_minute)
    {
        minute[segment][segment_count]=input_minute;

    }

    public void setPlan_num_record(int segment,int segment_count,int input_plan_num) {
        plan_num_record[segment][segment_count] = input_plan_num;
    }

    public int getPlan_num_record(int segment,int segment_count) {
        return plan_num_record[segment][segment_count];
    }

    public int getHour(int segment,int segment_count) {
        return hour[segment][segment_count];
    }

    public int getMinute(int segment,int segment_count) {
        return this.minute[segment][segment_count];
    }

    public void print_plan()
    {
        for(int i=0;i<32;i++)
        //Log.d("!!!!", "print_hour: "+hour[1][i]);
        Log.d("!!!!", "print_plan: "+plan_num_record[1][i]);
    }


}
