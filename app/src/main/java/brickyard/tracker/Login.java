package brickyard.tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import brickyard.tracker.bean.UserProfileBean;
import brickyard.tracker.util.DbHelper;

public class Login extends AppCompatActivity {

    TextView nameTxt;
    EditText passwordText;
    DbHelper dbHelper;
    UserProfileBean userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_activity);
            setTitle("Login");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(getApplicationContext());
            userProfile = dbHelper.getApplicationUserProfile(getApplicationContext());

            nameTxt = (TextView) findViewById(R.id.nameTxt);
            nameTxt.setText(userProfile.getName());

            passwordText = (EditText) findViewById(R.id.passwordText);
            passwordText.setText("");

            Button btnLogin = (Button) findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String password = passwordText.getText().toString();
                        if (password.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                            alertDialogBuilder.setMessage("Enter password")
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                            return;
                        }

                        if(userProfile.getPassword().equals(password)) {
                            dbHelper.updateApplicationUserSignout(getApplicationContext(), "0");

                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            TextView resetPassword = (TextView) findViewById(R.id.resetPassword);
            resetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                    startActivity(intent);
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
        ActivityCompat.finishAffinity(Login.this);

        /*new android.app.AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Closing Application")
                .setMessage("Please rate this app 5* and provide your valuable feedback.")
                .setPositiveButton("Rate This App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PlayStoreLink().rateApp(Login.this);
                    }
                })
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.finishAffinity(Login.this);
                    }
                })
                .show();*/
    }
}