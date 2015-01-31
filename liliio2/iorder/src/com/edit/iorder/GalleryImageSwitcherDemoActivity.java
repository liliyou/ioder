package com.edit.iorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class GalleryImageSwitcherDemoActivity extends Activity {
	/** Called when the activity is first created. */
	private Gallery gallery;
	// private ImageSwitcher imageSwitcher;
	private SimpleAdapter simpleAdapter;
	private int[] image = { R.drawable.title, R.drawable.title_2,
			R.drawable.title_3, R.drawable.title_4 };

	// private String[] imgText = {
	// "cat", "flower", "hippo", "monkey"
	// };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageloading);

		// imageSwitcher = (ImageSwitcher)findViewById(R.id.image_switcher);

		Resources res = getResources();
		Bitmap bmpbg = BitmapFactory.decodeResource(res,
				R.drawable.newbanquetbackground);
	
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

//		imageView1.getLayoutParams().width = (int)((int)metrics.heightPixels / (int)bmpbg
//				.getHeight()) * (int)bmpbg.getWidth();
		
		
		
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

		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long id) {
				
				// imageSwitcher.setImageResource(image[position]);
			}
		});
		

	}
}