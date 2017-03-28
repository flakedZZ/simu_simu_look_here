/**
 * Created by Anyi on 2017/3/28.
 */
public class ClientManager {
    public static void main(String[] args) {
        for(int i=0;i<50;i++){
            new Thread(new LogClient("client"+i+"xx")).start();
            System.out.println("µÚ"+i+"ÂÖ");
        }


    }}
