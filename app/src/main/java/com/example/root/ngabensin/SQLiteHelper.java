package com.example.root.ngabensin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sep on 06/11/17.
 */


public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ngabensin.db";
    private static final int DATABASE_VERSION = 8;

    public static final String TABLE_KENDARAAN_KUSTOM = "kendaraankustom";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_KENDARAAN = "namakendaraan";
    public static final String COLUMN_JENIS_KENDARAAN = "jeniskendaraan";
    public static final String COLUMN_ICON_KENDARAAN = "iconkendaraan";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_KENDARAAN_KUSTOM + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA_KENDARAAN + " TEXT, " +
                    COLUMN_JENIS_KENDARAAN + " TEXT, " +
                    COLUMN_ICON_KENDARAAN + " BLOB " +
                    ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KENDARAAN_KUSTOM);
        db.execSQL(TABLE_CREATE);
    }




    public SQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

//    public void insertData(String nama_kendaraan, String jenis_kendaraan, byte[] gambar){
//        SQLiteDatabase database = getWritableDatabase();
//        String sql = "INSERT INTO T_KENDARAAN_CUSTOM VALUES (NULL, ?, ?, ?)";
//
//        SQLiteStatement statement = database.compileStatement(sql);
//        statement.clearBindings();
//
//        statement.bindString(1, nama_kendaraan);
//        statement.bindString(2, jenis_kendaraan);
//        statement.bindBlob(3, gambar);
//
//        statement.executeInsert();
//    }
//
//    public Cursor getdata(String sql){
//        SQLiteDatabase database = getReadableDatabase();
//        return database.rawQuery(sql, null);
//    }
//    public Cursor getAllData(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor result = db.rawQuery("SELECT * FROM T_KENDARAAN_CUSTOM", null);
//        return result;
//    }


}
