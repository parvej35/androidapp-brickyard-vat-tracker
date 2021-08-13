package brickyard.tracker.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import brickyard.tracker.bean.BrickyardBean;
import brickyard.tracker.bean.CategoryBean;
import brickyard.tracker.bean.CircleBean;
import brickyard.tracker.bean.DivisionBean;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.bean.SectorBean;
import brickyard.tracker.bean.UserProfileBean;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "brickyard_vat_tracker_test.db";

    public final String TBL_APP_USER_PROFILE = "tbl_app_user_profile";
    public final String TBL_CATEGORY = "tbl_category";

    public final String TBL_COMMISSIONERATE = "tbl_commissionerate";
    public final String TBL_DIVISION = "tbl_division";
    public final String TBL_CIRCLE = "tbl_circle";
    public final String TBL_SECTOR = "tbl_sector";
    public final String TBL_BRICKYARD = "tbl_brickyard";
    public final String TBL_RECORD = "tbl_record";

    public final String COL_ID = "id";
    public final String COL_NAME = "name";
    public final String COL_EMAIL = "email";
    public final String COL_PASSWORD = "password";
    public final String COL_ENABLED_PASSWORD_PROTECTION = "enablePassProtection";
    public final String COL_CURRENCY = "currency";
    public final String COL_DATE = "date";
    public final String COL_DAY = "day";
    public final String COL_MONTH = "month";
    public final String COL_YEAR = "year";
    public final String COL_DAY_NAME = "day_name";
    public final String COL_TIME = "time";
    public final String COL_NOTE = "note";
    public final String COL_AMOUNT = "amount";
    public final String COL_TYPE = "type";
    public final String COL_CATEGORY = "category";
    public final String COL_LANGUAGE = "language";
    public final String COL_COUNTRY = "country";
    public final String COL_SIGNEDOUT = "signedout";

    public final String COL_COMMISSIONERATE_NAME = "commissionerate_name";
    public final String COL_DIVISION_NAME = "division_name";
    public final String COL_CIRCLE_NAME = "circle_name";
    public final String COL_SECTOR_NAME = "sector_name";

    public final String COL_BRICKYARD_NAME = "brickyard_name";
    public final String COL_BRICKYARD_TRADE_MARK = "brickyard_trade_mark";
    public final String COL_BRICKYARD_ADDRESS = "brickyard_address";
    public final String COL_BRICKYARD_AREA = "brickyard_area";
    public final String COL_BRICKYARD_TYPE = "brickyard_type";
    public final String COL_BRICKYARD_STATUS = "brickyard_status";

    public final String COL_FINANCIAL_YEAR = "financial_year";
    public final String COL_INSTALLMENT_1 = "installment1";
    public final String COL_INSTALLMENT_2 = "installment2";
    public final String COL_INSTALLMENT_3 = "installment3";

    public final String COL_PRE_DUE_AMOUNT = "pre_due_amount";
    public final String COL_PRE_VAT_PAID_AMOUNT = "pre_vat_paid_amount";
    public final String COL_TOTAL_PAID_AMOUNT = "total_paid_amount";
    public final String COL_CURRENT_DUE_AMOUNT = "current_due_amount";

    private final String SQL_CREATE_TBL_USER_PROFILE =
            "CREATE TABLE " + TBL_APP_USER_PROFILE + " (" +
                    COL_ID + " INTEGER," +
                    COL_NAME + " TEXT," +
                    COL_EMAIL + " TEXT," +
                    COL_PASSWORD + " TEXT," +
                    COL_ENABLED_PASSWORD_PROTECTION + " TEXT," +
                    COL_COUNTRY + " TEXT," +
                    COL_LANGUAGE + " TEXT," +
                    COL_CURRENCY + " TEXT," +
                    COL_SIGNEDOUT + " TEXT)";

    private final String SQL_CREATE_TBL_COMMISSIONERATE =
            "CREATE TABLE " + TBL_COMMISSIONERATE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT)";

    private final String SQL_CREATE_TBL_DIVISION =
            "CREATE TABLE " + TBL_DIVISION + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_COMMISSIONERATE_NAME + " TEXT)";

    private final String SQL_CREATE_TBL_CIRCLE =
            "CREATE TABLE " + TBL_CIRCLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_COMMISSIONERATE_NAME + " TEXT, " + COL_DIVISION_NAME + " TEXT)";

    private final String SQL_CREATE_TBL_SECTOR =
            "CREATE TABLE " + TBL_SECTOR + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_COMMISSIONERATE_NAME + " TEXT," + COL_DIVISION_NAME + " TEXT," + COL_CIRCLE_NAME + " TEXT)";

    private final String SQL_CREATE_TBL_CATEGORY =
            "CREATE TABLE " + TBL_CATEGORY + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TYPE + " INTEGER," + COL_NAME + " TEXT)";

    private final String SQL_CREATE_TBL_BRICKYARD =
            "CREATE TABLE " + TBL_BRICKYARD + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_COMMISSIONERATE_NAME + " TEXT," + COL_DIVISION_NAME + " TEXT," + COL_CIRCLE_NAME + " TEXT," + COL_SECTOR_NAME + " TEXT)";

    private final String SQL_CREATE_TBL_RECORD =
           "CREATE TABLE " + TBL_RECORD + " (" +
                   COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                   COL_DATE + " LONG," +
                   COL_COMMISSIONERATE_NAME + " TEXT," +
                   COL_DIVISION_NAME + " TEXT," +
                   COL_CIRCLE_NAME + " TEXT," +
                   COL_SECTOR_NAME + " TEXT," +
                   COL_BRICKYARD_NAME + " TEXT," +
                   COL_BRICKYARD_TRADE_MARK + " TEXT," +
                   COL_BRICKYARD_ADDRESS + " TEXT," +
                   COL_BRICKYARD_AREA + " TEXT," +
                   COL_BRICKYARD_TYPE + " TEXT," +
                   COL_BRICKYARD_STATUS + " TEXT," +
                   COL_FINANCIAL_YEAR + " TEXT," +
                   COL_INSTALLMENT_1 + " TEXT," +
                   COL_INSTALLMENT_2 + " TEXT," +
                   COL_INSTALLMENT_3 + " TEXT," +
                   COL_PRE_DUE_AMOUNT + " TEXT," +
                   COL_PRE_VAT_PAID_AMOUNT + " TEXT," +
                   COL_TOTAL_PAID_AMOUNT + " TEXT," +
                   COL_CURRENT_DUE_AMOUNT + " TEXT," +
                   COL_NOTE + " TEXT)";


    private final String SQL_QUERY_INSERT_DEFAULT_USER_PROFILE =
            "INSERT INTO " + TBL_APP_USER_PROFILE + " (id, name, email, password, enablePassProtection, country, language, currency, signedout) VALUES (1, '', '', '', '0', '', '', '', '1')";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        //mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+TBL_CATEGORY);
        db.execSQL(SQL_CREATE_TBL_CATEGORY);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_COMMISSIONERATE);
        db.execSQL(SQL_CREATE_TBL_COMMISSIONERATE);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_DIVISION);
        db.execSQL(SQL_CREATE_TBL_DIVISION);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_CIRCLE);
        db.execSQL(SQL_CREATE_TBL_CIRCLE);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_SECTOR);
        db.execSQL(SQL_CREATE_TBL_SECTOR);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_RECORD);
        db.execSQL(SQL_CREATE_TBL_RECORD);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_BRICKYARD);
        db.execSQL(SQL_CREATE_TBL_BRICKYARD);

        db.execSQL("DROP TABLE IF EXISTS "+ TBL_APP_USER_PROFILE);
        db.execSQL(SQL_CREATE_TBL_USER_PROFILE);

        db.execSQL(SQL_QUERY_INSERT_DEFAULT_USER_PROFILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void truncateAllTransactionRecordAndCategoryInfo(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        db.execSQL("DROP TABLE IF EXISTS "+TBL_CATEGORY);
        db.execSQL(SQL_CREATE_TBL_CATEGORY);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_BRICKYARD);
        db.execSQL(SQL_CREATE_TBL_BRICKYARD);

        db.execSQL("DROP TABLE IF EXISTS "+TBL_RECORD);
        db.execSQL(SQL_CREATE_TBL_RECORD);
    }


    public long saveRecord(Context context, RecordBean recordBean){
        DbHelper mDbHelper = new DbHelper(context);

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COL_DATE, recordBean.getLongDate());
        values.put(COL_DIVISION_NAME, recordBean.getDivision());
        values.put(COL_CIRCLE_NAME, recordBean.getCircle());
        values.put(COL_SECTOR_NAME, recordBean.getSector());
        values.put(COL_BRICKYARD_NAME, recordBean.getBrickyardName());
        values.put(COL_BRICKYARD_ADDRESS, recordBean.getAddress());
        values.put(COL_BRICKYARD_AREA, recordBean.getArea());
        values.put(COL_BRICKYARD_TYPE, recordBean.getBrickType());
        values.put(COL_BRICKYARD_STATUS, recordBean.getStatus());
        values.put(COL_FINANCIAL_YEAR, recordBean.getFinancialYear());
        values.put(COL_INSTALLMENT_1, recordBean.getInstallment1());
        values.put(COL_INSTALLMENT_2, recordBean.getInstallment2());
        values.put(COL_INSTALLMENT_3, recordBean.getInstallment3());
        values.put(COL_PRE_DUE_AMOUNT, recordBean.getPreDueAmount());
        values.put(COL_PRE_VAT_PAID_AMOUNT, recordBean.getPreVatPaidAmount());
        values.put(COL_TOTAL_PAID_AMOUNT, recordBean.getTotalPaidAmount());
        values.put(COL_CURRENT_DUE_AMOUNT, recordBean.getCurrentDueAmount());
        values.put(COL_NOTE, recordBean.getNote());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_RECORD, null, values);

        mDbHelper.close();

        return newRowId;
    }

    public List<RecordBean> populateTransListByTypeSectorAndDateRange(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD+" ORDER BY date DESC LIMIT 100";

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<RecordBean> recordBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int type = cursor.getInt(1);
                String category = cursor.getString(2);
                String amount = cursor.getString(3);
                long date = cursor.getLong(4);
                String time = cursor.getString(5);
                String note = cursor.getString(6);
                int day = cursor.getInt(7);
                int month = cursor.getInt(8);
                int year = cursor.getInt(9);
                String dayName = cursor.getString(10);

                //recordBeanList.add(new RecordBean(id, type, date, time, note, amount, category, dayName, day, month, year));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return recordBeanList;
    }

    public List<RecordBean> populateFilteredTransactionList(Context context, int filteredTranType, List<String> filteredMonthList, List<String> filteredYearList) {

        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY =
                "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD+" ORDER BY date DESC";

        /*String STR_QUERY =
                "SELECT "+
                        COL_ID+", "+COL_TYPE+", "+COL_CATEGORY+", "+COL_AMOUNT+", "+COL_DATE+", "+COL_TIME+", "+COL_NOTE+
                        " FROM " +TBL_RECORD+" ORDER BY " +COL_DATE+" DESC";*/

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<RecordBean> recordBeanList = new ArrayList<>();

        try {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int type = cursor.getInt(1);
                String category = cursor.getString(2);
                String amount = cursor.getString(3);
                long date = cursor.getLong(4);
                String time = cursor.getString(5);
                String note = cursor.getString(6);
                int day = cursor.getInt(7);
                int month = cursor.getInt(8);
                int year = cursor.getInt(9);
                String dayName = cursor.getString(10);

                boolean monthMatched = true;
                boolean yearMatched = true;

                if(filteredTranType == 0 || filteredTranType == type) {

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                    if (filteredMonthList.size() > 0) { // CAUTION : do not place 0 instead of 1
                        if(filteredMonthList.indexOf(month) == -1) monthMatched = false;
                    }

                    if (filteredYearList.size() > 0) { // CAUTION : do not place 0 instead of 1
                        if(filteredYearList.indexOf(year) == -1) yearMatched = false;
                    }

                    if(monthMatched && yearMatched) {
                        //recordBeanList.add(new RecordBean(id, type, date, time, note, amount, category, dayName, day, month, year));
                    }

                }
            }

        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return recordBeanList;
    }


    public int deleteRecord(Context context, int id) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = COL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int deletedRows = db.delete(TBL_RECORD, selection, selectionArgs);

        mDbHelper.close();

        return deletedRows;
    }

    public int updateRecord(Context context, RecordBean recordBean){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        /*values.put(COL_DATE, recordBean.getDate());
        values.put(COL_TIME, recordBean.getTime());
        values.put(COL_CATEGORY, recordBean.getCategory());
        values.put(COL_DATE, recordBean.getDate());
        values.put(COL_AMOUNT, recordBean.getAmount());
        values.put(COL_NOTE, recordBean.getNote());
        values.put(COL_DAY, recordBean.getDay());
        values.put(COL_MONTH, recordBean.getMonth());
        values.put(COL_YEAR, recordBean.getYear());
        values.put(COL_DAY_NAME, recordBean.getDayName());*/

        // Which row to update, based on the id
        String selection = COL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(recordBean.getId()) };

        int count = db.update(TBL_RECORD, values, selection, selectionArgs);

        mDbHelper.close();
        return count;
    }

    public long saveDefaultUserProfile(Context context){
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COL_ID, 1);
        values.put(COL_NAME, "Account Manager");
        values.put(COL_EMAIL, "technovalleyit@gmail.com");
        values.put(COL_LANGUAGE, "English");
        values.put(COL_CURRENCY, "USD");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_APP_USER_PROFILE, null, values);

        mDbHelper.close();

        return newRowId;
    }


    public int countRecordByTypeAndCategoryName(Context context, int recordType, String categoryName) {
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT(id) FROM " +TBL_RECORD+ " WHERE type = "+recordType+" AND category = '" + categoryName + "'";
        Cursor cursor = db.rawQuery(STR_QUERY, null);
        cursor.moveToFirst();
        int total = cursor.getInt(0);
        cursor.close();

        mDbHelper.close();

        return total;
    }

    public double getTotalAmountByType(Context context, int typeId) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        double totalAmount = 0;

        String STR_QUERY = "SELECT SUM(amount) FROM " + TBL_RECORD + " WHERE type = " + typeId;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            cursor.moveToFirst();
            totalAmount = cursor.getDouble(0);
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return totalAmount;
    }

    public double getTotalAmountByTypeSectorAndDateRange(Context context, int typeId, String sector, long fromDate, long toDate) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        double totalAmount = 0;

        String STR_QUERY = "SELECT SUM(amount) FROM " + TBL_RECORD + " WHERE type = " + typeId + " AND date BETWEEN " +fromDate+ " AND " +toDate;;

        if(!sector.equalsIgnoreCase(Constant.ALL_SECTOR) && !sector.equalsIgnoreCase("")){
            STR_QUERY = "SELECT SUM(amount) FROM " + TBL_RECORD + " WHERE type = " + typeId + " AND category = '" +sector+ "' AND date BETWEEN " +fromDate+ " AND " +toDate;
        }

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            cursor.moveToFirst();
            totalAmount = cursor.getDouble(0);
        } finally {
            cursor.close();
            mDbHelper.close();
        }


        return totalAmount;
    }

    public double getTotalAmountByTypeAndDateRange(Context context, int typeId, long fromDate, long toDate) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        double totalAmount = 0;

        String STR_QUERY = "SELECT SUM(amount) FROM " + TBL_RECORD + " WHERE type = " + typeId + " AND date BETWEEN " +fromDate+ " AND " +toDate;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            cursor.moveToFirst();
            totalAmount = cursor.getDouble(0);
        } finally {
            cursor.close();
            mDbHelper.close();
        }


        return totalAmount;
    }

    public double getTotalAmountByTypeYearAndMonth(Context context, int typeId, int year, int fromMonth, long toMonth) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        double totalAmount = 0;

        String STR_QUERY =
                "SELECT SUM(amount) FROM " + TBL_RECORD + " WHERE type = " + typeId + " AND year = "+year+" AND month BETWEEN " +fromMonth+ " AND " +toMonth;

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            cursor.moveToFirst();
            totalAmount = cursor.getDouble(0);
        } finally {
            cursor.close();
            mDbHelper.close();
        }


        return totalAmount;
    }

    public int countExistingCategoryByName(Context context, String categoryName, int existingCategoryId, int typeId) {
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT(id) FROM " +TBL_CATEGORY+ " WHERE id != " + existingCategoryId + " AND type = "+typeId+" AND name = '"+categoryName+"' COLLATE NOCASE";
        Cursor cursor = db.rawQuery(STR_QUERY, null);
        cursor.moveToFirst();

        int totalExistingCategory = cursor.getInt(0);

        cursor.close();
        mDbHelper.close();

        return totalExistingCategory;
    }

    public String getCategoryByTypeAndName(Context context, int typeId, String name) {
        String categoryName = "";

        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT name FROM " +TBL_CATEGORY+ " WHERE type = " + typeId + " AND name = '"+name+"' COLLATE NOCASE";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            while (cursor.moveToNext()) {
                categoryName = cursor.getString(0);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return categoryName;
    }

    public int countCategoryByType(Context context, int typeId) {
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT(id) FROM " +TBL_CATEGORY+ " WHERE type = " + typeId;
        Cursor cursor = db.rawQuery(STR_QUERY, null);
        cursor.moveToFirst();
        int totalCategory = cursor.getInt(0);
        cursor.close();

        mDbHelper.close();

        return totalCategory;
    }


    //if category list is blank then save default category in DB
    public void saveDefaultCategory(Context context, int typeId, String[] cat_name_arr){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        for(int i = 0; i < cat_name_arr.length; i++) {
            ContentValues values = new ContentValues();

            values.put(COL_TYPE, typeId);
            values.put(COL_NAME, cat_name_arr[i]);

            // Insert the new row, returning the primary key value of the new row
            db.insert(TBL_CATEGORY, null, values);
        }
        mDbHelper.close();
    }

    public long saveCategory(Context context, CategoryBean categoryBean){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TYPE, categoryBean.getType());
        values.put(COL_NAME, categoryBean.getName());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_CATEGORY, null, values);

        mDbHelper.close();

        return newRowId;
    }

    public int updateCategory(Context context, CategoryBean categoryBean){
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_NAME, categoryBean.getName());

        // Which row to update, based on the id
        String selection = COL_ID + " = ?";
        String[] selectionArgs = { ""+ categoryBean.getId()+"" };

        int count = db.update(TBL_CATEGORY, values, selection, selectionArgs);

        mDbHelper.close();
        return count;
    }

    public int deleteCategory(Context context, int id) {
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = COL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int deletedRows = db.delete(TBL_CATEGORY, selection, selectionArgs);

        mDbHelper.close();

        return deletedRows;
    }

    public List<CategoryBean> getCategoryListByType(Context context, int typeId) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT id, type, name FROM " +TBL_CATEGORY+ " WHERE type = " + typeId + " ORDER BY name ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<CategoryBean> categoryBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int type = cursor.getInt(1);
                String name = cursor.getString(2);

                categoryBeanList.add(new CategoryBean(id, name, type));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return categoryBeanList;
    }

    public List<String> getCategoryNameListByType(Context context, int typeId) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT name FROM " +TBL_CATEGORY+ " WHERE type = " + typeId + " ORDER BY name ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<String> categoryNameList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                categoryNameList.add(name);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return categoryNameList;
    }

    public String[] getCategoryNameByType(Context context, int typeId) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT name FROM " +TBL_CATEGORY+ " WHERE type = " + typeId + " ORDER BY name ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<String> categoryNameList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                categoryNameList.add(name);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        String[] categoryNameArray = new String[categoryNameList.size()];
        return categoryNameList.toArray(categoryNameArray);

        //return (String[]) categoryNameList.toArray();
    }

    public int countTotalTransaction(Context context) {
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT(id) FROM " +TBL_RECORD;
        Cursor cursor = db.rawQuery(STR_QUERY, null);
        cursor.moveToFirst();
        int totalTransaction = cursor.getInt(0);
        cursor.close();

        mDbHelper.close();

        return totalTransaction;
    }

    public UserProfileBean getApplicationUserProfile(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT id, name, email, country, language, currency, password, enablePassProtection, signedout FROM " + TBL_APP_USER_PROFILE;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        cursor.moveToFirst();

        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String email = cursor.getString(2);
        String country = cursor.getString(3);
        String language = cursor.getString(4);
        String currency = cursor.getString(5);
        String password = cursor.getString(6);
        String enablePassProtection = cursor.getString(7);
        String signedOut = cursor.getString(8);

        cursor.close();

        return new UserProfileBean(name, email, country, language, currency, password, enablePassProtection, signedOut);
    }

    public String getApplicationUserName(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT name FROM " + TBL_APP_USER_PROFILE;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        cursor.moveToFirst();
        String name = cursor.getString(0);
        cursor.close();

        return name;
    }

    public String getApplicationUserEmail(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT email FROM " + TBL_APP_USER_PROFILE;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        cursor.moveToFirst();
        String email = cursor.getString(0);
        cursor.close();

        return email;
    }

    public String getApplicationUserCurrencySymbol(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT currency FROM " + TBL_APP_USER_PROFILE;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        cursor.moveToFirst();
        String currency = cursor.getString(0); //"Pounds, £"
        cursor.close();

        String[] currencyArr = currency.split(Constant.CURRENCY_SPILTER);

        return currencyArr[1].trim();
        //return currencyArr[1].replace("(", "").replace(")", "");
    }

    public int updateApplicationUserProfile(Context context, UserProfileBean userProfileBean){
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_NAME, userProfileBean.getName());
        values.put(COL_EMAIL, userProfileBean.getEmail());
        values.put(COL_COUNTRY, userProfileBean.getCountry());
        values.put(COL_CURRENCY, userProfileBean.getCurrency());
        values.put(COL_LANGUAGE, userProfileBean.getLanguage());
        values.put(COL_PASSWORD, userProfileBean.getPassword());
        values.put(COL_ENABLED_PASSWORD_PROTECTION, userProfileBean.getEnablePassword());
        values.put(COL_SIGNEDOUT, userProfileBean.getSignedOut());

        int count = db.update(TBL_APP_USER_PROFILE, values, null, null);

        mDbHelper.close();
        return count;
    }

    public int updateApplicationUserPassword(Context context, String newPassword){
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_PASSWORD, newPassword);

        int count = db.update(TBL_APP_USER_PROFILE, values, null, null);

        mDbHelper.close();
        return count;
    }

    public int updateApplicationUserSignout(Context context, String strSignOut){
        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_SIGNEDOUT, strSignOut);

        int count = db.update(TBL_APP_USER_PROFILE, values, null, null);

        mDbHelper.close();
        return count;
    }

    public int countDatewiseTransactionList(Context context, long fromDate, long toDate) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT(id) FROM " +TBL_RECORD+ " WHERE date BETWEEN " +fromDate+ " AND " +toDate;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        int total = 0;
        if (cursor.moveToFirst())
            total = cursor.getInt(0);

        cursor.close();
        return total;
    }

    public List<RecordBean> getTransListByTypeSectorAndDateRange(Context context, int transactionType, String sector, long fromDate, long toDate) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String WHERE_CLAUSE = " date BETWEEN " +fromDate+ " AND " +toDate+ " ";

        if (transactionType == Constant.TYPE_INC || transactionType == Constant.TYPE_EXP) {
            WHERE_CLAUSE += " AND type = " +transactionType+ " ";
        }

        if(!sector.equalsIgnoreCase(Constant.ALL_SECTOR) && !sector.equalsIgnoreCase("")){
            WHERE_CLAUSE += " AND category = '" +sector+ "' ";
        }


        String STR_QUERY =
                "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD+" WHERE " +WHERE_CLAUSE+ " ORDER BY date, time";

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<RecordBean> recordBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int type = cursor.getInt(1);
                String category = cursor.getString(2);
                String amount = cursor.getString(3);
                long date = cursor.getLong(4);
                String time = cursor.getString(5);
                String note = cursor.getString(6);
                int day = cursor.getInt(7);
                int month = cursor.getInt(8);
                int year = cursor.getInt(9);
                String dayName = cursor.getString(10);

                //recordBeanList.add(new RecordBean(id, type, date, time, note, amount, category, dayName, day, month, year));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return recordBeanList;
    }

    public List<RecordBean> getTransListByTypeSectorAndDate(Context context, int transactionType, String sector, long longDate) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String WHERE_CLAUSE = " date = " +longDate+ " ";

        if (transactionType == Constant.TYPE_INC || transactionType == Constant.TYPE_EXP) {
            WHERE_CLAUSE += " AND type = " +transactionType+ " ";
        }

        if(!sector.equalsIgnoreCase(Constant.STR_ALL) && !sector.equalsIgnoreCase("")){
            WHERE_CLAUSE += " AND category = '" +sector+ "' ";
        }


        String STR_QUERY =
                "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD+" WHERE " +WHERE_CLAUSE+ " ORDER BY date, time";

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<RecordBean> recordBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int type = cursor.getInt(1);
                String category = cursor.getString(2);
                String amount = cursor.getString(3);
                long date = cursor.getLong(4);
                String time = cursor.getString(5);
                String note = cursor.getString(6);
                int day = cursor.getInt(7);
                int month = cursor.getInt(8);
                int year = cursor.getInt(9);
                String dayName = cursor.getString(10);

                //recordBeanList.add(new RecordBean(id, type, date, time, note, amount, category, dayName, day, month, year));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return recordBeanList;
    }

    public List<RecordBean> getTransListByDateRange(Context context, long fromDate, long toDate) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY =
                "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD+
                        " WHERE date BETWEEN " +fromDate+ " AND " +toDate+ " ORDER BY date";


        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<RecordBean> recordBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int type = cursor.getInt(1);
                String category = cursor.getString(2);
                String amount = cursor.getString(3);
                long date = cursor.getLong(4);
                String time = cursor.getString(5);
                String note = cursor.getString(6);
                int day = cursor.getInt(7);
                int month = cursor.getInt(8);
                int year = cursor.getInt(9);
                String dayName = cursor.getString(10);

                //recordBeanList.add(new RecordBean(id, type, date, time, note, amount, category, dayName, day, month, year));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return recordBeanList;
    }

    public Map<String, String> getTransListByYearAndMonth(Context context, int type, int selectedYear, int fromMonth, long toMonth) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY =
                "SELECT SUM(amount), month, year FROM " + TBL_RECORD +
                        " WHERE type = "+type+" AND year = "+selectedYear+" AND month BETWEEN " +fromMonth+ " AND " +toMonth+
                        " GROUP BY month, year ORDER BY month, year";

        Cursor cursor = db.rawQuery(STR_QUERY, null);

        Map<String, String> recordList = new HashMap<>();

        try {
            while (cursor.moveToNext()) {
                String amount = cursor.getString(0);
                String month = cursor.getString(1);
                String year = cursor.getString(2);

                recordList.put(year+"-"+month, amount);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return recordList;
    }

    //----DIVISION----------------------------------------------------------------------
    public String[] getDivisionNameListByCommissionerate(Context context, String commissionerate_name) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_NAME+" FROM " +TBL_DIVISION+ " WHERE " +COL_COMMISSIONERATE_NAME+ " = '" +commissionerate_name+ "' ORDER BY "+COL_NAME+" ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<String> nameList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                nameList.add(name);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        String[] nameArray = new String[nameList.size()];
        return nameList.toArray(nameArray);

    }

    public int countDivisionByNameAndCommissionerate(Context context, String newName, String commissionerate_name) {
        int total = 0;

        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT("+COL_NAME+") FROM " +TBL_DIVISION+ " WHERE UPPER("+COL_NAME+") = UPPER('"+newName+"') AND UPPER("+COL_COMMISSIONERATE_NAME+") = UPPER('"+commissionerate_name+"')";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            while (cursor.moveToNext()) {
                total = cursor.getInt(0);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return total;
    }

    public long saveDivision(Context context, DivisionBean division){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, division.getName());
        values.put(COL_COMMISSIONERATE_NAME, division.getCommissionerate());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_DIVISION, null, values);

        mDbHelper.close();

        return newRowId;
    }

    //----CIRCLE----------------------------------------------------------------------
    public String[] getCircleNameListByDivision(Context context, String division_name) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_NAME+" FROM " +TBL_CIRCLE+ " WHERE UPPER("+COL_DIVISION_NAME+") = UPPER('"+division_name+"') ORDER BY "+COL_NAME+" ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<String> nameList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                nameList.add(name);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        String[] nameArray = new String[nameList.size()];
        return nameList.toArray(nameArray);

    }

    public int countCircleByNameAndDivision(Context context, String newName, String division_name) {
        int total = 0;

        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT("+COL_NAME+") FROM " +TBL_CIRCLE+ " WHERE UPPER("+COL_NAME+") = UPPER('"+newName+"') AND UPPER("+COL_DIVISION_NAME+") = UPPER('"+division_name+"')";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            while (cursor.moveToNext()) {
                total = cursor.getInt(0);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return total;
    }

    public long saveCircle(Context context, CircleBean circle){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, circle.getName());
        values.put(COL_COMMISSIONERATE_NAME, circle.getCommissionerate());
        values.put(COL_DIVISION_NAME, circle.getDivision());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_CIRCLE, null, values);

        mDbHelper.close();

        return newRowId;
    }

    //----SECTOR----------------------------------------------------------------------
    public String[] getSectorNameListByCircle(Context context, String circle_name) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_NAME+" FROM " +TBL_SECTOR+ " WHERE UPPER("+COL_CIRCLE_NAME+") = UPPER('"+circle_name+"') ORDER BY "+COL_NAME+" ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<String> nameList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                nameList.add(name);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        String[] nameArray = new String[nameList.size()];
        return nameList.toArray(nameArray);

    }

    public int countSectorByNameAndCircle(Context context, String newName, String circle_name) {
        int total = 0;

        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT COUNT("+COL_NAME+") FROM " +TBL_SECTOR+ " WHERE UPPER("+COL_NAME+") = UPPER('"+newName+"') AND UPPER("+COL_CIRCLE_NAME+") = UPPER('"+circle_name+"')";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            while (cursor.moveToNext()) {
                total = cursor.getInt(0);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return total;
    }

    public long saveSector(Context context, SectorBean sector){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, sector.getName());
        values.put(COL_COMMISSIONERATE_NAME, sector.getCommissionerate());
        values.put(COL_DIVISION_NAME, sector.getDivision());
        values.put(COL_CIRCLE_NAME, sector.getCircle());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_SECTOR, null, values);

        mDbHelper.close();

        return newRowId;
    }

    //----BRICKYARD----------------------------------------------------------------------
    public List<BrickyardBean> getAllBrickYardList(Context context, int limit) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_ID+", "+COL_NAME+", "+COL_DIVISION_NAME+", "+COL_CIRCLE_NAME+", "+COL_SECTOR_NAME+" FROM " +TBL_BRICKYARD+
                " ORDER BY "+COL_NAME+" ASC LIMIT "+limit;
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<BrickyardBean> brickyardBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String division = cursor.getString(2);
                String circle = cursor.getString(3);
                String sector = cursor.getString(4);

                brickyardBeanList.add(new BrickyardBean(id, name, division, circle, sector));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return brickyardBeanList;
    }

    public List<BrickyardBean> getBrickYardListByDivisionCircleSector(Context context, String division_name, String circle_name, String sector_name) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_ID+", "+COL_NAME+", "+COL_DIVISION_NAME+", "+COL_CIRCLE_NAME+", "+COL_SECTOR_NAME+" FROM " +TBL_BRICKYARD+
                " WHERE " +COL_DIVISION_NAME+ " = '" +division_name+ "' AND " +COL_CIRCLE_NAME+ " = '" +circle_name+ "' AND " +COL_SECTOR_NAME+ " = '" +sector_name+ "'" +
                " ORDER BY "+COL_NAME+" ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<BrickyardBean> brickyardBeanList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String division = cursor.getString(2);
                String circle = cursor.getString(3);
                String sector = cursor.getString(4);

                brickyardBeanList.add(new BrickyardBean(id, name, division, circle, sector));
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return brickyardBeanList;
    }

    public String[] getBrickYardNameListByDivisionCircleSector(Context context, String division_name, String circle_name, String sector_name) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_NAME+" FROM " +TBL_BRICKYARD+
                " WHERE " +COL_DIVISION_NAME+ " = '" +division_name+ "' AND " +COL_CIRCLE_NAME+ " = '" +circle_name+ "' AND " +COL_SECTOR_NAME+ " = '" +sector_name+ "'" +
                " ORDER BY "+COL_NAME+" ASC";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        List<String> brickYardNameList = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                brickYardNameList.add(name);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        String[] categoryNameArray = new String[brickYardNameList.size()];
        return brickYardNameList.toArray(categoryNameArray);

    }

    public String getBrickyardNameByDivisionAndCircleAndSector(Context context, String division, String circle, String sector) {
        String name = "";

        DbHelper mDbHelper = new DbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String STR_QUERY = "SELECT "+COL_NAME+" FROM " +TBL_BRICKYARD+ " WHERE "+COL_DIVISION_NAME+" = '" + division + "' AND "+COL_CIRCLE_NAME+" = '"+circle+"' AND "+COL_SECTOR_NAME+" = '"+sector+"'";
        Cursor cursor = db.rawQuery(STR_QUERY, null);

        try {
            while (cursor.moveToNext()) {
                name = cursor.getString(0);
            }
        } finally {
            cursor.close();
            mDbHelper.close();
        }

        return name;
    }

    public long saveBrickyard(Context context, BrickyardBean brickyard){
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, brickyard.getName());
        values.put(COL_DIVISION_NAME, brickyard.getDivision());
        values.put(COL_CIRCLE_NAME, brickyard.getCircle());
        values.put(COL_SECTOR_NAME, brickyard.getSector());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TBL_BRICKYARD, null, values);

        mDbHelper.close();

        return newRowId;
    }

    //----BACKUP----------------------------------------------------------------------
    public boolean backupDatabase(Context context, String outputFileName) {
        try {
            String strData = "";

            DbHelper mDbHelper = new DbHelper(context);
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            FileWriter outputFileWriter = new FileWriter(outputFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(outputFileWriter);

            //----------------------------------------
            String STR_QUERY = "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD;
            Cursor cursor = db.rawQuery(STR_QUERY, null);

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String type = cursor.getString(1);
                String category = cursor.getString(2);
                String amount = cursor.getString(3);
                String date = cursor.getString(4);
                String time = cursor.getString(5);
                String note = cursor.getString(6);
                String day = cursor.getString(7);
                String month = cursor.getString(8);
                String year = cursor.getString(9);
                String dayName = cursor.getString(10);

                strData = TBL_RECORD + "#" + id + "#" + type + "#" + category + "#" + amount + "#" + date + "#" + time + "#" + note + "#" + day + "#" + month + "#" + year + "#" + dayName;

                bufferedWriter.write(strData);
                bufferedWriter.newLine();
            }

            //----------------------------------------
            STR_QUERY = "SELECT id, type, name FROM " +TBL_CATEGORY;
            cursor = db.rawQuery(STR_QUERY, null);

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String type = cursor.getString(1);
                String name = cursor.getString(2);

                strData = TBL_CATEGORY + "#" + id + "#" + type + "#" + name;

                bufferedWriter.write(strData);
                bufferedWriter.newLine();
            }

            //----------------------------------------
            outputFileWriter.flush();

            bufferedWriter.close();
            outputFileWriter.close();

            cursor.close();
            mDbHelper.close();

            return true;
        } catch(Exception ex) {
            File file = new File(outputFileName);
            if(file.exists()) {
                file.delete();
            }
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();

            return false;
        }
    }



    public boolean restoreDatabase(Context context, String inputFileName) {
        try {
            String line = "";

            truncateAllTransactionRecordAndCategoryInfo(context);

            DbHelper mDbHelper = new DbHelper(context);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // FileReader reads text files in the default encoding.
            FileReader inputFileReader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(inputFileReader);

            //----------------------------------------
            String STR_QUERY = "SELECT id, type, category, amount, date, time, note, day, month, year, day_name FROM " +TBL_RECORD;
            Cursor cursor = db.rawQuery(STR_QUERY, null);

            //tbl_record#id#type#category#amount#date#time#note#day#month#year#dayName;// 0...11
            //tbl_category#id#type#name;  // 0...3

            while((line = bufferedReader.readLine()) != null) {
                ContentValues values = new ContentValues();

                String[] dataArray = line.split("#");

                if(dataArray[0].equalsIgnoreCase(TBL_CATEGORY)) {
                    values.put(COL_TYPE, dataArray[2]);
                    values.put(COL_NAME, dataArray[3]);

                    db.insert(TBL_CATEGORY, null, values);
                }
                else if(dataArray[0].equalsIgnoreCase(TBL_RECORD)) {
                    values.put(COL_TYPE, dataArray[2]);
                    values.put(COL_CATEGORY, dataArray[3]);
                    values.put(COL_AMOUNT, dataArray[4]);
                    values.put(COL_DATE, dataArray[5]);
                    values.put(COL_TIME, dataArray[6]);
                    values.put(COL_NOTE, dataArray[7]);
                    values.put(COL_DAY, dataArray[8]);
                    values.put(COL_MONTH, dataArray[9]);
                    values.put(COL_YEAR, dataArray[10]);
                    values.put(COL_DAY_NAME, dataArray[11]);

                    db.insert(TBL_RECORD, null, values);
                }

            }

            // Always close files.
            bufferedReader.close();

            cursor.close();
            mDbHelper.close();

            return true;
        } catch(Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();

            return false;
        }
    }

}