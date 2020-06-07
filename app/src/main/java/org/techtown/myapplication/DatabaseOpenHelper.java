package org.techtown.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String ROOT_DIR = "/data/data/org.techtown.myapplication/databases/";  //로컬db 저장
    private static final String DATABASE_NAME = "Holiday.db"; //로컬db명
    private static final int SCHEMA_VERSION = 1; //로컬db 버전

    public DatabaseOpenHelper(Context context)    {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        setDB(context); // setDB에 context 부여
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void setDB(Context ctx) {

        File folder = new File(ROOT_DIR);
        if(folder.exists()) {
        } else {
            folder.mkdirs();
        }

        AssetManager assetManager = ctx.getResources().getAssets();       //ctx가 없다.
        File outfile = new File(ROOT_DIR+"Holidat.db");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("Holidat.db", AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            if (outfile.length() <= 0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } else {}
        } catch (IOException e) {}
    }

    class ProductDBHelper extends SQLiteOpenHelper{  //새로 생성한 adapter 속성은 SQLiteOpenHelper이다.
        public ProductDBHelper(Context context) {
            super(context, "Holiday.db", null, 1);    // db명과 버전만 정의 한다.
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        }
    }
}