package com.cpm.dailyEntry;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.PromoBean;
import com.cpm.parinaampepsicoo.R;
import com.cpm.tab.graphics.TabBitmap;

public class PromotinTabActivity extends TabActivity

{

	Intent editpepsi;
	Intent pepsi;
	Intent competitor;
	static Editor e1, e3, e11;
	private static final String TAG_1 = "tab1";
	private static final String TAG_2 = "tab2";
	static PepsicoDatabase database;
	private String store_id;
	private SharedPreferences preferences;
	TabHost mTabHost;
	private ArrayList<PromoBean> editpromo_list = new ArrayList<PromoBean>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost);

		pepsi = new Intent(this, PromotinPepsiActivity.class);
		editpepsi = new Intent(this, EditPromotinPepsiActivity.class);
		competitor = new Intent(this, PromotinCompetitorActivity.class);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");

		database = new PepsicoDatabase(this);
		database.open();
		editpromo_list = database.gePromotionPepsi(store_id);
		setTabs();

	}

	private void setTabs() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		if (editpromo_list.size() == 0) {
			addTab("Pepsi", TAG_1, null, pepsi);
			addTab("Competitor", TAG_2, null, competitor);
		} else {
			addTab("Pepsi", TAG_1, null, editpepsi);
			addTab("Competitor", TAG_2, null, competitor);

		}
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

		return tabIndicator;
	}

	private void addTab(String label, String tag, Drawable drawable, Intent id) {
		TabHost.TabSpec spec = mTabHost.newTabSpec(tag);
		spec.setIndicator(createTabIndicator(label, drawable));
		spec.setContent(id);

		mTabHost.addTab(spec);
	}

}
