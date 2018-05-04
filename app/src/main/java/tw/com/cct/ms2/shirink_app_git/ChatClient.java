package tw.com.cct.ms2.shirink_app_git;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UTFDataFormatException;
import java.net.InetAddress;
import java.net.Socket;

import static android.content.ContentValues.TAG;

public class ChatClient {
    private Socket socket = null;

    public ChatClient(String host, int port) throws IOException, IOException {
        socket = new Socket(host, port);
    }
    public Socket getSocket()
    {

        return socket;

    }
    /**
     * 向服务器端发送消息
     *
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Log.d(TAG, "sendMsg: send MSG");
        writer.write(msg.replace("\n", "") + "\n");
        writer.flush();

    }
    public String getMsg()throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF8"));

//        reader.
//        Log.d(TAG, "getMsg: MSG report"+);

        Log.d("", "run: ##########lenght="+reader.readLine().length());
        return reader.readLine();

    }

    public void sendMsg(String msg,Socket socket) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Log.d("by button", "sendMsg: send MSG");
        writer.write(msg.replace("\n", "") + "\n");
        writer.flush();
    }



}
//
//作者：Goo_Yao
//        链接：https://www.jianshu.com/p/4fca571f8a74
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。