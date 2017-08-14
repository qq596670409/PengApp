package me.peng.pengapp.common.view;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Administrator on 2017/8/6.
 */

public class CommonTabBean implements CustomTabEntity{

    private int selectedIcon;
    private int unselectedIcon;
    private String title;

    public CommonTabBean(String title){
        this.title = title;
    }

    public CommonTabBean(String title, int selectedIcon, int unselectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unselectedIcon = unselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedIcon;
    }
}


