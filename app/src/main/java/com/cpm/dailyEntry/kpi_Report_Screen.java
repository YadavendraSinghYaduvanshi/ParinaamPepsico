package com.cpm.dailyEntry;



import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.AssetDelegate;
import com.cpm.delegates.BrandWiseTarget;
import com.cpm.delegates.PromotionViewGetterSetter;
import com.cpm.parinaampepsico.MainMenuActivity;
import com.cpm.parinaampepsicoo.R;


public class kpi_Report_Screen extends Activity {
	private SharedPreferences preferences = null;
	private SharedPreferences.Editor editor = null;
	ArrayList<AssetDelegate> asset;
	ArrayList<PromotionViewGetterSetter> promotion;
	
	ArrayList<PromotionViewGetterSetter> promotion2;
	ArrayList<BrandWiseTarget> brandWiseTarget;
	String store_id,store_name,visit_date,username;
	PepsicoDatabase database;
	ListView list1,list2,list3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kipreport_layout);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
	
		list1 = (ListView)findViewById(R.id.listsos);
		list2 = (ListView)findViewById(R.id.listprom);
		
		database = new PepsicoDatabase(this);
		database.open();
		
		
	}
	
	@Override
	protected void onStart() {
	super.onStart();
    Context con =	getApplicationContext();

	promotion = database.getKpiReportDataFood(store_id);
	
	promotion2 = database.getKpiReportDataBev(store_id);
	
	//promotion2 = database.getKpiReportDataBev(store_id);
	
	
	
	
	brandWiseTarget = database.getBrandWiseData();
	MyAdapter adapter = new MyAdapter(con, promotion);
	list1.setAdapter(adapter);
//(list1);
	
	
	
	MyAdapterPro adapter1 = new MyAdapterPro(con, promotion2);
	list2.setAdapter(adapter1);
	//setListViewHeightBasedOnItems(list2);
	
	
	}
	
	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context mcontext;
		private ArrayList<PromotionViewGetterSetter> promotion;

		public MyAdapter(Context context, ArrayList<PromotionViewGetterSetter> list1) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
			promotion = list1;
		}

		public int getCount() {
			return promotion.size();

		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}



		class ViewHolder {
			TextView vertical, brand,target;
		

		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {

				convertView = mInflater
						.inflate(R.layout.targetrowlayout, null);
				holder = new ViewHolder();
			//	holder.srNo = (TextView) convertView.findViewById(R.id.textView1);
				
				holder.vertical = (TextView) convertView.findViewById(R.id.ver);

				holder.brand = (TextView) convertView.findViewById(R.id.brand);
			
				
				holder.target = (TextView) convertView.findViewById(R.id.target);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
	
			holder.vertical.setText(promotion.get(position).getVerticalName()
					.toString());
			holder.brand.setText(promotion.get(position).getKpi()
					.toString());
			
			holder.target.setText(promotion.get(position).getKpivalue()
					.toString());
			return convertView;
		}

	}
	
	
	private class MyAdapterPro extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context mcontext;
		private ArrayList<PromotionViewGetterSetter> promotion2;

		public MyAdapterPro(Context context, ArrayList<PromotionViewGetterSetter> list1) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
			promotion2 = list1;
		}

		public int getCount() {
			return promotion2.size();

		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}



		class ViewHolder {
			TextView vertical, brand,target;
		

		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {

				convertView = mInflater
						.inflate(R.layout.targetrowlayout, null);
				holder = new ViewHolder();
			//	holder.srNo = (TextView) convertView.findViewById(R.id.textView1);
				holder.vertical = (TextView) convertView.findViewById(R.id.ver);

				holder.brand = (TextView) convertView.findViewById(R.id.brand);
			
				
				holder.target = (TextView) convertView.findViewById(R.id.target);

				

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
	
			holder.vertical.setText(promotion2.get(position).getVerticalName()
					.toString());
			holder.brand.setText(promotion2.get(position).getKpi()
					.toString());
			
			holder.target.setText(promotion2.get(position).getKpivalue()
					.toString());
			
			return convertView;
		}

	}
	
	
	
	
	public  boolean setListViewHeightBasedOnItems(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter != null) {

		    int numberOfItems = listAdapter.getCount();

		    // Get total height of all items.
		    int totalItemsHeight = 0;
		    for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
		        View item = listAdapter.getView(itemPos, null, listView);
		        item.measure(0, 0);
		        totalItemsHeight += item.getMeasuredHeight();
		    }

		    // Get total height of all item dividers.
		    int totalDividersHeight = listView.getDividerHeight() * 
		            (numberOfItems - 1);

		    // Set list height.
		    ViewGroup.LayoutParams params = listView.getLayoutParams();
		    params.height = totalItemsHeight + totalDividersHeight;
		    listView.setLayoutParams(params);
		    listView.requestLayout();

		    return true;

		} else {
		    return false;
		}
		}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(kpi_Report_Screen.this, MainMenuActivity.class);
		startActivity(i);
		kpi_Report_Screen.this.finish();
		
	}
	
}
