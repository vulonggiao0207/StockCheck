package com.giao.stockcheck;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.giao.Controller.MainActivityController;
import com.giao.Dataconnection.DatabaseHelper;

public class MainActivity extends Activity {

    private Button itemManagementButton;
    private Button itemCheckButton;
    private Button historyButton;
    private Button exitButton;
    private MainActivityController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declare View Controls
        itemManagementButton=(Button)findViewById(R.id.itemManagementButton);
        itemCheckButton=(Button)findViewById(R.id.itemCheckButton);
        historyButton=(Button)findViewById(R.id.historyButton);
        exitButton=(Button)findViewById(R.id.exitButton);
        controller= new MainActivityController(this);
        //ViewControls Events
        CreateEvent();
        //Initial Data
        Init_Database();
        //
    }
    private void CreateEvent()
    {
        itemManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.itemManagementButton_onClick();
            }
        });
        itemCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.itemCheckButton_onClick();
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.historyButton_onClick();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.exitButton_onClick();
            }
        });
    }
    protected void Init_Database()
    {
        //Create database
        DatabaseHelper dbHelper= new DatabaseHelper(this.getBaseContext());
        dbHelper.getReadableDatabase();
        //Put data to database when install
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            //InitData initData = new InitData(this.getBaseContext());
            //initData.InitDatabase();
            //    InitDatabase(this.context);
            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }
}
