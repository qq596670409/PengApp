package me.peng.pengapp.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Created by Administrator on 2017/8/5.
 */

public class AppManager {
    private static Stack<AppCompatActivity> activityStack;
    private static AppManager instance;

    private AppManager(){

    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager(){
        if (instance == null){
            synchronized (AppManager.class){
                if (instance == null){
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity(){
        if (activityStack == null || activityStack.isEmpty()){
            return null;
        }
        AppCompatActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){
        AppCompatActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity){
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 找到指定类名的Activity
     */
    public AppCompatActivity findActivity(Class<?> cls){
        AppCompatActivity activity = null;
        for (AppCompatActivity appCompatActivity : activityStack){
            if (appCompatActivity.getClass().equals(cls)){
                activity = appCompatActivity;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){
        for (AppCompatActivity activity : activityStack){
            if (activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context){
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
