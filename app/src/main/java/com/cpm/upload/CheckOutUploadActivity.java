package com.cpm.upload;

import java.io.IOException;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

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

public class CheckOutUploadActivity extends Activity {

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String username, visit_date;
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
				checkout = db.getCheckoutData();

				data.value = 20;
				data.name = "Checkout Data Uploading";
				publishProgress(data);

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				String onXML = "[DATA][USER_DATA][USER_ID]" + username
						+ "[/USER_ID]" + "[LATITUDE]" + checkout.getLatitude()
						+ "[/LATITUDE][LONGITUDE]" + checkout.getLongitude()
						+ "[/LONGITUDE] [CHECKOUT_DATE]"
						+ checkout.getVisitDate()
						+ "[/CHECKOUT_DATE][CHECK_OUTTIME]"
						+ checkout.getTime() + "[/CHECK_OUTTIME][CREATED_BY]"
						+ username + "[/CREATED_BY][/USER_DATA][/DATA]";

				SoapObject request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_CHECKOUT);
				request.addProperty("onXML", onXML);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				data.value = 40;
				data.name = "Checkout Data Uploading";
				publishProgress(data);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(CommonString.SOAP_ACTION_CHECKOUT,
						envelope);
				Object result = (Object) envelope.getResponse();

				data.value = 100;
				data.name = "Checkout Data Uploading";
				publishProgress(data);

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

					SharedPreferences.Editor editor = preferences.edit();
					editor.putString(CommonString.KEY_UPLOADCHECKOUT_STATUS,
							"YES");
					editor.commit();
					

				} else {
					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_FALSE)) {
						return CommonString.METHOD_CHECKOUT;
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
						return CommonString.METHOD_CHECKOUT + ","
								+ failureGetterSetter.getErrorMsg();
					}

				}
				return CommonString.KEY_SUCCESS;

			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						CheckOutUploadActivity.this,
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
						CheckOutUploadActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_checkout", e);
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
						CheckOutUploadActivity.this,
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
						CheckOutUploadActivity.this,
						AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						CheckOutUploadActivity.this,result, "success", null);
				message.showMessage();
			}

		}

	}

	class Data {
		int value;
		String name;
	}

}
