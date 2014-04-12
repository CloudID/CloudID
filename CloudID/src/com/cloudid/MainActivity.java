package com.cloudid;

import java.io.IOException;
import java.util.Currency;
import java.util.Date;

import android.os.AsyncTask;
import android.content.Context;
import android.content.Intent;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.cloudid.noteendpoint.*;
import com.cloudid.noteendpoint.model.*;
import com.cloudid.userinfoendpoint.*;
import com.cloudid.userinfoendpoint.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import android.os.AsyncTask;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.cu;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.md5.MD5hash;

public class MainActivity extends Activity implements OnClickListener,
ConnectionCallbacks, OnConnectionFailedListener {
	
	private static final int RC_SIGN_IN = 0;
	// Log cat tag
	private static final String TAG = "MainActivity";

	// Profile pic image size in pixels
	private static final int PROFILE_PIC_SIZE = 400;
	public static final String GOOGLE_INFO = null;
	public static final String USER_NAME = null;
	public static final String EMAIL_ID = null;
	public static final String PHOTO_URL = null;
	public static final String INFO = null;

	// Google client to interact with Google API
	private GoogleApiClient mGoogleApiClient;

	/**
	 * A flag indicating that a PendingIntent is in progress and prevents us
	 * from starting further intents.
	 */
	private boolean mIntentInProgress;

	private boolean mSignInClicked;
	private boolean resumeHasRun = true;

	private ConnectionResult mConnectionResult;

	private SignInButton btnSignIn;
	private Button btnSignOut, btnRevokeAccess;
	private ImageView imgProfilePic;
	private TextView txtName, txtEmail;
	private LinearLayout llProfileLayout;
	Person currentPerson;
	String personPhotoUrl;
	String personName;
	String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		View theInflatedView = inflater.inflate(R.layout.activity_main, (ScrollView)findViewById(R.id.login_scroll_view));
        //setContentView(R.layout.activity_main);
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
		btnSignOut = (Button) findViewById(R.id.btn_sign_out);
		btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
		imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
		txtName = (TextView) findViewById(R.id.txtName);
		txtEmail = (TextView) findViewById(R.id.txtEmail);
		llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
		// Button click listeners
				btnSignIn.setOnClickListener(this);
				btnSignOut.setOnClickListener(this);
				btnRevokeAccess.setOnClickListener(this);
        /*Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);*/
				
				/*Intent intent= getIntent();
				SharedPreferences settings = getSharedPreferences(AdminActivity.SIGN_OUT, 0);
				String state= intent.getStringExtra(AdminActivity.SIGN_OUT);
				if(state.e
				quals("true")){
					Toast.makeText(getApplicationContext(), "It is a true man", Toast.LENGTH_LONG).show();
				}*/
        
        mGoogleApiClient = new GoogleApiClient.Builder(this)
		.addConnectionCallbacks(this)
		.addOnConnectionFailedListener(this).addApi(Plus.API, null)
		.addScope(Plus.SCOPE_PLUS_LOGIN).build();

      //new EndpointsTask().execute(getApplicationContext());
    }
    
   /* @Override
    protected void onResume(){
    	super.onResume();
    	if(!resumeHasRun){
    		resumeHasRun = false;
    	Toast.makeText(getApplicationContext(), "in resume", Toast.LENGTH_LONG).show();
    	Intent intent = getIntent();
    	String he= intent.getStringExtra("signout");
        if(intent.getStringExtra("signout").equals("true")){
     	   Toast.makeText(getApplicationContext(), "It is a true man", Toast.LENGTH_LONG).show();
           // onCreate(new Bundle ());
     	  // getApiClient();
           revokeGplusAccess();
        }
    	}
     }
    */
   @Override
   public void onRestart()
   {
	super.onRestart();
	Toast.makeText(getApplicationContext(), "in resume", Toast.LENGTH_LONG).show();
	revokeGplusAccess();
   }
    /*@Override
    protected void onNewIntent(Intent intent) {
    	
       super.onNewIntent(intent);
       Toast.makeText(getApplicationContext(), "in new intent", Toast.LENGTH_LONG).show();
       String he= intent.getStringExtra("signout");
       if(intent.getStringExtra("signout").equals("true")){
    	   Toast.makeText(getApplicationContext(), "It is a true man", Toast.LENGTH_LONG).show();
          // onCreate(new Bundle ());
    	  // getApiClient();
         // revokeGplusAccess();
       }
    }*/
    
    protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();
		/*if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}*/
	}
	

	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			if (responseCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}
		}
	}
	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
			catch (NullPointerException e) {
				// TODO: handle exception
				Log.i("null", "Null again");
			}
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public class EndpointsTask extends AsyncTask<Context, Integer, String> {
        protected String doInBackground(Context... contexts) {
        
        Userinfoendpoint.Builder userendpointbuilder = new Userinfoendpoint.Builder(AndroidHttp.newCompatibleTransport(),
              new JacksonFactory(),
              new HttpRequestInitializer() {
              public void initialize(HttpRequest httpRequest) { }
              });
        
        
          Noteendpoint.Builder endpointBuilder = new Noteendpoint.Builder(
              AndroidHttp.newCompatibleTransport(),
              new JacksonFactory(),
              new HttpRequestInitializer() {
              public void initialize(HttpRequest httpRequest) { }
              });
          
          
      Noteendpoint endpoint = CloudEndpointUtils.updateBuilder(
      endpointBuilder).build();
      Userinfoendpoint userendpoint =CloudEndpointUtils.updateBuilder(userendpointbuilder).build();
      String userid = null;
      try {
          
         
          UserInfo user =new UserInfo().setUserName(Plus.AccountApi.getAccountName(mGoogleApiClient));
          userid= MD5hash.md5Java(Plus.AccountApi.getAccountName(mGoogleApiClient));
          user.setId(userid);
          user.setType("U");
          user.setTimestamp(System.currentTimeMillis());
          userendpoint.insertUserInfo(user).execute();
          
          Note note = new Note().setDescription(currentPerson.getDisplayName());
          String noteID = new Date().toString();
          note.setId(noteID);
          note.setEmailAddress("Why Not");      
          Note result = endpoint.insertNote(note).execute();
      } catch (IOException e) {
    	  //find out if currentperson is admin or user accordingly shift activities. 
    	  try {
			UserInfo present= userendpoint.getUserInfo(userid).execute();
			if(present.getType().equals("U")){
				return "U";
				//publishProgress(1);
				//Toast.makeText(getApplicationContext(), "It is a USER", Toast.LENGTH_LONG).show();
			}
			else if(present.getType().equals("A")){
				return "A";
				//publishProgress(2);
				//Toast.makeText(getApplicationContext(), "It is ADMIN", Toast.LENGTH_LONG).show();
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	  
        e.printStackTrace();
      }
          return "";
        }
        
        protected void onPostExecute(String result) {
        	
        	if(result.equals("U")){
        		Intent in =new Intent(MainActivity.this, UserActivity.class);
        		String [] info = new String[3];
        		info[0]= personName;
        		info[1]=email;
        		info[2]=personPhotoUrl;
        		in.putExtra(INFO, info);
        		startActivity(in);
        	}
        	else if(result.equals("A")){
        		Intent in =new Intent(MainActivity.this, AdminActivity.class);
        		//in.putExtra(GOOGLE_INFO, currentPerson);
        		String [] info = new String[3];
        		info[0]= personName;
        		info[1]=email;
        		info[2]=personPhotoUrl;
        		in.putExtra(INFO, info);
        		
        		startActivity(in);
        		
        	}
			
		}
        
       
    }

    @Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
					0).show();
			return;
		}

		if (!mIntentInProgress) {
			// Store the ConnectionResult for later usage
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				resolveSignInError();
			}
			
		}
    }

		
		@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		mSignInClicked = false;
		Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

		// Get user's information
		getProfileInformation();
		new EndpointsTask().execute(getApplicationContext());

		// Update the UI after signin
		//updateUI(true);
		// QRCODE PART impoertant
		
		/*ImageView imageView = (ImageView) findViewById(R.id.qrCode);

		String qrData = currentPerson.getNickname()+" \n "+currentPerson.getAboutMe()+"\n  "+currentPerson.getGender();
		int qrCodeDimention = 500;

		QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null,
		        Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

		try {
		    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
		    imageView.setImageBitmap(bitmap);
		} catch (WriterException e) {
		    e.printStackTrace();
		}*/
		
		
		
	}
	
	/**
	 * Updating the UI, showing/hiding buttons and profile layout
	 * */
	private void updateUI(boolean isSignedIn) {
		if (isSignedIn) {
			btnSignIn.setVisibility(View.GONE);
			btnSignOut.setVisibility(View.VISIBLE);
			btnRevokeAccess.setVisibility(View.VISIBLE);
			llProfileLayout.setVisibility(View.VISIBLE);
		} else {
			btnSignIn.setVisibility(View.VISIBLE);
			btnSignOut.setVisibility(View.GONE);
			btnRevokeAccess.setVisibility(View.GONE);
			llProfileLayout.setVisibility(View.GONE);
		}
	}
	
	/**
	 * Fetching user's information name, email, profile pic
	 * */
	private void getProfileInformation() {
		
		try {
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				 currentPerson = Plus.PeopleApi
						.getCurrentPerson(mGoogleApiClient);
				personName = currentPerson.getDisplayName();
				personPhotoUrl = currentPerson.getImage().getUrl();
				String personGooglePlusProfile = currentPerson.getUrl();
				email = Plus.AccountApi.getAccountName(mGoogleApiClient);

				Log.e(TAG, "Name: " + personName + ", plusProfile: "
						+ personGooglePlusProfile + ", email: " + email
						+ ", Image: " + personPhotoUrl);

				txtName.setText(personName);
				txtEmail.setText(email);

				// by default the profile url gives 50x50 px image only
				// we can replace the value with whatever dimension we want by
				// replacing sz=X
				personPhotoUrl = personPhotoUrl.substring(0,
						personPhotoUrl.length() - 2)
						+ PROFILE_PIC_SIZE;

				new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);

			} else {
				Toast.makeText(getApplicationContext(),
						"Person information is null", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();
		//updateUI(false);
	}

	
	/**
	 * Button on click listener
	 * */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sign_in:
			// Signin button clicked
			signInWithGplus();
			break;
		case R.id.btn_sign_out:
			// Signout button clicked
			signOutFromGplus();
			break;
		case R.id.btn_revoke_access:
			// Revoke access button clicked
			revokeGplusAccess();
			break;
		}
	}

	/**
	 * Sign-in into google
	 * */
	private void signInWithGplus() {
		if (!mGoogleApiClient.isConnecting()) {
			mSignInClicked = true;
			resolveSignInError();
		}
	}

	/**
	 * Sign-out from google
	 * */
	private void signOutFromGplus() {
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();
			mGoogleApiClient.connect();
			//updateUI(false);
		}
	}

	/**
	 * Revoking access from google
	 * */
	public void revokeGplusAccess() {
		//if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
					.setResultCallback(new ResultCallback<Status>() {
						@Override
						public void onResult(Status arg0) {
							Log.e(TAG, "User access revoked!");
							mGoogleApiClient.connect();
							updateUI(false);
						}

					});
		//}
	}

	/**
	 * Background Async task to load user profile picture from url
	 * */
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

	
}