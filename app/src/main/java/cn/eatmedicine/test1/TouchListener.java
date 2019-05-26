package cn.eatmedicine.test1;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class TouchListener implements View.OnTouchListener {

    public ImageView img;
    //无任何手势模式
    public static final int NONE = 0;
    //移动
    public static final int MOVE = 1;
    //缩放模式
    public static final int SCALE = 2;
    //初始
    private int mode = NONE;
    //未缩放前两指之间的距离
    private float startDistance = 0f;
    //用于记录开始时候的坐标位置
    private PointF startPoint = new PointF();
    //用于记录拖拉图片移动的坐标位置
    public Matrix matrix = new Matrix();
    //用于识别其余手势的监听器
    private GestureDetector gd;


    public TouchListener(ImageView iv){
        img = iv;
        gd = new GestureDetector(new GestureListener(this));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("DEBUGIMG","ONTOUCH");
        gd.onTouchEvent(event);
        switch (event.getAction()& MotionEvent.ACTION_MASK) {
            case (MotionEvent.ACTION_DOWN):

                //拿取图片当前的matrix
                matrix.set(img.getImageMatrix());
                //拿取按下点的坐标
                startPoint.set(event.getX(), event.getY());
                mode = MOVE;
                break;
            case (MotionEvent.ACTION_POINTER_DOWN):
                //拿取图片当前的matrix
                matrix.set(img.getImageMatrix());
                //拿取当前两指间的距离
                startDistance = distance(event);
                //两指同时按下时，进入缩放模式
                mode = SCALE;
                break;
            case (MotionEvent.ACTION_MOVE):
                if(mode == MOVE){
                    //拿取移动后的点的坐标，和之前点的坐标比较，计算在这次移动中，横纵坐标各移动了多少。
                    //然后将图像的matrix对象也移动这么多。
                    matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
                    //在一次移动结束后，将移动起始点的坐标更新
                    startPoint.set(event.getX(), event.getY());
                }
                //当此时为缩放模式时
                if (mode == SCALE) {
                    //拿取缩放后两指之间的距离
                    float distance = distance(event);
                    //和缩放前两指间的距离比较，计算出缩放比例
                    float scale = distance / startDistance;
                    //按照手势的缩放比例，将图片的matrix对象也缩放这么多
                    PointF midP = mid(event);
                    matrix.postScale(scale, scale,midP.x,midP.y);
                    //在一次缩放后，将初始的两指间距离设置为当前的两指间距离
                    startDistance = distance;
                }
                break;
            case (MotionEvent.ACTION_UP):
            case (MotionEvent.ACTION_POINTER_UP):
                //一只手指抬起或者两只手指抬起时，进入无任何手势模式
                mode = NONE;
                break;
        }
        //在一次移动后，将根据移动后更新的matrix对象设置到ImageView上，以更新图像的位置
        img.setImageMatrix(matrix);
        //将此次事件消费
        return true;
    }

    // 计算两个手指间的距离
    public float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        //使用勾股定理返回两点之间的距离
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    //计算两个手指间的中间点
    public PointF mid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }
}
