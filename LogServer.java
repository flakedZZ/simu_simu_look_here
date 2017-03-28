
import java.io.IOException;
import java.io.SyncFailedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anyi on 2017/3/28.
 */
public class LogServer {
    public static String getFileName(){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        Date now =new Date();
        String ctime = formatter.format(now);


        System.out.println(ctime);
        return "log"+ctime+now.getSeconds()/10+".txt";
    }



    public static void main(String[] args) {
        System.out.println("·þÎñÆ÷Æô¶¯");
        LogFileSwitcher switcher=new LogFileSwitcher();
        Thread switchThread=new Thread(switcher);
        switchThread.start();


        try {
            ServerSocket serverSocket=new ServerSocket(8001,300);
            while(true){
                Socket client=serverSocket.accept();
                LogServerThread logServerThread=new LogServerThread(client);
                Thread thread=new Thread(logServerThread);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


