package cn.eatmedicine.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Test5 extends AppCompatActivity {

    private MySensorListener mySensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);
        mySensorListener = new MySensorListener(this,(TextView) findViewById(R.id.test5_txt1),(TextView) findViewById(R.id.test5_txt2),(ImageView) findViewById(R.id.test5_img1));
    }
    @Override
    protected void onResume(){
        super.onResume();
        mySensorListener.InitSensor();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mySensorListener.disableSensor();
    }
}
