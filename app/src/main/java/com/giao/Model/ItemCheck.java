package com.giao.Model;

import java.math.BigDecimal;

/**
 * Created by Long on 9/16/2016.
 */
public class ItemCheck extends Item{
    private Item item;
    private String CheckDate;
    private int ItemID;
    private BigDecimal Quantity;

    public ItemCheck() {
    }

    public ItemCheck(int itemID,String checkDate, BigDecimal quantity) {

        CheckDate = checkDate;
        ItemID = itemID;
        Quantity=quantity;
    }
    public ItemCheck(Item item, int itemID,String checkDate, BigDecimal quantity) {
        this.item=item;
        CheckDate = checkDate;
        ItemID = itemID;
        Quantity=quantity;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String checkDate) {
        CheckDate = checkDate;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public BigDecimal getQuantity() {
        return Quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        Quantity = quantity;
    }

    public Item Item() {
        return super.Item();
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
