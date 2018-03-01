package com.cpm.dailyEntry;

import java.io.IOException;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Calendar;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CheckoutBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class CheckOutStoreActivity extends Activity {

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String username, visit_date, store_id;
	private Data data;
	private PepsicoDatabase db;
	private SharedPreferences preferences = null;
	private CheckoutBean checkout = new CheckoutBean();
	private FailureGetterSetter failureGetterSetter = null;
	static int counter = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");

		db = new PepsicoDatabase(this);
		db.open();

		new BackgroundTask(this).execute();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		db.open();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		db.close();
	}

	private class BackgroundTask extends AsyncTask<Void, Data, String> {
		private Context context;

		BackgroundTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("Uploading Checkout Data");
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

				data = new Data();

				data.value = 20;
				data.name = "Checkout Data Uploading";
				publishProgress(data);

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				String onXML = "[DATA][USER_DATA][USER_ID]" + username
						+ "[/USER_ID]" + "[LATITUDE]" + 0.0
						+ "[/LATITUDE][LONGITUDE]" + 0.0
						+ "[/LONGITUDE] [CHECKOUT_DATE]" + visit_date
						+ "[/CHECKOUT_DATE][CHECK_OUTTIME]" + getCurrentTime()
						+ "[/CHECK_OUTTIME][CREATED_BY]" + username
						+ "[/CREATED_BY][STORE_ID]" + store_id
						+ "[/STORE_ID][/USER_DATA][/DATA]";

				SoapObject request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_Checkout_StatusNew);
				request.addProperty("onXML", onXML);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				data.value = 40;
				data.name = "Checkout from store";
				publishProgress(data);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_Checkout_StatusNew, envelope);
				Object result = (Object) envelope.getResponse();

				
			
				data.value = 100;
				data.name = "Checkout Done";
				publishProgress(data);

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

					SharedPreferences.Editor editor = preferences.edit();
					editor.putString(CommonString.KEY_STOREVISITED, store_id);
					editor.putString(CommonString.KEY_STOREVISITED_STATUS, "no");
					editor.commit();

				db.updateStoreStatusOnCheckout(store_id, visit_date,
							CommonString.KEY_C);

				} else {
					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_FALSE)) {
						return CommonString.METHOD_Checkout_StatusNew;
					}

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
						return CommonString.METHOD_Checkout_StatusNew + ","
								+ failureGetterSetter.getErrorMsg();
					}

				}
				return CommonString.KEY_SUCCESS;

			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						CheckOutStoreActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "acra_checkout", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						CheckOutStoreActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_checkout_store", e);
				// counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						message.showMessage();
						// TODO Auto-generated method stub
						/*
						 * if (counter < 10) { new
						 * BackgroundTask(CheckOutUploadActivity
						 * .this).execute(); } else { message.showMessage();
						 * counter =1; }
						 */
					}
				});
			} catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						CheckOutStoreActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "acra_checkout", e);
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
		protected void onProgressUpdate(Data... values) {
			// TODO Auto-generated method stub

			pb.setProgress(values[0].value);
			percentage.setText(values[0].value + "%");
			message.setText(values[0].name);

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {

				AlertMessage message = new AlertMessage(
						CheckOutStoreActivity.this,
						AlertMessage.MESSAGE_CHECKOUT, "checkout", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						CheckOutStoreActivity.this, AlertMessage.MESSAGE_ERROR
								+ result, "checkoutfail", null);
				message.showMessage();
			}

		}

	}

	class Data {
		int value;
		String name;
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();
		int hour = m_cal.get(Calendar.HOUR_OF_DAY);
		int min = m_cal.get(Calendar.MINUTE);

		String intime = "";

		if (hour == 0) {
			intime = "" + 12 + ":" + min + " AM";
		} else if (hour == 12) {
			intime = "" + 12 + ":" + min + " PM";
		} else {

			if (hour > 12) {
				hour = hour - 12;
				intime = "" + hour + ":" + min + " PM";
			} else {
				intime = "" + hour + ":" + min + " AM";
			}
		}
		return intime;

	}

}
