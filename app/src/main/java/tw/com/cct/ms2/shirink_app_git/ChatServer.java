package tw.com.cct.ms2.shirink_app_git;

import android.os.Bundle;
import android.os.Message;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Handler;

public class ChatServer extends Thread {
    /**
     * 服务器Socket对象
     */
    private ServerSocket server = null;

    /**
     * 端口
     */
    public static final int PORT = 5000;

    /**
     * 读写Buffer
     */
    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * 主线程Handler
     */
    private final Handler handler;

    /**
     * 标识
     */
    public static final int SERVER_TAG = 12345;
    public static final String MSG_KEY = "server";

    public ChatServer(Handler handler) throws IOException {
        this.handler = handler;
        createSocket();
    }

    /**
     * 创建ServerSocket
     */
    private void createSocket() throws IOException {
        server = new ServerSocket(PORT, 100);
    }

    @Override
    public void run() {
        Socket client;
        String text;
        try {
            //死循环监听
            while (true) {
                //响应客户端连接请求
                client = responseSocket();
                while (true) {
                    //接收客户端发送的消息
                    text = receiveMsg(client);
                    //显示消息结果
                    makeTips(text);
                    break;
                }
                closeSocket(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接及缓存
     *
     * @param client
     */
    private void closeSocket(Socket client) throws IOException {
        reader.close();
//        writer.close();
        client.close();
    }

    /**
     * 发送消息到客户端
     *
     * @param client
     * @param text
     */
    private void sendMsg(Socket client, String text) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write(text + "\n");
        writer.flush();//发送
    }

    /**
     * 提示
     *
     * @param text
     */
    private void makeTips(String text) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(ChatServer.MSG_KEY, text);
        msg.setData(bundle);
        msg.what = SERVER_TAG;

    }

    private String receiveMsg(Socket client) throws IOException {
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String result = reader.readLine();
        return "服务器收到:" + result;
    }

    private Socket responseSocket() throws IOException {
        return server.accept();
    }
}
//
//作者：Goo_Yao
//        链接：https://www.jianshu.com/p/4fca571f8a74
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。