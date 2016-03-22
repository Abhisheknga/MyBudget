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

public class MainActivity extends Activity implements OnClickListener{

	private Button login;
	private Button register;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
    	login.setOnClickListener(this);
    	register = (Button)findViewById(R.id.register);
    	register.setOnClickListener(this);
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    
    

	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.adminloginmenu:
			Intent l=new Intent(MainActivity.this,AdminLoginActivity.class);
			startActivity(l);
			//Toast.makeText(this, "Admin login menu ", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
			
		}
	}
    
    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==login){
			Intent explicitIntent = new Intent(MainActivity.this,LoginActivity.class);
    		startActivity(explicitIntent);
			//Toast.makeText(this,"login",Toast.LENGTH_LONG).show();
		}
		
       if(v==register){
    	   Intent explicitIntent = new Intent(MainActivity.this,RegisterActivity.class);
   		   startActivity(explicitIntent);
    	   //Toast.makeText(this,"Register",Toast.LENGTH_LONG).show();
		}
		
	}
    
}
