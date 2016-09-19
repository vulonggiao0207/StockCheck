package com.giao.Dataconnection;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Long on 2/13/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="StockDB";
    private static final String TABLE_ITEMLIST="ItemList";
    private static final String TABLE_ITEM="Item";
    private static final String TABLE_ITEMCHECK="ItemCheck";

    //Tables ItemList columns
    public static final String ListName="ListName";
    //Category Item columns
    public static final String ItemID="ItemID";
    public static final String Item_ListName="ListName";
    public static final String ItemName="ItemName";
    public static final String ItemUnit="Unit";
    public static final String ItemDelete="Del";
    //Order ItemCheck columns
    public static final String ItemCheck_ItemID="ItemID";
    public static final String ItemCheck_Date="Date";
    public static final String ItemCheck_Quantity="Quantity";

    private static final int DATABASE_VERSION=1;

    private static Context context;
    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String CreateTABLE_TABLES="CREATE TABLE IF NOT EXISTS "+TABLE_ITEMLIST+" ("
                + ListName+ " TEXT PRIMARY KEY NOT NULL "
                +");";
        String CreateTABLE_CATEGORY="CREATE TABLE IF NOT EXISTS "+TABLE_ITEM+" ("
                + ItemID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Item_ListName+ " TEXT NOT NULL, "
                + ItemName+ " TEXT NOT NULL, "
                + ItemUnit+ " TEXT NOT NULL, "
                + ItemDelete+ " INTEGER NOT NULL "
                +");";
        String CreateTABLE_ORDER=//"CREATE TABLE IF NOT EXISTS Orders(OrderID INTEGER PRIMARY KEY AUTOINCREMENT, TableName TEXT NOT NULL, OrderDate NUMERIC NOT NULL, NumberOfCustomer INTEGER, OrderNote TEXT, OrderPaid REAL)";
                "CREATE TABLE IF NOT EXISTS "+TABLE_ITEMCHECK+ " ("
                +ItemCheck_ItemID+" INTEGER NOT NULL, "
                + ItemCheck_Date+ " NUMERIC NOT NULL, "
                + ItemCheck_Quantity+ " NUMERIC NOT NULL, "
                +"PRIMARY KEY("+ItemCheck_ItemID+","+ItemCheck_Date+")"
                +");";
        db.execSQL(CreateTABLE_TABLES);
        db.execSQL(CreateTABLE_CATEGORY);
        db.execSQL(CreateTABLE_ORDER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //Log.w(TableDAO.class.getName(), "Upgrading database from old to new version...");
        db.execSQL("DROP TABLE IF EXISTs " + TABLE_ITEMLIST);
        db.execSQL("DROP TABLE IF EXISTs " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTs " + TABLE_ITEMCHECK);
        onCreate(db);
    }

}