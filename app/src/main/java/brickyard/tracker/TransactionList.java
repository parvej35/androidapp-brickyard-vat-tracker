package brickyard.tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import brickyard.tracker.adapter.TransactionAdapter;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class TransactionList extends AppCompatActivity {

    ListView listView;
    TransactionAdapter adapter;
    String currencySymbol;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_activity);
            setTitle("All Transactions");

            listView = (ListView) findViewById(R.id.transaction_list);

            String strTransactionType = "";
            if(getIntent().getStringExtra("transactionType") != null) {
                strTransactionType = (String) getIntent().getStringExtra("transactionType");
            }

            String filteredMonth = "";
            if(getIntent().getStringExtra("filteredMonth") != null) {
                filteredMonth = (String) getIntent().getStringExtra("filteredMonth");
            }

            String filteredYear = "";
            if(getIntent().getStringExtra("filteredYear") != null) {
                filteredYear = (String) getIntent().getStringExtra("filteredYear");
            }

            DbHelper dbHelper = new DbHelper(getApplicationContext());

            List<RecordBean> recordBeanList = new ArrayList<>();
            if(strTransactionType.equalsIgnoreCase("") && filteredMonth.equalsIgnoreCase("") && filteredYear.equalsIgnoreCase("")) {
                recordBeanList = dbHelper.populateTransListByTypeSectorAndDateRange(getApplicationContext());
            } else {
                int transactionType = 0;
                if(!strTransactionType.equalsIgnoreCase("")) {
                    if(strTransactionType.equalsIgnoreCase("Income")) {
                        transactionType = Constant.TYPE_INC;
                    } else if(strTransactionType.equalsIgnoreCase("Expense")) {
                        transactionType = Constant.TYPE_EXP;
                    }
                }

                //recordBeanList = dbHelper.populateFilteredTransactionList(context, filteredCategory, transactionType, filteredMonth, filteredYear);

                //List categoryList = new ArrayList();
                List<String> monthList = new ArrayList<String>();
                List<String> yearList = new ArrayList<String>();

                /*if(filteredCategory != ""){
                    String[] strArr = filteredCategory.split("#");
                    for (int i = 0; i < strArr.length; i++){
                        categoryList.add(strArr[i]);
                    }
                }*/

                if(filteredMonth != ""){
                    String[] strArr = filteredMonth.split("#");
                    for (int i = 0; i < strArr.length; i++){
                        monthList.add(strArr[i]);
                    }
                }

                if(filteredYear != ""){
                    String[] strArr = filteredYear.split("#");
                    for (int i = 0; i < strArr.length; i++){
                        yearList.add(strArr[i]);
                    }
                }

                /*for (int count = 0; count < categoryList.size(); count++) {
                    String filteredCat = String.valueOf(categoryList.get(count));
                    Toast.makeText(context, "--> " +count+") "+filteredCat, Toast.LENGTH_LONG).show();
                }*/

                //Toast.makeText(context, String.valueOf(categoryList.size()), Toast.LENGTH_LONG).show();
                //recordBeanList = dbHelper.populateFilteredTransactionList(context, categoryList, transactionType, monthList, yearList);

                recordBeanList = dbHelper.populateFilteredTransactionList(getApplicationContext(), transactionType, monthList, yearList);

            }

            adapter = new TransactionAdapter(getApplicationContext(), recordBeanList);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View view, int position, long id) {
                    RecordBean recordBean = (RecordBean) listView.getItemAtPosition(position);

                    Intent intent = new Intent(getApplicationContext(), TransactionDetails.class);
                    intent.putExtra("recordObj", recordBean);
                    startActivity(intent);
                }
            });


            RelativeLayout layoutFilter = (RelativeLayout) findViewById(R.id.filter);
            layoutFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), TransactionFilter.class);
                    startActivity(intent);
                }
            });

            /*if(recordBeanList.size() == 0) {
                layoutFilter.setVisibility(View.GONE);
            } else {
                layoutFilter.setVisibility(View.VISIBLE);
            }*/

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
