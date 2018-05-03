package tw.com.cct.ms2.shirink_app_git;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by user on 2018/4/30.
 */

public class V3_connection_activity extends Base_activity {

    Handler handler = new Handler() {


        @Override
        public void publish(LogRecord logRecord) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }


        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ChatServer.SERVER_TAG:
                    Bundle bundle = msg.getData();
                    Toast.makeText(V3_connection_activity.this, bundle.getString(ChatServer.MSG_KEY), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_v3_message);
        Button send_m=findViewById(R.id.send_m);



        launchServer();


        send_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchClient();

                Runtime.getRuntime().gc();
//System.gc();

            }
        });
    }

    private void launchServer() {
        //启动服务器端
        try {
            ChatServer
                    chatServer = new ChatServer(handler);

            Log.d("1", "launchServer:server start ");
            chatServer.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动客户端
     */
    private void launchClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChatClient client = null;
                try {
                    client = new ChatClient("192.168.2.1", ChatServer.PORT);
                    Log.d("2", "run: client");

                    client.sendMsg("123");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
//
//    作者：Goo_Yao
//    链接：https://www.jianshu.com/p/4fca571f8a74
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}