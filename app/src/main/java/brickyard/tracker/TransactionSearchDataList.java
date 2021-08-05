package brickyard.tracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import brickyard.tracker.adapter.TransactionReportAdapter;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.DbHelper;

public class TransactionSearchDataList extends AppCompatActivity {

    ListView listView;
    ProgressDialog mProgressBar;
    List<RecordBean> recordBeanList;
    Context context = this;
    DbHelper dbHelper;
    String currencySymbol;

    String sector = "";
    long longFromDate = 0L;
    long longToDate = 0L;
    int transactionType = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setTitle("Transaction List");
            setContentView(R.layout.transaction_search_report);

            /*MobileAds.initialize(getApplicationContext(), getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(context);
            mProgressBar = new ProgressDialog(context);
            listView = (ListView) findViewById(R.id.transaction_list);

            //Toast.makeText(context, "111111", Toast.LENGTH_LONG).show();

            sector = getIntent().getStringExtra("sector");
            longFromDate = Long.parseLong(getIntent().getStringExtra("longFromDate"));
            longToDate = Long.parseLong(getIntent().getStringExtra("longToDate"));
            transactionType = Integer.parseInt(getIntent().getStringExtra("transactionType"));

            //Toast.makeText(context, transactionType+" * "+sector+" * "+longFromDate+" * "+longToDate, Toast.LENGTH_LONG).show();

            currencySymbol = dbHelper.getApplicationUserCurrencySymbol(context);

            recordBeanList = dbHelper.getTransListByTypeSectorAndDateRange(context, transactionType, sector, longFromDate, longToDate);

            startLoadData();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View view, int position, long id) {
                    RecordBean recordBean = (RecordBean) listView.getItemAtPosition(position);

                    Intent intent = new Intent(context, TransactionDetails.class);
                    intent.putExtra("recordObj", recordBean);
                    intent.putExtra("sector", sector);
                    intent.putExtra("transactionType", String.valueOf(transactionType));
                    intent.putExtra("longFromDate", String.valueOf(longFromDate));
                    intent.putExtra("longToDate", String.valueOf(longToDate));

                    startActivity(intent);
                }
            });

        } catch (Exception ex) {
            Toast.makeText(context, "Ex : " +ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Loading...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new TransactionSearchDataList.LoadDataTask().execute(0);

    }

    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            loadData();
            mProgressBar.hide();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }

    private void loadData() {
        TransactionReportAdapter adapter = new TransactionReportAdapter(context, recordBeanList, currencySymbol);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_back:
                this.onBackPressed();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(context, SearchTransaction.class);
        startActivity(i);
    }
}