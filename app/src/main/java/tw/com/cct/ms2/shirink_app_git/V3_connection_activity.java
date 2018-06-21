package tw.com.cct.ms2.shirink_app_git;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class V3_connection_activity extends Base_activity {
    private String tmp;                    //做為接收時的緩存
    private JSONObject json_write, json_read,jsonALL;        //從java伺服器傳遞與接收資料的json
    // private OnMessageReceived mMessageListner = null;
    Socket socket = null;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    Button test;
    Handler handler = new Handler();
    JSONObject jsonObject=new JSONObject();

    V3_tc_data A=V3_tc_data.getV3_tc_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_v3_message);
        Button send_m = findViewById(R.id.send_m);
        test = findViewById(R.id.test);

        Map map = new HashMap();
        map.put("name", "Jacky");
        map.put("age", 30);
        map.put("gender", true);
        final JSONObject jsonObjectJacky = new JSONObject(map);
        final EditText textA = (EditText) findViewById(R.id.et_show);
        final TextView textB = findViewById(R.id.state_v3);
        EditText textC = findViewById(R.id.edit);


        send_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textB.append("手機說:"+textA.getText().toString()+"\n");
                send(textA.getText().toString());
                textA.setText("");
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();

            }
        });


    }

    public void connect() {

        final TextView textB = findViewById(R.id.state_v3);
       AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String line;
                try {
                    try {
                        socket = new Socket("192.168.1.90", 5000);
                    } catch (IOException e) {
                        Toast.makeText(V3_connection_activity.this, "連接失敗", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    try {
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    } catch (IOException e) {
                        Toast.makeText(V3_connection_activity.this, "發送失敗", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        Toast.makeText(V3_connection_activity.this, "server內容讀取失敗", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    publishProgress("init success");

                    while ((line = reader.readLine()) != null) {
                        publishProgress(line);
                       json_read=new JSONObject(line);
                       jsonObject=merge(jsonObject,json_read);
                       JSONArray jsonArray;
                       // jsonArray=json_read.getJSONArray("weekdaysegment");
                        Log.d("JSON", "doInBackground: WeekDayArray "+jsonObject.toString());
                        A.put_tc_data(json_read);
                        //json_read.getString("Data")
                       // json_read.getJSONObject(line);
                        //Log.d("jsonobject", "doInBackground: "+json_read.toString());
                        //Log.d("jsonobjectALL", "doInBackground: "+jsonObject.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

           @Override
           protected void onPostExecute(Void aVoid) {
               super.onPostExecute(aVoid);
           }

           @Override
            protected void onProgressUpdate(String... values) {
                textB.append("server說:"+values[0]+"\n");
               //textB.append("server說:"+line+"\n");
                Toast.makeText(V3_connection_activity.this, "連接成功", Toast.LENGTH_SHORT).show();
                super.onProgressUpdate(values);
            }


       };
        read.execute();

    }
    public void send(String A){
        try {
            writer.write(A);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
    private static JSONObject merge(JSONObject... jsonObjects) throws JSONException {//用來拼接多個json物件用的

        JSONObject jsonObject = new JSONObject();

        for(JSONObject temp : jsonObjects){
            Iterator<String> keys = temp.keys();
            while(keys.hasNext()){
                String key = keys.next();
                jsonObject.put(key, temp.get(key));
            }

        }
        return jsonObject;
    }

}


