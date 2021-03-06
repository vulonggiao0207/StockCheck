package com.giao.Dataconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.giao.Model.Item;
import com.giao.Model.ItemCheck;
import com.giao.Model.ItemList;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class ItemDAO {
    private static final String KEY_ROWID = "ItemID";
    private static final String ITEM_LISTNAME="ListName";
    private static final String ITEM_NAME="ItemName";
    private static final String ITEM_UNIT="Unit";
    private static final String ITEM_QUANTITY="Quantity";
    private static final String ITEM_DEL="Del";
    private static final String DATABASE_TABLE = "Item";
    private static DatabaseHelper databaseHelper;
    //
    public final Context context;
    private SQLiteDatabase database;

    public ItemDAO(Context context) {
        this.context = context;
        databaseHelper= new DatabaseHelper(context);
        // TODO Auto-generated constructor stub
    }

    public ItemDAO open() throws SQLException {
        database = databaseHelper.getReadableDatabase();
        return this;
    }

    public void close() throws SQLException {
        databaseHelper.close();
    }

    public ArrayList<Item> select() throws SQLException {
        String query = "SELECT itemID,listName,itemName,unit,quantity,del FROM Item WHERE del =0 ORDER BY itemName";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<Item> list = new ArrayList<Item>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            String listName,itemName,unit;
            int itemID=Integer.parseInt(cur.getString(0));
            listName=cur.getString(1);
            itemName=cur.getString(2);
            unit=cur.getString(3);
            BigDecimal quantity= new BigDecimal(cur.getString(4));
            boolean del=Boolean.parseBoolean(cur.getString(5));;
            Item record = new Item(itemID,listName,itemName,unit,quantity,del,new ArrayList<ItemCheck>());
            list.add(record);
        }
        cur.close();
        return list;
    }
    public Item selectItem(String ItemID) throws SQLException {
        String query = "SELECT itemID,listName,itemName,unit,del FROM Item WHERE itemID="+ItemID+"  ORDER BY itemName";
        Cursor cur = database.rawQuery(query, null);
        int iRow = cur.getColumnIndex(KEY_ROWID);
        Item record= new Item();
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            String listName,itemName,unit;
            int itemID=Integer.parseInt(cur.getString(0));
            listName=cur.getString(1);
            itemName=cur.getString(2);
            unit=cur.getString(3);
            BigDecimal quantity= new BigDecimal(cur.getString(4));
            boolean del=false;
            record = new Item(itemID,listName,itemName,unit,quantity,del,new ArrayList<ItemCheck>());
            break;
        }
        cur.close();
        return record;
    }
    public ArrayList<Item> select(String itemListName) throws SQLException {
        String query = "SELECT itemID,listName,itemName,unit,quantity,del FROM Item WHERE del =0 AND listName='"+itemListName+"' ORDER BY itemName";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<Item> list = new ArrayList<Item>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            String listName,itemName,unit;
            int itemID=Integer.parseInt(cur.getString(0));
            listName=cur.getString(1);
            itemName=cur.getString(2);
            unit=cur.getString(3);
            BigDecimal quantity=new BigDecimal(cur.getString(4));
            boolean del=Boolean.parseBoolean(cur.getString(5));;
            Item record = new Item(itemID,listName,itemName,unit,quantity,del,new ArrayList<ItemCheck>());
            list.add(record);
        }
        cur.close();
        return list;
    }

    public long create(String listName,String itemName,String unit) throws SQLException {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_LISTNAME, listName);
        cv.put(ITEM_NAME, itemName);
        cv.put(ITEM_UNIT, unit);
        cv.put(ITEM_QUANTITY,0);
        cv.put(ITEM_DEL, 0);
        return database.insert(DATABASE_TABLE, null, cv);
    }

    public long update(String itemID,String listName,String itemName,String unit,String quantity, String del) throws SQLException
    {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_LISTNAME, listName);
        cv.put(ITEM_NAME, itemName);
        cv.put(ITEM_UNIT, unit);
        cv.put(ITEM_QUANTITY,quantity);
        cv.put(ITEM_DEL, del);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{itemID});
    }
    public long update(String itemID,String quantity) throws SQLException
    {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_QUANTITY,quantity);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{itemID});
    }

    public long delete(String itemID) throws SQLException {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_DEL, 1);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{itemID});
    }
    public long delete(String listName,int i) throws SQLException {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_DEL, 1);
        return database.update(DATABASE_TABLE, cv, ITEM_LISTNAME + "=?", new String[]{listName});
    }

    public boolean delete() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }

}
