package com.example.budgetapp;

import java.util.ArrayList;

import android.R.string;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataListView2 extends ListActivity {
	
	private ArrayList<String> results = new ArrayList<String>();
	private String tablename = DBhelperBank.tablename;
	private SQLiteDatabase newDB;
	private string regno;
	private string password;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();
        
        displayResultList();
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("This data is retrieved from the Bank database.");
        getListView().addHeaderView(tView);
        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);
		
	}
	private void openAndQueryDatabase() {
		try {
			DBhelperBank dbHelper = new DBhelperBank(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT regno, money FROM " +
	    			tablename, null);

	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String dregno = c.getString(c.getColumnIndex("regno"));
	    				String money = c.getString(c.getColumnIndex("money"));
	    				results.add("Regno: " + dregno + ",Money: " +money);
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
        	if (newDB != null) 
        		//newDB.execSQL("DELETE FROM " + tablename);
        		newDB.close();
        }

	}
    
}
