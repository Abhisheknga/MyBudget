package com.example.budgetapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BankingActivity extends Activity implements OnClickListener{

	private Button database;
	private Button saveuser;
	private Button viewuser;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banking);
        database = (Button)findViewById(R.id.databaselist);
    	database.setOnClickListener(this);
    	saveuser = (Button)findViewById(R.id.saveuser);
    	saveuser.setOnClickListener(this);
    	viewuser = (Button)findViewById(R.id.viewuser);
    	viewuser.setOnClickListener(this);
    	
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
		if(v==database){
			Intent explicitIntent = new Intent(BankingActivity.this,DataListView2.class);
    		startActivity(explicitIntent);
			//Toast.makeText(this,"database",Toast.LENGTH_LONG).show();
		}
		      
       if(v==saveuser){
    	   Toast.makeText(this,"save user",Toast.LENGTH_LONG).show();
    	   Intent explicitIntent = new Intent(BankingActivity.this,SaveUserActivity.class);
   		   startActivity(explicitIntent);
    	  	}
       if(v==viewuser){
    	   Toast.makeText(this,"view user",Toast.LENGTH_LONG).show();
    	   Intent explicitIntent = new Intent(BankingActivity.this,ViewUserActivity.class);
   		   startActivity(explicitIntent);
    	  	}

       
	}
    
}

