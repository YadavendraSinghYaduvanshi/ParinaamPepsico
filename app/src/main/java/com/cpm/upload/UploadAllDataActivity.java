package com.cpm.upload;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CateogryDataDelegates;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.PromoBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.download.CompleteDownloadActivity;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class UploadAllDataActivity extends Activity {

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String visit_date, username;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	private int factor, k;
	private String faceup, stock, length;
	String sod = "";
	String total_sku = "";
	String sku = "";
	String sos_data = "";
	private String reasonid;
	String datacheck = "";
	String[] words;
	String validity, storename;
	int mid;
	String category_data = "";
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<PosmBean> assetlist = new ArrayList<PosmBean>();
	private ArrayList<VericalBrandBean> availablelist = new ArrayList<VericalBrandBean>();
	ArrayList<GeotaggingBeans> geotaglist = new ArrayList<GeotaggingBeans>();
	private ArrayList<SodBean> master_list = new ArrayList<SodBean>();
	private ArrayList<SodBean> trans_list = new ArrayList<SodBean>();
	private ArrayList<CateogryDataDelegates> cateogryimgdata = new ArrayList<CateogryDataDelegates>();
	private ArrayList<PromotionBean> promo_list = new ArrayList<PromotionBean>();
	private ArrayList<PromoBean> promotionpepsi_list = new ArrayList<PromoBean>();
	private FailureGetterSetter failureGetterSetter = null;
	static int counter = 1;
	StoreBean storestatus = new StoreBean();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username = preferences.getString(CommonString.KEY_USERNAME, null);

		database = new PepsicoDatabase(this);
		database.open();

		new UploadTask(this).execute();
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
			dialog.setTitle("Uploading Data");
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

				coverageBeanlist = database.getCoverageData(null, null);
				geotaglist = database.getGeotaggingData("Y");

				if (coverageBeanlist.size() > 0 || geotaglist.size() > 0) {

					if (coverageBeanlist.size() == 1 && geotaglist.size() == 0) {
						factor = 50;
					} else {

						factor = 100 / (coverageBeanlist.size() + geotaglist
								.size());
					}
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				for (int i = 0; i < coverageBeanlist.size(); i++) {

					storestatus = database.getStoreStatus(coverageBeanlist.get(
							i).getStoreId());

					if (true) {
						String onXML = "[DATA][USER_DATA][USER_ID]" + username
								+ "[/USER_ID]" + "[LATITUDE]" + 0.0
								+ "[/LATITUDE][LONGITUDE]" + 0.0
								+ "[/LONGITUDE] [CHECKOUT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/CHECKOUT_DATE][CHECK_OUTTIME]"
								+ "11:59 PM" + "[/CHECK_OUTTIME][CREATED_BY]"
								+ username + "[/CREATED_BY][/USER_DATA][/DATA]";

						SoapObject request = new SoapObject(
								CommonString.NAMESPACE,
								CommonString.METHOD_CHECKOUT);
						request.addProperty("onXML", onXML);

						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						HttpTransportSE androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport.call(
								CommonString.SOAP_ACTION_CHECKOUT, envelope);
						Object result = (Object) envelope.getResponse();
						if (result.toString().equalsIgnoreCase(
								CommonString.KEY_SUCCESS)) {

						}

					}

					if ((storestatus.getCheckout_status().equalsIgnoreCase(
							CommonString.KEY_L) || storestatus
							.getCheckout_status().equalsIgnoreCase(
									CommonString.KEY_C))) {

					} else {

						String onXML = "[DATA][USER_DATA][USER_ID]" + username
								+ "[/USER_ID]" + "[LATITUDE]" + 0.0
								+ "[/LATITUDE][LONGITUDE]" + 0.0
								+ "[/LONGITUDE] [CHECKOUT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/CHECKOUT_DATE][CHECK_OUTTIME]"
								+ "11:59 PM" + "[/CHECK_OUTTIME][CREATED_BY]"
								+ username + "[/CREATED_BY][STORE_ID]"
								+ coverageBeanlist.get(i).getStoreId()
								+ "[/STORE_ID][/USER_DATA][/DATA]";

						SoapObject request = new SoapObject(
								CommonString.NAMESPACE,
								CommonString.METHOD_Checkout_StatusNew);
						request.addProperty("onXML", onXML);

						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						HttpTransportSE androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport.call(
								CommonString.SOAP_ACTION_Checkout_StatusNew,
								envelope);
						Object result = (Object) envelope.getResponse();
						if (result.toString().equalsIgnoreCase(
								CommonString.KEY_SUCCESS)) {

						}
					}

					if (!coverageBeanlist.get(i).getStatus()
							.equalsIgnoreCase("D")) {

						String image = "", contactid = "";
						if (new File("/mnt/sdcard/PepsicoImages/"
								+ coverageBeanlist.get(i).getImage()).exists()) {
							image = coverageBeanlist.get(i).getImage();
						} else {
							image = "";
						}
						if (coverageBeanlist.get(i).getKeycontactId()
								.equals("")) {
							contactid = "0";
						} else {
							contactid = coverageBeanlist.get(i)
									.getKeycontactId();
						}

						String onXML = "[DATA][USER_DATA][STORE_ID]"
								+ coverageBeanlist.get(i).getStoreId()
								+ "[/STORE_ID]" + "[VISIT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/VISIT_DATE][LATITUDE]"
								+ coverageBeanlist.get(i).getLatitude()
								+ "[/LATITUDE][LONGITUDE]"
								+ coverageBeanlist.get(i).getLongitude()
								+ "[/LONGITUDE][INTIME]"
								+ coverageBeanlist.get(i).getInTime()
								+ "[/INTIME][OUTTIME]"
								+ coverageBeanlist.get(i).getOutTime()
								+ "[/OUTTIME][STATUS]P"
								+ "[/STATUS][CREATED_BY]" + username
								+ "[/CREATED_BY][REASON_IMAGE]" + image
								+ "[/REASON_IMAGE][REASON_ID]"
								+ coverageBeanlist.get(i).getReasonid()
								+ "[/REASON_ID][INFORMED_TO]"
								+ coverageBeanlist.get(i).getRemarks()
								+ "[/INFORMED_TO][KEY_ID]" + contactid
								+ "[/KEY_ID][VISIT_OTHER_STORE]"
								+ coverageBeanlist.get(i).getStoreinfo()
								+ "[/VISIT_OTHER_STORE][REASON_REMARK]"
								+ coverageBeanlist.get(i).getOtherreson()
								+ "[/REASON_REMARK][/USER_DATA][/DATA]";

						SoapObject request = new SoapObject(
								CommonString.NAMESPACE,
								CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE);
						request.addProperty("onXML", onXML);

						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						HttpTransportSE androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport
								.call(CommonString.SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE,
										envelope);
						Object result = (Object) envelope.getResponse();

						datacheck = result.toString();
						words = datacheck.split("\\;");
						validity = (words[0]);

						if (validity.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
							database.updateCoverageStatus(
									coverageBeanlist.get(i).getMID(),
									CommonString.KEY_P);

						} else {
							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {
								return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE;
							}

							// for failure
							FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
							xmlR.setContentHandler(failureXMLHandler);

							InputSource is = new InputSource();
							is.setCharacterStream(new StringReader(result
									.toString()));
							xmlR.parse(is);

							failureGetterSetter = failureXMLHandler
									.getFailureGetterSetter();

							if (failureGetterSetter.getStatus()
									.equalsIgnoreCase(CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE
										+ ","
										+ failureGetterSetter.getErrorMsg();
							}
						}
						mid = Integer.parseInt((words[1]));

						storename = database.getStoreName(coverageBeanlist.get(
								i).getStoreId());

						runOnUiThread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								k = k + factor;
								pb.setProgress(k);
								percentage.setText(k + "%");
								message.setText(storename + " Data Upload");
							}
						});

						if (!(coverageBeanlist.get(i).getReasonid()
								.equalsIgnoreCase("")
								|| coverageBeanlist.get(i).getReasonid()
										.equalsIgnoreCase(null) || coverageBeanlist
								.get(i).getReasonid().equalsIgnoreCase("0"))) {

						} else {
							// DELETE SOD

							request = new SoapObject(CommonString.NAMESPACE,
									CommonString.METHOD_SOD_DELETE);

							request.addProperty("STORE_ID", coverageBeanlist
									.get(i).getStoreId());
							request.addProperty("VISIT_DATE", coverageBeanlist
									.get(i).getVisitDate());
							request.addProperty("USER_ID", username);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION_SOD_DELETE,
									envelope);
							result = (Object) envelope.getResponse();

							if (!result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {

								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FALSE)) {
									return CommonString.METHOD_SOD_DELETE;
								}

								// for failure
								FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
								xmlR.setContentHandler(failureXMLHandler);

								InputSource is = new InputSource();
								is.setCharacterStream(new StringReader(result
										.toString()));
								xmlR.parse(is);

								failureGetterSetter = failureXMLHandler
										.getFailureGetterSetter();

								if (failureGetterSetter.getStatus()
										.equalsIgnoreCase(
												CommonString.KEY_FAILURE)) {
									return CommonString.METHOD_SOD_DELETE + ","
											+ failureGetterSetter.getErrorMsg();
								}
							}

							// add sod

							master_list = database.getSodDataMid(
									coverageBeanlist.get(i).getStoreId(),
									coverageBeanlist.get(i).getMID());
							sku = "";
							total_sku = "";
							sod = "";
							if (!(master_list.size() == 0)) {

								for (int j = 0; j < master_list.size(); j++) {

									trans_list = database.getTransData(Integer
											.parseInt(master_list.get(j)
													.getId()));

									String left_image = "";
									String ryt_image = "";
									String center_image = "";

									if (new File("/mnt/sdcard/PepsicoImages/"
											+ master_list.get(j).getImg1())
											.exists()) {
										left_image = master_list.get(j)
												.getImg1();
									} else {
										left_image = "";
									}
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ master_list.get(j).getImg2())
											.exists()) {
										ryt_image = master_list.get(j)
												.getImg2();
									} else {
										ryt_image = "";
									}
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ master_list.get(j).getImg3())
											.exists()) {
										center_image = master_list.get(j)
												.getImg3();
									} else {
										center_image = "";
									}

									String display_details = "[DISPLAY_DETAIL][MID]"
											+ mid
											+ "[/MID][ID]"
											+ master_list.get(j).getId()
											+ "[/ID][COMPANY_ID]"
											+ master_list.get(j).getCompanyid()
											+ "[/COMPANY_ID][POSM_ID]"
											+ master_list.get(j).getPosmid()
											+ "[/POSM_ID]"
											+ "[POSM_LENGTH]"
											+ master_list.get(j).getL()
											+ "[/POSM_LENGTH][POSM_BREADTH]"
											+ master_list.get(j).getB()
											+ "[/POSM_BREADTH]"
											+ "[POSM_HEIGHT]"
											+ master_list.get(j).getH()
											+ "[/POSM_HEIGHT][LEFT_IMAGE]"
											+ left_image
											+ "[/LEFT_IMAGE]"
											+ "[RIGHT_IMAGE]"
											+ ryt_image
											+ "[/RIGHT_IMAGE][CENTER_IMAGE]"
											+ center_image
											+ "[/CENTER_IMAGE][CRETAED_BY]"
											+ username + "[/CRETAED_BY]";

									for (int k = 0; k < trans_list.size(); k++) {

										String dom1 = "1/1/1900", dom2 = "1/1/1900", dom3 = "1/1/1900";
										if (trans_list.get(k).getDom1()
												.equalsIgnoreCase("")
												|| trans_list
														.get(k)
														.getDom1()
														.equalsIgnoreCase("MFD")) {

										} else {
											dom1 = trans_list.get(k).getDom1();
										}

										if (trans_list.get(k).getDom2()
												.equalsIgnoreCase("")
												|| trans_list
														.get(k)
														.getDom2()
														.equalsIgnoreCase("MFD")) {

										} else {
											dom2 = trans_list.get(k).getDom2();
										}

										if (trans_list.get(k).getDom3()
												.equalsIgnoreCase("")
												|| trans_list
														.get(k)
														.getDom3()
														.equalsIgnoreCase("MFD")) {

										} else {
											dom3 = trans_list.get(k).getDom3();
										}

										String sku_details = "[SKUDETAILS] [ID]"
												+ trans_list.get(k).getSodid()
												+ "[/ID]"
												+ " [SKU_ID]"
												+ trans_list.get(k).getSkuid()
												+ "[/SKU_ID][STOCK_QTY]"
												+ trans_list.get(k).getStock()
												+ "[/STOCK_QTY][FACEUP]"
												+ trans_list.get(k).getFaceup()
												+ "[/FACEUP]"
												+ "[DOM1]"
												+ dom1
												+ "[/DOM1][DOM2]"
												+ dom2
												+ "[/DOM2]"
												+ "[DOM3]"
												+ dom3
												+ "[/DOM3][USER_ID]"
												+ username
												+ "[/USER_ID][/SKUDETAILS]";

										sku = sku + sku_details;
									}

									total_sku = total_sku + sku;
									sku = "";
									String final_xml = display_details
											+ total_sku + "[/DISPLAY_DETAIL]";
									sod = sod + final_xml;
									total_sku = "";

								}

								String Sod_XML = "[DATA]" + sod + "[/DATA]";

								request = new SoapObject(
										CommonString.NAMESPACE,
										CommonString.METHOD_UPLOAD_SOD_DATA);
								request.addProperty("onXML", Sod_XML);

								envelope = new SoapSerializationEnvelope(
										SoapEnvelope.VER11);
								envelope.dotNet = true;
								envelope.setOutputSoapObject(request);

								androidHttpTransport = new HttpTransportSE(
										CommonString.URL);

								androidHttpTransport.call(
										CommonString.SOAP_ACTION_UPLOAD_SOD,
										envelope);
								result = (Object) envelope.getResponse();

								if (!result.toString().equalsIgnoreCase(
										CommonString.KEY_SUCCESS)) {

									if (result.toString().equalsIgnoreCase(
											CommonString.KEY_FALSE)) {
										return CommonString.METHOD_UPLOAD_SOD_DATA;
									}

									// for failure
									FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
									xmlR.setContentHandler(failureXMLHandler);

									InputSource is = new InputSource();
									is.setCharacterStream(new StringReader(
											result.toString()));
									xmlR.parse(is);

									failureGetterSetter = failureXMLHandler
											.getFailureGetterSetter();

									if (failureGetterSetter.getStatus()
											.equalsIgnoreCase(
													CommonString.KEY_FAILURE)) {
										return CommonString.METHOD_UPLOAD_SOD_DATA
												+ ","
												+ failureGetterSetter
														.getErrorMsg();
									}
								}

								runOnUiThread(new Runnable() {

									public void run() {
										// TODO Auto-generated method stub

										message.setText("SOD Data Upload");
									}
								});
							}

							// DELETE ASSEST DATA

							request = new SoapObject(CommonString.NAMESPACE,
									CommonString.METHOD_DELETE_ASSET);

							request.addProperty("STORE_ID", coverageBeanlist
									.get(i).getStoreId());
							request.addProperty("VISIT_DATE", coverageBeanlist
									.get(i).getVisitDate());
							request.addProperty("USER_ID", username);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION_DELETE_ASSET,
									envelope);
							result = (Object) envelope.getResponse();

							if (!result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {

								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FALSE)) {
									return CommonString.METHOD_DELETE_ASSET;
								}

								// for failure
								FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
								xmlR.setContentHandler(failureXMLHandler);

								InputSource is = new InputSource();
								is.setCharacterStream(new StringReader(result
										.toString()));
								xmlR.parse(is);

								failureGetterSetter = failureXMLHandler
										.getFailureGetterSetter();

								if (failureGetterSetter.getStatus()
										.equalsIgnoreCase(
												CommonString.KEY_FAILURE)) {
									return CommonString.METHOD_DELETE_ASSET
											+ ","
											+ failureGetterSetter.getErrorMsg();
								}
							}
							// assests Data

							assetlist = database.getAssetData(coverageBeanlist
									.get(i).getMID());

							if (!(assetlist.size() == 0)) {

								for (int j = 0; j < assetlist.size(); j++) {

									String left_image = "";
									String ryt_image = "";
									String center_image = "";

									if (new File("/mnt/sdcard/PepsicoImages/"
											+ assetlist.get(j).getImage1())
											.exists()) {
										left_image = assetlist.get(j)
												.getImage1();
									} else {
										left_image = "";
									}
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ assetlist.get(j).getImage2())
											.exists()) {
										ryt_image = assetlist.get(j)
												.getImage2();
									} else {
										ryt_image = "";
									}
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ assetlist.get(j).getImage3())
											.exists()) {
										center_image = assetlist.get(j)
												.getImage3();
									} else {
										center_image = "";
									}

									onXML = "[DATA][USER_DATA][MID]"
											+ mid
											+ "[/MID][ASSEST_ID]"
											+ assetlist.get(j).getAssetid()
											+ "[/ASSEST_ID][PRESENCE]"
											+ assetlist.get(j).getAvailable()
											+ "[/PRESENCE]"
											+ "[REMARKS]"
											+ assetlist.get(j).getRemarks()
											+ "[/REMARKS][LEFT_IMAGE]"
											+ left_image
											+ "[/LEFT_IMAGE]"
											+ "[RIGHT_IMAGE]"
											+ ryt_image
											+ "[/RIGHT_IMAGE][CENTER_IMAGE]"
											+ center_image
											+ "[/CENTER_IMAGE]"
											+ "[USER_ID]"
											+ username
											+ "[/USER_ID][VERTICAL_ID]"
											+ assetlist.get(j).getVerticalid()
											+ "[/VERTICAL_ID][/USER_DATA][/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_ASSET);
									request.addProperty("onXML", onXML);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_UPLOAD_ASSET,
													envelope);
									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.METHOD_UPLOAD_ASSET;
										}

										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_UPLOAD_ASSET
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}

									runOnUiThread(new Runnable() {

										public void run() { // TODO
															// Auto-generated
															// method

											message.setText("Assets Data Upload");
										}
									});
								}
							}

							// sos upload

							int count = database.getSosDataMid(coverageBeanlist
									.get(i).getStoreId(),
									coverageBeanlist.get(i).getMID());

							if (count > 0) {

								availablelist = database
										.getAvailableUploadData(coverageBeanlist
												.get(i).getStoreId());

								sos_data = "";
								if (!(availablelist.size() == 0)) {

									for (int j = 0; j < availablelist.size(); j++) {

										String dom1 = "1/1/1990", dom2 = "1/1/1990", dom3 = "1/1/1990";
										if (availablelist.get(j).getSkuqty1()
												.equalsIgnoreCase("")) {
											faceup = "0";
										} else {
											faceup = availablelist.get(j)
													.getSkuqty1();
										}

										if (availablelist.get(j).getSkuqty()
												.equalsIgnoreCase("")) {
											stock = "0";
										} else {
											stock = availablelist.get(j)
													.getSkuqty();
										}
										if (availablelist.get(j).getSkuqty2()
												.equalsIgnoreCase("")) {
											length = "0";
										} else {
											length = availablelist.get(j)
													.getSkuqty2();
										}
										if (availablelist.get(j).getDom1()
												.equalsIgnoreCase("mfd")
												|| availablelist.get(j)
														.getDom2()
														.equalsIgnoreCase("")) {

										} else {
											dom1 = availablelist.get(j)
													.getDom1();
										}
										if (availablelist.get(j).getDom2()
												.equalsIgnoreCase("mfd")
												|| availablelist.get(j)
														.getDom2()
														.equalsIgnoreCase("")) {

										} else {
											dom2 = availablelist.get(j)
													.getDom2();
										}
										if (availablelist.get(j).getDom3()
												.equalsIgnoreCase("mfd")
												|| availablelist.get(j)
														.getDom3()
														.equalsIgnoreCase("")) {

										} else {
											dom3 = availablelist.get(j)
													.getDom3();

										}

										if (availablelist.get(j).getReasonid()
												.equalsIgnoreCase(null)
												|| availablelist.get(j)
														.getReasonid()
														.equalsIgnoreCase("")) {
											reasonid = "0";
										} else {
											reasonid = availablelist.get(j)
													.getReasonid();
										}

										String data = "[USER_DATA][ID]"
												+ "1"
												+ "[/ID][MID]"
												+ mid
												+ "[/MID][SKU_ID]"
												+ availablelist.get(j)
														.getSkuid()
												+ "[/SKU_ID][STOCK]" + stock
												+ "[/STOCK][FACEUP]" + faceup
												+ "[/FACEUP][LENGHT]" + length
												+ "[/LENGHT][DOM1]" + dom1
												+ "[/DOM1][DOM2]" + dom2
												+ "[/DOM2][DOM3]" + dom3
												+ "[/DOM3][USER_ID]" + username
												+ "[/USER_ID][STOCK_REASON_ID]"
												+ reasonid
												+ "[/STOCK_REASON_ID]"
												+ "[/USER_DATA]";

										sos_data = sos_data + data;

										System.out.println(sos_data);

									}
									final String sos_xml = "[DATA]" + sos_data
											+ "[/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_AVAILABILITY);
									request.addProperty("onXML", sos_xml);
									request.addProperty("CREATED_BY", username);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_UPLOAD_AVAILABILITY,
													envelope);

									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.METHOD_UPLOAD_AVAILABILITY;
										}

										// for failure
										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_UPLOAD_AVAILABILITY
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}

									runOnUiThread(new Runnable() {

										public void run() { // TODO
															// Auto-generated
															// method

											message.setText("Sos Data Upload");

										}
									});
								}
							}

							cateogryimgdata = database
									.getCateogrydataData(coverageBeanlist
											.get(i).getStoreId());
							category_data = "";
							if (!(cateogryimgdata.size() == 0)) {

								for (int j = 0; j < cateogryimgdata.size(); j++) {

									String left_image = "";
									String ryt_image = "";
									String center_image = "";

									if (new File("/mnt/sdcard/PepsicoImages/"
											+ cateogryimgdata.get(j)
													.getImageurl()).exists()) {
										left_image = cateogryimgdata.get(j)
												.getImageurl();
									} else {
										left_image = "";
									}
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ cateogryimgdata.get(j)
													.getImageurl1()).exists()) {
										ryt_image = cateogryimgdata.get(j)
												.getImageurl1();
									} else {
										ryt_image = "";
									}
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ cateogryimgdata.get(j)
													.getImageurl2()).exists()) {
										center_image = cateogryimgdata.get(j)
												.getImageurl2();
									} else {
										center_image = "";
									}

									String cat_xml = "[USER_DATA] [ID]"
											+ "1"
											+ "[/ID][MID]"
											+ mid
											+ "[/MID][CATEGORY_ID]"
											+ cateogryimgdata.get(j)
													.getCateogryid()
											+ "[/CATEGORY_ID][LEFT_IMAGE]"
											+ left_image
											+ "[/LEFT_IMAGE][RIGHT_IMAGE]"
											+ ryt_image
											+ "[/RIGHT_IMAGE][CENTER_IMAGE]"
											+ center_image
											+ "[/CENTER_IMAGE] [USER_ID]"
											+ username
											+ "[/USER_ID][/USER_DATA]";

									category_data = category_data + cat_xml;

									System.out.println(category_data);

								}
								final String category_xml = "[DATA]"
										+ category_data + "[/DATA]";

								request = new SoapObject(
										CommonString.NAMESPACE,
										CommonString.METHOD_UPLOAD_CATEGORY_IMAGES);
								request.addProperty("onXML", category_xml);

								envelope = new SoapSerializationEnvelope(
										SoapEnvelope.VER11);
								envelope.dotNet = true;
								envelope.setOutputSoapObject(request);

								androidHttpTransport = new HttpTransportSE(
										CommonString.URL);

								androidHttpTransport
										.call(CommonString.SOAP_ACTION_UPLOAD_CATEGORY_IMAGES,
												envelope);

								result = (Object) envelope.getResponse();

								if (!result.toString().equalsIgnoreCase(
										CommonString.KEY_SUCCESS)) {

									if (result.toString().equalsIgnoreCase(
											CommonString.KEY_FALSE)) {
										return CommonString.METHOD_UPLOAD_CATEGORY_IMAGES;
									}

									// for failure
									FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
									xmlR.setContentHandler(failureXMLHandler);

									InputSource is = new InputSource();
									is.setCharacterStream(new StringReader(
											result.toString()));
									xmlR.parse(is);

									failureGetterSetter = failureXMLHandler
											.getFailureGetterSetter();

									if (failureGetterSetter.getStatus()
											.equalsIgnoreCase(
													CommonString.KEY_FAILURE)) {
										return CommonString.METHOD_UPLOAD_CATEGORY_IMAGES
												+ ","
												+ failureGetterSetter
														.getErrorMsg();
									}
								}

								runOnUiThread(new Runnable() {

									public void run() { // TODO Auto-generated
														// method

										message.setText("Category Data Upload");
									}
								});
							}

							// PROMOTION pepsi
							promotionpepsi_list = database
									.gePromotionPepsiData(coverageBeanlist.get(
											i).getMID());

							if (!(promotionpepsi_list.size() == 0)) {

								for (int j = 0; j < promotionpepsi_list.size(); j++) {

									String pro_image = "";
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ promotionpepsi_list.get(j)
													.getImage()).exists()) {
										pro_image = promotionpepsi_list.get(j)
												.getImage();
									} else {
										pro_image = "";
									}

									onXML = "[DATA][USER_DATA][MID]"
											+ mid
											+ "[/MID][PROMO_ID]"
											+ promotionpepsi_list.get(j)
													.getPromoid()
											+ "[/PROMO_ID][REMARK]"
											+ promotionpepsi_list.get(j)
													.getRemarks()
											+ "[/REMARK][COMPLIANCE]"
											+ promotionpepsi_list.get(j)
													.getAvailable()
											+ "[/COMPLIANCE]" + "[IMAGE_URL]"
											+ pro_image
											+ "[/IMAGE_URL][USER_ID]"
											+ username
											+ "[/USER_ID][/USER_DATA][/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_PROMOTION_PEPSI_UPLOAD);
									request.addProperty("onXML", onXML);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_PROMOTION_PEPSI_UPLOAD,
													envelope);
									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.METHOD_PROMOTION_PEPSI_UPLOAD;
										}

										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_PROMOTION_PEPSI_UPLOAD
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}

									runOnUiThread(new Runnable() {

										public void run() { // TODO
															// Auto-generated
															// method

											message.setText("Promotion Pepsi Data Upload");
										}
									});
								}
							}
							// PROMOTION com
							promo_list = database
									.gePromotionOtherMid(coverageBeanlist
											.get(i).getMID());

							if (!(promo_list.size() == 0)) {

								for (int j = 0; j < promo_list.size(); j++) {

									String pro_image = "";
									if (new File("/mnt/sdcard/PepsicoImages/"
											+ promo_list.get(j).getImage())
											.exists()) {
										pro_image = promo_list.get(j)
												.getImage();
									} else {
										pro_image = "";
									}

									onXML = "[DATA][USER_DATA][MID]" + mid
											+ "[/MID][COMPANY_ID]"
											+ promo_list.get(j).getCompanyid()
											+ "[/COMPANY_ID][BRAND_ID]"
											+ promo_list.get(j).getBrandid()
											+ "[/BRAND_ID]" + "[REMARK]"
											+ promo_list.get(j).getRemarks()
											+ "[/REMARK][IMAGE_URL]"
											+ pro_image
											+ "[/IMAGE_URL][USER_ID]"
											+ username
											+ "[/USER_ID][/USER_DATA][/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_PROMOTION_COM_UPLOAD);
									request.addProperty("onXML", onXML);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_PROMOTION_COM_UPLOAD,
													envelope);
									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.METHOD_PROMOTION_COM_UPLOAD;
										}

										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_PROMOTION_COM_UPLOAD
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}

									runOnUiThread(new Runnable() {

										public void run() { // TODO
															// Auto-generated
															// method

											message.setText("Promotion Competitor Data Upload");
										}
									});
								}
							}

						}

						database.updateCoverageStatus(coverageBeanlist.get(i)
								.getMID(), CommonString.KEY_D);

						database.deleteSosBrandStatusData(coverageBeanlist.get(
								i).getStoreId());

						// SET COVERAGE STATUS
						String statusxml = "[DATA][USER_DATA][USER_ID]"
								+ username + "[/USER_ID][STORE_ID]"
								+ coverageBeanlist.get(i).getStoreId()
								+ "[/STORE_ID][VISIT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/VISIT_DATE][STATUS]" + CommonString.KEY_D
								+ "[/STATUS][/USER_DATA][/DATA]";

						request = new SoapObject(CommonString.NAMESPACE,
								CommonString.METHOD_SET_COVERAGE_STATUS);

						request.addProperty("onXML", statusxml);

						envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport.call(
								CommonString.SOAP_ACTION_SET_COVERAGE_STATUS,
								envelope);
						result = (Object) envelope.getResponse();

						if (result.toString().equalsIgnoreCase(
								CommonString.KEY_SUCCESS)) {

						}

					}
				}

				// Geo Data

				for (int i = 0; i < geotaglist.size(); i++) {

					runOnUiThread(new Runnable() {

						public void run() {
							// TODO Auto-generated method stub
							k = k + factor;
							pb.setProgress(k);
							percentage.setText(k + "%");
							message.setText("Uploading Geotag Data");
						}
					});

					String onXML = "[DATA][USER_DATA][STORE_ID]"
							+ Integer.parseInt(geotaglist.get(i).getStoreid())
							+ "[/STORE_ID][LATITUDE]"
							+ Double.toString(geotaglist.get(i).getLatitude())
							+ "[/LATITUDE][LONGITUDE]"
							+ Double.toString(geotaglist.get(i).getLongitude())
							+ "[/LONGITUDE][IMAGE_URL1]"
							+ geotaglist.get(i).getUrl1() + "[/IMAGE_URL1]"
							+ "[IMAGE_URL2]" + geotaglist.get(i).getUrl2()
							+ "[/IMAGE_URL2][IMAGE_URL3]"
							+ geotaglist.get(i).getUrl3()
							+ "[/IMAGE_URL3][CREATED_BY]" + username
							+ "[/CREATED_BY][/USER_DATA][/DATA]";

					SoapObject request = new SoapObject(CommonString.NAMESPACE,
							CommonString.METHOD_UPLOAD_GEOTAG);
					request.addProperty("onXML", onXML);

					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					envelope.dotNet = true;
					envelope.setOutputSoapObject(request);

					HttpTransportSE androidHttpTransport = new HttpTransportSE(
							CommonString.URL);

					androidHttpTransport.call(
							CommonString.SOAP_ACTION_UPLOAD_GEOTAG, envelope);
					Object result = (Object) envelope.getResponse();

					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_SUCCESS)) {

						database.updateGeoTagData(geotaglist.get(i)
								.getStoreid());

					} else {

						if (result.toString().equalsIgnoreCase(
								CommonString.KEY_FALSE)) {
							return CommonString.METHOD_UPLOAD_GEOTAG;
						}

						// for failure
						FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
						xmlR.setContentHandler(failureXMLHandler);

						InputSource is = new InputSource();
						is.setCharacterStream(new StringReader(result
								.toString()));
						xmlR.parse(is);

						failureGetterSetter = failureXMLHandler
								.getFailureGetterSetter();

						if (failureGetterSetter.getStatus().equalsIgnoreCase(
								CommonString.KEY_FAILURE)) {
							return CommonString.METHOD_UPLOAD_GEOTAG + ","
									+ failureGetterSetter.getErrorMsg();
						}
					}

				}

				return CommonString.KEY_SUCCESS;
			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						UploadAllDataActivity.this,
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
						UploadAllDataActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_uploadall", e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						message.showMessage();
						// TODO Auto-generated method stub

						/*
						 * if (counter < 3) { new
						 * UploadTask(UploadAllDataActivity.this) .execute(); }
						 * else { message.showMessage(); counter = 1; }
						 */

					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						UploadAllDataActivity.this,
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

				startActivity(new Intent(context, UploadAllImageActivity.class));

				finish();

			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						UploadAllDataActivity.this, result, "success", null);
				message.showMessage();
			}

		}

	}
}
