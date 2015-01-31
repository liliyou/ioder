package com.edit.iorder;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements LocationListener,
		OnMarkerClickListener {
	private GoogleMap mMap;
	private Marker myMarker;

	// public static ArrayList<HashMap<String, String>> DatabaseStringList = new
	// ArrayList<HashMap<String, String>>();
	// public static ArrayList<HashMap<String, Double>> DatabaseDoubleList = new
	// ArrayList<HashMap<String, Double>>();
	// public static ArrayList<HashMap<String, Double>> DatabaseDoubleList2 =
	// new ArrayList<HashMap<String, Double>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#4185F4")));

		setContentView(R.layout.activity_main);

		// Parse.enableLocalDatastore(this);
		// Parse.initialize(this, "yOYn9klTPIEKfgLPFglxbHUc9K7GpuUVm6Xr334m",
		// "b6TTWVdcMresQPU6agGbNzotJEu5vPeEm4A2fNBB");
		// new Thread(new Runnable() {
		//
		// public void run() {
		// loadDatabase();
		// }
		//
		// }).start();

		loadMap();

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			onLocationChanged(location);
		} else {

			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					22.6297370, 120.3278820), 9));

		}

		// HashMap<String, Double> hmap = new HashMap<String, Double>();
		// hmap.put("X_num", 22.6297370);
		// hmap.put("Y_num", 120.3278820);
		// DatabaseDoubleList2.add(hmap);
		// hmap.put("X_num", 22.6295370);
		// hmap.put("Y_num", 121.3276820);
		// DatabaseDoubleList2.add(hmap);
		//
		//
		// String[] values = new String[DatabaseDoubleList2.size()];
		// for (int i = 0; i < DatabaseDoubleList2.size(); i++) {
		// values[i] = String.valueOf(DatabaseDoubleList2.get(i).get("X_num"))
		// + ","
		// + String.valueOf(DatabaseDoubleList2.get(i).get("Y_num"));
		// // // DatabaseDoubleList2.get(i).get("Y_num");
		// }
		//
		// String values[] = { "22.6297370, 120.3278820",
		// "22.6295370, 120.3276820", "22.6298370, 120.3279820" };
		//

		// // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
		// 22.6297370, 120.3278820), 16));
		markerClass amarkerClass = new markerClass();
		Bitmap b = amarkerClass.Doublecircle(40, 35);
		//
		load lo = new load();
		for (int i = 0; i < lo.DatabaseDoubleList.size(); i++) {

			myMarker = mMap.addMarker(new MarkerOptions()

					.position(
							new LatLng(lo.DatabaseDoubleList.get(i)
									.get("X_num"), lo.DatabaseDoubleList.get(i)
									.get("Y_num")))

					.title(lo.DatabaseStringList.get(i).get("name"))
					.snippet(lo.DatabaseStringList.get(i).get("address"))
					.icon(BitmapDescriptorFactory.fromBitmap(b)));
			mMap.setInfoWindowAdapter(new InfoWindowAdapter() {

				// Use default InfoWindow frame
				@Override
				public View getInfoWindow(Marker arg0) {
					return null;
				}

				// Defines the contents of the InfoWindow
				@Override
				public View getInfoContents(Marker arg0) {

					// Getting view from the layout file info_window_layout
					View v = getLayoutInflater().inflate(
							R.layout.info_window_layout, null);

					// Getting the position from the marker
					// LatLng latLng = arg0.getPosition();
					//
					// // Getting reference to the TextView to set latitude
					// TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
					//
					// // Getting reference to the TextView to set longitude
					// TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
					//
					// // Setting the latitude
					// tvLat.setText("Latitude:" + latLng.latitude);
					//
					// // Setting the longitude
					// tvLng.setText("Longitude:"+ latLng.longitude);

					String title = arg0.getTitle();
					TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
					String snippet = arg0.getSnippet();
					TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

					tvLat.setText(title);
					tvLng.setText(snippet);
					// Returning the view containing InfoWindow contents
					return v;

				}

			});
		}
		// mMap.s

		// mMap.setInfoWindowAdapter(new CustomizeInfoWindowAdapter(
		// getLayoutInflater()));//
		// // lo.DatabaseStringList.get(i).get("name").toString()
		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
				Intent intent1 = new Intent(MainActivity.this,
						secondActivity.class);
				intent1.putExtra("title", marker.getTitle());
				intent1.putExtra("sub", marker.getSnippet());
				LatLng latLng = marker.getPosition();
				intent1.putExtra("Latitude", latLng.latitude);
				intent1.putExtra("Longitude", latLng.longitude);
				// intent1.putExtra("pictureListNum", marker.getSnippet());

				startActivity(intent1);
			}
		});
		// mMap.setOnMapClickListener(new OnMapClickListener() {
		//
		// @Override
		// public void onMapClick(LatLng arg0) {
		// // Clears any existing markers from the GoogleMap
		// mMap.clear();
		//
		// // Creating an instance of MarkerOptions to set position
		// MarkerOptions markerOptions = new MarkerOptions();
		//
		// // Setting position on the MarkerOptions
		// markerOptions.position(arg0);
		//
		// // Animating to the currently touched position
		// mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
		//
		// // Adding marker on the GoogleMap
		// Marker marker = mMap.addMarker(markerOptions);
		//
		// // Showing InfoWindow on the GoogleMap
		// marker.showInfoWindow();
		//
		// }
		// });

		locationManager.requestLocationUpdates(provider, 20000, 0, this);
	}

	// class CustomizeInfoWindowAdapter implements InfoWindowAdapter {
	// LayoutInflater inflater = null;
	//
	// CustomizeInfoWindowAdapter(LayoutInflater inflater) {
	// this.inflater = inflater;
	// }
	//
	// // 未點選前
	// public View getInfoWindow(Marker marker) {
	// // 可以沒有 InfoWindow
	// View popWindow = inflater.inflate(
	// R.layout.customize_infowindow_layout, null);
	// TextView textName = (TextView) popWindow
	// .findViewById(R.id.textName);
	//
	// textName.setText(marker.getTitle());
	// RelativeLayout mainlayout = (RelativeLayout) popWindow
	// .findViewById(R.id.main_layout);
	// mainlayout.setBackgroundColor(Color.RED);
	// return (null);
	// }
	//
	// // 點選後
	// public View getInfoContents(Marker marker) {
	// // 自訂 InfoWindow layout
	// // View popWindow = inflater.inflate(
	// // R.layout.customize_infowindow_layout, null);
	// // TextView textName = (TextView) popWindow
	// // .findViewById(R.id.textName);
	// //
	// // textName.setText(marker.getTitle());
	// // RelativeLayout mainlayout = (RelativeLayout) popWindow
	// // .findViewById(R.id.main_layout);
	// // mainlayout.setBackgroundColor(Color.RED);
	// // // bababa...
	// //
	// // textName.setText(marker.getTitle());
	// // load lo = new load();
	// // lo.DatabaseStringList.get(1).get("address");
	// return (null);
	// }
	// }

	@Override
	protected void onResume() {
		super.onResume();
		loadMap();
	}

	// this is method for create and load map with Support Map Fragment

	private void loadMap() {
		if (mMap != null) {
			return;
		}
		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		if (mMap == null) {
			return;
		}
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.setMyLocationEnabled(true);

	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();

		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);

		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in, animating the camera.
		mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		if (arg0.equals(myMarker)) {
			Intent intent1 = new Intent(MainActivity.this, secondActivity.class);
			intent1.putExtra("title", arg0.getTitle());
			intent1.putExtra("sub", arg0.getSnippet());
			// intent1.putExtra("pictureListNum", marker.getSnippet());

			startActivity(intent1);

		}

		return false;
	}
}