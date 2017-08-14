package me.peng.pengapp.duanzi.bean;

/**
 * Created by Administrator on 2017/8/12.
 */

public class GroupBean {

    private String text;
    private long id;
    private UserBean user;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean{
        private long user_id;
        private String name;
        private int followings;
        private boolean user_verified;
        private int ugc_count;
        private String avatar_url;
        private int followers;
        private boolean is_following;
        private boolean is_pro_user;

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFollowings() {
            return followings;
        }

        public void setFollowings(int followings) {
            this.followings = followings;
        }

        public boolean isUser_verified() {
            return user_verified;
        }

        public void setUser_verified(boolean user_verified) {
            this.user_verified = user_verified;
        }

        public int getUgc_count() {
            return ugc_count;
        }

        public void setUgc_count(int ugc_count) {
            this.ugc_count = ugc_count;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public boolean is_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public boolean is_pro_user() {
            return is_pro_user;
        }

        public void setIs_pro_user(boolean is_pro_user) {
            this.is_pro_user = is_pro_user;
        }
    }

}
