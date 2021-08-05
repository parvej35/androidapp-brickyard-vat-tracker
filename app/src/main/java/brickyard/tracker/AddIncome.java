package brickyard.tracker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import brickyard.tracker.bean.CategoryBean;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class AddIncome extends AppCompatActivity {

    TextView dateTextView, timeTextView;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    SimpleDateFormat dateFormatter;
    Calendar calendar;

    DbHelper dbHelper;

    TextView currencyTextView, amountTextView, sectorTextView, noteTextView;

    StringBuilder sbGivenNumber;
    String currencySymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_record);
            setTitle("Add Income");

            dbHelper = new DbHelper(getApplicationContext());

            currencySymbol = dbHelper.getApplicationUserCurrencySymbol(getApplicationContext());

            currencyTextView = (TextView) findViewById(R.id.currencyTextView);
            currencyTextView.setText(currencySymbol);

            sbGivenNumber = new StringBuilder("0");
            amountTextView = (TextView) findViewById(R.id.amountTextView);
            noteTextView = (TextView) findViewById(R.id.noteTextView);
            sectorTextView = (TextView) findViewById(R.id.sectorTextView);
            sectorTextView.setText(Constant.SELECT);

            dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            dateTextView = (TextView) findViewById(R.id.dateTextView);
            dateTextView.setInputType(InputType.TYPE_NULL);
            dateTextView.requestFocus();

            calendar = Calendar.getInstance();

            dateTextView.setText(dateFormatter.format(new Date()));

            //--------------------------------------------------------------------------------------
            LinearLayout dateLayout = (LinearLayout) findViewById(R.id.dateLayout);
            dateLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDatePicker(); }});

            LinearLayout dateImageLayout = (LinearLayout) findViewById(R.id.dateImageLayout);
            dateImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDatePicker(); }});
            //--------------------------------------------------------------------------------------

            timeTextView = (TextView) findViewById(R.id.timeTextView);
            timeTextView.setInputType(InputType.TYPE_NULL);
            timeTextView.requestFocus();
            timeTextView.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

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
            sectorLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(); }});

            LinearLayout sectorImageLayout = (LinearLayout) findViewById(R.id.sectorImageLayout);
            sectorImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(); }});

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

            final LinearLayout saveLayout = (LinearLayout) findViewById(R.id.saveLayout);
            saveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        long newRecordId = save_new_record();

                        if(newRecordId == -1) return;

                        if(newRecordId > 0){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddIncome.this);
                            alertDialogBuilder.setMessage("New transaction successfully saved")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();

                            reset_form();
                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddIncome.this);
                            alertDialogBuilder.setMessage("Failed to save new transaction information")
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

            final LinearLayout cancelLayout = (LinearLayout) findViewById(R.id.cancelLayout);
            cancelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        reset_form();
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
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(i);
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

    private long save_new_record() {
        long newRecordId = 0;
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddIncome.this);
                alertDialogBuilder.setMessage("Enter income amount")
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddIncome.this);
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
            strNote = strNote.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

//            RecordBean newRecordBean = new RecordBean(Constant.TYPE_INC, longDate, strTime, strNote, strAmount, strCategory, dayName, day, month, year);
//            newRecordId = dbHelper.saveRecord(getApplicationContext(), newRecordBean);

            return newRecordId;
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Failed to save new record. Exception : " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return 0;
        }
    }

    private void reset_form() {
        dateTextView.setText(dateFormatter.format(new Date()));
        timeTextView.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

        sbGivenNumber = new StringBuilder().append("0");
        amountTextView.setText(sbGivenNumber.toString());

        //amountTextView.setText("0");
        noteTextView.setText("");
        sectorTextView.setText(Constant.SELECT);
    }

    private void showDatePicker() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(AddIncome.this, new DatePickerDialog.OnDateSetListener() {
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

    private void showSectorList() {
        try {
            String[] category_arr = (String[]) dbHelper.getCategoryNameByType(getApplicationContext(), Constant.TYPE_INC);
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddIncome.this);

            builder.setSingleChoiceItems(category_arr, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user checked an item
                }
            });

            builder.setNeutralButton("Add New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();

                    showDialogToAddCategory();
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
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLUE);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddCategory(){
        try {
            LayoutInflater layoutInflater = LayoutInflater.from(AddIncome.this);
            View promptView = layoutInflater.inflate(R.layout.name_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddIncome.this);
            alertDialogBuilder.setView(promptView);

            final EditText input = (EditText) promptView.findViewById(R.id.name);

            // setup a dialog window
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String categoryName = input.getText().toString().trim();
                            categoryName = categoryName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                            if (categoryName.length() > 0) {
                                String existingCatName = dbHelper.getCategoryByTypeAndName(getApplicationContext(), Constant.TYPE_INC, categoryName);
                                if (existingCatName == null || existingCatName.equalsIgnoreCase("")) {
                                    dbHelper.saveCategory(getApplicationContext(), new CategoryBean(categoryName, Constant.TYPE_INC));
                                } else {
                                    categoryName = existingCatName;
                                }
                            }

                            sectorTextView.setText(categoryName);
                            dialog.cancel();
                            //showSectorList();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            showSectorList();
                        }
                    });

            // create an alert dialog
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddNote(){
        try {
            LayoutInflater layoutInflater = LayoutInflater.from(AddIncome.this);
            View promptView = layoutInflater.inflate(R.layout.note_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddIncome.this);
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

                            if (strNote.length() > Constant.MAX_NOTE_LEN) {
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
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
