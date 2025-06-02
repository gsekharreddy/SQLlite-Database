package com.example.sqllitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "StudentData";

	private static final int DATABASE_VERSION = 1;

	private static final String STUDENT_NAME = "name";
	private static final String STUDENT_QUALIFICATION = "qualification";
	private static final String STUDENT_DOB = "date";

	private static final String ROW_ID = "id";


	private static final String TABLE_NAME = "student";


	public DataBaseHelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + TABLE_NAME + " ("
				+ ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ STUDENT_NAME + " TEXT,"
				+ STUDENT_DOB + " TEXT,"
				+ STUDENT_QUALIFICATION + " TEXT)";

		// at last we are calling a exec sql
		// method to execute above sql query
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void addStudentData(String name, String date, String qualification) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(STUDENT_NAME, name);
		values.put(STUDENT_DOB, date);
		values.put(STUDENT_QUALIFICATION, qualification);
		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	public ArrayList<StudentData> fetchStudentData() {
		ArrayList<StudentData> studentDataArrayList = new ArrayList<>();

		SQLiteDatabase db = null;
		Cursor cursor = null;

		try {
			db = this.getReadableDatabase();
			cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

			if (cursor != null && cursor.moveToFirst()) {
				do {
					String name = cursor.getString(1);
					String phone = cursor.getString(2);
					String address = cursor.getString(3);

					// Optional: check for null in individual columns
					if (name != null && phone != null && address != null) {
						studentDataArrayList.add(new StudentData(name, phone, address));
					}
				} while (cursor.moveToNext());
			} else {
				// Optionally log or toast when no records found
				Log.d("DB_FETCH", "No data found in the table.");
				// Toast.makeText(context, "No records found", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Log.e("DB_FETCH_ERROR", "Error while fetching data: " + e.getMessage());
			// Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
		} finally {
			if (cursor != null) cursor.close();
			if (db != null) db.close();
		}

		return studentDataArrayList;
	}

	public void updateStudentData(String name,String new_name,String new_dob, String new_qual){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(STUDENT_NAME, new_name);
		values.put(STUDENT_DOB, new_dob);
		values.put(STUDENT_QUALIFICATION, new_qual);
		db.update(TABLE_NAME, values, "name=?", new String[]{name});
		db.close();
	}

	public void deleteStudentData(String name){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, "name=?", new String[]{name});
		db.close();
	}
}
