

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

public class ViewUserActivity extends Activity implements OnClickListener{
	private Button submit,clear;
	private SQLiteDatabase newDB;
	private String tablename=DBhelperBank.tablename;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewuser);
		
		
		submit=(Button)findViewById(R.id.vsubmit);
		submit.setOnClickListener(this);
		
		clear=(Button)findViewById(R.id.vclear);
		clear.setOnClickListener(this);
	}
	
	
	public void onClick(View v) {
		EditText regnotext=(EditText)findViewById(R.id.vregno_field);
		EditText moneytext=(EditText)findViewById(R.id.vmoney_field);
		
		String regno=regnotext.getText().toString();
		//String money=moneytext.getText().toString();
		
		if(v==submit){
			if(regno.length()==0)
			{
				new AlertDialog.Builder(this).setMessage("empty regno field!!").setNeutralButton("error",null).show();
				return;
			}
			
			//if(money.length()==0)
		//	{
		//		new AlertDialog.Builder(this).setMessage("empty money").setNeutralButton("error",null).show();
		//		return;
		//	}
			
			DBhelperBank dbHelper = new DBhelperBank(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			
			
			

			Cursor c=newDB.rawQuery("SELECT money FROM " +
	    			tablename +
	    			" where regno='"+regno+"'", null);
			
			if (c != null )
			{
				c.moveToFirst();
	    				String money = c.getString(c.getColumnIndex("money"));
	    				moneytext.setText(money);
			}	    					    				
	    	else{
	    	new AlertDialog.Builder(this).setMessage("no reg no!!").setNeutralButton("error",null).show();
	    		return;
	    					
	    				}
	    					

			
				    	
			
		}
		if(v==clear)
		{
//			int resource=R.string.clear_msg;
//			String greeting=getResources().getString(resource);
//			Toast.makeText(this, greeting, Toast.LENGTH_LONG).show();
			regnotext.setText("");
			moneytext.setText("");
			
		}
	}

}

		
		