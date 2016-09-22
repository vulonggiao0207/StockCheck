package com.giao.Dataconnection;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.giao.Model.ItemList;

import java.util.ArrayList;

/**
 * Created by Long on 9/20/2016.
 */
public class ItemListDAO {
    private static final String KEY_ROWID = "ListName";
    private static final String DATABASE_TABLE = "ItemList";
    private static DatabaseHelper databaseHelper;
    //
    public final Context context;
    private SQLiteDatabase database;

    public ItemListDAO(Context context) {
        this.context = context;
        databaseHelper= new DatabaseHelper(context);
        // TODO Auto-generated constructor stub
    }

    public ItemListDAO open() throws SQLException {
        database = databaseHelper.getReadableDatabase();
        return this;
    }

    public void close() throws SQLException {
        databaseHelper.close();
    }

    public ArrayList<ItemList> select() throws SQLException {
        String query = "SELECT ListName FROM ItemList ORDER BY ListName";
        Cursor cur = database.rawQuery(query, null);
        ArrayList<ItemList> list = new ArrayList<ItemList>();
        int iRow = cur.getColumnIndex(KEY_ROWID);
        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            String itemListName;
            itemListName=cur.getString(0);
            ItemList record = new ItemList(itemListName);
            list.add(record);
        }
        cur.close();
        return list;
    }

    public long create(String itemListName) throws SQLException {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID, itemListName);
        return database.insert(DATABASE_TABLE, null, cv);
    }

    public long update(String itemListName) throws SQLException
    {
        ContentValues cv = new ContentValues();
        if (itemListName != null)
            cv.put(KEY_ROWID, itemListName);
        return database.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{itemListName});
    }

    public boolean delete(String itemListName) throws SQLException {
        return database.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String[]{itemListName}) > 0;
    }

    public boolean delete() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }

}
