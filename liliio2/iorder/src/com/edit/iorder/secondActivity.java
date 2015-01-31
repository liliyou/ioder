package com.edit.iorder;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class secondActivity extends FragmentActivity implements
		OnStreetViewPanoramaReadyCallback {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#0000ff")));
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		String title = getIntent().getStringExtra("title");
		String sub = getIntent().getStringExtra("sub");
		TextView Store_name2 = (TextView) findViewById(R.id.Store_name2);
		TextView Address2 = (TextView) findViewById(R.id.Address2);
		Store_name2.setText(title);
		Address2.setText(sub);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		LinearLayout lLinearLayout1 = (LinearLayout) findViewById(R.id.page);
		lLinearLayout1.getLayoutParams().height = metrics.heightPixels / 5 * 3;
		StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager()
				.findFragmentById(R.id.streetviewpanorama);

		streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
	}

	@Override
	public void onStreetViewPanoramaReady(StreetViewPanorama arg0) {
		// TODO Auto-generated method stub
		arg0.setPosition(new LatLng(-33.87365, 151.20689));
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
