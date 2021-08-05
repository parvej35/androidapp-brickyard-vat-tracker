package brickyard.tracker.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import brickyard.tracker.Dashboard;
import brickyard.tracker.R;
import brickyard.tracker.util.Constant;

public class Setting extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.setting_activity);
            setTitle(getString(R.string.setting));

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            LinearLayout incomeCategory = (LinearLayout) findViewById(R.id.layout_incomeCategory);
            incomeCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AddCategory.class);
                    intent.putExtra("recordType", String.valueOf(Constant.TYPE_INC));
                    startActivity(intent);
                }
            });

            LinearLayout expenseCategory = (LinearLayout) findViewById(R.id.layout_expenseCategory);
            expenseCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AddCategory.class);
                    intent.putExtra("recordType", String.valueOf(Constant.TYPE_EXP));
                    startActivity(intent);
                }
            });

            /*LinearLayout layoutGeneral = (LinearLayout) findViewById(R.id.layout_general);
            layoutGeneral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, General.class);
                    startActivity(intent);
                }
            });*/

            /*LinearLayout layoutDeleteAll = (LinearLayout) findViewById(R.id.layout_deleteAll);
            layoutDeleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DeleteAllRecord.class);
                    startActivity(intent);
                }
            });*/

            LinearLayout layoutChangepass = (LinearLayout) findViewById(R.id.layout_updatepass);
            layoutChangepass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdatePassword.class);
                    startActivity(intent);
                }
            });

            /*LinearLayout layoutVersion = (LinearLayout) findViewById(account.manager.R.id.layout_version);
            layoutVersion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showVersionInformation();
                }
            });*/

            LinearLayout layoutDatabackup = (LinearLayout) findViewById(R.id.layout_databackup);
            layoutDatabackup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder
                        .setMessage("Data Backup & Restore feature will be available on upcoming release.")
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                    // create an alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });


        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
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
        Intent i = new Intent(context, Dashboard.class);
        startActivity(i);
    }

    private void showVersionInformation() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.version_info_prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setCancelable(false)
        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        // create an alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}