package com.cpm.Constants;

import android.os.Environment;

public class CommonString {

	// preferenec keys
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_REMEMBER = "remember";
	public static final String KEY_RIGHT_NAME = "right_name";
	public static final String KEY_PATH = "path";
	public static final String KEY_FILE_PATH = Environment.getExternalStorageDirectory() + "/PepsicoImages/";
	public static final String KEY_VERSION = "version";
	// public static final String KEY_APPVERSION = "1.1";
	public static final String KEY_DATE = "date";
	public static final String MID = "MID";
	public static final String KEY_P = "P";
	public static final String KEY_D = "D";
	public static final String KEY_U = "U";
	public static final String KEY_N = "N";
	public static final String KEY_C = "C";
	public static final String KEY_L = "Leave";
	public static final String KEY_VALID = "Valid";

	public static final String STORE_STATUS_LEAVE = "L";

	public static final String KEY_pepsi = "pepsi";
	public static final String KEY_other = "other";

	// webservice constants
	public static final String KEY_SUCCESS = "Success";
	public static final String KEY_FAILURE = "Failure";
	public static final String KEY_FALSE = "False";
	public static final String KEY_CHANGED = "Changed";
	public static final String KEY_CHECKOUT_STATUS = "CHECKOUT_STATUS";
	
	public static final String KEY_WEEK_STATUS = "weekStatus";
	
	public static final String KEY_SERVICE_STATUS = "SERVICE_STATUS";
	public static final String KEY_UPLOADCHECKOUT_STATUS = "UPLOADCHECKOUT_STATUS";
	public static final String KEY_NO_DATA = "No Data";

	public static final String KEY_PROMO = "PROMO";
	public static final String KEY_PROMO_TYPE = "PROMO_TYPE";
	public static final String KEY_PROMO_ID = "PROMO_ID";
	public static final String KEY_PROMO_TYPE_ID = "PROMO_TYPE_ID";
	public static final String KEY_VERTICALID = "VERTICALID";

	public static final String KEY_DOM1 = "DOM1";
	public static final String KEY_DOM2 = "DOM2";
	public static final String KEY_DOM3 = "DOM3";

	public static final String KEY_ENTRY = "ENTRY";
	public static final String KEY_IMAGE = "IMAGE";

	public static final String KEY_SOD_ID = "SOD_ID";

	public static final String KEY_ASSETS_ID = "ASSETS_ID";
	public static final String KEY_ASSETS = "ASSET";

	public static final String KEY_STOCK_REASON_ID = "STOCKREASON_ID";
	public static final String KEY_STOCK_REASON = "STOCK_REASON";

	// location
	public static final String TABLE_LOCATION_STATUS = "LOCATION_STATUS";
	public static final String KEY_NETWORK_STATUS = "NETWORK_STATUS";
	public static final String KEY_CURRENT_TIME = "CURRENT_TIME";

	public static final String NAMESPACE = "http://tempuri.org/";

	//public static final String URL1 = "http://10.200.20.133/PepsicoService/PepsicoWebService.asmx";
	public static final String URL = "http://pepsico.parinaam.in/Pepsicowebservice.asmx";

	public static final String METHOD_LOGIN = "Attendance_Status_V2";
	public static final String SOAP_ACTION_LOGIN = "http://tempuri.org/"
			+ METHOD_LOGIN;

	public static final String METHOD_UPLOAD_STORE_STATUS = "InsertUserCurrentLocation";
	public static final String SOAP_ACTION_UPLOAD_STORE_STATUS = "http://tempuri.org/"
			+ METHOD_UPLOAD_STORE_STATUS;

	public static final String METHOD_NAME_JCP = "DOWLOAD_JCP_CURRENT";
	public static final String SOAP_ACTION_JCP = "http://tempuri.org/"
			+ METHOD_NAME_JCP;

	// Download
	public static final String METHOD_VERTICAL_MASTER = "DOWLOAD_VERTICALMASTER";
	public static final String SOAP_ACTION_VERTICAL_MASTER = "http://tempuri.org/"
			+ METHOD_VERTICAL_MASTER;

	public static final String METHOD_BRAND_MASTER = "DOWLOAD_BRANDMASTER";
	public static final String SOAP_ACTION_BRAND_Master = "http://tempuri.org/"
			+ METHOD_BRAND_MASTER;

	public static final String METHOD_VERTICAL_BRAND_MAPPING = "DOWLOAD_VERTICALBRANDMAPPING";
	public static final String SOAP_ACTION_VERTICAL_BRAND_Mapping = "http://tempuri.org/"
			+ METHOD_VERTICAL_BRAND_MAPPING;

	public static final String METHOD_VERTICAL_SKU_MAPPING = "SKUBRANDMAPPINGDownload";
	public static final String SOAP_ACTION_VERTICAL_SKU_Mapping = "http://tempuri.org/"
			+ METHOD_VERTICAL_SKU_MAPPING;

	public static final String METHOD_CATEGORY_MASTER = "DOWLOAD_CATEGORYMASTER";
	public static final String SOAP_ACTION_CATEGORY_MASTER = "http://tempuri.org/"
			+ METHOD_CATEGORY_MASTER;

	public static final String METHOD_CATEGORY_SKU_MAPPING = "CATEGORYSKUMAPPINGDownload";
	public static final String SOAP_ACTION_CATEGORY_SKU_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_SKU_MAPPING;

	public static final String METHOD_CATEGORY_VERTICAL_MAPPING = "CATEGORYVERTICALMAPPINGDownload";
	public static final String SOAP_ACTION_CATEGORY_VERTICAL_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_VERTICAL_MAPPING;

	public static final String METHOD_CATEGORY_POSM_MAPPING = "POSMBRANDMAPPINGDownload";
	public static final String SOAP_ACTION_POSM_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_POSM_MAPPING;

	public static final String METHOD_SKU_MASTER_DOWNLOAD = "SKU_MASTERDownload";
	public static final String SOAP_ACTION_SKU_MASTER = "http://tempuri.org/"
			+ METHOD_SKU_MASTER_DOWNLOAD;

	public static final String METHOD_COMPANY_MASTER_DOWNLOAD = "COMPANY_MASTERDownload";
	public static final String SOAP_ACTION_COMPANY_MASTER = "http://tempuri.org/"
			+ METHOD_COMPANY_MASTER_DOWNLOAD;

	// Shahab
	public static final String METHOD_NONSKU_REASON = "DOWLOAD_NON_STOCK_REASON_MASTER";
	public static final String SOAP_ACTION_NONSKU_REASON = "http://tempuri.org/"
			+ METHOD_NONSKU_REASON;

	public static final String METHOD_SKU_FOCUS_DOWNLOAD = "SKUAVALIBILITY_FOCUS";
	public static final String SOAP_ACTION_SKU_FOCUS = "http://tempuri.org/"
			+ METHOD_SKU_FOCUS_DOWNLOAD;

	public static final String METHOD_MAPPING_COMPETITOR = "DOWLOAD_MAPPINGCOMPEPITORBRAND";
	public static final String SOAP_ACTION_MAPPING_COMPETITOR = "http://tempuri.org/"
			+ METHOD_MAPPING_COMPETITOR;

	public static final String METHOD_POSM_MASTER_DOWNLOAD = "DOWLOAD_POSMMASTER";
	public static final String SOAP_ACTION_POSM_MASTER_DOWNLOAD = "http://tempuri.org/"
			+ METHOD_POSM_MASTER_DOWNLOAD;

	public static final String METHOD_SKUTARGET_FOCUS = "SOD_TARGET";
	public static final String SOAP_ACTION_SKUTARGET_FOCUS = "http://tempuri.org/"
			+ METHOD_SKUTARGET_FOCUS;
	
	//public static final String METHOD_PROMOTION_TARGET = "DOWLOAD_TARGETPROMOTION";
	public static final String METHOD_PROMOTION_TARGET = "DOWLOAD_TARGETPROMOTION_NEW";
	public static final String SOAP_ACTION_PROMOTION = "http://tempuri.org/"
			+ METHOD_PROMOTION_TARGET;
	
	public static final String METHOD_TARGET_KPI = "DOWNLOAD_KPI_TARGET";
	public static final String SOAP_TARGET_KPI = "http://tempuri.org/"
			+ METHOD_TARGET_KPI;
	
	
	// common string for kpi report
	
	public static final String METHOD_KPI_REPORT = "DOWLOAD_STORE_KPI";
	public static final String SOAP_KPI_REPORT = "http://tempuri.org/"
			+ METHOD_KPI_REPORT;
	
	
	public static final String METHOD_TARGET_BRANDWISE_SOS = "Download_Universal";
	public static final String SOAP_ACTION_TARGET_BRANDWISE = "http://tempuri.org/"
			+ METHOD_TARGET_BRANDWISE_SOS;

	public static final String METHOD_ASSETS_MASTER = "DOWLOAD_ASSETMASTER";
	public static final String SOAP_ACTION_ASSET_MASTER = "http://tempuri.org/"
			+ METHOD_ASSETS_MASTER;

	public static final String METHOD_ASSETS_TARGET = "ASSETTARGET_V3_BRAND";
	public static final String SOAP_ACTION_ASSET_TARGET = "http://tempuri.org/"
			+ METHOD_ASSETS_TARGET;

	// Geo Tag
	public static final String METHOD_GEO_TAG = "STORES_BY_MAPPING";
	public static final String SOAP_ACTION_GEO_TAG = "http://tempuri.org/"
			+ METHOD_GEO_TAG;

	public static final String METHOD_GEO_TAG_CITY = "DOWLOAD_CITYMASTER";
	public static final String SOAP_ACTION_GEO_TAG_CITY = "http://tempuri.org/"
			+ METHOD_GEO_TAG_CITY;

	// Geo Tag
	public static final String TABLE_GEOTAG_CITY = "GEO_TAG_CITY";
	public static final String TABLE_GEO_TAG_MAPPING = "GEO_TAG_MAPPING";

	public static final String TABLE_INSERT_GEO_TAG = "INSERT_GEO_TAG_DATA";

	// Upload Coverage
	public static final String METHOD_UPLOAD_DR_STORE_COVERAGE = "UPLOAD_COVERAGENEW_VISITOTHER_STORE_V2";
	
	
	public static final String METHOD_UPLOAD_ATTANDANCEDATA = "WS_AttendanceUpdate";
	
	public static final String SOAP_ACTION_UPLOAD_ATTANDANCE = "http://tempuri.org/"
			+ METHOD_UPLOAD_ATTANDANCEDATA;
	
	
	
	public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_STORE_COVERAGE;

	public static final String METHOD_UPLOAD_ASSET = "UPLOAD_ASSET_EXECUTION_V3";
	public static final String SOAP_ACTION_UPLOAD_ASSET = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET;

	public static final String METHOD_DELETE_ASSET = "DeleteASSET_EXECUTION";
	public static final String SOAP_ACTION_DELETE_ASSET = "http://tempuri.org/"
			+ METHOD_DELETE_ASSET;

	public static final String METHOD_UPLOAD_AVAILABILITY = "InsertSOSXML_UPLOAD_NEW";// "SOS_UPLOAD";
	public static final String SOAP_ACTION_UPLOAD_AVAILABILITY = "http://tempuri.org/"
			+ METHOD_UPLOAD_AVAILABILITY;

	public static final String METHOD_UPLOAD_CATEGORY_IMAGES = "Upload_AVAILABILITY_CATEGORY_IMAGES";// "SOS_UPLOAD";
	public static final String SOAP_ACTION_UPLOAD_CATEGORY_IMAGES = "http://tempuri.org/"
			+ METHOD_UPLOAD_CATEGORY_IMAGES;

	public static final String METHOD_UPLOAD_SOD_DATA = "ShareOfDisplay_UPDATE";
	public static final String SOAP_ACTION_UPLOAD_SOD = "http://tempuri.org/"
			+ METHOD_UPLOAD_SOD_DATA;

	public static final String METHOD_UPLOAD_GEOTAG = "UPLOAD_Geotag";
	public static final String SOAP_ACTION_UPLOAD_GEOTAG = "http://tempuri.org/"
			+ METHOD_UPLOAD_GEOTAG;

	public static final String METHOD_NON_WORKING_MASTER = "DOWLOAD_NONWORKINGMaster";
	public static final String SOAP_ACTION_NONWORKING = "http://tempuri.org/"
			+ METHOD_NON_WORKING_MASTER;

	
	public static final String METHOD_SUBNON_WORKING_MASTER = "DOWLOAD_NONWORKINGV1";
	public static final String SOAP_ACTION_SUBNONWORKING = "http://tempuri.org/"
			+ METHOD_SUBNON_WORKING_MASTER;

	
	public static final String METHOD_KEY_ACCOUNT_MASTER = "DOWLOAD_KeyAccountMaster";
	public static final String SOAP_ACTION_KEY_ACCOUNT = "http://tempuri.org/"
			+ METHOD_KEY_ACCOUNT_MASTER;

	public static final String METHOD_SET_COVERAGE_STATUS = "COVERAGESTATUS_UPDATE";
	public static final String SOAP_ACTION_SET_COVERAGE_STATUS = "http://tempuri.org/"
			+ METHOD_SET_COVERAGE_STATUS;

	public static final String METHOD_Checkout_StatusNew = "Checkout_StatusNew";
	public static final String SOAP_ACTION_Checkout_StatusNew = "http://tempuri.org/"
			+ METHOD_Checkout_StatusNew;

	public static final String METHOD_SOD_DELETE = "DeleteSod";
	public static final String SOAP_ACTION_SOD_DELETE = "http://tempuri.org/"
			+ METHOD_SOD_DELETE;

	
	public static final String METHOD_UPLOAD_ASSET_NEW = "UPLOAD_ASSET_EXECUTION_V3_BRAND";
	public static final String SOAP_ACTION_ASSET_UPLOAD_NEW = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET_NEW;

	
	
	
	public static final String METHOD_UPLOAD_ASSET_SIZE = "UPLOAD_ASSET_EXECUTION_SIZE";
	public static final String SOAP_ACTION_ASSET_UPLOAD_SIZE = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET_SIZE;

	public static final String METHOD_UPLOAD_ASSET_SKU = "UPLOAD_ASSET_EXECUTION_SKU";
	public static final String SOAP_ACTION_ASSET_UPLOAD_SKU = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET_SKU;
	
	
	
	
	public static final String METHOD_UPLOAD_ASSET_SKU_OOSE = "UPLOAD_DR_STOCk";
	public static final String SOAP_ACTION_ASSET_UPLOAD_SKU_OOSE = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET_SKU_OOSE;
	
	
	
	public static final String METHOD_UPLOAD_ASSET_SKU_OOSE_NEW_SERVICE = "DrUploadXml";
	public static final String SOAP_ACTION_ASSET_UPLOAD_SKU_OOSE_NEW_SERVICE = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET_SKU_OOSE_NEW_SERVICE;
	
	
	
	
	
	
	public static final String METHOD_SOD_UPLOAD = "ShareOfDisplay_UPDATE";
	public static final String SOAP_ACTION_SOD_UPLOAD = "http://tempuri.org/"
			+ METHOD_SOD_UPLOAD;

	public static final String METHOD_PROMOTION_PEPSI_UPLOAD = "Upload_PEPSICO_PromotionActivity";
	public static final String SOAP_ACTION_PROMOTION_PEPSI_UPLOAD = "http://tempuri.org/"
			+ METHOD_PROMOTION_PEPSI_UPLOAD;
	
	

	public static final String METHOD_PROMOTION_COM_UPLOAD = "UploadCOMPETITION_PROMOTION";
	public static final String SOAP_ACTION_PROMOTION_COM_UPLOAD = "http://tempuri.org/"
			+ METHOD_PROMOTION_COM_UPLOAD;

	public static final String METHOD_CHECKOUT = "Checkout_Status";
	public static final String SOAP_ACTION_CHECKOUT = "http://tempuri.org/"
			+ METHOD_CHECKOUT;

	// database
	public static final String KEY_KPI = "KPI";
	
	public static final String KEY_KPI_VALUE = "KPI_VALUE";
	
	public static final String KEY_FOOD = "FOOD";
	public static final String KEY_BEV = "BEV";
	
	
	
	public static final String KEY_ID = "_id";
	public static final String KEY_STORE_ID = "STORE_ID";
	public static final String KEY_SR_NO = "SR_NO";
	
	public static final String KEY_STORE_NAME = "STORE_NAME";
	public static final String KEY_ADDRESS = "ADDRESS";
	public static final String KEY_USER_ID = "USER_ID";
	public static final String KEY_IN_TIME = "IN_TIME";
	public static final String KEY_OUT_TIME = "OUT_TIME";
	public static final String KEY_VISIT_DATE = "VISIT_DATE";
	public static final String KEY_LATITUDE = "LATITUDE";
	public static final String KEY_LONGITUDE = "LONGITUDE";
	public static final String KEY_REASON_ID = "REASON_ID";
	public static final String KEY_REASON = "REASON";
	
	public static final String KEY_MREASON_ID = "MREASON_ID";
	public static final String KEY_MREASON = "MREASON";
	public static final String KEY_ACC_ID = "KEYACCOUNT_ID";
	public static final String KEY_ACCOUNT= "KEYACCOUNT";
	public static final String KEY_STORE_INFO = "STORE_INFO";
	public static final String KEY_INFORMED_TO = "INFORMED_TO";

	public static final String KEY_STATUS = "STATUS";
	public static final String KEY_STOREVISITED = "STORE_VISITED";
	public static final String KEY_STOREVISITED_STATUS = "STORE_VISITED_STATUS";

	// FOCUS SKU

	
	
	
	public static final String KEY_VERTICAL_ID = "VERTICAL_ID";
	
	public static final String KEY_SOS = "SOS";
	
	public static final String KEY_VERTICAL_NAME = "VERTICAL_NAME";
	public static final String KEY_BRAND_ID = "BRAND_ID";
	public static final String KEY_CATEGORY_ID = "CATEGORY_ID";

	public static final String KEY_COMPETITOR_ID = "COMPETITOR_BRANDID";
	public static final String KEY_CATEGORY_NAME = "CATEGORY_NAME";
	public static final String KEY_CATEGORY_CODE = "CATEGORY_CODE";
	public static final String KEY_CATEGORY_SEQ = "CATEGORY_SEQUENCE";
	public static final String KEY_BRAND_NAME = "BRAND_NAME";
	public static final String KEY_SKU_ID = "SKU_ID";
	public static final String KEY_SKU_NAME = "SKU_NAME";
	public static final String KEY_COMPANY_ID = "COMPANY_ID";
	public static final String KEY_COMAPANY_NAME = "COMPANY_NAME";
	public static final String KEY_MID = "MID";
	public static final String KEY_VALUE = "VALUE";
	public static final String KEY_IMAGE_PATH1 = "IMAGE_PATH1";
	public static final String KEY_IMAGE_PATH2 = "IMAGE_PATH2";
	public static final String KEY_IMAGE_PATH3 = "IMAGE_PATH3";
	public static final String KEY_FOCUS = "FOCUS";
	public static final String KEY_BR_SEQUENCE = "BRSEQUENCE";
	public static final String KEY_SKU_SEQUENCE = "SKUSEQUENCE";
	public static final String KEY_COMPETITOR = "COMPETITOR";
	public static final String KEY_NON_KEYCOMPETITOR = "NONKEYCOMPETITOR";
	public static final String KEY_AVAILABLE = "AVAILABLE";
	public static final String KEY_REMARKS = "REMARKS";
	// Traing table
	public static final String KEY_RSP_ID = "RSP_ID";
	public static final String KEY_RSP_NAME = "RSP_NAME";
	public static final String KEY_EMAIL = "EMAIL";
	public static final String KEY_MOBILE = "MOBILE";
	public static final String KEY_LEVEL_ID = "LEVEL_ID";
	public static final String KEY_TRAINING_LEVEL = "TRAINING_LEVEL";
	public static final String KEY_TRAINING_ID = "TRAINING_ID";
	public static final String KEY_TRAINING = "TRAINING";
	public static final String KEY_POSM_ID = "POSM_ID";
	public static final String KEY_POSM = "POSM_NAME";
	public static final String KEY_TARGET = "TARGET";
	public static final String KEY_ASSET_PURE = "ASSET_PURE";
	public static final String KEY_ASSET_PURE_DOWNLOAD = "ASSET_PURE_DOWNLOAD";
	public static final String KEY_STOCK = "STOCK";
	public static final String KEY_SPINNER = "SPINNER";
	public static final String KEY_LENGTH = "LENGTH";
	public static final String KEY_HEIGHT = "HEIGHT";
	public static final String KEY_RANDOM = "RANDOM";
	
	public static final String KEY_SECONDARY = "SECONDARY";
	public static final String KEY_SECONDPRIMARY = "SECONDPRIMARY";
	public static final String KEY_BREATH = "BREATH";
	public static final String KEY_FACEUP = "FACEUP";

	public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";
	public static final String TABLE_STORE_DETAIL = "STORE_MASTER";

	public static final String TABLE_VERTICAL_MASTER = "VERTICAL_MASTER";
	public static final String TABLE_BRAND_MASTER = "BRAND_MASTER";
	public static final String TABLE_BRAND_MAPPING = "BRAND_MAPPING";
	public static final String TABLE_SKU_MAPPING = "SKU_MAPPING";
	public static final String TABLE_POSM_MASTER = "POSM_MASTER";
	public static final String TABLE_CATEGORY_MASTER = "CATEGORY_MASTER";
	public static final String TABLE_CATEGORY_MAPPING = "CATEGORY_MAPPING";
	public static final String TABLE_CATEGORY_VERTICALMAPPING = "CATEGORY_VERTICALMAPPING";

	public static final String TABLE_SKU_MASTER = "SKU_MASTER";
	public static final String TABLE_COMPANY_MASTER = "COMPANY_MASTER";
	public static final String TABLE_SKU_FOCUS = "SKU_FOCUS";
	public static final String TABLE_COMPETITOR_MAPPING = "COMPETITOR_MAPPING";
	public static final String TABLE_SKU_TARGET = "SOD_TARGET";
	public static final String TABLE_SOD_DATA = "SOD_MASTER";
	public static final String TABLE_DISPLAY_DATA = "DISPLAY_MASTER";
	public static final String TABLE_SOD_PEPSIDATA = "SOD_TRANSACTION";
	public static final String TABLE_DISPLAY_TRANSACTION = "DISPLAY_TRANSACTION";
	public static final String TABLE_SOD_OTHERDATA = "SOD_TEMP";
	public static final String TABLE_DISPLAY_SKU = "SKU_TEMP";
	public static final String TABLE_SKU_AVAILBILITY = "SKU_AVAILBILITY";

	public static final String TABLE_ASSET_MASTER = "ASSET_MASTER";
	public static final String TABLE_ASSET_TARGET = "ASSEST_TARGET";

	public static final String TABLE_INSERT_ASSET_DATA = "ASSET_DATA";
	
	public static final String TABLE_INSERT_DISPLAY_TEMPDATA = "DISPLAY_TEMP_DATA";

	public static final String TABLE_SUBNON_WORKING_REASON = "SUBNON_WORKING_DATA";
	
	public static final String TABLE_NON_WORKING_REASON = "NON_WORKING_DATA";

	public static final String TABLE_KEY_ACCOUNT = "KEY_ACCOUNT";

	public static final String TABLE_NON_SKU_REASON = "NON_SKU_REASON";

	public static final String TABLE_SOS_PEPSI = "SOS_PEPSI";

	public static final String TABLE_SOS_IMAGES = "SOS_IMAGES";
	
	public static final String TABLE_KPI_TARGET = "KPI_TARGET";
	public static final String TABLE_KPI_REPORT = "KPI_REPORT";
	

	public static final String TABLE_SOS_BRAND_CHECK = "SOS_BRAND_CHECK";

	public static final String TABLE_PROMOTION_OTHER = "PROMOTION_OTHER";

	public static final String TABLE_PROMOTION_PEPSI = "PROMOTION_PEPSI";

	public static final String TABLE_INSERT_PROMOTION_PEPSI = "INSERT_PROMOTION_PEPSI";

	public static final String TABLE_CHECK_OUT = "CHECK_OUT";
	
	public static final String TABLE_SOS_PEPSI_OOS = "SOS_PEPSI_OOS";

	public static final String CREATE_TABLE_NON_SKU_REASON = "CREATE TABLE NON_SKU_REASON(STOCK_REASON VARCHAR, STOCK_REASON_ID VARCHAR)";
	// public static final String CREATE_TABLE_KEY_MODEL_DATA =
	// "CREATE TABLE KEY_MODEL_DATA (MID INTEGER, KEY_MODEL_NAME VARCHAR,KEY_MODEL_ID INTEGER,KEY_MODEL_QTNY INTEGER)";

	public static final String CREATE_TABLE_LOCATION_STATUS = "CREATE TABLE "
			+ TABLE_LOCATION_STATUS + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_DATE + " VARCHAR,"
			+ KEY_CURRENT_TIME + " VARCHAR," + KEY_LATITUDE + " VARCHAR,"
			+ KEY_LONGITUDE + " VARCHAR," + KEY_NETWORK_STATUS + " VARCHAR)";

	public static final String CREATE_TABLE_CHECKOUT = "CREATE TABLE "
			+ TABLE_CHECK_OUT + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_DATE + " VARCHAR,"
			+ KEY_CURRENT_TIME + " VARCHAR," + KEY_LATITUDE + " VARCHAR,"
			+ KEY_LONGITUDE + " VARCHAR)";

	public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE "
			+ TABLE_COVERAGE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " INTEGER,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
			+ KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"
			+ KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR,"+KEY_other + " VARCHAR,"
			+ KEY_REASON_ID + " INTEGER," + KEY_REASON + " VARCHAR,"+KEY_STORE_INFO + " VARCHAR," + KEY_ACC_ID + " VARCHAR,"
			+ KEY_REMARKS + " VARCHAR," + KEY_IMAGE + " VARCHAR," + KEY_STATUS
			+ " VARCHAR)";

	public static final String CREATE_TABLE_STORE_MASTER = "CREATE TABLE "
			+ TABLE_STORE_DETAIL + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " INTEGER," + KEY_STORE_NAME + " VARCHAR," + KEY_ADDRESS
			+ " VARCHAR," + KEY_VISIT_DATE + " VARCHAR," + KEY_STATUS
			+ " VARCHAR," + KEY_CHECKOUT_STATUS + " VARCHAR,"+ KEY_WEEK_STATUS +" VARCHAR," + KEY_LATITUDE
			+ " VARCHAR," + KEY_LONGITUDE + " VARCHAR )";

	public static final String CREATE_TABLE_PROMOTION_OTHER = "CREATE TABLE "
			+ TABLE_PROMOTION_OTHER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_STORE_ID + " INTEGER," + KEY_COMPANY_ID + " INTEGER,"
			+ KEY_COMAPANY_NAME + " VARCHAR," + KEY_REMARKS + " VARCHAR,"
			+ KEY_BRAND_ID + " INTEGER," + KEY_BRAND_NAME + " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR)";

	public static final String CREATE_TABLE_PROMOTION_PEPSI = "CREATE TABLE "
			+ TABLE_PROMOTION_PEPSI + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " INTEGER," + KEY_PROMO + " VARCHAR," + KEY_PROMO_ID
			+ " INTEGER," + KEY_PROMO_TYPE_ID + " INTEGER," + KEY_VERTICAL_ID + " INTEGER,"  + KEY_PROMO_TYPE
			+ " VARCHAR)";

	public static final String CREATE_TABLE_INSERT_PROMOTION_PEPSI = "CREATE TABLE "
			+ TABLE_INSERT_PROMOTION_PEPSI
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ MID
			+ " INTEGER,"
			+ KEY_STORE_ID
			+ " INTEGER,"
			+ KEY_PROMO
			+ " VARCHAR,"
			+ KEY_PROMO_ID
			+ " INTEGER,"
			+ KEY_PROMO_TYPE_ID
			+ " INTEGER,"
			+ KEY_PROMO_TYPE
			+ " VARCHAR,"
			+ KEY_IMAGE
			+ " VARCHAR,"
			+ KEY_REMARKS + " VARCHAR," + KEY_AVAILABLE + " VARCHAR)";

	public static final String CREATE_TABLE_VERTICAL_MASTER = "CREATE TABLE "
			+ TABLE_VERTICAL_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_VERTICAL_ID
			+ " INTEGER," + KEY_VERTICAL_NAME + " VARCHAR)";

	public static final String CREATE_TABLE_BRAND_MASTER = "CREATE TABLE "
			+ TABLE_BRAND_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_BRAND_ID
			+ " INTEGER," + KEY_BRAND_NAME + " VARCHAR," + KEY_CATEGORY_ID
			+ " INTEGER," + KEY_BR_SEQUENCE + " INTEGER," + KEY_COMPANY_ID
			+ " VARCHAR)";

	public static final String CREATE_TABLE_SUBNON_WORKING = "CREATE TABLE "
			+ TABLE_SUBNON_WORKING_REASON + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_REASON_ID
			+ " INTEGER," +KEY_MREASON_ID
			+ " INTEGER," + KEY_REASON + " VARCHAR," +KEY_other+ " VARCHAR," + KEY_ENTRY + " VARCHAR,"+KEY_INFORMED_TO + " VARCHAR," + KEY_STORE_INFO + " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR)";
	
	public static final String CREATE_TABLE_NON_WORKING = "CREATE TABLE "
			+ TABLE_NON_WORKING_REASON + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," +KEY_MREASON_ID
			+ " INTEGER," + KEY_MREASON + " VARCHAR)";
	
	public static final String CREATE_TABLE_KEY_ACCOUNT = "CREATE TABLE "
			+ TABLE_KEY_ACCOUNT + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," +KEY_ACC_ID
			+ " VARCHAR," + KEY_ACCOUNT + " VARCHAR)";


	public static final String CREATE_TABLE_ASSETS_MASTER = "CREATE TABLE "
			+ TABLE_ASSET_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_ASSETS + " VARCHAR,"
			+ KEY_ASSETS_ID + " VARCHAR)";

	public static final String CREATE_TABLE_ASSETS_TARGET = "CREATE TABLE "
			+ TABLE_ASSET_TARGET + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_SKU_ID + " VARCHAR,"
			+KEY_VERTICAL_ID + " VARCHAR,"
			+ KEY_TARGET + " VARCHAR,"
			+KEY_BRAND_ID + " VARCHAR,"
			+ KEY_BRAND_NAME + " VARCHAR,"
			+ KEY_RANDOM + " VARCHAR,"
			+ KEY_SECONDPRIMARY + " VARCHAR,"	
			+ KEY_ASSET_PURE + " VARCHAR,"
			+ KEY_ASSETS_ID + " VARCHAR)";

	public static final String CREATE_TABLE_SKU_AVAILBILITY = "CREATE TABLE "
			+ TABLE_SKU_AVAILBILITY + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_STORE_ID + " VARCHAR," + KEY_SKU_ID + " VARCHAR," + KEY_STOCK
			+ " VARCHAR," + KEY_FACEUP + " VARCHAR," + KEY_LENGTH + " VARCHAR)";

	public static final String CREATE_TABLE_SOS_PEPSI = "CREATE TABLE "
			+ TABLE_SOS_PEPSI
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ MID
			+ " INTEGER,"
			+ KEY_STORE_ID
			+ " INTEGER,"
			+ KEY_SKU_ID
			+ " INTEGER,"
			+ KEY_SKU_NAME
			+ " VARCHAR,"
			+ KEY_STOCK
			+ " INTEGER,"
			+ KEY_FACEUP
			+ " INTEGER,"
			+ KEY_LENGTH
			+ " VARCHAR,"
			+ KEY_DOM1
			+ " VARCHAR,"
			+ KEY_DOM2
			+ " VARCHAR,"
			+ KEY_DOM3
			+ " VARCHAR,"
			+ KEY_COMAPANY_NAME
			+ " VARCHAR,"
			+ KEY_BRAND_ID
			+ " INTEGER,"
			+ " SKU_REASON_ID VARCHAR,UNIQUE(STORE_ID, SKU_ID) ON CONFLICT FAIL)";

	public static final String CREATE_TABLE_SOS_PEPSI_OOS = "CREATE TABLE "
			+ TABLE_SOS_PEPSI_OOS
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ MID
			+ " INTEGER,"
			+ KEY_STORE_ID
			+ " INTEGER,"
			+ KEY_SKU_ID
			+ " INTEGER,"
			+ KEY_SKU_NAME
			+ " VARCHAR,"
			+ KEY_SPINNER
			+ " VARCHAR,"
			+ KEY_COMAPANY_NAME
			+ " VARCHAR,"
			+ KEY_VISIT_DATE
			+ " VARCHAR,"
			+ KEY_BRAND_ID
			+ " INTEGER)";
	
	public static final String CREATE_TABLE_SOS_IMAGES = "CREATE TABLE "
			+ TABLE_SOS_IMAGES + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_CATEGORY_ID + " VARCHAR," + KEY_IMAGE_PATH1
			+ " VARCHAR," + KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3
			+ " VARCHAR )";

	
	public static final String CREATE_TABLE_KPI_TARGET = "CREATE TABLE "
			+ TABLE_KPI_TARGET + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_KPI
			+ " VARCHAR," + KEY_FOOD + " VARCHAR," +  KEY_BEV
			+ " VARCHAR)";
	
	
	
	
	public static final String CREATE_TABLE_KPI_REPORT = "CREATE TABLE "
			+ TABLE_KPI_REPORT + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," 
			+ KEY_KPI
			+ " VARCHAR,"
			+ KEY_VERTICAL_ID
			+ " VARCHAR,"
			+ KEY_STORE_ID
			+ " VARCHAR,"
			+ KEY_SR_NO
			+ " VARCHAR,"
			+  KEY_KPI_VALUE
			+ " VARCHAR)";
	
	
	
	

	public static final String CREATE_TABLE_CATEGORY_MASTER = "CREATE TABLE "
			+ TABLE_CATEGORY_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_CATEGORY_ID
			+ " VARCHAR," + KEY_CATEGORY_NAME + " VARCHAR," + KEY_CATEGORY_CODE
			+ " VARCHAR," + KEY_CATEGORY_SEQ + " INTEGER)";

	public static final String CREATE_TABLE_SOS_BRAND_CHECK = "CREATE TABLE "
			+ TABLE_SOS_BRAND_CHECK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR,"+KEY_COMAPANY_NAME
			+ " VARCHAR," + KEY_BRAND_ID + " VARCHAR)";

	public static final String CREATE_TABLE_COMPETITOR_MAPPING = "CREATE TABLE "
			+ TABLE_COMPETITOR_MAPPING
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ KEY_BRAND_ID
			+ " VARCHAR," + KEY_COMPETITOR_ID + " VARCHAR)";

	public static final String CREATE_TABLE_CATEGORY_VERTICALMAPPING = "CREATE TABLE "
			+ TABLE_CATEGORY_VERTICALMAPPING
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ KEY_CATEGORY_ID
			+ " VARCHAR,"
			+ KEY_VERTICAL_ID
			+ " VARCHAR,"
			+ KEY_CATEGORY_NAME + " VARCHAR)";

	public static final String CREATE_TABLE_POSM_MASTER = "CREATE TABLE "
			+ TABLE_POSM_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_POSM + " VARCHAR,"
			+ KEY_POSM_ID + " VARCHAR)";

	// SKU MASTER
	public static final String CREATE_TABLE_SKU_MASTER = "CREATE TABLE "
			+ TABLE_SKU_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_SKU_ID + " VARCHAR,"
			+ KEY_SKU_NAME + " VARCHAR," + KEY_BRAND_ID + " VARCHAR,"
			+ KEY_SKU_SEQUENCE + " INTEGER)";

	// COMPANY MASTER
	public static final String CREATE_TABLE_COMPANY_MASTER = "CREATE TABLE "
			+ TABLE_COMPANY_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_COMPANY_ID
			+ " VARCHAR," + KEY_COMAPANY_NAME + " VARCHAR," + KEY_COMPETITOR
			+ " VARCHAR," + KEY_NON_KEYCOMPETITOR + " VARCHAR)";

	// / SKU FOCUS
	public static final String CREATE_TABLE_SKU_FOCUS = "CREATE TABLE "
			+ TABLE_SKU_FOCUS + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_SKU_ID + " VARCHAR," + KEY_FOCUS
			+ " VARCHAR,UNIQUE(STORE_ID, SKU_ID) ON CONFLICT FAIL)";

	// / SKU TARGET
	public static final String CREATE_TABLE_SKU_TARGET = "CREATE TABLE "
			+ TABLE_SKU_TARGET + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_SKU_ID + " VARCHAR," + KEY_POSM_ID
			+ " VARCHAR)";

	// for uploading tables

	// / sod competitor
	public static final String CREATE_TABLE_SOD_DATA = "CREATE TABLE "
			+ TABLE_SOD_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_COMPANY_ID + " VARCHAR," + KEY_POSM_ID + " VARCHAR,"
			+ KEY_IMAGE_PATH1 + " VARCHAR," + KEY_IMAGE_PATH2 + " VARCHAR,"
			+ KEY_IMAGE_PATH3 + " VARCHAR," + KEY_STORE_ID + " VARCHAR,"
			+ KEY_LENGTH + " VARCHAR," + KEY_BREATH + " VARCHAR,"
			+ KEY_COMAPANY_NAME + " VARCHAR," + KEY_POSM + " VARCHAR,"
			+ KEY_HEIGHT + " VARCHAR)";
	
	
	public static final String CREATE_TABLE_DISPLAY_MASTER = "CREATE TABLE "
			+ TABLE_DISPLAY_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_COMPANY_ID + " VARCHAR," 
			 + KEY_STORE_ID + " VARCHAR,"
			+ KEY_LENGTH + " VARCHAR," + KEY_BREATH + " VARCHAR,"
			+ KEY_COMAPANY_NAME + " VARCHAR," 
			+ KEY_HEIGHT + " VARCHAR)";

	public static final String CREATE_TABLE_SOD_OTHERDATA = "CREATE TABLE "
			+ TABLE_SOD_OTHERDATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_SKU_ID + " VARCHAR," + KEY_DOM1 + " VARCHAR," + KEY_DOM2
			+ " VARCHAR," + KEY_SKU_NAME + " VARCHAR," + KEY_STOCK
			+ " VARCHAR," + KEY_STORE_ID + " VARCHAR," + KEY_DOM3 + " VARCHAR," + "VERTICAL" + " VARCHAR,"
			+ KEY_FACEUP + " VARCHAR)";
	
	public static final String CREATE_TABLE_SKU_DATA = "CREATE TABLE "
			+ TABLE_DISPLAY_SKU + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_SKU_ID + " VARCHAR," + KEY_DOM1 + " VARCHAR," + KEY_DOM2
			+ " VARCHAR," + KEY_SKU_NAME + " VARCHAR," + KEY_STOCK
			+ " VARCHAR," + KEY_STORE_ID + " VARCHAR," + KEY_DOM3 + " VARCHAR,"
			+ KEY_FACEUP + " VARCHAR)";

	public static final String CREATE_TABLE_SOD_PEPSIDATA = "CREATE TABLE "
			+ TABLE_SOD_PEPSIDATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_SOD_ID + " INTEGER,"
			+ KEY_SKU_ID + " VARCHAR," + KEY_DOM1 + " VARCHAR," + KEY_DOM2
			+ " VARCHAR," + KEY_SKU_NAME + " VARCHAR," + KEY_STOCK
			+ " VARCHAR," + KEY_STORE_ID + " VARCHAR," + KEY_DOM3 + " VARCHAR," + "VERTICAL" + " VARCHAR,"
			+ "COMPANY_NAME" + " VARCHAR,"
			+ KEY_FACEUP + " VARCHAR)";
	
	
	public static final String CREATE_TABLE_DISPLAY_TRANSCTION = "CREATE TABLE "
			+ TABLE_DISPLAY_TRANSACTION + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_SOD_ID + " INTEGER,"
			+ KEY_SKU_ID + " INTEGER," + KEY_DOM1 + " VARCHAR," + KEY_DOM2
			+ " VARCHAR," + KEY_SKU_NAME + " VARCHAR," + KEY_STOCK
			+ " VARCHAR," + KEY_STORE_ID + " INTEGER," + KEY_DOM3 + " VARCHAR,"
			+ KEY_FACEUP + " INTEGER,"
					+ KEY_ASSETS + " VARCHAR," + KEY_ASSETS_ID + " INTEGER,"
					 + KEY_REMARKS + " VARCHAR,"
					+ KEY_AVAILABLE + " VARCHAR,"+KEY_VERTICAL_ID + " VARCHAR,"
					+ KEY_ASSET_PURE_DOWNLOAD + " VARCHAR," 
					+ KEY_VERTICAL_NAME + " VARCHAR," 
					+ KEY_ASSET_PURE + " VARCHAR,"
					+ KEY_IMAGE_PATH1 + " VARCHAR,"
						+ KEY_LENGTH + " VARCHAR," 
					+ KEY_BREATH + " VARCHAR,"
					+ KEY_HEIGHT + " VARCHAR,"
					+ KEY_RANDOM + " VARCHAR,"
					+ KEY_BRAND_ID + " VARCHAR,"
					+ KEY_SECONDARY + " VARCHAR,"
					+ KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3 + " VARCHAR)";
	

	public static final String CREATE_TABLE_ASSET_DATA = "CREATE TABLE "
			+ TABLE_INSERT_ASSET_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_ASSETS + " VARCHAR," + KEY_ASSETS_ID + " INTEGER,"
			+ KEY_STORE_ID + " INTEGER," + KEY_REMARKS + " VARCHAR,"
			+ KEY_AVAILABLE + " VARCHAR,"+KEY_VERTICAL_ID + " VARCHAR,"
			+ KEY_ASSET_PURE_DOWNLOAD + " VARCHAR," 
			+ KEY_VERTICAL_NAME + " VARCHAR," 
			+ KEY_ASSET_PURE + " VARCHAR,"
			+ KEY_IMAGE_PATH1 + " VARCHAR,"
			+ KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3 + " VARCHAR)";
	
	public static final String CREATE_TABLE_DISPLAY_TEMPDATA = "CREATE TABLE "
			+ TABLE_INSERT_DISPLAY_TEMPDATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
			+ KEY_ASSETS + " VARCHAR," + KEY_ASSETS_ID + " INTEGER,"
			+ KEY_STORE_ID + " VARCHAR," + KEY_REMARKS + " VARCHAR,"
			+ KEY_AVAILABLE + " VARCHAR,"+KEY_VERTICAL_ID + " VARCHAR,"
			+ KEY_ASSET_PURE_DOWNLOAD + " VARCHAR," 
			+ KEY_VERTICAL_NAME + " VARCHAR," 
			+ KEY_ASSET_PURE + " VARCHAR,"
			+ KEY_IMAGE_PATH1 + " VARCHAR,"
			+ KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3 + " VARCHAR)";

	// Upload Image
	public static final String METHOD_Get_DR_POSM_THEME_IMAGES = "GetImagePepsico";
	public static final String SOAP_ACTION_Get_DR_POSM_THEME_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_POSM_THEME_IMAGES;

	public static final String METHOD_Get_DR_PROMOTION_IMAGES = "GetImagePromotionActivity";
	public static final String SOAP_ACTION_Get_DR_PROMOTION_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_PROMOTION_IMAGES;

	public static final String METHOD_Get_DR_SOD_IMAGES = "GetImagePepsicoSOD";
	public static final String SOAP_ACTION_Get_DR_SOD_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_SOD_IMAGES;

	// Geo tag
	public static final String CREATE_TABLE_GEO_TAG_CITY = "CREATE TABLE "
			+ TABLE_GEOTAG_CITY + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + "CITY_ID" + " VARCHAR,"
			+ "CITY" + " VARCHAR)";

	public static final String CREATE_TABLE_GEO_TAG_MAPPING = "CREATE TABLE "
			+ TABLE_GEO_TAG_MAPPING + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_STORE_NAME + " VARCHAR," + KEY_ADDRESS
			+ " VARCHAR," + "CITY_ID" + " VARCHAR," + "STORE_TYPE_ID"
			+ " VARCHAR," + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE
			+ " VARCHAR," + KEY_STATUS + " VARCHAR)";

	public static final String CREATE_TABLE_INSERT_GEOTAG = "CREATE TABLE "
			+ TABLE_INSERT_GEO_TAG + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " INTEGER," + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE
			+ " VARCHAR," + KEY_STATUS + " VARCHAR," + KEY_IMAGE_PATH1
			+ " VARCHAR," + KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3
			+ " VARCHAR)";
}
