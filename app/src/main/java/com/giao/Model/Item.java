package com.giao.Model;

import java.util.ArrayList;

/**
 * Created by Long on 9/16/2016.
 */
public class Item  {
    private String ListName;
    private int ItemID;
    private boolean Delete;
    private ArrayList<ItemCheck> ItemCheck;

    public Item() {
    }

    public Item(String listName, int itemID, boolean delete) {
        ListName = listName;
        ItemID = itemID;
        Delete = delete;
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

    public boolean isDelete() {
        return Delete;
    }

    public void setDelete(boolean delete) {
        Delete = delete;
    }

    public ArrayList<com.giao.Model.ItemCheck> getItemCheck() {
        return ItemCheck;
    }

    public void setItemCheck(ArrayList<com.giao.Model.ItemCheck> itemCheck) {
        ItemCheck = itemCheck;
    }
}
