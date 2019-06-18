package cn.eatmedicine.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Test6 extends AppCompatActivity {

    TcpThread myThread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test6);
        //添加连接的事件
        findViewById(R.id.test6_btnConnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView status = findViewById(R.id.test6_txtConnectStatus);
                EditText txt = findViewById(R.id.test6_editIP);
                String tmp = txt.getText().toString();
                if(tmp == null||tmp == "") {
                    Log.i("TEST6","NULL");
                    return;
                }
                String[] data = tmp.split(":");
                if(data.length<2) {
                    status.setText("IP端口输入错误，格式:xxx.xxx.xxx.xxx:xxx");
                    return;
                }
                int port = 0;
                try{
                    port = Integer.parseInt(data[1]);
                }catch (Exception e){
                    status.setText("IP端口输入错误，格式:xxx.xxx.xxx.xxx:xxx");
                    return;
                }
                myThread = new TcpThread(data[0],port,
                        (TextView) findViewById(R.id.test6_txtShow),(TextView)findViewById(R.id.test6_txtConnectStatus));
                myThread.start();
                //设置发送的
                findViewById(R.id.test6_btnSend).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText txt = findViewById(R.id.test6_editMsg);
                        String str = txt.getText().toString();
                        myThread.writeMsg(str);
                    }
                });
            }
        });
    }

}
