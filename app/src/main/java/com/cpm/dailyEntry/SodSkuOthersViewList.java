package com.cpm.dailyEntry;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.SodBean;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.parinaampepsicoo.R;

public class SodSkuOthersViewList extends Activity {

	public String store_name;
	public ListView lv;
	public static int pos[], pos1[], pos2[], pos3[], pos4[], pos5[];
	public Keyboard mKeyboard;
	public static CustomKeyboardView mKeyboardView;
	private String store_id, username, intime, brand_id, brand_name,
			visit_date;
	private SharedPreferences preferences;
	private Bundle extras;
	private PepsicoDatabase database;
	public final static int DATE_DIALOG_ID = 0;
	private static ArrayList<SodBean> sku_list = new ArrayList<SodBean>();
	private static int myear;
	private static int month;
	private static int day;
	static String date = "";
	static int date_position;
	static String date_num = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sod_othersskulist);

		lv = (ListView) findViewById(R.id.list);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		brand_id = preferences.getString(CommonString.KEY_BRAND_ID, "");
		brand_name = preferences.getString(CommonString.KEY_BRAND_NAME, "");
		visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name + " - " + brand_name);

		database = new PepsicoDatabase(this);
		database.open();

		Intent i = getIntent();
		String sod_id = i.getStringExtra(CommonString.KEY_SOD_ID);

		sku_list = database.getSodTransactionData(Integer.parseInt(sod_id));

		lv.setAdapter(new MyAdapter(this));

	}

	private static class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		static Context mcontext;

		public MyAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;

		}

		public int getCount() {
			return sku_list.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.sodotherlist, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku);
				holder.stock = (TextView) convertView.findViewById(R.id.stock);
				holder.faceup = (TextView) convertView
						.findViewById(R.id.faceup);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.sku.setText(sku_list.get(position).getSkuname().toString());
			holder.stock.setText(sku_list.get(position).getStock().toString());
			holder.faceup
					.setText(sku_list.get(position).getFaceup().toString());

			return convertView;
		}

		static class ViewHolder {
			TextView sku, stock, faceup;

		}
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent intent = new Intent(this, SodMasterActivity.class);

		startActivity(intent);

		SodSkuOthersViewList.this.finish();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

}
