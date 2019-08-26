package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.view.View;

public class MyDbHandllers  extends  SQLiteOpenHelper{

    private  static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="product.db";
    public static final  String TABLE_PRODUCTS = "products";

    public  static  final  String  COLUMN_ID ="_id";
    public  static  final  String  COLUMN_PRODUCTNAME ="_productname";

    public MyDbHandllers(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE" + TABLE_PRODUCTS + "(" + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREAMENT  " + COLUMN_PRODUCTNAME + " TEXT " + ");";

        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_PRODUCTS );
        onCreate(db);


    }

    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }

    // delete the products from database

    public void deleteProduct(String productName){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_PRODUCTS + " WHERE "+ COLUMN_PRODUCTNAME + "=\"" + productName+"\";" );


    }

    // print out the database as string

    public  String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 " ;

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_productname"))!=null){
                dbString += c.getString(c.getColumnIndex("_productname"));
                dbString += "\n";


            }

        }
        db.close();
        return dbString;

    }





}
