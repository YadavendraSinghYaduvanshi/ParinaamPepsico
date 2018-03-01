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

import org.apache.http.client.HttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.dailyEntry.DailyentryMenuActivity;
import com.cpm.dailyEntry.SodMasterActivity;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CategoryBean;
import com.cpm.delegates.CateogryDataDelegates;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.FinalDataBeanDisplay;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.OosEntryBean;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.PromoBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsico.LoginActivity;
import com.cpm.parinaampepsico.MyApplication;
import com.cpm.parinaampepsicoo.R;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class UploadDataActivity extends Activity {

	private Dialog dialog;
	Intent intent;
	private ProgressBar pb;
	private TextView percentage, message;
	private String visit_date, username;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	private String reasonid, faceup, stock, length;
	private int factor,k;
	String datacheck = "";
	String[] words;
	String validity, storename;
	int mid;
	String sod = "";
	String total_sku = "";
	String sku = "";
	String sos_data = "";
	String category_data = "";

	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<PosmBean> assetlist = new ArrayList<PosmBean>();
	private ArrayList<VericalBrandBean> availablelist = new ArrayList<VericalBrandBean>();
	
	
	ArrayList<GeotaggingBeans> gdata = new ArrayList<GeotaggingBeans>();

	
	private ArrayList<OosEntryBean> ooSlist = new ArrayList<OosEntryBean>();
	private ArrayList<FinalDataBeanDisplay> displaylist = new ArrayList<FinalDataBeanDisplay>();
	private ArrayList<FinalDataBeanDisplay> displayChillerdata = new ArrayList<FinalDataBeanDisplay>();
	
	private ArrayList<FinalDataBeanDisplay> displayDataLBH = new ArrayList<FinalDataBeanDisplay>();
	private ArrayList<FinalDataBeanDisplay> displayDataSku = new ArrayList<FinalDataBeanDisplay>();
	
	ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();

	
	
	
	
	ArrayList<GeotaggingBeans> geotaglist = new ArrayList<GeotaggingBeans>();
	private ArrayList<SodBean> master_list = new ArrayList<SodBean>();
	private ArrayList<CateogryDataDelegates> cateogryimgdata = new ArrayList<CateogryDataDelegates>();
	private ArrayList<PromotionBean> promo_list = new ArrayList<PromotionBean>();
	private ArrayList<PromoBean> promotionpepsi_list = new ArrayList<PromoBean>();
	private FailureGetterSetter failureGetterSetter = null;
	StoreBean storestatus = new StoreBean();
	static int counter = 1;
	String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_option);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username = preferences.getString(CommonString.KEY_USERNAME, null);
		date = preferences.getString(CommonString.KEY_DATE, null);
		database = new PepsicoDatabase(this);
		database.open();

		new UploadTask(this).execute();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// database.close();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, DailyentryMenuActivity.class);
		startActivity(i);
		UploadDataActivity.this.finish();
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
				coverageBeanlist = database.getCoverageData(visit_date, null);
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

					if ((storestatus.getCheckout_status().equalsIgnoreCase(
							CommonString.KEY_L) || storestatus
							.getCheckout_status().equalsIgnoreCase(
									CommonString.KEY_C))) {

						if (!coverageBeanlist.get(i).getStatus()
								.equalsIgnoreCase("D")) {

							String image= "",contactid = "";
							if (new File("/mnt/sdcard/PepsicoImages/"
									+ coverageBeanlist.get(i).getImage())
									.exists()) {
								image = coverageBeanlist.get(i).getImage();
							} else {
								image = "";
							}

							if (coverageBeanlist.get(i).getKeycontactId().equals("")) {
								contactid = "0";
							} else {
								contactid = coverageBeanlist.get(i).getKeycontactId();
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
									+ "[/INFORMED_TO][KEY_ID]"
									+ contactid
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

							if (validity
									.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
								database.updateCoverageStatus(coverageBeanlist
										.get(i).getMID(), CommonString.KEY_P);

								database.updateStoreStatusOnLeave(
										coverageBeanlist.get(i).getStoreId(),
										visit_date, CommonString.KEY_P);
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
										.equalsIgnoreCase(
												CommonString.KEY_FAILURE)) {
									return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE
											+ ","
											+ failureGetterSetter.getErrorMsg();
								}
							}
							mid = Integer.parseInt((words[1]));

							storename = database.getStoreName(coverageBeanlist
									.get(i).getStoreId());

							runOnUiThread(new Runnable() {

								public void run() {
									// TODO Auto-generated method stub
									k = k + factor;
									pb.setProgress(k);
									percentage.setText(k + "%");
									message.setText(storename
											+ " Data Are Uploading");
								}
							});

							if (!(coverageBeanlist.get(i).getReasonid()
									.equalsIgnoreCase("")
									|| coverageBeanlist.get(i).getReasonid()
											.equalsIgnoreCase(null) || coverageBeanlist
									.get(i).getReasonid().equalsIgnoreCase("0"))) {

								/*
								 * database.updateStoreStatusOnCheckout(
								 * coverageBeanlist.get(i).getStoreId(),
								 * visit_date, CommonString.KEY_L);
								 */
								System.out.println("");
							} else {
								
						
								// daily reporting data
								String sku_Dataoose;
								String TotalDataOOSE="";
								
								ooSlist =  database.getPepsiDataOOS(coverageBeanlist.get(i).getStoreId(),visit_date);
								if(ooSlist.size()>0){
									
									for(int noOfOos = 0; noOfOos<ooSlist.size() ; noOfOos++){
										
										if(ooSlist.get(noOfOos).getStock()==null){
											ooSlist.get(noOfOos).setStock("select stock");
										}

										sku_Dataoose = "[DISPLAY_DETAIL]"
											+ "[SKU_ID]"
											+ ooSlist.get(noOfOos).getSku_id()
											+ "[/SKU_ID][STOCK_QTY]"
											+ ooSlist.get(noOfOos).getStock()
											+ "[/STOCK_QTY][MID]"
											+ mid
											+"[/MID]"										
											+ "[USER_ID]"
											+ username + "[/USER_ID][/DISPLAY_DETAIL]";
									
									
										TotalDataOOSE=TotalDataOOSE + sku_Dataoose;
										
									}
									
									
									final String skuDataXml = "[DATA]"
											+ TotalDataOOSE + "[/DATA]";

									// ashish replacing the services
								
//									request = new SoapObject(
//											CommonString.NAMESPACE,
//											CommonString.METHOD_UPLOAD_ASSET_SKU_OOSE);
	//
//									request.addProperty("onXML", skuDataXml);
									

									request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_ASSET_SKU_OOSE_NEW_SERVICE);
									request.addProperty("MID", mid);
							        request.addProperty("KEYS", "DAILYENTRY");
							        request.addProperty("USERNAME", username);
							        request.addProperty("XMLDATA", skuDataXml);
									
									
									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport.call(
											CommonString.SOAP_ACTION_ASSET_UPLOAD_SKU_OOSE_NEW_SERVICE,
											envelope);
									Object	resultsku = (Object) envelope.getResponse();
								}
								

									//
									
									
								
	//oose completed ///////////////////							
								
								
							
								// add sod

								master_list = database.getSodDataPepsico(
										coverageBeanlist.get(i).getStoreId(),
										coverageBeanlist.get(i).getMID());
								sku = "";
								total_sku = "";
								sod = "";
								if (!(master_list.size() == 0)) {

									for (int j = 0; j < master_list.size(); j++) {

//										trans_list = database
//												.getTransData(Integer
//														.parseInt(master_list
//																.get(j).getId()));

										String left_image = "";
										String ryt_image = "";
										String center_image = "";

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ master_list.get(j)
																.getImg1())
												.exists()) {
											left_image = master_list.get(j)
													.getImg1();
										} else {
											left_image = "";
										}
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ master_list.get(j)
																.getImg2())
												.exists()) {
											ryt_image = master_list.get(j)
													.getImg2();
										} else {
											ryt_image = "";
										}
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ master_list.get(j)
																.getImg3())
												.exists()) {
											center_image = master_list.get(j)
													.getImg3();
										} else {
											center_image = "";
										}
										

										String dom1 = "1/1/1900", dom2 = "1/1/1900", dom3 = "1/1/1900";
										if (master_list.get(j).getDom1()
												.equalsIgnoreCase("")
												|| master_list
														.get(j)
														.getDom1()
														.equalsIgnoreCase(
																"MFD")) {

										} else {
											dom1 = master_list.get(j)
													.getDom1();
										}

										if (master_list.get(j).getDom2()
												.equalsIgnoreCase("")
												|| master_list
														.get(j)
														.getDom2()
														.equalsIgnoreCase(
																"MFD")) {

										} else {
											dom2 = master_list.get(j)
													.getDom2();
										}

										if (master_list.get(j).getDom3()
												.equalsIgnoreCase("")
												|| master_list
														.get(j)
														.getDom3()
														.equalsIgnoreCase(
																"MFD")) {

										} else {
											dom3 = master_list.get(j)
													.getDom3();
										}

										String display_details = "[DISPLAY_DETAIL][MID]"
												+ mid
												+ "[/MID][SOD_ID]"
												+ master_list.get(j).getSodid()
												+ "[/SOD_ID][COMPANY_ID]"
												+ master_list.get(j)
														.getCompanyid()
												+ "[/COMPANY_ID][POSM_ID]"
												+ master_list.get(j)
														.getPosmid()
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
												+ "[/CENTER_IMAGE]"
												+ "[SKU_ID]"
													+ master_list.get(j)
															.getSkuid()
													+ "[/SKU_ID][STOCK_QTY]"
													+ master_list.get(j)
															.getStock()
													+ "[/STOCK_QTY][FACEUP]"
													+ master_list.get(j)
															.getFaceup()
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
													+ "[/USER_ID]"+"[/DISPLAY_DETAIL]";
										sod = sod + display_details;
										
									}

									String Sod_XML = "[DATA]" + sod + "[/DATA]";

//									request = new SoapObject(
//											CommonString.NAMESPACE,
//											CommonString.METHOD_UPLOAD_SOD_DATA);
									
									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_ASSET_SKU_OOSE_NEW_SERVICE);
									
									//request.addProperty("onXML", Sod_XML);
									
									
									request.addProperty("MID", mid);
							        request.addProperty("KEYS", "ADDITIONAL_DISPLAY");
							        request.addProperty("USERNAME", username);
							        request.addProperty("XMLDATA", Sod_XML);
									
								

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

//									androidHttpTransport
//											.call(CommonString.SOAP_ACTION_UPLOAD_SOD,
//													envelope);
									
									androidHttpTransport.call(
											CommonString.SOAP_ACTION_ASSET_UPLOAD_SKU_OOSE_NEW_SERVICE,
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

										if (failureGetterSetter
												.getStatus()
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
////////////////////////// Additional Display Complete /////////////////////////
								
								// assests Data
								
								
		displayChillerdata=database.getTotalDisplayTransactionData(
										coverageBeanlist.get(i).getStoreId());
								if(displayChillerdata.size()>0){
									  String totalAssetData ="";
									  
									  String displaydata;
									for(int assetData = 0;assetData<displayChillerdata.size();assetData++){
										displaydata ="[DISPLAY_DETAIL]"+ "[AUTO_ID]" +displayChillerdata.get(assetData).getAuto_Id() +"[/AUTO_ID]"+
									"[ASSET_ID]"+displayChillerdata.get(assetData).getAssets_Id()+"[/ASSET_ID]"+
									"[SKU_ID]"+displayChillerdata.get(assetData).getSku_Id()+"[/SKU_ID]"+
									"[STORE_ID]"+displayChillerdata.get(assetData).getStore_id()+"[/STORE_ID]"+
									"[DOME1]"+displayChillerdata.get(assetData).getDom1()+"[/DOME1]"+
									"[STOCK]"+displayChillerdata.get(assetData).getStock()+"[/STOCK]"+
									"[REMARKS]"+displayChillerdata.get(assetData).getRemark()+"[/REMARKS]"+
									"[AVAILABLE]"+displayChillerdata.get(assetData).getAvailable()+"[/AVAILABLE]"+
									"[VERTICAL_ID]"+displayChillerdata.get(assetData).getVertical_ID()+"[/VERTICAL_ID]"+
									"[ASSET_PURE]"+displayChillerdata.get(assetData).getAsset_Pure()+"[/ASSET_PURE]"+
									"[BRAND_ID]"+displayChillerdata.get(assetData).getBrandId()+"[/BRAND_ID]"+
									"[HEIGHT]"+"0"+"[/HEIGHT]"+
									"[BREATH]"+"0"+"[/BREATH]"+
									"[LENGTH]"+"0"+"[/LENGTH]"+
									"[IMG1]"+displayChillerdata.get(assetData).getImage_Path1()+"[/IMG1]"+
									"[IMG2]"+displayChillerdata.get(assetData).getImage_Path2()+"[/IMG2]"+
									"[IMG3]"+displayChillerdata.get(assetData).getImage_Path3()+"[/IMG3]"+
									"[MID]"+mid+"[/MID]"+
									"[USER_ID]"+username+"[/USER_ID]"+
									"[RANDOM]"+displayChillerdata.get(assetData).getRandomnumber()+"[/RANDOM]"+
									"[FACEUP]"+displayChillerdata.get(assetData).getFaceup()+"[/FACEUP]"+
									"[/DISPLAY_DETAIL]";
										totalAssetData = totalAssetData +displaydata;
									}
									String xmlData = "[DATA]" + totalAssetData + "[/DATA]";
									
									
									
									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_ASSET_SKU_OOSE_NEW_SERVICE);
											request.addProperty("MID", mid);
									        request.addProperty("KEYS", "ASSET");
									        request.addProperty("USERNAME", username);
									        request.addProperty("XMLDATA", xmlData);
											
											
											envelope = new SoapSerializationEnvelope(
													SoapEnvelope.VER11);
											envelope.dotNet = true;
											envelope.setOutputSoapObject(request);

											androidHttpTransport = new HttpTransportSE(
													CommonString.URL);

											androidHttpTransport.call(
													CommonString.SOAP_ACTION_ASSET_UPLOAD_SKU_OOSE_NEW_SERVICE,
													envelope);
											Object	res = (Object) envelope.getResponse();
									
									System.out.println("ddd"+res);
								}
						
								
//								displayChillerdata=database.getDisplayTransactionData_chillars(
//										coverageBeanlist.get(i).getStoreId());
								
								
//								for(int cvv=0;cvv<displayChillerdata.size();cvv++)
//								
//								{
//								
//					
//								displaylist = database.getDisplayTransactionData_main(
//										displayChillerdata.get(cvv).getAssets_Id(),displayChillerdata.get(cvv).getSecondary());
//								
//								
//								
//								
//								for(int noofassetID =0; noofassetID<displaylist.size();noofassetID++)
//								
//								{
//								
//
//								String val="True";
//								
//								String vesipure=null;
//								if(displaylist.get(noofassetID).getAsset_Pure().equalsIgnoreCase("True")||displaylist.get(noofassetID).getAsset_Pure().equalsIgnoreCase("Yes"))
//								{
//									vesipure="1";
//									
//								}
//								else{
//									vesipure="0";
//								}
//								
//								
//								
//								String presence=null;
//								
//								if(displaylist.get(noofassetID).getAvailable().equalsIgnoreCase("yes")||displaylist.get(noofassetID).getAvailable().equalsIgnoreCase("true"))
//								{
//									presence="1";
//									
//								}
//								else{
//									presence="0";
//								}
//								
//								
//									
//									String display_chillerdata = "[DATA][DISPLAY_DETAIL]"
//											+ "[MID]"
//											+ mid
//											+ "[/MID][ASSET_ID]"
//											+ displaylist.get(noofassetID).getAssets_Id()
//											+ "[/ASSET_ID][PRESENCE]"
//											+ presence
//											+ "[/PRESENCE][REMARK]"
//											+ displaylist.get(noofassetID)
//													.getRemark()
//											+ "[/REMARK]"
//											+ "[RIGHT_IMAGE]"
//											+ displaylist.get(noofassetID)
//											.getImage_Path1()
//											+ "[/RIGHT_IMAGE][LEFT_IMAGE]"
//											+displaylist.get(noofassetID)
//											.getImage_Path2()
//											+ "[/LEFT_IMAGE]"
//											+ "[CENTER_IMAGE]"
//											+displaylist.get(noofassetID)
//											.getImage_Path3()
//											+ "[/CENTER_IMAGE][VERTICAL_ID]"
//											+displaylist.get(noofassetID)
//											.getVertical_ID()
//											+ "[/VERTICAL_ID]"
//											+ "[VISI_PURE]"
//											+vesipure			
//											+ "[/VISI_PURE] [BRAND]"
//											+displaylist.get(noofassetID)
//											.getBrandId()
//										
//											+ "[/BRAND][USER_ID]"
//											+ username + "[/USER_ID]"
//													+ "[/DISPLAY_DETAIL][/DATA]";
//									
//									request = new SoapObject(
//											CommonString.NAMESPACE,
//											CommonString.METHOD_UPLOAD_ASSET_NEW);
//
//									request.addProperty("onXML", display_chillerdata);
//									
//
//									envelope = new SoapSerializationEnvelope(
//											SoapEnvelope.VER11);
//									envelope.dotNet = true;
//									envelope.setOutputSoapObject(request);
//
//									androidHttpTransport = new HttpTransportSE(
//											CommonString.URL);
//
//									androidHttpTransport.call(
//											CommonString.SOAP_ACTION_ASSET_UPLOAD_NEW,
//											envelope);
//									Object	result1 = (Object) envelope.getResponse();
//
//									datacheck = result1.toString();
//									words = datacheck.split("\\;");
//									validity = (words[1]);
//									
//									
//								
//									
//								
//								
//								
//									displayDataLBH = database.getDisplayTransactionData_LBH(
//											
//								(Long.parseLong(displaylist.get(noofassetID).getAssets_Id())),displaylist.get(noofassetID).getSecondary());
//									
//								/*	AID
//									ASSET_LENGTH
//									ASSET_BREADTH
//									ASSET_HEIGHT
//*/
//									
//									
//									String lbhData = "[DISPLAY_DETAIL][AID]"
//											+ validity
//											+ "[/AID][ASSET_LENGTH]"
//											+ displayDataLBH.get(0).getLenth()
//											+ "[/ASSET_LENGTH][ASSET_BREADTH]"
//											+ displayDataLBH.get(0).getBreath()
//											+ "[/ASSET_BREADTH][ASSET_HEIGHT]"
//											+displayDataLBH.get(0).getHeight()
//											+"[/ASSET_HEIGHT]"
//											+ "[USER_ID]"
//											+ username + "[/USER_ID]"
//													+ "[/DISPLAY_DETAIL]";
//									
//									
//									
//									
//									request = new SoapObject(
//											CommonString.NAMESPACE,
//											CommonString.METHOD_UPLOAD_ASSET_SIZE);
//
//									request.addProperty("onXML", lbhData);
//									
//
//									envelope = new SoapSerializationEnvelope(
//											SoapEnvelope.VER11);
//									envelope.dotNet = true;
//									envelope.setOutputSoapObject(request);
//
//									androidHttpTransport = new HttpTransportSE(
//											CommonString.URL);
//
//									androidHttpTransport.call(
//											CommonString.SOAP_ACTION_ASSET_UPLOAD_SIZE,
//											envelope);
//									Object	resultlbh = (Object) envelope.getResponse();
//
//
//									datacheck = resultlbh.toString();
//									words = datacheck.split("\\;");
//									validity = (words[1]);
//									
//									
//									// uploading sku
//									
//									
//									displayDataSku = database.getDisplayTransactionData_SkuData(
//											
//											(Long.parseLong(displaylist.get(noofassetID).getAssets_Id())),displaylist.get(noofassetID).getSecondary());
//									
//									String skuData;
//									String TotalData = "";
//									for(int p= 0; p <displayDataSku.size(); p++){
//										
//										
//										
//										/*SID
//										SKU_ID
//										STOCK_QTY
//										FACEUP
//										DOM*/
//
//										
//										String domvalue = displayDataSku.get(p).getDom1();
//										if(domvalue.equalsIgnoreCase(""))
//										{
//											domvalue="1/1/1900";
//										}
//										
//												
//									
//									
//										skuData = "[DISPLAY_DETAIL][SID]"
//											+ validity
//											+ "[/SID][SKU_ID]"
//											+ displayDataSku.get(p).getSku_Id()
//											+ "[/SKU_ID][STOCK_QTY]"
//											+ displayDataSku.get(p).getStock()
//											+ "[/STOCK_QTY][FACEUP]"
//											+displayDataSku.get(p).getFaceup()
//											+"[/FACEUP]"										
//											+ "[DOM]"
//											+domvalue
//											+"[/DOM]"			
//											+ "[USER_ID]"
//											+ username + "[/USER_ID][/DISPLAY_DETAIL]";
//									
//									
//										TotalData=TotalData + skuData;
//										
//									}
//									
//									
//									final String skuDataXml2 = "[DATA]"
//											+ TotalData + "[/DATA]";
//
//									
//								
//									request = new SoapObject(
//											CommonString.NAMESPACE,
//											CommonString.METHOD_UPLOAD_ASSET_SKU);
//
//									request.addProperty("onXML", skuDataXml2);
//									
//
//									envelope = new SoapSerializationEnvelope(
//											SoapEnvelope.VER11);
//									envelope.dotNet = true;
//									envelope.setOutputSoapObject(request);
//
//									androidHttpTransport = new HttpTransportSE(
//											CommonString.URL);
//
//									androidHttpTransport.call(
//											CommonString.SOAP_ACTION_ASSET_UPLOAD_SKU,
//											envelope);
//									Object	resultsku2 = (Object) envelope.getResponse();
//									datacheck = resultsku2.toString();
//									
//								
//								
//								}
//								
//						
//								}
								
								
				// /////////////////// Asset Data is completed ///////////////////////////		
								

								assetlist = database
										.getAssetData(coverageBeanlist.get(i)
												.getMID());

								if (!(assetlist.size() == 0)) {

									for (int j = 0; j < assetlist.size(); j++) {

										String left_image = "";
										String ryt_image = "";
										String center_image = "";

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ assetlist.get(j)
																.getImage1())
												.exists()) {
											left_image = assetlist.get(j)
													.getImage1();
										} else {
											left_image = "";
										}
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ assetlist.get(j)
																.getImage2())
												.exists()) {
											ryt_image = assetlist.get(j)
													.getImage2();
										} else {
											ryt_image = "";
										}
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ assetlist.get(j)
																.getImage3())
												.exists()) {
											center_image = assetlist.get(j)
													.getImage3();
										} else {
											center_image = "";
										}
										
										String assetvalue="";
										if(assetlist.get(j).getAssetpure().equalsIgnoreCase("YES"))
										{
											assetvalue="1";
										}
										else
										{
											assetvalue = "0";
										}
										
										if(assetlist.get(j).getAssetpure().equalsIgnoreCase(""))
										{
											assetvalue = "0";
										}

										onXML = "[DATA][USER_DATA][MID]"
												+ mid
												+ "[/MID][ASSEST_ID]"
												+ assetlist.get(j).getAssetid()
												+ "[/ASSEST_ID][PRESENCE]"
												+ assetlist.get(j)
														.getAvailable()
												+ "[/PRESENCE]"
												+ "[REMARKS]"
												+ assetlist.get(j).getRemarks()
												+ "[/REMARKS][VISI_PURE]"
												+ assetvalue
												+ "[/VISI_PURE]"
												+ "[LEFT_IMAGE]"
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
												+ assetlist.get(j)
														.getVerticalid()
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
										result = (Object) envelope
												.getResponse();

										if (!result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_SUCCESS))
										{

											if (result
													.toString()
													.equalsIgnoreCase(
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

											
											Intent mail = new Intent(android.content.Intent.ACTION_SEND);
											mail.setType("text/plain");
											mail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"santosh.yadav@in.cpm-int.com"});
											mail.putExtra(Intent.EXTRA_SUBJECT, " vesipure Error " + username);
											mail.putExtra(Intent.EXTRA_TEXT   , onXML);
											startActivity(Intent.createChooser(mail, "Email:"));
											
											
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

								// CATEGORY IMAGES

								cateogryimgdata = database
										.getCateogrydataData(coverageBeanlist
												.get(i).getStoreId());
								category_data = "";
								if (!(cateogryimgdata.size() == 0)) {

									for (int j = 0; j < cateogryimgdata.size(); j++) {

										String left_image = "";
										String ryt_image = "";
										String center_image = "";

										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ cateogryimgdata
																.get(j)
																.getImageurl())
												.exists()) {
											left_image = cateogryimgdata.get(j)
													.getImageurl();
										} else {
											left_image = "";
										}
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ cateogryimgdata
																.get(j)
																.getImageurl1())
												.exists()) {
											ryt_image = cateogryimgdata.get(j)
													.getImageurl1();
										} else {
											ryt_image = "";
										}
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ cateogryimgdata
																.get(j)
																.getImageurl2())
												.exists()) {
											center_image = cateogryimgdata.get(
													j).getImageurl2();
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

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_UPLOAD_CATEGORY_IMAGES
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}

									runOnUiThread(new Runnable() {

										public void run() { // TODO
															// Auto-generated
															// method

											message.setText("Category Data Upload");
										}
									});
								}

								// PROMOTION pepsi
								promotionpepsi_list = database
										.gePromotionPepsiData(coverageBeanlist
												.get(i).getMID());
                                 
								if (!(promotionpepsi_list.size() == 0)) {
									 String promotionData="";
									 String totaldata ="";
									for (int j = 0; j < promotionpepsi_list
											.size(); j++) {
										String data ="";
										String pro_image = "";
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ promotionpepsi_list
																.get(j)
																.getImage())
												.exists()) {
											pro_image = promotionpepsi_list
													.get(j).getImage();
										} else {
											pro_image = "";
										}

										onXML = "[USER_DATA][MID]"
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
												+ "[/COMPLIANCE]"
												+ "[IMAGE_URL]"
												+ pro_image
												+ "[/IMAGE_URL][USER_ID]"
												+ username
												+ "[/USER_ID][/USER_DATA]";
										totaldata =totaldata + data+onXML;
										
									}
                                     promotionData = "[DATA]"+ totaldata + "[/DATA]";
//										request = new SoapObject(
//												CommonString.NAMESPACE,
//												CommonString.METHOD_PROMOTION_PEPSI_UPLOAD);
//										request.addProperty("onXML", onXML);
										
										
										request = new SoapObject(
												CommonString.NAMESPACE,
												CommonString.METHOD_UPLOAD_ASSET_SKU_OOSE_NEW_SERVICE);
												request.addProperty("MID", mid);
										        request.addProperty("KEYS", "PROMOTION");
										        request.addProperty("USERNAME", username);
										        request.addProperty("XMLDATA", promotionData);
										
										

										envelope = new SoapSerializationEnvelope(
												SoapEnvelope.VER11);
										envelope.dotNet = true;
										envelope.setOutputSoapObject(request);

										androidHttpTransport = new HttpTransportSE(
												CommonString.URL);

//										androidHttpTransport
//												.call(CommonString.SOAP_ACTION_PROMOTION_PEPSI_UPLOAD,
//														envelope);
										
										androidHttpTransport
										.call(CommonString.SOAP_ACTION_ASSET_UPLOAD_SKU_OOSE_NEW_SERVICE,
												envelope);
										
										
										result = (Object) envelope
												.getResponse();

										if (!result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_SUCCESS)) {

											if (result
													.toString()
													.equalsIgnoreCase(
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
									//}
								}
								// PROMOTION com
								promo_list = database
										.gePromotionOtherMid(coverageBeanlist
												.get(i).getMID());

								if (!(promo_list.size() == 0)) {

									for (int j = 0; j < promo_list.size(); j++) {

										String pro_image = "";
										if (new File(
												"/mnt/sdcard/PepsicoImages/"
														+ promo_list.get(j)
																.getImage())
												.exists()) {
											pro_image = promo_list.get(j)
													.getImage();
										} else {
											pro_image = "";
										}

										onXML = "[DATA][USER_DATA][MID]"
												+ mid
												+ "[/MID][COMPANY_ID]"
												+ promo_list.get(j)
														.getCompanyid()
												+ "[/COMPANY_ID][BRAND_ID]"
												+ promo_list.get(j)
														.getBrandid()
												+ "[/BRAND_ID]"
												+ "[REMARK]"
												+ promo_list.get(j)
														.getRemarks()
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
										result = (Object) envelope
												.getResponse();

										if (!result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_SUCCESS)) {

											if (result
													.toString()
													.equalsIgnoreCase(
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

								// sos upload

								int count = database.getSosDataMid(
										coverageBeanlist.get(i).getStoreId(),
										coverageBeanlist.get(i).getMID());

								if (count > 0) {

									availablelist = database
											.getAvailableUploadData(coverageBeanlist
													.get(i).getStoreId());
									sos_data = "";

									if (!(availablelist.size() == 0)) {

										for (int j = 0; j < availablelist
												.size(); j++) {

											String dom1 = "1/1/1990", dom2 = "1/1/1990", dom3 = "1/1/1990";
											if (availablelist.get(j)
													.getSkuqty1()
													.equalsIgnoreCase("")) {
												faceup = "0";
											} else {
												faceup = availablelist.get(j)
														.getSkuqty1();
											}

											if (availablelist.get(j)
													.getSkuqty()
													.equalsIgnoreCase("")) {
												stock = "0";
											} else {
												stock = availablelist.get(j)
														.getSkuqty();
											}
											if (availablelist.get(j)
													.getSkuqty2()
													.equalsIgnoreCase("")) {
												length = "0";
											} else {
												length = availablelist.get(j)
														.getSkuqty2();
											}
											if (availablelist.get(j).getDom1()
													.equalsIgnoreCase("mfd")
													|| availablelist
															.get(j)
															.getDom2()
															.equalsIgnoreCase(
																	"")) {

											} else {
												dom1 = availablelist.get(j)
														.getDom1();
											}
											if (availablelist.get(j).getDom2()
													.equalsIgnoreCase("mfd")
													|| availablelist
															.get(j)
															.getDom2()
															.equalsIgnoreCase(
																	"")) {

											} else {
												dom2 = availablelist.get(j)
														.getDom2();
											}
											if (availablelist.get(j).getDom3()
													.equalsIgnoreCase("mfd")
													|| availablelist
															.get(j)
															.getDom3()
															.equalsIgnoreCase(
																	"")) {

											} else {
												dom3 = availablelist.get(j)
														.getDom3();

											}

											if (availablelist.get(j)
													.getReasonid()
													.equalsIgnoreCase(null)
													|| availablelist
															.get(j)
															.getReasonid()
															.equalsIgnoreCase(
																	"")) {
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
													+ "[/SKU_ID][STOCK]"
													+ stock
													+ "[/STOCK][FACEUP]"
													+ faceup
													+ "[/FACEUP][LENGHT]"
													+ length
													+ "[/LENGHT][DOM1]"
													+ dom1
													+ "[/DOM1][DOM2]"
													+ dom2
													+ "[/DOM2][DOM3]"
													+ dom3
													+ "[/DOM3][USER_ID]"
													+ username
													+ "[/USER_ID][STOCK_REASON_ID]"
													+ reasonid
													+ "[/STOCK_REASON_ID]"
													+ "[/USER_DATA]";

											sos_data = sos_data + data;

										}
										final String sos_xml = "[DATA]"
												+ sos_data + "[/DATA]";

										System.out.println(sos_xml);

										request = new SoapObject(
												CommonString.NAMESPACE,
												CommonString.METHOD_UPLOAD_AVAILABILITY);
										request.addProperty("onXML", sos_xml);
										request.addProperty("CREATED_BY",
												username);

										envelope = new SoapSerializationEnvelope(
												SoapEnvelope.VER11);
										envelope.dotNet = true;
										envelope.setOutputSoapObject(request);

										androidHttpTransport = new HttpTransportSE(
												CommonString.URL);

										androidHttpTransport
												.call(CommonString.SOAP_ACTION_UPLOAD_AVAILABILITY,
														envelope);

										result = (Object) envelope
												.getResponse();

										if (!result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_SUCCESS)) {

											if (result
													.toString()
													.equalsIgnoreCase(
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

							}
							

							
							database.open();
							database.updateCoverageStatus(
									coverageBeanlist.get(i).getMID(),
									CommonString.KEY_D);

							database.deleteSosBrandStatusData(coverageBeanlist
									.get(i).getStoreId());

							database.updateStoreStatusOnLeave(coverageBeanlist
									.get(i).getStoreId(), visit_date,
									CommonString.KEY_D);

							// SET COVERAGE STATUS

							String statusxml = "[DATA][USER_DATA][USER_ID]"
									+ username + "[/USER_ID][STORE_ID]"
									+ coverageBeanlist.get(i).getStoreId()
									+ "[/STORE_ID][VISIT_DATE]"
									+ coverageBeanlist.get(i).getVisitDate()
									+ "[/VISIT_DATE][STATUS]"
									+ CommonString.KEY_D
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

							androidHttpTransport
									.call(CommonString.SOAP_ACTION_SET_COVERAGE_STATUS,
											envelope);
							result = (Object) envelope.getResponse();
							
							
						
							

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {
								
								  database.open();
								  database.updateCoverageStatus(
								  coverageBeanlist.get(i).getMID(),
								  CommonString.KEY_D);
								  
								  database.deleteSosBrandStatusData(
								  coverageBeanlist .get(i).getStoreId());
								  
								  database.updateStoreStatusOnLeave(
								  coverageBeanlist .get(i).getStoreId(),
								  visit_date, CommonString.KEY_D);
								 
							} else {
								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FALSE)) {
									// return
									// CommonString.METHOD_SET_COVERAGE_STATUS;
								}

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
								 * if (failureGetterSetter.getStatus()
								 * .equalsIgnoreCase(CommonString.KEY_FAILURE))
								 * { return
								 * CommonString.METHOD_SET_COVERAGE_STATUS + ","
								 * + failureGetterSetter.getErrorMsg(); }
								 */
							}

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
							message.setText("Uploading Geotag Data...");
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
						database.open();

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
						UploadDataActivity.this,
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
						UploadDataActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket_upload",
						e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
						/*
						 * if (counter < 3) { new
						 * UploadTask(UploadDataActivity.this).execute(); } else
						 * { message.showMessage(); counter = 1; }
						 */

					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						UploadDataActivity.this,
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

			
				/*//code for uploading image with data
				
				
				cdata = database.getCoverageData(date, null);
				gdata = database.getGeotaggingData("D");

				if (cdata.size() == 0 && gdata.size() == 0) {

					Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_IMAGE,
							Toast.LENGTH_LONG).show();

				}

				else {

					if (validate() || ValidateGeoTagImage() == 1) {

						intent = new Intent(UploadDataActivity.this, UploadImageActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(getBaseContext(),
								AlertMessage.MESSAGE_DATA_FIRST, Toast.LENGTH_LONG)
								.show();
					}
				}
				
				///code for uploading image with data
*/				
				
				
				AlertMessage message = new AlertMessage(
						UploadDataActivity.this,
						AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
				message.showMessage();
				
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						UploadDataActivity.this, result, "success", null);
				message.showMessage();
			}

		}

	}
	
	public boolean validate() {
		boolean result = false;

		database.open();
		cdata = database.getCoverageData(date, null);

		for (int i = 0; i < cdata.size(); i++) {

			if (cdata.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_D)) {
				result = true;
				break;

			}
		}

		return result;
	}
	
	private int ValidateGeoTagImage() {

		int result = 0;

		gdata = database.getGeotaggingData(CommonString.KEY_D);

		if (gdata.size() > 0) {

			result = 1;
		}
		return result;
	}

}
