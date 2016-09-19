package com.giao.Controller;
import android.app.Activity;
import android.content.Context;


/**
 * Created by Long on 9/16/2016.
 */
public class ItemManagementActivityController {
    Activity activity;
    Context context;
    public ItemManagementActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
    }
    //Control Views events
    public int backButton_onClick() {

        return 1;
    }
    public int itemListAutoTextView_onItemSelected() {

        return 1;
    }
    public int itemListAutoTextView_onNothingSelected() {

        return 1;
    }
    public int addItemListButton_onClick() {

        return 1;
    }
    public int addItemButton_onClick() {

        return 1;
    }
}
