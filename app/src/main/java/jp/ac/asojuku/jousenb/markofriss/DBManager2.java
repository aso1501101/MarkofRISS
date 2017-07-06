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
import java.util.ArrayList;

import static android.R.attr.path;


/**
 * Created by user on 2017/06/12.
 */

public class DBManager2 extends SQLiteOpenHelper {

    private static final String DB_FILE_NAME = "sample.sqlite3";
    private static final String DB_NAME = "realhide2.sqlite3";
    private static final int DB_VERSION = 3;

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

    //答え表示用
    public  String selectanswer(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT mondai_answer FROM question WHERE no = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //正解の記号を持ってくる
    public  String selectkigo(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT answer FROM question WHERE no = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //年代別問題表示
    public String selectcount(SQLiteDatabase db , String count) {

        String result = null;
        String select = "SELECT * FROM question WHERE no = ? ;";
        String aaa[];
        aaa = new String[1];
        aaa[0] = count;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(6);
        }
        cursor.close();
        return result;
    }

    //ジャンル問題表示
    public String selectgenre(SQLiteDatabase db , String genre) {

        String result = null;
        String select = "SELECT * FROM question WHERE genre_id = ? ORDER BY RANDOM();";
        String aaa[];
        aaa = new String[1];
        aaa[0] = genre;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(6);
        }
        cursor.close();
        return result;
    }

    //真・履歴問題表示
    public String selectrireki(SQLiteDatabase db,String year) {

        String result = null;
        String select = "SELECT * FROM question WHERE year = ? AND mondai_flg = 'J' ORDER BY RANDOM();";
        String aaa[];
        aaa = new String[1];
        aaa[0] = year;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(6);
        }
        cursor.close();
        return result;
    }

    //真・履歴問題表示改
    /*public Mondaimodel selectrireki2(SQLiteDatabase db , String year) {

        Mondaimodel result = new Mondaimodel();
        String select = "SELECT * FROM question WHERE year = ? AND mondai_flg = 'J' ORDER BY RANDOM();";
        String aaa[];
        aaa = new String[1];
        aaa[0] = year;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result.set_id(cursor.getString(0));
            result.set_Ans(cursor.getString(5));
            result.set_Ansk(cursor.getString(4));
            result.set_Path(cursor.getString(6));

        }
        cursor.close();
        return result;
    }*/

    //真・履歴問題表示
    /*public String selectrireki3(SQLiteDatabase db , String path) {

        String result = null;
        String select = "SELECT * FROM question WHERE year = ? AND mondai_flg = 'J' ORDER BY RANDOM();";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(6);
        }
        cursor.close();
        return result;
    }*/

    //不正解フラグ変更
    public void flgJx(SQLiteDatabase db, String path ,String year){
        String deleteSql = "UPDATE question SET mondai_flg = 'J' WHERE mondai_id = ?";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;
        aaa[1] = "29";
        db.execSQL(deleteSql,aaa);
    }
    //不正解フラグ変更
    public void flgJ(SQLiteDatabase db, String path ,String year){
        String deleteSql = "UPDATE question SET mondai_flg = 'J' WHERE no = ? AND year = ?";
        String aaa[];
        aaa = new String[2];
        aaa[0] = path;
        aaa[1] = "29";
        db.execSQL(deleteSql,aaa);
    }
    //正解フラグ変更
    public void flgR(SQLiteDatabase db, String path ,String year){
        String deleteSql = "UPDATE question SET mondai_flg = 'R' WHERE no = ? AND year = ?";
        String aaa[];
        aaa = new String[2];
        aaa[0] = path;
        aaa[1] = "29";
        db.execSQL(deleteSql,aaa);
    }
    //正解フラグ変更
    public void flgRx(SQLiteDatabase db, int id){
        String deleteSql = "UPDATE question SET mondai_flg = 'R' WHERE mondai_id = ?";
        db.execSQL(deleteSql,new String[]{String.valueOf(id)});
    }
    //不正解問題表示
    public String huseikai(SQLiteDatabase db , String year) {
        String result = null;
        String select = "SELECT * FROM question WHERE year = ? AND mondai_flg = 'J';";
        String aaa[];
        aaa = new String[1];
        aaa[0] = year;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(6);
        }
        cursor.close();
        return result;
    }

    //答え表示用
    public  String pathanswer(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT mondai_answer FROM question WHERE imgpath = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //正解の記号を持ってくる
    public  String pathkigo(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT answer FROM question WHERE imgpath = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //ジャンル別の正解カウント
    public void genrecount(SQLiteDatabase db, String ans){
        String deleteSql = "UPDATE genre SET genre_seikai = genre_seikai + 1 , genre_count = genre_count + 1 WHERE genre_id = (select genre_id from question where mondai_answer =? )";
        db.execSQL(deleteSql,new String[]{String.valueOf(ans)});
    }

    //ジャンル別の不正解カウント
    public void genrecountJ(SQLiteDatabase db, String ans){
        String deleteSql = "UPDATE genre SET genre_count = genre_count + 1 WHERE genre_id = (select genre_id from question where mondai_answer = ?)";
        db.execSQL(deleteSql,new String[]{String.valueOf(ans)});
    }
}
