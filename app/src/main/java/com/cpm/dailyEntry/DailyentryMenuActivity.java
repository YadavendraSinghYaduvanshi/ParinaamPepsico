package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.FinalDataBeanDisplay;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.PromoBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.TargetKpi;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.parinaampepsico.GetService;
import com.cpm.parinaampepsico.MainMenuActivity;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.CheckOutUploadActivity;

public class DailyentryMenuActivity extends Activity {
    private Button btnNewEntry,btnDisplay,btnAddI,btnshare,btnPro;
	private PepsicoDatabase database;
	private static ArrayList<VericalBrandBean> sku_listforfood = new ArrayList<VericalBrandBean>();
	private static ArrayList<VericalBrandBean> sku_listforbev = new ArrayList<VericalBrandBean>();
	private Bundle extras = null;
	private String store_id, store_address, store_name, visit_date, username,
			store_latitude, store_longitude;
	private Intent intent;
	private TextView text;
	private SharedPreferences preferences = null;
	private SharedPreferences.Editor editor = null;

	private ArrayList<VericalBrandBean> availablelist = new ArrayList<VericalBrandBean>();
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<PosmBean> assetlist = new ArrayList<PosmBean>();
	private ArrayList<SodBean> master_list = new ArrayList<SodBean>();
	private ArrayList<PromotionBean> promo_list = new ArrayList<PromotionBean>();
	private ArrayList<PromoBean> promotionpepsi_list = new ArrayList<PromoBean>();
	private static ArrayList<PosmBean> editassest_list = new ArrayList<PosmBean>();
	boolean sos, sod, assest, promotion;
	private ArrayList<VericalBrandBean> brandForFood = new ArrayList<VericalBrandBean>();
	ArrayList<TargetKpi> target  = new ArrayList<TargetKpi>();
	private ArrayList<VericalBrandBean> brandForBeverage = new ArrayList<VericalBrandBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		database = new PepsicoDatabase(this);
		database.open();
		StoreBean staus = database.getStoreWeek(store_id);
		if(staus.getWeekstatus().toString().equalsIgnoreCase("N")){
			setContentView(R.layout.newmainscreen);
		}else{
			setContentView(R.layout.newscreendaily);
		}
		

	//	text = (TextView) findViewById(R.id.mainpage_remembermetext);
		btnNewEntry = (Button) findViewById(R.id.btn_newentry);
		
		btnDisplay = (Button) findViewById(R.id.btn_displaypaid);
		btnshare = (Button) findViewById(R.id.btn_news_training);
		btnAddI = (Button) findViewById(R.id.btn_posmtheme);
		btnPro = (Button) findViewById(R.id.btn_promotion);

		

		

		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(CommonString.KEY_CATEGORY_ID, "N");
		editor.putString("Assest_Category", "N");
		editor.putString("Cateogry_Edit", "N");
		editor.commit();
		
		 target  =	database.getTargetKPI();

	//	text.setText(store_name);
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.btn_news_training:
			intent = new Intent(this, ShareOfShelfActivity.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		case R.id.btn_posmtheme:
			intent = new Intent(this, SodMasterActivity.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		case R.id.btn_flex:

			database.open();

			if (database.getAssets(store_id).size() == 0) {
				
				Toast.makeText(DailyentryMenuActivity.this,"No data in Asset_Target",Toast.LENGTH_SHORT).show();

			} else {
				editassest_list = database.getAssetDataStore(store_id);

				if (editassest_list.size() > 0) {
					intent = new Intent(this, EditAssetsSkuActivity.class);
					startActivity(intent);
					DailyentryMenuActivity.this.finish();

				} else {
					intent = new Intent(this, AssetsSkuActivity.class);
					startActivity(intent);
					DailyentryMenuActivity.this.finish();

				}
			}

			break;
		case R.id.btn_promotion:

			intent = new Intent(this, PromotinTabActivity.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();

			break;

		case R.id.training_intellogo:
			intent = new Intent(this, StorelistActivity.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		case R.id.btn_newentry:
			intent = new Intent(this, oopseTabhost.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		case R.id.btn_displaypaid:
			intent = new Intent(this, DisplayPaid.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		}

	}

	public boolean CheckNetAvailability() {

		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			// we are connected to a network
			connected = true;
		}
		return connected;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		database.open();
		filldata();
		
		
		getAssetYesData();
		
	}

	private void getAssetYesData() {
//		 TODO Auto-generated method stub
//		ArrayList<FinalDataBeanDisplay> data = database.getAssetYesdataFood(storeid)(store_id);
//		System.out.println("data"+data);
	}




	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		intent = new Intent(this, StorelistActivity.class);
		startActivity(intent);
		DailyentryMenuActivity.this.finish();
	}

	public long checkMid() {
		return database.CheckMid(visit_date, store_id);
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	private void filldata() {
		
		if (database.gePromotionDownloaded(store_id).size() > 0) {
			
			
		} else {
			promotion = true;
			((Button) findViewById(R.id.btn_promotion))
			.setCompoundDrawablesWithIntrinsicBounds(
					null,
					getResources().getDrawable(
							R.drawable.posmexecution_na),
					null, null);
			
		}
		
		
		StoreBean staus = database.getStoreWeek(store_id);
		if(staus.getWeekstatus().toString().equalsIgnoreCase("N")){
			
			((Button) findViewById(R.id.btn_promotion))
			.setCompoundDrawablesWithIntrinsicBounds(
					null,
					getResources().getDrawable(
							R.drawable.posmexecution_dis),
					null, null);
		}
		
		
		
		
		

		coverageBeanlist = database.getCoverageData(visit_date, store_id);

		for (int i = 0; i < coverageBeanlist.size(); i++) {

			if (database.getAssets(store_id).size() > 0) {
				assetlist = database.getAssetData(coverageBeanlist.get(i)
						.getMID());

				if (assetlist.size() > 0) {
					((Button) findViewById(R.id.btn_flex))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.shareofdisplay_tick),
									null, null);

					assest = true;

				}
			} else {
				assest = true;
			}

			master_list = database.getSodDataMidForTick(store_id, coverageBeanlist
					.get(i).getMID());

			if (master_list.size() > 0) {
				
				int count2,count3,downloadcountforfood,downloadcountforbev;
				boolean flagCompetitor;
				
				downloadcountforbev=database.getComptetionRecordfromAdditionalBeveragesDownloadedData(store_id);
				if(downloadcountforbev>0)
				{
					 count2 = database.getComptetionRecordfromAdditionalBeverages();
				}
				else
				{
					count2=4;
				}
				
				downloadcountforfood=database.getComptetionRecordfromAdditionalFOODDownloadedData(store_id);
				if(downloadcountforfood>0)
				{
					 count3 = database.getComptetionRecordfromAdditionalFood();
					
				}
				else
				{
					count3=3;
					
				}
				
					
				
					
					if(count2>0&&count3>0)
					{
						flagCompetitor=true;
						
					}
					else{
						flagCompetitor=false;
						
					}
					
					if(flagCompetitor==true)
					{
						((Button) findViewById(R.id.btn_posmtheme))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.shareofdisplay_tick), null,
								null);
				
				
		for(int j=0;j<target.size();j++){
					
					if(target.get(j).getKpi().equalsIgnoreCase("SOPOI")){
						float tarfood = Float.parseFloat(target.get(j).getFood());
						float tarbevrage = Float.parseFloat(target.get(j).getBev());
						float foodPercentage = 0;
						float bevPercentage = 0;
						if(!database.getDisplayPercentageforfood(store_id).equalsIgnoreCase("")&&database.getDisplayPercentageforfood(store_id)!=null){
							 foodPercentage =	 Float.parseFloat(database.getDisplayPercentageforfood(store_id));
							
						}
						
						if(!database.getDisplayPercentageforbeverage(store_id).equalsIgnoreCase("")&&database.getDisplayPercentageforbeverage(store_id)!=null){
							
							 bevPercentage =	Float.parseFloat(database.getDisplayPercentageforbeverage(store_id));
						}
					
						if(tarfood>foodPercentage&&tarbevrage>bevPercentage){
							((Button) findViewById(R.id.btn_posmtheme))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.shareofdisplay_fred_bred), null, null);
						}else if(tarfood<=foodPercentage&&tarbevrage<=bevPercentage){
							((Button) findViewById(R.id.btn_posmtheme))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.shareofdisplay_fgreen_bgreen), null, null);
						}else if(tarfood<=foodPercentage&&tarbevrage>bevPercentage){
							((Button) findViewById(R.id.btn_posmtheme))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.shareofdisplay_fgreen_bred), null, null);
						}else if(tarfood>foodPercentage&&tarbevrage<=bevPercentage){
							((Button) findViewById(R.id.btn_posmtheme))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.shareofdisplay_fred_bgreen), null, null);
						}else{
							((Button) findViewById(R.id.btn_posmtheme))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.shareofdisplay_tick), null,
									null);
						}
						
					}
					
				}
				
		
					}

				sod = true;
			}

			//availablelist = database.getSosData(coverageBeanlist.get(i)
				//	.getMID());
			
			

			/*if (database.getBrands(store_id)/2<= database.getInsertedBrands(store_id)) {
				((Button) findViewById(R.id.btn_news_training))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.focussku_tick), null, null);

				sos = true;
			}*/
			
			if (database.getSkuFocusData_forCounting(store_id)<= database.getallinteredSOSrecord(store_id)) {
				((Button) findViewById(R.id.btn_news_training))
				.setCompoundDrawablesWithIntrinsicBounds(
						null,
						getResources().getDrawable(
								R.drawable.focussku_tick), null, null);
				
				for(int j=0;j<target.size();j++){
					
					if(target.get(j).getKpi().equalsIgnoreCase("SOS")){
						float tarfood = Float.parseFloat(target.get(j).getFood());
						float tarbevrage = Float.parseFloat(target.get(j).getBev());
						float foodPercentage = 0;
						float bevPercentage = 0;
						if(database.getSOSforFood(store_id)!=null&&!database.getSOSforFood(store_id).equalsIgnoreCase("")){
							 foodPercentage =	 Float.parseFloat(database.getSOSforFood(store_id));
							 
						}
						
						if(database.getSOSforBevarage(store_id)!=null&&!database.getSOSforBevarage(store_id).equalsIgnoreCase("")){
							 
							 bevPercentage =	Float.parseFloat(database.getSOSforBevarage(store_id));
						}
						if(tarfood>foodPercentage&&tarbevrage>bevPercentage){
							((Button) findViewById(R.id.btn_news_training))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.focussku_fred_bred), null, null);
						}else if(tarfood<=foodPercentage&&tarbevrage<=bevPercentage){
							((Button) findViewById(R.id.btn_news_training))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.focussku_fgreen_bgreen), null, null);
						}else if(tarfood<=foodPercentage&&tarbevrage>bevPercentage){
							((Button) findViewById(R.id.btn_news_training))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.focussku_fgreen_bred), null, null);
						}else if(tarfood>foodPercentage&&tarbevrage<=bevPercentage){
							((Button) findViewById(R.id.btn_news_training))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.focussku_fred_bgreen), null, null);
						}else{
							((Button) findViewById(R.id.btn_news_training))
							.setCompoundDrawablesWithIntrinsicBounds(
									null,
									getResources().getDrawable(
											R.drawable.focussku_tick), null, null);
						}
						
					}
					
				}
				
	
				sos = true;
			}
			
			
			
			
			/*if (database.getBrandswiTHsku(store_id)-18 <= database.getallinteredSOSrecord(store_id)) {
				((Button) findViewById(R.id.btn_news_training))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.focussku_tick), null, null);

				sos = true;
			}
			*/
			
			
			
			
			

			/*promo_list = database.gePromotionOtherMid(coverageBeanlist.get(i)
					.getMID());

			if (promo_list.size() > 0) {
				((Button) findViewById(R.id.btn_promotion))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.posmexecutiontick), null,
								null);

				promotion = true;
			}*/

			if (database.gePromotionDownloaded(store_id).size() > 0) {

				promotionpepsi_list = database
						.gePromotionPepsiData(coverageBeanlist.get(i).getMID());

				if (promotionpepsi_list.size() > 0) {
					((Button) findViewById(R.id.btn_promotion))
					.setCompoundDrawablesWithIntrinsicBounds(
							null,
							getResources().getDrawable(
									R.drawable.posmexecutiontick),
							null, null);
					
					
					for(int j=0;j<target.size();j++){
						
						if(target.get(j).getKpi().equalsIgnoreCase("Promo Compliance")){
							float tarfood = Float.parseFloat(target.get(j).getFood());
							float tarbevrage = Float.parseFloat(target.get(j).getBev());
							float foodPercentage = 0;
							float bevPercentage = 0;
							if(database.getPromotionforFood(store_id)!=null&&!database.getPromotionforFood(store_id).equalsIgnoreCase("")){
								 foodPercentage =	 Float.parseFloat(database.getPromotionforFood(store_id));
								
							}	
							
							if(database.getPromotionforBeverage(store_id)!=null&&!database.getPromotionforBeverage(store_id).equalsIgnoreCase("")){
								 
								 bevPercentage =	Float.parseFloat(database.getPromotionforBeverage(store_id));
							}
							
							if(tarfood>foodPercentage&&tarbevrage>bevPercentage){
								((Button) findViewById(R.id.btn_promotion))
								.setCompoundDrawablesWithIntrinsicBounds(
										null,
										getResources().getDrawable(
												R.drawable.posmexecution_fred_bred), null, null);
							}else if(tarfood<=foodPercentage&&tarbevrage<=bevPercentage){
								((Button) findViewById(R.id.btn_promotion))
								.setCompoundDrawablesWithIntrinsicBounds(
										null,
										getResources().getDrawable(
												R.drawable.posmexecution_fgreen_bgreen), null, null);
							}else if(tarfood<=foodPercentage&&tarbevrage>bevPercentage){
								((Button) findViewById(R.id.btn_promotion))
								.setCompoundDrawablesWithIntrinsicBounds(
										null,
										getResources().getDrawable(
												R.drawable.posmexecution_fgreen_bred), null, null);
							}else if(tarfood>foodPercentage&&tarbevrage<=bevPercentage){
								((Button) findViewById(R.id.btn_promotion))
								.setCompoundDrawablesWithIntrinsicBounds(
										null,
										getResources().getDrawable(
												R.drawable.posmexecution_fred_bgreen), null, null);
							}else{
								((Button) findViewById(R.id.btn_promotion))
								.setCompoundDrawablesWithIntrinsicBounds(
										null,
										getResources().getDrawable(
												R.drawable.posmexecutiontick),
										null, null);
							}
							
						}
						
					}

					promotion = true;
				}

			} else {
				promotion = true;
				((Button) findViewById(R.id.btn_promotion))
				.setCompoundDrawablesWithIntrinsicBounds(
						null,
						getResources().getDrawable(
								R.drawable.posmexecution_na),
						null, null);
				
			}
			
			
			//asset and display
			
			if((database.getDisplayTransactionData_allAssetsFORVALIDATIAON(store_id).size()) ==(database.getAssets(store_id).size())){
				
				if(database.getAssets(store_id).size()==0)
				{
					
				}
				else{
				
				((Button) findViewById(R.id.btn_displaypaid))
				.setCompoundDrawablesWithIntrinsicBounds(
						null,
						getResources().getDrawable(
								R.drawable.paid_tick),
						null, null);
	
			
			for(int j=0;j<target.size();j++){
				
				if(target.get(j).getKpi().equalsIgnoreCase("Asset Compliance")){
					float tarfood = Float.parseFloat(target.get(j).getFood());
					float tarbevrage = Float.parseFloat(target.get(j).getBev());
					float foodPercentage = 0;
					float bevPercentage = 0;
					if(database.getAssetYesdataFood(store_id)!=null&&!database.getAssetYesdataFood(store_id).equalsIgnoreCase("")){
						 foodPercentage =	 Float.parseFloat(database.getAssetYesdataFood(store_id));
						
					}	
					
					if(database.getAssetYesdataBeverage(store_id)!=null&&!database.getAssetYesdataBeverage(store_id).equalsIgnoreCase("")){
						 
						 bevPercentage =	Float.parseFloat(database.getAssetYesdataBeverage(store_id));
					}
					
					if(tarfood>foodPercentage&&tarbevrage>bevPercentage){
						((Button) findViewById(R.id.btn_displaypaid))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.displaypaid_fred_bred), null, null);
					}else if(tarfood<=foodPercentage&&tarbevrage<=bevPercentage){
						((Button) findViewById(R.id.btn_displaypaid))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.displaypaid_fgreen_bgreen), null, null);
					}else if(tarfood<=foodPercentage&&tarbevrage>bevPercentage){
						((Button) findViewById(R.id.btn_displaypaid))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.displaypaid_fgreen_bred), null, null);
					}else if(tarfood>foodPercentage&&tarbevrage<=bevPercentage){
						((Button) findViewById(R.id.btn_displaypaid))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.displaypaidfredbgreen), null, null);
					}else{
						((Button) findViewById(R.id.btn_displaypaid))
						.setCompoundDrawablesWithIntrinsicBounds(
								null,
								getResources().getDrawable(
										R.drawable.paid_tick),
								null, null);
					}
					
				}
				
			}
				
				
				
				
				
				}
				
			}
			
			if(database.getPepsiDataOOS(store_id,visit_date).size() > 0){
				
				
			
				sku_listforfood  = database.getSkuMappingData121_forbevrages(store_id);



				sku_listforbev= database.getSkuMappingData121_forFood(store_id);
				
				
				int size=sku_listforbev.size()+sku_listforfood.size();
				
				if(database.getPepsiDataOOS(store_id,visit_date).size()>=size)
				{
				//validation for osse bev
			
				((Button) findViewById(R.id.btn_newentry))
				.setCompoundDrawablesWithIntrinsicBounds(
						null,
						getResources().getDrawable(
								R.drawable.entry_tick),
						null, null);
				}
			}
			
			
			StoreBean stauss = database.getStoreWeek(store_id);
			if(stauss.getWeekstatus().toString().equalsIgnoreCase("N")){
				
				((Button) findViewById(R.id.btn_promotion))
				.setCompoundDrawablesWithIntrinsicBounds(
						null,
						getResources().getDrawable(
								R.drawable.posmexecution_dis),
						null, null);
			}else
			{
				
			}
			
		

		}
		if (sos && sod && promotion && assest) {/*
			database.updateStoreStatusOnCheckout(store_id, visit_date,
					CommonString.KEY_VALID);
		*/}

	}
}
