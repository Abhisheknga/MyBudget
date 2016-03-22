package com.example.budgetapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelperBank extends SQLiteOpenHelper {

	public SQLiteDatabase DB;
	public String dbpath;
	public static String dbname="bank";
	public static final int version='1';
	public static Context currentContext;
	public static String tablename="banktable";
	
	
	public DBhelperBank(Context context){
		super(context,dbname,null,version);
		currentContext=context;
		dbpath="/data/data/"+context.getPackageName() + "/databases";
		createDatabase();  // create database called
			}
	
	
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		
	}
   public void onCreate(SQLiteDatabase database){
		
		}
	// now putting body of createdatabase() method
	
	private void createDatabase(){
		boolean dbExists=checkDbExists();
		
		if(dbExists){
			// do nothing
		}
		else{
			DB=currentContext.openOrCreateDatabase(dbname,0,null);
			DB.execSQL("CREATE TABLE IF NOT EXISTS " +
			      tablename +
			      " (regno VARCHAR," +	       		      
			      "money VARCHAR);");
			
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('1','20000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('2','30000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('3','340000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('4','60000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('5','60000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('6','30000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('7','50000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('8','80000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('9','90000');");
			DB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('10','30000');");
		}
		
	}
	
	private boolean checkDbExists() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = dbpath + dbname;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}


	
}
