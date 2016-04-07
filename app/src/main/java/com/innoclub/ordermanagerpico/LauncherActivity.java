package com.innoclub.ordermanagerpico;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by presisco on 2016/4/6.
 */

/**
 * 应用的入口，点击应用图标后打开的第一个Activity，根据登录历史确定是否显示LoginActivity
 */

public class LauncherActivity extends AppCompatActivity {
    //获取是否登陆过
    boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        loggedIn=getSharedPreferences(ConstParams.MAIN_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                    .getBoolean(ConstParams.MAIN_SHARED_PREFERENCE_KEY_LOGGEDIN,false);

        new WasteofTime().execute();
    }

    private void close(){
        finish();
    }

    /**
     * 等待一段时间后根据登录状态进行操作
     */
    private class WasteofTime extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(loggedIn){
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));

            }else{
                startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
            }
            close();
        }
    }
}
