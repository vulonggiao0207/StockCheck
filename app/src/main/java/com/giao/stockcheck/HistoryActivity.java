package com.giao.stockcheck;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.giao.Controller.HistoryActivityController;

/**
 * Created by Long on 9/16/2016.
 */
public class HistoryActivity extends Activity {
    private Button backButton;
    private Button sendButton;
    private Spinner itemListSpinner;
    private Spinner dateSpinner;
    private ListView itemListView;
    public HistoryActivityController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        backButton=(Button)findViewById(R.id.backButton);
        sendButton=(Button)findViewById(R.id.sendButton);
        itemListSpinner=(Spinner)findViewById(R.id.itemListSpinner);
        dateSpinner=(Spinner)findViewById(R.id.dateSpinner);
        itemListView=(ListView)findViewById(R.id.itemListView);
        controller= new HistoryActivityController(this);
        //Create Events
        CreateEvent();

    }
    private void CreateEvent()
    {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backButton_onClick();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.sendButton_onClick();
            }
        });
        itemListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                controller.itemListSpinner_onItemSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                controller.itemListSpinner_onNothingSelected();
            }
        });
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                controller.dateSpinner_onItemSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                controller.dateSpinner_onNothingSelected();
            }
        });
    }
}
