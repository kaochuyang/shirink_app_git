package tw.com.cct.ms2.shirink_app_git;

import android.annotation.SuppressLint;
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
    private JSONObject  json_read;        //從java伺服器傳遞與接收資料的json
    static Socket socket = null;
    static BufferedReader reader = null;
    static BufferedWriter writer = null;
    Button test;
    Handler handler = new Handler();
    JSONObject jsonObject = new JSONObject();

    V3_tc_data A = V3_tc_data.getV3_tc_data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_v3_message);
        Button send_m = findViewById(R.id.send_m);
        test = findViewById(R.id.test);

        final EditText textA = (EditText) findViewById(R.id.et_show);
        final TextView textB = findViewById(R.id.state_v3);
        EditText textC = findViewById(R.id.edit);

        final JSONObject tes = new JSONObject();

        send_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textB.append("手機說:" + textA.getText().toString() + "\n");
//                send(textA.getText().toString());

                try {
                    tes.put("test", 123);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject trans_obj;
                if (textA.getText().toString().equals("0")) send("hello");
                if (textA.getText().toString().equals("1")) {
                    send(tes.toString());
                }


                try {
                    if (textA.getText().toString().equals("2")) {
                        trans_obj = new JSONObject();
//                            A.getV3_json_data().getJSONArray("plancontext").get(0)
                        trans_obj.put("plancontext", A.getV3_json_data().getJSONArray("plancontext").get(0));
                        Log.d("plancontext", "onClick: " + trans_obj.toString());
                        send(trans_obj.toString());
                        trans_obj = null;
                    }
                    if (textA.getText().toString().equals("3")) {
                        trans_obj = new JSONObject();
                        trans_obj.put("weekdaysegment", A.getV3_json_data().getJSONArray("weekdaysegment"));
                        send(trans_obj.toString());
                        Log.d("weekdaysegment", "onClick: " + trans_obj.toString());
                        trans_obj = null;
                    }

                    if (textA.getText().toString().equals("4")) {
                        trans_obj = new JSONObject();
                        trans_obj.put("specialdaycontext", A.getV3_json_data().getJSONArray("specialdaycontext"));
                        send(trans_obj.toString());
                        Log.d("specialdaycontext", "onClick: " + trans_obj.toString());
                        trans_obj = null;
                    }
                    if (textA.getText().toString().equals("5")) {
                        trans_obj = new JSONObject();
                        trans_obj.put("plancontext", A.getV3_json_data().getJSONArray("plancontext").getJSONObject(0));
                        send(trans_obj.toString());
                        Log.d("plancontext", "onClick: " + trans_obj.toString());
                        trans_obj = null;
                    }
                    if (textA.getText().toString().equals("6")) {
                        trans_obj = new JSONObject();
                        trans_obj.put("step", A.getV3_json_data().getJSONArray("step").getJSONObject(128));
                        send(trans_obj.toString());
                        Log.d("step", "onClick: " + trans_obj.toString());
                        trans_obj = null;
                    }
                    if (textA.getText().toString().equals("7")) {
                        trans_obj = new JSONObject();
                        trans_obj.put("segmentinfo", A.getV3_json_data().getJSONObject("segmentinfo").getJSONArray("segcontext").getJSONObject(3));
                        send(trans_obj.toString());
                        Log.d("segmentinfo", "onClick: " + trans_obj.toString());
                        trans_obj = null;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
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
                        json_read = new JSONObject(line);
                        jsonObject = merge(jsonObject, json_read);
                        JSONArray jsonArray;
                        // jsonArray=json_read.getJSONArray("weekdaysegment");
                        Log.d("JSON", "doInBackground: WeekDayArray " + jsonObject.toString());
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
                textB.append("server說:" + values[0] + "\n");
                //textB.append("server說:"+line+"\n");
                Toast.makeText(V3_connection_activity.this, "連接成功", Toast.LENGTH_SHORT).show();
                super.onProgressUpdate(values);
            }


        };
        read.execute();

    }

    static public void send(String A) {
        try {
            writer.write(A);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        SocketDestroy();
        super.onDestroy();


    }



    public void SocketDestroy()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject merge(JSONObject... jsonObjects) throws JSONException {//用來拼接多個json物件用的

        JSONObject jsonObject = new JSONObject();

        for (JSONObject temp : jsonObjects) {
            Iterator<String> keys = temp.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                jsonObject.put(key, temp.get(key));
            }

        }
        return jsonObject;
    }

}


