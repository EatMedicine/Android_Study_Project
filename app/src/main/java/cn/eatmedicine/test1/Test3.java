package cn.eatmedicine.test1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Test3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        Intent serviceIntent = new Intent(this,MyService1.class);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyBoradcast.text");
        TextView textView = findViewById(R.id.test3_txt1);
        registerReceiver(new MyReceiver1(textView),intentFilter);

        bindService(serviceIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("Service","onServiceConnected--");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("Service","onServiceDisconnected--");
            }
        },BIND_AUTO_CREATE);
    }
}
