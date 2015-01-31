package com.edit.iorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class loading extends ActionBarActivity {
	public boolean OKLoad = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dd);

		Button button01 = (Button) findViewById(R.id.button1);
		button01.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(loading.this, MainActivity.class);
				startActivity(intent1);
			}

		});
		Parse.enableLocalDatastore(this);

		Parse.initialize(this, "yOYn9klTPIEKfgLPFglxbHUc9K7GpuUVm6Xr334m",
				"b6TTWVdcMresQPU6agGbNzotJEu5vPeEm4A2fNBB");

		ParseQuery<ParseObject> query = ParseQuery.getQuery("stores");
		// query.whereNear("geo", point);
		query.setLimit(10);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
				// address,location,name,subtitle,tel,
				if (e == null) {

					// TextView textName = (TextView)
					// findViewById(R.id.textName);
					//
					for (int i = 0; i < scoreList.size(); i++) {
						HashMap<String, String> hmapS = new HashMap<String, String>();
						hmapS.put("address", scoreList
								.get(scoreList.size() - 1).getString("address"));
						hmapS.put(
								"location",
								scoreList.get(scoreList.size() - 1).getString(
										"location"));
						hmapS.put("name", scoreList.get(scoreList.size() - 1)
								.getString("name"));
						hmapS.put(
								"subtitle",
								scoreList.get(scoreList.size() - 1).getString(
										"subtitle"));
						hmapS.put("tel", scoreList.get(scoreList.size() - 1)
								.getString("tel"));
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
					OKLoad = true;
				} else {
				}

			}
		});
		new Thread(new Runnable() {

			public void run() {
				while (!OKLoad) {
					if (OKLoad) {
						Intent intent1 = new Intent(loading.this,
								MainActivity.class);
						startActivity(intent1);
					}

				}

			}

		}).start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}