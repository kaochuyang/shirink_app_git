package tw.com.cct.ms2.shirink_app_git;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/4/30.
 */

public class V3_connection_activity extends Base_activity {
    private Thread thread;                //執行緒
    private String tmp;                    //做為接收時的緩存
    private JSONObject json_write, json_read;        //從java伺服器傳遞與接收資料的json
    // private OnMessageReceived mMessageListner = null;
    Socket socket;
    BufferedReader in;
    BufferedWriter out;
    Send_msg send_msg;
    ResultReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_v3_message);
        final Button send_m = findViewById(R.id.send_m);
//        thread = new Thread(Connection);                //賦予執行緒工作
        //     thread.start();                    //讓執行緒開始執行


        Map map = new HashMap();
        map.put("name", "Jacky");
        map.put("age", 30);
        map.put("gender", true);
        final JSONObject jsonObjectJacky = new JSONObject(map);
        final EditText textA = findViewById(R.id.et_show);
        final TextView textB=findViewById(R.id.state_v3);
        //  launchServer();
        try {
            socket=new Socket("192.168.2.1",5000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**/
        send_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            send_msg.execute(textA.getText().toString());
            textA.setText("");
            receiver.execute();
            textB.setText(tmp);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            Socket_disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private class SocketAction extends AsyncTask<String, Void, String> {
//
//
//        protected void onDestroy() {            //當銷毀該app時
//
//            try {
//                json_write = new JSONObject();
//                json_write.put("action", "離線");    //傳送離線動作給伺服器
//                Log.i("text", "onDestroy()=" + json_write + "\n");
//                //寫入後送出
//                bw.write(json_write + "\n");
//                bw.flush();
//                //關閉輸出入串流後,關閉Socket
//                //最近在小作品有發現close()這3個時,導致while (clientSocket.isConnected())這個迴圈內的區域錯誤
//                //會跳出java.net.SocketException:Socket is closed錯誤,讓catch內的處理再重複執行,如有同樣問題的可以將下面這3行註解掉
//                bw.close();
//                in.close();
//                clientSocket.close();
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                Log.e("text", "onDestroy()=" + e.toString());
//            }
//        }
//
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//
//            int serverPort = 5000;
//            try {
//                clientSocket = new Socket("192.168.2.1", serverPort);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            //取得網路輸出串流
//            try {
//                bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            // 取得網路輸入串流
//            try {
//                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            // 當連線後
//            while (clientSocket.isConnected()) {
//
//
//                // 取得網路訊息
//                try {
//                    tmp = in.readLine();    //宣告一個緩衝,從br串流讀取值
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }






        public void Socket_disconnect() throws IOException {
            out.flush();
            out.close();
            in.close();
            socket.close();
        }

        public class Socket_connect extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... strings) {


                return null;
            }


        }


        public class Send_msg extends AsyncTask<String, Void, Void> {
            @Override
            protected Void doInBackground(String... strings) {

                try {
                    out.write(strings + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

        public class ResultReceiver extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... strings) {

                while (socket.isConnected()) {
                    try {
                        tmp=in.readLine();
                        return tmp;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                return null;
            }
        }


    }



//        public void run() {
//            // TODO Auto-generated method stub
//            try {
//                // IP為Server端
//                InetAddress serverIp = InetAddress.getByName("192.168.2.1");
//                int serverPort = 5000;
//                clientSocket = new Socket(serverIp, serverPort);
//                //取得網路輸出串流
//                bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//                // 取得網路輸入串流
//                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                // 當連線後
//                while (clientSocket.isConnected()) {
//
//
//                    // 取得網路訊息
//                    tmp = br.readLine();    //宣告一個緩衝,從br串流讀取值
//
//                    if (serverMessage != null && mMessageListner != null) {
//                        mMessageListner.messageReceived(serverMessage);
//
//                    }
//                    serverMessage = null;
//
////                    // 如果不是空訊息
////                    if (tmp != null) {
////                        //將取到的String抓取{}範圍資料
////                        tmp = tmp.substring(tmp.indexOf("{"), tmp.lastIndexOf("}") + 1);
////
////                        json_read = new JSONObject(tmp);
////                        //從java伺服器取得值後做拆解,可使用switch做不同動作的處理
////                        C=json_read.toString();
////
////                    }
//                }
//            } catch (Exception e) {
//                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
//                e.printStackTrace();
//                Log.e("text", "Socket連線=" + e.toString());
//                //  finish();    //當斷線時自動關閉房間
//            }
//        }
//    };
//
//
//    /**
//     * 启动客户端
//     */
//    String C;
//
////
//
//    public interface OnMessageReceived {
//        public void messageReceived(String message);
//    }
//
//    @Override
//    protected void onDestroy() {            //當銷毀該app時
//        super.onDestroy();
//        try {
//            json_write = new JSONObject();
//            json_write.put("action", "離線");    //傳送離線動作給伺服器
//            Log.i("text", "onDestroy()=" + json_write + "\n");
//            //寫入後送出
//            bw.write(json_write + "\n");
//            bw.flush();
//            //關閉輸出入串流後,關閉Socket
//            //最近在小作品有發現close()這3個時,導致while (clientSocket.isConnected())這個迴圈內的區域錯誤
//            //會跳出java.net.SocketException:Socket is closed錯誤,讓catch內的處理再重複執行,如有同樣問題的可以將下面這3行註解掉
//            bw.close();
//            br.close();
//            clientSocket.close();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            Log.e("text", "onDestroy()=" + e.toString());
//        }
//    }
////    作者：Goo_Yao
////    链接：https://www.jianshu.com/p/4fca571f8a74
////    來源：简书
////    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//


