package tw.com.cct.ms2.shirink_app_git;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

public class V3MessageActivity extends Activity {

    private EditText et_show,et_input;
    private Button bt_send;
    private OutputStream os;
    private Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v3_message);

        et_input = (EditText) this.findViewById(R.id.et_input);
        et_show = (EditText) this.findViewById(R.id.et_show);
        bt_send = (Button) this.findViewById(R.id.bt_send);


        try {
            //定義客戶連接的socket
            Socket socket = new Socket("192.168.1.101",7002);
            //启動客戶端監聽線程
            new Thread(new ClinetThread(socket,handler)).start();
            os = socket.getOutputStream();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //得到輸入框中的內容，寫入到輸入流中
                    os.write((et_input.getText().toString()+"\r\n").getBytes("utf-8"));
                    et_input.setText("");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1){
                    //得到服務器返回的信息
                    et_show.append("\n"+msg.obj.toString());
                }
            }
        };
    }

}