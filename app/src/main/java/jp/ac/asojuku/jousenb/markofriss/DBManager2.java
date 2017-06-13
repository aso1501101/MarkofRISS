package jp.ac.asojuku.jousenb.markofriss;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 2017/06/12.
 */

public class DBManager2 extends SQLiteOpenHelper {

    private static final String DB_FILE_NAME = "sample.sqlite3";
    private static final String DB_NAME = "realhide2.sqlite3";
    private static final int DB_VERSION = 1;

    private Context context;
    private File dbPath;
    private boolean createDatabase = false;

    public DBManager2(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(DB_NAME);
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase database = super.getReadableDatabase();
        if (createDatabase) {
            try {
                database = copyDatabase(database);
            } catch (IOException e) {
            }
        }
        return database;
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase database = super.getWritableDatabase();
        if (createDatabase) {
            try {
                database = copyDatabase(database);
            } catch (IOException e) {
            }
        }
        return database;
    }

    private SQLiteDatabase copyDatabase(SQLiteDatabase database) throws IOException {
        // dbがひらきっぱなしなので、書き換えできるように閉じる
        database.close();

        // コピー！
        InputStream input = context.getAssets().open(DB_FILE_NAME);
        OutputStream output = new FileOutputStream(this.dbPath);
        copy(input, output);

        createDatabase = false;
        // dbを閉じたので、また開く
        return super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onOpen(db);
        // getWritableDatabase()したときに呼ばれてくるので、
        // 初期化する必要があることを保存する
        this.createDatabase = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // CopyUtilsからのコピペ
    private static int copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024 * 4];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }


    public void insertHitokoto(SQLiteDatabase db, String inputMessage) {
        String sql = "INSERT INTO hitokoto(phrase) VALUES(?)";

        db.execSQL(sql, new String[]{inputMessage});
    }

    public String selectHitokotoRandom(SQLiteDatabase db) {
        String result = null;
        String select = "SELECT * FROM genre ORDER BY RANDOM()";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select, null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(1);
        }
        cursor.close();
        return result;
    }

    public SQLiteCursor selectHitokotoList(SQLiteDatabase db) {
        String selectSql = "SELECT * FROM genre ORDER BY _id";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(selectSql, null);
        return cursor;
    }

    public void deleteHitokoto(SQLiteDatabase db, int id) {
        String deleteSql = "DELETE FROM hitokoto WHERE _id = ?";
        db.execSQL(deleteSql, new String[]{String.valueOf(id)});
    }
}
