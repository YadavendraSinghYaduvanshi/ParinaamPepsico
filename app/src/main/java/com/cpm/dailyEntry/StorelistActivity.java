package com.cpm.dailyEntry;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.StoreStatusUploadService;

public class StorelistActivity extends ListActivity implements OnClickListener {

    private Intent intent;
    private static ArrayList<VericalBrandBean> sku_listforfood = new ArrayList<VericalBrandBean>();
    private static ArrayList<VericalBrandBean> sku_listforbev = new ArrayList<VericalBrandBean>();
    private Cursor cursor;
    private PepsicoDatabase database;
    private SharedPreferences preferences;
    private String date;
    private ArrayList<StoreBean> list = new ArrayList<StoreBean>();
    private SharedPreferences.Editor editor = null;
    boolean leave;
    StoreBean storestatus = new StoreBean();
    private String store_id, store_address, store_name, visit_date, username,
            store_latitude, store_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storename);

        database = new PepsicoDatabase(this);
        database.open();


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString.KEY_DATE, null);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
        store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
        visit_date = preferences.getString(CommonString.KEY_DATE, "");
        username = preferences.getString(CommonString.KEY_USERNAME, "");

        filldata();

        list = database.getStoreData(date);

        setListAdapter(new MyAdapter());

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.storeviewlist, null);

                holder.storename = (TextView) convertView
                        .findViewById(R.id.storelistviewxml_storename);
                holder.storeaddress = (TextView) convertView
                        .findViewById(R.id.storelistviewxml_storeaddress);

                holder.imgtick = (ImageView) convertView
                        .findViewById(R.id.storelistviewxml_storeico);

                holder.checkout = (Button) convertView
                        .findViewById(R.id.chkout);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if ((list.get(position).getCheckout_status()
                    .equals(CommonString.KEY_C))) {

                holder.checkout.setBackgroundResource(R.drawable.tick_c);
                holder.checkout.setEnabled(false);
                holder.checkout.setVisibility(View.VISIBLE);

            } else {
                if ((list.get(position).getCheckout_status()
                        .equals(CommonString.KEY_VALID))) {
                    holder.checkout.setBackgroundResource(R.drawable.checkout);
                    holder.checkout.setVisibility(View.VISIBLE);
                    holder.checkout.setEnabled(true);

                } else {
                    holder.checkout.setEnabled(false);
                    holder.checkout.setVisibility(View.INVISIBLE);

                }
            }

            if (list.get(position).getStatus().equals(CommonString.KEY_U)) {

                if ((list.get(position).getCheckout_status()
                        .equalsIgnoreCase(CommonString.KEY_L))) {

                    holder.checkout.setVisibility(View.INVISIBLE);
                    holder.imgtick.setBackgroundResource(R.drawable.leave_tick);

                } else {

                    holder.imgtick.setBackgroundResource(R.drawable.tick_u);
                }

            } else if ((list.get(position).getStatus()
                    .equals(CommonString.KEY_D))) {

                holder.imgtick.setBackgroundResource(R.drawable.tick_d);

            } else if ((list.get(position).getStatus()
                    .equals(CommonString.KEY_P))) {

                holder.imgtick.setBackgroundResource(R.drawable.tick_p);

            } else if ((list.get(position).getStatus()
                    .equals(CommonString.STORE_STATUS_LEAVE))) {
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.imgtick.setBackgroundResource(R.drawable.tickl);
            } else if (validateCoverage(list.get(position).getStoreid())) {

                if (leave)

                    holder.imgtick.setBackgroundResource(R.drawable.tickl);
                else

                    holder.imgtick.setBackgroundResource(R.drawable.tickgreenv);
            } else {

                holder.imgtick.setBackgroundResource(R.drawable.storeico);

            }

            holder.storename.setText(list.get(position).getStorename());
            holder.storeaddress.setText(list.get(position).getStoreaddress());

            holder.checkout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent intent = new Intent(
                            "android.location.GPS_ENABLED_CHANGE");
                    intent.putExtra("enabled", true);
                    //	sendBroadcast(intent);

                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            StorelistActivity.this);
                    builder.setMessage("Are you sure you want to Checkout")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            if (CheckNetAvailability()) {

                                                editor = preferences.edit();

                                                editor.putString(
                                                        CommonString.KEY_STORE_ID,
                                                        list.get(position)
                                                                .getStoreid());
                                                editor.putString(
                                                        CommonString.KEY_STORE_NAME,
                                                        list.get(position)
                                                                .getStorename());

                                                editor.commit();

                                                Intent i = new Intent(
                                                        StorelistActivity.this,
                                                        CheckOutStoreActivity.class);
                                                startActivity(i);
                                            }

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

            return convertView;
        }

        private class ViewHolder {
            TextView storename, storeaddress;
            ImageView imgtick;
            Button checkout;
        }

        public boolean CheckNetAvailability() {

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                    || connectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                // we are connected to a network
                connected = true;
            }
            return connected;
        }

        public boolean validateCoverage(String storeid) {
            boolean result = false;

            if (database.CheckMid(date, storeid) > 0) {
                result = true;

                if (database.CheckMidWithStatus(date, storeid)
                        .equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE)) {
                    leave = true;
                    database.updateStoreStatusOnLeave(storeid, date,
                            CommonString.STORE_STATUS_LEAVE);
                    database.updateStoreStatusOnCheckout(storeid, date,
                            CommonString.KEY_L);
                } else {
                    leave = false;

                }

            }

            return result;

        }
    }

    private void filldata() {

        ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
        coverageBeanlist = database.getCoverageData(date, null);


        for (int i = 0; i < coverageBeanlist.size(); i++) {

            boolean sos = false, sod = false, assest = false, promotion = false;
            storestatus = database.getStoreStatus(coverageBeanlist.get(i)
                    .getStoreId());

            if ((storestatus.getCheckout_status().equalsIgnoreCase(
                    CommonString.KEY_L) || storestatus.getCheckout_status()
                    .equalsIgnoreCase(CommonString.KEY_C))) {

            } else {

                if (database.getAssets(coverageBeanlist.get(i).getStoreId())
                        .size() > 0) {

                    if ((database.getDisplayTransactionData_allAssetsFORVALIDATIAON(coverageBeanlist.get(i).getStoreId()).size()) == (database.getAssets(coverageBeanlist.get(i).getStoreId()).size())) {

                        assest = true;

                    }
                } else {
                    assest = true;
                }

                if (database.getSodDataMid(
                        coverageBeanlist.get(i).getStoreId(),
                        coverageBeanlist.get(i).getMID()).size() > 0) {

                    sod = true;


                }
                /*if (database.getBrands(coverageBeanlist.get(i).getStoreId())/2 <= database
						.getInsertedBrands(coverageBeanlist.get(i).getStoreId())) {

					sos = true;
				}*/

                if (database.getSkuFocusData_forCounting(coverageBeanlist.get(i).getStoreId()) <= database.getallinteredSOSrecord(coverageBeanlist.get(i).getStoreId())) {


                    sos = true;
                } else {
                    sos = false;
                }
				
				
				
				
				

				/*if (database.gePromotionOtherMid(
						coverageBeanlist.get(i).getMID()).size() > 0) {

					promotion = true;
				}*/


                if (database.gePromotionDownloaded(
                        coverageBeanlist.get(i).getStoreId()).size() > 0) {

                    if (database.gePromotionPepsiData(
                            coverageBeanlist.get(i).getMID()).size() > 0)

                    {

                        promotion = true;
                    }

                } else {
                    promotion = true;
                }

                boolean flagCompetitor = false;
                int count;
                int count2;
                int count3;
                StoreBean staus = database.getStoreWeek(store_id);
                if (staus.getWeekstatus().toString().equalsIgnoreCase("N")) {


                    sku_listforfood = database.getSkuMappingData121_forbevrages(coverageBeanlist.get(i).getStoreId());


                    sku_listforbev = database.getSkuMappingData121_forFood(coverageBeanlist.get(i).getStoreId());


                    int size = sku_listforbev.size() + sku_listforfood.size();

                    if (database.getPepsiDataOOS(coverageBeanlist.get(i).getStoreId(), visit_date).size() >= size) {
                        //validation for osse bev
                        flagCompetitor = true;

                    } else {
                        flagCompetitor = false;
                    }


                } else {


                    // count2 = database.getComptetionRecordfromAdditional();
					
					
				
				/*	if(count2>0)
					{
						flagCompetitor=true;
						
					}*/


                    int downloadcountforfood, downloadcountforbev;
                    //boolean flagCompetitor;

                    downloadcountforbev = database.getComptetionRecordfromAdditionalBeveragesDownloadedData(store_id);
                    if (downloadcountforbev > 0) {
                        count2 = database.getComptetionRecordfromAdditionalBeverages();
                    } else {
                        count2 = 4;
                    }

                    downloadcountforfood = database.getComptetionRecordfromAdditionalFOODDownloadedData(store_id);
                    if (downloadcountforfood > 0) {
                        count3 = database.getComptetionRecordfromAdditionalFood();

                    } else {
                        count3 = 3;

                    }


                    // count2 = database.getComptetionRecordfromAdditionalBeverages();

                    // count3 = database.getComptetionRecordfromAdditionalFood();

                    if (count2 > 0 && count3 > 0) {
                        flagCompetitor = true;

                    } else {
                        flagCompetitor = false;

                    }

                }
			
				
				
				/*sos=true;
				sod=true;
				
				
				assest=true;
				//promotion=true;
*/


                if (staus.getWeekstatus().toString().equalsIgnoreCase("y")) {

                    //have to open below commented line
				/*	database.updateStoreStatusOnCheckout(coverageBeanlist
							.get(i).getStoreId(), date, CommonString.KEY_VALID);*/

                    if (sos && sod && promotion && assest && flagCompetitor) {

                        database.updateStoreStatusOnCheckout(coverageBeanlist
                                .get(i).getStoreId(), date, CommonString.KEY_VALID);
                    }

                } else

                {
                    if (flagCompetitor == true) {
                        database.updateStoreStatusOnCheckout(coverageBeanlist
                                .get(i).getStoreId(), date, CommonString.KEY_VALID);


                    }

                }


            }
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        list = database.getStoreData(date);
        StoreBean sb = list.get(position);

        if ((sb.getStatus().equals(CommonString.KEY_U))) {

            if (((sb.getCheckout_status().equalsIgnoreCase(CommonString.KEY_L)))) {

                showToast(AlertMessage.MESSAGE_LEAVE_UPLOAD);
            } else {

                showToast(AlertMessage.MESSAGE_UPLOAD);
            }

        } else if (((sb.getCheckout_status().equals(CommonString.KEY_C)))) {

            showToast(AlertMessage.MESSAGE_CHECKOUT_UPLOAD);
        } else if ((sb.getStatus().equals(CommonString.KEY_D))) {

            showToast(AlertMessage.MESSAGE_DATA_UPLOAD);

        } else if (((sb.getStatus().equals(CommonString.STORE_STATUS_LEAVE)))) {

            showToast(AlertMessage.MESSAGE_LEAVE);
        } else {

            if (preferences.getString(CommonString.KEY_STOREVISITED_STATUS,
                    "no").equalsIgnoreCase("yes")) {

                if (preferences.getString(CommonString.KEY_STOREVISITED, "")
                        .equalsIgnoreCase(sb.getStoreid())) {

                    editor = preferences.edit();
                    editor.putString(CommonString.KEY_STORE_ID, sb.getStoreid());
                    editor.putString(CommonString.KEY_STORE_NAME,
                            sb.getStorename());
                    editor.putString(CommonString.KEY_VISIT_DATE,
                            sb.getVisitdate());

                    editor.putString(CommonString.KEY_STOREVISITED,
                            sb.getStoreid());
                    editor.putString(CommonString.KEY_STOREVISITED_STATUS,
                            "yes");

                    editor.commit();

                    intent = new Intent(getBaseContext(),
                            StoreVisitedActivity.class);

                    startActivity(intent);
                    this.finish();

                } else {
                    Toast.makeText(
                            StorelistActivity.this,
                            "You Cant Checkin,First Checkout the Previous Store",
                            Toast.LENGTH_SHORT).show();
                }

            } else {

                // PUT IN PREFERENCES
                editor = preferences.edit();
                editor.putString(CommonString.KEY_STORE_ID, sb.getStoreid());
                editor.putString(CommonString.KEY_STORE_NAME, sb.getStorename());
                editor.putString(CommonString.KEY_VISIT_DATE, sb.getVisitdate());
                editor.putString(CommonString.KEY_STOREVISITED, sb.getStoreid());
                editor.putString(CommonString.KEY_STOREVISITED_STATUS, "yes");
                editor.commit();

                intent = new Intent(getBaseContext(),
                        StoreVisitedActivity.class);

                startActivity(intent);
                this.finish();
            }
        }

    }

    public void onBackClick(View v) {

        Intent i = new Intent(this,
                com.cpm.parinaampepsico.MainMenuActivity.class);
        startActivity(i);
        StorelistActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this,
                com.cpm.parinaampepsico.MainMenuActivity.class);
        startActivity(i);
        StorelistActivity.this.finish();
    }

    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}
