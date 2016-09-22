package com.giao.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.giao.Dataconnection.ItemCheckDAO;
import com.giao.Dataconnection.ItemDAO;
import com.giao.Dataconnection.ItemListDAO;
import com.giao.Model.Item;
import com.giao.Model.ItemCheck;
import com.giao.Model.ItemList;
import com.giao.stockcheck.Item_Check_List_Adapter;
import com.giao.stockcheck.Item_Management_List_Adapter;
import com.giao.stockcheck.R;

import java.util.ArrayList;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemCheckActivityController {
    private Activity activity;
    private Context context;
    private ItemListDAO itemListDAO;
    public ItemCheckActivityController(Activity activity)
    {
        this.activity=activity;
        this.context=activity.getBaseContext();
        itemListDAO= new ItemListDAO(activity.getBaseContext());
    }
    //Control Views events
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
            Toast.makeText(context, "The application gots error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public int backButton_onClick()
    {
        try {
            AlertDialog.Builder mDialog = new AlertDialog.Builder(activity);
            mDialog.setTitle("Back");
            mDialog.setMessage("Are you sure you already save your work!");
            mDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Doing save here
                    dialog.cancel();
                    activity.onBackPressed();
                }

            });
            mDialog.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = mDialog.create();
            alert.show();
            return 1;
        }
        catch (Exception e)
        {
            Toast.makeText(activity.getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    public int saveButton_onClick(String currentDate,ListView itemListView)
    {
        try {
            ItemDAO itemDAO= new ItemDAO(context);
            ItemCheckDAO itemcheckDAO= new ItemCheckDAO(context);
            //Get the Array<Item> saveItems from itemListView
            ArrayList<Item> savedItems= new ArrayList<>();
            for(int i =0;i<itemListView.getChildCount();i++){
                Item item = new Item();
                LinearLayout layout = (LinearLayout)itemListView.getChildAt(i);
                String itemID = ((TextView)layout.getChildAt(0)).getText().toString();
                String quantity = ((EditText)layout.getChildAt(3)).getText().toString();
                try {item.setItemID(Integer.parseInt(itemID));}
                catch(Exception e){item.setItemID(0);}
                try {item.setQuantity(Long.parseLong(quantity));}
                catch(Exception e){item.setQuantity(0);}
                savedItems.add(item);
            }
            //Go through every single line of ListView
            for(Item item: savedItems)
            {
                String itemid=Integer.toString(item.getItemID());
                //String listname=item.getListName();
                //String itemname=item.getItemName();
                //String unit=item.getUnit();
                String quantity=Long.toString(item.getQuantity());
                //String del=Boolean.toString(item.isDel());
                //Update the new quantity to Item (ItemID,quantity)
                itemDAO.open();
                itemDAO.update(itemid,quantity);
                itemDAO.close();

                //Create new version to ItemCheck (ItemId, Date, quantity)
                itemcheckDAO.open();
                itemcheckDAO.create(itemid,currentDate,quantity);
                itemcheckDAO.close();
            }
            return 1;
        }
        catch (Exception e)
        {
            Toast.makeText(activity.getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    public int itemListSpinner_onItemSelected(String itemListName,ListView itemListView)
    {
        try
        {
            ItemDAO itemDAO = new ItemDAO(context);
            itemDAO.open();
            Item_Check_List_Adapter adapter=  new Item_Check_List_Adapter(activity,itemDAO.select(itemListName));
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
    public int itemListSpinner_onNothingSelected()
    {
        return 1;
    }
}
