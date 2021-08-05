package brickyard.tracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import brickyard.tracker.adapter.TransactionAdapter;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class TransactionFilter extends AppCompatActivity {

    TransactionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setTitle("Filter option");
            setContentView(R.layout.transaction_filter);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            DbHelper dbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            List<String> monthList = new ArrayList<>();
            List<String> yearList = new ArrayList<>();
            String STR_QUERY = "SELECT DISTINCT (" + dbHelper.COL_DATE + ") FROM " + dbHelper.TBL_RECORD +"";
            Cursor cursor1 = db.rawQuery(STR_QUERY, null);

            while (cursor1.moveToNext()) {
                String strDate = cursor1.getString(0);

                String date = dateFormatter.format(new Date(Long.parseLong(strDate)));
                String dateArr[] = date.split("-");

                if(!yearList.contains(dateArr[0])) {
                    yearList.add(dateArr[0]);
                }

                if(!monthList.contains(dateArr[1])) {
                    monthList.add(dateArr[1]);
                }
            }
            cursor1.close();

            Collections.sort(monthList);

            LinearLayout monthFilter = (LinearLayout) findViewById(R.id.month);
            for (int counter = 0; counter < monthList.size(); counter++) {
                String fullMonthName = Constant.getFullMonthNameFromNumber(monthList.get(counter));

                CheckBox monthChecbBox = new CheckBox(this);
                monthChecbBox.setId(counter); // 05,10,12 etc...
                monthChecbBox.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                monthChecbBox.setText(fullMonthName);
                //monthChecbBox.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.layout_border));

                monthFilter.addView(monthChecbBox);
            }

            Collections.sort(yearList);
            LinearLayout yearFilter = (LinearLayout) findViewById(R.id.year);
            for (int counter = 0; counter < yearList.size(); counter++) {
                CheckBox yearCheckBox = new CheckBox(this);
                yearCheckBox.setId(counter);
                yearCheckBox.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                yearCheckBox.setText(yearList.get(counter));
                //yearCheckBox.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.layout_border));

                yearFilter.addView(yearCheckBox);
            }

            List<String> categoryList = new ArrayList<>();
            STR_QUERY = "SELECT DISTINCT " + dbHelper.COL_CATEGORY + " FROM " + dbHelper.TBL_RECORD + " ORDER BY " + dbHelper.COL_CATEGORY +"";
            Cursor cursor2 = db.rawQuery(STR_QUERY, null);
            while (cursor2.moveToNext()) {
                categoryList.add(cursor2.getString(0));
            }
            cursor2.close();

            Collections.sort(categoryList);
            /*LinearLayout categoryFilter = (LinearLayout) findViewById(R.id.category);
            for (int counter = 0; counter < categoryList.size(); counter++) {
                CheckBox catCheckBox = new CheckBox(this);
                catCheckBox.setId(counter);
                catCheckBox.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                catCheckBox.setText(categoryList.get(counter));
                //catCheckBox.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.layout_border));

                categoryFilter.addView(catCheckBox);
            }*/

            dbHelper.close();

            Button btnCancelFilter = (Button) findViewById(R.id.btnCancelFilter);
            btnCancelFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), TransactionList.class);
                    startActivity(intent);
                }
            });

            Button btnFilter=(Button) findViewById(R.id.btnFilter);
            btnFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.transactionType);
                        int radioButtonID = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                        String transactionType = (String) radioButton.getText();

                        StringBuffer filteredMonth = new StringBuffer();

                        LinearLayout monthLayout = (LinearLayout) findViewById(R.id.month);
                        for (int x = 0; x < monthLayout.getChildCount();x++){
                            CheckBox cb = (CheckBox) monthLayout.getChildAt(x);
                            if(cb.isChecked()){
                                filteredMonth.append(cb.getText()).append("#");
                            }
                        }

                        StringBuffer filteredYear = new StringBuffer();
                        LinearLayout yearLayout = (LinearLayout) findViewById(R.id.year);
                        for (int x = 0; x < yearLayout.getChildCount();x++){
                            CheckBox cb = (CheckBox) yearLayout.getChildAt(x);
                            if(cb.isChecked()){
                                filteredYear.append(cb.getText()).append("#");
                            }
                        }

                        /*StringBuffer filteredCategory = new StringBuffer();
                        LinearLayout categoryLayout = (LinearLayout) findViewById(R.id.category);
                        for (int x = 0; x < categoryLayout.getChildCount();x++){
                            CheckBox cb = (CheckBox) categoryLayout.getChildAt(x);
                            if(cb.isChecked()){
                                filteredCategory.append(cb.getText()).append("#");
                            }
                        }*/

                        Intent intent = new Intent(getApplicationContext(), TransactionList.class);
                        intent.putExtra("transactionType", transactionType);
                        intent.putExtra("filteredMonth", filteredMonth.toString());
                        intent.putExtra("filteredYear", filteredYear.toString());
                        //intent.putExtra("filteredCategory", filteredCategory.toString());

                        startActivity(intent);

                        //Toast.makeText(getApplicationContext(), filteredCategory.toString() +" - "+ filteredMonth.toString() +" - "+ filteredYear.toString(), Toast.LENGTH_LONG).show();
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
        Intent i = new Intent(getApplicationContext(), TransactionList.class);
        startActivity(i);
    }
}