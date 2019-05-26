package cn.eatmedicine.test1;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    public ImageView iv;
    public GestureListener(ImageView img){
        iv = img;
    }
    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("DEBUGIMG","DOWN");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY){
        Log.i("DEBUGIMG",distanceX+"|"+distanceY);
        iv.setX(iv.getX()-distanceX);
        iv.setY(iv.getY()-distanceY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e){
        return true;
    }

}
