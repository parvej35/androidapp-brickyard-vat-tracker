package brickyard.tracker.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import brickyard.tracker.Dashboard;
import brickyard.tracker.R;
import brickyard.tracker.adapter.CategoryAdapter;
import brickyard.tracker.bean.CategoryBean;
import brickyard.tracker.util.Constant;
import brickyard.tracker.util.DbHelper;

public class AddCategory extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView listView;
    CategoryAdapter adapter;
    SearchView editsearch;

    int recordType = 0;

    final Context context = this;
    final DbHelper dbHelper = new DbHelper(context);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.category_activity);

            Intent myIntent = getIntent(); // gets the previously created intent
            recordType = Integer.parseInt(myIntent.getStringExtra("recordType"));
            //Toast.makeText(getApplicationContext(), String.valueOf(recordType), Toast.LENGTH_LONG).show();

            if(recordType == Constant.TYPE_INC){
                setTitle("Income Sectors");
            } else if(recordType == Constant.TYPE_EXP){
                setTitle("Expense Sectors");
            }

            // Locate the ListView in category_activity.xml
            listView = (ListView) findViewById(R.id.listview);

            List<CategoryBean> categoryBeanList = dbHelper.getCategoryListByType(context, recordType);
            adapter = new CategoryAdapter(context, categoryBeanList);
            listView.setAdapter(adapter);

            // When the user clicks on the ListItem(country)
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View view, int position, long id) {
                    CategoryBean categoryBean = (CategoryBean) listView.getItemAtPosition(position);
                    showPromptDialogToUpdate(categoryBean);
                }
            });

            // Locate the EditText in list_view_main.xml
            editsearch = (SearchView) findViewById(R.id.search);
            editsearch.setOnQueryTextListener(this);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View promptView = layoutInflater.inflate(R.layout.name_entry_prompt, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(promptView);

                    final EditText input = (EditText) promptView.findViewById(R.id.name);

                    TextView textViewSector = (TextView) promptView.findViewById(R.id.textViewSector);
                    if(recordType == Constant.TYPE_INC){
                        textViewSector.setText("Income Sector Name :");
                    } else if(recordType == Constant.TYPE_EXP){
                        textViewSector.setText("Expense Sector Name :");
                    }

                    // setup a dialog window
                    alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String categoryName = input.getText().toString().trim();
                            categoryName = categoryName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                            if(categoryName.length() > 0){
                                String existingCatName = dbHelper.getCategoryByTypeAndName(context, recordType, categoryName);
                                if(existingCatName == null || existingCatName.equalsIgnoreCase("")) {
                                    dbHelper.saveCategory(context, new CategoryBean(categoryName, recordType));
                                }

                                List<CategoryBean> categoryBeanList = dbHelper.getCategoryListByType(context, recordType);
                                adapter = new CategoryAdapter(context, categoryBeanList);
                                listView.setAdapter(adapter);

                                Toast.makeText(context, "New sector name saved.", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    // create an alert dialog
                    AlertDialog alertD = alertDialogBuilder.create();
                    alertD.show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
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
        Intent i = new Intent(AddCategory.this, Dashboard.class);
        startActivity(i);
    }

    private void showPromptDialogToUpdate(final CategoryBean categoryBean){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.name_entry_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.name);
        input.setText(categoryBean.getName());

        TextView textViewSector = (TextView) promptView.findViewById(R.id.textViewSector);
        if(categoryBean.getType() == Constant.TYPE_INC){
            textViewSector.setText("Income Sector Name :");
            //alertDialogBuilder.setTitle("Income Sector Name");
        } else if(categoryBean.getType() == Constant.TYPE_EXP){
            textViewSector.setText("Expense Sector Name :");
            //alertDialogBuilder.setTitle("Expense Sector Name");
        }

        alertDialogBuilder
        .setCancelable(false)
        //.setTitle("Dialog Button")
        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String newCategoryName = input.getText().toString().trim();
                newCategoryName = newCategoryName.replace(Constant.NOT_ALLOWED_CHAR, Constant.REPLACED_CHAR);

                if(newCategoryName.length() > 0){
                    int totalExistingCategory = dbHelper.countExistingCategoryByName(context, newCategoryName, categoryBean.getId(), categoryBean.getType());

                    if(totalExistingCategory == 0) {
                        categoryBean.setName(newCategoryName);
                        dbHelper.updateCategory(context, categoryBean);

                        List<CategoryBean> categoryBeanList = dbHelper.getCategoryListByType(context, categoryBean.getType());
                        adapter = new CategoryAdapter(context, categoryBeanList);
                        listView.setAdapter(adapter);

                        Toast.makeText(context, "Sector name successfully updated.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Same sector name already exist.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        })
        .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    int total = dbHelper.countRecordByTypeAndCategoryName(context, categoryBean.getType(), categoryBean.getName());
                    if (total > 0) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder
                        .setIcon(android.R.drawable.ic_delete)
                        .setCancelable(false)
                        .setTitle("Failed !!!")
                        .setMessage("This sector can not be deleted. "+total + " transaction(s) are associated with this sector.")
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        // create an alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Deleting sector")
                        .setMessage("Are you sure to delete this sector information?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int count = dbHelper.deleteCategory(context, categoryBean.getId());
                                if(count > 0) {
                                    List<CategoryBean> categoryBeanList = dbHelper.getCategoryListByType(context, categoryBean.getType());
                                    adapter = new CategoryAdapter(context, categoryBeanList);
                                    listView.setAdapter(adapter);

                                    Toast.makeText(getApplicationContext(), "Sector information successfully deleted.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed to delete sector information.", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                    }
                } catch(Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        // create an alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}