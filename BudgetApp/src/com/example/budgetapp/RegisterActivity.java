package com.example.budgetapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener {
//	public SQLiteDatabase DB=null;

	private Button submit,clear;
	private SQLiteDatabase newDB;
	private String tablename=DBHelper.tablename;
	
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		
		submit=(Button)findViewById(R.id.submit);
		submit.setOnClickListener(this);
		
		clear=(Button)findViewById(R.id.clear);
		clear.setOnClickListener(this);
		
		
		
	}
		
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText regnotext=(EditText)findViewById(R.id.regno_field);
				String regno=regnotext.getText().toString();
				
				EditText nametext=(EditText)findViewById(R.id.name_field);
				String name=nametext.getText().toString();
				
				EditText passtext=(EditText)findViewById(R.id.password_field);
				String password=passtext.getText().toString();
				
				EditText cnametext=(EditText)findViewById(R.id.cname_field);
				String cname=cnametext.getText().toString();
				
				EditText addresstext=(EditText)findViewById(R.id.address_field);
				String address=addresstext.getText().toString();
				
				EditText mobiletext=(EditText)findViewById(R.id.mobile_field);
				String mobile=mobiletext.getText().toString();
				
		
				if(v==submit){
			
			if(regno.length()==0){
				new AlertDialog.Builder(this).setMessage("empty regno").setNeutralButton("error ok",null).show();
				return;
			}
			 if(name.length()==0){
				new AlertDialog.Builder(this).setMessage("empty name").setNeutralButton("error ok",null).show();
				return;
			}
			 if(password.length()==0){
				new AlertDialog.Builder(this).setMessage(" empty password").setNeutralButton("error ok",null).show();
				return;
			}
			 if(cname.length()==0){
				new AlertDialog.Builder(this).setMessage("empty city name").setNeutralButton("error ok",null).show();
				return;
			}
			 if(address.length()==0){
				new AlertDialog.Builder(this).setMessage("empty address").setNeutralButton("error ok",null).show();
				return;
			}
			if(mobile.length()==0){
				new AlertDialog.Builder(this).setMessage("empty mobile").setNeutralButton("error ok",null).show();				
							}
			//else{
			//Intent logout=new Intent(RegisterActivity.this,LoggedIn.class);
			//startActivity(logout);
			//}
		
			else{
			 
			 DBHelper dbHelper = new DBHelper(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase(); 
			  
			  
				//newDB.execSQL("insert into dbsms values('"+regno+"','"+name+"','"+password+"','"+cname+"','"+address+"','"+mobile+"')");
				
				newDB.execSQL("INSERT INTO " +
        			tablename +
        			" Values ('"+regno+"','"+name+"','"+password+"','"+cname+"','"+address+"','"+mobile+"');"); 
				 
				 
				Intent registeract=new Intent(RegisterActivity.this,UserHomeActivity.class);
				
				 //startActivityForResult(registeract,500);
				 startActivity(registeract);
				//startActivityForResult(registeract, requestCode)(intent)
			}
			
			
		
				}
		
		if(v==clear){
			regnotext.setText("");
			nametext.setText("");
			passtext.setText("");
			cnametext.setText("");
			addresstext.setText("");
			mobiletext.setText("");
		}
			
			
			}
}
