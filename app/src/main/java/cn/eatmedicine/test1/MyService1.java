package cn.eatmedicine.test1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService1 extends Service {
    public MyService1() {
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent
            }
        }).start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
