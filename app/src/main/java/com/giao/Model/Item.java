package com.giao.Model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Long on 9/16/2016.
 */
public class Item  extends ItemList{
    private int ItemID;
    private String ListName;
    private String ItemName;
    private String Unit;
    private BigDecimal Quantity;
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
    public Item(boolean del, String listName, String itemName, String unit, BigDecimal quantity) {
        Del = del;
        ListName = listName;
        ItemName = itemName;
        Unit = unit;
        Quantity=quantity;
    }

    public Item(int itemID, String listName, String itemName, String unit,BigDecimal quantity,
                boolean del, ArrayList<ItemCheck> itemChecks) {
        ItemID = itemID;
        ListName = listName;
        ItemName = itemName;
        Unit = unit;
        Quantity=quantity;
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

    public BigDecimal getQuantity() {
        return Quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        Quantity = quantity;
    }

    public ArrayList<ItemCheck> getItemChecks() {
        return ItemChecks;
    }

    public void setItemChecks(ArrayList<ItemCheck> itemChecks) {
        ItemChecks = itemChecks;
    }

    public Item getItem() {
        return Item;
    }

    public void setItem(com.giao.Model.Item item) {
        Item = item;
    }

    public ItemList getItemList() {
        return ItemList;
    }

    public void setItemList(com.giao.Model.ItemList itemList) {
        ItemList = itemList;
    }

    public boolean isDel() {
        return Del;
    }

    public void setDel(boolean del) {
        Del = del;
    }

    public ArrayList<ItemCheck> getItemCheck() {
        return ItemChecks;
    }

    public void setItemCheck(ArrayList<ItemCheck> itemChecks) {
        ItemChecks = itemChecks;
    }

    public Item Item()
    {
        return Item;
    }

    public ItemList ItemList(){return super.ItemList();}

}
