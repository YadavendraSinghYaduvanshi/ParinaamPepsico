package com.cpm.geotagging;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CityBean;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.Storenamebean;
import com.cpm.parinaampepsico.MainMenuActivity;
import com.cpm.parinaampepsicoo.R;

public class GeoTagging extends Activity {
	ArrayList<CityBean> temp = new ArrayList<CityBean>();
	public static ArrayList<Storenamebean> storedetails = new ArrayList<Storenamebean>();
	ArrayList<GeotaggingBeans> geotaglist = new ArrayList<GeotaggingBeans>();
	ListView l1;
	static Editor e1, e3, e11;
	private PepsicoDatabase data;

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		data.close();
	}

	private static class EfficientAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public EfficientAdapter(Context context) {
			mInflater = LayoutInflater.from(context);

		}

		public int getCount() {
			return storedetails.size();
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
				convertView = mInflater.inflate(R.layout.geoviewlist, null);
				holder = new ViewHolder();
				holder.text = (TextView) convertView
						.findViewById(R.id.geolistviewxml_storename);

				holder.text1 = (TextView) convertView
						.findViewById(R.id.geolistviewxml_storeaddress);

				holder.check = (ImageView) convertView
						.findViewById(R.id.imageView1);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text.setText(storedetails.get(position).getStorename());
			holder.text1.setText(storedetails.get(position).getAddress());

			if (storedetails.get(position).getStatus().equalsIgnoreCase("U")) {
				holder.check.setVisibility(View.VISIBLE);

				holder.check.setBackgroundResource(R.drawable.tick_u);

			} else if (storedetails.get(position).getStatus()
					.equalsIgnoreCase("Y")) {
				holder.check.setVisibility(View.VISIBLE);

			} else if (storedetails.get(position).getStatus()
					.equalsIgnoreCase("N")) {
				holder.check.setVisibility(View.INVISIBLE);

			}

			else if (storedetails.get(position).getStatus()
					.equalsIgnoreCase("P")) {
				holder.check.setVisibility(View.VISIBLE);

				holder.check.setBackgroundResource(R.drawable.pointer1);

			} else {
				holder.check.setVisibility(View.INVISIBLE);
			}

			return convertView;
		}

		static class ViewHolder {
			TextView text;
			TextView text1;
			ImageView check;

		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.geo_tag_listpage);

		data = new PepsicoDatabase(this);
		data.open();
		
		validate_status();
		

		/*Button back = (Button) findViewById(R.id.mainpage_intellogo);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(GeoTagging.this,
						MainMenuActivity.class);

				startActivity(intent);
				GeoTagging.this.finish();

			}
		});*/

		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		// final ProgressBar _progress = (ProgressBar)
		// findViewById(R.id.progressBar1);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		temp = data.getCityDetails();

		for (int i = 0; i < temp.size(); i++) {
			adapter.add(temp.get(i).getCityName());

		}

		final Spinner sp = (Spinner) findViewById(R.id.geopage_subhead_textbox1_2C);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int item = sp.getSelectedItemPosition();

				String selectedcity = temp.get(item).getCityId();
				storedetails = new ArrayList<Storenamebean>();
				storedetails = data.getGeoTagStore(selectedcity);
				
				generatelistview();

			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		sp.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	}
	
	public void validate_status()
	{
		geotaglist = data.getGeotaggingStatusData();
		for(int i =0 ;i< geotaglist.size();i++)
		{
			data.updateGeoTagStatus(geotaglist.get(i).getStoreid(), geotaglist.get(i).getStatus(), geotaglist.get(i).getLatitude(), geotaglist.get(i).getLongitude());
		}
			
	}

	public void generatelistview() {

		l1 = (ListView) findViewById(R.id.list1);
		l1.setAdapter(new EfficientAdapter(this));

		l1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				if (storedetails.get(position).getStatus()
						.equalsIgnoreCase("U")) {

					Toast.makeText(getBaseContext(), "Store Uploaded",
							Toast.LENGTH_LONG).show();

				}

				else if (storedetails.get(position).getStatus()
						.equalsIgnoreCase("Y")) {

					Toast.makeText(getBaseContext(), "Store Already Geotagged",
							Toast.LENGTH_LONG).show();

				} else if (storedetails.get(position).getStatus()
						.equalsIgnoreCase("P")) {

					Intent intent = new Intent(GeoTagging.this,
							LocationActivity.class);
					intent.putExtra("StoreName", storedetails.get(position)
							.getStorename());
					intent.putExtra("Storeid", storedetails.get(position)
							.getStoreid());

					intent.putExtra("Storeaddress", storedetails.get(position)
							.getAddress());

					intent.putExtra("storelatitude", storedetails.get(position)
							.getLatitude());

					intent.putExtra("storelongitude", storedetails
							.get(position).getLongitude());

					startActivity(intent);
					locationactivity();

				} else {

					/*Intent intent = new Intent(GeoTagging.this,
							LocationActivity.class);*/
					Intent intent = new Intent(GeoTagging.this,
							GeoTagActivity.class);
					intent.putExtra("StoreName", storedetails.get(position)
							.getStorename());
					intent.putExtra("Storeid", storedetails.get(position)
							.getStoreid());

					intent.putExtra("Storeaddress", storedetails.get(position)
							.getAddress());
					intent.putExtra("storelatitude", "0");

					intent.putExtra("storelongitude", "0");

					startActivity(intent);
					locationactivity();

				}
			}
		});
	}

	public void locationactivity() {

		this.finish();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(GeoTagging.this, MainMenuActivity.class);

		startActivity(intent);
		GeoTagging.this.finish();
	}
}
