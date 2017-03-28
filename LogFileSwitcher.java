import java.io.*;
import java.util.Date;

/**
 * Created by Anyi on 2017/3/28.
 */
public class LogFileSwitcher implements Runnable {
    public boolean needRun = true;
    static long LOG_SWITCH_TIME = 10000;
    static File file =null;
    static FileWriter writer = null;
    static long lastLogTime = 0;
    static String bufferLog="";

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

    public  static void createLog() {//����log�ļ�
        if (System.currentTimeMillis() - lastLogTime > LOG_SWITCH_TIME) {
            if(!bufferLog.equals("")){
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                    bufferedWriter.write(bufferLog);
                    bufferedWriter.flush();
                    bufferLog="";
                }catch (Exception e){

                }
            }

            file = new File(LogServer.getFileName());
            try (FileWriter writer = new FileWriter(file)) {
                writer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
            lastLogTime = System.currentTimeMillis();

        }
    }

    @Override
    public synchronized void run() {
        System.out.println("�ļ�switcher����");


        while (needRun) {

            createLog();
        }
    }



    public synchronized void writeLog(String log) {//д��log
        System.out.println("д������");
        bufferLog+=log+new Date().getTime()+"\r\n";


    }


}
