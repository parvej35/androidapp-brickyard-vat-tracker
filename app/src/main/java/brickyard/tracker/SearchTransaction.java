package brickyard.tracker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class SearchTransaction extends AppCompatActivity {

    TextView fromDateTxt, toDateTxt;
    TextView typeTextView, sectorTextView;

    DbHelper dbHelper;
    Calendar calendar;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_search);
            setTitle("Search Transaction");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            dbHelper = new DbHelper(getApplicationContext());

            dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            typeTextView = (TextView) findViewById(R.id.typeTextView);
            sectorTextView = (TextView) findViewById(R.id.sectorTextView);

            fromDateTxt = (TextView) findViewById(R.id.fromDateText);
            fromDateTxt.setInputType(InputType.TYPE_NULL);
            fromDateTxt.requestFocus();

            toDateTxt = (TextView) findViewById(R.id.toDateText);
            toDateTxt.setInputType(InputType.TYPE_NULL);
            toDateTxt.requestFocus();

            calendar = Calendar.getInstance();

            fromDateTxt.setText(dateFormatter.format(new Date()));
            toDateTxt.setText(dateFormatter.format(new Date()));

            //--------------------------------------------------------------------------------------
            LinearLayout fromDateLayout = (LinearLayout) findViewById(R.id.fromDateLayout);
            fromDateLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showFromDatePicker(); }});

            LinearLayout fromDateImageLayout = (LinearLayout) findViewById(R.id.fromDateImageLayout);
            fromDateImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showFromDatePicker(); }});

            LinearLayout toDateLayout = (LinearLayout) findViewById(R.id.toDateLayout);
            toDateLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showToDatePicker(); }});

            LinearLayout toDateImageLayout = (LinearLayout) findViewById(R.id.toDateImageLayout);
            toDateImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showToDatePicker(); }});

            //--------------------------------------------------------------------------------------
            LinearLayout typeLayout = (LinearLayout) findViewById(R.id.typeLayout);
            typeLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showTypeList(); }});

            LinearLayout typeImageLayout = (LinearLayout) findViewById(R.id.typeImageLayout);
            typeImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showTypeList(); }});

            //--------------------------------------------------------------------------------------
            LinearLayout sectorLayout = (LinearLayout) findViewById(R.id.sectorLayout);
            sectorLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(); }});

            LinearLayout sectorImageLayout = (LinearLayout) findViewById(R.id.sectorImageLayout);
            sectorImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(); }});
            //--------------------------------------------------------------------------------------

            Button btnSearch = (Button) findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String strFromDate = fromDateTxt.getText().toString();
                        Date date1 = dateFormatter.parse(strFromDate);
                        long longDate1 = date1.getTime();

                        String strToDate = toDateTxt.getText().toString();
                        Date date2 = dateFormatter.parse(strToDate);
                        long longDate2 = date2.getTime();

                        if(longDate2 < longDate1) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchTransaction.this);
                            alertDialogBuilder.setMessage("'From Date' should be less than 'To Date'")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();

                            return;
                        }

                        // Calculate difference in days
                        long date_range = (longDate2 - longDate1) / (24 * 60 * 60 * 1000) + 1;

                        if(date_range > 31) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchTransaction.this);
                            alertDialogBuilder.setMessage("Selected date range should not be more than 30 days")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();

                            return;
                        }

                        String type = typeTextView.getText().toString();

                        int transactionType = 0;
                        if(type.equalsIgnoreCase("Income")){
                            transactionType = Constant.TYPE_INC;
                        } else if(type.equalsIgnoreCase("Expense")){
                            transactionType = Constant.TYPE_EXP;
                        }

                        String sector = sectorTextView.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), TransactionSearchDataList.class);
                        intent.putExtra("transactionType", String.valueOf(transactionType));
                        intent.putExtra("sector", sector);
                        intent.putExtra("longFromDate", String.valueOf(longDate1));
                        intent.putExtra("longToDate", String.valueOf(longDate2));
                        startActivity(intent);

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

    private void showFromDatePicker() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(SearchTransaction.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String strDate = String.valueOf(dayOfMonth);
                if(dayOfMonth < 10) {
                    strDate = "0" + dayOfMonth;
                }

                if(monthOfYear + 1 < 10) {
                    fromDateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + strDate);
                } else {
                    fromDateTxt.setText(year + "-" + (monthOfYear + 1) + "-" + strDate);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showToDatePicker() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(SearchTransaction.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String strDate = String.valueOf(dayOfMonth);
                if(dayOfMonth < 10) {
                    strDate = "0" + dayOfMonth;
                }

                if(monthOfYear + 1 < 10) {
                    toDateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + strDate);
                } else {
                    toDateTxt.setText(year + "-" + (monthOfYear + 1) + "-" + strDate);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTypeList() {
        try {
            String[] category_arr = Constant.TYPE_SELECTOR;

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(SearchTransaction.this);

            builder.setSingleChoiceItems(category_arr, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ListView lw = ((AlertDialog)dialog).getListView();
                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                    typeTextView.setText(checkedItem.toString());
                    sectorTextView.setText(Constant.ALL_SECTOR);

                    dialog.cancel();

                    //showSectorList();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            // add OK and Cancel buttons
            /*builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ListView lw = ((AlertDialog)dialog).getListView();
                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                    typeTextView.setText(checkedItem.toString());
                }
            });*/

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            //dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showSectorList() {
        try {
            String[] category_arr = new String[] {Constant.ALL_SECTOR};

            String selectedType = typeTextView.getText().toString();
            if(selectedType.equalsIgnoreCase(Constant.STR_INCOME)) {
                category_arr = (String[]) dbHelper.getCategoryNameByType(getApplicationContext(), Constant.TYPE_INC);
            } else if(selectedType.equalsIgnoreCase(Constant.STR_EXPENSE)) {
                category_arr = (String[]) dbHelper.getCategoryNameByType(getApplicationContext(), Constant.TYPE_EXP);
            }

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(SearchTransaction.this);

            builder.setSingleChoiceItems(category_arr, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user checked an item
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            // add OK and Cancel buttons
            builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ListView lw = ((AlertDialog)dialog).getListView();
                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                    sectorTextView.setText(checkedItem.toString());
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}