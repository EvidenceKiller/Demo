package com.user.test;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tv_Unit;
    private TextView tv_Current;
    private TextView tv_City;
    private TextView tv_Daily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_Unit = (TextView) findViewById(R.id.tv_unit);
        tv_Current = (TextView) findViewById(R.id.tv_current);
        tv_City = (TextView) findViewById(R.id.tv_city);
        tv_Daily = (TextView) findViewById(R.id.tv_daily);
        showUnitInfo();
        showCurrentInfo();
        showCityInfo();
        showDailyInfo();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void showUnitInfo() {
        Uri uri = Uri.parse("content://com.tct.provider.weatherinfo/unitinfo");
        String temp[] = new String[] {"isUnitC"};
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(uri, temp, null, null, null);
        Log.i("MainActivity", cursor.toString());
//        int id = cursor.getColumnIndex("id");
        int unit = cursor.getColumnIndex(temp[0]);
        StringBuffer sb = new StringBuffer();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            String strId = cursor.getString(id);
            int isUnitC = cursor.getInt(unit);
//            StringBuffer sb = new StringBuffer(id);
//            sb.append("id :").append(strId);
            sb.append("isUnitC :").append(isUnitC);
            sb.append("\n");
            Log.i("test", sb.toString());
        }
        tv_Unit.setText(sb.toString());
        cursor.close();
    }

    private void showCurrentInfo() {
        Uri uri = Uri.parse("content://com.tct.provider.weatherinfo/current");
        String current[] = new String[] {"locationKey", "text", "temp", "isdaytime"};
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(uri, current, null, null, null);
        Log.i("MainActivity", cursor.toString());
        int locationKey = cursor.getColumnIndex("locationKey");
        int cityName = cursor.getColumnIndex("text");
        int updateTime = cursor.getColumnIndex("temp");
        int isdaytime = cursor.getColumnIndex("isdaytime");
        Log.i(TAG, "isdaytime index : " + isdaytime);
        StringBuffer sb = new StringBuffer();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String id = cursor.getString(0);
            String location = cursor.getString(locationKey);
            String city = cursor.getString(cityName);
            String update = cursor.getString(updateTime);
            int isDayTime = cursor.getInt(isdaytime);
            sb.append("location :").append(location);
            sb.append(", text :").append(city);
            sb.append(", temp :").append(update);
            sb.append(", isDayTime :").append(isDayTime);
            sb.append("\n");
        }
        tv_Current.setText(sb.toString());
        cursor.close();
    }


    private void showCityInfo() {
        Uri uri = Uri.parse("content://com.tct.provider.weatherinfo/city");
        String current[] = new String[] {"locationKey", "cityName", "updateTime", "state"};
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(uri, current, null, null, null);
        Log.i("MainActivity", cursor.toString());
        int locationKey = cursor.getColumnIndex("locationKey");
        int cityName = cursor.getColumnIndex("cityName");
        int updateTime = cursor.getColumnIndex("updateTime");
        int state = cursor.getColumnIndex("state");
        StringBuffer sb = new StringBuffer();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String id = cursor.getString(0);
            String location = cursor.getString(locationKey);
            String city = cursor.getString(cityName);
            String update = cursor.getString(updateTime);
            String s = cursor.getString(state);
            sb.append("location :").append(location);
            sb.append("city :").append(city);
            sb.append("update :").append(update);
            sb.append("state :").append(s);
            sb.append("\n");
        }
        tv_City.setText(sb.toString());
        cursor.close();
    }

    private void showDailyInfo() {
        Uri uri = Uri.parse("content://com.tct.provider.weatherinfo/daily");
        String current[] = new String[] {"locationKey", "week", "date", "phrase"};
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(uri, current, null, null, null);
        Log.i("MainActivity", cursor.toString());
        int locationKey = cursor.getColumnIndex("locationKey");
        int cityName = cursor.getColumnIndex("week");
        int updateTime = cursor.getColumnIndex("date");
        int state = cursor.getColumnIndex("phrase");
        StringBuffer sb = new StringBuffer();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String id = cursor.getString(0);
            String location = cursor.getString(locationKey);
            String city = cursor.getString(cityName);
            String update = cursor.getString(updateTime);
            String s = cursor.getString(state);
            sb.append("location :").append(location);
            sb.append("week :").append(city);
            sb.append("date :").append(update);
            sb.append("phrase :").append(s);
            sb.append("\n");
        }
        tv_Daily.setText(sb.toString());
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
