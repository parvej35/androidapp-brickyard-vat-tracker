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

import java.util.Locale;
import java.util.Map;

import brickyard.tracker.R;
import brickyard.tracker.adapter.MonthlyTranReportAdapter;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class MonthlyTransactionList extends AppCompatActivity {

    ProgressDialog mProgressBar;
    //List<RecordBean> recordBeanList;
    ListView listView;
    Context context = this;
    DbHelper dbHelper;
    Map<String, String> monthlyIncTransList, monthlyExpTransList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_search_monthly_report);

            setTitle("Monthly Transactions");

            mProgressBar = new ProgressDialog(context);

            dbHelper = new DbHelper(context);
            listView = (ListView) findViewById(R.id.monthly_transaction_list);

            int selectedYear = Integer.parseInt(getIntent().getStringExtra("selectedYear"));
            int selectedMonth1 = Integer.parseInt(getIntent().getStringExtra("selectedMonth1"));
            int selectedMonth2 = Integer.parseInt(getIntent().getStringExtra("selectedMonth2"));

            TextView description = (TextView) findViewById(R.id.description);
            description.setText("From "+selectedMonth1+" To " +selectedMonth2 + " in the year " + selectedYear);

            double totalIncAmount = dbHelper.getTotalAmountByTypeYearAndMonth(context, Constant.TYPE_INC, selectedYear, selectedMonth1, selectedMonth2);
            double totalExpAmount = dbHelper.getTotalAmountByTypeYearAndMonth(context, Constant.TYPE_EXP, selectedYear, selectedMonth1, selectedMonth2);
            double balanceAmount = totalIncAmount - totalExpAmount;

            TextView txtIncome = (TextView) findViewById(R.id.totalIncome);
            txtIncome.setText(String.format(Locale.US, "%.2f", (totalIncAmount)));

            TextView txtExpense = (TextView) findViewById(R.id.totalExpense);
            txtExpense.setText(String.format(Locale.US, "%.2f", (totalExpAmount)));

            TextView txtBalance = (TextView) findViewById(R.id.balance);
            txtBalance.setText(String.format(Locale.US, "%.2f", (balanceAmount)));

            monthlyIncTransList = dbHelper.getTransListByYearAndMonth(context, Constant.TYPE_INC, selectedYear, selectedMonth1, selectedMonth2);
            monthlyExpTransList = dbHelper.getTransListByYearAndMonth(context, Constant.TYPE_EXP, selectedYear, selectedMonth1, selectedMonth2);

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
        new MonthlyTransactionList.LoadDataTask().execute(0);
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
        MonthlyTranReportAdapter adapter = new MonthlyTranReportAdapter(context, monthlyIncTransList, monthlyExpTransList);
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
        Intent i = new Intent(context, SearchMonthlyTransaction.class);
        startActivity(i);
    }
}
