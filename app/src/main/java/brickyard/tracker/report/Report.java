package brickyard.tracker.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import brickyard.tracker.Dashboard;
import brickyard.tracker.R;

public class Report extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            try {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.report_activity);
                setTitle("Report");

                /*MobileAds.initialize(this, getString(R.string.admob_app_id));
                AdView mAdView = findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);*/

                TableRow report1 = (TableRow) findViewById(R.id.tablerow_datewise_search);
                report1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), SearchDatewiseTransaction.class);
                        startActivity(intent);
                    }
                });

                TableRow report2 = (TableRow) findViewById(R.id.tablerow_monthwise_search);
                report2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), SearchMonthlyTransaction.class);
                        startActivity(intent);
                    }
                });

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
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
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        }
}
