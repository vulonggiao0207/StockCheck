package com.giao.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemList {
    private String ListName;
    private ArrayList<ItemList> Items;

    public ItemList() {
    }

    public ItemList(String listName) {
        ListName = listName;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public ArrayList<ItemList> getItems() {
        return Items;
    }

    public void setItems(ArrayList<ItemList> items) {
        Items = items;
    }
}
