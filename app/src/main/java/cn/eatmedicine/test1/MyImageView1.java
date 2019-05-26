package cn.eatmedicine.test1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView1 extends android.support.v7.widget.AppCompatImageView {

    public GestureDetector gd;
    public MyImageView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        gd = new GestureDetector(new GestureListener((ImageView) findViewById(R.id.test4_img1)));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.i("DEBUGIMG","onTouch");
        return gd.onTouchEvent(event);
    }
}
