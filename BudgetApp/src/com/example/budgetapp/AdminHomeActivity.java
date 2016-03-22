
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

public class AdminHomeActivity extends Activity implements OnClickListener{

	private Button bank;
	private Button logout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhome);
        bank = (Button)findViewById(R.id.bank);
    	bank.setOnClickListener(this);
    	logout = (Button)findViewById(R.id.adminlogout);
    	logout.setOnClickListener(this);
    	
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
		if(v==bank){
			Intent explicitIntent = new Intent(AdminHomeActivity.this,BankingActivity.class);
    		startActivity(explicitIntent);
			//Toast.makeText(this,"In bank",Toast.LENGTH_LONG).show();
		}
		      
       if(v==logout){
    	   Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
    	   Intent explicitIntent = new Intent(AdminHomeActivity.this,MainActivity.class);
   		   startActivity(explicitIntent);
    	  
		}
       
	}
    
}

