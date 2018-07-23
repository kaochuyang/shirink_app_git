package tw.com.cct.ms2.shirink_app_git;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
/*
public class MyService extends Service implements Runnable {

    private String WorkState;//null處理中 success處理成功 failure處理失敗
    private String currentAction;//跟tc_server確認現在第幾個訊息
    static private Socket socket;
    static PrintWriter writer;
    static BufferedReader reader;
    private JSONObject jsonread;
    V3_tc_data A = V3_tc_data.getV3_tc_data();
    private Binder binder;
    private Thread thread;

    private void setWorkState(String workState) {
        WorkState = workState;
    }

    public String getWorkState() {
        return WorkState;
    }

    public void setJsonContext(String messageName, String message) {
        setWorkState(null);
        JSONObject json = new JSONObject();
        try {
            json.put(messageName, message);
            currentAction = message;
            send(json);
        } catch (JSONException e) {
            setWorkState("failure");
            e.printStackTrace();
        }
    }

    private void dealUploadSuperviseTask(JSONObject json) {
        try {
            setWorkState(json.getString("result"));
        } catch (JSONException e) {
            e.printStackTrace();
            setWorkState("failure");
        }
    }

    public void closeConnection() {
        JSONObject json = new JSONObject();
        try {
            json.put("action", "exit");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void connectService() {
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("192.168.1.90", 5000);
            socket.connect(socketAddress, 30000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();
            setWorkState("Timeout");
        } catch (IOException e) {
            e.printStackTrace();
            setWorkState("ConnectFailure");
        }
    }

    private void send(JSONObject json) {
        if (!socket.isConnected()) {
            setWorkState("ServerNotFound");//這段不確定寫得對不對
            Log.d("Socket", "send: " + getWorkState());
        }
        if (socket == null) connectService();
        if (!InternetService.this.thread.isAlive()) {
            thread = new Thread(InternetService.this).start();
        }
        if (!socket.isConnected() || socket.isClosed())
        //isConnected()代表曾經連過、isClosed()才是代表現在是否關閉
//isConnected()==true 且isClosed()返回false時網路才是連接狀態
        {
            Log.d("Socket", "send: workStatus is not connected");
            //重連三次，成功就結束這邊循環
            for (int times = 1; times < 4 && getWorkState() == null; times++) {
                socket = null;
                connectService();
                if (socket.isConnected() && (!socket.isClosed())) {
                    Log.d("Socket", "send: workStatus is  connected ");
                    break;
                }
            }
            if (!socket.isConnected() || (socket.isClosed()))//重連三次還是失敗的處理
            {
                setWorkState("ConnectFailure");
            }
        }
        if (!socket.isOutputShutdown()) {
            try {
                writer.println(json.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Socket", "send: " + getWorkState());
                setWorkState("Failure");
            }
        } else {
            setWorkState("ConnectFailure");
            Log.d("Socket", "send: " + getWorkState());
        }
    }

    private void getMessage(String message) {
        try {
            JSONObject json = new JSONObject(message);
            String action = json.getString("action");
            if (!currentAction.equals(action)) {
            }
            if (action.equals("getCategory")) {
                dealUploadSuperviseTask(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            setWorkState("jsonFail");
        }


    }

    public class InterBinder extends Binder {
        public InternetService getService() {
            return InternetService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        binder = new InterBinder();
        thread = new Thread(InternetService.this);
        thread.start();
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();//connectService
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("bind", "onUnbind: ");
        return super.onUnbind(intent);
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(500);// 休眠0.5s
                if (socket != null && !socket.isClosed()) {// 如果socket没有被关闭
                    if (socket.isConnected()) {// 判断socket是否连接成功
                        if (!socket.isInputShutdown()) {
                            String content;
                            if ((content = reader.readLine()) != null) {
                                getMessage(content);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {

            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setWorkState("Failure");// 如果出现异常，提示网络连接出现问题。
            ex.printStackTrace();
        }
    }

}

*/