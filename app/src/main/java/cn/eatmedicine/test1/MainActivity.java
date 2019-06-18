package cn.eatmedicine.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    View layout1=null;
    View layout2=null;
    boolean first=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用了布局服务来保存两个页面的信息
        //不然切换页面后其监听器等信息会丢失
        LayoutInflater inflater = this.getLayoutInflater();
        layout1 = inflater.inflate(R.layout.activity_main,null);
        layout2 = inflater.inflate(R.layout.view2,null);
        setContentView(layout1);
        ((Button)findViewById(R.id.btn1)).setOnClickListener(new btnListener());
        findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(MainActivity.this,Test2.class);
                startActivity(i);
            }
        });
        findViewById(R.id.test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(MainActivity.this,Test3.class);
                startActivity(i);
            }
        });
        findViewById(R.id.test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(MainActivity.this,Test4.class);
                startActivity(i);
            }
        });
        findViewById(R.id.test5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(MainActivity.this,Test5.class);
                startActivity(i);
            }
        });
        findViewById(R.id.test6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(MainActivity.this,Test6.class);
                startActivity(i);
            }
        });
    }
    //用于按钮的监听器
    class btnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn1:GoView2();break;
                case R.id.btn2:GoView1();break;
                default:break;
            }
        }


    }
    //切换页面2
    public void GoView2(){
        setContentView(layout2);
        //如果是第一次切换界面2的话要在这里注册监听器
        if(first){
            ((Button)findViewById(R.id.btn2)).setOnClickListener(new btnListener());
            first = false;
        }
    }
    //切换页面1
    public void GoView1(){
        setContentView(layout1);
    }


}



