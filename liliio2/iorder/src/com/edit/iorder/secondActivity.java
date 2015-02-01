package com.edit.iorder;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
//import com.parse.CountCallback;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;

public class secondActivity extends FragmentActivity implements
		OnStreetViewPanoramaReadyCallback {
	double DLatitude = -33.87365;
	double DLongitude = 151.20689;
	 String add,tel;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#4185F4")));
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		String title = getIntent().getStringExtra("title");
		String sub = getIntent().getStringExtra("sub");
		DLatitude = getIntent().getExtras().getDouble("Latitude");
		DLongitude = getIntent().getExtras().getDouble("Longitude");
		// DLatitude = Double.valueOf(Latitude);
		// DLongitude = Double.valueOf(Longitude);
		TextView Store_name2 = (TextView) findViewById(R.id.Store_name2);
		Store_name2.setText(title);
		TextView Address2 = (TextView) findViewById(R.id.Address2);
		Address2.setText(sub);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		LinearLayout lLinearLayout1 = (LinearLayout) findViewById(R.id.page);
		lLinearLayout1.getLayoutParams().height = metrics.heightPixels / 2;
		StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager()
				.findFragmentById(R.id.streetviewpanorama);

		streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

//		ParseQuery<ParseObject> query = ParseQuery.getQuery("stores");
//		query.whereEqualTo("name", title);
//		query.countInBackground(new CountCallback() {
//			public void done(int count, ParseException e) {
//				if (e == null) {
//					// The count request succeeded. Log the count
//					Log.d("score", "Sean has played " + count + " games");
//
//				} else {
//					// The request failed
//				}
//			}
//		});
//		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("stores");
//		query2.whereEqualTo("name", title);
//		query2.findInBackground(new FindCallback<ParseObject>() {
//			public void done(List<ParseObject> scoreList, ParseException e) {
//				// address,location,name,subtitle,tel,
//				if (e == null) {
//					for (int i = 0; i < scoreList.size(); i++) {
////						HashMap<String, String> hmapS = new HashMap<String, String>();
//						 add=scoreList.get(i).getString("address");
//						 tel=scoreList.get(i).getString("tel");
//							
//							TextView Address2 = (TextView) findViewById(R.id.Address2);
//							Address2.setText(add);
//							TextView tel2 = (TextView) findViewById(R.id.tel);
//							tel2.setText(tel);
//					}
//
//				} else {
//				}
//			
//
//			}
//		});

		Button goodBotton = (Button) findViewById(R.id.goodBotton);
		goodBotton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
//				ParseQuery<ParseObject> query = ParseQuery.getQuery("vote");
//				// query.whereNear("geo", point);
//				query.setLimit(1000);
//				query.findInBackground(new FindCallback<ParseObject>() {
//					public void done(List<ParseObject> scoreList,
//							ParseException e) {
//
//					}
//				});

			}

		});
		Button badBotton = (Button) findViewById(R.id.badBotton);
		badBotton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});
		Button gettogeter = (Button) findViewById(R.id.gettogeter);
		gettogeter.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});
		Button getShow = (Button) findViewById(R.id.getShow);
		getShow.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});
	}

	private Intent getDouble() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStreetViewPanoramaReady(StreetViewPanorama arg0) {
		// TODO Auto-generated method stub
		arg0.setPosition(new LatLng(DLatitude, DLongitude));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
