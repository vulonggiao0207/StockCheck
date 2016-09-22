package com.giao.stockcheck;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.giao.Dataconnection.ItemDAO;
import com.giao.Model.Item;

import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class Item_Management_List_Adapter extends BaseAdapter {
    private ArrayList<Item> data;
    private static LayoutInflater inflater = null;
    public Activity activity;
    public TextView itemNameTextView;
    public TextView itemUnitTextView;
    public Button deleteItemButton;
    //This view/itemStr is used in AlertDialog
    public View view;
    public String itemStr="";
    public Item_Management_List_Adapter(Activity activity, ArrayList<Item> data)
    {
        this.activity=activity;
        this.data=new ArrayList<Item>(data);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Item getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.item_management_listview_layout, null, true);
        }
        Item temp = (Item) data.get(position);
        //Create Control views
        itemNameTextView=(TextView) vi.findViewById(R.id.itemNameTextView);
        itemUnitTextView=(TextView)vi.findViewById(R.id.itemUnitTextView);
        deleteItemButton=(Button)vi.findViewById(R.id.deleteItemButton);
        //Set data
        itemNameTextView.setText(temp.getItemName());
        itemUnitTextView.setText(temp.getUnit());
        deleteItemButton.setTag(position);
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    view=v;
                    int index = (Integer) view.getTag();
                    itemStr = data.get(index).getItemName();
                    //Display Dialog
                    AlertDialog.Builder mDialog = new AlertDialog.Builder(activity);
                    mDialog.setTitle("Delete");
                    mDialog.setMessage("Do you want to delete item '"+itemStr+"'");
                    //If Agree delete --> Do deleting
                    mDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which) {
                            int index = (Integer) view.getTag();
                            Item item = data.get(index);
                            ItemDAO itemDAO = new ItemDAO(activity.getBaseContext());
                            itemDAO.open();
                            long result = itemDAO.delete(Integer.toString(item.getItemID()));
                            itemDAO.close();
                            if (result > 0) {
                                Toast.makeText(activity.getBaseContext(), "Item '" + item.getItemName() + "' is deleted successfully", Toast.LENGTH_SHORT).show();
                                data.remove(index);
                                notifyDataSetChanged();
                            }
                            dialog.cancel();
                        }
                    });
                    //If do not agree deleting --> return to parent view
                    mDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = mDialog.create();
                    alert.show();
                } catch (Exception e) {
                    Toast.makeText(activity.getBaseContext(), "Try again! Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        return vi;
    }
}
