package tw.com.cct.ms2.shirink_app_git;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
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
import java.util.Iterator;

public class tcpClass {

    private static JSONObject  json_read;        //從java伺服器傳遞與接收資料的json
    private static Socket socket = null;
    private static BufferedReader reader = null;
    private static BufferedWriter writer = null;
    Button test;
    private static JSONObject jsonObject = new JSONObject();

    static V3_tc_data A = V3_tc_data.getV3_tc_data();
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

    public static void connect() {


        @SuppressLint("StaticFieldLeak") AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String line;
                try {
                    try {
                        socket = new Socket("192.168.1.90", 5000);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    try {
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {

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
                } catch (IOException | JSONException e) {
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

    static public void SocketDestroy()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
