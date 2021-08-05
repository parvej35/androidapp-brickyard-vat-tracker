package brickyard.tracker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class TransactionDetails extends AppCompatActivity {

    TextView dateTextView, timeTextView;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    SimpleDateFormat dateFormatter;
    Calendar calendar;

    DbHelper dbHelper;

    TextView currencyTextView, amountTextView, sectorTextView, noteTextView;

    StringBuilder sbGivenNumber;
    String currencySymbol = "";

    //EditText dateText, timeText, amountText, noteText;
    //Button btnUpdate, btnCancel, btnDelete;

    //AutoCompleteTextView categoryText;
    //Spinner categorySelector;

    //TextView labelCategory, labelNote, labelAmount;

    RecordBean recordBean;

    String sector = "";
    long longFromDate = 0L;
    long longToDate = 0L;
    int transactionType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_details);
            setTitle("Transaction details");

            /*MobileAds.initialize(this, getString(R.string.admob_app_id));
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            //----------------------------------------------------------------------------
            recordBean = (RecordBean) getIntent().getSerializableExtra("recordObj");
            longFromDate = Long.parseLong(getIntent().getStringExtra("longFromDate"));
            longToDate = Long.parseLong(getIntent().getStringExtra("longToDate"));
            transactionType = Integer.parseInt(getIntent().getStringExtra("transactionType"));
            sector = getIntent().getStringExtra("sector");

            dbHelper = new DbHelper(getApplicationContext());

            currencySymbol = dbHelper.getApplicationUserCurrencySymbol(getApplicationContext());
            currencyTextView = (TextView) findViewById(R.id.currencyTextView);
            currencyTextView.setText(currencySymbol);

//            sbGivenNumber = new StringBuilder(recordBean.getAmount());

            amountTextView = (TextView) findViewById(R.id.amountTextView);
//            amountTextView.setText(recordBean.getAmount());

            noteTextView = (TextView) findViewById(R.id.noteTextView);
            noteTextView.setContentDescription(recordBean.getNote());

            if(recordBean.getNote() != null && recordBean.getNote().length() > Constant.MAX_NOTE_LEN) {
                noteTextView.setText(recordBean.getNote().substring(0, Constant.MAX_NOTE_LEN) + "...");
            } else {
                noteTextView.setText(recordBean.getNote());
            }

            sectorTextView = (TextView) findViewById(R.id.sectorTextView);
//            sectorTextView.setText(recordBean.getCategory());

            dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            dateTextView = (TextView) findViewById(R.id.dateTextView);

//            String strMonth = String.valueOf(recordBean.getMonth());
//            if(recordBean.getMonth() < 10) {
//                strMonth = "0" + strMonth;
//            }

//            String strDate = String.valueOf(recordBean.getDay());
//            if(recordBean.getDay() < 10) {
//                strDate = "0" + strDate;
//            }

//            dateTextView.setText(recordBean.getYear()+"-"+strMonth+"-"+strDate);

            calendar = Calendar.getInstance();

            //dateTextView.setText(dateFormatter.format(new Date()));

            //--------------------------------------------------------------------------------------
            LinearLayout dateLayout = (LinearLayout) findViewById(R.id.dateLayout);
            dateLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDatePicker(); }});

            LinearLayout dateImageLayout = (LinearLayout) findViewById(R.id.dateImageLayout);
            dateImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDatePicker(); }});
            //--------------------------------------------------------------------------------------

            timeTextView = (TextView) findViewById(R.id.timeTextView);
//            timeTextView.setText(recordBean.getTime());

            timePickerDialog = new TimePickerDialog(this, new OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int currentHour, int currentMin) {
                    timeTextView.setText(currentHour + ":" + currentMin);
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false);

            timeTextView.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { timePickerDialog.show(); }});

            LinearLayout timeLayout = (LinearLayout) findViewById(R.id.timeLayout);
            timeLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { timePickerDialog.show(); }});

            LinearLayout timeImageLayout = (LinearLayout) findViewById(R.id.timeImageLayout);
            timeImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { timePickerDialog.show(); }});

            //--------------------------------------------------------------------------------------
            LinearLayout sectorLayout = (LinearLayout) findViewById(R.id.sectorLayout);
//            sectorLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(recordBean.getType()); }});

            LinearLayout sectorImageLayout = (LinearLayout) findViewById(R.id.sectorImageLayout);
//            sectorImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(recordBean.getType()); }});

            //--------------------------------------------------------------------------------------
            LinearLayout noteLayout = (LinearLayout) findViewById(R.id.noteLayout);
            noteLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDialogToAddNote(); }});

            LinearLayout noteImageLayout = (LinearLayout) findViewById(R.id.noteImageLayout);
            noteImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDialogToAddNote(); }});

            //--------------------------------------------------------------------------------------

            final LinearLayout nub0 = (LinearLayout) findViewById(R.id.nub0);
            nub0.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub0); }});

            final LinearLayout nub1 = (LinearLayout) findViewById(R.id.nub1);
            nub1.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub1); }});

            final LinearLayout nub2 = (LinearLayout) findViewById(R.id.nub2);
            nub2.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub2); }});

            final LinearLayout nub3 = (LinearLayout) findViewById(R.id.nub3);
            nub3.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub3); }});

            final LinearLayout nub4 = (LinearLayout) findViewById(R.id.nub4);
            nub4.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub4); }});

            final LinearLayout nub5 = (LinearLayout) findViewById(R.id.nub5);
            nub5.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub5); }});

            final LinearLayout nub6 = (LinearLayout) findViewById(R.id.nub6);
            nub6.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub6); }});

            final LinearLayout nub7 = (LinearLayout) findViewById(R.id.nub7);
            nub7.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub7); }});

            final LinearLayout nub8 = (LinearLayout) findViewById(R.id.nub8);
            nub8.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub8); }});

            final LinearLayout nub9 = (LinearLayout) findViewById(R.id.nub9);
            nub9.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nub9); }});

            final LinearLayout nubDot = (LinearLayout) findViewById(R.id.nubDot);
            nubDot.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(nubDot); }});

            final LinearLayout clear = (LinearLayout) findViewById(R.id.clear);
            clear.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { updateGivenAmount(clear); }});
            //----------------------------------------------------------------------------


            final LinearLayout updateLayout = (LinearLayout) findViewById(R.id.updateLayout);
            updateLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int updateCount = update_record();

                        if(updateCount == -1) return;

                        if(updateCount > 0){
                            //Toast.makeText(getApplicationContext(), "Transaction successfully updated", Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(getApplicationContext(), TransactionSearchDataList.class);
                            intent.putExtra("sector", sector);
                            intent.putExtra("transactionType", String.valueOf(transactionType));
                            intent.putExtra("longFromDate", String.valueOf(longFromDate));
                            intent.putExtra("longToDate", String.valueOf(longToDate));
                            startActivity(intent);

                            //reset_form();
                            //onBackPressed();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to update transaction information", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });


            final LinearLayout cancelLayout = (LinearLayout) findViewById(R.id.cancelLayout);
            cancelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        onBackPressed();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            final LinearLayout deleteLayout = (LinearLayout) findViewById(R.id.deleteLayout);
            deleteLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        new AlertDialog.Builder(TransactionDetails.this)
                                .setIcon(android.R.drawable.ic_delete)
                                .setTitle("Deleting transaction")
                                .setMessage("Are you sure to delete this record?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int count = dbHelper.deleteRecord(getApplicationContext(), recordBean.getId());

                                        if(count > 0) {
                                            //onBackPressed();
                                            //Toast.makeText(getApplicationContext(), "Transaction successfully deleted", Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(getApplicationContext(), TransactionSearchDataList.class);
                                            intent.putExtra("sector", sector);
                                            intent.putExtra("transactionType", String.valueOf(transactionType));
                                            intent.putExtra("longFromDate", String.valueOf(longFromDate));
                                            intent.putExtra("longToDate", String.valueOf(longToDate));
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Failed to delete transaction record.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                .setNeutralButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                //.setNegativeButton("Cancel", null)
                                .show();
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
        Intent i = new Intent(getApplicationContext(), SearchTransaction.class);
        startActivity(i);
    }

    private int update_record() {
        int updateCount = 0;
        try {
            String strDate = dateTextView.getText().toString();
            Date date = dateFormatter.parse(strDate);
            long longDate = date.getTime();

            String dateArr[] = strDate.split("-");
            int year = Integer.parseInt(dateArr[0]);
            int month = Integer.parseInt(dateArr[1]);
            int day = Integer.parseInt(dateArr[2]);

            String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

            String strTime = timeTextView.getText().toString();

            String strAmount = amountTextView.getText().toString();
            if (strAmount.equalsIgnoreCase("0") || strAmount.length() == 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TransactionDetails.this);
                alertDialogBuilder.setMessage("Enter amount")
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                return -1;
            }

            String strCategory = sectorTextView.getText().toString();
            if (strCategory.length() == 0 || strCategory.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TransactionDetails.this);
                alertDialogBuilder.setMessage("Select sector")
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                return -1;
            }

            String strNote = noteTextView.getContentDescription().toString();

//            recordBean.setDate(longDate);
//            recordBean.setTime(strTime);
//            recordBean.setNote(strNote);
//            recordBean.setAmount(strAmount);
//            recordBean.setCategory(strCategory);
//            recordBean.setDay(day);
//            recordBean.setMonth(month);
//            recordBean.setYear(year);
//            recordBean.setDayName(dayName);

            updateCount = dbHelper.updateRecord(getApplicationContext(), recordBean);

            return updateCount;
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            return updateCount;
        }
    }

    private void updateGivenAmount(LinearLayout linearLayout) {
        String text = linearLayout.getContentDescription().toString();

        if(text.equalsIgnoreCase("clear")){
            //sbGivenNumber = new StringBuilder().append(currencySymbol).append(" ").append("0");
            sbGivenNumber = new StringBuilder("0");
            amountTextView.setText(sbGivenNumber.toString());
        }
        else if(text.equalsIgnoreCase("save")){
            Toast.makeText(getApplicationContext(), sbGivenNumber.toString(), Toast.LENGTH_LONG).show();
        }
        else if (text.equalsIgnoreCase(".")) {
            if (!sbGivenNumber.toString().contains(".")) {
                sbGivenNumber.append(text);
                amountTextView.setText(sbGivenNumber.toString());
            }
        }
        else if (sbGivenNumber.toString().length() < 12) {
            if (!sbGivenNumber.toString().equals("0")) {//123...0
                sbGivenNumber.append(text);
                amountTextView.setText(sbGivenNumber.toString());
            }

            else if (sbGivenNumber.toString().equals("0") && !text.equals("0")) {//0...1
                //sbGivenNumber = new StringBuilder().append(currencySymbol).append(" ").append(text);
                sbGivenNumber = new StringBuilder();
                sbGivenNumber.append(text);
                amountTextView.setText(sbGivenNumber.toString());
            }
        }
    }

    private void showDatePicker() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(TransactionDetails.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String strDate = String.valueOf(dayOfMonth);
                if(dayOfMonth < 10) {
                    strDate = "0" + dayOfMonth;
                }

                if(monthOfYear + 1 < 10) {
                    dateTextView.setText(year + "-0" + (monthOfYear + 1) + "-" + strDate);
                } else {
                    dateTextView.setText(year + "-" + (monthOfYear + 1) + "-" + strDate);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showSectorList(int transactionType) {
        try {
            String[] category_arr = (String[]) dbHelper.getCategoryNameByType(getApplicationContext(), transactionType);
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(TransactionDetails.this);

            builder.setSingleChoiceItems(category_arr, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user checked an item
                }
            });

            /*builder.setNeutralButton("Add New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();

                    showDialogToAddCategory();
                }
            });*/

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
            //dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLUE);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddNote(){
        LayoutInflater layoutInflater = LayoutInflater.from(TransactionDetails.this);
        View promptView = layoutInflater.inflate(R.layout.note_entry_prompt, null);

        /*AlertDialog alertDialogBuilder = new AlertDialog.Builder(TransactionDetails.this);
        alertDialogBuilder.setView(promptView);

        final EditText note = (EditText) promptView.findViewById(R.id.note);
        note.setText(noteTextView.getContentDescription());

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String strNote = note.getText().toString().trim();
                        noteTextView.setContentDescription(strNote);

                        if(strNote.length() > Constant.MAX_NOTE_LEN) {
                            strNote = strNote.substring(0, Constant.MAX_NOTE_LEN) + "...";
                        }
                        noteTextView.setText(strNote);

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create an alert dialog
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        dialog.getButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
        dialog.getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);*/
    }
}