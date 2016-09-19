package com.giao.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.giao.stockcheck.HistoryActivity;
import com.giao.stockcheck.ItemCheckActivity;
import com.giao.stockcheck.ItemManagementActivity;

/**
 * Created by Long on 9/16/2016.
 */
public class MainActivityController {
    Activity activity;
    Context context;
    public MainActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
    }
    //Control Views events
    public int itemManagementButton_onClick() {
        Intent intent = new Intent(activity.getBaseContext(), ItemManagementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return 1;
    }
    public int itemCheckButton_onClick() {
        Intent intent = new Intent(activity.getBaseContext(), ItemCheckActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return 1;
    }
    public int historyButton_onClick() {
        Intent intent = new Intent(activity.getBaseContext(), HistoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return 1;
    }
    public int exitButton_onClick() {
        activity.onBackPressed();
        return 1;
    }
}
