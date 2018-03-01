package com.cpm.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.dailyEntry.DailyentryMenuActivity;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CateogryDataDelegates;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.FinalDataBeanDisplay;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.PromoBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.StoreBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class UploadImageActivity extends Activity {

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String visit_date;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	private int factor, k;
	private FailureGetterSetter failureGetterSetter = null;

	String result, username;
	String datacheck = "";
	String[] words;
	String validity, storename;
	String mid = "";
	String errormsg = "";
	static int counter = 1;
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<PosmBean> assetlist = new ArrayList<PosmBean>();
	ArrayList<GeotaggingBeans> geotaglist = new ArrayList<GeotaggingBeans>();
	private ArrayList<FinalDataBeanDisplay> displaylist = new ArrayList<FinalDataBeanDisplay>();

	private ArrayList<SodBean> master_list = new ArrayList<SodBean>();
	private ArrayList<CateogryDataDelegates> cateogryimgdata = new ArrayList<CateogryDataDelegates>();
	private ArrayList<PromotionBean> promo_list = new ArrayList<PromotionBean>();
	private ArrayList<PromoBean> promotionpepsi_list = new ArrayList<PromoBean>();
	StoreBean storestatus = new StoreBean();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_option);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		username = preferences.getString(CommonString.KEY_USERNAME, null);

		database = new PepsicoDatabase(this);
		database.open();

		new UploadTask(this).execute();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, DailyentryMenuActivity.class);
		startActivity(i);
		UploadImageActivity.this.finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// database.close();
	}

	private class UploadTask extends AsyncTask<Void, Void, String> {
		private Context context;

		UploadTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("Uploading Image");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {
				coverageBeanlist = database.getCoverageData(visit_date, null);
				geotaglist = database.getGeotaggingData(CommonString.KEY_D);

				if (coverageBeanlist.size() > 0 || geotaglist.size() > 0) {

					if (coverageBeanlist.size() == 1 && geotaglist.size() == 0) {
						factor = 50;
					} else {

						factor = 100 / (coverageBeanlist.size() + geotaglist
								.size());
					}
				}

				for (int i = 0; i < coverageBeanlist.size(); i++) {

					if (coverageBeanlist.get(i).getStatus()
							.equalsIgnoreCase("D")) {

						storestatus = database.getStoreStatus(coverageBeanlist
								.get(i).getStoreId());

						if ((storestatus.getCheckout_status().equalsIgnoreCase(
								CommonString.KEY_L) || storestatus
								.getCheckout_status().equalsIgnoreCase(
										CommonString.KEY_C))) {

							database.open();
							storename = database.getStoreName(coverageBeanlist
									.get(i).getStoreId());

							runOnUiThread(new Runnable() {

								public void run() {
									// TODO Auto-generated method stub
									k = k + factor;
									pb.setProgress(k);
									percentage.setText(k + "%");
									message.setText(storename + " Images");
								}
							});

							if (!(coverageBeanlist.get(i).getReasonid()
									.equalsIgnoreCase("")
									|| coverageBeanlist.get(i).getReasonid()
											.equalsIgnoreCase(null) || coverageBeanlist
									.get(i).getReasonid().equalsIgnoreCase("0"))) {
								if (coverageBeanlist.get(i).getImage() != null
										&& !coverageBeanlist.get(i).getImage()
												.equals("")) {

									if (new File("/mnt/sdcard/PepsicoImages/"
											+ coverageBeanlist.get(i)
													.getImage()).exists()) {

										result = UploadImage(coverageBeanlist
												.get(i).getImage());

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {

											return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
										} else if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {
												// TODO Auto-generated method
												// stub

												message.setText("Reason Image Upload");
											}
										});
									}
								}
							} else {
								// sod images Data
								database.open();
								master_list = database.getSodDataMid(
										coverageBeanlist.get(i).getStoreId(),
										coverageBeanlist.get(i).getMID());
								
								
								
								
								displaylist = database.getDisplayTransactionData_allAssets_allData(
										coverageBeanlist.get(i).getStoreId());
								
								{
									
									
									//uploading images
									
							
									
									for (int j = 0; j < displaylist.size(); j++) {

										if (displaylist.get(j).getImage_Path1() != null
												&& !displaylist.get(j).getImage_Path1()
														.equals("")) {

											if (new File(
													"/mnt/sdcard/PepsicoImages/"
															+ displaylist.get(j)
																	.getImage_Path1())
													.exists()) {

												result = UploadImage(displaylist
														.get(j).getImage_Path1());

												if (result
														.toString()
														.equalsIgnoreCase(
																CommonString.KEY_FALSE)) {

													return CommonString.METHOD_Get_DR_SOD_IMAGES;
												} else if (result
														.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

													return CommonString.METHOD_Get_DR_SOD_IMAGES
															+ "," + errormsg;
												}

												runOnUiThread(new Runnable() {

													public void run() {
														// TODO Auto-generated
														// method
														// stub

														message.setText("SOD Image1 Upload");
													}
												});
											}
										}
										if (displaylist.get(j).getImage_Path2() != null
												&& !displaylist.get(j).getImage_Path2()
														.equals("")) {

											if (new File(
													"/mnt/sdcard/PepsicoImages/"
															+ displaylist.get(j)
																	.getImage_Path2())
													.exists()) {

												result = UploadImage(displaylist
														.get(j).getImage_Path2());

												if (result
														.toString()
														.equalsIgnoreCase(
																CommonString.KEY_FALSE)) {

													return CommonString.METHOD_Get_DR_SOD_IMAGES;
												} else if (result
														.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

													return CommonString.METHOD_Get_DR_SOD_IMAGES
															+ "," + errormsg;
												}


												runOnUiThread(new Runnable() {

													public void run() {
														// TODO Auto-generated
														// method
														// stub

														message.setText("SOD Image2 Upload");
													}
												});
											}
										}
										if (displaylist.get(j).getImage_Path3() != null
												&& !displaylist.get(j).getImage_Path3()
														.equals("")) {

											if (new File(
													"/mnt/sdcard/PepsicoImages/"
															+ displaylist.get(j)
																	.getImage_Path3())
													.exists()) {

												result = UploadImage(displaylist
														.get(j).getImage_Path3());

												if (result
														.toString()
														.equalsIgnoreCase(
																CommonString.KEY_FALSE)) {

													return CommonString.METHOD_Get_DR_SOD_IMAGES;
												} else if (result
														.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

													return CommonString.METHOD_Get_DR_SOD_IMAGES
															+ "," + errormsg;
												}

												runOnUiThread(new Runnable() {

													public void run() {
														// TODO Auto-generated
														// method
														// stub

														message.setText("SOD Image3 Upload");
													}
												});
											}
										}

									}
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
								//uploading images	
									
									
									
									
									
									
									
								}
								
								

								for (int j = 0; j < master_list.size(); j++) {

									if (master_list.get(j).getImg1() != null
											&& !master_list.get(j).getImg1()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ master_list.get(j)
																.getImg1())
												.exists()) {

											result = UploadSODImage(master_list
													.get(j).getImg1());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_SOD_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_SOD_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("SOD Image1 Upload");
												}
											});
										}
									}
									if (master_list.get(j).getImg2() != null
											&& !master_list.get(j).getImg2()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ master_list.get(j)
																.getImg2())
												.exists()) {

											result = UploadSODImage(master_list
													.get(j).getImg2());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_SOD_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_SOD_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("SOD Image2 Upload");
												}
											});
										}
									}
									if (master_list.get(j).getImg3() != null
											&& !master_list.get(j).getImg3()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ master_list.get(j)
																.getImg3())
												.exists()) {

											result = UploadSODImage(master_list
													.get(j).getImg3());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_SOD_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_SOD_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("SOD Image3 Upload");
												}
											});
										}
									}

								}

								database.open();
								assetlist = database
										.getAssetData(coverageBeanlist.get(i)
												.getMID());

								for (int j = 0; j < assetlist.size(); j++) {

									if (assetlist.get(j).getImage1() != null
											&& !assetlist.get(j).getImage1()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ assetlist.get(j)
																.getImage1())
												.exists()) {

											result = UploadImage(assetlist.get(
													j).getImage1());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Assets Image1 Upload");
												}
											});
										}
									}
									if (assetlist.get(j).getImage2() != null
											&& !assetlist.get(j).getImage2()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ assetlist.get(j)
																.getImage2())
												.exists()) {

											result = UploadImage(assetlist.get(
													j).getImage2());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Assets Image2 Upload");
												}
											});
										}
									}
									if (assetlist.get(j).getImage3() != null
											&& !assetlist.get(j).getImage3()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ assetlist.get(j)
																.getImage3())
												.exists()) {

											result = UploadImage(assetlist.get(
													j).getImage3());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Assets Image3 Upload");
												}
											});
										}
									}

								}

								cateogryimgdata = database
										.getCateogrydataData(coverageBeanlist
												.get(i).getStoreId());

								for (int j = 0; j < cateogryimgdata.size(); j++) {

									if (cateogryimgdata.get(j).getImageurl() != null
											&& !cateogryimgdata.get(j)
													.getImageurl().equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ cateogryimgdata
																.get(j)
																.getImageurl())
												.exists()) {

											result = UploadImage(cateogryimgdata
													.get(j).getImageurl());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Category Image1 Upload");
												}
											});
										}
									}
									if (cateogryimgdata.get(j).getImageurl1() != null
											&& !cateogryimgdata.get(j)
													.getImageurl1().equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ cateogryimgdata
																.get(j)
																.getImageurl1())
												.exists()) {

											result = UploadImage(cateogryimgdata
													.get(j).getImageurl1());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Category Image2 Upload");
												}
											});
										}
									}
									if (cateogryimgdata.get(j).getImageurl2() != null
											&& !cateogryimgdata.get(j)
													.getImageurl2().equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ cateogryimgdata
																.get(j)
																.getImageurl2())
												.exists()) {

											result = UploadImage(cateogryimgdata
													.get(j).getImageurl2());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Category Image3 Upload");
												}
											});
										}
									}

								}

								promotionpepsi_list = database
										.gePromotionPepsiData(coverageBeanlist
												.get(i).getMID());

								for (int j = 0; j < promotionpepsi_list.size(); j++) {

									if (promotionpepsi_list.get(j).getImage() != null
											&& !promotionpepsi_list.get(j)
													.getImage().equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ promotionpepsi_list
																.get(j)
																.getImage())
												.exists()) {

											result = UploadPromotionImage(promotionpepsi_list
													.get(j).getImage());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_PROMOTION_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_PROMOTION_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Promotion Pepsi Images Upload");
												}
											});
										}
									}

								}

								promo_list = database
										.gePromotionOtherMid(coverageBeanlist
												.get(i).getMID());

								for (int j = 0; j < promo_list.size(); j++) {

									if (promo_list.get(j).getImage() != null
											&& !promo_list.get(j).getImage()
													.equals("")) {

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ promo_list.get(j)
																.getImage())
												.exists()) {

											result = UploadPromotionImage(promo_list
													.get(j).getImage());

											if (result
													.toString()
													.equalsIgnoreCase(
															CommonString.KEY_FALSE)) {

												return CommonString.METHOD_Get_DR_PROMOTION_IMAGES;
											} else if (result
													.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

												return CommonString.METHOD_Get_DR_PROMOTION_IMAGES
														+ "," + errormsg;
											}

											runOnUiThread(new Runnable() {

												public void run() {
													// TODO Auto-generated
													// method
													// stub

													message.setText("Promotion Competitor Images Upload");
												}
											});
										}
									}

								}

							}

							database.open();
							database.deleteStoreMidData(coverageBeanlist.get(i)
									.getMID(), coverageBeanlist.get(i)
									.getStoreId());
							database.updateStoreStatus(coverageBeanlist.get(i)
									.getStoreId(), coverageBeanlist.get(i)
									.getVisitDate());

							// SET COVERAGE STATUS
							String statusxml = "[DATA][USER_DATA][USER_ID]"
									+ username + "[/USER_ID][STORE_ID]"
									+ coverageBeanlist.get(i).getStoreId()
									+ "[/STORE_ID][VISIT_DATE]"
									+ coverageBeanlist.get(i).getVisitDate()
									+ "[/VISIT_DATE][STATUS]"
									+ CommonString.KEY_U
									+ "[/STATUS][/USER_DATA][/DATA]";

							SoapObject request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_SET_COVERAGE_STATUS);

							request.addProperty("onXML", statusxml);
							SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							HttpTransportSE androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport
									.call(CommonString.SOAP_ACTION_SET_COVERAGE_STATUS,
											envelope);
							Object result = (Object) envelope.getResponse();

							if (!result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {

								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FALSE)) {
									// return
									// CommonString.METHOD_SET_COVERAGE_STATUS;
								}

								SAXParserFactory saxPF = SAXParserFactory
										.newInstance();
								SAXParser saxP = saxPF.newSAXParser();
								XMLReader xmlR = saxP.getXMLReader();

								// for failure
								/*
								 * FailureXMLHandler failureXMLHandler = new
								 * FailureXMLHandler();
								 * xmlR.setContentHandler(failureXMLHandler);
								 * 
								 * InputSource is = new InputSource();
								 * is.setCharacterStream(new StringReader(result
								 * .toString())); xmlR.parse(is);
								 * 
								 * failureGetterSetter = failureXMLHandler
								 * .getFailureGetterSetter();
								 * 
								 * if
								 * (failureGetterSetter.getStatus().equalsIgnoreCase
								 * ( CommonString.KEY_FAILURE)) {
								 * 
								 * return
								 * CommonString.METHOD_SET_COVERAGE_STATUS +
								 * ", " + failureGetterSetter.getErrorMsg(); }
								 */
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {
								
							}

						}
					}
				}

				// for geotag

				for (int i = 0; i < geotaglist.size(); i++) {

					runOnUiThread(new Runnable() {

						public void run() {
							// TODO Auto-generated method stub
							k = k + factor;
							pb.setProgress(k);
							percentage.setText(k + "%");
							message.setText("Uploading Geotag Images...");
						}
					});

					if (geotaglist.get(i).getUrl1() != null
							&& !geotaglist.get(i).getUrl1()
									.equalsIgnoreCase("")) {

						if (new File("/mnt/sdcard/PepsicoImages/"
								+ geotaglist.get(i).getUrl1()).exists()) {
							result = UploadImage(geotaglist.get(i).getUrl1());

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {

								return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
							} else if (result
									.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

								return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
										+ "," + errormsg;
							}

							runOnUiThread(new Runnable() {

								public void run() {
									// TODO Auto-generated method stub

									message.setText("Image1 Upload");
								}
							});
						}
					}
					if (geotaglist.get(i).getUrl2() != null
							&& !geotaglist.get(i).getUrl2()
									.equalsIgnoreCase("")) {

						if (new File("/mnt/sdcard/PepsicoImages/"
								+ geotaglist.get(i).getUrl2()).exists()) {

							result = UploadImage(geotaglist.get(i).getUrl2());

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {

								return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
							} else if (result
									.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

								return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
										+ "," + errormsg;
							}

							runOnUiThread(new Runnable() {

								public void run() {
									// TODO Auto-generated method stub

									message.setText("Image2 Upload");
								}
							});
						}
					}
					if (geotaglist.get(i).getUrl3() != null
							&& !geotaglist.get(i).getUrl3()
									.equalsIgnoreCase("")) {

						if (new File("/mnt/sdcard/PepsicoImages/"
								+ geotaglist.get(i).getUrl3()).exists()) {

							result = UploadImage(geotaglist.get(i).getUrl3());

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {

								return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES;
							} else if (result
									.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

								return CommonString.METHOD_Get_DR_POSM_THEME_IMAGES
										+ "," + errormsg;
							}

							runOnUiThread(new Runnable() {

								public void run() {
									// TODO Auto-generated method stub

									message.setText("Image3 Upload");
								}
							});
						}
					}
					database.open();
					database.updateGeoTagDataInMain(geotaglist.get(i)
							.getStoreid());
					database.deleteGeoTagData(geotaglist.get(i).getStoreid());
				}

				return CommonString.KEY_SUCCESS;
			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_uploadimage", e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
						/*
						 * if (counter < 3) { new
						 * UploadTask(UploadImageActivity.this).execute(); }
						 * else { message.showMessage(); counter = 1; }
						 */
					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});
			}

			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {
				AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_UPLOAD_IMAGE, "success", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						UploadImageActivity.this, result, "success", null);
				message.showMessage();
			}
		}

		public String UploadSODImage(String path) throws Exception {

			errormsg = "";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile("/mnt/sdcard/PepsicoImages/" + path, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 1024;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;

			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeFile(
					"/mnt/sdcard/PepsicoImages/" + path, o2);

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			byte[] ba = bao.toByteArray();
			String ba1 = Base64.encodeBytes(ba);

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_Get_DR_SOD_IMAGES);

			request.addProperty("img", ba1);
			request.addProperty("name", path);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport.call(
					CommonString.SOAP_ACTION_Get_DR_SOD_IMAGES, envelope);
			Object result = (Object) envelope.getResponse();

			if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.KEY_FALSE;
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				// for failure
				FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
				xmlR.setContentHandler(failureXMLHandler);

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result.toString()));
				xmlR.parse(is);

				failureGetterSetter = failureXMLHandler
						.getFailureGetterSetter();

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					errormsg = failureGetterSetter.getErrorMsg();
					return CommonString.KEY_FAILURE;
				}
			} else {
				new File("/mnt/sdcard/PepsicoImages/" + path).delete();
			}

			return "";
		}

		public String UploadPromotionImage(String path) throws Exception {

			errormsg = "";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile("/mnt/sdcard/PepsicoImages/" + path, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 1024;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;

			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeFile(
					"/mnt/sdcard/PepsicoImages/" + path, o2);

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			byte[] ba = bao.toByteArray();
			String ba1 = Base64.encodeBytes(ba);

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_Get_DR_PROMOTION_IMAGES);

			request.addProperty("img", ba1);
			request.addProperty("name", path);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport.call(
					CommonString.SOAP_ACTION_Get_DR_PROMOTION_IMAGES, envelope);
			Object result = (Object) envelope.getResponse();

			if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.KEY_FALSE;
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				// for failure
				FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
				xmlR.setContentHandler(failureXMLHandler);

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result.toString()));
				xmlR.parse(is);

				failureGetterSetter = failureXMLHandler
						.getFailureGetterSetter();

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					errormsg = failureGetterSetter.getErrorMsg();
					return CommonString.KEY_FAILURE;
				}
			} else {
				new File("/mnt/sdcard/PepsicoImages/" + path).delete();
			}

			return "";
		}

		public String UploadImage(String path) throws Exception {

			errormsg = "";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile("/mnt/sdcard/PepsicoImages/" + path, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 1024;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;

			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeFile(
					"/mnt/sdcard/PepsicoImages/" + path, o2);

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			byte[] ba = bao.toByteArray();
			String ba1 = Base64.encodeBytes(ba);

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_Get_DR_POSM_THEME_IMAGES);

			request.addProperty("img", ba1);
			request.addProperty("name", path);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport
					.call(CommonString.SOAP_ACTION_Get_DR_POSM_THEME_IMAGES,
							envelope);
			Object result = (Object) envelope.getResponse();

			if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.KEY_FALSE;
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				// for failure
				FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
				xmlR.setContentHandler(failureXMLHandler);

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result.toString()));
				xmlR.parse(is);

				failureGetterSetter = failureXMLHandler
						.getFailureGetterSetter();

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					errormsg = failureGetterSetter.getErrorMsg();
					return CommonString.KEY_FAILURE;
				}
			} else {
				new File("/mnt/sdcard/PepsicoImages/" + path).delete();
			}

			return "";
		}

	}
}
