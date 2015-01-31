package com.edit.iorder;

import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class markerClass {
	Bitmap bitmap;

	
	
	public HashMap putInArray(Double X_num,Double Y_num) {
		HashMap<String, Double> hmap = new HashMap<String, Double>();
		hmap.put("X",X_num);
		hmap.put("Y",Y_num);
		return hmap;
		
	}
	
	
	public Bitmap circle(int dir) {
		Bitmap bmap = Bitmap.createBitmap(dir, dir, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmap);
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(3);
		canvas.drawCircle(dir / 2, dir / 2, dir / 2, paint);
		return bmap;
	}

	// (¤j¶ê¥b®|,¤p¶ê¥b®|)
	public Bitmap Doublecircle(int dir1, int dir2) {
		Bitmap bmap = Bitmap.createBitmap(dir1, dir1, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmap);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(3);
		canvas.drawCircle(dir1 / 2, dir1 / 2, dir1 / 2, paint);
		paint.setColor(Color.WHITE);
		canvas.drawCircle(dir1 / 2, dir1 / 2, dir2 / 2, paint);
		return bmap;
	}
	
}
