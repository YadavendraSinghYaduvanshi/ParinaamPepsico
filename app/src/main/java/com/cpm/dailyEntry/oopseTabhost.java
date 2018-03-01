package com.cpm.dailyEntry;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.parinaampepsicoo.R;
import com.cpm.tab.graphics.TabBitmap;

public class oopseTabhost extends TabActivity

{

	Intent ooseFood;
	Intent ooseBevrages;
	static Editor e1, e3, e11;
	private static final String TAG_1 = "tab1";
	private static final String TAG_2 = "tab2";

	public Keyboard mKeyboard;

	CustomKeyboardView mKeyboardView;

	TabHost mTabHost;
	static oopseTabhost instance;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost);

		oopseTabhost.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		ooseFood = new Intent(this,ooseVerticalFood.class);
		ooseBevrages = new Intent(this, ooseVerticalbevrages.class);
		setTabs();
		instance = this;
		

	}

	private void setTabs() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		addTab("FOOD", TAG_1, null, ooseFood);
		addTab("BEVERAGES", TAG_2, null, ooseBevrages);
		


	}

	private Drawable createTabDrawable(int resId) {
		Resources res = getResources();
		StateListDrawable states = new StateListDrawable();

		final Options options = new Options();
		options.inPreferredConfig = Config.ARGB_8888;

		Bitmap icon = BitmapFactory.decodeResource(res, resId, options);

		Bitmap unselected = TabBitmap.createUnselectedBitmap(res, icon);
		Bitmap selected = TabBitmap.createSelectedBitmap(res, icon);

		icon.recycle();

		states.addState(new int[] { android.R.attr.state_selected },
				new BitmapDrawable(res, selected));
		states.addState(new int[] { android.R.attr.state_enabled },
				new BitmapDrawable(res, unselected));

		return states;
	}

	private View createTabIndicator(String label, Drawable drawable) {
		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.tab_indicator, mTabHost.getTabWidget(), false);

		TextView txtTitle = (TextView) tabIndicator
				.findViewById(R.id.text_view_tab_title);
		txtTitle.setText(label);
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) txtTitle
				.getLayoutParams();
		txtTitle.setLayoutParams(params);

		/*
		 * ImageView imgIcon = (ImageView) tabIndicator
		 * .findViewById(R.id.image_view_tab_icon);
		 * imgIcon.setImageDrawable(drawable);
		 */

		return tabIndicator;
	}

	private void addTab(String label, String tag, Drawable drawable, Intent id) {
		TabHost.TabSpec spec = mTabHost.newTabSpec(tag);
		spec.setIndicator(createTabIndicator(label, drawable));
		spec.setContent(id);

		mTabHost.addTab(spec);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
	}

}
