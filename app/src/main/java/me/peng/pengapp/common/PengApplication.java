package me.peng.pengapp.common;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/8/3.
 */

public class PengApplication extends Application {

    private static PengApplication mContext;
    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        requestQueue = Volley.newRequestQueue(mContext);
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }

    public static Context getContext(){
        return mContext;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
}
