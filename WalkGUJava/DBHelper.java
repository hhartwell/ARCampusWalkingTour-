package harvey.com.walkgujava;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import harvey.com.walkgujava.Omeka;

/**
 * Created by Harvey on 2/3/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "omeka";
    static final String TABLE_OMEKA_TAGS = "omeka_tags";
    static final String TABLE_OMEKA_ID = "id";
    static final String TABLE_OMEKA_NAME = "name";



    static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE " + TABLE_OMEKA_TAGS + " (" +
                TABLE_OMEKA_ID + " int(10) unsigned NOT NULL AUTO_INCREMENT, " +
                " PRIMARY KEY (" + TABLE_OMEKA_ID  + "), UNIQUE KEY " +
                TABLE_OMEKA_NAME + "(" + TABLE_OMEKA_NAME + "))";
        // check your spelling check your spacing check yours parens
        //Log.d(TAG, "onCreate: " + sqlCreate);
        db.execSQL(sqlCreate);
        /*CREATE TABLE `omeka_tags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getSelectAllCursor() {
        // cursor used to navigate through query results
        // a table of records
        // SQL select statement to query our table
        // SELECT * FROM tableContacts
        // * means all columns
        String sqlSelectAll = "SELECT * FROM " + TABLE_OMEKA_TAGS;
        //Log.d(TAG, "getSelectAllContactsCursor: " + sqlSelectAll);
        // get reference to the database, read only
        SQLiteDatabase db = this.getReadableDatabase();
        // call rawQuery(), which returns a Cursor reference
        Cursor cursor = db.rawQuery(sqlSelectAll, null);

        // dont close the db, cursor needs it
        return cursor;
    }

    public List<Omeka> getSelectAllList() {
        // for debugging purposes only
        // get a cursor, navigate through all records and construct a list
        // of contacts
        // A list of contacts is something we are familiar
        List<Omeka> omeka = new ArrayList<>();
        // populate the list with records information
        Cursor cursor = getSelectAllCursor();
        // cursor does not start at the first record, there may not be a first record
        // we need move our cursor through the results table
        // relevant methods: moveToFirst(), moveToNext(), moveToLast()
        while (cursor.moveToNext()) { // returns false when there is no next
            // extract record info and package into an omeka object
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            // task: finish extracting info, create a contact, insert the contact into
            // list
            String phoneNumber = cursor.getString(2);
            int imageResourceId = cursor.getInt(3);
            Omeka historyFile = new Omeka(id, name);
            omeka.add(historyFile);
        }

        return omeka;
    }
}