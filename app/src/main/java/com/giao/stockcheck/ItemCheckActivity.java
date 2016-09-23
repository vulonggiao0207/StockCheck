package com.giao.stockcheck;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.giao.Controller.ItemCheckActivityController;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String selectedListName="";
    public ItemCheckActivityController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_check);
        //Declare View Controls
        Initial();
        //Create Events
        CreateEvent();
    }

    private void Initial()
    {
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
        controller.onCreate(itemListSpinner);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.backButton_onClick();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButton.setFocusable(true);
                int result = controller.saveButton_onClick(dateTextView.getText().toString(), itemListView);
                //Reset current date
                dateTextView.setText("");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
                Date date = new Date();
                dateTextView.setText(dateFormat.format(date));
            }
        });
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout _parent = (LinearLayout) view.getParent();
                EditText quantityEditText = (EditText) _parent.findViewById(R.id.itemQuantityEditText);
                quantityEditText.setFocusable(true);
            }
        });
        itemListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int curPosition = position;
                //Get position in Original Dish List
                selectedListName = (String) parent.getItemAtPosition(position);
                //Reload the Items ListView
                int result = controller.itemListSpinner_onItemSelected(selectedListName, itemListView);
                //Set current time
                dateTextView.setText("");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
                Date date = new Date();
                dateTextView.setText(dateFormat.format(date));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                controller.itemListSpinner_onNothingSelected();

            }
        });

    }
}
