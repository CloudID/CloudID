package com.cloudid;

import java.io.IOException;
import java.io.InputStream;

import com.cloudid.useridendpoint.Useridendpoint;
import com.cloudid.useridendpoint.model.UserID;
import com.cloudid.userinfoendpoint.Userinfoendpoint;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.md5.MD5hash;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends FragmentActivity implements AddIdDialogFragment.AddIdInterface, ConnectionCallbacks, OnConnectionFailedListener {
	
	public static final String SIGN_OUT = "";
	private ImageView imgProfilePic;
	private TextView txtName, txtEmail;
	private GoogleApiClient mGoogleApiClient;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.background);
        LayoutInflater inflater = LayoutInflater.from(AdminActivity.this);
		View theInflatedView = inflater.inflate(R.layout.activity_admin, (ScrollView)findViewById(R.id.login_scroll_view));
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
    	txtName = (TextView) findViewById(R.id.txtName);
    	txtEmail = (TextView) findViewById(R.id.txtEmail);
    	
    	Intent intent=getIntent();
    	String[] info=intent.getStringArrayExtra(MainActivity.INFO);
    	txtName.setText(info[0]);
    	txtEmail.setText(info[1]);
    	Toast.makeText(getApplicationContext(), "It is ADMIN"+info[3], Toast.LENGTH_LONG).show();
        
        new LoadProfileImage(imgProfilePic).execute(info[2]);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId()==R.id.action_new){
    		FragmentManager fm = getSupportFragmentManager();
			//DialogFragment dialog = new EditNameDialogListener();
    		DialogFragment dialog = new AddIdDialogFragment();
    		dialog.show(fm, "hello");
//			FireMissilesDialogFragment f = new FireMissilesDialogFragment();
//			
//			f.show(fm, "hello");
    	}
    	if(item.getItemId()==R.id.menu){
    		
    		//MainActivity.revokeGplusAccess();
    		/* mGoogleApiClient = new GoogleApiClient.Builder(this)
    			.addConnectionCallbacks(this)
    			.addOnConnectionFailedListener(this).addApi(Plus.API, null)
    			.addScope(Plus.SCOPE_PLUS_LOGIN).build();
    		 mGoogleApiClient.connect();
    		 if (mGoogleApiClient.isConnected()) {
    		 Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
 			Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
 					.setResultCallback(new ResultCallback<Status>() {
 						@Override
 						public void onResult(Status arg0) {
 							//Log.e(TAG, "User access revoked!");
 							mGoogleApiClient.connect();
 							//updateUI(false);
 						}

 					});
    		 }*/
    		Intent in = new Intent(AdminActivity.this,MainActivity.class);
    		in.putExtra("signout", "true");
    		startActivity(in);
    	}
		return false;
    	
    }
	
	private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public LoadProfileImage(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}
	
	private class InsertID extends AsyncTask<Context, Integer, String>{

		@Override
		protected String doInBackground(Context... params) {
			// TODO Auto-generated method stub
			Useridendpoint.Builder useridendpoint=new Useridendpoint.Builder(AndroidHttp.newCompatibleTransport(),
		              new JacksonFactory(),
		              new HttpRequestInitializer() {
		              public void initialize(HttpRequest httpRequest) { }
		              });
			
			Useridendpoint userendpoint =CloudEndpointUtils.updateBuilder(useridendpoint).build();
			try{
				UserID id= new UserID();
				id.setFName("Nikhilesh");
				id.setLName("Payyavuala");
				id.setDob("07/06/1992");
				id.setUfID("48914676");
				id.setUfHash(MD5hash.md5Java("48914676"));
				id.setTimeStampCreated(System.currentTimeMillis());
				id.setTimeStampLastAccessed(System.currentTimeMillis());
				
				userendpoint.insertUserID(id).execute();
			}catch(IOException e){
				
			}
			return null;
		}
		
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		View vi=dialog.getView();
		Dialog dialogView = dialog.getDialog();
		//Dialog dialog2 =Dialog.class.cast(dialog);
 	  EditText edit = (EditText) dialogView.findViewById(R.id.username);
 	 
 	 new InsertID().execute(getApplicationContext());
 	Toast.makeText(getApplicationContext(), "Done, "+edit.getText().toString(), Toast.LENGTH_SHORT).show();
 	 
	}

	@Override
	public void getAddId(String uname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}


}
