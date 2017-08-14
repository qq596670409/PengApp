package me.peng.pengapp.meizi.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 妹子的实体类
 * Created by Administrator on 2017/8/13.
 */

public class MeiziBean {

    @SerializedName("_id")
    private String id;
    @SerializedName("url")
    private String imageUrl;
    @SerializedName("who")
    private String who;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
