package com.giao.Controller;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.giao.Dataconnection.ItemDAO;
import com.giao.Dataconnection.ItemListDAO;
import com.giao.Model.Item;
import com.giao.Model.ItemList;
import com.giao.stockcheck.ItemList_Add_Remove_Dialog;
import com.giao.stockcheck.Item_Management_List_Adapter;
import com.giao.stockcheck.R;

import java.util.ArrayList;


/**
 * Created by Long on 9/16/2016.
 */
public class ItemManagementActivityController {
    private Activity activity;
    private Context context;
    private ItemListDAO itemListDAO;
    public ItemManagementActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
        itemListDAO= new ItemListDAO(activity.getBaseContext());
    }
    //item_management
    public void onCreate(Spinner itemListSpinner)
    {
        try {
            //get list of Dish
            itemListDAO.open();
            ArrayList<ItemList> itemListList = new ArrayList<>();
            itemListList = itemListDAO.select();
            ArrayList<String> recList = new ArrayList<>();
            for (int i = 0; i < itemListList.size(); i++) {
                recList.add(itemListList.get(i).getListName());
            }
            itemListDAO.close();
            //Bind data to autocompleteTextview
            ArrayAdapter adapter = new ArrayAdapter(activity.getBaseContext(), R.layout.simple_spinner_item, recList);
            itemListSpinner.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(context, "The application gots error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public int backButton_onClick() {
        activity.onBackPressed();
        return 1;
    }
    public int itemListSpinner_onItemSelected(String itemListName,ListView itemListView)
    {
        try
        {
            ItemDAO itemDAO = new ItemDAO(context);
            itemDAO.open();
            Item_Management_List_Adapter adapter=  new Item_Management_List_Adapter(activity,itemDAO.select(itemListName));
            itemDAO.close();
            itemListView.setAdapter(adapter);
            return 1;
        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The pplication gots error:"+e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    public int addItemListButton_onClick() {
        try {
            Intent intent = new Intent(activity.getBaseContext(), ItemList_Add_Remove_Dialog.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.getBaseContext().startActivity(intent);
            return 1;
        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The pplication gots error:"+e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    public int deleteItemListButton_onClick(String listName){
        try {
            Intent intent = new Intent(activity.getBaseContext(), ItemList_Add_Remove_Dialog.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("listName", listName);
            activity.getBaseContext().startActivity(intent);
            return 1;
        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The pplication gots error:"+e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    public int addItemButton_onClick(String listName,String itemName, String Unit) {

        try{
            //valication
            String validation="Cannot create new Item:";
            boolean isValid=true;
            if(listName.trim().equals("") )
            {
                isValid=false;
                validation+="\n- Item List cannot be blank.";
            }
            if(itemName.trim().equals("") )
            {
                isValid=false;
                validation+="\n- Item name cannot be blank.";
            }
            if(Unit.trim().equals("") )
            {
                isValid=false;
                validation+="\n- Unit cannot be blank.";
            }
            if(isValid==false)
            {
                Toast.makeText(context, validation, Toast.LENGTH_LONG).show();
                return 0;
            }
            //Check if the Item already exists in the selected List
            ItemDAO itemDAO = new ItemDAO(context);
            itemDAO.open();
            ArrayList<Item> Items= itemDAO.select(listName);
            itemDAO.close();
            for(Item item: Items)
            {
                if (item.getItemName().equals(itemName)&&item.getUnit().equals(Unit)) {
                    Toast.makeText(context, "Item '" + itemName + "' already exists in List " + listName, Toast.LENGTH_SHORT).show();
                    return 0;
                }
            }
            //Create new after pass all validation
            itemDAO.open();
            long result = itemDAO.create(listName, itemName, Unit);
            itemDAO.close();
            return 1;

        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The pplication gots error:"+e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    //item_list_add_remove_dialog
    public int dialog_onCreate()
    {
        return 1;
    }
    public int list_add_dialog_backButton_onClick()
    {
        activity.onBackPressed();
        return 1;
    }
    public int list_add_dialog_OKButton_onClick(String listName, int type)
    {
        try {
            if(listName.trim().equals(""))
            {
                Toast.makeText(context, "List name cannot be blank.", Toast.LENGTH_SHORT).show();
                return 1;
            }
            if(type==1 ) {
                itemListDAO.open();
                if(itemListDAO.select().contains(listName))
                {
                    Toast.makeText(context, "List name already exists", Toast.LENGTH_SHORT).show();
                    itemListDAO.close();
                    return 1;
                }
                itemListDAO.close();
                itemListDAO.open();
                itemListDAO.create(listName);
                itemListDAO.close();
            }
            else
            {
                //delete all ItemCheck
                //delete all Item
                ItemDAO itemDAO= new ItemDAO(context);
                itemDAO.open();
                itemDAO.delete(listName,1);
                itemDAO.close();
                //delete all ItemList
                itemListDAO.open();
                itemListDAO.delete(listName);
                itemListDAO.close();
            }
            return 1;
        }
        catch (Exception e)
        {
            //Toast.makeText(context, "The pplication gots error:"+e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
}
