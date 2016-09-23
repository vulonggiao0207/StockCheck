package com.giao.stockcheck;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.giao.Model.ItemCheck;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class Item_History_List_Adapter extends BaseAdapter{
    private ArrayList<ItemCheck> data;
    private static LayoutInflater inflater = null;
    public Activity activity;
    public TextView itemIDTextView;
    public TextView itemNameTextView;
    public TextView itemUnitTextView;
    public TextView itemQuantityTextView;
    public Button deleteItemButton;

    public Item_History_List_Adapter(Activity activity, ArrayList<ItemCheck> data)
    {
        this.activity=activity;
        this.data=new ArrayList<ItemCheck>(data);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public ItemCheck getItem(int position) {
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
            vi = inflater.inflate(R.layout.item_history_listview_layout, null, true);
        }
        ItemCheck temp = (ItemCheck) data.get(position);
        //Create Control views
        itemIDTextView=(TextView)vi.findViewById(R.id.itemIDTextView);
        itemNameTextView=(TextView) vi.findViewById(R.id.itemNameTextView);
        itemUnitTextView=(TextView)vi.findViewById(R.id.itemUnitTextView);
        itemQuantityTextView=(TextView)vi.findViewById(R.id.itemQuantityTextView);
        deleteItemButton=(Button)vi.findViewById(R.id.deleteItemButton);
        //Set data
        itemIDTextView.setText(Integer.toString(temp.getItemID()));
        itemNameTextView.setText(temp.getItem().getItemName());
        itemUnitTextView.setText(temp.getItem().getUnit());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        itemQuantityTextView.setText(decimalFormat.format(temp.getQuantity()));
        deleteItemButton.setTag(position);
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (Integer) v.getTag();
                ItemCheck itemCheck = data.get(index);
                data.remove(itemCheck);
                notifyDataSetChanged();
            }
        });
        return vi;
    }
}
