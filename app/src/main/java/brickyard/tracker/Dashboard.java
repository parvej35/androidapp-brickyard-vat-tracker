package brickyard.tracker;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import brickyard.tracker.adapter.BrickyardListAdapter;
import brickyard.tracker.bean.BrickyardBean;
import brickyard.tracker.bean.UserProfileBean;
import brickyard.tracker.report.SearchDatewiseTransaction;
import brickyard.tracker.setting.General;
import brickyard.tracker.setting.Permissions;
import brickyard.tracker.setting.RestoreDB;
import brickyard.tracker.setting.UpdatePassword;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;

    DbHelper dbHelper;
    UserProfileBean userProfile;

    ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_activity);
            setTitle("Dashboard");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            dbHelper = new DbHelper(getApplicationContext());
            userProfile = dbHelper.getApplicationUserProfile(getApplicationContext());

            mProgressBar = new ProgressDialog(this);

            List<BrickyardBean> brickyardBeanList = dbHelper.getAllBrickYardList(getApplicationContext(), 10);

            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new BrickyardListAdapter(getApplicationContext(), brickyardBeanList));

            LinearLayout incomeLayout = (LinearLayout) findViewById(R.id.layout_add_record);
            incomeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddRecord.class);
                    startActivity(intent);
                }
            });

            /*LinearLayout layout_search = (LinearLayout) findViewById(R.id.layout_search);
            layout_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SearchTransaction.class);
                    startActivity(intent);
                }
            });

            LinearLayout layout_report = (LinearLayout) findViewById(R.id.layout_report);
            layout_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SearchDatewiseTransaction.class);
                    startActivity(intent);
                }
            });*/

            startLoadData();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Loading...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new Dashboard.LoadDataTask().execute(0);
    }

    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            mProgressBar.hide();
            //loadData();
        }
        @Override
        protected void onPreExecute() { }

        @Override
        protected void onProgressUpdate(Integer... values) { }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();

            if (doubleBackToExitPressedOnce) {
                ActivityCompat.finishAffinity(Dashboard.this);
            }

            doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press once again to exit", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);//'main' is the xml file name

        if (userProfile.getEnablePassword().equals("") || userProfile.getEnablePassword().equals("0")) {
            menu.findItem(R.id.action_logout).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_close) {
            dbHelper.updateApplicationUserSignout(getApplicationContext(), "0");
            ActivityCompat.finishAffinity(Dashboard.this);
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if (id == R.id.nav_backup) {
            performBackup();
        } else if (id == R.id.nav_restore) {
            Intent intent = new Intent(getApplicationContext(), RestoreDB.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(getApplicationContext(), General.class);
            startActivity(intent);
        } else if (id == R.id.nav_password) {
            Intent intent = new Intent(getApplicationContext(), UpdatePassword.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            new PlayStoreLink().shareApp(this);
        } else if (id == R.id.nav_rate) {
            new PlayStoreLink().rateApp(this);
        } else if (id == R.id.nav_moreapp) {
            new PlayStoreLink().moreApps(this);
        } else if (id == R.id.nav_income) {
            Intent intent = new Intent(getApplicationContext(), AddIncome.class);
            //intent.putExtra("recordType", String.valueOf(Constant.TYPE_INC));
            startActivity(intent);
        } else if (id == R.id.nav_expense) {
            Intent intent = new Intent(getApplicationContext(), AddExpense.class);
            //intent.putExtra("recordType", String.valueOf(Constant.TYPE_EXP));
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            new PlayStoreLink().rateApp(this);
        } else if (id == R.id.nav_version) {
            new PlayStoreLink().rateApp(this);
        } else if (id == R.id.nav_policy) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_and_policy_link)));
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            new PlayStoreLink().moreApps(this);
        } /*else if (id == R.id.nav_more) {
            new PlayStoreLink().moreApps(this);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void performBackup() {
        try {
            boolean hasPermission = Permissions.verifyStoragePermissions(Dashboard.this);
            if(hasPermission) {
                final Calendar calendar = Calendar.getInstance();

                //File folder = new File(Environment.getExternalStorageDirectory() + File.separator + Constant.MONEY_TRACKER_DIR);
                File folder = new File(Environment.getExternalStorageDirectory() + File.separator + Constant.MONEY_TRACKER_DIR);

                boolean success = true;
                if (!folder.exists()) success = folder.mkdirs();

                if (success) {
                    String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                    if (day.length() == 1) day = "0" + day;

                    String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                    if (hour.length() == 1) hour = "0" + hour;

                    String minute = String.valueOf(calendar.get(Calendar.MINUTE));
                    if (minute.length() == 1) minute = "0" + minute;

                    String sec = String.valueOf(calendar.get(Calendar.SECOND));
                    if (sec.length() == 1) sec = "0" + sec;

                    final String backup_file_name = getApplicationContext().getResources().getString(R.string.db_backup_file_name)
                            + "_" + calendar.get(Calendar.YEAR) + "_" + Constant.getShortMonthNameFromMonthNumber(calendar.get(Calendar.MONTH) + 1) + "_" + day
                            + "_" + hour + ":" + minute + ":" + sec;

                    // setup the alert builder
                    LayoutInflater layoutInflater = LayoutInflater.from(Dashboard.this);
                    View promptView = layoutInflater.inflate(R.layout.db_backup_file_prompt, null);

                    final EditText input = (EditText) promptView.findViewById(R.id.name);
                    input.setText(backup_file_name);
                    //input.setInputType(InputType.TYPE_NULL);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Dashboard.this);
                    alertDialogBuilder.setView(promptView);

                    // setup a dialog window
                    alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Backup", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //String file_name = directoryName + backup_file_name + Constant.DB_EXTENSION;
                                String file_name = Constant.APP_DIRECTORY + backup_file_name + Constant.DB_EXTENSION;

                                boolean isBackupSuccess = dbHelper.backupDatabase(Dashboard.this, file_name);
                                if (isBackupSuccess) {
                                    Toast.makeText(getApplicationContext(), "Backup successfully completed", Toast.LENGTH_LONG).show();
                                } else {
                                    //Toast.makeText(getApplicationContext(), "Failed to backup database", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                    // create an alert dialog
                    AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();

                    alertD.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                    alertD.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);

                } else {
                    Toast.makeText(getApplicationContext(), "Unable to create DB backup directory.", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
