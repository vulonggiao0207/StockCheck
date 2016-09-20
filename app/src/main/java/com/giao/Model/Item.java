package com.giao.Model;

import java.util.ArrayList;

/**
 * Created by Long on 9/16/2016.
 */
public class Item  {
    private int ItemID;
    private String ListName;
    private String ItemName;
    private String Unit;
    private boolean Del;
    private ArrayList<ItemCheck> ItemCheck;
    private Item Item;
    public Item() {
        Item=this;
    }
    public Item(ArrayList<com.giao.Model.ItemCheck> itemCheck) {
        ItemCheck = itemCheck;
    }
    public Item(boolean del, String listName, String itemName, String unit) {
        Del = del;
        ListName = listName;
        ItemName = itemName;
        Unit = unit;
    }

    public Item(int itemID, String listName, String itemName, String unit, boolean del, ArrayList<com.giao.Model.ItemCheck> itemCheck) {
        ItemID = itemID;
        ListName = listName;
        ItemName = itemName;
        Unit = unit;
        Del = del;
        ItemCheck = itemCheck;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public boolean isDel() {
        return Del;
    }

    public void setDel(boolean del) {
        Del = del;
    }

    public ArrayList<com.giao.Model.ItemCheck> getItemCheck() {
        return ItemCheck;
    }

    public void setItemCheck(ArrayList<com.giao.Model.ItemCheck> itemCheck) {
        ItemCheck = itemCheck;
    }
    public Item Item()
    {
        return Item;
    }

    public long Insert()
    {
        return 0;
    }
    public long Delete()
    {
        return 0;
    }
}
