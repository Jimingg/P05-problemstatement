package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "Note.db";
	private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_Singer = "singer";
    private static final String COLUMN_Year = "year";
    private static final String COLUMN_star = "star";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override 
	public void onCreate(SQLiteDatabase db) {
		//TODO CREATE TABLE Note
        String createTableSql = "CREATE TABLE " + TABLE_SONG +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_Singer + " TEXT,"
                + COLUMN_Year + " TEXT,"
                + COLUMN_star + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
		onCreate(db);
	}

	public void insertNote(String title, String singers, String year, int stars) {
		//TODO insert the data into the database
		SQLiteDatabase db = this.getWritableDatabase();
		// We use ContentValues object to store the values for
		//  the db operation
		ContentValues values = new ContentValues();
		// Store the column name as key and the description as value
		values.put(COLUMN_TITLE, title);
		values.put(COLUMN_Singer, singers);
		values.put(COLUMN_Year, year);
		values.put(COLUMN_star, stars);
		// Insert the row into the TABLE_TASK
		db.insert(TABLE_SONG, null, values);
		db.close();
	}

	public ArrayList<Song> getAllSongs() {
		//TODO return records in Java objects
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_TITLE + ", "
                + COLUMN_star
                + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song obj = new Song(id, title, singer, year, star);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
	}

    public ArrayList<String> getNoteContent() {
        //TODO return records in Strings

		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = " SELECT " + COLUMN_TITLE + " FROM " + TABLE_SONG;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {

                notes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}
