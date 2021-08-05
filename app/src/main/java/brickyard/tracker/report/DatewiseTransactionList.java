package brickyard.tracker.report;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

import brickyard.tracker.R;
import brickyard.tracker.adapter.DatewiseTranReportAdapter;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class DatewiseTransactionList extends AppCompatActivity {

    ProgressDialog mProgressBar;
    List<RecordBean> recordBeanList;
    ListView listView;
    Context context = this;
    DbHelper dbHelper;
    String currencySymbol;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_search_datewise_report);
            setTitle("Transaction List");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            mProgressBar = new ProgressDialog(context);

            dbHelper = new DbHelper(context);
            listView = (ListView) findViewById(R.id.datewise_transaction_list);

            String strFromDate = getIntent().getStringExtra("strFromDate");
            String dateArr[] = strFromDate.split("-");
            String customFromDate = dateArr[2] +"-"+Constant.getShortMonthNameFromNumber(dateArr[1])+"-"+dateArr[0];

            String strToDate = getIntent().getStringExtra("strToDate");
            dateArr = strToDate.split("-");
            String customToDate = dateArr[2] +"-"+Constant.getShortMonthNameFromNumber(dateArr[1])+"-"+dateArr[0];

            TextView description = (TextView) findViewById(R.id.description);
            //description.setText("Transactions from "+customFromDate+" to " +customToDate);
            description.setText("From  "+customFromDate+"  To  " +customToDate);

            String type = getIntent().getStringExtra("type");
            String sector = getIntent().getStringExtra("sector");

            TextView typeTextView = (TextView) findViewById(R.id.type);
            if(type.equalsIgnoreCase(Constant.BOTH_INC_AND_EXP)){
                typeTextView.setText("(All Transactions)");
            } else if(type.equalsIgnoreCase(Constant.STR_INCOME) && sector.equalsIgnoreCase(Constant.ALL_SECTOR)){
                typeTextView.setText("(Incomes from all sectors)");
            } else if(type.equalsIgnoreCase(Constant.STR_EXPENSE) && sector.equalsIgnoreCase(Constant.ALL_SECTOR)){
                typeTextView.setText("(Expenses at all sectors)");
            } else {
                typeTextView.setText(type+" Sector : "+sector);
            }

            int transactionType = 0;
            if(type.equalsIgnoreCase(Constant.STR_INCOME)){
                transactionType = Constant.TYPE_INC;
            } else if(type.equalsIgnoreCase(Constant.STR_EXPENSE)){
                transactionType = Constant.TYPE_EXP;
            }

            long fromDate = Long.parseLong(getIntent().getStringExtra("fromDate"));
            long toDate = Long.parseLong(getIntent().getStringExtra("toDate"));

            double totalIncAmount = dbHelper.getTotalAmountByTypeSectorAndDateRange(context, Constant.TYPE_INC, sector, fromDate, toDate);
            double totalExpAmount = dbHelper.getTotalAmountByTypeSectorAndDateRange(context, Constant.TYPE_EXP, sector, fromDate, toDate);
            double balanceAmount = totalIncAmount - totalExpAmount;

            currencySymbol = dbHelper.getApplicationUserCurrencySymbol(context);

            TextView txtIncome = (TextView) findViewById(R.id.totalIncome);
            txtIncome.setText(currencySymbol +" "+ String.format(Locale.US, "%.2f", (totalIncAmount)));

            TextView txtExpense = (TextView) findViewById(R.id.totalExpense);
            txtExpense.setText(currencySymbol +" "+ String.format(Locale.US, "%.2f", (totalExpAmount)));

            TextView txtBalance = (TextView) findViewById(R.id.balance);
            txtBalance.setText(currencySymbol +" "+ String.format(Locale.US, "%.2f", (balanceAmount)));

            recordBeanList = dbHelper.getTransListByTypeSectorAndDateRange(context, transactionType, sector, fromDate, toDate);

            startLoadData();

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Loading...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new DatewiseTransactionList.LoadDataTask().execute(0);

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
        DatewiseTranReportAdapter adapter = new DatewiseTranReportAdapter(context, recordBeanList, currencySymbol);
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
        Intent i = new Intent(context, SearchDatewiseTransaction.class);
        startActivity(i);
    }
}
