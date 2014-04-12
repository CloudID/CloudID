package com.cloudid;

import java.io.InputStream;






import android.app.Activity;
import android.app.Dialog;
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

public class UserActivity extends FragmentActivity implements AddIdDialogFragment.AddIdInterface {
	
	private ImageView imgProfilePic;
	private TextView txtName, txtEmail;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user);
        
        setContentView(R.layout.background);
        LayoutInflater inflater = LayoutInflater.from(UserActivity.this);
		View theInflatedView = inflater.inflate(R.layout.activity_user, (ScrollView)findViewById(R.id.login_scroll_view));
        
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
    	txtName = (TextView) findViewById(R.id.txtName);
    	txtEmail = (TextView) findViewById(R.id.txtEmail);
    	
    	Intent intent=getIntent();
    	String[] info=intent.getStringArrayExtra(MainActivity.INFO);
    	txtName.setText(info[0]);
    	txtEmail.setText(info[1]);
        
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
    		Intent in = new Intent(UserActivity.this,MainActivity.class);
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
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		View vi=dialog.getView();
		Dialog dialogView = dialog.getDialog();
		//Dialog dialog2 =Dialog.class.cast(dialog);
 	  EditText edit = (EditText) dialogView.findViewById(R.id.username);
 	 Toast.makeText(getApplicationContext(), "Hi, "+edit.getText().toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void getAddId(String uname) {
		// TODO Auto-generated method stub
		
	}
	
	


}
