package tw.com.cct.ms2.shirink_app_git;

import org.json.JSONObject;

class V3_tc_data {


     private  V3_tc_data(){}
     private  static V3_tc_data  tc_data=new V3_tc_data();



    static JSONObject jsonObject=new JSONObject();

     public static V3_tc_data getV3_tc_data()
     {
         return tc_data;
     }

public boolean put_tc_data(JSONObject object)
{
    jsonObject=object;

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
}
