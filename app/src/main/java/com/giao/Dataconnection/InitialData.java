package com.giao.Dataconnection;

import android.content.Context;

import com.giao.Model.ItemList;

/**
 * Created by Long on 9/19/2016.
 */
public class InitialData {
    Context context;
    public InitialData(Context context) {
        this.context=context;
    }
    public void InitData()
    {
        //Create List
        ItemListDAO itemList= new ItemListDAO(context);
        String Lists[]={"Drink","Meat","Vegeterian"};
        for(int i=0;i<Lists.length;i++)
        {
            itemList.open();
            itemList.create(Lists[i]);
            itemList.close();
        }
        //Create Items
        ItemDAO itemDAO= new ItemDAO(context);
        String Items1[][]={{"Meat","Pork","kg"},
                {"Meat","Beef","kg"},
                {"Meat","Chicken","kg"},
                {"Meat","Fish","kg"},
                {"Meat","Prawn","kg"},
                {"Meat","Duck","kg"}};
        for(int i=0;i<Items1.length;i++)
        {
            itemDAO.open();
            itemDAO.create(Items1[i][0],Items1[i][1],Items1[i][2]);
            itemDAO.close();
        }
        String Items2[][]={{"Drink","Coke","Bottle"},
                {"Drink","Coke","Can"},
                {"Drink","Diet Coke","Bottle"},
                {"Drink","Fanta","Bottle"},
                {"Drink","Pepsi","Bottle"},
                {"Drink","Solo","Bottle"}};
        for(int i=0;i<Items2.length;i++)
        {
            itemDAO.open();
            itemDAO.create(Items2[i][0],Items2[i][1],Items2[i][2]);
            itemDAO.close();
        }
        String Items3[][]={{"Vegeterian","Mint","Bunch"},
                {"Vegeterian","Onion","Kg"},
                {"Vegeterian","Garlic","Bunch"},
                {"Vegeterian","Basil","Bunch"},
                {"Vegeterian","Cereal","Bunch"},
                {"Vegeterian","Tomato","kg"},
                {"Vegeterian","Potato","kg"},
                {"Vegeterian","Cereal","Bunch"},
                {"Vegeterian","Cucumber","kg"},
                {"Vegeterian","Chili","kg"},
                {"Vegeterian","Peanut","kg"}};
        for(int i=0;i<Items3.length;i++)
        {
            itemDAO.open();
            itemDAO.create(Items3[i][0],Items3[i][1],Items3[i][2]);
            itemDAO.close();
        }
    }
}
