package com.giao.Model;

import java.util.ArrayList;

/**
 * Created by Long on 9/16/2016.
 */
public class Item  extends ItemList{
    private int ItemID;
    private String ListName;
    private String ItemName;
    private String Unit;
    private boolean Del;
    private ArrayList<ItemCheck> ItemChecks;
    private Item Item;
    private ItemList ItemList;
    public Item() {
        Item=this;
    }
    public Item(ArrayList<com.giao.Model.ItemCheck> itemChecks) {
        ItemChecks = itemChecks;
    }
    public Item(boolean del, String listName, String itemName, String unit) {
        Del = del;
        ListName = listName;
        ItemName = itemName;
        Unit = unit;
    }

    public Item(int itemID, String listName, String itemName, String unit, boolean del, ArrayList<com.giao.Model.ItemCheck> itemChecks) {
        ItemID = itemID;
        ListName = listName;
        ItemName = itemName;
        Unit = unit;
        Del = del;
        ItemChecks = itemChecks;
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
        return ItemChecks;
    }

    public void setItemCheck(ArrayList<com.giao.Model.ItemCheck> itemChecks) {
        ItemChecks = itemChecks;
    }

    public Item Item()
    {
        return Item;
    }

    public ItemList ItemList(){return super.ItemList();}

}
