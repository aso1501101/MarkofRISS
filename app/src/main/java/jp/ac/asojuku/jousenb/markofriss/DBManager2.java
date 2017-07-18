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
import static android.R.attr.yearListItemTextAppearance;


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
    public  String selectanswer(SQLiteDatabase db, String count,String year,String season) {
        String result = null;
        String select = "SELECT mondai_answer FROM question WHERE no = ? AND year = ? AND season = ?;";

        String aaa[];
        aaa = new String[3];
        aaa[0] = count;
        aaa[1] = year;
        aaa[2] = season;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //正解の記号を持ってくる
    public  String selectkigo(SQLiteDatabase db, String count,String year , String season) {
        String result = null;
        String select = "SELECT answer FROM question WHERE no = ? AND year = ? AND season = ?;";

        String aaa[];
        aaa = new String[3];
        aaa[0] = count;
        aaa[1] = year;
        aaa[2] = season;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //年代別問題表示
    public String selectcount(SQLiteDatabase db , String count ,String year,String season) {

        String result = null;
        String select = "SELECT * FROM question WHERE no = ? AND year = ? AND season = ?;";
        String aaa[];
        aaa = new String[3];
        aaa[0] = count;
        aaa[1] = year;
        aaa[2] = season;

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

    //不正解フラグ変更
    public void flgJ(SQLiteDatabase db, String path ,String year,String season){
        String deleteSql = "UPDATE question SET mondai_flg = 'J' WHERE no = ? AND year = ? AND season = ?;";
        String aaa[];
        aaa = new String[3];
        aaa[0] = path;
        aaa[1] = year;
        aaa[2] = season;
        db.execSQL(deleteSql,aaa);
    }
    //正解フラグ変更
    public void flgR(SQLiteDatabase db, String no ,String year,String season){
        String deleteSql = "UPDATE question SET mondai_flg = 'R' WHERE no = ? AND year = ? AND season = ?;";
        String aaa[];
        aaa = new String[3];
        aaa[0] = no;
        aaa[1] = year;
        aaa[2] = season;
        db.execSQL(deleteSql,aaa);
    }
    //不正解フラグ変更
    public void flgJ(SQLiteDatabase db, String path){
        String deleteSql = "UPDATE question SET mondai_flg = 'J' WHERE imgpath = ?;";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;
        db.execSQL(deleteSql,aaa);
    }
    //正解フラグ変更
    public void flgR(SQLiteDatabase db, String path){
        String deleteSql = "UPDATE question SET mondai_flg = 'R' WHERE imgpath = ?;";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;
        db.execSQL(deleteSql,aaa);
    }



    public SQLiteCursor miss(SQLiteDatabase db , String year , String season) {
        String select = "SELECT no || '問目' AS '_id' FROM question WHERE year = ? AND season = ? AND mondai_flg = 'J';";
        String aaa[];
        aaa = new String[2];
        aaa[0] = year;
        aaa[1] = season;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        return cursor;
    }
    /*public SQLiteCursor miss(SQLiteDatabase db) {
        String select = "SELECT no || '問目' AS '_id' FROM question;";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        return cursor;
    }*/





    //------path関連表示

    //path答え表示用
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

    //path正解の記号を持ってくる
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

    //path何問目表示用
    public  String pathno(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT no FROM question WHERE imgpath = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }

    //path季節表示用
    public  String pathseason(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT season FROM question WHERE imgpath = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }
    //path季節表示用
    public  String pathyear(SQLiteDatabase db, String id) {
        String result = null;
        String select = "SELECT year FROM question WHERE imgpath = ?";

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,new String[]{id});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(0);
        }
        cursor.close();
        return  result;
    }





/////etonのジャンル別用---------

    //pathジャンル別の正解カウント
    public void genrecountpath(SQLiteDatabase db, String path){
        String deleteSql = "UPDATE genre SET genre_seikai = genre_seikai + 1 , genre_count = genre_count + 1 WHERE genre_id = (select genre_id from question where imgpath = ?);";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;
        db.execSQL(deleteSql,aaa);
    }

    //pathジャンル別の不正解カウント
    public void genrecountJpath(SQLiteDatabase db, String path){
        String deleteSql = "UPDATE genre SET genre_count = genre_count + 1 WHERE genre_id = (select genre_id from question where imgpath = ?)";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;
        db.execSQL(deleteSql,aaa);
    }

    //ジャンル別の正解カウント
    public void genrecount(SQLiteDatabase db, String no ,String year , String season){
        String deleteSql = "UPDATE genre SET genre_seikai = genre_seikai + 1 , genre_count = genre_count + 1 WHERE genre_id = (select genre_id from question where no = ? AND year = ? AND season = ?);";
        String aaa[];
        aaa = new String[3];
        aaa[0] = no;
        aaa[1] = year;
        aaa[2] = season;
        db.execSQL(deleteSql,aaa);
    }

    //ジャンル別の不正解カウント
    public void genrecountJ(SQLiteDatabase db, String no ,String year,String season){
        String deleteSql = "UPDATE genre SET genre_count = genre_count + 1 WHERE genre_id = (select genre_id from question where no = ? AND year = ? AND season = ?)";
        String aaa[];
        aaa = new String[3];
        aaa[0] = no;
        aaa[1] = year;
        aaa[2] = season;
        db.execSQL(deleteSql,aaa);
    }

    public Genremodel genre1(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '1';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre2(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '2';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre3(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '3';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre4(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '4';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre5(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '5';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre6(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '6';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre7(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '7';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }
    public Genremodel genre8(SQLiteDatabase db) {
        Genremodel result = new Genremodel();
        String select = "SELECT * FROM genre WHERE genre_id = '8';";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result.set_Seikai(cursor.getInt(2));
            result.set_Count(cursor.getInt(3));
        }
        cursor.close();
        return result;
    }

    //uema

    public void insertuema(SQLiteDatabase db, String path){
        String deleteSql = "INSERT INTO `uema`(`id`)\n" +
                "select mondai_id\n" +
                "from question\n" +
                "where imgpath = ?;";
        String aaa[];
        aaa = new String[1];
        aaa[0] = path;
        db.execSQL(deleteSql,aaa);
    }

    public void insertuema(SQLiteDatabase db, String no,String year ,String season){
        String deleteSql = "INSERT INTO uema(mondai_id) select mondai_id from question where no = ? AND year = ? AND season = ?;";
        String aaa[];
        aaa = new String[3];
        aaa[0] = no;
        aaa[1] = year;
        aaa[2] = season;
        db.execSQL(deleteSql,aaa);
    }

    public SQLiteCursor missuema(SQLiteDatabase db) {
        String select = "SELECT * FROM uema;";
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,null);
        return cursor;
    }
    public void goodbyuema(SQLiteDatabase db) {
        String select = "DELETE FROM uema;";
        db.execSQL(select);
    }

    public String whatuema(SQLiteDatabase db , String _id) {

        String result = null;
        String select = "SELECT mondai_id FROM uema WHERE _id = ?;";
        String aaa[];
        aaa = new String[1];
        aaa[0] = _id;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(0);
        }
        cursor.close();
        return result;
    }


    public Mondaimodel questionuema(SQLiteDatabase db , String _id) {

        Mondaimodel result = new Mondaimodel();
        String select = "SELECT * FROM question WHERE mondai_id = ? ;";
        String aaa[];
        aaa = new String[1];
        aaa[0] = _id;

        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(select,aaa);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            result.set_Path(cursor.getString(6));
            result.set_Ans(cursor.getString(4));
            result.set_Ansk(cursor.getString(5));
            result.set_id(cursor.getString(0));

        }
        cursor.close();
        return result;
    }

    //リセット
    public void reset(SQLiteDatabase db){
        String deleteSql = "update genre set genre_seikai = 0,genre_count = 0;";
        db.execSQL(deleteSql);
    }

}
