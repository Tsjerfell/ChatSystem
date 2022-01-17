package Interface;

import databases.DBConv;

public class ThreadDisplayHistory implements Runnable{

    String IPuser1;
    String IPuser2;

    public ThreadDisplayHistory(String usr1, String usr2){
        this.IPuser1=usr1;
        this.IPuser2=usr2;
    }

    public void run() {
        DBConv DB1=new DBConv();
        Visuel.textHistory.setText(DB1.getConv(IPuser1, IPuser2).toString());
    }
}