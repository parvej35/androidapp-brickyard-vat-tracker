package brickyard.tracker.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import brickyard.tracker.Dashboard;
import brickyard.tracker.R;
import brickyard.tracker.util.DbHelper;

public class UpdatePassword extends AppCompatActivity {

    EditText newPasswordText1, newPasswordText2;
    DbHelper dbHelper;
    //UserProfileBean userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.update_password_activity);
            setTitle("Update password");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(getApplicationContext());

            newPasswordText1 = (EditText) findViewById(R.id.newPasswordText1);
            newPasswordText1.setText("");

            newPasswordText2 = (EditText) findViewById(R.id.newPasswordText2);
            newPasswordText2.setText("");

            Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String newPassword1 = newPasswordText1.getText().toString();
                        if (newPassword1.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UpdatePassword.this);
                            alertDialogBuilder.setMessage("Enter password")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                            return;
                        }

                        String newPassword2 = newPasswordText2.getText().toString();
                        if (newPassword2.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UpdatePassword.this);
                            alertDialogBuilder.setMessage("Re-Enter password")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                            return;
                        }

                        if (!newPassword2.equals(newPassword1)) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UpdatePassword.this);
                            alertDialogBuilder.setMessage("Password does not match")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                            return;
                        }

                        dbHelper.updateApplicationUserPassword(getApplicationContext(), newPassword1);

                        Toast.makeText(getApplicationContext(), "Password successfully updated", Toast.LENGTH_LONG).show();

                        onBackPressed();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            Button btnCancel = (Button) findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
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