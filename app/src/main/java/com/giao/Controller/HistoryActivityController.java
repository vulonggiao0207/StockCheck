package com.giao.Controller;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Long on 9/16/2016.
 */
public class HistoryActivityController {
    Activity activity;
    Context context;
    public HistoryActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
    }
    //Control Views events
    public int backButton_onClick()
    {
        return 1;
    }
    public int sendButton_onClick()
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
    public int dateSpinner_onItemSelected()
    {
        return 1;
    }
    public int dateSpinner_onNothingSelected()
    {
        return 1;
    }
}
