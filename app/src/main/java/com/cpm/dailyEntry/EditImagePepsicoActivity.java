package com.cpm.dailyEntry;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.ImageUploadActivity;

public class EditImagePepsicoActivity extends Activity {
	int pos;
	String _UserId;
	String _Currentdate;
	String _intime;
	private int _mid;

	protected static final String PHOTO_TAKEN = "photo_taken";

	protected String _imageurl, store_id;

	protected boolean _taken;

	private SharedPreferences preferences;
	ImageView imageview1, imageview2, imageview3;
	String sd_path = "/mnt/sdcard/PepsicoImages/";
	BitmapFactory.Options options;
	String imgpath1, imgpath2, imgpath3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editimages_sod);

		options = new BitmapFactory.Options();
		options.inSampleSize = 8;

		imageview1 = (ImageView) findViewById(R.id.imageView1);
		imageview2 = (ImageView) findViewById(R.id.imageView2);
		imageview3 = (ImageView) findViewById(R.id.imageView3);

		Intent i = getIntent();
		imgpath1 = i.getStringExtra("image1path");
		imgpath2 = i.getStringExtra("image2path");
		imgpath3 = i.getStringExtra("image3path");

		if (imgpath1 != null && !imgpath1.equals("")) {
			if (new File(sd_path + imgpath1).exists()) {

				String PATH = sd_path + imgpath1;

				imageview1
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
		}

		if (imgpath2 != null && !imgpath2.equals("")) {

			if (new File(sd_path + imgpath2).exists()) {
				String PATH = sd_path + imgpath2;

				imageview2
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
		}

		if (imgpath3 != null && !imgpath3.equals("")) {

			if (new File(sd_path + imgpath3).exists()) {
				String PATH = sd_path + imgpath3;

				imageview3
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent intent = new Intent(this, SodMasterActivity.class);
		startActivity(intent);
		EditImagePepsicoActivity.this.finish();
	}

}
