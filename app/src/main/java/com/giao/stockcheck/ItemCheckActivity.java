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

import com.giao.Controller.ItemCheckActivityController;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemCheckActivity extends Activity {

    private Button backButton;
    private Button saveButton;
    private Spinner itemListSpinner;
    private TextView dateTextView;
    private ListView itemListView;
    public ItemCheckActivityController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_check);
        //Declare View Controls
        backButton=(Button)findViewById(R.id.backButton);
        saveButton=(Button)findViewById(R.id.saveButton);
        itemListSpinner=(Spinner)findViewById(R.id.itemListSpinner);
        dateTextView=(TextView)findViewById(R.id.dateTextView);
        itemListView=(ListView)findViewById(R.id.itemListView);
        controller= new ItemCheckActivityController(this);
    }
    private void CreateEvent()
    {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backButton_onClick();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.saveButton_onClick();
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

    }
}
