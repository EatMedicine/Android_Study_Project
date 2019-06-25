package cn.eatmedicine.test1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

import java.util.List;

public class Test7 extends Activity implements TencentLocationListener{

    private TencentLocationManager mLocationManager;
    MapView mapview = null;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test7);
        mapview = findViewById(R.id.test7_mapView);

        TencentMap map = mapview.getMap();
        float min = map.getMinZoomLevel();
        float max = map.getMaxZoomLevel();
        map.setMinZoomLevel(8);
        map.setMaxZoomLevel(8);
        map.setMinZoomLevel((int) min);
        map.setMaxZoomLevel((int) max);

        mLocationManager = TencentLocationManager.getInstance(this);
        int error = mLocationManager.requestLocationUpdates(TencentLocationRequest.create()
                .setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME).setInterval(500)
                .setAllowDirection(true), this);
        Log.i("TEST_LOCAL","Error:"+error);

    }

    @Override
    protected void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mapview.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        super.onStop();
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        Log.i("TEST_LOCAL","Change:)"+tencentLocation.getLongitude()+"|"+tencentLocation.getLatitude());
        TencentMap map = mapview.getMap();
        LatLng local = new LatLng(tencentLocation.getLatitude(),tencentLocation.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(local,15));
        map.addMarker(new MarkerOptions()
                .position(local)  //标注的位置
                .title("我的位置")     //标注的InfoWindow的标题
                .snippet("经度："+tencentLocation.getLongitude()+"纬度"+tencentLocation.getLatitude())); //标注的InfoWindow的内容])


    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        Log.i("TEST_LOCAL","Update");
    }
}
