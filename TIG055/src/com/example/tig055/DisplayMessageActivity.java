package com.example.tig055;

//import com.testPkg.test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class DisplayMessageActivity extends ActionBarActivity {

	private static final String TAG = "Displaymessageactivity";
	
	static final String publicTollParkings =
			"http://data.goteborg.se/ParkingService/v2.0/PublicTollParkings" +
				"/%7B57217002-37bb-43ce-8867-69a9b01cf6e9%7D?" +
				"latitude={LATITUDE}&" +
				"longitude={LONGITUDE}&" +
				"radius={RADIUS}&" +
				"format=json";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String data = null;
		JSONObject json = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		String jsonobject = null;

		try{
			URL url = new URL(publicTollParkings);
			try{
				URLConnection connect = url.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				
				URLConnection connection = new URL(url.toString()).openConnection();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()), 1024 * 16);
				
				data = reader.readLine();
				reader.close();
				Log.i(TAG, data);

				
//				try {
//					jsonarray = json.getJSONArray(data);
//					json = jsonarray.getJSONObject(0);
//
//				} catch (JSONException e) {
//					Log.i(TAG, "JSONException");
//					e.printStackTrace();
//				}//JSON

			}catch (IOException e){
				e.printStackTrace();
			}//IO
			
		}catch (MalformedURLException e){
			e.printStackTrace();
		}//URL

    	
		
		TextView textView = new TextView(this);
		//textView.canScrollVertically(5);
		setContentView(textView);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);	
		textView.setText(data);
		
		
		//setContentView(R.layout.activity_display_message);
		

		
		//TextView textview = (TextView) findViewById(R.id.jsondata);


		
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}

}
