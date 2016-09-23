package com.giao.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.giao.Dataconnection.ItemCheckDAO;
import com.giao.Dataconnection.ItemListDAO;
import com.giao.Model.Item;
import com.giao.Model.ItemCheck;
import com.giao.Model.ItemList;
import com.giao.stockcheck.Item_History_List_Adapter;
import com.giao.stockcheck.R;

import java.util.ArrayList;

/**
 * Created by Long on 9/16/2016.
 */
public class HistoryActivityController {
    Activity activity;
    Context context;
    ItemCheckDAO itemCheckDAO;
    public HistoryActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
        itemCheckDAO= new ItemCheckDAO(context);
    }
    //Control Views events
    public void onCreate(Spinner itemListSpinner)
    {
        try {
            ItemListDAO itemListDAO= new ItemListDAO(context);
            //get list of Dish
            itemListDAO.open();
            ArrayList<ItemList> itemListList = new ArrayList<>();
            itemListList = itemListDAO.select();
            ArrayList<String> recList = new ArrayList<>();
            for (int i = 0; i < itemListList.size(); i++) {
                recList.add(itemListList.get(i).getListName());
            }
            itemListDAO.close();
            //Bind data to itemListSpinner
            ArrayAdapter adapter = new ArrayAdapter(activity.getBaseContext(), R.layout.simple_spinner_item, recList);
            itemListSpinner.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(context, "The application gots error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public int backButton_onClick()
    {
        activity.onBackPressed();
        return 1;
    }
    public int sendButton_onClick(String selectedList,String selectedDate,ListView itemListView)
    {
        try {
            String msg="List: "+selectedList+"\n";
            msg+="Checked time: "+ selectedDate+"\n";
            //Get the checked only -->put to the sentItems
            //ArrayList<ItemCheck> sentItems= new ArrayList<>();
            ListAdapter itemList= itemListView.getAdapter();
            for(int i=0;i<itemList.getCount();i++)
            {
                //sentItems.add((ItemCheck) itemList.getItem(i));
                ItemCheck item=(ItemCheck) itemList.getItem(i);
                msg+=item.getItem().getItemName();
                msg+= item.getQuantity();
                msg+=" ("+item.getItem().getUnit()+") ";
                msg+="\n";
            }
            //
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address","");
            smsIntent.putExtra("sms_body",msg);
            activity.startActivity(smsIntent);
            return 1;
        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The application gots error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return -11;
        }
    }
    public int itemListSpinner_onItemSelected(String selectedListName,Spinner dateSpinner)
    {

            //Get data to dataSpinner
            ItemCheckDAO itemCheckDAO = new ItemCheckDAO(context);
            itemCheckDAO.open();
            ArrayList<ItemCheck> itemCheckList=itemCheckDAO.select(selectedListName);
            //Assign to dataSpinner
            ArrayList<String> resList = new ArrayList<>();
            for (int i = 0; i < itemCheckList.size(); i++) {
                resList.add(itemCheckList.get(i).getCheckDate());
            }
            itemCheckDAO.close();
            //Bind data to autocompleteTextview
            ArrayAdapter adapter = new ArrayAdapter(activity.getBaseContext(), R.layout.simple_spinner_item, resList);
            dateSpinner.setAdapter(adapter);
            return 1;

    }
    public int itemListSpinner_onNothingSelected()
    {
        return 1;
    }
    public int dateSpinner_onItemSelected(String selectedListName, String selectedDate, ListView itemListView)
    {
        try {
            //Get data to itemListView
            ItemCheckDAO itemCheckDAO = new ItemCheckDAO(context);
            itemCheckDAO.open();
            ArrayList<ItemCheck> itemChecks=itemCheckDAO.select(selectedListName, selectedDate);
            itemCheckDAO.close();
            Item_History_List_Adapter adapter = new Item_History_List_Adapter(activity,itemChecks);
            //Assign to itemListView
            itemListView.setAdapter(adapter);

            return 1;
        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The application gots error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return -1;
        }
    }
    public int dateSpinner_onNothingSelected()
    {
        return 1;
    }
}
