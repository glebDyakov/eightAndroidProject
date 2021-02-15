package gleb.first_app.myeightandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.lang.reflect.Array;

public class DataBase extends SQLiteOpenHelper {
    private static final String db_name = "itProgerApp";
    private static final int db_ver = 1;
    private static final String db_table = "tasks";
    private static final String db_column = "taskName

    @Override
    public void OnCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", db_table, db_column);
        db.execSQL(query);
    }

    @Override
    public void OnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s", db_table);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertData(String task) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(db_column,task);
        db.insertWithOnConflict(db_table,null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }
    public void deleteData(String task) {
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(db_table,db_column + "= ?", new String[]{task});
        db.close();

    }
    public ArrayList<String> getAllTasks() {
        ArrayList<String> all_tasks =new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cur =db.query(db_table, new String[]{db_column},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex(db_column);
            all_tasks.add(cursor.getString(index));

        }
        cursor.close();
        db.close();
        return all_tasks;

    }


}