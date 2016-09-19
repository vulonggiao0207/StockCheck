package com.giao.Controller;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemCheckActivityController {
    Activity activity;
    Context context;
    public ItemCheckActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
    }
    //Control Views events
    public int backButton_onClick()
    {
        return 1;
    }
    public int saveButton_onClick()
    {
        return 1;
    }
    public int itemListSpinner_onItemSelected()
    {
        return 1;
    }
    public int itemListSpinner_onNothingSelected()
    {
        return 1;
    }
}
