package com.example.root.ngabensin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.root.ngabensin.Vechile.VechileItem;

import java.util.ArrayList;

/**
 * Created by sep on 13/11/17.
 */

public class SQLiteOperation {
    public static final String LOGTAG = "EMP_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_NAMA_KENDARAAN,
            SQLiteHelper.COLUMN_JENIS_KENDARAAN,
            SQLiteHelper.COLUMN_ICON_KENDARAAN
    };

    public SQLiteOperation(Context context){
        dbhandler = new SQLiteHelper(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
    }

    public VechileItem addVechile(VechileItem vechileItem){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NAMA_KENDARAAN,vechileItem.getNmkendaraan());
        values.put(SQLiteHelper.COLUMN_JENIS_KENDARAAN,vechileItem.getJnkendaraan());
        values.put(SQLiteHelper.COLUMN_ICON_KENDARAAN,vechileItem.getImage());
        long vechileid = database.insert(SQLiteHelper.TABLE_KENDARAAN_KUSTOM,null,values);
        vechileItem.setId(vechileid);
        return vechileItem;
    }

    public VechileItem getVechile(long id){
        Cursor cursor = database.query(SQLiteHelper.TABLE_KENDARAAN_KUSTOM,allColumns,SQLiteHelper.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        VechileItem vechileItem = new VechileItem();
        if (cursor.moveToFirst()) {
            vechileItem = new VechileItem(Long.parseLong(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3));
        }
        return vechileItem;
    }

    public ArrayList<VechileItem> getAllVechile(String orderby, String column) {
        Cursor cursor = null;
        if (orderby==null){
            cursor = database.query(SQLiteHelper.TABLE_KENDARAAN_KUSTOM, allColumns, null, null, null, null, null);
        }
        else {
            cursor = database.query(SQLiteHelper.TABLE_KENDARAAN_KUSTOM, allColumns, null, null, null, null, column + " " + orderby);
        }
        ArrayList<VechileItem> listvechile = new ArrayList<>();
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                VechileItem tugas = new VechileItem();
                tugas.setId(cursor.getLong(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)));
                tugas.setNmkendaraan(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_NAMA_KENDARAAN)));
                tugas.setJnkendaraan(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_JENIS_KENDARAAN)));
                tugas.setImage(cursor.getBlob(cursor.getColumnIndex(SQLiteHelper.COLUMN_ICON_KENDARAAN)));
                listvechile.add(tugas);
            }
        }
        return listvechile;
    }

    public String cekData(){
        String stat;
        Cursor cursor = database.query(SQLiteHelper.TABLE_KENDARAAN_KUSTOM,allColumns,null,null,null,null,null);
        if(cursor.getCount() > 0){
            stat = String.valueOf(cursor.getCount());
        }
        else {
            stat = "Ga ada";
        }
        return stat;
    }

    public int UpdateVechile(VechileItem vechileItem){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NAMA_KENDARAAN,vechileItem.getNmkendaraan());
        values.put(SQLiteHelper.COLUMN_JENIS_KENDARAAN,vechileItem.getJnkendaraan());
        values.put(SQLiteHelper.COLUMN_ICON_KENDARAAN,vechileItem.getImage());

        return database.update(SQLiteHelper.TABLE_KENDARAAN_KUSTOM,values,
                SQLiteHelper.COLUMN_ID + "=?",new String[]{String.valueOf(vechileItem.getId())});
    }

    public int removeTugas(Long id){
        return database.delete(SQLiteHelper.TABLE_KENDARAAN_KUSTOM,SQLiteHelper.COLUMN_ID+"="+id,null);
    }

}
