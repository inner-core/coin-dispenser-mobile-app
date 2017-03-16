package ph.adaptdev.coindispenser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "coin_dispenser";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_1 = "api_host";
    private static final String TABLE_NAME_1_COLUMN_2 = "api_host";
    private static final String TABLE_NAME_2 = "port";
    private static final String TABLE_NAME_2_COLUMN_2 = "port";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(id INTEGER PRIMARY KEY,"
                + TABLE_NAME_1_COLUMN_2 + " TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(id INTEGER PRIMARY KEY,"
                + TABLE_NAME_2_COLUMN_2 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(sqLiteDatabase);
    }

    public long createApiHost(String apiHost){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_NAME_1_COLUMN_2, apiHost);
        return sqLiteDatabase.insert(TABLE_NAME_1, null, values);
    }

    public String readApiHost() {
        String apiHost = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_1, new String[] { "id",
                        TABLE_NAME_1_COLUMN_2}, "id = ?",
                new String[] { String.valueOf(1) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                apiHost = cursor.getString(1);
            }
            cursor.close();
        }
        return apiHost;
    }

    public int updateApiHost(String apiHost) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_NAME_1_COLUMN_2, apiHost);
        return sqLiteDatabase.update(TABLE_NAME_1, values, "id = ?",
                new String[] { String.valueOf(1) });
    }

    public long createPort(String port){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_NAME_2_COLUMN_2, port);
        return sqLiteDatabase.insert(TABLE_NAME_2, null, values);
    }

    public String readPort() {
        String port = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_2, new String[] { "id",
                        TABLE_NAME_2_COLUMN_2}, "id = ?",
                new String[] { String.valueOf(1) }, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                port = cursor.getString(1);
            }
            cursor.close();
        }
        return port;
    }

    public int updatePort(String port) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_NAME_2_COLUMN_2, port);
        return sqLiteDatabase.update(TABLE_NAME_2, values, "id = ?",
                new String[] { String.valueOf(1) });
    }

}
