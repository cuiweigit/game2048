package com.cw.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * @author cuiwei
 */
public class SuperActivity extends Activity {
    private static final String _SP_KEY = "spkey";
    public static final String _SCORE_KEY = "score_key";
    public static final String _BEST_KEY = "best_key";
    public static final String _DATA_KEY = "data_key";
    public static final String _FLAG_NEW = "new";
    public static final String _FLAG_RESUME ="resume";
    public static final String _INTENT_FLAG_KEY = "flag";
    protected SharedPreferences sp;
    protected  SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences(_SP_KEY, getApplicationContext().MODE_WORLD_READABLE);
        editor = sp.edit();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

}
