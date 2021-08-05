package brickyard.tracker.setting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import brickyard.tracker.R;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class DeleteAllRecord extends AppCompatActivity {

    Button btnDeleteAll, btnCancel;
    DbHelper dbHelper;

    ProgressDialog mProgressBar;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.delete_all_activity);
            setTitle("Delete Records");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(context);
            mProgressBar = new ProgressDialog(context);

            btnDeleteAll=(Button) findViewById(R.id.btnDeleteAll);
            btnDeleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startLoadData();
                }
            });


            btnCancel=(Button) findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Deleting ...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new DeleteAllRecord.LoadDataTask().execute(0);

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
            dbHelper.truncateAllTransactionRecordAndCategoryInfo(context);
            dbHelper.saveDefaultCategory(context, Constant.TYPE_INC, Constant.DEFAULT_INC_CATEGORY);
            dbHelper.saveDefaultCategory(context, Constant.TYPE_EXP, Constant.DEFAULT_EXP_CATEGORY);

            mProgressBar.hide();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder
                .setMessage("All existing records have been deleted.")
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dialog.cancel();
                        onBackPressed();
                    }
                });

            // create an alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
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
        Intent i = new Intent(getApplicationContext(), Setting.class);
        startActivity(i);
    }
}
