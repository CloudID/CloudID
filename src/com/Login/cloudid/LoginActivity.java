package com.Login.cloudid;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.plus.PlusClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	
	Button hello,login;
	TextView display;
	EditText h;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		//findViewById(R.id.sign_in_button).setOnClickListener((OnClickListener) this);
		hello= (Button)findViewById(R.id.button1);
		display=(TextView)findViewById(R.id.editText2);
		h=(EditText)findViewById(R.id.editText1);
		Toast.makeText(getApplicationContext(), "Type something in the box", 
				   Toast.LENGTH_LONG).show();
		hello.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String in=h.getText().toString();
				//Toast.makeText(getApplicationContext(), in, Toast.LENGTH_LONG).show();
				if(in.equals("")){
					Toast.makeText(getApplicationContext(), "Type something in the box", 
							   Toast.LENGTH_LONG).show();
					h.requestFocus();
				}
				else{
					display.setText("new words include "+ in );
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void gotoActivity(View v){
		Intent intd=new Intent(this,NewActivity.class);
		startActivity(intd);
	}

	
	
	
}
