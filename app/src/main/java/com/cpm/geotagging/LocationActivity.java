package com.cpm.geotagging;

import java.io.File;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.ImageUploadActivity;
/*
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
*/

//public class LocationActivity extends MapActivity implements LocationListener {
public class LocationActivity  {

	/*private static final String TAG = "LocationActivity";
	protected static final String PHOTO_TAKEN = "photo_taken";
	LocationManager locationManager;
	Geocoder geocoder;
	TextView locationText;
	MapView map;
	protected Button _button;
	protected Button _buttonsave;
	public Camera camera;

	protected ImageView _image;
	protected TextView _field;
	Button imagebtn;
	MapController mapController;
	GeoPoint point;
	protected boolean _taken;
	ImageView capture_1;
	ImageView capture_2;
	ImageView capture_3;
	public String text;
	public View view;
	Location location;
	GeotaggingBeans data = new GeotaggingBeans();

	protected String diskpath = "";
	protected String _path;
	protected String _path1;
	protected String _path2;

	protected String _pathforcheck = "";
	protected String _path1forcheck = "";
	protected String _path2forcheck = "";

	String storename;
	String storeid;
	String storeaddress = "";
	String storelatitude = "";
	String storelongitude = "";
	protected int resultCode;
	public BitmapDrawable bitmapDrawable;
	int abc;
	int abd;
	int abe;
	TextView Stroename;
	static Editor e1, e2, e3;
	double lat;
	double longitude;
	private Bitmap mBubbleIcon, mShadowIcon;
	ProgressBar progress;

	class MapOverlay extends Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);

			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(point, screenPts);

			// ---add the marker---
			progress.setVisibility(View.INVISIBLE);
			Bitmap mBubbleIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.pointer);
			canvas.drawBitmap(mBubbleIcon, screenPts.x - mBubbleIcon.getWidth()
					/ 2, screenPts.y - mBubbleIcon.getHeight(), null);
			return true;
		}
	}

	*//** Called when the activity is first created. *//*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_location);

		_buttonsave = (Button) findViewById(R.id.geotag_sumbitbtn);
		capture_1 = (ImageView) findViewById(R.id.geotag_frontcamera);
		capture_2 = (ImageView) findViewById(R.id.geotag_insidecamera1);
		capture_3 = (ImageView) findViewById(R.id.geotag_insidecamera2);
		Stroename = (TextView) findViewById(R.id.geotag_store_orginalname);

		progress = (ProgressBar) findViewById(R.id.progressBar1);

		map = (MapView) this.findViewById(R.id.mapview);
		map.setBuiltInZoomControls(true);
		map.setSatellite(true);
		Intent intent = getIntent();
		storename = intent.getStringExtra("StoreName");
		storeid = intent.getStringExtra("Storeid");
		storeaddress = intent.getStringExtra("Storeaddress");
		storelatitude = intent.getStringExtra("storelatitude");
		storelongitude = intent.getStringExtra("storelongitude");

		mapController = map.getController();
		mapController.setZoom(15);

		locationManager = (LocationManager) this
				.getSystemService(LOCATION_SERVICE);
		geocoder = new Geocoder(this);
		location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		Stroename.setText(storename);

		if (!(storelatitude.equals("0")) && !(storelongitude.equals("0"))) {

			int latiti = (int) (Double.parseDouble(storelatitude) * 1000000);
			int longi = (int) (Double.parseDouble(storelongitude) * 1000000);

			point = new GeoPoint(latiti, longi);
			mapController.setCenter(point);
			MapOverlay mapOverlay = new MapOverlay();
			List<Overlay> listOfOverlays = map.getOverlays();
			listOfOverlays.clear();
			listOfOverlays.add(mapOverlay);

		} else {

			if (!(data.getLatitude() == 0) && !(data.getLongitude() == 0)) {

			} else {

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {

						if (!(data.getLatitude() == 0)
								&& !(data.getLongitude() == 0)) {

						} else {
							openNetwork();
						}

					}
				}, 120000);

			}

		}

		Stroename.setText(storename);

		_pathforcheck = storeid + "front.jpg";
		_path1forcheck = storeid + "inside.jpg";
		_path2forcheck = storeid + "inside1.jpg";
		_buttonsave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (!(storelatitude.equals("0"))
						&& !(storelongitude.equals("0"))) {
					lat = Double.parseDouble(storelatitude);
					longitude = Double.parseDouble(storelongitude);

				} else {
					lat = data.getLatitude();
					longitude = data.getLongitude();

				}

				if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)
						&& ImageUploadActivity.CheckGeotagImage(_path1forcheck)
						&& ImageUploadActivity.CheckGeotagImage(_path2forcheck)) {

					String status = "Y";
					PepsicoDatabase data = new PepsicoDatabase(
							getApplicationContext());
					data.open();
					data.updateGeoTagStatus(storeid, status, lat, longitude);

					data.InsertStoregeotagging(storeid, lat, longitude,
							_pathforcheck, _path1forcheck, _path2forcheck,
							status);
					data.close();

					// display toast
					AlertDialog.Builder builder = new AlertDialog.Builder(v
							.getContext());

					builder.setMessage("Data is saved successfully.")
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {

											Intent intent = new Intent(
													LocationActivity.this,
													GeoTagging.class);

											startActivity(intent);

											LocationActivity.this.finish();

										}
									});
					AlertDialog alert = builder.create();
					alert.show();

				} else if (!(data.getLatitude() == 0)
						&& !(data.getLongitude() == 0)) {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							LocationActivity.this);
					builder.setMessage(
							"Do you want to save geo location without store images.")
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {

											SavePartialData(lat, longitude);

										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
					AlertDialog alert = builder.create();

					alert.show();

				} else if (!(storelatitude.equals("0"))
						&& !(storelongitude.equals("0"))) {

					if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)
							&& ImageUploadActivity
									.CheckGeotagImage(_path1forcheck)
							&& ImageUploadActivity
									.CheckGeotagImage(_path2forcheck)) {

						String status = "Y";
						PepsicoDatabase data = new PepsicoDatabase(
								getApplicationContext());
						data.open();
						data.updateGeoTagStatus(storeid, status, lat, longitude);
						data.InsertStoregeotagging(storeid, lat, longitude,
								_pathforcheck, _path1forcheck, _path2forcheck,
								status);
						data.close();

						AlertDialog.Builder builder = new AlertDialog.Builder(v
								.getContext());

						builder.setMessage("Data is saved sucessfully.")
								.setCancelable(false)
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {

												Intent intent = new Intent(
														LocationActivity.this,
														GeoTagging.class);

												startActivity(intent);

												LocationActivity.this.finish();

											}
										});
						AlertDialog alert = builder.create();
						alert.show();

					} else {

						Toast.makeText(getBaseContext(),
								"Please Capture all store images",
								Toast.LENGTH_LONG).show();

					}
				} else {

					AlertDialog.Builder builder = new AlertDialog.Builder(v
							.getContext());

					builder.setMessage("Wait for geo location")
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
					AlertDialog alert = builder.create();
					alert.show();

				}

			}
		});

		capture_1.setOnClickListener(new ButtonClickHandler());
		capture_2.setOnClickListener(new ButtonClickHandler());
		capture_3.setOnClickListener(new ButtonClickHandler());

	}

	public void openNetwork() {

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, this);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, this);
		if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

			capture_1.setImageResource(R.drawable.camera_done);

		}
		if (ImageUploadActivity.CheckGeotagImage(_path1forcheck)) {

			capture_2.setImageResource(R.drawable.camera_done);

		}
		if (ImageUploadActivity.CheckGeotagImage(_path2forcheck)) {

			capture_3.setImageResource(R.drawable.camera_done);

		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

		try {

			// Convert latitude and longitude into int that the GeoPoint
			// constructor can understand
			int latiti;
			int longi;
			if (!(storelatitude.equals("0")) && !(storelongitude.equals("0"))) {
				latiti = (int) (Double.parseDouble(storelatitude) * 1000000);
				longi = (int) (Double.parseDouble(storelongitude) * 1000000);

			} else {
				data.setLatitude((location.getLatitude()));
				data.setLongitude((location.getLongitude()));

				latiti = (int) (location.getLatitude() * 1000000);
				longi = (int) (location.getLongitude() * 1000000);

			}

			point = new GeoPoint(latiti, longi);
			mapController.animateTo(point);
			MapOverlay mapOverlay = new MapOverlay();
			List<Overlay> listOfOverlays = map.getOverlays();
			listOfOverlays.clear();
			listOfOverlays.add(mapOverlay);

		} catch (Exception e) {
			Log.e("LocateMe", "Could not get Geocoder data", e);
		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void gobacktogeoselection() {
		Intent intent = new Intent(LocationActivity.this, GeoTagging.class);

		startActivity(intent);
		this.finish();

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	protected void startCameraActivity() {
		Log.i("MakeMachine", "startCameraActivity()");
		File file = new File(diskpath);
		Uri outputFileUri = Uri.fromFile(file);

		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		startActivityForResult(intent, 0);

	}

	public boolean isNetworkOnline() {
		boolean status = false;
		try {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getNetworkInfo(0);
			if (netInfo != null
					&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = false;
			} else {
				netInfo = cm.getNetworkInfo(1);
				if (netInfo != null
						&& netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return status;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MakeMachine", "resultCode: " + resultCode);
		switch (resultCode) {
		case 0:
			Log.i("MakeMachine", "User cancelled");
			break;

		case -1:
			onPhotoTaken();

			if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

				capture_1.setImageResource(R.drawable.camera_done);

			}
			if (ImageUploadActivity.CheckGeotagImage(_path1forcheck)) {

				capture_2.setImageResource(R.drawable.camera_done);

			}
			if (ImageUploadActivity.CheckGeotagImage(_path2forcheck)) {

				capture_3.setImageResource(R.drawable.camera_done);

			}

			break;
		}
	}

	protected void onPhotoTaken() {

		Log.i("MakeMachine", "onPhotoTaken");
		_taken = true;

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		Bitmap bitmap = BitmapFactory.decodeFile(diskpath, options);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.i("MakeMachine", "onRestoreInstanceState()");
		if (savedInstanceState.getBoolean(this.PHOTO_TAKEN)) {
			onPhotoTaken();
			if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

				capture_1.setImageResource(R.drawable.camera_done);

			}
			if (ImageUploadActivity.CheckGeotagImage(_path1forcheck)) {

				capture_2.setImageResource(R.drawable.camera_done);

			}
			if (ImageUploadActivity.CheckGeotagImage(_path2forcheck)) {

				capture_3.setImageResource(R.drawable.camera_done);

			}

		}

		if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

			capture_1.setImageResource(R.drawable.camera_done);

		}
		if (ImageUploadActivity.CheckGeotagImage(_path1forcheck)) {

			capture_2.setImageResource(R.drawable.camera_done);

		}
		if (ImageUploadActivity.CheckGeotagImage(_path2forcheck)) {

			capture_3.setImageResource(R.drawable.camera_done);

		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(this.PHOTO_TAKEN, _taken);
	}

	public class ButtonClickHandler implements View.OnClickListener {
		LocationActivity loc = new LocationActivity();

		public void onClick(View view) {

			if (!(storelatitude.equals("0")) && !(storelongitude.equals("0"))) {

				if (view.getId() == R.id.geotag_frontcamera) {

					diskpath = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + storeid + "front.jpg";
					_path = storeid + "front.jpg";

					Log.i("MakeMachine", "ButtonClickHandler.onClick()");

					abc = 03;

					startCameraActivity();

				}
				if (view.getId() == R.id.geotag_insidecamera1) {
					Log.i("MakeMachine", "ButtonClickHandler.onClick()");
					diskpath = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + storeid + "inside.jpg";
					_path1 = storeid + "inside.jpg";

					abd = 01;

					startCameraActivity();

				}
				if (view.getId() == R.id.geotag_insidecamera2) {

					Log.i("MakeMachine", "ButtonClickHandler.onClick()");
					diskpath = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + storeid + "inside1.jpg";
					_path2 = storeid + "inside1.jpg";

					abe = 02;

					startCameraActivity();

				}

			} else if (data.getLatitude() == 0 && data.getLongitude() == 0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						view.getContext());

				builder.setMessage("Wait For Geo Location")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();

			}

			else {
				if (view.getId() == R.id.geotag_frontcamera) {

					diskpath = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + storeid + "front.jpg";
					_path = storeid + "front.jpg";

					Log.i("MakeMachine", "ButtonClickHandler.onClick()");

					abc = 03;

					startCameraActivity();

				}
				if (view.getId() == R.id.geotag_insidecamera1) {
					Log.i("MakeMachine", "ButtonClickHandler.onClick()");
					diskpath = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + storeid + "inside.jpg";
					_path1 = storeid + "inside.jpg";

					abd = 01;

					startCameraActivity();

				}
				if (view.getId() == R.id.geotag_insidecamera2) {

					Log.i("MakeMachine", "ButtonClickHandler.onClick()");
					diskpath = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + storeid + "inside1.jpg";
					_path2 = storeid + "inside1.jpg";

					abe = 02;

					startCameraActivity();

				}

			}

		}
	}

	public void SavePartialData(Double lat, Double longitude) {

		String status = "P";
		PepsicoDatabase data = new PepsicoDatabase(getApplicationContext());
		data.open();
		data.updateGeoTagStatus(storeid, status, lat, longitude);
		data.InsertStoregeotagging(storeid, lat, longitude, _pathforcheck,
				_path1forcheck, _path2forcheck, status);
		data.close();
		Intent intent = new Intent(LocationActivity.this, GeoTagging.class);

		startActivity(intent);

		LocationActivity.this.finish();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Ask the user if they want to quit

			Intent intent = new Intent(LocationActivity.this, GeoTagging.class);

			startActivity(intent);
			LocationActivity.this.finish();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	public void ShowToast(String message) {

		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}*/

}