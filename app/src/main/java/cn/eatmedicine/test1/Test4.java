package cn.eatmedicine.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Test4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        ImageView img = findViewById(R.id.test4_img1);
        img.setOnTouchListener(new TouchListener(img));
    }
}
