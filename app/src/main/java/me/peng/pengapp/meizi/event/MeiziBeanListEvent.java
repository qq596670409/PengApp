package me.peng.pengapp.meizi.event;

import java.util.List;

import me.peng.pengapp.meizi.bean.MeiziBean;

/**
 * 包含妹子信息的Event
 * Created by Administrator on 2017/8/13.
 */

public class MeiziBeanListEvent {
    private List<MeiziBean> mMeiziBeanList;
    public MeiziBeanListEvent(List<MeiziBean> meiziBeenList){
        mMeiziBeanList = meiziBeenList;
    }
    public List<MeiziBean> getMeiziBeanList(){
        return mMeiziBeanList;
    }
}
