




package com.example.budgetapp;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperBudget extends SQLiteOpenHelper {

	public SQLiteDatabase DB;
	public String dbpath;
	public static String dbname="budget";
	public static final int version='1';
	public static Context currentContext;
	public static String tablename="budgetinfo";
	public static String tablename1="allotbudget";
	String suserid;
	
	public DBHelperBudget(Context context,String suserid){
		super(context,dbname,null,version);
		currentContext=context;
		this.suserid=suserid;
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
			      "budget VARCHAR);");
			
			DB.execSQL("CREATE TABLE IF NOT EXISTS " +
				      tablename1 +
				      " (regno VARCHAR," +
				      "budgetallot VARCHAR);");	
			
			
			
			//DB.execSQL("INSERT INTO " +
        	//		tablename +
        	//		" Values ('1','2000');");
			
			String budget="";
			Cursor c=DB.rawQuery("SELECT regno, budget FROM " +
	    			tablename +
	    			" where regno='"+suserid+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			//do {
	    				String dregno = c.getString(c.getColumnIndex("regno"));
	    				 budget = c.getString(c.getColumnIndex("budget"));
	    			//}while (c.moveToNext());
	    		}
			
	    	}
			
			Double d=Double.valueOf(budget);
	    	String badd="";
	    	
	    	 final Calendar cal = Calendar.getInstance();
	         int year = cal.get(Calendar.YEAR);
	         int month = cal.get(Calendar.MONTH);
	         int day = cal.get(Calendar.DAY_OF_MONTH);
	    	badd=""+month+"/"+day+"/"+year;
	    	
			if(d==2000)
	    	badd=badd+"   >1000(mf),1000(fd)";
			else if(d==3000)
				badd=badd+"  >2000(mf),1000(fd)";	
			else if(d==4000)
				badd=badd+"  >2000(mf),1000(bd),1000(fd)";
			else if(d==5000)
				badd=badd+"  >2000(mf),2000(bd),1000(fd)";
			else if(d==6000)
				badd=badd+"  >2000(mf),2000(bd),2000(fd)";
			
			
			DB.execSQL("INSERT INTO " +
        			tablename1 +
        			" Values ('"+suserid+"','"+badd+"');");
			
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
