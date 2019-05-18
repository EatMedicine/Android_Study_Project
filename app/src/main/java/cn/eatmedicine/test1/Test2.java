package cn.eatmedicine.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 extends AppCompatActivity {

    private ListView listV;
    private String[] FoodName={"红烧肉","辣子鸡","宫保鸡丁"};
    private String[] FoodInfo={"好吃不腻","辣爽","酸甜"};
    private int[] Img={R.drawable.img1,R.drawable.img2,R.drawable.img3};

    private List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        listV = findViewById(R.id.list1);
        list = getList();
        SimpleAdapter sa = new SimpleAdapter(this,list,R.layout.list_item,
                new String[]{"name","img"},new int[]{R.id.item_name1,R.id.item_img1});
        listV.setAdapter(sa);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                HashMap<String,Object> data = (HashMap<String,Object>) listView.getItemAtPosition(position);
                String info = data.get("info").toString();
                Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<Map<String,Object>> getList(){
        List<Map<String,Object>> result = new ArrayList<>();
        for(int count=0;count<FoodName.length;count++){
            Map<String,Object> map = new HashMap<>();
            map.put("name",FoodName[count]);
            map.put("info",FoodInfo[count]);
            map.put("img",Img[count]);
            result.add(map);
        }
        return result;
    }

}
