package com.edit.iorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class GalleryImageSwitcherDemoActivity extends Activity {
	/** Called when the activity is first created. */
	private Gallery gallery;
	// private ImageSwitcher imageSwitcher;
	private SimpleAdapter simpleAdapter;
	private int[] image = { R.drawable.title, R.drawable.title_2,
			R.drawable.title_3, R.drawable.title_4 };

	ImageButton imageButton1;

	// private String[] imgText = {
	// "cat", "flower", "hippo", "monkey"
	// };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.imageloading);

		// imageSwitcher = (ImageSwitcher)findViewById(R.id.image_switcher);

		Resources res = getResources();
		// Bitmap bmpbg = BitmapFactory.decodeResource(res,
		// R.drawable.newbanquetbackground);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		// imageView1.getLayoutParams().width = (int)((int)metrics.heightPixels
		// / (int)bmpbg
		// .getHeight()) * (int)bmpbg.getWidth();
		imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		imageButton1.getLayoutParams().width = metrics.widthPixels * 15 / 16;

		gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setSpacing(0);
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < image.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("image", image[i]);

			// item.put("text", imgText[i]);
			items.add(item);
		}
		simpleAdapter = new SimpleAdapter(this, items, R.layout.simple_adapter,
				new String[] { "image" }, new int[] { R.id.image });

		gallery.setAdapter(simpleAdapter);

		gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2 == 3) {
					imageButton1.setVisibility(View.VISIBLE);
					Animation animation = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.myanim);
					imageButton1.startAnimation(animation);
					imageButton1.setVisibility(View.VISIBLE);

				} else {
					imageButton1.setVisibility(View.INVISIBLE);
				}
				;
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		Parse.enableLocalDatastore(this);

		Parse.initialize(this, "yOYn9klTPIEKfgLPFglxbHUc9K7GpuUVm6Xr334m",
				"b6TTWVdcMresQPU6agGbNzotJEu5vPeEm4A2fNBB");

		ParseQuery<ParseObject> query = ParseQuery.getQuery("stores");
		// query.whereNear("geo", point);
		query.setLimit(1000);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
				// address,location,name,subtitle,tel,
				if (e == null) {

					// TextView textName = (TextView)
					// findViewById(R.id.textName);
					//
					for (int i = 0; i < scoreList.size(); i++) {
						HashMap<String, String> hmapS = new HashMap<String, String>();
						hmapS.put("address",
								scoreList.get(i).getString("address"));
						hmapS.put("location",
								scoreList.get(i).getString("location"));
						hmapS.put("name", scoreList.get(i).getString("name"));
						hmapS.put("subtitle",
								scoreList.get(i).getString("subtitle"));
						hmapS.put("tel", scoreList.get(i).getString("tel"));
						load lo = new load();
						lo.DatabaseStringList.add(hmapS);
						// lo.ccc();
						HashMap<String, Double> hmap = new HashMap<String, Double>();
						hmap.put("X_num",
								scoreList.get(i).getParseGeoPoint("geo")
										.getLatitude());
						hmap.put("Y_num",
								scoreList.get(i).getParseGeoPoint("geo")
										.getLongitude());

						lo.DatabaseDoubleList.add(hmap);
					}
					// load lo = new load();
					// String[] values = new
					// String[lo.DatabaseDoubleList.size()];
					// for (int i = 0; i < lo.DatabaseDoubleList.size(); i++) {
					// values[i] = String.valueOf(lo.DatabaseDoubleList.get(i)
					// .get("X_num"))
					// + ","
					// + String.valueOf(lo.DatabaseDoubleList.get(i)
					// .get("Y_num"));
					// // // DatabaseDoubleList2.get(i).get("Y_num");
					// }
					// lo.values = values;
					// OKLoad = true;
				} else {
				}

			}
		});

		imageButton1.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				ParseAnonymousUtils.logIn(new LogInCallback() {
					@Override
					public void done(ParseUser user, ParseException e) {
						if (e != null) {
							Log.d("MyApp", "Anonymous login failed.");
						} else {
							Log.d("MyApp", "Anonymous user logged in.");
						}
					}
				});

				// login();

				Intent intent1 = new Intent(
						GalleryImageSwitcherDemoActivity.this,
						MainActivity.class);
				startActivity(intent1);
				finish();

			}

		});

	}

	// public void login()
	// {
	//
	//
	// }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}
}