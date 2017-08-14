package me.peng.pengapp.meizi.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.peng.pengapp.meizi.bean.MeiziBean;

/**
 * Gson的处理类
 * Created by Administrator on 2017/8/13.
 */

public class GsonHelper {

    public static List<MeiziBean> getMeiziBean(String response){
        List<MeiziBean> meiziBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String meiziArrayStr = jsonObject.getString("results");
            Type meiziListType = new TypeToken<List<MeiziBean>>(){}
                    .getType();
            Gson gson = new Gson();
            meiziBeanList = gson.fromJson(meiziArrayStr, meiziListType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meiziBeanList;
    }
}
