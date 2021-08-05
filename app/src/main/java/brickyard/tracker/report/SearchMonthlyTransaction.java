package brickyard.tracker.report;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import brickyard.tracker.R;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class SearchMonthlyTransaction extends AppCompatActivity {

    DbHelper dbHelper;
    Spinner yearSpinner, monthSpinner1, monthSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_search_monthly);
            setTitle("Monthly Transaction");

            dbHelper = new DbHelper(getApplicationContext());

            yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
            monthSpinner1 = (Spinner) findViewById(R.id.monthSpinner1);
            monthSpinner2 = (Spinner) findViewById(R.id.monthSpinner2);

            Button btnSearch = (Button) findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        String strYear = yearSpinner.getSelectedItem().toString();
                        String month1 = monthSpinner1.getSelectedItem().toString();
                        String month2 = monthSpinner2.getSelectedItem().toString();

                        if(month1.equalsIgnoreCase("") || month2.equalsIgnoreCase("")){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchMonthlyTransaction.this);
                                alertDialogBuilder.setMessage("Enter both 'From Month' & 'To Month'")
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();

                            return;
                        }

                        int selectedMonth1Position = Arrays.asList(Constant.MONTH_ARRAY).indexOf(month1);
                        int selectedMonth2Position = Arrays.asList(Constant.MONTH_ARRAY).indexOf(month2);
                        if(selectedMonth1Position > selectedMonth2Position) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchMonthlyTransaction.this);
                            alertDialogBuilder.setMessage("'From Month' should be less than 'To Month'")
                                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();

                            return;
                        }

                        /*if(Integer.parseInt(toYear) < Integer.parseInt(fromYear)){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchMonthlyTransaction.this);
                                alertDialogBuilder.setMessage("'From Year' should be less than 'To Year'")
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();

                            return;
                        }*/

                        Intent intent = new Intent(getApplicationContext(), MonthlyTransactionList.class);
                        intent.putExtra("selectedYear", strYear);
                        intent.putExtra("selectedMonth1", (month1 + 1));
                        intent.putExtra("selectedMonth2", (month2 + 1));
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
                    Intent intent = new Intent(getApplicationContext(), Report.class);
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
        Intent i = new Intent(getApplicationContext(), Report.class);
        startActivity(i);
    }
}
