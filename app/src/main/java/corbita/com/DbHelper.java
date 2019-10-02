package corbita.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TBL_USERS = "users",
            TBL_ID = "id",
            TBL_USERNAME = "username",
            TBL_PASSWORD = "password",
            TBL_FULLNAME = "fullname";

    SQLiteDatabase dbReadable = getReadableDatabase();
    SQLiteDatabase dbWritable = getWritableDatabase();


    public DbHelper(Context context) {
        super(context, "STIDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_create_users_table = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                TBL_USERS, TBL_ID, TBL_USERNAME, TBL_PASSWORD, TBL_FULLNAME);
        db.execSQL(sql_create_users_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int createUser(HashMap<String, String> map_user) {
        int userid = 0;
        String sql_check_username = String.format("SELECT * FROM %s WHERE %s = '%s'",
                TBL_USERS, TBL_USERNAME, map_user.get(TBL_USERNAME));
        Cursor cur = dbReadable.rawQuery(sql_check_username, null);
        if(cur.moveToNext()) {
            userid = cur.getInt(cur.getColumnIndex(TBL_ID));
        }
        else {
            // Insertion of data to database
            ContentValues val = new ContentValues();
            val.put(TBL_USERNAME, map_user.get(TBL_USERNAME));
            val.put(TBL_PASSWORD, map_user.get(TBL_PASSWORD));
            val.put(TBL_FULLNAME, map_user.get(TBL_FULLNAME));
            dbWritable.insert(TBL_USERS, null, val);
        }
        return userid;
    }

}
