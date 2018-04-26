package tw.com.cct.ms2.shirink_app_git;

/**
 * Created by user on 2018/4/26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 負責每個線程通信的類
 * 該類不斷讀取客戶端數據，使用自定義的readFromClient()方法讀取
 * 客戶端數據，如果出現異常表明該socket對應的客戶端socket出現了問題
 * 程序將該socket從list中移除。
 * 當服務器線程讀取到了客戶端數據後，遍曆list集合，並將數據發送到每個
 * socket中
 * @author Administrator
 *
 */
public class ServerThead implements Runnable {

    //定義當前線程處理的socket
    Socket s = null;
    //該線程所處理的socket對應的輸入流
    BufferedReader br = null;

    public ServerThead(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        String conntent = null;
        while((conntent=readFromClient())!=null){
            //遍曆socket中的每一個socket
            for(Socket s:SimpleServer.socketList){
                try {
                    OutputStream os = s.getOutputStream();
                    os.write((conntent+"\n").getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private String readFromClient() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            SimpleServer.socketList.remove(s);
        }
        return null;
    }

}