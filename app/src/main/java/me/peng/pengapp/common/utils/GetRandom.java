package me.peng.pengapp.common.utils;

/**
 * 获取随机数的工具类
 * Created by Administrator on 2017/8/6.
 */

public class GetRandom {

    /**
     * 获取一个0 - 48之间的随机整数
     * @return
     */
    public static int getRandom(){
        double random = Math.random();
        int result = (int)(random * 50 - 20);
        return Math.abs(result);
    }
}
