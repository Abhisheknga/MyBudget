package com.example.budgetapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserHomeActivity extends Activity implements OnClickListener{

	private Button setbudget;
	private Button viewbudget;
	private Button notification;
	//private Button setbanking;
	private Button logout;
	private TextView userid;
	String suserid="";
	

	private SQLiteDatabase newDBL;
	private String tablenamel=DBHelperLogin.tablename;
	
	final Context context = this;
	
	private String tablename = DBHelperBudget.tablename1;
	private SQLiteDatabase newDB;
	private String tablenamep = DBHelperBudget.tablename;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);
        setbudget = (Button)findViewById(R.id.setbudget);
    	setbudget.setOnClickListener(this);
    	viewbudget = (Button)findViewById(R.id.viewbudget);
    	viewbudget.setOnClickListener(this);
    	notification = (Button)findViewById(R.id.notification);
    	notification.setOnClickListener(this);
    	
    	//setbanking = (Button)findViewById(R.id.setbanking);
    	//setbanking.setOnClickListener(this);
       
    	logout = (Button)findViewById(R.id.logout);
    	logout.setOnClickListener(this);
    	userid=(TextView)findViewById(R.id.userid);
    
    	
    	
    	
    	DBHelperLogin dbHelperl = new DBHelperLogin(this.getApplicationContext());
		newDBL = dbHelperl.getWritableDatabase();
		Cursor c = newDBL.rawQuery("SELECT regno FROM " +
    			tablenamel, null);

    	if (c != null ) {
    		if  (c.moveToFirst()) {
    			
    			suserid = c.getString(c.getColumnIndex("regno"));
    			userid.setText(suserid);		 			
    			
    		} 
    	}			

    	
    	
    	
    	
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    
    

    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==setbudget){
			//Intent explicitIntent = new Intent(MainActivity.this,LoginActivity.class);
    		//startActivity(explicitIntent);
			Toast.makeText(this,"Set Budget",Toast.LENGTH_LONG).show();
			
			
			
			LayoutInflater li = LayoutInflater.from(context);
			View promptsView = li.inflate(R.layout.setbudget, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			final EditText userInput = (EditText) promptsView
					.findViewById(R.id.newbudget);

			// set dialog message
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// get user input and set it to result
									// edit text
									String b=userInput.getText().toString();
									DBHelperBudget dbHelperb = new DBHelperBudget(context,suserid);
									newDB = dbHelperb.getWritableDatabase();
									
									newDB.execSQL("DELETE FROM " + tablenamep +
											" where regno='"+suserid+"'");
									
									

									//Cursor c=newDB.rawQuery("SELECT regno, budgetallot FROM " +
							    	//		tablename +
							    	//		" where regno='"+suserid+"'", null);

									
									
									newDB.execSQL("INSERT INTO " +
						        			tablenamep +
						        			" Values ('"+suserid+"','"+b+"');");	
									
									
									
									
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
			
			
			
			
			
			
			
			
			
		}
		
       if(v==viewbudget){
    	   Intent explicitIntent = new Intent(UserHomeActivity.this,DataListView3.class);
   		   startActivity(explicitIntent);
    	   Toast.makeText(this,"View Budget",Toast.LENGTH_LONG).show();
		}
      
       
       
       if(v==notification){
    	   //Intent explicitIntent = new Intent(MainActivity.this,RegisterActivity.class);
   		   //startActivity(explicitIntent);
    	   Toast.makeText(this,"Notification",Toast.LENGTH_LONG).show();

         
    	   

			LayoutInflater li = LayoutInflater.from(context);
			View promptsView = li.inflate(R.layout.viewnotification, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			 TextView noti = (TextView) promptsView
					.findViewById(R.id.noti);

			
			
			
			DBHelperBudget dbHelperb = new DBHelperBudget(this.getApplicationContext(),suserid);
			newDB = dbHelperb.getWritableDatabase();
			

			Cursor c=newDB.rawQuery("SELECT regno, budgetallot FROM " +
	    			tablename +
	    			" where regno='"+suserid+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToLast()) {
	    			//do {
	    				String dregno = c.getString(c.getColumnIndex("regno"));
	    				String budgetallot = c.getString(c.getColumnIndex("budgetallot"));
	    			//	results.add("Regno: " + dregno + ",Budget: " +budgetallot);
	    			//}while (c.moveToNext());
	    				noti.setText(dregno+budgetallot);
	    		} 
	    	}
			
			
			
			
			
			
			
			
			
			
			
			// set dialog message
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// get user input and set it to result
									// edit text
									//result.setText(userInput.getText());
									
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

       
       
       
       
       
       
       }
      // if(v==setbanking){
    	   //Intent explicitIntent = new Intent(MainActivity.this,RegisterActivity.class);
   		   //startActivity(explicitIntent);
    	//   Toast.makeText(this,"Set Banking",Toast.LENGTH_LONG).show();
		//}
      
       if(v==logout){
    	   Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
    	   Intent explicitIntent = new Intent(UserHomeActivity.this,MainActivity.class);
   		   startActivity(explicitIntent);
    	  
		}
       
	}
    
}

