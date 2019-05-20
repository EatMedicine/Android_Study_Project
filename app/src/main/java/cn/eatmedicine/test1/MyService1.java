package cn.eatmedicine.test1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService1 extends Service {
    public MyService1() {
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){

        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                    String str = "Random Num: " +Math.random()*100;
                    Intent intent = new Intent("MyBoradcast.text");
                    intent.putExtra("text",str);
                    sendBroadcast(intent);
                    try{
                        Thread.sleep(2000);
                    }
                    catch (Exception e){

                    }

                }

            }
        }).start();
        return null;
    }
}
