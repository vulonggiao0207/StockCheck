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

import com.giao.Model.Item;
import com.giao.Model.ItemCheck;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class Item_Check_List_Adapter extends BaseAdapter {
    private ArrayList<ItemCheck> data;
    private static LayoutInflater inflater = null;
    public Activity activity;
    public TextView itemNameTextView;
    public TextView itemUnitTextView;
    public EditText itemQuantityEditText;

    public Item_Check_List_Adapter(Activity activity, ArrayList<ItemCheck> data)
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
            vi = inflater.inflate(R.layout.item_check_listview_layout, null, true);
        }
        ItemCheck temp = (ItemCheck) data.get(position);
        //Create Control views
        itemNameTextView=(TextView) vi.findViewById(R.id.itemNameTextView);
        itemUnitTextView=(TextView)vi.findViewById(R.id.itemUnitTextView);
        itemQuantityEditText=(EditText)vi.findViewById(R.id.itemQuantityEditText);
        //Set data
        itemNameTextView.setText(temp.Item().getItemName());
        itemUnitTextView.setText(temp.Item().getUnit());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        itemQuantityEditText.setText(decimalFormat.format(temp.getQuantity()));
        return vi;
    }
}
