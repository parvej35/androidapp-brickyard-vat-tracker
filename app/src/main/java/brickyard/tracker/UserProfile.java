package brickyard.tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import brickyard.tracker.bean.UserProfileBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class UserProfile extends AppCompatActivity {

    EditText nameTxt, emailTxt, passTxt;
    CheckBox enablePassProtecCheckBox;
    Spinner countrySpinner;
    //Spinner currencySpinner;
    DbHelper dbHelper;

    TableRow titleTextTR, passTxtTR, lineViewTR;

    ArrayAdapter<String> countryAdapter;
    //ArrayAdapter<String> currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.profile_info_activity);
            setTitle("Profile Information");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(getApplicationContext());
            //UserProfileBean userProfileBean = dbHelper.getApplicationUserProfile(getApplicationContext());

            //--------------------------------------------------------
            //List<String> countryList = Constant.populateCountryList();
            List<String> countryList = Constant.populateCountryANdCurrencyList();
            countryList.add(0, "");

            countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
            countryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, countryList);
            countryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            countrySpinner.setAdapter(countryAdapter);

            //--------------------------------------------------------
            //List<String> currencyList = Constant.populateCurrencyList();
            //currencyList.add(0, "");

            /*currencySpinner = (Spinner) findViewById(R.id.currencySpinner);
            currencyAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, currencyList);
            currencyAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            currencySpinner.setAdapter(currencyAdapter);*/

            nameTxt = (EditText) findViewById(R.id.nameTxt);
            nameTxt.setText("");

            emailTxt = (EditText) findViewById(R.id.emailTxt);
            emailTxt.setText("");

            passTxt = (EditText) findViewById(R.id.passTxt);
            passTxt.setText("");

            titleTextTR = (TableRow) findViewById(R.id.titleTextTR);
            titleTextTR.setVisibility(View.INVISIBLE);

            passTxtTR = (TableRow) findViewById(R.id.passTxtTR);
            passTxtTR.setVisibility(View.INVISIBLE);

            lineViewTR = (TableRow) findViewById(R.id.lineViewTR);
            lineViewTR.setVisibility(View.INVISIBLE);

            enablePassProtecCheckBox = (CheckBox) findViewById(R.id.enablePassProtecCheckBox);
            enablePassProtecCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(enablePassProtecCheckBox.isChecked()) {
                        titleTextTR.setVisibility(View.VISIBLE);
                        passTxtTR.setVisibility(View.VISIBLE);
                        lineViewTR.setVisibility(View.VISIBLE);
                    } else {
                        titleTextTR.setVisibility(View.INVISIBLE);
                        passTxtTR.setVisibility(View.INVISIBLE);
                        lineViewTR.setVisibility(View.INVISIBLE);
                    }

                }
            });

            Button btnSave = (Button) findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String name = nameTxt.getText().toString().trim();
                        if (name.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserProfile.this);
                            alertDialogBuilder.setMessage("Enter full name")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                            return;
                        }

                        String email = emailTxt.getText().toString().trim();
                        if (email.trim().length() == 0) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserProfile.this);
                            alertDialogBuilder.setMessage("Enter email")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                            return;
                        }

                        String selectedCountryInfo = countrySpinner.getSelectedItem().toString();
                        if(selectedCountryInfo == null || selectedCountryInfo.equalsIgnoreCase("")){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserProfile.this);
                            alertDialogBuilder.setMessage("Select country")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();

                            return;
                        }

                        /*String currency = currencySpinner.getSelectedItem().toString();
                        if(currency == null || currency.equalsIgnoreCase("")){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserProfile.this);
                            alertDialogBuilder.setMessage("Select currency")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                            return;
                        }*/

                        String password = "";
                        if(enablePassProtecCheckBox.isChecked()) {
                            password = passTxt.getText().toString().trim();
                            if (password.trim().length() == 0) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserProfile.this);
                                alertDialogBuilder.setMessage("Enter password")
                                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        }).show();
                                return;
                            }
                        }

                        String isPassEnabled = enablePassProtecCheckBox.isChecked() ? "1" : "0";

                        String[] countryInfoArray = selectedCountryInfo.split(Constant.HASH_TAG_WITH_SPACE);
                        String country = countryInfoArray[0].trim();
                        String currency = countryInfoArray[1].trim();

                        dbHelper.updateApplicationUserProfile(getApplicationContext(), new UserProfileBean(name, email, country, "", currency, password, isPassEnabled, ""));

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
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
        //getMenuInflater().inflate(R.menu.sub_menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.go_back:
                this.onBackPressed();
                return (true);
        }*/
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), WelcomeSplashActivity.class);
        startActivity(i);
    }
}