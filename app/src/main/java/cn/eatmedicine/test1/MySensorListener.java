package cn.eatmedicine.test1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MySensorListener implements SensorEventListener {

    public enum FangXiang{
        UP,DOWN,LEFT,RIGHT,POSITIVE,NEGATIVE
    }
    private Context context = null;
    private SensorManager sm;
    private Sensor sensor1;
    private Sensor sensor2;

    private float sx,sy,sz;
    private FangXiang fangXiang = FangXiang.POSITIVE;

    private TextView textView1;
    private TextView textView2;
    private ImageView imageView;

    public MySensorListener(Context c, TextView dir,TextView light, ImageView image){
        context = c;
        this.textView1 = dir;
        this.textView2 = light;
        this.imageView = image;
    }

    public void InitSensor(){
        if(context == null ) {
            Log.i("TEST5", "context NULL");
            return;
        }
        sm =(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        sensor1 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor2 = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sm == null){
            Log.i("TEST5","SensorManager NULL");
            return;
        }
        sm.registerListener(this,sensor1,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void disableSensor(){
        if(sm != null){
            sm.unregisterListener(this);
            sm = null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == null){
            Log.i("TEST5","Event Sensor NULL");
            return;
        }

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            sx = event.values[SensorManager.DATA_X];
            sy = event.values[SensorManager.DATA_Y];
            sz = event.values[SensorManager.DATA_Z];
            Log.i("TEST5","Sensor X: "+sx);
            Log.i("TEST5","Sensor Y: "+sy);
            Log.i("TEST5","Sensor Z: "+sz);
            //正面朝上
            if(8<sz&&sz<11){
                fangXiang = FangXiang.POSITIVE;
            }
            //反面朝上
            if(-11<sz&&sz<-8){
                fangXiang = FangXiang.NEGATIVE;
            }
            //右侧朝上
            if(8<sx&&sx<11){
                fangXiang = FangXiang.RIGHT;
            }
            //左侧朝上
            if(-11<sx&&sx<-8){
                fangXiang = FangXiang.LEFT;
            }
            //竖直朝上
            if(8<sy&&sy<11){
                fangXiang = FangXiang.UP;
            }
            //底部朝上
            if(-11<sy&&sy<-8){
                fangXiang = FangXiang.DOWN;
            }
            Log.i("TEST5_1","Sensor DIR: "+fangXiang);

            switch (fangXiang){
                case UP:textView1.setText("竖直向上");imageView.setRotation(0);break;
                case DOWN: textView1.setText("底部向上");imageView.setRotation(180);break;
                case LEFT: textView1.setText("左侧向上");imageView.setRotation(-90);break;
                case RIGHT: textView1.setText("右侧向上");imageView.setRotation(90);break;
                case POSITIVE: textView1.setText("正面向上");break;
                case NEGATIVE: textView1.setText("反面向上");break;
                default:break;
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            float tmp = event.values[0];
            textView2.setText("光线强度："+tmp);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
