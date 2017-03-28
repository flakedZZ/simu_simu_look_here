import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Anyi on 2017/3/28.
 */
public class LogClient implements Runnable{
    String name=null;
    public LogClient(String name){
        this.name=name;
    }
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
        System.out.println("客户端启动");
        int counter=0;
        int MAXCOUNT=50;
        while(counter<MAXCOUNT){
            try(Socket socket=new Socket("127.0.0.1",8001);){
                DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("客户端"+name+"发送数据"+counter);
                outputStream.flush();
                System.out.println(counter);
                Thread.sleep(100);
                counter++;
            }catch (Exception e) {
                System.out.println("本次发送失败"+counter);
            }
        }
    }
}
