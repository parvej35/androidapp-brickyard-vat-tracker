package brickyard.tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import brickyard.tracker.bean.BrickyardBean;
import brickyard.tracker.bean.CircleBean;
import brickyard.tracker.bean.DivisionBean;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.bean.SectorBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class AddRecord extends AppCompatActivity {

    DbHelper dbHelper;

    TextView commissionerateTextView, divisionTextView, circleTextView, sectorTextView;
    TextView brickyardTextView, addressTextView, areaTextView, brickTypeTextView, statusTextView, financialYearTextView;

    EditText installment1, installment2, installment3, noteTextView;
    EditText preDueAmount, preVatPaidAmount, totalPaidAmount, currentDueAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_record);
            setTitle("Add Record");

            dbHelper = new DbHelper(getApplicationContext());

            commissionerateTextView = (TextView) findViewById(R.id.commissionerateTextView);
            divisionTextView = (TextView) findViewById(R.id.divisionTextView);
            circleTextView = (TextView) findViewById(R.id.circleTextView);
            sectorTextView = (TextView) findViewById(R.id.sectorTextView);

            brickyardTextView = (TextView) findViewById(R.id.brickyardTextView);
            addressTextView = (TextView) findViewById(R.id.addressTextView);
            areaTextView = (TextView) findViewById(R.id.areaTextView);
            brickTypeTextView = (TextView) findViewById(R.id.brickTypeTextView);
            statusTextView = (TextView) findViewById(R.id.statusTextView);
            financialYearTextView = (TextView) findViewById(R.id.financialYearTextView);

            installment1 = (EditText) findViewById(R.id.installment1);
            installment2 = (EditText) findViewById(R.id.installment2);
            installment3 = (EditText) findViewById(R.id.installment3);

            preDueAmount = (EditText) findViewById(R.id.preDueAmount);
            preVatPaidAmount = (EditText) findViewById(R.id.preVatPaidAmount);
            totalPaidAmount = (EditText) findViewById(R.id.totalPaidAmount);
            currentDueAmount = (EditText) findViewById(R.id.currentDueAmount);

            noteTextView = (EditText) findViewById(R.id.noteTextView);

            //---COMMISSIONERATE-----------------------------------------------------------------------------------
            LinearLayout commissionerateLayout = (LinearLayout) findViewById(R.id.commissionerateLayout);
            commissionerateLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showCommissionerateList(); }});

            LinearLayout commissionerateImageLayout = (LinearLayout) findViewById(R.id.commissionerateImageLayout);
            commissionerateImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showCommissionerateList(); }});

            //---DIVISION-----------------------------------------------------------------------------------
            LinearLayout divisionLayout = (LinearLayout) findViewById(R.id.divisionLayout);
            divisionLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDivisionList(); }});

            LinearLayout divisionImageLayout = (LinearLayout) findViewById(R.id.divisionImageLayout);
            divisionImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDivisionList(); }});

            //---CIRCLE-----------------------------------------------------------------------------------
            LinearLayout circleLayout = (LinearLayout) findViewById(R.id.circleLayout);
            circleLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showCircleList(); }});

            LinearLayout circleImageLayout = (LinearLayout) findViewById(R.id.circleImageLayout);
            circleImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showCircleList(); }});

            //---SECTOR-----------------------------------------------------------------------------------
            LinearLayout sectorLayout = (LinearLayout) findViewById(R.id.sectorLayout);
            sectorLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(); }});

            LinearLayout sectorImageLayout = (LinearLayout) findViewById(R.id.sectorImageLayout);
            sectorImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showSectorList(); }});

            //---BRICKYARD-----------------------------------------------------------------------------------
            LinearLayout brickyardLayout = (LinearLayout) findViewById(R.id.brickyardLayout);
            brickyardLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showBrickyardList(); }});

            LinearLayout brickyardImageLayout = (LinearLayout) findViewById(R.id.brickyardImageLayout);
            brickyardImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showBrickyardList(); }});

            //---BRICKYARD AREA-----------------------------------------------------------------------------------
            LinearLayout areaLayout = (LinearLayout) findViewById(R.id.areaLayout);
            areaLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showAreaList(); }});

            LinearLayout areaImageLayout = (LinearLayout) findViewById(R.id.areaImageLayout);
            areaImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showAreaList(); }});

            //---BRICK TYPE-----------------------------------------------------------------------------------
            LinearLayout brickTypeLayout = (LinearLayout) findViewById(R.id.brickTypeLayout);
            brickTypeLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showBrickTypeList(); }});

            LinearLayout brickTypeImageLayout = (LinearLayout) findViewById(R.id.brickTypeImageLayout);
            brickTypeImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showBrickTypeList(); }});

            //---BRICKYARD STATUS-----------------------------------------------------------------------------------
            LinearLayout statusLayout = (LinearLayout) findViewById(R.id.statusLayout);
            statusLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showStatusList(); }});

            LinearLayout statusImageLayout = (LinearLayout) findViewById(R.id.statusImageLayout);
            statusImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showStatusList(); }});

            //---FINANCIAL YEAR-----------------------------------------------------------------------------------
            LinearLayout financialYearLayout = (LinearLayout) findViewById(R.id.financialYearLayout);
            financialYearLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showFinancialYearList(); }});

            LinearLayout financialYearImageLayout = (LinearLayout) findViewById(R.id.financialYearImageLayout);
            financialYearImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showFinancialYearList(); }});

            //---NOTE---------------------------------------------------------------------------------------------
            LinearLayout noteLayout = (LinearLayout) findViewById(R.id.noteLayout);
            noteLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDialogToAddNote(); }});

            LinearLayout noteImageLayout = (LinearLayout) findViewById(R.id.noteImageLayout);
            noteImageLayout.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showDialogToAddNote(); }});

            //---SAVING DATA-----------------------------------------------------------------------------------
            LinearLayout saveLayout = (LinearLayout) findViewById(R.id.saveLayout);
            saveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        long newRecordId = save_new_record();

                        if(newRecordId == -1) return;

                        if(newRecordId > 0){

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                            alertDialogBuilder.setMessage("New record successfully saved")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                            reset_form();

                        } else {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                            alertDialogBuilder.setMessage("Failed to save new record information")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

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

    private long save_new_record() {
        long newRecordId = 0;
        try {
//            Date date = new Date();
//            long longDate = date.getTime();

            /*String dateArr[] = strDate.split("-");
            int year = Integer.parseInt(dateArr[0]);
            int month = Integer.parseInt(dateArr[1]);
            int day = Integer.parseInt(dateArr[2]);

            String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
            String strTime = timeTextView.getText().toString();

            String strAmount = amountTextView.getText().toString();
            if (strAmount.equalsIgnoreCase("0") || strAmount.length() == 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Enter amount")
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                return -1;
            }*/

            String strDivision = divisionTextView.getText().toString();
            if (strDivision.length() == 0 || strDivision.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Division").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strCircle = circleTextView.getText().toString();
            if (strCircle.length() == 0 || strCircle.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Circle").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strSector = sectorTextView.getText().toString();
            if (strSector.length() == 0 || strSector.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Sector").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strBrickyard = brickyardTextView.getText().toString();
            if (strBrickyard.length() == 0 || strBrickyard.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Brickyard").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strAddress = addressTextView.getText().toString();

            String strArea = areaTextView.getText().toString();
            if (strArea.length() == 0 || strArea.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Area").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strBrickType = brickTypeTextView.getText().toString();
            if (strBrickType.length() == 0 || strBrickType.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Brick Type").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strStatus = statusTextView.getText().toString();
            if (strStatus.length() == 0 || strStatus.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Status").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strFinancialYear = financialYearTextView.getText().toString();
            if (strFinancialYear.length() == 0 || strFinancialYear.equalsIgnoreCase(Constant.SELECT)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Select Financial Year").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strInstallment1 = installment1.getText().toString();
            String strInstallment2 = installment2.getText().toString();
            String strInstallment3 = installment3.getText().toString();

            if ((strInstallment1.length() == 0) && (strInstallment2.length() == 0) && (strInstallment3.length() == 0))  {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Enter Installment").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strPreDueAmount = preDueAmount.getText().toString();
            if (strPreDueAmount.length() == 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Enter Pre Due Amount").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strPreVatPaidAmount = preVatPaidAmount.getText().toString();
            if (strPreVatPaidAmount.length() == 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Enter Pre VAT Paid Amount").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strTotalPaidAmount = totalPaidAmount.getText().toString();
            if (strTotalPaidAmount.length() == 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Enter Total Paid Amount").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strCurrentDueAmount = currentDueAmount.getText().toString();
            if (strCurrentDueAmount.length() == 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
                alertDialogBuilder.setMessage("Enter Current Due Amount").setPositiveButton("Close", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }}).show();

                return -1;
            }

            String strNote = noteTextView.getText().toString();
            strNote = strNote.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

            RecordBean newRecordBean = new RecordBean(System.currentTimeMillis(), strDivision, strCircle, strSector, strBrickyard, strAddress,
                    strArea, strBrickType, strStatus, strFinancialYear, strInstallment1, strInstallment2, strInstallment3, strPreDueAmount,
                    strPreVatPaidAmount, strTotalPaidAmount, strCurrentDueAmount, strNote);

            newRecordId = dbHelper.saveRecord(getApplicationContext(), newRecordBean);

            return newRecordId;

        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Failed to save new record. Exception : " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return 0;
        }
    }

    private void reset_form() {
        noteTextView.setText("");
        addressTextView.setText("");

        installment1.setText("");
        installment2.setText("");
        installment3.setText("");

        preDueAmount.setText("");
        preVatPaidAmount.setText("");
        totalPaidAmount.setText("");
        currentDueAmount.setText("");

        commissionerateTextView.setText(Constant.SELECT);
        divisionTextView.setText(Constant.SELECT);
        circleTextView.setText(Constant.SELECT);
        sectorTextView.setText(Constant.SELECT);
        brickyardTextView.setText(Constant.SELECT);
        areaTextView.setText(Constant.SELECT);
        brickTypeTextView.setText(Constant.SELECT);
        statusTextView.setText(Constant.SELECT);
        financialYearTextView.setText(Constant.SELECT);
    }

    private void showCommissionerateList() {
        try {
            String[] commissionerateArray = Constant.COMMISSIONERATE_ARRAY;
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(commissionerateArray, 0, new DialogInterface.OnClickListener() {
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

                    commissionerateTextView.setText(checkedItem.toString());
                    divisionTextView.setText(Constant.SELECT);
                    circleTextView.setText(Constant.SELECT);
                    sectorTextView.setText(Constant.SELECT);
                    brickyardTextView.setText(Constant.SELECT);
                    addressTextView.setText("");
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

    private void showDivisionList() {
        try {

            String[] division_arr = (String[]) dbHelper.getDivisionNameListByCommissionerate(getApplicationContext(), commissionerateTextView.getText().toString().trim());
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(division_arr, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user checked an item
                }
            });

            builder.setNeutralButton("Add New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();

                    showDialogToAddDivision();
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

                    divisionTextView.setText(checkedItem.toString());
                    circleTextView.setText(Constant.SELECT);
                    sectorTextView.setText(Constant.SELECT);
                    brickyardTextView.setText(Constant.SELECT);
                    addressTextView.setText("");
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showCircleList() {
        try {

            String[] circle_arr = (String[]) dbHelper.getCircleNameListByDivision(getApplicationContext(), divisionTextView.getText().toString().trim());
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(circle_arr, 0, new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { }});

            builder.setNeutralButton("Add New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    showDialogToAddCircle();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int id) { dialog.cancel(); }});

            // add OK and Cancel buttons
            builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ListView lw = ((AlertDialog)dialog).getListView();
                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                    circleTextView.setText(checkedItem.toString());
                    sectorTextView.setText(Constant.SELECT);
                    brickyardTextView.setText(Constant.SELECT);
                    addressTextView.setText("");
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showSectorList() {
        try {

            String[] sector_arr = (String[]) dbHelper.getSectorNameListByCircle(getApplicationContext(), circleTextView.getText().toString().trim());
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(sector_arr, 0, new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { }});

            builder.setNeutralButton("Add New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    showDialogToAddSector();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int id) { dialog.cancel(); }});

            // add OK and Cancel buttons
            builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ListView lw = ((AlertDialog)dialog).getListView();
                    Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                    sectorTextView.setText(checkedItem.toString());
                    brickyardTextView.setText(Constant.SELECT);
                    addressTextView.setText("");
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showAreaList() {
        try {

            String[] areaArray = Constant.BRICKYARD_AREA_ARRAY;

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(areaArray, 0, new DialogInterface.OnClickListener() {
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

                    areaTextView.setText(checkedItem.toString());
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

    private void showBrickTypeList() {
        try {

            String[] brickTypeArray = Constant.BRICKYARD_BRICK_TYPE;

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(brickTypeArray, 0, new DialogInterface.OnClickListener() {
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

                    brickTypeTextView.setText(checkedItem.toString());
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

    private void showStatusList() {
        try {

            String[] statusTypeArray = Constant.BRICKYARD_STATUS_TYPE;

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(statusTypeArray, 0, new DialogInterface.OnClickListener() {
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

                    statusTextView.setText(checkedItem.toString());
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

    private void showFinancialYearList() {
        try {

            List<String> yearList = new ArrayList<>();
            for(int year = 2020; year <= 2030; year++) {
                yearList.addAll(Collections.singleton(year + " - " + (year + 1)));
            }
            String[] itemsArray = new String[yearList.size()];
            String[] financialYearArray = yearList.toArray(itemsArray);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(financialYearArray, 0, new DialogInterface.OnClickListener() {
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

                    financialYearTextView.setText(checkedItem.toString());
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

    private void showBrickyardList() {
        try {
            String commissionerate = commissionerateTextView.getText().toString().trim();
            String division = divisionTextView.getText().toString().trim();
            String circle = circleTextView.getText().toString().trim();
            String sector = sectorTextView.getText().toString().trim();

            String[] brickyard_arr = (String[]) dbHelper.getBrickYardNameListByCommissionerateDivisionCircleSector(getApplicationContext(), commissionerate, division, circle, sector);
            //Arrays.sort(category_arr);

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(AddRecord.this);

            builder.setSingleChoiceItems(brickyard_arr, 0, new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { }});

            builder.setNeutralButton("Add New", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();

                    showDialogToAddBrickyard();
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

                    brickyardTextView.setText(checkedItem.toString());

                    //String address = dbHelper.getBrickyardAddressByNameAndCommissionerateAndDivisionAndCircleAndSector(getApplicationContext(), checkedItem.toString(), commissionerate, division, circle, sector);
                    //addressTextView.setText(address);

                    BrickyardBean brickyardBean = dbHelper.getBrickYardBeanByNameAndCommissionerateAndDivisionAndCircleAndSector(getApplicationContext(), checkedItem.toString(), commissionerate, division, circle, sector);
                    addressTextView.setText(brickyardBean.getAddress());
                    areaTextView.setText(brickyardBean.getArea());
                    brickTypeTextView.setText(brickyardBean.getType());
                    statusTextView.setText(brickyardBean.getStatus());
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddBrickyard(){
        try {

            String commissionerateVal = commissionerateTextView.getText().toString();
            String divisionVal = divisionTextView.getText().toString();
            String circleVal = circleTextView.getText().toString();
            String sectorVal = sectorTextView.getText().toString();

            LayoutInflater layoutInflater = LayoutInflater.from(AddRecord.this);
            View promptView = layoutInflater.inflate(R.layout.new_brickyard_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
            alertDialogBuilder.setView(promptView);

            //TextView commissionerate = (TextView) promptView.findViewById(R.id.commissionerateTextView);
            TextView division = (TextView) promptView.findViewById(R.id.divisionTextView);
            TextView circle = (TextView) promptView.findViewById(R.id.circleTextView);
            TextView sector = (TextView) promptView.findViewById(R.id.sectorTextView);

            final EditText name = (EditText) promptView.findViewById(R.id.name);
            final EditText address = (EditText) promptView.findViewById(R.id.address);

            if(divisionVal.equals("") || divisionVal.equals(Constant.SELECT) || circleVal.equals("") || circleVal.equals(Constant.SELECT) || sectorVal.equals("") || sectorVal.equals(Constant.SELECT)) {

                name.setText("Please Select Division, Circle & Sector");

                alertDialogBuilder.setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

            } else {

                //commissionerate.setText(commissionerateVal);
                division.setText(divisionVal);
                circle.setText(circleVal);
                sector.setText(sectorVal);

                // setup a dialog window
                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newName = name.getText().toString().trim();
                        newName = newName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                        String newAddress = address.getText().toString().trim();
                        newAddress = newAddress.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                        if (newName.length() > 0) {
                            int countExisting = dbHelper.countBrickyardNameByCommissionerateAndDivisionAndCircleAndSector(getApplicationContext(), newName, commissionerateVal, divisionVal, circleVal, sectorVal);
                            if (countExisting == 0) {
                                dbHelper.saveBrickyard(getApplicationContext(), new BrickyardBean(newName, newAddress, commissionerateVal, divisionVal, circleVal, sectorVal, "", "", ""));
                            }
                        }

                        brickyardTextView.setText(newName);
                        addressTextView.setText(newAddress);

                        dialog.cancel();

//                        showBrickyardList();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        showBrickyardList();
                    }
                });
            }

            // create an alert dialog
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddDivision(){
        try {

            String commissionerateVal = commissionerateTextView.getText().toString();

            LayoutInflater layoutInflater = LayoutInflater.from(AddRecord.this);
            View promptView = layoutInflater.inflate(R.layout.new_division_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
            alertDialogBuilder.setView(promptView);

            TextView commissionerate = (TextView) promptView.findViewById(R.id.commissionerateTextView);

            final EditText input = (EditText) promptView.findViewById(R.id.name);

            if(commissionerateVal.equals("") || commissionerateVal.equals(Constant.SELECT)) {

                input.setText("Please Select Commissionerate");

                alertDialogBuilder.setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

            } else {

                commissionerate.setText(commissionerateVal);

                // setup a dialog window
                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newName = input.getText().toString().trim();
                        newName = newName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                        if (newName.length() > 0) {
                            int countExisting = dbHelper.countDivisionByNameAndCommissionerate(getApplicationContext(), newName, commissionerateVal);
                            if (countExisting == 0) {
                                dbHelper.saveDivision(getApplicationContext(), new DivisionBean(newName, commissionerateVal));
                            }

                            divisionTextView.setText(newName);
                        }

                        dialog.cancel();
                        //showDivisionList();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        showDivisionList();
                    }
                });
            }

            // create an alert dialog
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddCircle(){
        try {

            String commissionerateVal = commissionerateTextView.getText().toString();
            String divisionVal = divisionTextView.getText().toString();

            LayoutInflater layoutInflater = LayoutInflater.from(AddRecord.this);
            View promptView = layoutInflater.inflate(R.layout.new_circle_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
            alertDialogBuilder.setView(promptView);

            TextView commissionerate = (TextView) promptView.findViewById(R.id.commissionerateTextView);
            TextView division = (TextView) promptView.findViewById(R.id.divisionTextView);

            final EditText input = (EditText) promptView.findViewById(R.id.name);

            if(divisionVal.equals("") || divisionVal.equals(Constant.SELECT)) {

                input.setText("Please Select Division");

                alertDialogBuilder.setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

            } else {

                commissionerate.setText(commissionerateVal);
                division.setText(divisionVal);

                // setup a dialog window
                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newName = input.getText().toString().trim();
                        newName = newName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                        if (newName.length() > 0) {
                            int countExisting = dbHelper.countCircleByNameAndDivision(getApplicationContext(), newName, divisionVal);
                            if (countExisting == 0) {
                                dbHelper.saveCircle(getApplicationContext(), new CircleBean(newName, commissionerateVal, divisionVal));
                            }

                            circleTextView.setText(newName);
                        }

                        dialog.cancel();
                        //showDivisionList();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        showCircleList();
                    }
                });
            }

            // create an alert dialog
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogToAddSector(){
        try {

            String commissionerateVal = commissionerateTextView.getText().toString();
            String divisionVal = divisionTextView.getText().toString();
            String circleVal = circleTextView.getText().toString();

            LayoutInflater layoutInflater = LayoutInflater.from(AddRecord.this);
            View promptView = layoutInflater.inflate(R.layout.new_sector_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
            alertDialogBuilder.setView(promptView);

            TextView commissionerate = (TextView) promptView.findViewById(R.id.commissionerateTextView);
            TextView division = (TextView) promptView.findViewById(R.id.divisionTextView);
            TextView circle = (TextView) promptView.findViewById(R.id.circleTextView);

            final EditText input = (EditText) promptView.findViewById(R.id.name);

            if(circleVal.equals("") || circleVal.equals(Constant.SELECT)) {

                input.setText("Please Select Circle");

                alertDialogBuilder.setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

            } else {

                commissionerate.setText(commissionerateVal);
                division.setText(divisionVal);
                circle.setText(circleVal);

                // setup a dialog window
                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newName = input.getText().toString().trim();
                        newName = newName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                        if (newName.length() > 0) {
                            int countExisting = dbHelper.countSectorByNameAndCircle(getApplicationContext(), newName, circleVal);
                            if (countExisting == 0) {
                                dbHelper.saveSector(getApplicationContext(), new SectorBean(newName, commissionerateVal, divisionVal, circleVal));
                            }

                            sectorTextView.setText(newName);
                        }

                        dialog.cancel();
                        //showDivisionList();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        showSectorList();
                    }
                });
            }

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

            LayoutInflater layoutInflater = LayoutInflater.from(AddRecord.this);
            View promptView = layoutInflater.inflate(R.layout.note_entry_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecord.this);
            alertDialogBuilder.setView(promptView);

            final EditText note = (EditText) promptView.findViewById(R.id.note);
            note.setText(noteTextView.getContentDescription());

            // setup a dialog window
            alertDialogBuilder.setCancelable(false).setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String strNote = note.getText().toString().trim();
                    noteTextView.setContentDescription(strNote);

                    if (strNote.length() > Constant.MAX_NOTE_LEN) {
                        strNote = strNote.substring(0, Constant.MAX_NOTE_LEN) + "...";
                    }
                    noteTextView.setText(strNote);

                    dialog.cancel();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
