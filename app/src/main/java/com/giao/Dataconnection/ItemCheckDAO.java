package com.giao.Dataconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.giao.Model.Item;
import com.giao.Model.ItemCheck;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class ItemCheckDAO {
    private static final String KEY_ROWID = "ItemID";
    private static final String ITEMCHECK_DATE="Date";
    private static final String ITEMCHECK_NAME="Quantity";
    private static final String DATABASE_TABLE = "ItemCheck";
    private static DatabaseHelper databaseHelper;
    //
    public final Context context;
    private SQLiteDatabase database;

    public ItemCheckDAO(Context context) {
        this.context = context;
        databaseHelper= new DatabaseHelper(context);
        // TODO Auto-generated constructor stub
    }

    public ItemCheckDAO open() throws SQLException {
        database = databaseHelper.getReadableDatabase();
        return this;
    }

    public void close() throws SQLException {
        databaseHelper.close();
    }
    public ArrayList<ItemCheck> select() throws SQLException {
        String query = "SELECT Item.itemID,ItemCheck.date,ItemCheck.quantity FROM ItemCheck, Item ";
        query+= " WHERE ItemCheck.itemID=ITem.itemID";
        query+=" ORDER BY ItemCheck.date DESC";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<ItemCheck> list = new ArrayList<ItemCheck>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            int itemID=Integer.parseInt(cur.getString(0));
            String date=cur.getString(1);
            BigDecimal quantity= new BigDecimal(cur.getString(2));
            //Create new Itemcheck
            ItemCheck record = new ItemCheck(itemID,date,quantity);
            list.add(record);
        }
        cur.close();
        return list;
    }

    public ArrayList<ItemCheck> select(String listName) throws SQLException {
        String query = "SELECT distinct(ItemCheck.date) FROM ItemCheck, Item";
        query+= " WHERE ItemCheck.itemID=ITem.itemID";
        query+=" AND Item.ListName='"+listName+"'";
        query+=" ORDER BY ItemCheck.date DESC";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<ItemCheck> list = new ArrayList<ItemCheck>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            int itemID=0;
            String date=cur.getString(0);
            BigDecimal quantity= new BigDecimal(0);
            //Create new Itemcheck
            ItemCheck record = new ItemCheck(itemID,date,quantity);
            //Add to ItemCheck List
            list.add(record);
        }
        cur.close();
        return list;
    }

    public ArrayList<ItemCheck> select(String listName,String selectedDate) throws SQLException {
        String query = "SELECT Item.itemID,ItemCheck.date,ItemCheck.quantity FROM ItemCheck, Item";
        query+= " WHERE ItemCheck.itemID=ITem.itemID";
        query+= " AND ItemCheck.date='"+selectedDate+"'";
        query+=" AND Item.ListName='"+listName+"'";
        query+=" ORDER BY itemName";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<ItemCheck> list = new ArrayList<ItemCheck>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            int itemID=Integer.parseInt(cur.getString(0));
            String date=cur.getString(1);
            BigDecimal quantity= new BigDecimal(cur.getString(2));
            //get parent Item
            ItemDAO itemDAO= new ItemDAO(context);
            itemDAO.open();
            Item item= itemDAO.selectItem(cur.getString(0));
            itemDAO.close();
            //Create new Itemcheck
            ItemCheck record = new ItemCheck(item,itemID,date,quantity);
            //Add to ItemCheck List
            list.add(record);
        }
        cur.close();
        return list;
    }
    public long create(String itemID,String date,String quantity) throws SQLException {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,itemID);
        cv.put(ITEMCHECK_DATE,date);
        cv.put(ITEMCHECK_NAME,quantity);
        return database.insert(DATABASE_TABLE, null, cv);
    }

    public long update(String itemID,String date,String quantity) throws SQLException
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,itemID);
        cv.put(ITEMCHECK_DATE,date);
        cv.put(ITEMCHECK_NAME,quantity);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=AND " + ITEMCHECK_DATE + " = ?", new String[]{itemID,date});
    }

    public boolean delete(String itemID, String date) throws SQLException {
        return database.delete(DATABASE_TABLE, KEY_ROWID + "=AND " + ITEMCHECK_DATE + " = ?", new String[]{itemID,date}) >0;
    }

    public boolean delete() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }
}
