package tw.com.cct.ms2.shirink_app_git;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/4/30.
 */

public class V3_connection_activity extends Base_activity {
    private Thread thread;                //執行緒
    private Socket clientSocket;        //客戶端的socket
    private BufferedWriter bw;            //取得網路輸出串流
    private BufferedReader br;            //取得網路輸入串流
    private String tmp;                    //做為接收時的緩存
    private JSONObject json_write, json_read;        //從java伺服器傳遞與接收資料的json


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_v3_message);
        Button send_m = findViewById(R.id.send_m);
        thread = new Thread(Connection);                //賦予執行緒工作
        thread.start();                    //讓執行緒開始執行
        Map map = new HashMap();
                 map.put("name", "Jacky");
                 map.put("age", 30);
                 map.put("gender", true);
                 final JSONObject jsonObjectJacky = new JSONObject(map);
        final EditText textA = findViewById(R.id.et_show);
        //  launchServer();

/**/
        send_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        launchClient(textA, textA.getText().toString());
                //  textA.clearAnimation();
                Log.d("2", "run: client");
                try {
                    bw.write(jsonObjectJacky.toString());
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Runtime.getRuntime().gc();
                try {
                    Log.d("", "run: ##########lenght="+br.readLine().length()+"*"+br.readLine()+"*");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                textA.setText("");


//System.gc();

            }
        });
    }

    private Runnable Connection = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                // IP為Server端
                InetAddress serverIp = InetAddress.getByName("192.168.2.1");
                int serverPort = 5000;
                clientSocket = new Socket(serverIp, serverPort);
                //取得網路輸出串流
                bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                // 取得網路輸入串流
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // 當連線後
                while (clientSocket.isConnected()) {
                    // 取得網路訊息
                    tmp = br.readLine();    //宣告一個緩衝,從br串流讀取值
                    // 如果不是空訊息
                    if (tmp != null) {
                        //將取到的String抓取{}範圍資料
                        tmp = tmp.substring(tmp.indexOf("{"), tmp.lastIndexOf("}") + 1);

                        json_read = new JSONObject(tmp);
                        //從java伺服器取得值後做拆解,可使用switch做不同動作的處理
                        C=json_read.toString();

                    }
                }
            } catch (Exception e) {
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text", "Socket連線=" + e.toString());
                finish();    //當斷線時自動關閉房間
            }
        }
    };

    /**
     * 启动客户端
     */
    String C;

    private void launchClient(final EditText B, final String A) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ChatClient client = null;
                try {
                    JSONObject json_read, json_write;
                    client = new ChatClient("192.168.2.1", ChatServer.PORT);
                    client.sendMsg(A);

                    C = client.getMsg();

                    //      C=C.substring(C.indexOf("{"), C.lastIndexOf("}") + 1);
                    //                  json_read=new JSONObject(C);
//System.out.print(json_read.toString());
//System.out.print(C);

                    Log.d("", "run: ##########");
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();
    }


    @Override
    protected void onDestroy() {            //當銷毀該app時
        super.onDestroy();
        try {
            json_write = new JSONObject();
            json_write.put("action", "離線");    //傳送離線動作給伺服器
            Log.i("text", "onDestroy()=" + json_write + "\n");
            //寫入後送出
            bw.write(json_write + "\n");
            bw.flush();
            //關閉輸出入串流後,關閉Socket
            //最近在小作品有發現close()這3個時,導致while (clientSocket.isConnected())這個迴圈內的區域錯誤
            //會跳出java.net.SocketException:Socket is closed錯誤,讓catch內的處理再重複執行,如有同樣問題的可以將下面這3行註解掉
//            bw.close();
//            br.close();
//            clientSocket.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("text", "onDestroy()=" + e.toString());
        }
    }
//    作者：Goo_Yao
//    链接：https://www.jianshu.com/p/4fca571f8a74
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}