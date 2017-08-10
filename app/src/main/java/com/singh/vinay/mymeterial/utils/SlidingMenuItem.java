package com.singh.vinay.mymeterial.utils;

/**
 * Created by proloy on 1/7/16.
 */
public class SlidingMenuItem {

    private int imageResource;
    private String menuItemName;

    public SlidingMenuItem(String name) {
        this.menuItemName = name;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }
}
