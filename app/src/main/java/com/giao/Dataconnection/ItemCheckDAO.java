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
        String query = "SELECT itemID,date,quantity FROM ItemCheck";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<ItemCheck> list = new ArrayList<ItemCheck>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            int itemID=Integer.parseInt(cur.getString(0));
            String date=cur.getString(1);
            long quantity= Long.parseLong(cur.getString(2));
            ItemCheck record = new ItemCheck(itemID,date,quantity);
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

    public boolean remove(String itemID, String date) throws SQLException {
        return database.delete(DATABASE_TABLE, KEY_ROWID + "=AND " + ITEMCHECK_DATE + " = ?", new String[]{itemID,date}) >0;
    }

    public boolean removeAll() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }
}
