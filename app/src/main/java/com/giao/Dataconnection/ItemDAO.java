package com.giao.Dataconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.giao.Model.Item;
import com.giao.Model.ItemCheck;
import com.giao.Model.ItemList;

import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class ItemDAO {
    private static final String KEY_ROWID = "ItemID";
    private static final String ITEM_LISTNAME="ListName";
    private static final String ITEM_NAME="ItemName";
    private static final String ITEM_UNIT="Unit";
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
        String query = "SELECT itemID,listName,itemName,unit,del FROM Item";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<Item> list = new ArrayList<Item>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            String listName,itemName,unit;
            int itemID=Integer.parseInt(cur.getString(0));
            listName=cur.getString(1);
            itemName=cur.getString(2);
            unit=cur.getString(3);
            boolean del=Boolean.parseBoolean(cur.getString(4));;
            Item record = new Item(itemID,listName,itemName,unit,del,new ArrayList<ItemCheck>());
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
        cv.put(ITEM_DEL, 0);
        return database.insert(DATABASE_TABLE, null, cv);
    }

    public long update(String itemID,String listName,String itemName,String unit, String del) throws SQLException
    {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_LISTNAME, listName);
        cv.put(ITEM_NAME, itemName);
        cv.put(ITEM_UNIT, unit);
        cv.put(ITEM_DEL, del);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{itemID});
    }

    public long remove(String itemID) throws SQLException {
        ContentValues cv = new ContentValues();
        cv.put(ITEM_DEL, 1);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{itemID});
    }

    public boolean removeAll() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }

}
