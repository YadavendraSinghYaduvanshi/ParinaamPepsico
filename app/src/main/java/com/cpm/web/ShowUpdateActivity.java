package com.cpm.web;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.cpm.Constants.CommonString;
import com.cpm.parinaampepsico.GetService;
import com.cpm.parinaampepsico.LoginActivity;
import com.cpm.parinaampepsico.MainMenuActivity;
import com.cpm.parinaampepsicoo.R;

public class ShowUpdateActivity extends Activity {

	WebView mWebView;

	Button mcontinuebtn;
	String status, service_status;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		status = preferences.getString(CommonString.KEY_CHECKOUT_STATUS, "");
		service_status = preferences.getString(CommonString.KEY_SERVICE_STATUS,
				"");

		if (!status.equalsIgnoreCase("yes")) {

			if (!service_status.equalsIgnoreCase("yes")) {
				Intent serviceIntent = new Intent(ShowUpdateActivity.this,
						GetService.class);
				startService(serviceIntent);
			}

		}

		mcontinuebtn = (Button) findViewById(R.id.header);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.clearCache(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://pepsico.parinaam.in/notice/index.html");

		mWebView.setWebViewClient(new HelloWebViewClient());

		mcontinuebtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(ShowUpdateActivity.this,
						MainMenuActivity.class);
				startActivity(i);

				ShowUpdateActivity.this.finish();

			}
		});

	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(ShowUpdateActivity.this, LoginActivity.class);
		startActivity(i);
		ShowUpdateActivity.this.finish();

	}

}
