package cn.eatmedicine.test1;

import android.graphics.PointF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private TouchListener touchListener;
    public GestureListener(TouchListener touch){
        touchListener = touch;
    }

    @Override
    public boolean onDown(MotionEvent e) {
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
                            float distanceX, float distanceY) {
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
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i("DEBUGIMG","DoubleTap");
        //双击放大1.5倍
        touchListener.matrix.postScale(1.5f, 1.5f,e.getX(),e.getY());
        touchListener.img.setImageMatrix(touchListener.matrix);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }
}
