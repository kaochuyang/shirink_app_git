package tw.com.cct.ms2.shirink_app_git;

/**
 * Created by user on 2018/4/26.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 創建ServerSocket監聽的主類
 * 該類只負責接收客戶端的socket連接請求，每當客戶端
 * 連接到該serversocket之後，程序將對應socket加入到list
 * 並为該socket開一挑單獨的線程，負責socket的所有通信任務
 * @author Administrator
 *
 */
public class SimpleServer {

    //定義保存所有Socket的ArrayList
    public static ArrayList<Socket> socketList = new ArrayList<Socket>();

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(30000);
            while (true) {
                Socket s = ss.accept();
                socketList.add(s);
                new Thread(new ServerThead(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
