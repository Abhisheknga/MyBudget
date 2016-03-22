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

public class DataListView3 extends ListActivity {
	
	private ArrayList<String> results = new ArrayList<String>();
	private String tablename = DBHelperBudget.tablename1;
	private SQLiteDatabase newDB;
	private string regno;

    String suserid="";
	

	private SQLiteDatabase newDBL;
	private String tablenamel=DBHelperLogin.tablename;
	
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        

    	DBHelperLogin dbHelperl = new DBHelperLogin(this.getApplicationContext());
		newDBL = dbHelperl.getWritableDatabase();
		Cursor c = newDBL.rawQuery("SELECT regno FROM " +
    			tablenamel, null);

    	if (c != null ) {
    		if  (c.moveToFirst()) {
    			
    			suserid = c.getString(c.getColumnIndex("regno"));
    			//userid.setText(suserid);		 			
    			
    		} 
    	}			


        
        
        
        openAndQueryDatabase();
        
        displayResultList();
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Your budget....");
        getListView().addHeaderView(tView);
        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);
		
	}
	private void openAndQueryDatabase() {
		try {
			DBHelperBudget dbHelperb = new DBHelperBudget(this.getApplicationContext(),suserid);
			newDB = dbHelperb.getWritableDatabase();
			

			Cursor c=newDB.rawQuery("SELECT regno, budgetallot FROM " +
	    			tablename +
	    			" where regno='"+suserid+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String dregno = c.getString(c.getColumnIndex("regno"));
	    				String budgetallot = c.getString(c.getColumnIndex("budgetallot"));
	    				results.add("Regno: " + dregno + ",Budget: " +budgetallot);
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
