package com.example.shadow.foodfast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shadow.foodfast.Models.OrdersModel;

import java.util.ArrayList;

/*
 * @Abhishek Goyal
 *  One of my most important Java Class to handle the data in database and perform CRUD Operations
 */

public class DBHelper extends SQLiteOpenHelper
{

    final static String DBNAME="mydatabase.db";
    final static int DBVERSION=8;

    public DBHelper(@Nullable Context context)
    {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /*

        id 0 int
        name 1
        phone 2
        price 3 int
        image 4 int
        quantity 5
        description 6
        foodname 7

         */
        sqLiteDatabase.execSQL
                (
                        "create table orders"+
                                "(id integer primary key autoincrement,"+
                                "name text,"+
                                "phone text,"+
                                "price integer,"+
                                "image integer,"+
                                "quantity text,"+
                                "description text,"+
                                "foodname text)"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name,String phone,int price,int image,int quan,String desc,String foodName)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quan);
        values.put("description",desc);
        values.put("foodname",foodName);
        long id=database.insert("orders",null,values);
        if(id<=0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders()
    {
        ArrayList<OrdersModel> orders=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext())
            {
                OrdersModel model=new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select * from orders where id ="+id,null);
        if(cursor !=null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String name,String phone,int price,int image,int quan,String desc,String foodName,int id)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quan);
        values.put("description",desc);
        values.put("foodname",foodName);
        long row=database.update("orders",values,"id="+id,null);
        if(row<=0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int deleteOrder(String id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }
}

