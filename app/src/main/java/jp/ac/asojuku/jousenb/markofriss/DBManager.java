package jp.ac.asojuku.jousenb.markofriss;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2017/06/12.
 */

public class DBManager extends SQLiteOpenHelper{

    public DBManager(Context context) {
        super(context, "realhide.sqlite3", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS genre ( `genre_id` TEXT, `genre_name` TEXT, `genre_seikai` INTEGER, `genre_count` INTEGER, PRIMARY KEY(`genre_id`) )");
        db.execSQL("CREATE TABLE IF NOT EXISTS question ( `mondai_id` TEXT, `year` TEXT, `season` TEXT, `genre_id` TEXT, `mondai_answer` TEXT, `answer` TEXT, `imgpath` TEXT, PRIMARY KEY(`mondai_id`) )");
        db.execSQL("CREATE TABLE IF NOT EXISTS rireki ( `mondai_id` TEXT, `monda_flg` TEXT, PRIMARY KEY(`mondai_id`) )");
        db.execSQL("INSERT INTO `genre` VALUES ('1','ｾｷｭﾘﾃｨ',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('2','ｾｷｭﾘﾃｨ管理',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('3','セキュリティ技術評価',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('4','情報セキュリティ対策',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('5','セキュリティ実装技術',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('6','DBネットワーク',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('7','システムソフトウェア',0,0)");
        db.execSQL("INSERT INTO `genre` VALUES ('8','マネジメントシステム監査',0,0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE genre");
        db.execSQL("DROP TABLE question");
        db.execSQL("DROP TABLE rireki");
        onCreate(db);
    }

    public void insertHitokoto(SQLiteDatabase db, String inputMessage){
        String sql = "INSERT INTO hitokoto(phrase) VALUES(?)";

        db.execSQL(sql, new String[]{inputMessage});
    }

    public String selectHitokotoRandom(SQLiteDatabase db){
        String result = null;
        String select ="SELECT * FROM genre ORDER BY RANDOM()";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select, null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();

            result = cursor.getString(1);
        }
        cursor.close();
        return result;
    }

    public SQLiteCursor selectHitokotoList(SQLiteDatabase db){
        String selectSql = "SELECT * FROM genre ORDER BY _id";
        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(selectSql,null);
        return cursor;
    }

    public void deleteHitokoto(SQLiteDatabase db,int id){
        String deleteSql = "DELETE FROM hitokoto WHERE _id = ?";
        db.execSQL(deleteSql,new String[]{String.valueOf(id)});
    }
}
