package itp.com.aqeelmirza_searchaddress.Utils;

/**
 * Created by Mirza on 04-Sep-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_Helper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database_1";

    // Contacts table name
    private static final String TABLE_DATA = "list_data_1";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "addressName";
    private static final String KEY_LAT = "addressLatitude";
    private static final String KEY_LON = "addressLongitude";
    private static final String KEY_PICKUP = "pickup";


    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_LAT + " TEXT,"
                + KEY_LON + " TEXT,"
                + KEY_PICKUP + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addContact(List_DataModel items) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //  values.put(KEY_ID, contact.getId());
        values.put(KEY_NAME, items.getAddressName());
        values.put(KEY_LAT, items.getAddressLatitude());
        values.put(KEY_LON, items.addressLongitude);
        values.put(KEY_PICKUP, items.getPickup());

        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public List_DataModel getitem(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATA, new String[]{KEY_NAME, KEY_LAT,
                        KEY_LON, KEY_PICKUP,}, KEY_NAME + "=?",

                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        List_DataModel contact = new List_DataModel(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public ArrayList<List_DataModel> getAllItems() {
        ArrayList<List_DataModel> contactList = new ArrayList<List_DataModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                List_DataModel items = new List_DataModel();
                items.setId(Integer.parseInt(cursor.getString(0)));
                items.setAddressName(cursor.getString(1));
                items.setAddressLatitude(cursor.getString(2));
                items.setAddressLongitude(cursor.getString(3));
                items.setPickup(cursor.getString(4));

                // Adding contact to list
                contactList.add(items);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(List_DataModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_NAME + " = ?",
                new String[]{contact.getAddressName()});
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_DATA);

    }
}
