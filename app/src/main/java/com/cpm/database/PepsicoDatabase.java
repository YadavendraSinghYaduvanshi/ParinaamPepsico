package com.cpm.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cpm.Constants.CommonString;
import com.cpm.delegates.AssetDelegate;
import com.cpm.delegates.BrandWiseTarget;
import com.cpm.delegates.CategoryBean;
import com.cpm.delegates.CategoryImageBean;
import com.cpm.delegates.CateogryDataDelegates;
import com.cpm.delegates.CheckoutBean;
import com.cpm.delegates.CityBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.DisplayDelegates;
import com.cpm.delegates.FinalDataBeanDisplay;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.LocationBean;
import com.cpm.delegates.NonSkuBean;
import com.cpm.delegates.OosEntryBean;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.PromoBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.PromotionDelegates;
import com.cpm.delegates.PromotionViewGetterSetter;
import com.cpm.delegates.ReasonModel;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.Storenamebean;
import com.cpm.delegates.TableBeen;
import com.cpm.delegates.TargetKpi;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.geotagging.GeoTagCityGetterSetter;
import com.cpm.geotagging.GeoTagGetterSetter;
import com.cpm.xmlGetterSetter.AssetsGetterSetter;
import com.cpm.xmlGetterSetter.AssetsTargetGetterSetter;
import com.cpm.xmlGetterSetter.BrandMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMappingGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryVerticalMappingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyMasterGetterSetter;
import com.cpm.xmlGetterSetter.CompetitorGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.KpiReportGetterSetter;
import com.cpm.xmlGetterSetter.NonSkuGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingGetterSetter;
import com.cpm.xmlGetterSetter.PosmMasterGetterSetter;
import com.cpm.xmlGetterSetter.PromotionGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterDataGetterSetter;
import com.cpm.xmlGetterSetter.SkuTargetGetterSetter;
import com.cpm.xmlGetterSetter.SkufocusGetterSetter;
import com.cpm.xmlGetterSetter.TargetBrandWiseGetterSetter;
import com.cpm.xmlGetterSetter.TargetKPIGetterSetter;
import com.cpm.xmlGetterSetter.VerticalBrandMappingGetterSetter;
import com.cpm.xmlGetterSetter.VerticalMasterGetterSetter;

public class PepsicoDatabase extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "PEPSICO_DATABASE";
	public static final int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/com.cpm.parinaampepsico/databases/";

	private Context myContext;
	private SQLiteDatabase db;

	public PepsicoDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public void open() {
		try {
			db = this.getWritableDatabase();
		} catch (Exception e) {
		}
	}

	public void close() {
		db.close();
	}

	// End

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub


		
		
		db.execSQL(CommonString.CREATE_TABLE_KPI_REPORT);
		db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
		db.execSQL(CommonString.CREATE_TABLE_STORE_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_VERTICAL_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_BRAND_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_SOD_PEPSIDATA);
		
		db.execSQL(CommonString.CREATE_TABLE_DISPLAY_TRANSCTION);
		db.execSQL(CommonString.CREATE_TABLE_SOD_OTHERDATA);
		db.execSQL(CommonString.CREATE_TABLE_SKU_DATA);
		db.execSQL(CommonString.CREATE_TABLE_GEO_TAG_CITY);
		db.execSQL(CommonString.CREATE_TABLE_GEO_TAG_MAPPING);
		db.execSQL(CommonString.CREATE_TABLE_INSERT_GEOTAG);
		db.execSQL(CommonString.CREATE_TABLE_SKU_AVAILBILITY);
		db.execSQL(CommonString.CREATE_TABLE_SOD_DATA);
		
		db.execSQL(CommonString.CREATE_TABLE_DISPLAY_MASTER);
		
		db.execSQL(CommonString.CREATE_TABLE_CATEGORY_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_SKU_TARGET);
		db.execSQL(CommonString.CREATE_TABLE_CATEGORY_VERTICALMAPPING);
		db.execSQL(CommonString.CREATE_TABLE_POSM_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_SKU_FOCUS);
		db.execSQL(CommonString.CREATE_TABLE_SKU_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_COMPANY_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_COMPETITOR_MAPPING);
		db.execSQL(CommonString.CREATE_TABLE_ASSETS_MASTER);
		db.execSQL(CommonString.CREATE_TABLE_ASSETS_TARGET);
		db.execSQL(CommonString.CREATE_TABLE_ASSET_DATA);
		db.execSQL(CommonString.CREATE_TABLE_DISPLAY_TEMPDATA);
		db.execSQL(CommonString.CREATE_TABLE_NON_WORKING);
		db.execSQL(CommonString.CREATE_TABLE_SOS_PEPSI);
		db.execSQL(CommonString.CREATE_TABLE_SOS_PEPSI_OOS);
		db.execSQL(CommonString.CREATE_TABLE_NON_SKU_REASON);
		db.execSQL(CommonString.CREATE_TABLE_SOS_IMAGES);
		db.execSQL(CommonString.CREATE_TABLE_SOS_BRAND_CHECK);
		db.execSQL(CommonString.CREATE_TABLE_PROMOTION_OTHER);
		db.execSQL(CommonString.CREATE_TABLE_PROMOTION_PEPSI);
		db.execSQL(CommonString.CREATE_TABLE_INSERT_PROMOTION_PEPSI);
		db.execSQL(CommonString.CREATE_TABLE_LOCATION_STATUS);
		db.execSQL(CommonString.CREATE_TABLE_CHECKOUT);
		db.execSQL(CommonString.CREATE_TABLE_SUBNON_WORKING);
		db.execSQL(CommonString.CREATE_TABLE_KEY_ACCOUNT);
		
		
		db.execSQL(CommonString.CREATE_TABLE_KPI_TARGET);
		
		
		if(TableBeen.getBrandWiseSOS()!=null){
			db.execSQL(TableBeen.getBrandWiseSOS());
		}
		
		
	}
	
	public void reCreatePerformance(){

		if(TableBeen.getBrandWiseSOS()!=null){
			db.execSQL(TableBeen.getBrandWiseSOS());
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_COVERAGE_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_STORE_DETAIL);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_VERTICAL_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_BRAND_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_TARGET);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOD_DATA);
		
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_DISPLAY_TRANSACTION);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_DISPLAY_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_AVAILBILITY);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOD_PEPSIDATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOD_OTHERDATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_DISPLAY_SKU);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_ASSET_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_ASSET_TARGET);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_ASSET_DATA);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_DISPLAY_TEMPDATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_INSERT_GEO_TAG);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_GEO_TAG_MAPPING);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_GEOTAG_CITY);

		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_CATEGORY_MASTER);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_CATEGORY_VERTICALMAPPING);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_POSM_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_FOCUS);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_MASTER);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_NON_WORKING_REASON);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_COMPANY_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_PEPSI);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_PEPSI_OOS);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_COMPETITOR_MAPPING);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_NON_SKU_REASON);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_IMAGES);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_BRAND_CHECK);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_PROMOTION_OTHER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_PROMOTION_PEPSI);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_PROMOTION_PEPSI);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_LOCATION_STATUS);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_CHECK_OUT);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_KEY_ACCOUNT);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_SUBNON_WORKING_REASON);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_KPI_TARGET);
		onCreate(db);
	}

	public void deleteAllTables() {

		
		
		
		
		db.delete(CommonString.TABLE_STORE_DETAIL, null, null);
		db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
		db.delete(CommonString.TABLE_VERTICAL_MASTER, null, null);
		db.delete(CommonString.TABLE_BRAND_MASTER, null, null);
		db.delete(CommonString.TABLE_SOD_PEPSIDATA, null, null);
		db.delete(CommonString.TABLE_DISPLAY_TRANSACTION, null, null);
		db.delete(CommonString.TABLE_SOD_OTHERDATA, null, null);
		db.delete(CommonString.TABLE_DISPLAY_SKU, null, null);
		db.delete(CommonString.TABLE_GEO_TAG_MAPPING, null, null);
		db.delete(CommonString.TABLE_GEO_TAG_MAPPING, null, null);
		db.delete(CommonString.TABLE_INSERT_GEO_TAG, null, null);
		db.delete(CommonString.TABLE_SKU_AVAILBILITY, null, null);
		db.delete(CommonString.TABLE_SOD_DATA, null, null);
		db.delete(CommonString.TABLE_DISPLAY_DATA, null, null);
		db.delete(CommonString.TABLE_CATEGORY_MASTER, null, null);
		db.delete(CommonString.TABLE_SKU_TARGET, null, null);
		db.delete(CommonString.TABLE_CATEGORY_VERTICALMAPPING, null, null);
		db.delete(CommonString.TABLE_POSM_MASTER, null, null);
		db.delete(CommonString.TABLE_SKU_FOCUS, null, null);
		db.delete(CommonString.TABLE_SKU_MASTER, null, null);
		db.delete(CommonString.TABLE_COMPANY_MASTER, null, null);
		db.delete(CommonString.TABLE_COMPETITOR_MAPPING, null, null);
		db.delete(CommonString.TABLE_ASSET_MASTER, null, null);
		db.delete(CommonString.TABLE_ASSET_TARGET, null, null);
		db.delete(CommonString.TABLE_INSERT_ASSET_DATA, null, null);
		
		db.delete(CommonString.TABLE_INSERT_DISPLAY_TEMPDATA, null, null);
		
		db.delete(CommonString.TABLE_NON_WORKING_REASON, null, null);
		db.delete(CommonString.TABLE_SOS_PEPSI, null, null);
		db.delete(CommonString.TABLE_SOS_PEPSI_OOS, null, null);
		db.delete(CommonString.TABLE_NON_SKU_REASON, null, null);
		db.delete(CommonString.TABLE_SOS_IMAGES, null, null);
		db.delete(CommonString.TABLE_SOS_BRAND_CHECK, null, null);
		db.delete(CommonString.TABLE_PROMOTION_OTHER, null, null);
		db.delete(CommonString.TABLE_PROMOTION_PEPSI, null, null);
		db.delete(CommonString.TABLE_INSERT_PROMOTION_PEPSI, null, null);
		db.delete(CommonString.TABLE_LOCATION_STATUS, null, null);
		db.delete(CommonString.TABLE_CHECK_OUT, null, null);
		db.delete(CommonString.TABLE_SUBNON_WORKING_REASON, null, null);
		db.delete(CommonString.TABLE_KEY_ACCOUNT, null, null);

	}

	// location_Status

	public void InsertLocationData(String visitdate, String currenttime,
			String latitude, String longitude, String networkstatus) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_DATE, visitdate);
			values.put(CommonString.KEY_CURRENT_TIME, currenttime);
			values.put(CommonString.KEY_LATITUDE, latitude);
			values.put(CommonString.KEY_LONGITUDE, longitude);
			values.put(CommonString.KEY_NETWORK_STATUS, networkstatus);

			db.insert(CommonString.TABLE_LOCATION_STATUS, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// CHECKOUT

	public void InsertCheckoutData(String visitdate, String currenttime,
			String latitude, String longitude) {

		db.delete(CommonString.TABLE_CHECK_OUT, null, null);
		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_DATE, visitdate);
			values.put(CommonString.KEY_CURRENT_TIME, currenttime);
			values.put(CommonString.KEY_LATITUDE, latitude);
			values.put(CommonString.KEY_LONGITUDE, longitude);

			db.insert(CommonString.TABLE_CHECK_OUT, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public ArrayList<LocationBean> getLocationData() {

		ArrayList<LocationBean> list = new ArrayList<LocationBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_LOCATION_STATUS, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					LocationBean sb = new LocationBean();

					sb.setId(dbcursor.getInt(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setVisitDate(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DATE)));
					sb.setTime(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CURRENT_TIME)));
					sb.setLatitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
					sb.setLongitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));
					sb.setNetworkStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_NETWORK_STATUS)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	public CheckoutBean getCheckoutData() {

		CheckoutBean sb = new CheckoutBean();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_CHECK_OUT, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					sb.setVisitDate(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DATE)));
					sb.setTime(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CURRENT_TIME)));
					sb.setLatitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
					sb.setLongitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));

					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return sb;
	}

	public void deleteLocatioData(int id) {

		try {

			db.delete(CommonString.TABLE_LOCATION_STATUS, CommonString.KEY_ID
					+ " = '" + id + "'", null);

		} catch (Exception e) {
			Log.d("Database Exception while Insert Posm Master Data ",
					e.getMessage());
		}

	}

	public void deleteCheckout() {

		try {

			db.delete(CommonString.TABLE_CHECK_OUT, null, null);
		} catch (Exception e) {
			Log.d("Database Exception while Insert Posm Master Data ",
					e.getMessage());
		}

	}

	// jcp

	public void InsertStoreData(JCPGetterSetter data) {

		db.delete(CommonString.TABLE_STORE_DETAIL, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStoreid().size(); i++) {

				values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
				values.put(CommonString.KEY_STORE_NAME, data.getStorename()
						.get(i));
				values.put(CommonString.KEY_ADDRESS,
						data.getStoreaddres().get(i));
				/*values.put(CommonString.KEY_VISIT_DATE, data.getVisitdate()
						.get(i));*/

				values.put(CommonString.KEY_VISIT_DATE, "10/31/2017");
				
				values.put(CommonString.KEY_LATITUDE, data.getStorelatitude()
						.get(i));
				values.put(CommonString.KEY_LONGITUDE, data.getStorelongitude()
						.get(i));
				
//				values.put(CommonString.KEY_STATUS, "N");
//				values.put(CommonString.KEY_CHECKOUT_STATUS,"N");
//				values.put(CommonString.KEY_WEEK_STATUS, "Y");

				
			    values.put(CommonString.KEY_STATUS, data.getStatus().get(i));
				values.put(CommonString.KEY_CHECKOUT_STATUS, data
						.getCheckout_status().get(i));
				
				
				values.put(CommonString.KEY_WEEK_STATUS, data.getStatusStore().get(i));
				
				
				db.insert(CommonString.TABLE_STORE_DETAIL, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public void InsertSosImagesData(String categoryid, String storeid,
			String img1, String img2, String img3) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_CATEGORY_ID, categoryid);
			values.put(CommonString.KEY_STORE_ID, storeid);
			values.put(CommonString.KEY_IMAGE_PATH1, img1);
			values.put(CommonString.KEY_IMAGE_PATH2, img2);
			values.put(CommonString.KEY_IMAGE_PATH3, img3);

			db.insert(CommonString.TABLE_SOS_IMAGES, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public void UpdateSosImagesData(String categoryid, String storeid,
			String img1, String img2, String img3) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_IMAGE_PATH1, img1);
			values.put(CommonString.KEY_IMAGE_PATH2, img2);
			values.put(CommonString.KEY_IMAGE_PATH3, img3);

			db.update(CommonString.TABLE_SOS_IMAGES, values,
					CommonString.KEY_CATEGORY_ID + " =? AND "
							+ CommonString.KEY_STORE_ID + " =?", new String[] {
							categoryid, storeid });

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public void InsertBrandCheck(String brandid, String storeid, String type) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_BRAND_ID, brandid);
			values.put(CommonString.KEY_STORE_ID, storeid);
			values.put(CommonString.KEY_COMAPANY_NAME, type);

			db.insert(CommonString.TABLE_SOS_BRAND_CHECK, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public void deleteStoreMidData(int mid, String store_id) {

		try {

			db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_ID
					+ " = '" + mid + "'", null);

			db.delete(CommonString.TABLE_INSERT_ASSET_DATA, CommonString.MID
					+ " = '" + mid + "'", null);
			
			

			db.delete(CommonString.TABLE_SOS_PEPSI, CommonString.MID + " = '"
					+ mid + "'", null);

			db.delete(CommonString.TABLE_SOD_DATA, CommonString.MID + " = '"
					+ mid + "'", null);
			
			db.delete(CommonString.TABLE_DISPLAY_DATA, CommonString.MID + " = '"
					+ mid + "'", null);

			db.delete(CommonString.TABLE_SOD_PEPSIDATA,
					CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);
			
			db.delete(CommonString.TABLE_DISPLAY_TRANSACTION,
					CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);

			db.delete(CommonString.TABLE_SOS_IMAGES, CommonString.KEY_STORE_ID
					+ " = '" + store_id + "'", null);

			db.delete(CommonString.TABLE_SOS_BRAND_CHECK,
					CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);

			db.delete(CommonString.TABLE_PROMOTION_OTHER, CommonString.MID
					+ " = '" + mid + "'", null);

			db.delete(CommonString.TABLE_INSERT_PROMOTION_PEPSI,
					CommonString.MID + " = '" + mid + "'", null);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	public void deleteSosBrandStatusData(String storeid) {

		try {

			db.delete(CommonString.TABLE_SOS_BRAND_CHECK,
					CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	public StoreBean getStoreStatus(String id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		StoreBean sb = new StoreBean();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_STORE_DETAIL + "  WHERE STORE_ID = '"
					+ id + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setStorename((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME))));
					sb.setStoreaddress(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ADDRESS)));
					sb.setVisitdate((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))));
					sb.setLatitude((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
					sb.setLongitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));

					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STATUS)));
					sb.setCheckout_status(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CHECKOUT_STATUS)));

					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return sb;

	}
	
	
	
	public StoreBean getStoreWeek(String id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		StoreBean sb = new StoreBean();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_STORE_DETAIL + "  WHERE STORE_ID = '"
					+ id + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					sb.setWeekstatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_WEEK_STATUS)));
				
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return sb;

	}
	

	public CategoryImageBean getImages(String id, String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		CategoryImageBean sb = new CategoryImageBean();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_IMAGES + "  WHERE "
					+ CommonString.KEY_CATEGORY_ID + " = '" + id + "' AND "
					+ CommonString.KEY_STORE_ID + " ='" + storeid + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					sb.setImg1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));

					sb.setImg2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));

					sb.setImg3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));

					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return sb;

	}

	public ArrayList<ReasonModel> getSubReasonData(String mreson) {

		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SUBNON_WORKING_REASON + " where "
					+ CommonString.KEY_MREASON_ID + "='" + mreson + "'", null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();

					sb.setReason(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REASON)))));

					sb.setReasonid((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REASON_ID))))));

					sb.setImage(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)))));

					sb.setMreasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_MREASON_ID)))));

					sb.setEntry(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ENTRY)))));

					sb.setInformnedto(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_INFORMED_TO)))));

					sb.setStoreinfo(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_INFO)))));

					sb.setOtherreason(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_other)))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	public ArrayList<ReasonModel> getNonWorkingData() {

		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_NON_WORKING_REASON, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();

					sb.setMreasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_MREASON_ID)))));

					sb.setMreason((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_MREASON))))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	public ArrayList<ReasonModel> getKEYAccData() {

		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_KEY_ACCOUNT, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();

					sb.setKeyid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ACC_ID)))));

					sb.setKeyaccunt((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ACCOUNT))))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	public String getStoreName(String storeid) {
		String storename = "";
		Cursor cursor = db.rawQuery("select " + CommonString.KEY_STORE_NAME
				+ " from " + CommonString.TABLE_STORE_DETAIL + " where "
				+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

		if (cursor != null) {
			cursor.moveToFirst();
			storename = cursor.getString(cursor
					.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME));
		}

		cursor.close();
		return storename;
	}

	public void insertInto(long mid, String storeid) {

		try {
			Cursor dbcursor = db
					.rawQuery(
							"INSERT INTO ASSET_DATA (MID, SKU_ID, ASSETS_ID, STORE_ID, REMARKS, AVAILABLE,IMAGE_PATH1,IMAGE_PATH2,IMAGE_PATH3) SELECT "
									+ mid
									+ " AS MID ,SKU_ID, ASSETS_ID, STORE_ID, '' AS REMARKS , 0 AS AVAIL, '' AS IMAGE_PATH1, '' AS IMAGE_PATH2, '' AS IMAGE_PATH3 FROM ASSEST_TARGET WHERE STORE_ID = '"
									+ storeid + "'", null);
			if (dbcursor != null) {
				dbcursor.moveToFirst();
				dbcursor.close();
			}
		} catch (Exception e) {
			Log.d("Database Exception while Insert Posm Master Data ",
					e.getMessage());
		}

	}

	public void updateGeoTagData(String storeid) {

		try {

			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, CommonString.KEY_D);

			int l = db.update(CommonString.TABLE_INSERT_GEO_TAG, values,
					CommonString.KEY_STORE_ID + "=?", new String[] { storeid });
			System.out.println("update : " + l);
		} catch (Exception e) {
			Log.d("Database Exception while Insert Posm Master Data ",
					e.getMessage());
		}
	}

	public void InsertBrandMasterData(BrandMasterGetterSetter data) {

		db.delete(CommonString.TABLE_BRAND_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getBrandid().size(); i++) {

				values.put(CommonString.KEY_BRAND_ID,
						Integer.parseInt(data.getBrandid().get(i)));
				values.put(CommonString.KEY_BRAND_NAME, data.getBrand().get(i));
				values.put(CommonString.KEY_CATEGORY_ID, data.getCategoryid()
						.get(i));
				values.put(CommonString.KEY_BR_SEQUENCE, data.getBRANDSEQ()
						.get(i));
				values.put(CommonString.KEY_COMPANY_ID, data.getCompany_id()
						.get(i));

				db.insert(CommonString.TABLE_BRAND_MASTER, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertSkuAvailbityData(long mid, String storeid,
			ArrayList<VericalBrandBean> data) {

		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuid());
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_FACEUP, data.get(i).getSkuqty1());
				values.put(CommonString.KEY_STOCK, data.get(i).getSkuqty());
				values.put(CommonString.KEY_LENGTH, data.get(i).getSkuqty2());
				db.insert(CommonString.TABLE_SKU_AVAILBILITY, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertSosPepsiData(long mid, String storeid,
			ArrayList<VericalBrandBean> data, String brandid, String type) {

		ContentValues values = new ContentValues();

		try {

			String dom1, dom2, dom3;

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getDom1().equalsIgnoreCase("MFD")
						&& data.get(i).getDom2().equalsIgnoreCase("MFD")
						&& data.get(i).getDom3().equalsIgnoreCase("MFD")) {
					dom1 = "1/1/1900";
					dom2 = "1/1/1900";
					dom3 = "1/1/1900";
				} else {
					dom1 = data.get(i).getDom1();
					dom2 = data.get(i).getDom2();
					dom3 = data.get(i).getDom3();
				}

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuid());
				values.put(CommonString.KEY_SKU_NAME, data.get(i).getSkuname());
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_FACEUP, data.get(i).getSkuqty1());
				values.put(CommonString.KEY_STOCK, data.get(i).getSkuqty());
				values.put(CommonString.KEY_LENGTH, data.get(i).getSkuqty2());
				values.put(CommonString.KEY_DOM1, dom1);
				values.put(CommonString.KEY_DOM2, dom2);
				values.put(CommonString.KEY_DOM3, dom3);
				values.put("SKU_REASON_ID", data.get(i).getReasonid());
				values.put(CommonString.KEY_COMAPANY_NAME, type);
				values.put(CommonString.KEY_BRAND_ID, brandid);
				db.insert(CommonString.TABLE_SOS_PEPSI, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	
	public int getComptetionRecord() {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			dbcursor = db.rawQuery("SELECT COMPANY_NAME FROM SOS_PEPSI_OOS WHERE COMPANY_NAME = "+"'pepsi'", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
			
		}
		return cnt;

	}
	
	
	
	
	public int getallinteredSOSrecord(String storeid) {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			dbcursor = db.rawQuery("SELECT * FROM SOS_PEPSI WHERE STORE_ID ='"+storeid +"'", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
			
		}
		return cnt;

	}
	
	
	
	
	public int getComptetionRecordfromAdditional() {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			dbcursor = db.rawQuery("SELECT COMPANY_NAME FROM SOD_MASTER WHERE COMPANY_NAME != "+"'Pepsico'", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return cnt;

	}
	
	
	//for Beverages
	
	
	public int getComptetionRecordfromAdditionalBeverages() {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			dbcursor = db.rawQuery("SELECT COMPANY_NAME FROM SOD_TRANSACTION WHERE COMPANY_NAME != "+"'Pepsico' and VERTICAL = 'Beverages'", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return cnt;

	}
	
	
	public int getComptetionRecordfromAdditionalBeveragesDownloadedData(String Store_id) {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			//dbcursor = db.rawQuery("SELECT COMPANY_NAME FROM SOD_TRANSACTION WHERE COMPANY_NAME != "+"'Pepsico' and VERTICAL = 'Beverages'", null);
			dbcursor = db.rawQuery("select DISTINCT VM.VERTICAL_ID from SKU_FOCUS F INNER JOIN SKU_MASTER SK ON F.SKU_ID = SK.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN CATEGORY_VERTICALMAPPING  CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID AND BR.COMPANY_ID <> 1 AND STORE_ID = '"+Store_id +"' AND VM.VERTICAL_ID = 2", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return cnt;

	}
	
	
	public int getComptetionRecordfromAdditionalFOODDownloadedData(String Store_id) {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			//dbcursor = db.rawQuery("SELECT COMPANY_NAME FROM SOD_TRANSACTION WHERE COMPANY_NAME != "+"'Pepsico' and VERTICAL = 'Beverages'", null);
			dbcursor = db.rawQuery("select DISTINCT VM.VERTICAL_ID from SKU_FOCUS F INNER JOIN SKU_MASTER SK ON F.SKU_ID = SK.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN CATEGORY_VERTICALMAPPING  CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID AND BR.COMPANY_ID <> 1 AND STORE_ID = '"+Store_id +"' AND VM.VERTICAL_ID = 1", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return cnt;

	}
	
	
	//for Food
	
	
	public int getComptetionRecordfromAdditionalFood() {

		Cursor dbcursor = null;
		int cnt = 0;
		try {
			
		
			dbcursor = db.rawQuery("SELECT COMPANY_NAME FROM SOD_TRANSACTION WHERE COMPANY_NAME != "+"'Pepsico' and VERTICAL = 'Food'", null);


			 cnt = dbcursor.getCount();
			dbcursor.close();
		    return cnt;

		} catch(Exception ex){
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return cnt;

	}
	
	
	
	
	
	
	
	
	public void InsertSosPepsiDataOOS(long mid, String storeid,
			ArrayList<VericalBrandBean> data, String brandid, String type, String visit_date) {

		ContentValues values = new ContentValues();

		
		
		
		try {
			for (int i = 0; i < data.size(); i++) {
				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuid());
				values.put(CommonString.KEY_SKU_NAME, data.get(i).getSkuname());
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_SPINNER, data.get(i).getReason());
				values.put(CommonString.KEY_COMAPANY_NAME, type);
				values.put(CommonString.KEY_BRAND_ID, brandid);
				values.put(CommonString.KEY_VISIT_DATE, visit_date);
				db.insert(CommonString.TABLE_SOS_PEPSI_OOS, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	
	public ArrayList<OosEntryBean> getPepsiDataOOS(String storeid, String visit_date) {

		ArrayList<OosEntryBean> list = new ArrayList<OosEntryBean>();
		Cursor dbcursor = null;

		try {
			/*dbcursor = db.rawQuery("SELECT DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					, null);*/
		
			dbcursor = db.rawQuery("SELECT * FROM SOS_PEPSI_OOS"+ " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'" +" AND "+CommonString.KEY_VISIT_DATE + "='"+ visit_date+"'", null);


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					OosEntryBean sb = new OosEntryBean();

		
					sb.setSku_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
					
					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SPINNER)));
			
					
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;


	}
	public void UpdateSosPepsiData(String storeid,
			ArrayList<VericalBrandBean> data, String brandid, String type) {

		ContentValues values = new ContentValues();

		try {

			String reasonid, dom1, dom2, dom3;
			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getDom1().equalsIgnoreCase("MFD")
						&& data.get(i).getDom2().equalsIgnoreCase("MFD")
						&& data.get(i).getDom3().equalsIgnoreCase("MFD")) {
					dom1 = "1/1/1900";
					dom2 = "1/1/1900";
					dom3 = "1/1/1900";
				} else {
					dom1 = data.get(i).getDom1();
					dom2 = data.get(i).getDom2();
					dom3 = data.get(i).getDom3();
				}

				if (data.get(i).getReasonid().equalsIgnoreCase(null)
						|| data.get(i).getReasonid().equalsIgnoreCase("")) {
					reasonid = "0";
				} else {
					reasonid = data.get(i).getReasonid();
				}

				values.put(CommonString.KEY_FACEUP, data.get(i).getSkuqty1());
				values.put(CommonString.KEY_STOCK, data.get(i).getSkuqty());
				values.put(CommonString.KEY_LENGTH, data.get(i).getSkuqty2());
				values.put(CommonString.KEY_DOM1, dom1);
				values.put(CommonString.KEY_DOM2, dom2);
				values.put(CommonString.KEY_DOM3, dom3);
				values.put("SKU_REASON_ID", reasonid);

				db.update(CommonString.TABLE_SOS_PEPSI, values,
						CommonString.KEY_SKU_ID + " =? AND "
								+ CommonString.KEY_STORE_ID + " =? AND "
								+ CommonString.KEY_BRAND_ID + " =? AND "
								+ CommonString.KEY_COMAPANY_NAME + " =?",
						new String[] { data.get(i).getSkuid(), storeid,
								brandid, type });

			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// assets data

	public void InsertAssetData(long mid, String storeid,
			ArrayList<PosmBean> data) {

		db.delete(CommonString.TABLE_INSERT_ASSET_DATA,
				CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
		
		ContentValues values = new ContentValues();

		String available;
		try {

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getAvailable().equalsIgnoreCase("YES")) {
					available = "1";
				} else {
					available = "0";
				}

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_ASSETS, data.get(i).getAssest());
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_ASSETS_ID, data.get(i).getAssetid());
				values.put(CommonString.KEY_AVAILABLE, available);
				values.put(CommonString.KEY_REMARKS, data.get(i).getRemarks()
						.replaceAll("[&^<>{}'$]", " "));
				values.put(CommonString.KEY_IMAGE_PATH1, data.get(i)
						.getImage1());

				values.put(CommonString.KEY_IMAGE_PATH2, data.get(i)
						.getImage2());

				values.put(CommonString.KEY_IMAGE_PATH3, data.get(i)
						.getImage3());

				values.put(CommonString.KEY_VERTICAL_ID, data.get(i)
						.getVerticalid());

				values.put(CommonString.KEY_VERTICAL_NAME, data.get(i)
						.getVertical());
				
				values.put(CommonString.KEY_ASSET_PURE, data.get(i)
						
						.getAssetpure());
				
				values.put(CommonString.KEY_ASSET_PURE_DOWNLOAD, data.get(i)
						.getDownlodassetpure());

				db.insert(CommonString.TABLE_INSERT_ASSET_DATA, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void UpdateAssetData(String storeid, ArrayList<PosmBean> data) {

	
		
		ContentValues values = new ContentValues();

		String available;
		try {

			
			for (int i = 0; i < data.size(); i++) {
				
				

				if (data.get(i).getAvailable().equalsIgnoreCase("YES")) {
					available = "1";
				} else {
					available = "0";
				}

				values.put(CommonString.KEY_AVAILABLE, available);
				values.put(CommonString.KEY_REMARKS, data.get(i).getRemarks()
						.replaceAll("[&^<>{}'$]", " "));
				values.put(CommonString.KEY_IMAGE_PATH1, data.get(i)
						.getImage1());
				values.put(CommonString.KEY_IMAGE_PATH2, data.get(i)
						.getImage2());
				values.put(CommonString.KEY_IMAGE_PATH3, data.get(i)
						.getImage3());
				values.put(CommonString.KEY_ASSET_PURE, data.get(i)
						.getAssetpure());

				db.update(CommonString.TABLE_INSERT_ASSET_DATA, values,
						CommonString.KEY_ASSETS_ID + " =? AND "
								+ CommonString.KEY_STORE_ID + " =?",
						new String[] { data.get(i).getAssetid(), storeid });

			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// getCoverageData
	public void updateCoverageStatus(int mid, String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, status);

			db.update(CommonString.TABLE_COVERAGE_DATA, values,
					CommonString.KEY_ID + "=" + mid, null);
		} catch (Exception e) {

		}
	}

	// insert promo data

	public void InsertPromotionData(long mid, String storeid,
			ArrayList<PromoBean> data) {

		ContentValues values = new ContentValues();
		String available;

		try {

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getAvailable().equalsIgnoreCase("YES")) {
					available = "1";
				} else {
					available = "0";
				}

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_PROMO, data.get(i).getPromo());
				values.put(CommonString.KEY_PROMO_ID, data.get(i).getPromoid());
				values.put(CommonString.KEY_PROMO_TYPE, data.get(i)
						.getPromotype());
				values.put(CommonString.KEY_PROMO_TYPE_ID, data.get(i)
						.getPromotypeid());

				values.put(CommonString.KEY_IMAGE, data.get(i).getImage());

				values.put(CommonString.KEY_AVAILABLE, available);
				values.put(CommonString.KEY_REMARKS, data.get(i).getRemarks()
						.replaceAll("[&^<>{}'$]", " "));

				db.insert(CommonString.TABLE_INSERT_PROMOTION_PEPSI, null,
						values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void UpdatePromotionData(String storeid, ArrayList<PromoBean> data) {

		ContentValues values = new ContentValues();
		String available;

		try {

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getAvailable().equalsIgnoreCase("YES")) {
					available = "1";
				} else {
					available = "0";
				}

				values.put(CommonString.KEY_IMAGE, data.get(i).getImage());

				values.put(CommonString.KEY_AVAILABLE, available);
				values.put(CommonString.KEY_REMARKS, data.get(i).getRemarks()
						.replaceAll("[&^<>{}'$]", " "));
				db.update(CommonString.TABLE_INSERT_PROMOTION_PEPSI, values,
						CommonString.KEY_PROMO_ID + " =? AND "
								+ CommonString.KEY_STORE_ID + " =? AND "
								+ CommonString.KEY_PROMO_TYPE_ID + " =?",
						new String[] { data.get(i).getPromoid(), storeid,
								data.get(i).getPromotypeid() });

			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// get assets data

	public ArrayList<PosmBean> getAssetData(long mid) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_ASSET_DATA + " WHERE "
					+ CommonString.MID + " =" + mid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();

					sb.setAssest(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)))));

					sb.setAssetid((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID))))));

					sb.setAvailable(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)))));

					sb.setRemarks(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)))));

					sb.setImage1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)))));

					sb.setImage2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)))));

					sb.setImage3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)))));

					sb.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));

					sb.setVerticalid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					sb.setAssetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}
	
	
	
	
	public ArrayList<PosmBean> getAssetDataOur(String mid) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " WHERE "
					+ CommonString.KEY_SOD_ID + " =" + mid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();

					sb.setAssest(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)))));

					sb.setAssetid((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID))))));

					sb.setAvailable(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)))));

					sb.setRemarks(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)))));

					sb.setImage1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)))));

					sb.setImage2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)))));

					sb.setImage3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)))));

					sb.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));

					sb.setVerticalid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					sb.setAssetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	public ArrayList<PosmBean> getAssetDataStore(String storeid) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_ASSET_DATA + " WHERE "
					+ CommonString.KEY_STORE_ID + " =" + storeid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();

					sb.setAssest(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)))));

					sb.setAssetid((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID))))));

					sb.setAvailable(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)))));

					sb.setRemarks(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)))));

					sb.setImage1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)))));

					sb.setImage2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)))));

					sb.setImage3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)))));

					sb.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));

					sb.setVerticalid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					sb.setAssetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
					
					sb.setDownlodassetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE_DOWNLOAD)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}
	
	//behalf of mid get displat temp data
	
	public ArrayList<PosmBean> getDisplayDataTemp(long mid) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_DISPLAY_TEMPDATA + " WHERE "
					+ CommonString.MID + " =" + mid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();

					sb.setAssest(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)))));

					sb.setAssetid((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID))))));

					sb.setAvailable(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)))));

					sb.setRemarks(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)))));

					sb.setImage1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)))));

					sb.setImage2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)))));

					sb.setImage3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)))));

					sb.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));

					sb.setVerticalid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					sb.setAssetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}
	
	
	// getTempDisplay data
	
	public ArrayList<PosmBean> getTempDisplayData(String storeid) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_DISPLAY_TEMPDATA + " WHERE "
					+ CommonString.KEY_STORE_ID + " =" + storeid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();

					sb.setAssest(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)))));

					sb.setAssetid((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID))))));

					sb.setAvailable(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)))));

					sb.setRemarks(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)))));

					sb.setImage1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)))));

					
					
					sb.setImage2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)))));

					sb.setImage3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)))));

					sb.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));

					sb.setVerticalid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					sb.setAssetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
					
					sb.setDownlodassetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE_DOWNLOAD)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	// availblity upload

	public ArrayList<VericalBrandBean> getAvailableUploadData(String storeid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT MP.SKU_ID, IFNULL( AV.STOCK,0) AS STOCK, IFNULL(AV.FACEUP,0) AS FACEUP, IFNULL(AV.LENGTH,0) AS LENGTH,IFNULL(AV.DOM1,'1/1/1900') AS DOM1 ,IFNULL(AV.DOM2,'1/1/1900') AS DOM2  ,IFNULL(AV.DOM3,'1/1/1900') AS DOM3,IFNULL(AV.SKU_REASON_ID,0) AS SKU_REASON_ID FROM SKU_FOCUS MP LEFT OUTER JOIN SOS_PEPSI AV ON MP.SKU_ID = AV.SKU_ID AND MP.STORE_ID = AV.STORE_ID WHERE MP.STORE_ID ='"
									+ storeid + "'", null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)))));

					sb.setSkuqty((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK))))));

					sb.setSkuqty1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)))));

			    	sb.setSkuqty2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)))));

					sb.setDom1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)))));

					sb.setDom2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)))));

					sb.setDom3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)))));

					sb.setReasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SKU_REASON_ID")))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	// sos edit

	public ArrayList<VericalBrandBean> getSosEditData(String storeid,
			String brandid, String company) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_PEPSI + " WHERE "
					+ CommonString.KEY_STORE_ID + " ='" + storeid + "' AND "
					+ CommonString.KEY_COMAPANY_NAME + " ='" + company
					+ "' AND " + CommonString.KEY_BRAND_ID + " ='" + brandid
					+ "'", null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)))));

					sb.setSkuid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)))));

					sb.setSkuqty((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK))))));

					sb.setSkuqty1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)))));

				/*	sb.setSkuqty2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)))));*/

						sb.setSkuqty2("0");
					
					sb.setDom1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)))));

					sb.setDom2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)))));

					sb.setDom3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)))));

					sb.setReasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SKU_REASON_ID")))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	public ArrayList<VericalBrandBean> getSosData(int mid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_PEPSI + " WHERE "
					+ CommonString.MID + " =" + mid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)))));

					sb.setSkuqty((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK))))));

					sb.setSkuqty1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)))));

					sb.setSkuqty2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)))));

					sb.setDom1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)))));

					sb.setDom2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)))));

					sb.setDom3(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)))));

					sb.setReasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SKU_REASON_ID")))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}
	
	
	// get the brandwise faceup ashish
	
	public ArrayList<VericalBrandBean> getBrandwiseFaceupforPepsi(String branId,String type) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  FACEUP from "
					+ CommonString.TABLE_SOS_PEPSI + "  WHERE "
					+ CommonString.KEY_BRAND_ID + " ='" + branId+"'" +" AND "+ CommonString.KEY_COMAPANY_NAME + " ='" + type
					+ "'", null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();


					sb.setSkuqty1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)))));

			

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}
	
	
	
	

	// Get Cateogryimagedata

	public ArrayList<CateogryDataDelegates> getCateogrydataData(String mid) {

		ArrayList<CateogryDataDelegates> list = new ArrayList<CateogryDataDelegates>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_IMAGES + " WHERE "
					+ CommonString.KEY_STORE_ID + " =" + mid, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CateogryDataDelegates sb = new CateogryDataDelegates();

					sb.setCateogryid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)))));

					sb.setImageurl((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1))))));

					sb.setImageurl1(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)))));

					sb.setImageurl2(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return list;

	}

	public void updateGeoTagDataInMain(String storeid) {

		try {

			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, CommonString.KEY_U);

			int l = db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
					CommonString.KEY_STORE_ID + "=?", new String[] { storeid });
			System.out.println("update : " + l);
		} catch (Exception e) {

		}
	}

	public void delete_temp() {
		db.delete(CommonString.TABLE_SOD_OTHERDATA, null, null);
	}
	
	public void delete_tempDisplay() {
		db.delete(CommonString.TABLE_INSERT_DISPLAY_TEMPDATA, null, null);
	}
	public void delete_tempDisplaySku() {
		db.delete(CommonString.TABLE_DISPLAY_SKU, null, null);
	}

	
	public void deleteDailyEntryData() {
		db.delete(CommonString.TABLE_SOS_PEPSI_OOS, null, null);
	}

	public long InsertSodData(long mid, SodBean data) {

		ContentValues values = new ContentValues();
		db.delete(CommonString.TABLE_SOD_OTHERDATA, null, null);

		long sod_id = 0;
		try {
	
			
			values.put(CommonString.MID, mid);
			values.put(CommonString.KEY_COMPANY_ID, data.getCompanyid());

			values.put(CommonString.KEY_POSM_ID, data.getPosmid());

			values.put(CommonString.KEY_POSM, data.getPosm());

			values.put(CommonString.KEY_COMAPANY_NAME, data.getCompanyname());

			values.put(CommonString.KEY_STORE_ID, data.getStoreid());

			values.put(CommonString.KEY_LENGTH, data.getL());

			values.put(CommonString.KEY_HEIGHT, data.getH());

			values.put(CommonString.KEY_BREATH, data.getB());

			values.put(CommonString.KEY_IMAGE_PATH1, data.getImg1());

			values.put(CommonString.KEY_IMAGE_PATH2, data.getImg2());
			values.put(CommonString.KEY_IMAGE_PATH3, data.getImg3());
			
			sod_id = db.insert(CommonString.TABLE_SOD_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return sod_id;

	}
	
	public long InsertDisplayPaidfinalData(long mid, SodBean data) {

		ContentValues values = new ContentValues();
		db.delete(CommonString.TABLE_DISPLAY_SKU, null, null);

		long sod_id = 0;
		try {

			values.put(CommonString.MID, mid);
			values.put(CommonString.KEY_COMPANY_ID, data.getCompanyid());

			

			

			values.put(CommonString.KEY_COMAPANY_NAME, data.getCompanyname());

			values.put(CommonString.KEY_STORE_ID, data.getStoreid());

			values.put(CommonString.KEY_LENGTH, data.getL());

			values.put(CommonString.KEY_HEIGHT, data.getH());

			values.put(CommonString.KEY_BREATH, data.getB());

			

			sod_id = db.insert(CommonString.TABLE_DISPLAY_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return sod_id;

	}

	public void InsertSodTransactionData(long sod_id, ArrayList<SodBean> data,String compname) {

		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString.KEY_SOD_ID, sod_id);
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuid());

				values.put(CommonString.KEY_SKU_NAME, data.get(i).getSkuname());

				values.put(CommonString.KEY_STOCK, data.get(i).getStock());

				values.put(CommonString.KEY_STORE_ID, data.get(i).getStoreid());

				values.put(CommonString.KEY_FACEUP, data.get(i).getFaceup());

				values.put(CommonString.KEY_DOM1, data.get(i).getDom1());

				values.put(CommonString.KEY_DOM2, data.get(i).getDom2());

				values.put(CommonString.KEY_DOM3, data.get(i).getDom3());
				values.put("VERTICAL", data.get(i).getVerticalName());
				values.put("COMPANY_NAME", compname);

				db.insert(CommonString.TABLE_SOD_PEPSIDATA, null, values);
			}
		}

		catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());

		}
	}
	
	public void InsertDisplayTransaction(long mid, ArrayList<SodBean> data, ArrayList<PosmBean> temp_list1, SodBean sod, String index, String randomNumber, String sECONDARY_KEY, String brandId) {
		 int position =  Integer.parseInt(index);
		
		 db.delete(CommonString.TABLE_DISPLAY_TRANSACTION,
				CommonString.KEY_ASSETS_ID + "='" + temp_list1.get(position).getAssetid() + "'" +" AND " +CommonString.KEY_STORE_ID + "='" +data.get(0).getStoreid() +"'" +" AND " +CommonString.KEY_SECONDARY + "='" + sECONDARY_KEY +"'" , null);
		ContentValues values = new ContentValues();
       
		try {

			for (int i = 0; i < data.size(); i++) {
				
				
				
				values.put(CommonString.KEY_BRAND_ID, brandId);
				values.put(CommonString.KEY_RANDOM, randomNumber);
				values.put(CommonString.KEY_LENGTH, sod.getL());
				
				values.put(CommonString.KEY_HEIGHT, sod.getH());

				values.put(CommonString.KEY_BREATH, sod.getB());

//add sku
				values.put(CommonString.KEY_SOD_ID, mid);
				
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuid());

				values.put(CommonString.KEY_SKU_NAME, data.get(i).getSkuname());

				values.put(CommonString.KEY_STOCK, data.get(i).getStock());

				values.put(CommonString.KEY_STORE_ID, data.get(i).getStoreid());

				values.put(CommonString.KEY_FACEUP, data.get(i).getFaceup());

				values.put(CommonString.KEY_DOM1, data.get(i).getDom1());

				values.put(CommonString.KEY_DOM2, data.get(i).getDom2());

				values.put(CommonString.KEY_DOM3, data.get(i).getDom3());
				
			//	values.put(CommonString.MID, mid);
				//first list enter
				
				values.put(CommonString.KEY_ASSETS, temp_list1.get(position).getAssest());
			//	values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_ASSETS_ID, temp_list1.get(position).getAssetid());
				values.put(CommonString.KEY_AVAILABLE, "YES");
				values.put(CommonString.KEY_REMARKS, temp_list1.get(position).getRemarks()
						.replaceAll("[&^<>{}'$]", " "));
				values.put(CommonString.KEY_IMAGE_PATH1, temp_list1.get(position)
						.getImage1());
//				values.put(CommonString.KEY_SECONDARY, temp_list1.get(position)
//						.getSECONDARYKEY());
				values.put(CommonString.KEY_IMAGE_PATH2, temp_list1.get(position)
						.getImage2());

				values.put(CommonString.KEY_IMAGE_PATH3, temp_list1.get(position)
						.getImage3());

				values.put(CommonString.KEY_VERTICAL_ID, temp_list1.get(position)
						.getVerticalid());

				values.put(CommonString.KEY_VERTICAL_NAME, temp_list1.get(position)
						.getVertical());
				
				values.put(CommonString.KEY_ASSET_PURE, temp_list1.get(position)
						
						.getAssetpure());
				
				values.put(CommonString.KEY_ASSET_PURE_DOWNLOAD, temp_list1.get(position)
						.getDownlodassetpure());
				
				values.put(CommonString.KEY_SECONDARY, sECONDARY_KEY);

				db.insert(CommonString.TABLE_DISPLAY_TRANSACTION, null, values);
			}
		}

		catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());

		}
	}
	
	
	public void InsertDisplayTransactionNO(long mid, ArrayList<SodBean> data, ArrayList<PosmBean> temp_list1, SodBean sod,String storeid, String asset, String assetid, String remark, String verticalid, String randomNumber, String sECONDARY_KEY, String brandId, String index) {

	//	 int position =  Integer.parseInt(index);
		 db.delete(CommonString.TABLE_DISPLAY_TRANSACTION,
					CommonString.KEY_ASSETS_ID + "='" + assetid + "'" +" and " +CommonString.KEY_STORE_ID + "='" + storeid +"'" +" and " +CommonString.KEY_SECONDARY + "='" + sECONDARY_KEY +"'", null);
//		
//		 db.delete(CommonString.TABLE_DISPLAY_TRANSACTION,
//					CommonString.KEY_ASSETS_ID + "='" + assetid + "'", null);
		ContentValues values = new ContentValues();

		try {

		//	for (int i = 0; i < data.size(); i++) {
				
				
			
			   values.put(CommonString.KEY_RANDOM, randomNumber);
			   values.put(CommonString.KEY_SECONDARY, sECONDARY_KEY);
				values.put(CommonString.KEY_LENGTH, "0");
				values.put(CommonString.KEY_BRAND_ID, brandId);
				values.put(CommonString.KEY_HEIGHT, "0");

				values.put(CommonString.KEY_BREATH, "0");

//add sku
				values.put(CommonString.KEY_SOD_ID, mid);
				
				values.put(CommonString.KEY_SKU_ID, "");

				values.put(CommonString.KEY_SKU_NAME, "0");

				values.put(CommonString.KEY_STOCK, "0");

				values.put(CommonString.KEY_STORE_ID, storeid);

				values.put(CommonString.KEY_FACEUP, "0");

				values.put(CommonString.KEY_DOM1, "");

				values.put(CommonString.KEY_DOM2, "");

				values.put(CommonString.KEY_DOM3, "");
				
			//	values.put(CommonString.MID, mid);
				//first list enter
				
				values.put(CommonString.KEY_ASSETS, asset);
			//	values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_ASSETS_ID, assetid);
				values.put(CommonString.KEY_AVAILABLE, "NO");
				values.put(CommonString.KEY_REMARKS, remark
						.replaceAll("[&^<>{}'$]", " "));
				values.put(CommonString.KEY_IMAGE_PATH1, "");

				values.put(CommonString.KEY_IMAGE_PATH2, "");

				values.put(CommonString.KEY_IMAGE_PATH3, "");

				values.put(CommonString.KEY_VERTICAL_ID, verticalid);

				values.put(CommonString.KEY_VERTICAL_NAME,verticalid);
				
				values.put(CommonString.KEY_ASSET_PURE, "False");
				
				values.put(CommonString.KEY_ASSET_PURE_DOWNLOAD, "False");

				db.insert(CommonString.TABLE_DISPLAY_TRANSACTION, null, values);
			//}
		}

		catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());

		}
	}

	public void InsertSodTempData(long mid, SodBean data) {

		String dom1 = "", dom2 = "", dom3 = "";
		ContentValues values = new ContentValues();

		if (!data.getDom1().equalsIgnoreCase("MFD")) {
			dom1 = data.getDom1();
		}

		if (!data.getDom2().equalsIgnoreCase("MFD")) {
			dom2 = data.getDom2();
		}

		if (!data.getDom3().equalsIgnoreCase("MFD")) {
			dom3 = data.getDom3();
		}

		try {

			values.put(CommonString.MID, mid);
			values.put(CommonString.KEY_SKU_ID, data.getSkuid());

			values.put(CommonString.KEY_SKU_NAME, data.getSkuname());

			values.put(CommonString.KEY_STOCK, data.getStock());

			values.put(CommonString.KEY_STORE_ID, data.getStoreid());

			values.put(CommonString.KEY_FACEUP, data.getFaceup());
			
			values.put("VERTICAL", data.getVerticalName());

			values.put(CommonString.KEY_DOM1, dom1);

			values.put(CommonString.KEY_DOM2, dom2);

			values.put(CommonString.KEY_DOM3, dom3);
			
			db.insert(CommonString.TABLE_SOD_OTHERDATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	public void InsertSodDisplayTempData(long mid, SodBean data) {

		String dom1 = "", dom2 = "", dom3 = "";
		ContentValues values = new ContentValues();

		if (!data.getDom1().equalsIgnoreCase("MFD")) {
			dom1 = data.getDom1();
		}

		if (!data.getDom2().equalsIgnoreCase("MFD")) {
			dom2 = data.getDom2();
		}

		if (!data.getDom3().equalsIgnoreCase("MFD")) {
			dom3 = data.getDom3();
		}

		try {

			values.put(CommonString.MID, mid);
			values.put(CommonString.KEY_SKU_ID, data.getSkuid());

			values.put(CommonString.KEY_SKU_NAME, data.getSkuname());

			values.put(CommonString.KEY_STOCK, data.getStock());

			values.put(CommonString.KEY_STORE_ID, data.getStoreid());

			values.put(CommonString.KEY_FACEUP, data.getFaceup());

			values.put(CommonString.KEY_DOM1, dom1);

			values.put(CommonString.KEY_DOM2, dom2);

			values.put(CommonString.KEY_DOM3, dom3);

			db.insert(CommonString.TABLE_DISPLAY_SKU, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	

	public void InsertDisplayTempData(long mid, String storeid,
			ArrayList<PosmBean> data) {

		db.delete(CommonString.TABLE_INSERT_DISPLAY_TEMPDATA,
				CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
		
		ContentValues values = new ContentValues();

		String available;
		try {

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getAvailable().equalsIgnoreCase("YES")) {
					available = "1";
				} else {
					available = "0";
				}

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_ASSETS, data.get(i).getAssest());
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_ASSETS_ID, data.get(i).getAssetid());
				values.put(CommonString.KEY_AVAILABLE, available);
				values.put(CommonString.KEY_REMARKS, data.get(i).getRemarks()
						.replaceAll("[&^<>{}'$]", " "));
				values.put(CommonString.KEY_IMAGE_PATH1, data.get(i)
						.getImage1());

				values.put(CommonString.KEY_IMAGE_PATH2, data.get(i)
						.getImage2());

				values.put(CommonString.KEY_IMAGE_PATH3, data.get(i)
						.getImage3());

				values.put(CommonString.KEY_VERTICAL_ID, data.get(i)
						.getVerticalid());

				values.put(CommonString.KEY_VERTICAL_NAME, data.get(i)
						.getVertical());
				
				values.put(CommonString.KEY_ASSET_PURE, data.get(i)
						
						.getAssetpure());
				
				values.put(CommonString.KEY_ASSET_PURE_DOWNLOAD, data.get(i)
						.getDownlodassetpure());

				db.insert(CommonString.TABLE_INSERT_DISPLAY_TEMPDATA, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	public void InsertPromotionOtherData(long mid, PromotionBean data) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.MID, mid);
			values.put(CommonString.KEY_BRAND_NAME, data.getBrand());

			values.put(CommonString.KEY_BRAND_ID, data.getBrandid());

			values.put(CommonString.KEY_COMPANY_ID, data.getCompanyid());

			values.put(CommonString.KEY_COMAPANY_NAME, data.getCompanyname());

			values.put(CommonString.KEY_STORE_ID, data.getStoreid());

			values.put(CommonString.KEY_REMARKS, data.getRemarks());

			values.put(CommonString.KEY_IMAGE, data.getImage());

			db.insert(CommonString.TABLE_PROMOTION_OTHER, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertSodPepsiData(long mid, String storeid,
			ArrayList<PosmBean> data) {

		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuid());

				values.put(CommonString.KEY_SKU_NAME, data.get(i).getSkuname());

				values.put(CommonString.KEY_POSM, data.get(i).getPosm());
				values.put(CommonString.KEY_POSM_ID, data.get(i).getPosmid());
				values.put(CommonString.KEY_STOCK, data.get(i).getStock());

				values.put(CommonString.KEY_STORE_ID, storeid);

				values.put(CommonString.KEY_FACEUP, data.get(i).getFaceup());

				values.put(CommonString.KEY_LENGTH, data.get(i).getL());

				values.put(CommonString.KEY_HEIGHT, data.get(i).getH());

				values.put(CommonString.KEY_BREATH, data.get(i).getB());

				values.put(CommonString.KEY_IMAGE_PATH1, data.get(i)
						.getImage1());

				values.put(CommonString.KEY_IMAGE_PATH2, data.get(i)
						.getImage2());

				values.put(CommonString.KEY_IMAGE_PATH3, data.get(i)
						.getImage3());

				db.insert(CommonString.TABLE_SOD_PEPSIDATA, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// delete data

	public void DeleteSodData(int id) {

		try {

			db.delete(CommonString.TABLE_SOD_DATA, CommonString.KEY_ID + "="
					+ id, null);

			db.delete(CommonString.TABLE_SOD_PEPSIDATA, CommonString.KEY_SOD_ID
					+ "=" + id, null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	
	public void DeleteDisplayMAsterData(int id) {

		try {

			db.delete(CommonString.TABLE_DISPLAY_DATA, CommonString.KEY_ID + "="
					+ id, null);

			db.delete(CommonString.TABLE_DISPLAY_TRANSACTION, CommonString.KEY_SOD_ID
					+ "=" + id, null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void DeleteSodTempData(int id) {

		try {

			db.delete(CommonString.TABLE_SOD_OTHERDATA, CommonString.KEY_ID
					+ "=" + id, null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	public void DeleteDisplayTempData(int id) {

		try {

			db.delete(CommonString.TABLE_DISPLAY_SKU, CommonString.KEY_ID
					+ "=" + id, null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void DeletePromotion(int id) {

		try {

			db.delete(CommonString.TABLE_PROMOTION_OTHER, CommonString.KEY_ID
					+ "=" + id, null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void deleteGeoTagData(String storeid) {

		try {
			db.delete(CommonString.TABLE_INSERT_GEO_TAG,
					CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
		} catch (Exception e) {

		}
	}

	public void DeleteSodOtherData(int id) {

		try {

			db.delete(CommonString.TABLE_SOD_OTHERDATA, CommonString.KEY_ID
					+ "=" + id, null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public int GetSkuConut() {
		Cursor dbcursor = null;
		try {

			dbcursor = db.rawQuery(
					"SELECT COUNT( DISTINCT SKU_ID) FROM ASSET_DATA", null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}
		return dbcursor.getCount();
	}

	// get sod data

	public ArrayList<SodBean> getSodData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_DATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
			int counter=1;	
			
			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImg1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					sb.setImg2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					sb.setImg3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					sb.setL(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
					sb.setH(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
					sb.setB(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					sb.setCounter(String.valueOf(counter));

					list.add(sb);
					counter++;
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	public ArrayList<SodBean> getDisplayTransactionData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_DISPLAY_DATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
			int counter=1;
			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImg1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					sb.setImg2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					sb.setImg3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					sb.setL(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
					sb.setH(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
					sb.setB(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					sb.setCounter(String.valueOf(counter));
					
					counter++;

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	
	public ArrayList<SodBean> getSodDataMid(String storeid, int mid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_DATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'"
					+ " AND " + CommonString.KEY_MID + " ='" + mid +"'", null);
			
			//making changes for hardcoded value of company id 
			//below original code
			
			/*dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_DATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'"
					+ " AND " + CommonString.KEY_MID + " ='" + mid + "' AND "+ CommonString.KEY_COMPANY_ID +" ='1'", null);*/

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImg1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					sb.setImg2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					sb.setImg3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					sb.setL(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
					sb.setH(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
					sb.setB(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	public ArrayList<SodBean> getSodDataPepsico(String storeid, int mid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			
			
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_DATA +" ,sod_transaction " + " where sod_master._id = sod_transaction.sod_id and "
					+ "sod_master.STORE_ID" + "='" + storeid + "'"
					+ " AND " + CommonString.KEY_MID + " ='" + mid +"'", null);
			
			//making changes for hardcoded value of company id 
			//below original code
			
			/*dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_DATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'"
					+ " AND " + CommonString.KEY_MID + " ='" + mid + "' AND "+ CommonString.KEY_COMPANY_ID +" ='1'", null);*/

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();
					
					sb.setSodid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SOD_ID)));

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImg1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					sb.setImg2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					sb.setImg3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					sb.setL(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
					sb.setH(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
					sb.setB(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					
					
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
					
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
					
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<SodBean> getSodDataMidForTick(String storeid, int mid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_DATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'"
					+ " AND " + CommonString.KEY_MID + " ='" + mid + "' AND "+ CommonString.KEY_COMPANY_ID +" ='1'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImg1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					sb.setImg2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					sb.setImg3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					sb.setL(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
					sb.setH(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
					sb.setB(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	public int getSosDataMid(String storeid, int mid) {

		Cursor dbcursor = null;
		int count = 0;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_PEPSI + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'"
					+ " AND " + CommonString.KEY_MID + " ='" + mid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				count = dbcursor.getCount();
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return count;

		}
		return count;

	}

	public ArrayList<SodBean> getTransData(int id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_PEPSIDATA + " where "
					+ CommonString.KEY_SOD_ID + "='" + id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setSodid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SOD_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));

					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get promotion other

	public ArrayList<PromotionBean> gePromotionOtherData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PromotionBean> list = new ArrayList<PromotionBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_PROMOTION_OTHER + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PromotionBean sb = new PromotionBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setRemarks(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get promotion other

	public ArrayList<PromotionBean> gePromotionOtherMid(int mid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PromotionBean> list = new ArrayList<PromotionBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_PROMOTION_OTHER + " where "
					+ CommonString.MID + "='" + mid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PromotionBean sb = new PromotionBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setRemarks(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
					sb.setCompanyid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
					sb.setCompanyname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME)));

					sb.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get promotion pepsi

	public ArrayList<PromoBean> gePromotionPepsiData(int mid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PromoBean> list = new ArrayList<PromoBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_PROMOTION_PEPSI + " where "
					+ CommonString.MID + "='" + mid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PromoBean sb = new PromoBean();

					sb.setPromo(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO)));

					sb.setPromoid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_ID)));
					sb.setPromotype(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_TYPE)));
					sb.setPromotypeid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_TYPE_ID)));

					sb.setAvailable(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));

					sb.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));

					sb.setRemarks(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get promotion pepsi

	public ArrayList<PromoBean> gePromotionPepsi(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PromoBean> list = new ArrayList<PromoBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_PROMOTION_PEPSI + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PromoBean sb = new PromoBean();

					sb.setPromo(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO)));

					sb.setPromoid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_ID)));
					sb.setPromotype(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_TYPE)));
					sb.setPromotypeid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_TYPE_ID)));

					sb.setAvailable(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));

					sb.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));

					sb.setRemarks(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	public ArrayList<PromoBean> gePromotionDownloaded(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PromoBean> list = new ArrayList<PromoBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_PROMOTION_PEPSI + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PromoBean sb = new PromoBean();

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setPromo(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO)));

					sb.setPromoid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_ID)));
					sb.setPromotype(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_TYPE)));
					sb.setPromotypeid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_PROMO_TYPE_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get sod otehr data

	public ArrayList<SodBean> getSodOtherData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_OTHERDATA + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));
					
					sb.setVerticalName(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("VERTICAL")));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	public ArrayList<SodBean> getDisplaySkuData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_DISPLAY_SKU + " where "
					+ CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get sod otehr data

	public ArrayList<SodBean> getSodTransactionData(long sod_id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SodBean> list = new ArrayList<SodBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOD_PEPSIDATA + " where "
					+ CommonString.KEY_SOD_ID + "=" + sod_id + "", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SodBean sb = new SodBean();

					sb.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID)));

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_allAssetsFORVALIDATIAON(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT DISTINCT SECONDARY FROM DISPLAY_TRANSACTION where STORE_ID ="+ storeid 
					, null);
		
//			dbcursor = db.rawQuery("SELECT ASSETS_ID FROM DISPLAY_TRANSACTION where STORE_ID ="+ storeid 
//					, null);
		
			
			
			
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

			
			sb.setAssets_Id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow("SECONDARY")));
			
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	
	// get sod otehr data

	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_allAssets(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT ASSETS_ID FROM DISPLAY_TRANSACTION where STORE_ID ="+ storeid 
					, null);
		
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

			
			sb.setAssets_Id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
			
			sb.setAssets_Id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_SECONDARY)));
			
			
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_allAssets_allData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT IMAGE_PATH1,IMAGE_PATH2,IMAGE_PATH3 FROM DISPLAY_TRANSACTION where STORE_ID ="+ storeid 
					, null);
		
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

		
			sb.setImage_Path1(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
			sb.setImage_Path2(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
			sb.setImage_Path3(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_chillars(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT DISTINCT SECONDARY,ASSETS_ID FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_STORE_ID+ "=" + storeid + "", null);

					
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

					
					
					sb.setSecondary(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SECONDARY)));
					
					
					sb.setAssets_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
					
			/*
					sb.setImage_Path2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					
					
					
					sb.setImage_Path3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					
					sb.setImage_Path1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					
					
					sb.setVertical_ID(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					
					sb.setVertical_Name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));
					
					sb.setAvailable(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));
					sb.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
					
					sb.setAsset_Pure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
					
					sb.setBrandId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));*/
			/*		
			sb.setAsset_PureDownload(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE_DOWNLOAD)));
			
			
			sb.setStore_id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
			
			sb.setVertical_Name(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
			sb.setAsset_Pure(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));

			
			
	
			
			
			sb.setVertical_ID(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
			
			sb.setVertical_Name(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));
			sb.setAsset_Pure(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

			
			sb.setAssets(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSETS)));
			sb.setAssets_Id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
			sb.setAvailable(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));
			sb.setRemark(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
			sb.setImage_Path1(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));

				sb.setLenth(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
				sb.setHeight(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
				sb.setBreath(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					sb.setSoid_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
					sb.setSku_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
					sb.setSku_Name(dbcursor.getString(dbcursor
							
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));
					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
					sb.setStore_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));*/
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	
	
	
	public ArrayList<FinalDataBeanDisplay> getTotalDisplayTransactionData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_STORE_ID+ "=" + storeid + "", null);

					
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();
//
//					sb.setHeight(dbcursor.getString(dbcursor
//							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
//					sb.setLenth(dbcursor.getString(dbcursor
//							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
//					sb.setBreath(dbcursor.getString(dbcursor
//							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
//					
		
					sb.setRandomnumber(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_RANDOM)));		
			sb.setAsset_PureDownload(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE_DOWNLOAD)));
	
			sb.setBrandId(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

			sb.setImage_Path2(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
			
			
			
			sb.setImage_Path3(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
			
			sb.setImage_Path1(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
			sb.setAsset_Pure(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
			
			sb.setVertical_ID(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
			sb.setAvailable(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));
			sb.setRemark(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));

			
			sb.setAssets_Id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
			sb.setStock(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
			
			sb.setStore_id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
			sb.setDom1(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
			

			sb.setAuto_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("_id")));
			
			sb.setSku_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
			
			sb.setFaceup(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	
	// get asset yes data
	
	public String getAssetYesdataFood(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		String assetYesPer = "";
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  CAST(SUM(AVL) AS FLOAT)/CAST(COUNT(AVL) AS FLOAT)*100 AS ASSET  FROM " 
					+"(select DISTINCT ASSETS_ID, CASE WHEN AVAILABLE ='NO' THEN 0 ELSE 1 END AS AVL from DISPLAY_TRANSACTION  where Store_id = '"+storeid+"'"+  " AND VERTICAL_ID = 1) AS T", null);

	


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					assetYesPer = dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET"));

					dbcursor.moveToNext();
				}
				dbcursor.close();
				return assetYesPer;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return assetYesPer;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return assetYesPer;

	}
	
	
	public String getAssetYesdataBeverage(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		String assetYesPer = "";
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  CAST(SUM(AVL) AS FLOAT)/CAST(COUNT(AVL) AS FLOAT)*100 AS ASSET  FROM " 
					+"(select DISTINCT ASSETS_ID, CASE WHEN AVAILABLE ='NO' THEN 0 ELSE 1 END AS AVL from DISPLAY_TRANSACTION  where Store_id = '"+storeid+"'"+  " AND VERTICAL_ID = 2) AS T", null);

	


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					assetYesPer = dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET"));

					dbcursor.moveToNext();
				}
				dbcursor.close();
				return assetYesPer;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return assetYesPer;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return assetYesPer;

	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_main(String asset_id,String secondary) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + " and " + CommonString.KEY_SECONDARY +"=" + "'"+secondary+"'"+ ""  , null);

					
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

			
					sb.setImage_Path2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					
					sb.setSecondary(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SECONDARY)));
					
					
					sb.setAssets_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
					
					sb.setImage_Path3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					
					sb.setImage_Path1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					
					
					sb.setVertical_ID(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					
					sb.setVertical_Name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));
					
					sb.setAvailable(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));
					sb.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
					
					sb.setAsset_Pure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
					
					sb.setBrandId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
			/*		
			sb.setAsset_PureDownload(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE_DOWNLOAD)));
			
			
			sb.setStore_id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
			
			sb.setVertical_Name(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
			sb.setAsset_Pure(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));

			
			
	
			
			
			sb.setVertical_ID(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
			
			sb.setVertical_Name(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));
			sb.setAsset_Pure(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

			
			sb.setAssets(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSETS)));
			sb.setAssets_Id(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
			sb.setAvailable(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));
			sb.setRemark(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
			sb.setImage_Path1(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));

				sb.setLenth(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
				sb.setHeight(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
				sb.setBreath(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					sb.setSoid_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));
					sb.setSku_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
					sb.setSku_Name(dbcursor.getString(dbcursor
							
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));
					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
					sb.setStore_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					
					sb.setDom2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM2)));
					sb.setDom3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM3)));*/
					
					list.add(sb);
					break;
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_chillars_StoreId(String storeId) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_STORE_ID + "=" + storeId + "", null);

					
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();
					
					sb.setAuto_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_RANDOM)));
			
					sb.setImage_Path2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					
					sb.setAssets_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
					
					sb.setImage_Path3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					
					sb.setImage_Path1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					
					
					sb.setVertical_ID(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					
					sb.setVertical_Name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));
					
					sb.setAvailable(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABLE)));
					sb.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)));
					
					sb.setAsset_Pure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
					
					
		
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_LBH(long asset_id, String store_id, String sECONDARY_KEY) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			/*dbcursor = db.rawQuery("SELECT LENGTH,HEIGHT,BREATH FROM TABLE_DISPLAY_TRANSACTION"
					, null);*/
			
			
			dbcursor = db.rawQuery("SELECT LENGTH,HEIGHT,BREATH FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_ASSETS_ID + "='" + asset_id + "'" +" and " +CommonString.KEY_STORE_ID + "='" + store_id +"'" +" and " +CommonString.KEY_SECONDARY + "='" + sECONDARY_KEY +"'", null);

			
		
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/

			
		
			
			

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

			
		
				sb.setLenth(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
				sb.setHeight(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
				sb.setBreath(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_LBH(long asset_id,String secondary) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			/*dbcursor = db.rawQuery("SELECT LENGTH,HEIGHT,BREATH FROM TABLE_DISPLAY_TRANSACTION"
					, null);*/
			
			
			dbcursor = db.rawQuery("SELECT LENGTH,HEIGHT,BREATH FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + " and " + CommonString.KEY_SECONDARY +"=" + "'"+secondary+"'"+ ""  , null);

			
		
		/*	dbcursor = db.rawQuery("SELECT  DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					+ CommonString.TABLE_DISPLAY_TRANSACTION + " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + "", null);*/

			
		
			
			

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

			
					/*sb.setSecondary(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SECONDARY)));*/
				sb.setLenth(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LENGTH)));
				sb.setHeight(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_HEIGHT)));
				sb.setBreath(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BREATH)));
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	public ArrayList<FinalDataBeanDisplay> getDisplayTransactionData_SkuData(long asset_id,String secondary) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<FinalDataBeanDisplay> list = new ArrayList<FinalDataBeanDisplay>();
		Cursor dbcursor = null;

		try {
			/*dbcursor = db.rawQuery("SELECT DISTINCT KEY_ASSETS_ID FROM TABLE_DISPLAY_TRANSACTION"
					, null);*/
		
			dbcursor = db.rawQuery("SELECT * FROM DISPLAY_TRANSACTION"+ " where "
					+ CommonString.KEY_ASSETS_ID + "=" + asset_id + " and " + CommonString.KEY_SECONDARY +"=" + "'"+secondary+"'"+ ""  , null);


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FinalDataBeanDisplay sb = new FinalDataBeanDisplay();

			
			
		
			sb.setFaceup(dbcursor.getString(dbcursor
					.getColumnIndexOrThrow(CommonString.KEY_FACEUP)));

			
		
					sb.setSku_Id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
					
					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
				
				
					sb.setDom1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_DOM1)));
					
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	
	

	public void InsertVerticalMaster(VerticalMasterGetterSetter data) {

		db.delete(CommonString.TABLE_VERTICAL_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getVerticalid().size(); i++) {

				values.put(CommonString.KEY_VERTICAL_ID, data.getVerticalid()
						.get(i));
				values.put(CommonString.KEY_VERTICAL_NAME, data
						.getVertical_name().get(i));

				db.insert(CommonString.TABLE_VERTICAL_MASTER, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Theme Master Data ",
					ex.getMessage());
		}

	}

	public void InsertComapnyMasterData(CompanyMasterGetterSetter data) {

		db.delete(CommonString.TABLE_COMPANY_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getCompany().size(); i++) {

				values.put(CommonString.KEY_COMPANY_ID, data.getCompanyid()
						.get(i));
				values.put(CommonString.KEY_COMAPANY_NAME, data.getCompany()
						.get(i));

				values.put(CommonString.KEY_COMPETITOR, data.getCompetitor()
						.get(i));

				values.put(CommonString.KEY_NON_KEYCOMPETITOR, data
						.getNonkeycompetitor().get(i));
				db.insert(CommonString.TABLE_COMPANY_MASTER, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertSkuMasterData(SkuMasterDataGetterSetter data) {

		db.delete(CommonString.TABLE_SKU_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getSku_id().size(); i++) {

				values.put(CommonString.KEY_SKU_ID, data.getSku_id().get(i));
				values.put(CommonString.KEY_SKU_NAME, data.getSku_name().get(i));
				values.put(CommonString.KEY_BRAND_ID, data.getBrand_id().get(i));
				values.put(CommonString.KEY_SKU_SEQUENCE,
						Integer.parseInt(data.getSkuseq().get(i)));

				db.insert(CommonString.TABLE_SKU_MASTER, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertSkuTargetData(SkuTargetGetterSetter data) {

		db.delete(CommonString.TABLE_SKU_TARGET, null, null);
		ContentValues values = new ContentValues();

		try {

			db.beginTransaction();

			for (int i = 0; i < data.getSKU_ID().size(); i++) {

				values.put(CommonString.KEY_SKU_ID, data.getSKU_ID().get(i));
				values.put(CommonString.KEY_STORE_ID, data.getSTORE_ID().get(i));
				values.put(CommonString.KEY_POSM_ID, data.getPosm_id().get(i));

				db.insert(CommonString.TABLE_SKU_TARGET, null, values);
			}
			db.setTransactionSuccessful();
			db.endTransaction();

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertAssetData(AssetsGetterSetter data) {

		db.delete(CommonString.TABLE_ASSET_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getASSET_ID().size(); i++) {

				values.put(CommonString.KEY_ASSETS, data.getASSET().get(i));
				values.put(CommonString.KEY_ASSETS_ID, data.getASSET_ID()
						.get(i));

				db.insert(CommonString.TABLE_ASSET_MASTER, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	
	
	
	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		/*String intime = m_cal.get(Calendar.HOUR_OF_DAY)
				+ m_cal.get(Calendar.MINUTE) + "_" + 
*/
		return null;

	}
	public void InsertAssetTarget(AssetsTargetGetterSetter data) {

		db.delete(CommonString.TABLE_ASSET_TARGET, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getSTORE_ID().size(); i++) {

				
				Random next = new Random();
				Calendar m_cal = Calendar.getInstance();
				
				String intSecondary = String.valueOf(next.nextInt(100000000)+m_cal.get(Calendar.SECOND));
				
				
				
				
				
				
				// values.put(CommonString.KEY_SKU_ID, data.getSKU_ID().get(i));
				values.put(CommonString.KEY_STORE_ID, data.getSTORE_ID().get(i));
				values.put(CommonString.KEY_ASSETS_ID, data.getASSET_ID()
						.get(i));
				values.put(CommonString.KEY_TARGET, data.getTARGETQTY().get(i));
				values.put(CommonString.KEY_VERTICAL_ID, data.getVerticalid()
						.get(i));
				values.put(CommonString.KEY_ASSET_PURE, data.getASSET_PURE()
						.get(i));
				
				values.put(CommonString.KEY_BRAND_ID, data.getBRAND_ID()
						.get(i));
				values.put(CommonString.KEY_BRAND_NAME, data.getBRAND_NAME()
						.get(i));
				
				values.put(CommonString.KEY_RANDOM, "");
				values.put(CommonString.KEY_SECONDPRIMARY, intSecondary);
				
				
				
				db.insert(CommonString.TABLE_ASSET_TARGET, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// Non Sku
	public void InsertNonSKUReason(NonSkuGetterSetter data) {

		db.delete(CommonString.TABLE_NON_SKU_REASON, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStockreasonid().size(); i++) {

				values.put("STOCK_REASON", data.getStockreason().get(i));
				values.put("STOCK_REASON_ID", data.getStockreasonid().get(i));

				db.insert(CommonString.TABLE_NON_SKU_REASON, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// Get Non SKU Details
	public ArrayList<NonSkuBean> getNonSkuReasonData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<NonSkuBean> list = new ArrayList<NonSkuBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_NON_SKU_REASON, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					NonSkuBean sb = new NonSkuBean();

					sb.setSkureaonid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STOCK_REASON_ID")));

					sb.setSkureason(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STOCK_REASON")));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// Non Working Reason
	public void InsertSubNonWorkingData(NonWorkingGetterSetter data) {

		db.delete(CommonString.TABLE_SUBNON_WORKING_REASON, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getReason_id().size(); i++) {

				values.put(CommonString.KEY_REASON, data.getReason().get(i));
				values.put(CommonString.KEY_REASON_ID,
						data.getReason_id().get(i));
				values.put(CommonString.KEY_IMAGE, data.getImage().get(i));
				values.put(CommonString.KEY_ENTRY, data.getEntry().get(i));
				values.put(CommonString.KEY_MREASON_ID, data.getMreason_id()
						.get(i));
				values.put(CommonString.KEY_INFORMED_TO, data.getInformedto().get(i));
				values.put(CommonString.KEY_STORE_INFO, data.getStoreinfo()
						.get(i));
				values.put(CommonString.KEY_other, data.getOther()
						.get(i));

				db.insert(CommonString.TABLE_SUBNON_WORKING_REASON, null,
						values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// Non Working Reason
	public void InsertNonWorkingData(NonWorkingGetterSetter data) {

		db.delete(CommonString.TABLE_NON_WORKING_REASON, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getMreason_id().size(); i++) {

				values.put(CommonString.KEY_MREASON, data.getMreason().get(i));
				values.put(CommonString.KEY_MREASON_ID, data.getMreason_id()
						.get(i));

				db.insert(CommonString.TABLE_NON_WORKING_REASON, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertKEYAccData(NonWorkingGetterSetter data) {

		db.delete(CommonString.TABLE_KEY_ACCOUNT, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getKeyid().size(); i++) {

				values.put(CommonString.KEY_ACC_ID, data.getKeyid().get(i));
				values.put(CommonString.KEY_ACCOUNT, data.getKeyacunt().get(i));

				db.insert(CommonString.TABLE_KEY_ACCOUNT, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// promotion
	public void InsertPromotionData(PromotionGetterSetter data) {

		db.delete(CommonString.TABLE_PROMOTION_PEPSI, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getPROMO().size(); i++) {

				values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
				values.put(CommonString.KEY_PROMO, data.getPROMO().get(i));
				values.put(CommonString.KEY_PROMO_ID, data.getPROMO_ID().get(i));
				values.put(CommonString.KEY_PROMO_TYPE, data.getPROMO_TYPE()
						.get(i));
				values.put(CommonString.KEY_PROMO_TYPE_ID, data
						.getPROMOTYPE_ID().get(i));

				values.put(CommonString.KEY_VERTICAL_ID, data
						.getVertical_id().get(i));
				db.insert(CommonString.TABLE_PROMOTION_PEPSI, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertSkuFocusData(final SkufocusGetterSetter data) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				db.delete(CommonString.TABLE_SKU_FOCUS, null, null);

				ContentValues values = new ContentValues();

				try {
					db.beginTransaction();

					for (int i = 0; i < data.getSTORE_ID().size(); i++) {

						values.put(CommonString.KEY_STORE_ID, data
								.getSTORE_ID().get(i));
						values.put(CommonString.KEY_SKU_ID, data.getSKU_ID()
								.get(i));
						values.put(CommonString.KEY_FOCUS,
								data.getFOCUS().get(i));

						db.insert(CommonString.TABLE_SKU_FOCUS, null, values);

					}
					db.setTransactionSuccessful();
					db.endTransaction();

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.getMessage());
				}

			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void InsertCompetitorData(CompetitorGetterSetter data) {

		db.delete(CommonString.TABLE_COMPETITOR_MAPPING, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getBrandid().size(); i++) {

				values.put(CommonString.KEY_BRAND_ID, data.getBrandid().get(i));
				values.put(CommonString.KEY_COMPETITOR_ID, data
						.getCompetitor_brandid().get(i));

				db.insert(CommonString.TABLE_COMPETITOR_MAPPING, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void InsertBrandMappingData(VerticalBrandMappingGetterSetter data) {

		db.delete(CommonString.TABLE_BRAND_MAPPING, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getVerticalid().size(); i++) {

				values.put(CommonString.KEY_VERTICAL_ID, data.getVerticalid()
						.get(i));
				values.put(CommonString.KEY_BRAND_ID, data.getBrandid().get(i));
				values.put(CommonString.KEY_BRAND_NAME, data.getBrandname()
						.get(i));

				db.insert(CommonString.TABLE_BRAND_MAPPING, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Theme mapping Data ",
					ex.getMessage());
		}

	}

	// category master
	public void InsertCategoryMaster(CategoryMasterGetterSetter data) {

		db.delete(CommonString.TABLE_CATEGORY_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getCategory_id().size(); i++) {

				values.put(CommonString.KEY_CATEGORY_ID, data.getCategory_id()
						.get(i));
				values.put(CommonString.KEY_CATEGORY_NAME, data
						.getCategory_name().get(i));
				values.put(CommonString.KEY_CATEGORY_CODE, data
						.getCategory_name().get(i));
				values.put(CommonString.KEY_CATEGORY_SEQ,
						Integer.parseInt(data.getCategory_seq().get(i)));

				db.insert(CommonString.TABLE_CATEGORY_MASTER, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Theme mapping Data ",
					ex.getMessage());
		}

	}

	// category mapping
	public void InsertCategoryMapping(CategoryMappingGetterSetter data) {

		db.delete(CommonString.TABLE_CATEGORY_MAPPING, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getCategory_id().size(); i++) {

				values.put(CommonString.KEY_CATEGORY_ID, data.getCategory_id()
						.get(i));
				values.put(CommonString.KEY_SKU_ID, data.getSku_id().get(i));
				values.put(CommonString.KEY_SKU_NAME, data.getSku_name().get(i));

				db.insert(CommonString.TABLE_CATEGORY_MAPPING, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Theme mapping Data ",
					ex.getMessage());
		}

	}

	// category vertical mapping
	public void InsertCategoryVerticalMapping(
			CategoryVerticalMappingGetterSetter data) {

		db.delete(CommonString.TABLE_CATEGORY_VERTICALMAPPING, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getCategory_id().size(); i++) {

				values.put(CommonString.KEY_CATEGORY_ID, data.getCategory_id()
						.get(i));
				values.put(CommonString.KEY_VERTICAL_ID, data.getVertical_id()
						.get(i));
				values.put(CommonString.KEY_CATEGORY_NAME, data
						.getCategory_name().get(i));

				db.insert(CommonString.TABLE_CATEGORY_VERTICALMAPPING, null,
						values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Theme mapping Data ",
					ex.getMessage());
		}

	}

	/*
	 * public void InsertSkuMappingData(SkuBrandMappingGetterSetter data) {
	 * 
	 * db.delete(CommonString.TABLE_SKU_MAPPING, null, null); ContentValues
	 * values = new ContentValues();
	 * 
	 * try {
	 * 
	 * for (int i = 0; i < data.getSkuid().size(); i++) {
	 * 
	 * values.put(CommonString.KEY_SKU_ID, data.getSkuid().get(i));
	 * values.put(CommonString.KEY_BRAND_ID, data.getBrandid().get(i));
	 * values.put(CommonString.KEY_SKU_NAME, data.getSkuname().get(i));
	 * 
	 * db.insert(CommonString.TABLE_SKU_MAPPING, null, values); }
	 * 
	 * } catch (Exception ex) {
	 * Log.d("Database Exception while Insert POSM THEME Data ",
	 * ex.getMessage()); }
	 * 
	 * }
	 */

	// posm
	public void InsertPosmData(PosmMasterGetterSetter data) {

		db.delete(CommonString.TABLE_POSM_MASTER, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getPosmid().size(); i++) {

				values.put(CommonString.KEY_POSM_ID, data.getPosmid().get(i));

				values.put(CommonString.KEY_POSM, data.getPosmname().get(i));

				db.insert(CommonString.TABLE_POSM_MASTER, null, values);
			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert POSM THEME Data ",
					ex.getMessage());
		}

	}

	public ArrayList<StoreBean> getStoreData(String date) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_STORE_DETAIL + " where "
					+ CommonString.KEY_VISIT_DATE + "='" + date + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					StoreBean sb = new StoreBean();
					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setStorename((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME))));
					sb.setStoreaddress(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ADDRESS)));
					sb.setVisitdate((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))));
					sb.setLatitude((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
					sb.setLongitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));

					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STATUS)));
					sb.setCheckout_status(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CHECKOUT_STATUS)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get vertical Master data
	public ArrayList<VericalBrandBean> getVerticalLevelMasterData() {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_VERTICAL_MASTER, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();
					sb.setVerticalid((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID))));
					sb.setVertical((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	// get vertical Master data
	public ArrayList<CityBean> getCompanyMaster() {

		ArrayList<CityBean> list = new ArrayList<CityBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COMPANY_MASTER, null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CityBean sb = new CityBean();
					sb.setCompany((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME))));
					sb.setCompanyid((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	// get vertical Master data
	public ArrayList<CityBean> getCompanyMasterExceptPepsi() {

		ArrayList<CityBean> list = new ArrayList<CityBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COMPANY_MASTER + " WHERE "
					+ CommonString.KEY_COMPETITOR + "= 'True' OR "
					+ CommonString.KEY_NON_KEYCOMPETITOR + " = 'True'", null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CityBean sb = new CityBean();
					sb.setCompany((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMAPANY_NAME))));
					sb.setCompanyid((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	// getsku competitor data
	public ArrayList<VericalBrandBean> getSodSkuCompetitor() {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SKU_NAME  FROM SKU_MASTER SK INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE CO.COMPETITOR ='True'",
							null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();
					sb.setSkuid((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID))));
					sb.setSkuname((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	// getsku other data
	public ArrayList<VericalBrandBean> getSodSkuOther() {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SKU_NAME  FROM SKU_MASTER SK INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE CO.COMPETITOR ='False' AND CO.NONKEYCOMPETITOR ='True'",
							null);

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean sb = new VericalBrandBean();
					sb.setSkuid((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID))));
					sb.setSkuname((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME))));
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	// get brand Master data
	public ArrayList<VericalBrandBean> getBrandMappingData(String id) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_BRAND_MAPPING + " where "
					+ CommonString.KEY_VERTICAL_ID + "='" + id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get brand id
	public ArrayList<VericalBrandBean> getBrandList(String id, String COMPANY) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_BRAND_CHECK + " where "
					+ CommonString.KEY_STORE_ID + "='" + id + "' AND "
					+ CommonString.KEY_COMAPANY_NAME + " = '" + COMPANY + "'",
					null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get brand basis of cmpnny
	public ArrayList<VericalBrandBean> getBrandCompanyList(String id) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_BRAND_MASTER + " where "
					+ CommonString.KEY_COMPANY_ID + "='" + id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get brand JOIN data
	public ArrayList<VericalBrandBean> getBrandJoinData(String storeid,
			String category) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;
		/*
		 * + "WHERE MP.STORE_ID = '" + storeid + "'" + "AND CA.CATEGORY_ID = '"
		 * + category + "'" + "AND CO.COMPETITOR='False'" +
		 * "ORDER BY BR.BRSEQUENCE",
		 */
		try {
			dbcursor = db
					.rawQuery(
							"SELECT DISTINCT BR.BRAND_ID,BR.BRAND_NAME FROM SKU_FOCUS MP INNER JOIN SKU_MASTER SK ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN CATEGORY_MASTER CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE MP.STORE_ID = '"
									+ storeid
									+ "'"
									+ " AND CA.CATEGORY_ID = '"
									+ category
									+ "'"
									+ "AND CO.COMPETITOR='False'"
									+ "ORDER BY BR.BRSEQUENCE", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}
	
	
	// total food data
	
	public ArrayList<VericalBrandBean> getBrandForFood(String storeid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;
		/*
		 * + "WHERE MP.STORE_ID = '" + storeid + "'" + "AND CA.CATEGORY_ID = '"
		 * + category + "'" + "AND CO.COMPETITOR='False'" +
		 * "ORDER BY BR.BRSEQUENCE",
		 */
		try {
			dbcursor = db
					.rawQuery(
							"SELECT DISTINCT BR.BRAND_ID,BR.BRAND_NAME FROM SKU_FOCUS MP INNER JOIN SKU_MASTER SK ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN CATEGORY_MASTER CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE MP.STORE_ID = '"
									+ storeid
									+ "'"
									+ " AND CA.CATEGORY_ID IN (4,21,22)"
									
									
									+ " AND CO.COMPETITOR='False'"
									+ "ORDER BY BR.BRSEQUENCE", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}
	
	
	// get total beverage brandid
	
	public ArrayList<VericalBrandBean> getBrandForBeverage(String storeid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;
		/*
		 * + "WHERE MP.STORE_ID = '" + storeid + "'" + "AND CA.CATEGORY_ID = '"
		 * + category + "'" + "AND CO.COMPETITOR='False'" +
		 * "ORDER BY BR.BRSEQUENCE",
		 */
		try {
			dbcursor = db
					.rawQuery(
							"SELECT DISTINCT BR.BRAND_ID,BR.BRAND_NAME FROM SKU_FOCUS MP INNER JOIN SKU_MASTER SK ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN CATEGORY_MASTER CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE MP.STORE_ID = '"
									+ storeid
									+ "'"
									+ " AND CA.CATEGORY_ID IN (5,13,15,16,17,19)"
									
									
									+ " AND CO.COMPETITOR='False'"
									+ "ORDER BY BR.BRSEQUENCE", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get brand JOIN data
	public ArrayList<VericalBrandBean> getSku(String category, String company,
			String storeid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_NAME,SK.SKU_ID FROM SKU_MASTER SK INNER JOIN BRAND_MASTER BM ON SK.BRAND_ID= BM.BRAND_ID INNER JOIN SKU_FOCUS SF ON SF.SKU_ID= SK.SKU_ID WHERE  BM.COMPANY_ID = '"
									+ company
									+ "' AND BM.CATEGORY_ID = '"
									+ category
									+ "' AND SF.STORE_ID = '"
									+ storeid + "'", null);
			
			/*"SELECT SK.SKU_NAME,SK.SKU_ID FROM SKU_MASTER SK INNER JOIN BRAND_MASTER BM ON SK.BRAND_ID= BM.BRAND_ID INNER JOIN SKU_FOCUS SF ON SF.SKU_ID= SK.SKU_ID WHERE  BM.COMPANY_ID = '"
			+ company
			+ "' AND BM.CATEGORY_ID = '"
			+ category
			+ "' AND SF.STORE_ID = '"
			+ storeid + "'", null);*/
			
			

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get category mapping data
	public ArrayList<CategoryBean> getCategoryVerticalMapping(String id) {

		ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_CATEGORY_VERTICALMAPPING + " where "
					+ CommonString.KEY_VERTICAL_ID + "='" + id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					CategoryBean sb = new CategoryBean();
					sb.setCategoryid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));

					sb.setCategoryname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_NAME)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	public ArrayList<CategoryBean> getCategoryVerticalMapping() {

		ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT VERTICAL_NAME,CH.VERTICAL_ID,COUNT(DISTINCT CATEGORY_ID)  from  CATEGORY_VERTICALMAPPING CH  inner join VERTICAL_MASTER V on V.VERTICAL_ID = CH.VERTICAL_ID GROUP By VERTICAL_NAME,CH.VERTICAL_ID",
							null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					CategoryBean sb = new CategoryBean();

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));
					sb.setSkuqty(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COUNT(DISTINCT CATEGORY_ID)")));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get
	// get category sku mapping data
	public ArrayList<CategoryBean> getCategorySkuMapping(String id) {

		ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_CATEGORY_MAPPING + " where "
					+ CommonString.KEY_CATEGORY_ID + "='" + id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					CategoryBean sb = new CategoryBean();
					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setCategoryid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get sku mapping data
	public ArrayList<VericalBrandBean> getSkuMappingData(String brandid,
			String store_id) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP  ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID  WHERE BR.BRAND_ID"
									+ "='"
									+ brandid
									+ "'"
									+ " AND MP.STORE_ID"
									+ "='" + store_id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}
	
	
	//GET SKU_fOCUS DATA
	
	

	public int getSkuFocusData_forCounting
			(String store_id) {
       int count = 0;
	
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SUM(SKU_CNT) AS SKU_COUNT FROM ( SELECT COUNT(DISTINCT sm.SKU_ID ) AS SKU_CNT FROM SKU_FOCUS SF INNER JOIN SKU_MASTER SM ON SF.SKU_ID=SM.SKU_ID INNER JOIN BRAND_MASTER BR ON BR.BRAND_ID = SM.BRAND_ID INNER JOIN COMPANY_MASTER CT ON CT.COMPANY_ID = BR.COMPANY_ID WHERE STORE_ID='"+store_id+"'"+"  AND CT.COMPANY_ID=1 UNION ALL SELECT COUNT(DISTINCT S.SKU_ID) AS SKU_CNT FROM SKU_FOCUS SK INNER JOIN SKU_MASTER S ON SK.SKU_ID = S.SKU_ID WHERE BRAND_ID IN ( SELECT DISTINCT CC.COMPETITOR_BRANDID FROM SKU_FOCUS SF INNER JOIN SKU_MASTER SM ON SF.SKU_ID=SM.SKU_ID INNER JOIN BRAND_MASTER BR ON BR.BRAND_ID = SM.BRAND_ID INNER JOIN COMPANY_MASTER CT ON CT.COMPANY_ID = BR.COMPANY_ID INNER JOIN COMPETITOR_MAPPING CC ON CC.BRAND_ID = BR.BRAND_ID WHERE STORE_ID='"+store_id+"'"+"  AND CT.COMPANY_ID=1 ) AND STORE_ID='"+store_id+"'"+") AS T1", null);
			dbcursor.moveToPosition(0);
			count =	Integer.parseInt(dbcursor.getString(0));

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return count;
	}
	
	
	
	
	
	
	

	
	
	public ArrayList<VericalBrandBean> getSkuMappingData121() {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP  ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}
	
	
	
	
	
	public ArrayList<VericalBrandBean> getSkuMappingData121_forbevrages(String store_id) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID  INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID WHERE MP.STORE_ID = "+store_id+" AND VM.VERTICAL_ID = 2 AND BR.COMPANY_ID = 1", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	
	
	public ArrayList<VericalBrandBean> getSkuMappingData121_forFood(String store_id) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
//			dbcursor = db
//					.rawQuery(
//							"SELECT SM.SKU_ID, SM.SKU_NAME FROM SKU_MASTER SM INNER JOIN BRAND_MASTER BM ON BM.BRAND_ID = SM.BRAND_ID INNER JOIN CATEGORY_VERTICALMAPPING CVM ON CVM.CATEGORY_ID = BM.CATEGORY_ID INNER JOIN VERTICAL_MASTER VM ON  VM.VERTICAL_ID = CVM.VERTICAL_ID WHERE VM.VERTICAL_ID =1  AND BM.COMPANY_ID=1 ORDER BY SM.BRAND_ID", null);

			dbcursor = db
					.rawQuery("SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID  INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID WHERE MP.STORE_ID = "+store_id+ "  AND VM.VERTICAL_ID = 1 AND BR.COMPANY_ID = 1"
							, null);
			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}
	
	
	// get sod sku mapping data
	public ArrayList<PosmBean> getSodSkuMappingData(String brandid,
			String store_id) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME, PO.POSM_ID, PO.POSM_NAME FROM SOD_TARGET TG INNER JOIN SKU_MASTER SK ON TG.SKU_ID = SK.SKU_ID INNER JOIN POSM_MASTER PO ON TG.POSM_ID = PO.POSM_ID WHERE TG.STORE_ID = '"
									+ store_id
									+ "' AND SK.BRAND_ID = '"
									+ brandid + "' ORDER BY SK.SKUSEQUENCE",
							null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					PosmBean sb = new PosmBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get ASSEST SKU
	public ArrayList<PosmBean> getAssetsSku(String brandid, String store_id) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME, AM.ASSETS_ID, AM.ASSET FROM ASSEST_TARGET TG INNER JOIN SKU_MASTER SK ON TG.SKU_ID = SK.SKU_ID INNER JOIN ASSET_MASTER AM ON TG.ASSETS_ID=  AM.ASSETS_ID WHERE TG.STORE_ID ='"
									+ store_id
									+ "' AND SK.BRAND_ID = '"
									+ brandid + "' ORDER BY SK.SKUSEQUENCE",
							null);
			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					PosmBean sb = new PosmBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					sb.setAssetid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));

					sb.setAssest(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get ASSEST SKU
	public ArrayList<PosmBean> getAssets(String store_id) {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					
					.rawQuery(
							"SELECT AT.ASSETS_ID,AM.ASSET,VM.VERTICAL_NAME,AT.ASSET_PURE,AT.VERTICAL_ID,AT.BRAND_NAME,AT.BRAND_ID,AT.RANDOM,AT.SECONDPRIMARY FROM ASSEST_TARGET AT INNER JOIN ASSET_MASTER AM ON AT.ASSETS_ID = AM.ASSETS_ID INNER JOIN VERTICAL_MASTER VM ON VM.VERTICAL_ID = AT.VERTICAL_ID WHERE STORE_ID = '"
									+ store_id + "'", null);
			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					PosmBean sb = new PosmBean();

					sb.setAssetid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS_ID)));
					
					sb.setDownlodassetpure(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSET_PURE)));
					
					sb.setAssest(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ASSETS)));

					sb.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_NAME)));

					sb.setVerticalid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					
					
					
					sb.setBrand_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

					sb.setBrand_name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_NAME)));
					
					sb.setAutoId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_RANDOM)));
					sb.setSECONDARYKEY(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SECONDPRIMARY)));
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get sku COMPETIOTOR
	public ArrayList<VericalBrandBean> getSkuCompetitorData(String brandid,
			String storeid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP  ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN COMPETITOR_MAPPING CM ON BR.BRAND_ID = CM.COMPETITOR_BRANDID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE CM.BRAND_ID"
									+ " ='"
									+ brandid
									+ "'"
									+ " AND CO.COMPETITOR='True' AND CO.NONKEYCOMPETITOR= 'False' AND MP.STORE_ID"
									+ " ='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}
	
	
	

	// get sku other
	public ArrayList<VericalBrandBean> getSkuOtherCompetitorData(
			String brandid, String storeid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			/*
			 * dbcursor = db .rawQuery(
			 * "SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP  ON SK.SKU_ID = MP.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID INNER JOIN COMPETITOR_MAPPING CM ON BR.BRAND_ID = CM.COMPETITOR_BRANDID INNER JOIN COMPANY_MASTER CO ON BR.COMPANY_ID = CO.COMPANY_ID WHERE CM.BRAND_ID"
			 * + " ='" + brandid + "'" +
			 * " AND CO.COMPETITOR='False' AND CO.NONKEYCOMPETITOR= 'True' AND MP.STORE_ID"
			 * + " ='" + storeid + "'", null);
			 */

			dbcursor = db
					.rawQuery(
							"SELECT SK.SKU_ID, SK.SKU_NAME FROM SKU_MASTER SK INNER JOIN  SKU_FOCUS MP  ON SK.SKU_ID = MP.SKU_ID WHERE BRAND_ID ='"
									+ brandid
									+ "' AND STORE_ID"
									+ " ='"
									+ storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					VericalBrandBean sb = new VericalBrandBean();

					sb.setSkuname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_NAME)));

					sb.setSkuid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	// get POSM mapping data
	public ArrayList<PosmBean> getPosmMasterdata() {

		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_POSM_MASTER, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {

					PosmBean sb = new PosmBean();

					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					sb.setPosmid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();

			}
		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;
	}

	public long InsertCoverageData(CoverageBean data) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_STORE_ID, data.getStoreId());
			values.put(CommonString.KEY_USER_ID, data.getUserId());
			values.put(CommonString.KEY_IN_TIME, data.getInTime());
			values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
			values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
			values.put(CommonString.KEY_LATITUDE, data.getLatitude());
			values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
			values.put(CommonString.KEY_REASON_ID, data.getReasonid());
			values.put(CommonString.KEY_REASON, data.getReason());
			values.put(CommonString.KEY_STATUS, data.getStatus());
			values.put(CommonString.KEY_IMAGE, data.getImage());
			values.put(CommonString.KEY_REMARKS, data.getRemarks());
			values.put(CommonString.KEY_STORE_INFO, data.getStoreinfo());
			values.put(CommonString.KEY_ACC_ID, data.getKeycontactId());
			values.put(CommonString.KEY_other, data.getOtherreson());

			return db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.getMessage());
		}
		return 0;
	}

	public boolean update_CoverageTable(String time, String id) {
		ContentValues values = new ContentValues();

		values.put(CommonString.KEY_OUT_TIME, time);

		return db.update(CommonString.TABLE_COVERAGE_DATA, values,
				CommonString.KEY_STORE_ID + " =" + id, null) > 0;

	}
	
	
	//update asset target
	public boolean update_assetTarget(String randomNumber, String assetid, String secondarykey) {
		ContentValues values = new ContentValues();

		values.put(CommonString.KEY_RANDOM, randomNumber);

		return db.update(CommonString.TABLE_ASSET_TARGET, values,
				CommonString.KEY_ASSETS_ID + " =" + assetid + " AND SECONDPRIMARY ="+ secondarykey, null) > 0;

	}
	
	

	// getCoverageData
	public ArrayList<CoverageBean> getCoverageData(String visitdate,
			String store_id) {

		ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
		Cursor dbcursor = null;

		try {

			if (visitdate == null) {

				dbcursor = db.rawQuery("SELECT  * from "
						+ CommonString.TABLE_COVERAGE_DATA, null);

			} else if (store_id == null) {
				dbcursor = db.rawQuery("SELECT  * from "
						+ CommonString.TABLE_COVERAGE_DATA + " where "
						+ CommonString.KEY_VISIT_DATE + "='" + visitdate + "'",
						null);
			} else {
				dbcursor = db.rawQuery("SELECT  * from "
						+ CommonString.TABLE_COVERAGE_DATA + " where "
						+ CommonString.KEY_VISIT_DATE + "='" + visitdate
						+ "' AND " + CommonString.KEY_STORE_ID + "='"
						+ store_id + "'", null);
			}

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CoverageBean sb = new CoverageBean();
					sb.setMID((dbcursor.getInt(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID))));
					sb.setStoreId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setUserId((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
					sb.setInTime(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
					sb.setOutTime(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
					sb.setVisitDate((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
					sb.setLatitude(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
					sb.setLongitude(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
					sb.setReasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
					sb.setReason(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REASON)))));
					sb.setStatus((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));

					sb.setImage((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE))))));

					sb.setStoreinfo((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_INFO))))));

					sb.setKeycontactId((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ACC_ID))))));

					sb.setRemarks(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARKS)))));
					
					sb.setOtherreson(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_other)))));
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	// getCoverageData
	public void deleteCoverageData(int mid) {

		try {
			db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_ID
					+ "=" + mid, null);

		} catch (Exception e) {

		}
	}

	// Update store status
	public void updateStoreStatus(String storeid, String visitdate) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, "U");

			db.update(CommonString.TABLE_STORE_DETAIL, values,
					CommonString.KEY_STORE_ID + "='" + storeid + "' AND "
							+ CommonString.KEY_VISIT_DATE + "='" + visitdate
							+ "'", null);
		} catch (Exception e) {

		}
	}

	public void updateStoreStatusOnLeave(String storeid, String visitdate,
			String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, status);

			db.update(CommonString.TABLE_STORE_DETAIL, values,
					CommonString.KEY_STORE_ID + "='" + storeid + "' AND "
							+ CommonString.KEY_VISIT_DATE + "='" + visitdate
							+ "'", null);
		} catch (Exception e) {

		}
	}

	public void updateStoreStatusOnCheckout(String storeid, String visitdate,
			String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_CHECKOUT_STATUS, status);

			db.update(CommonString.TABLE_STORE_DETAIL, values,
					CommonString.KEY_STORE_ID + "='" + storeid + "' AND "
							+ CommonString.KEY_VISIT_DATE + "='" + visitdate
							+ "'", null);
		} catch (Exception e) {

		}
	}

	public int CheckMid(String currdate, String storeid) {

		Cursor dbcursor = null;
		int mid = 0;
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
					+ CommonString.KEY_VISIT_DATE + " = '" + currdate
					+ "' AND " + CommonString.KEY_STORE_ID + " ='" + storeid
					+ "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				mid = dbcursor.getInt(dbcursor
						.getColumnIndexOrThrow(CommonString.KEY_ID));

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return mid;
	}

	public String CheckMidWithStatus(String currdate, String storeid) {

		Cursor dbcursor = null;
		String status = "";
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
					+ CommonString.KEY_VISIT_DATE + " = '" + currdate
					+ "' AND " + CommonString.KEY_STORE_ID + " ='" + storeid
					+ "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				status = dbcursor.getString(dbcursor
						.getColumnIndexOrThrow(CommonString.KEY_STATUS));

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return status;
	}

	public int CheckBrandid(String brandid, String storeid, String type) {

		int val = 0;
		Cursor dbcursor = null;
		try {
			dbcursor = db
					.rawQuery("SELECT  * from "
							+ CommonString.TABLE_SOS_BRAND_CHECK + "  WHERE "
							+ CommonString.KEY_BRAND_ID + " = '" + brandid
							+ "' AND " + CommonString.KEY_STORE_ID + " ='"
							+ storeid + "' AND "
							+ CommonString.KEY_COMAPANY_NAME + " ='" + type
							+ "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				val = dbcursor.getCount();

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return val;
	}

	public int CheckBranddata(String brandid, String storeid) {

		int val = 0;
		Cursor dbcursor = null;
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_BRAND_CHECK + "  WHERE "
					+ CommonString.KEY_BRAND_ID + " = '" + brandid + "' AND "
					+ CommonString.KEY_STORE_ID + " ='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				val = dbcursor.getCount();

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return val;
	}

	public int getBrands(String storeid) {

		int val = 0;
		Cursor dbcursor = null;
		try {
			dbcursor = db
					.rawQuery(
							"SELECT DISTINCT SF.STORE_ID , B.BRAND_ID,BRAND_NAME FROM BRAND_MASTER AS B INNER JOIN SKU_MASTER AS S ON B.BRAND_ID =S.BRAND_ID INNER JOIN SKU_FOCUS AS SF ON SF.SKU_ID = S.SKU_ID WHERE SF.STORE_ID ='"
							//"SELECT DISTINCT SF.STORE_ID , B.BRAND_ID,BRAND_NAME FROM BRAND_MASTER AS B INNER JOIN SKU_MASTER AS S ON B.BRAND_ID =S.BRAND_ID INNER JOIN SKU_FOCUS AS SF ON SF.SKU_ID = S.SKU_ID WHERE B.COMPANY_ID = 1 AND SF.STORE_ID ='"
									+ storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				val = dbcursor.getCount();
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return val;
	}

	
	
	public int getBrandswiTHsku(String storeid) {

		int val = 0;
		Cursor dbcursor = null;
		try {
			dbcursor = db
					.rawQuery(
							"SELECT * FROM SKU_FOCUS F INNER JOIN SKU_MASTER M ON F.SKU_ID= M.SKU_ID WHERE STORE_ID ='"
							//"SELECT DISTINCT SF.STORE_ID , B.BRAND_ID,BRAND_NAME FROM BRAND_MASTER AS B INNER JOIN SKU_MASTER AS S ON B.BRAND_ID =S.BRAND_ID INNER JOIN SKU_FOCUS AS SF ON SF.SKU_ID = S.SKU_ID WHERE B.COMPANY_ID = 1 AND SF.STORE_ID ='"
									+ storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				val = dbcursor.getCount();
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return val;
	}
	
	
	
	public int getInsertedBrands(String storeid) {

		int val = 0;
		Cursor dbcursor = null;
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_BRAND_CHECK + "  WHERE "
					
					+ CommonString.KEY_STORE_ID + " ='" + storeid + "'", null);
			
			
			/*dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_BRAND_CHECK + "  WHERE "
					+ CommonString.KEY_COMAPANY_NAME + " = '"
					+ CommonString.KEY_pepsi + "' AND "
					+ CommonString.KEY_STORE_ID + " ='" + storeid + "'", null);
*/			
			

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				val = dbcursor.getCount();
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return val;
	}

	// geotag

	public void InsertGeoTagCityData(GeoTagCityGetterSetter data) {

		db.delete(CommonString.TABLE_GEOTAG_CITY, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getCity().size(); i++) {

				values.put("CITY_ID", data.getCityid().get(i));
				values.put("CITY", data.getCity().get(i));

				db.insert(CommonString.TABLE_GEOTAG_CITY, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public void InsertGeoTagData(GeoTagGetterSetter data) {

		db.delete(CommonString.TABLE_GEO_TAG_MAPPING, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStoreid().size(); i++) {

				values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
				values.put(CommonString.KEY_STORE_NAME, data.getStorename()
						.get(i));
				values.put(CommonString.KEY_ADDRESS, data.getAddress().get(i));
				values.put("CITY_ID", data.getCityid().get(i));

				values.put("STORE_TYPE_ID", data.getStoretype().get(i));

				values.put(CommonString.KEY_LATITUDE, data.getLatitude().get(i));
				values.put(CommonString.KEY_LONGITUDE,
						data.getLongitude().get(i));
				values.put(CommonString.KEY_STATUS, data.getStatus().get(i));

				db.insert(CommonString.TABLE_GEO_TAG_MAPPING, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public ArrayList<CityBean> getCityDetails() {

		ArrayList<CityBean> list = new ArrayList<CityBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_GEOTAG_CITY, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CityBean pb = new CityBean();
					pb.setCityId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CITY_ID")));
					pb.setCityName(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CITY")));

					list.add(pb);
					dbcursor.moveToNext();

				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}
		return list;
	}

	public ArrayList<Storenamebean> getGeoTagStore(String id) {

		ArrayList<Storenamebean> list = new ArrayList<Storenamebean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_GEO_TAG_MAPPING + " where "
					+ "CITY_ID" + "='" + id + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					Storenamebean pb = new Storenamebean();
					pb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					pb.setStorename(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME)));

					pb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STATUS)));
					pb.setStoretypeid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_TYPE_ID")));

					pb.setAddress(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ADDRESS)));

					pb.setLatitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
					pb.setLongitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));

					list.add(pb);
					dbcursor.moveToNext();

				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}
		return list;
	}

	public ArrayList<VericalBrandBean> getBrandStatus(String brandid, int mid) {

		ArrayList<VericalBrandBean> list = new ArrayList<VericalBrandBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SOS_BRAND_CHECK + " where "
					+ CommonString.KEY_BRAND_ID + "='" + brandid + "'"
					+ " AND " + CommonString.MID + " ='" + mid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					VericalBrandBean pb = new VericalBrandBean();
					pb.setBrandid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
					pb.setMid(dbcursor.getInt(dbcursor
							.getColumnIndexOrThrow(CommonString.MID)));

					list.add(pb);
					dbcursor.moveToNext();

				}
				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}
		return list;
	}

	public void updateGeoTagStatus(String id, String status, double lat,
			double longtitude) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_STATUS, status);
			values.put(CommonString.KEY_LATITUDE, Double.toString(lat));
			values.put(CommonString.KEY_LONGITUDE, Double.toString(longtitude));

			db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
					CommonString.KEY_STORE_ID + "='" + id + "'", null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Earned Toggle Data ",
					ex.getMessage());
		}

	}

	public void InsertStoregeotagging(String storeid, double lat,
			double longitude, String path, String path_inside,
			String path_inside1, String status) {

		db.delete(CommonString.TABLE_INSERT_GEO_TAG, CommonString.KEY_STORE_ID+" ='"+storeid+"'", null);
		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_STORE_ID, storeid);
			values.put(CommonString.KEY_LATITUDE, Double.toString(lat));
			values.put(CommonString.KEY_LONGITUDE, Double.toString(longitude));
			values.put(CommonString.KEY_IMAGE_PATH1, path);
			values.put(CommonString.KEY_IMAGE_PATH2, path_inside);
			values.put(CommonString.KEY_IMAGE_PATH3, path_inside1);
			values.put(CommonString.KEY_STATUS, status);

			db.insert(CommonString.TABLE_INSERT_GEO_TAG, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.getMessage());
		}

	}

	public ArrayList<GeotaggingBeans> getGeotaggingData(String status) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_GEO_TAG + "  WHERE STATUS = '"
					+ status + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					GeotaggingBeans data = new GeotaggingBeans();
					data.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					data.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
					data.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE))));
					data.setUrl1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					data.setUrl2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					data.setUrl3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					geodata.add(data);
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return geodata;

	}

	public ArrayList<GeotaggingBeans> getGeotaggingStatusData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_GEO_TAG, null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					GeotaggingBeans data = new GeotaggingBeans();
					data.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					data.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
					data.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE))));
					data.setUrl1(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
					data.setUrl2(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
					data.setUrl3(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
					data.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STATUS)));
					geodata.add(data);
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return geodata;

	}
	
	public void InsertBrandWiseTarget(TargetBrandWiseGetterSetter brandwisetargetdata) {

		db.delete("TARGET_BRANDWISE_SOS", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < brandwisetargetdata.getVerticalId().size(); i++) {

				values.put("VERTICAL_ID", brandwisetargetdata.getVerticalId().get(i));
				values.put("BRAND_ID", brandwisetargetdata.getBrandid().get(i));
				values.put("TARGET", brandwisetargetdata.getTarget().get(i));
			

				db.insert("TARGET_BRANDWISE_SOS", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	// insert kpi target
	public void insertKPITarget(TargetKPIGetterSetter data) {

		db.delete(CommonString.TABLE_KPI_TARGET, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getkpi().size(); i++) {

				values.put(CommonString.KEY_KPI, data.getkpi().get(i));
				values.put(CommonString.KEY_FOOD, data.getfood().get(i));
				values.put(CommonString.KEY_BEV, data.getbev().get(i));
			
				db.insert(CommonString.TABLE_KPI_TARGET, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	
	
	
	public void insertKPI_REPORTDATA(KpiReportGetterSetter data) {

		db.delete(CommonString.TABLE_KPI_REPORT, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getKpi_Value().size(); i++) {

			
				
				
				
				values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
				values.put(CommonString.KEY_KPI,data.getKPI().get(i));
				values.put(CommonString.KEY_KPI_VALUE,data.getKpi_Value().get(i));
				
				values.put(CommonString.KEY_VERTICAL_ID, data.getVertical_Type().get(i));
				values.put(CommonString.KEY_SR_NO, data.getSR_NO().get(i));
				
				
				db.insert(CommonString.TABLE_KPI_REPORT, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}
	
	
	
	
	// get BrandWise data for view
	
	public ArrayList<BrandWiseTarget> getBrandWiseData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		ArrayList<BrandWiseTarget> geodata = new ArrayList<BrandWiseTarget>();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  VERTICAL_NAME,BRAND_NAME,TARGET from TARGET_BRANDWISE_SOS TB INNER JOIN VERTICAL_MASTER VM ON TB.VERTICAL_ID =VM.VERTICAL_ID INNER JOIN BRAND_MASTER BM ON BM.BRAND_ID = TB.BRAND_ID"
					, null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					BrandWiseTarget data = new BrandWiseTarget();
					data.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("VERTICAL_NAME")));
					data.setBrandname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND_NAME")));
					data.setTarget(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("TARGET")));
				
					geodata.add(data);
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return geodata;

	}
	
	
	// get promotion data for view
	

	
	public ArrayList<PromotionViewGetterSetter> getPromotionData(String store_id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		ArrayList<PromotionViewGetterSetter> geodata = new ArrayList<PromotionViewGetterSetter>();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT VERTICAL_NAME,PROMO,PROMO_TYPE from PROMOTION_PEPSI PP INNER JOIN VERTICAL_MASTER VM ON PP.VERTICAL_ID = VM.VERTICAL_ID WHERE STORE_ID = '"
									+ store_id + "'", null);
			

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					PromotionViewGetterSetter data = new PromotionViewGetterSetter();
					data.setVerticalname(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("VERTICAL_NAME")));
					data.setPromo(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PROMO")));
						
					data.setProType(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PROMO_TYPE")));
				
					
				
					geodata.add(data);
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return geodata;

	}
	
	
	
	//data for kpi report food
	
	public ArrayList<PromotionViewGetterSetter> getKpiReportDataFood(String store_id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		ArrayList<PromotionViewGetterSetter> geodata = new ArrayList<PromotionViewGetterSetter>();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * FROM KPI_REPORT WHERE STORE_ID = '"
									+ store_id + "' AND VERTICAL_ID = "+"'Food' ORDER BY SR_NO", null);
			

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					
				/*	values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
					values.put(CommonString.KEY_KPI,data.getKPI().get(i));
					values.put(CommonString.KEY_KPI_VALUE,data.getKpi_Value().get(i));
					
					values.put(CommonString.KEY_VERTICAL_ID, data.getVertical_Type().get(i));
					//values.put(CommonString.KEY_SR_NO, data.getSR_NO().get(i));
					*/
					
					
					PromotionViewGetterSetter data = new PromotionViewGetterSetter();
					data.setKpi(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_KPI)));
					data.setKpivalue(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_KPI_VALUE)));
					data.setVerticalName(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
				
					geodata.add(data);
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return geodata;

	}
	
	
	
	
	
	//data for kpi report bevrages
	
		public ArrayList<PromotionViewGetterSetter> getKpiReportDataBev(String store_id) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");

			ArrayList<PromotionViewGetterSetter> geodata = new ArrayList<PromotionViewGetterSetter>();

			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT * FROM KPI_REPORT WHERE STORE_ID = '"
										+ store_id + "' AND VERTICAL_ID = "+"'Bev' ORDER BY SR_NO", null);
				

				if (dbcursor != null) {
					int numrows = dbcursor.getCount();

					dbcursor.moveToFirst();
					for (int i = 1; i <= numrows; ++i) {

						
					/*	values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
						values.put(CommonString.KEY_KPI,data.getKPI().get(i));
						values.put(CommonString.KEY_KPI_VALUE,data.getKpi_Value().get(i));
						
						values.put(CommonString.KEY_VERTICAL_ID, data.getVertical_Type().get(i));
						//values.put(CommonString.KEY_SR_NO, data.getSR_NO().get(i));
						*/
						
						
						PromotionViewGetterSetter data = new PromotionViewGetterSetter();
						data.setKpi(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_KPI)));
						data.setKpivalue(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_KPI_VALUE)));
						data.setVerticalName(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_VERTICAL_ID)));
					
						geodata.add(data);
						dbcursor.moveToNext();

					}

					dbcursor.close();

				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.getMessage());
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return geodata;

		}
		
	
	
	
	
	// get Asset data
	
	public ArrayList<AssetDelegate> getAssetDataforview(String store_id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		ArrayList<AssetDelegate> geodata = new ArrayList<AssetDelegate>();

		Cursor dbcursor = null;

		try {
     dbcursor = db.rawQuery("SELECT AM.ASSET,VM.VERTICAL_NAME FROM ASSEST_TARGET AT INNER JOIN ASSET_MASTER AM ON AT.ASSETS_ID = AM.ASSETS_ID INNER JOIN VERTICAL_MASTER VM ON VM.VERTICAL_ID = AT.VERTICAL_ID WHERE STORE_ID = '"
									+ store_id + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					AssetDelegate data = new AssetDelegate();
					data.setAsset(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET")));
					data.setVertical(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("VERTICAL_NAME")));
					
				
					geodata.add(data);
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return geodata;

	}

	
	
	// get display percentage
	
	public String getDisplayPercentageforfood(String store_id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

	
		Cursor dbcursor = null;
		String displayFood ="";

		try {
     dbcursor = db.rawQuery("SELECT ROUND(CAST(T2.PEPSI_DISPLAY AS FLOAT)/ CAST(T1.TOT_DISPLAY AS FLOAT)*100,2) AS SOPOI FROM "+
"(SELECT M.STORE_ID, COUNT(M.POSM_ID) AS TOT_DISPLAY FROM SOD_MASTER M INNER JOIN SOD_TRANSACTION T ON M._ID = T.SOD_ID"+
" INNER JOIN SKU_MASTER SK ON T.SKU_ID = SK.SKU_ID "+"INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID"+
" INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID "+" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID"+
" WHERE M.STORE_ID ='"+store_id +"'"+" AND VM.VERTICAL_ID = 1 GROUP BY M.STORE_ID) AS T1 "+
" INNER JOIN "+"(SELECT M.STORE_ID, COUNT(M.POSM_ID) AS PEPSI_DISPLAY FROM SOD_MASTER M INNER"+
" JOIN SOD_TRANSACTION T ON M._ID = T.SOD_ID "+"INNER JOIN SKU_MASTER SK ON T.SKU_ID = SK.SKU_ID"+

" INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID "+" INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID"+
" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID "+ "WHERE M.STORE_ID ='"+store_id +"'"+" AND VM.VERTICAL_ID = 1 AND M.COMPANY_ID = 1 GROUP BY "+

" M.STORE_ID) AS T2 ON T1.STORE_ID = T2.STORE_ID", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					displayFood = dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SOPOI"));
					
				
					
				
				
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return displayFood;

	}
	
	
	// get display percentage for beverage
	
	public String getDisplayPercentageforbeverage(String store_id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

	  String displayBev = "";

		Cursor dbcursor = null;

		try {
			 dbcursor = db.rawQuery("SELECT ROUND(CAST(T2.PEPSI_DISPLAY AS FLOAT)/ CAST(T1.TOT_DISPLAY AS FLOAT)*100,2) AS SOPOI FROM "+
					 "(SELECT M.STORE_ID, COUNT(M.POSM_ID) AS TOT_DISPLAY FROM SOD_MASTER M INNER JOIN SOD_TRANSACTION T ON M._ID = T.SOD_ID"+
					 " INNER JOIN SKU_MASTER SK ON T.SKU_ID = SK.SKU_ID "+"INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID"+
					 " INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID "+" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID"+
					 " WHERE M.STORE_ID ='"+store_id +"'"+" AND VM.VERTICAL_ID = 2 GROUP BY M.STORE_ID) AS T1 "+
					 " INNER JOIN "+"(SELECT M.STORE_ID, COUNT(M.POSM_ID) AS PEPSI_DISPLAY FROM SOD_MASTER M INNER"+
					 " JOIN SOD_TRANSACTION T ON M._ID = T.SOD_ID "+"INNER JOIN SKU_MASTER SK ON T.SKU_ID = SK.SKU_ID"+

					 " INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID "+" INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID"+
					 " INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID "+ "WHERE M.STORE_ID ='"+store_id +"'"+" AND VM.VERTICAL_ID = 2 AND M.COMPANY_ID = 1 GROUP BY "+

					 " M.STORE_ID) AS T2 ON T1.STORE_ID = T2.STORE_ID", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {
					displayBev =dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SOSPOI"));
					
					
				
					
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return displayBev;

	}
	
	
	// get promotion yes data
	
	public ArrayList<PromotionDelegates> getAvailablePromotiondata(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PromotionDelegates> list = new ArrayList<PromotionDelegates>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT PROMO_ID FROM INSERT_PROMOTION_PEPSI"+ " where "
					+ CommonString.KEY_STORE_ID+ "='" + storeid + "'" +" AND "+CommonString.KEY_AVAILABLE + "= '1" + "'", null);

	


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PromotionDelegates sb = new PromotionDelegates();

					
				sb.setPromoid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PROMO_ID")));
					
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	///// get target KPI
	
	
	public ArrayList<TargetKpi> getTargetKPI() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<TargetKpi> list = new ArrayList<TargetKpi>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * FROM KPI_TARGET", null);

	


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					TargetKpi sb = new TargetKpi();

					
				sb.setKpi(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("KPI")));
				
				sb.setFood(dbcursor.getString(dbcursor
						.getColumnIndexOrThrow("FOOD")));
				sb.setBev(dbcursor.getString(dbcursor
						.getColumnIndexOrThrow("BEV")));
					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	public String getSOSforFood(String store_id){

		Cursor dbcursor = null;
		
		String sosFood = "";

		try {
		     dbcursor = db.rawQuery("SELECT ROUND(AVG(SOS),2) AS SOS FROM (SELECT T1.CATEGORY_ID, cast(T2.PEPSI_TOT as float)/cast(T1.CAT_TOT as float)"+
		"*100 AS SOS FROM "+"(SELECT CA.CATEGORY_ID, SUM(FACEUP) AS CAT_TOT FROM SOS_PEPSI S INNER JOIN "+"SKU_MASTER SK ON S.SKU_ID = SK.SKU_ID"+
" INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID "+"INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID"+
		" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID "+"WHERE S.STORE_ID ='"+store_id+"'"+" AND VM.VERTICAL_ID = 1"+" GROUP BY CA.CATEGORY_ID) AS T1"+
" INNER JOIN "+"(SELECT CA.CATEGORY_ID, SUM(FACEUP) AS PEPSI_TOT FROM SOS_PEPSI S INNER JOIN "+
		" SKU_MASTER SK ON S.SKU_ID = SK.SKU_ID "+"INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID"+
" INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID"+" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID"
		+" WHERE S.STORE_ID = '"+store_id+"'"+" AND VM.VERTICAL_ID = 1 AND S.COMPANY_NAME='pepsi' GROUP BY CA.CATEGORY_ID) AS T2 ON T1.CATEGORY_ID = T2.CATEGORY_ID) as F"

, null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					sosFood = dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SOS"));
					
				
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return sosFood;
	
		
	}
	
	/// get SOS beverage
	
	public String getSOSforBevarage(String store_id){

		Cursor dbcursor = null;
		
		String sosBEv = "";

		try {
		     dbcursor = db.rawQuery("SELECT ROUND(AVG(SOS),2) AS SOS FROM (SELECT T1.CATEGORY_ID, cast(T2.PEPSI_TOT as float)/cast(T1.CAT_TOT as float)"+
		"*100 AS SOS FROM "+"(SELECT CA.CATEGORY_ID, SUM(FACEUP) AS CAT_TOT FROM SOS_PEPSI S INNER JOIN "+"SKU_MASTER SK ON S.SKU_ID = SK.SKU_ID"+
" INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID "+"INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID"+
		" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID "+"WHERE S.STORE_ID ='"+store_id+"'"+" AND VM.VERTICAL_ID = 2"+" GROUP BY CA.CATEGORY_ID) AS T1"+
" INNER JOIN "+"(SELECT CA.CATEGORY_ID, SUM(FACEUP) AS PEPSI_TOT FROM SOS_PEPSI S INNER JOIN "+
		" SKU_MASTER SK ON S.SKU_ID = SK.SKU_ID "+"INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID"+
" INNER JOIN CATEGORY_VERTICALMAPPING CA ON BR.CATEGORY_ID = CA.CATEGORY_ID"+" INNER JOIN VERTICAL_MASTER VM ON CA.VERTICAL_ID = VM.VERTICAL_ID"
		+" WHERE S.STORE_ID = '"+store_id+"'"+" AND VM.VERTICAL_ID =2 AND S.COMPANY_NAME='pepsi' GROUP BY CA.CATEGORY_ID) AS T2 ON T1.CATEGORY_ID = T2.CATEGORY_ID) as F"

, null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					sosBEv = dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SOS"));
					
				
					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return sosBEv;
	
		
	}
	
	
	///////////////////////////////////////////////////////
	

	///// get Promotion
	
	
	public String getPromotionforFood(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		String promotion="";
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT CAST(SUM(AVAILABLE) AS FLOAT) / CAST(COUNT(AVAILABLE) AS FLOAT)*100 AS "+
"PROMOTION FROM INSERT_PROMOTION_PEPSI D INNER JOIN PROMOTION_PEPSI M ON D.STORE_ID = M.STORE_ID AND D.PROMO_ID = M.PROMO_ID WHERE D.STORE_ID = '"+storeid+" AND M.VERTICAL_ID = 1", null);

	


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					promotion =dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PROMOTION"));

					dbcursor.moveToNext();
				}
				dbcursor.close();
		
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return promotion;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return promotion;

	}
	
	///// get Promotion
	
	
	public String getPromotionforBeverage(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		String promotion="";
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT CAST(SUM(AVAILABLE) AS FLOAT) / CAST(COUNT(AVAILABLE) AS FLOAT)*100 AS "+
"PROMOTION FROM INSERT_PROMOTION_PEPSI D INNER JOIN PROMOTION_PEPSI M ON D.STORE_ID = M.STORE_ID AND D.PROMO_ID = M.PROMO_ID WHERE D.STORE_ID = '"+storeid+" AND M.VERTICAL_ID = 2", null);

	


			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					promotion =dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PROMOTION"));

					dbcursor.moveToNext();
				}
				dbcursor.close();
		
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return promotion;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return promotion;

	}
}
