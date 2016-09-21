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
import android.widget.Spinner;
import android.widget.Toast;

import com.giao.Controller.ItemManagementActivityController;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemManagementActivity extends Activity {

    private static Button backButton;
    private static Spinner itemListSpinner;
    private static EditText itemNameEditText;
    private static EditText itemUnitEditText;
    private static Button addItemListButton;
    private static Button deleteItemListButton;
    private static Button addItemButton;
    private static ListView itemListView;
    private String selectedList;
    public ItemManagementActivityController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_management);
        //Initial
        Initial();
        //Create Event;
        CreateEvent();


    }
    @Override
    protected void onResume()
    {
        super.onResume();
        controller.onCreate(itemListSpinner);
    }
    private void Initial()
    {
        //Declare Control Views
        backButton= (Button)findViewById(R.id.backButton);
        itemListSpinner=(Spinner)findViewById(R.id.itemListSpinner);
        itemNameEditText=(EditText)findViewById(R.id.itemNameEditText);
        itemUnitEditText=(EditText)findViewById(R.id.itemUnitEditText);
        addItemListButton=(Button)findViewById(R.id.addItemListButton);
        deleteItemListButton=(Button)findViewById(R.id.deleteItemListButton);
        addItemButton=(Button)findViewById(R.id.addItemButton);
        itemListView=(ListView)findViewById(R.id.itemListView);
        controller= new ItemManagementActivityController(this);
        //Set Invisibility to add and delete Button
        //addItemListButton.setVisibility(View.INVISIBLE);
        //deleteItemListButton.setVisibility(View.INVISIBLE);
    }
    private void CreateEvent()
    {
        //onCreate Event
        controller.onCreate(itemListSpinner);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backButton_onClick();
            }
        });
        //Current selected position of AutoCompletTextView
        //int curPosition;

        itemListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "onItemClick" , Toast.LENGTH_LONG).show();
                int curPosition=position;
                //Get position in Original Dish List
                selectedList = (String) parent.getItemAtPosition(position);
                //Reload the Items ListView
                int result= controller.itemListSpinner_onItemSelected(selectedList,itemListSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addItemListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addItemListButton_onClick();
            }
        });
        deleteItemListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.deleteItemListButton_onClick(selectedList);
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
