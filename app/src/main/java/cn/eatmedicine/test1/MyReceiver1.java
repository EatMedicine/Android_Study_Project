package cn.eatmedicine.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyReceiver1 extends BroadcastReceiver {
    private TextView text;
    public MyReceiver1(TextView text){
        this.text = text;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if("MyBoradcast.text".equals(action)){
            String content = intent.getStringExtra("text");
            text.setText(content);
        }
    }
}
