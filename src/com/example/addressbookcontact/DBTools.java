package com.example.addressbookcontact;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper{

	public DBTools (Context applicationcontext) {
		//call or create the database
		super(applicationcontext, "contactbook.db", null, 1);
	}
	
	//is called the first time the database is created
	@Override
	public void onCreate(SQLiteDatabase db) {
		//create a table
		String query ="CREATE TABLE contacts (" +
				"contactId INTEGER PRIMARY KEY, " +
				"firstName TEXT, " +
				"lastName TEXT" +
				"phoneNumber TEXT" +
				"emailAddress TEXT" +
				"homeAddress TEXT)";
		//execute the query as long it does not return any data
		db.execSQL(query);
	}
	//drop tables, add tables, do anything else it needs to upgrade
	//drop the table and then make new table
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String query ="DROP TABLE IF EXISTS contacts";
		db.execSQL(query);
		onCreate(db);
	}
	
	public void insertContact(HashMap<String, String> queryValues){
		//open database for reading and writing
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("phoneNumber", queryValues.get("phoneNumber"));
		values.put("emailAddress", queryValues.get("emailAddress"));
		values.put("homeAddress", queryValues.get("homeAddress"));
		
		database.insert("contacts", null, values);
		
		database.close();
	}
	
	public int updateContact(HashMap<String, String> queryValues){
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("phoneNumber", queryValues.get("phoneNumber"));
		values.put("emailAddress", queryValues.get("emailAddress"));
		values.put("homeAddress", queryValues.get("homeAddress"));
		
		String[] contactIdValue = new String[] {queryValues.get("contactId")};
		
		return database.update("contacts", values, "contactId = ?", contactIdValue);
		
	}
	//delete contact with a matching contactId
	public void deleteContact(String id){
		SQLiteDatabase database = this.getWritableDatabase();
		String deleteQuery ="DELETE FROM contacts where contactId=" + id+ "'";
		
		database.execSQL(deleteQuery);
	}
	
	//delete contact with a matching firstName
		public void deleteContactfirstName(String firstName){
			SQLiteDatabase database = this.getWritableDatabase();
			String deleteQuery ="DELETE FROM contacts where firstName=" + firstName+ "'";
			
			database.execSQL(deleteQuery);
		}
		
	//delete contact with a matching lastName
		public void deleteContactlastName(String lastName){
			SQLiteDatabase database = this.getWritableDatabase();
			String deleteQuery ="DELETE FROM contacts where firstName=" + lastName+ "'";
					
			database.execSQL(deleteQuery);
		}
		
	//get AllContacts
		public ArrayList<HashMap<String, String>> getAllContacts(){
			ArrayList<HashMap<String,String>> contactArrayList = new ArrayList<HashMap<String, String>>();
			
			String query="SELECT * from contacts ORDER BY lastName";
			
			SQLiteDatabase database = this.getWritableDatabase();
			
			Cursor cursor = database.rawQuery(query, null);
			
			if(cursor.moveToFirst()){
				do{
					HashMap<String, String> contactMap= new HashMap<String, String>();
					
					contactMap.put("contactId", cursor.getString(0));
					contactMap.put("firstName", cursor.getString(1));
					contactMap.put("lastName", cursor.getString(2));
					contactMap.put("phoneNumber", cursor.getString(3));
					contactMap.put("emailAddress", cursor.getString(4));
					contactMap.put("homeAddress", cursor.getString(5));
					
					contactArrayList.add(contactMap);
					
					
				}while(cursor.moveToNext());
			}
			
			return contactArrayList;
			
		}
		
public HashMap<String, String> getContactInfo(String id){
	HashMap<String, String> contactMap= new HashMap<String, String>();
	
	SQLiteDatabase database = this.getReadableDatabase();
	String query ="SELECT * FROM contacts where contactId='" + id+"'";
	Cursor cursor = database.rawQuery(query, null);
	if(cursor.moveToFirst()){
		do{
			contactMap.put("firstName", cursor.getString(1));
			contactMap.put("lastName", cursor.getString(2));
			contactMap.put("phoneNumber", cursor.getString(3));
			contactMap.put("emailAddress", cursor.getString(4));
			contactMap.put("homeAddress", cursor.getString(5));
			
		}while(cursor.moveToNext());
	}
	return contactMap;
}
		
		
		
		
		
		
		
		
		
		
	
}
