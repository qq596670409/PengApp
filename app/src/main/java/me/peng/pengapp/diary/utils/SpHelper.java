package me.peng.pengapp.diary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences的辅助类
 * Created by Administrator on 2017/8/12.
 */

public class SpHelper {
    private static final String SP_NAME = "sp_name";
    private static SpHelper mSpHelper;
    private Context mAppContext;
    private SharedPreferences mSharedPreferences;
    private String info;

    private SpHelper(Context context){
        mAppContext = context.getApplicationContext();
    }

    //获取SpHelper的实例
    public static SpHelper getInstance(Context context){
        if (mSpHelper == null){
            synchronized (SpHelper.class){
                if (mSpHelper == null){
                    mSpHelper = new SpHelper(context);
                }
            }
        }
        return mSpHelper;
    }

    private SharedPreferences getSharedPreferences(){
        if (mSharedPreferences == null){
            mSharedPreferences = mAppContext.getSharedPreferences(SP_NAME, Context.MODE_APPEND);
        }
        return mSharedPreferences;
    }

    public void setInfo(String info){
        this.info = info;
        getSharedPreferences().edit().putString("info", info).apply();
    }

    public String getInfo(){
        if (info.equals("") || info.length() == 0){
            info = getSharedPreferences().getString("info", "");
        }
        return info;
    }
}
