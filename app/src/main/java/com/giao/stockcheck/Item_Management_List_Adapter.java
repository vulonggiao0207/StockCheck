package com.giao.stockcheck;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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
                int index = (Integer) v.getTag();
                Item item = data.get(index);
                //Doing delete Item

            }
        });

        return vi;
    }
}
