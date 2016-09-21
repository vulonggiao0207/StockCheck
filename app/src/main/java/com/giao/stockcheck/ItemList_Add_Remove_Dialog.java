package com.giao.stockcheck;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.giao.Controller.ItemManagementActivityController;
import com.giao.Dataconnection.ItemListDAO;

import org.w3c.dom.Text;

/**
 * Created by Long on 9/20/2016.
 */
public class ItemList_Add_Remove_Dialog extends Activity {

    private static Button backButton;
    private static Button OKButton;
    private static TextView itemListHeaderTextView;
    private static TextView addNewItemTextView;
    private static EditText addNewItemEditText;
    private String listName;
    private ItemManagementActivityController controller;
    private static boolean isDelete=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_add_remove_dialog);
        Initial();
        CreateEvent();
    }
    private void Initial()
    {
        itemListHeaderTextView=(TextView)findViewById(R.id.itemListHeaderTextView);
        backButton=(Button)findViewById(R.id.backButton);
        OKButton=(Button)findViewById(R.id.OKButton);
        addNewItemTextView=(TextView)findViewById(R.id.addNewItemTextView);
        addNewItemEditText=(EditText)findViewById(R.id.addNewItemEditText);
        controller = new ItemManagementActivityController(this);
    }
    private void onCreate()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            listName = extras.getString("listName");
        }
        if(listName==null) {
            itemListHeaderTextView.setText("Add Item List");
            addNewItemTextView.setText("Please enter the new list you want to add");
            addNewItemEditText.setEnabled(true);
            isDelete=false;
        }
        else {
            itemListHeaderTextView.setText("Delete Item List");
            addNewItemTextView.setText("Do you want to delete the list:");
            addNewItemEditText.setText(listName);
            addNewItemEditText.setEnabled(false);
            isDelete=true;
        }
    }
    private void CreateEvent()
    {
        //onCreate Event
        onCreate();
        //controller.dialog_onCreate();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.list_add_dialog_backButton_onClick();
            }
        });
        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemListDAO itemListDAO= new ItemListDAO(getBaseContext());
                String str=addNewItemEditText.getText().toString();;
                int type=1; //1 for create and -1 for delete
                if(isDelete==true)
                {
                    type=-1;
                }
                else
                {
                    type=1;
                }
                int result=controller.list_add_dialog_OKButton_onClick(str,type);
                if(result==1) {
                    addNewItemEditText.setText("");
                    addNewItemEditText.setHint("");
                    if(type==1)
                        Toast.makeText(getBaseContext(), "New list is created successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getBaseContext(), "List is deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
