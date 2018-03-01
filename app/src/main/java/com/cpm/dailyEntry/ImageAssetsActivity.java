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

public class ImageAssetsActivity extends Activity implements OnClickListener {
	int pos;
	String _UserId;
	String _Currentdate;
	String _intime;
	private int _mid;
	protected String _path;
	protected String _path1;
	protected String _path2;

	protected String _pathforcheck = "";
	protected String _path1forcheck = "";
	protected String _path2forcheck = "";
	Button savebtn;
	private String image1 = "";

	private String image2 = "";

	private String image3 = "";

	protected static final String PHOTO_TAKEN = "photo_taken";

	protected String _imageurl, store_id;

	protected boolean _taken;
	ImageView mpicbtn1, mpicbtn2, mpicbtn3;
	private SharedPreferences preferences;
	ImageView imageview1, imageview2, imageview3;
	String sd_path = "/mnt/sdcard/PepsicoImages/";
	BitmapFactory.Options options;
	String imgpath1, imgpath2, imgpath3;
	AlertDialog alert ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.images_sos);

		options = new BitmapFactory.Options();
		options.inSampleSize = 8;

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		_UserId = preferences.getString(CommonString.KEY_USERNAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");

		mpicbtn1 = (ImageView) findViewById(R.id.geotag_camera1);

		mpicbtn2 = (ImageView) findViewById(R.id.geotag_camera2);

		mpicbtn3 = (ImageView) findViewById(R.id.geotag_camera3);

		imageview1 = (ImageView) findViewById(R.id.imageView1);
		imageview2 = (ImageView) findViewById(R.id.imageView2);
		imageview3 = (ImageView) findViewById(R.id.imageView3);

		savebtn = (Button) findViewById(R.id.save);

		mpicbtn1.setOnClickListener(this);

		mpicbtn2.setOnClickListener(this);

		mpicbtn3.setOnClickListener(this);
		savebtn.setOnClickListener(this);

		Intent i = getIntent();
		pos = i.getIntExtra(("position"), 1000);
		imgpath1 = i.getStringExtra("image1path");
		imgpath2 = i.getStringExtra("image2path");
		imgpath3 = i.getStringExtra("image3path");

		if (imgpath1 != null && !imgpath1.equals("")) {
			if (new File(sd_path + imgpath1).exists()) {
				image1 = imgpath1;

				String PATH = sd_path + imgpath1;
				mpicbtn1.setBackgroundResource(R.drawable.camera_list_tick);
				imageview1
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
		}

		if (imgpath2 != null && !imgpath2.equals("")) {
			image2 = imgpath2;
			if (new File(sd_path + imgpath2).exists()) {
				String PATH = sd_path + imgpath2;

				mpicbtn2.setBackgroundResource(R.drawable.camera_list_tick);
				imageview2
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
		}

		if (imgpath3 != null && !imgpath3.equals("")) {
			image3 = imgpath3;
			if (new File(sd_path + imgpath3).exists()) {
				String PATH = sd_path + imgpath3;
				mpicbtn3.setBackgroundResource(R.drawable.camera_list_tick);
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

		Intent intent = new Intent(ImageAssetsActivity.this,
				AssetsSkuActivity.class);
		intent.putExtra("position", 5000);
		intent.putExtra(CommonString.KEY_IMAGE_PATH1, image1);
		intent.putExtra(CommonString.KEY_IMAGE_PATH2, image2);
		intent.putExtra(CommonString.KEY_IMAGE_PATH3, image3);
		setResult(RESULT_OK, intent);
		ImageAssetsActivity.this.finish();

	}

	public String getTime() {
		Calendar m_cal = Calendar.getInstance();
		int hour = m_cal.get(Calendar.HOUR_OF_DAY);
		int min = m_cal.get(Calendar.MINUTE);
		int sec = m_cal.get(Calendar.SECOND);
		return hour + "-" + min + "-" + sec;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.geotag_camera1) {

			_pathforcheck = store_id + "Assests" + _UserId + getTime()
					+ "image1" + ".jpg";

			_path = Environment.getExternalStorageDirectory()
					+ "/PepsicoImages/" + _pathforcheck;
			Log.i("MakeMachine", "ButtonClickHandler.onClick()");
			startCameraActivity();

		}
		if (v.getId() == R.id.geotag_camera2) {

			_path1forcheck = store_id + "Assests" + _UserId + getTime()
					+ "image2" + ".jpg";

			_path = Environment.getExternalStorageDirectory()
					+ "/PepsicoImages/" + _path1forcheck;

			Log.i("MakeMachine", "ButtonClickHandler.onClick()");
			startCameraActivity();

		}
		if (v.getId() == R.id.geotag_camera3) {

			_path2forcheck = store_id + "Assests" + _UserId + getTime()
					+ "image3" + ".jpg";

			_path = Environment.getExternalStorageDirectory()
					+ "/PepsicoImages/" + _path2forcheck;

			Log.i("MakeMachine", "ButtonClickHandler.onClick()");
			startCameraActivity();

		}

		if (v.getId() == R.id.save) {
			if (ValidateImage()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ImageAssetsActivity.this);
				builder.setMessage("Do you want to save the data")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										alert.getButton(
												AlertDialog.BUTTON_POSITIVE)
												.setEnabled(false);
										
										Intent intent = new Intent(
												ImageAssetsActivity.this,
												AssetsSkuActivity.class);
										intent.putExtra("position", pos);
										intent.putExtra(
												CommonString.KEY_IMAGE_PATH1,
												image1);
										intent.putExtra(
												CommonString.KEY_IMAGE_PATH2,
												image2);
										intent.putExtra(
												CommonString.KEY_IMAGE_PATH3,
												image3);
										setResult(RESULT_OK, intent);
										ImageAssetsActivity.this.finish();

									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});
			    alert = builder.create();
				alert.show();
			} else {
				Toast.makeText(getApplicationContext(),
						"Please Capture Atleast One Image", Toast.LENGTH_SHORT)
						.show();
			}

		}

	}

	public boolean ValidateImage() {
		boolean result = true;

		if ((image1.equals("")) && (image2.equals("")) && (image3.equals(""))) {
			result = false;
		}

		return result;
	}

	protected void startCameraActivity() {

		try {
			Log.i("MakeMachine", "startCameraActivity()");
			File file = new File(_path);
			Uri outputFileUri = Uri.fromFile(file);

			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			startActivityForResult(intent, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MakeMachine", "resultCode: " + resultCode);
		switch (resultCode) {
		case 0:
			Log.i("MakeMachine", "User cancelled");
			break;

		case -1:

			if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

				mpicbtn1.setBackgroundResource(R.drawable.camera_list_tick);
				image1 = _pathforcheck;

				String PATH = sd_path + image1;

				imageview1
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
			if (ImageUploadActivity.CheckGeotagImage(_path1forcheck)) {

				mpicbtn2.setBackgroundResource(R.drawable.camera_list_tick);
				image2 = _path1forcheck;
				String PATH = sd_path + image2;

				imageview2
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));

			}
			if (ImageUploadActivity.CheckGeotagImage(_path2forcheck)) {

				mpicbtn3.setBackgroundResource(R.drawable.camera_list_tick);
				image3 = _path2forcheck;
				String PATH = sd_path + image3;
				imageview3
						.setImageBitmap(Bitmap.createScaledBitmap(
								BitmapFactory.decodeFile(PATH, options), 200,
								90, true));
			}
			break;
		}
	}

}
