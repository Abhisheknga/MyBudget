package com.example.budgetapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminLoginActivity extends Activity implements OnClickListener{
	private Button submit,clear;
	private SQLiteDatabase newDB;
	private String tablename=DBHelper.tablename;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_login);
		
		
		
		submit=(Button)findViewById(R.id.asubmit);
		submit.setOnClickListener(this);
		
		clear=(Button)findViewById(R.id.aclear);
		clear.setOnClickListener(this);
	}
	
	
	public void onClick(View v) {
		EditText regnotext=(EditText)findViewById(R.id.adminid);
		EditText passwordtext=(EditText)findViewById(R.id.adminpassword);
		
		String regno=regnotext.getText().toString();
		String password=passwordtext.getText().toString();
		
		
		if(v==submit){
			if(regno.length()==0)
			{
				new AlertDialog.Builder(this).setMessage("empty name field!!").setNeutralButton("error",null).show();
				return;
			}
			
			if(password.length()==0)
			{
				new AlertDialog.Builder(this).setMessage("empty password").setNeutralButton("error",null).show();
				return;
			}
			
			
			regno="admin"+regno;
			password="admin"+password;
			DBHelper dbHelper = new DBHelper(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			
			
			Cursor c=newDB.rawQuery("SELECT password FROM " +
	    			tablename +
	    			" where regno='"+regno+"'", null);
			
			if (c != null )
			{
				c.moveToFirst();
	    				String dbpass = c.getString(c.getColumnIndex("password"));
	    				if(password.equals(dbpass))
	    				{
				Toast.makeText(this, "login sucess", Toast.LENGTH_LONG).show();
	    					Intent explicitIntent =new Intent(AdminLoginActivity.this,AdminHomeActivity.class);
	    					startActivity(explicitIntent);
	    				}
	    				else{
	    					new AlertDialog.Builder(this).setMessage("not match").setNeutralButton("error",null).show();
	    					return;
	    					
	    				}
	    					
					    	}
			
			
			
		}
		if(v==clear)
		{
//			int resource=R.string.clear_msg;
//			String greeting=getResources().getString(resource);
//			Toast.makeText(this, greeting, Toast.LENGTH_LONG).show();
			regnotext.setText("");
			passwordtext.setText("");
			
		}
	}

}
