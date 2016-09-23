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
    public String selectedListName;
    public String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Initial();
        //Create Events
        CreateEvent();

    }
    private void Initial()
    {
        backButton=(Button)findViewById(R.id.backButton);
        sendButton=(Button)findViewById(R.id.sendButton);
        itemListSpinner=(Spinner)findViewById(R.id.itemListSpinner);
        dateSpinner=(Spinner)findViewById(R.id.dateSpinner);
        itemListView=(ListView)findViewById(R.id.itemListView);
        controller= new HistoryActivityController(this);
    }
    private void CreateEvent()
    {
        controller.onCreate(itemListSpinner);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backButton_onClick();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.sendButton_onClick(selectedListName,selectedDate,itemListView);
            }
        });
        itemListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int curPosition = position;
                //Get position in Original Dish List
                selectedListName = (String) parent.getItemAtPosition(position);
                //Reload the Items ListView
                int result= controller.itemListSpinner_onItemSelected(selectedListName, dateSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                controller.itemListSpinner_onNothingSelected();
            }
        });
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int curPosition = position;
                //Get position in Original Dish List
                selectedDate = (String) parent.getItemAtPosition(position);
                //Reload the Items ListView
                int result= controller.dateSpinner_onItemSelected(selectedListName,selectedDate,itemListView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                controller.dateSpinner_onNothingSelected();
            }
        });
    }
}
