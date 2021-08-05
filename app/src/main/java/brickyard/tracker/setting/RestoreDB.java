package brickyard.tracker.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import brickyard.tracker.Dashboard;
import brickyard.tracker.R;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class RestoreDB extends AppCompatActivity {

    ListView listView;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setTitle("Restore Database");
            setContentView(R.layout.db_backup_file_activity);

            dbHelper = new DbHelper(getApplicationContext());
            listView = (ListView) findViewById(R.id.listView);

            boolean hasPermission = Permissions.verifyStoragePermissions(RestoreDB.this);
            if(hasPermission) {

                final File folder = new File(Environment.getExternalStorageDirectory() + File.separator + Constant.MONEY_TRACKER_DIR);

                if (folder.exists()) {

                    List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

                    final File[] files = folder.listFiles();
                    List<String> fileNameList = new ArrayList<>();
                    for (File file : files) {
                        String fileName = file.getName();
                        fileName = fileName.replace(Constant.DB_EXTENSION, "");

                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("db_old_backup_file", fileName);

                        aList.add(hm);

                        fileNameList.add(fileName);
                    }

                    String[] from = {"db_old_backup_file"};
                    int[] to = {R.id.fileNameTextView};

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.db_backup_file_list, from, to);
                    listView.setAdapter(simpleAdapter);

                } else {
                    Toast.makeText(getApplicationContext(), "Backup folder not present.\nDo a backup before a restore!", Toast.LENGTH_LONG).show();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View view, int position, long id) {
                        try {
                            HashMap<String, String> mapData = (HashMap<String, String>) listView.getItemAtPosition(position);

                            restoreDatabase(Constant.APP_DIRECTORY + mapData.get("db_old_backup_file") + Constant.DB_EXTENSION);
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

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

    private void restoreDatabase(final String old_backup_db_file){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RestoreDB.this);
        alertDialogBuilder
        .setIcon(android.R.drawable.ic_delete)
        .setTitle("Restoring old database").setCancelable(false)
        .setMessage("Are you sure to restore selected old database?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {

                    File file = new File(old_backup_db_file);
                    if (!file.exists()) {
                        Toast.makeText(getApplicationContext(), "Selected backup file does not exists", Toast.LENGTH_LONG).show();
                    } else {

                        //Before restoring old database; keep backup of existing database
                        /*Calendar calendar = Calendar.getInstance();

                        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                        if (day.length() == 1) day = "0" + day;

                        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                        if (hour.length() == 1) hour = "0" + hour;

                        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
                        if (minute.length() == 1) minute = "0" + minute;

                        String sec = String.valueOf(calendar.get(Calendar.SECOND));
                        if (sec.length() == 1) sec = "0" + sec;

                        String new_backup_db_file = getApplicationContext().getResources().getString(R.string.db_backup_file_name)
                                + "_" + calendar.get(Calendar.YEAR) + "_" + Constant.getShortMonthNameFromMonthNumber(calendar.get(Calendar.MONTH) + 1) + "_" + day
                                + "_" + hour + ":" + minute + ":" + sec;

                        String file_name = Constant.APP_DIRECTORY + new_backup_db_file + Constant.DB_EXTENSION;

                        boolean isBackupSuccess = dbHelper.backupDatabase(RestoreDB.this, file_name);

                        if (isBackupSuccess) {
                            boolean isRestoreSuccess = dbHelper.restoreDatabase(RestoreDB.this, old_backup_db_file);
                            if (isRestoreSuccess) {
                                Toast.makeText(getApplicationContext(), "Database successfully restored", Toast.LENGTH_LONG).show();

                                onBackPressed();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to restore database", Toast.LENGTH_LONG).show();
                        }*/

                        boolean isRestoreSuccess = dbHelper.restoreDatabase(RestoreDB.this, old_backup_db_file);
                        if (isRestoreSuccess) {
                            Toast.makeText(getApplicationContext(), "Database successfully restored", Toast.LENGTH_LONG).show();

                            onBackPressed();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to restore database", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch(Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        })
        .setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })
        .show();
    }
}