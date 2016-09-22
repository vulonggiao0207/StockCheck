package com.giao.stockcheck;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.giao.Model.Item;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class Item_Check_List_Adapter extends BaseAdapter {
    private ArrayList<Item> data;
    private static LayoutInflater inflater = null;
    public Activity activity;

    //public int currPosition;
    //private String[] arrayItem;
    public Item_Check_List_Adapter(Activity activity, ArrayList<Item> data)
    {
        this.activity=activity;
        this.data=new ArrayList<Item>(data);
        //arrayItem = new String[data.size()];
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //View vi = convertView;
        final ViewHolder holder;
        final Item temp = (Item) data.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_check_listview_layout, null, true);

            holder= new ViewHolder();
            holder.itemIDTextView =(TextView) convertView.findViewById(R.id.itemIDTextView);
            holder.itemNameTextView=(TextView) convertView.findViewById(R.id.itemNameTextView);
            holder.itemUnitTextView=(TextView)convertView.findViewById(R.id.itemUnitTextView);
            holder.itemQuantityEditText=(EditText)convertView.findViewById(R.id.itemQuantityEditText);
            convertView.setTag(holder);
        }
        //currPosition=position;
        //Create Control views
        else
            holder = (ViewHolder) convertView.getTag();
        //Set data

        holder.indexReference = position;
        holder.itemIDTextView.setText(Integer.toString(temp.getItemID()));
        holder.itemNameTextView.setText(temp.getItemName());
        holder.itemUnitTextView.setText(temp.getUnit());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        holder.itemQuantityEditText.setText(decimalFormat.format(temp.getQuantity()));
        //Update dataset after change
        //notifyDataSetChanged();
        //preventValueChange(position);
        holder.itemQuantityEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    LinearLayout _parent = (LinearLayout) v.getParent();
                    EditText quantityEditText = (EditText) _parent.findViewById(R.id.itemQuantityEditText);
                    data.get(position).setQuantity(Long.parseLong(quantityEditText.getText().toString()));
                }
            }
        });
        holder.itemQuantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //arrayItem[holder.indexReference]= s.toString();
                data.get(holder.indexReference).setQuantity(Long.parseLong(s.toString()));
                //Toast.makeText(activity.getBaseContext(), s.toString()+" update "+data.get(holder.indexReference).getItemName() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //arrayItem[holder.indexReference]= s.toString();
                //data.get(holder.indexReference).setQuantity(Long.parseLong(s.toString()));
            }
        });

        return convertView;
    }
    private static class ViewHolder
    {
        private int indexReference;
        private TextView itemIDTextView;
        private TextView itemNameTextView;
        private TextView itemUnitTextView;
        private EditText itemQuantityEditText;
    }
}