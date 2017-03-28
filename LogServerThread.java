import java.io.*;
import java.net.Socket;

/**
 * Created by Anyi on 2017/3/28.
 */
public class LogServerThread implements Runnable{
    private Socket socket;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            DataInputStream inputStream=new DataInputStream(socket.getInputStream());
            String clientInputStr=inputStream.readUTF();
            LogFileSwitcher switcher=new LogFileSwitcher();
            switcher.writeLog(clientInputStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LogServerThread(Socket client){
        socket=client;
    }



}
