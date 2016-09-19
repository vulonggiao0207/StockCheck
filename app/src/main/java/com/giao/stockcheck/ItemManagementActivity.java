package com.giao.stockcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemManagementActivity extends AppCompatActivity {

    private Button backButton;
    private AutoCompleteTextView itemListAutoTextView;
    private EditText itemNameEditText;
    private EditText itemUnitEditText;
    private Button addItemListButton;
    private Button addItemButton;
    private ListView itemListView;
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
    }
}
