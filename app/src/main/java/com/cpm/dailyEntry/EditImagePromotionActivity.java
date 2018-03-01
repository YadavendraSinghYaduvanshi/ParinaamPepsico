package com.cpm.dailyEntry;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.cpm.parinaampepsicoo.R;

public class EditImagePromotionActivity extends Activity {

	ImageView imageview1;
	String sd_path = "/mnt/sdcard/PepsicoImages/";
	BitmapFactory.Options options;
	String imgpath1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editimages_promotion);
		
		options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		
		imageview1 = (ImageView) findViewById(R.id.imageView1);

		Intent i = getIntent();
		imgpath1 = i.getStringExtra("image1path");

		if (imgpath1 != null && !imgpath1.equals("")) {
			if (new File(sd_path + imgpath1).exists()) {

				String PATH = sd_path + imgpath1;

				imageview1
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 350,
								200, true));

			}
		}

	}

	
}
