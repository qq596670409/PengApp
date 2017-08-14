package me.peng.pengapp.duanzi.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 段子的实体类
 * Created by Administrator on 2017/8/12.
 */

public class DuanziBean {

    @SerializedName("group")
    private GroupBean groupBean;
    private String type;

    public GroupBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(GroupBean groupBean) {
        this.groupBean = groupBean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
