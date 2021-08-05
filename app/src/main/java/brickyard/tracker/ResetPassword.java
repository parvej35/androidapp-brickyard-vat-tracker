package brickyard.tracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import brickyard.tracker.bean.UserProfileBean;
import brickyard.tracker.util.DbHelper;

public class ResetPassword extends AppCompatActivity {

    EditText emailText;
    EditText newPasswordText1, newPasswordText2;
    DbHelper dbHelper;
    UserProfileBean userProfile;
    Context context = this;

    TableLayout resetTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.password_reset_activity);
            setTitle("Reset Password");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(context);

            emailText = (EditText) findViewById(R.id.emailText);
            emailText.setText("");

            newPasswordText1 = (EditText) findViewById(R.id.newPasswordText1);
            newPasswordText1.setText("");

            newPasswordText2 = (EditText) findViewById(R.id.newPasswordText2);
            newPasswordText2.setText("");

            resetTableLayout = (TableLayout) findViewById(R.id.resetTableLayout);
            resetTableLayout.setVisibility(View.INVISIBLE);

            Button btnVerifyEmail = (Button) findViewById(R.id.btnVerifyEmail);
            btnVerifyEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String email = emailText.getText().toString();
                        if (email.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResetPassword.this);
                            alertDialogBuilder.setMessage("Enter your email ID")
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                            return;
                        }

                        userProfile = dbHelper.getApplicationUserProfile(context);
                        if(userProfile.getEmail().equals(email)) {
                            TableLayout verificationTableLayout = (TableLayout) findViewById(R.id.verificationTableLayout);
                            verificationTableLayout.setVisibility(View.INVISIBLE);

                            TableLayout resetTableLayout = (TableLayout) findViewById(R.id.resetTableLayout);
                            resetTableLayout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(context, "Wrong email ID", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            Button btnResetPassword = (Button) findViewById(R.id.btnResetPassword);
            btnResetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        String newPassword1 = newPasswordText1.getText().toString();
                        String newPassword2 = newPasswordText2.getText().toString();
                        if (newPassword1.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResetPassword.this);
                            alertDialogBuilder.setMessage("Enter your new password.")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                        } else if (newPassword2.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResetPassword.this);
                            alertDialogBuilder.setMessage("Re enter new password.")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                        }

                        if(newPassword1.equals(newPassword2)) {
                            dbHelper.updateApplicationUserPassword(getApplicationContext(), newPassword1);
                            Toast.makeText(getApplicationContext(), "Password successfully updated", Toast.LENGTH_LONG).show();

                            onBackPressed();

                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResetPassword.this);
                            alertDialogBuilder.setMessage("Password mismatched")
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu_close, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                this.onBackPressed();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}