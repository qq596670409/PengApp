package me.peng.pengapp.diary.utils;

import java.util.Calendar;

/**
 * 获取当前的日期，格式为： 2017年 8月 8日
 * Created by Administrator on 2017/8/8.
 */

public class GetDate {
    public static StringBuilder getDate(){
        StringBuilder stringBuilder = new StringBuilder();
        Calendar now = Calendar.getInstance();
        stringBuilder.append(now.get(Calendar.YEAR) + "年 ");
        stringBuilder.append((int)(now.get(Calendar.MONTH) + 1) + "月 ");
        stringBuilder.append(now.get(Calendar.DAY_OF_MONTH) + "日 ");
        return stringBuilder;
    }
}
