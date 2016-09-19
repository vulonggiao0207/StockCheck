package com.giao.stockcheck;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.giao.Controller.ItemManagementActivityController;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemManagementActivity extends Activity {

    private Button backButton;
    private AutoCompleteTextView itemListAutoTextView;
    private EditText itemNameEditText;
    private EditText itemUnitEditText;
    private Button addItemListButton;
    private Button addItemButton;
    private ListView itemListView;
    public ItemManagementActivityController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_management);
        //Declare Control Views
        backButton= (Button)findViewById(R.id.backButton);
        itemListAutoTextView=(AutoCompleteTextView)findViewById(R.id.itemListAutoTextView);
        itemNameEditText=(EditText)findViewById(R.id.itemNameEditText);
        itemUnitEditText=(EditText)findViewById(R.id.itemUnitEditText);
        addItemListButton=(Button)findViewById(R.id.addItemListButton);
        addItemButton=(Button)findViewById(R.id.addItemButton);
        itemListView=(ListView)findViewById(R.id.itemListView);
        controller= new ItemManagementActivityController(this);
        //Create Event;
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
        itemListAutoTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                controller.itemListAutoTextView_onItemSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                controller.itemListAutoTextView_onNothingSelected();
            }
        });
        addItemListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addItemListButton_onClick();
            }
        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addItemButton_onClick();
            }
        });
    }
}
