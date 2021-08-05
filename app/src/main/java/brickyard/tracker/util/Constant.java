package brickyard.tracker.util;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Constant {

    public static final String MONEY_TRACKER_DIR = "MoneyTracker";
    public static final String DEFAULT_LAN = "EN";
    public static final String STR_ALL = "ALL";
    public static final String ALL_SECTOR = "- All Sector -";
    public static final String SELECT = "- Select -";
    public static final String BOTH_INC_AND_EXP = "Both (Income and Expense)";
    public static final String STR_INCOME = "Income";
    public static final String STR_EXPENSE = "Expense";

    public static final String DB_EXTENSION = ".db";

    public static final String LAN_EN = "EN";
    public static final String LAN_BN = "BN";
    public static final String CURRENCY_SPILTER = ", ";

    public static final String NOT_ALLOWED_CHAR = "#";
    public static final String REPLACED_CHAR = "-";

    public static final String HASH_TAG_WITH_SPACE = " # ";
    public static final String FIRST_BRACKET_START = "(";
    public static final String FIRST_BRACKET_END = ")";

    public static final String APP_DIRECTORY = Environment.getExternalStorageDirectory() + File.separator + MONEY_TRACKER_DIR + File.separator;

    public static final int TYPE_INC = 1;
    public static final int TYPE_EXP = 2;

    public static final int MAX_NOTE_LEN = 25;

    public static final int MAX_DATE_RANGE_IN_DAYS = 365;

    public static final String[] TYPE_SELECTOR = {"Both (Income and Expense)", "Income", "Expense" };

    public static final String[] BRICKYARD_AREA_ARRAY = {"Area 1", "Area 2", "Area 3"};
    public static final String[] BRICKYARD_BRICK_TYPE = {"Brick Type 1", "Brick Type 2"};
    public static final String[] BRICKYARD_STATUS_TYPE = {"Active", "Inactive"};

    public static final String[] DEFAULT_INC_CATEGORY = {
            "Awards", "Advance Payment", "Dividend", "House Rent", "Gift", "Lottery", "Pension", "Refunds", "Salary", "Scholarship"
    };

    public static final String[] DEFAULT_EXP_CATEGORY = {
            "Education", "Insurance", "Entertainment", "Restaurant", "Office Expense", "Gift",
            "Courier", "Commissions", "Maintenance", "Subscription", "Medicine",
            "Service Charge", "Meeting", "Food", "Electronics", "Vegetables", "Snacks",
            "Fruits", "Shopping", "House Rent", "Transportation", "Tuition Fees", "Fish"
    };

    public static final String[][] COUNTRY_INFO_ARRAY = {
            {"Afghanistan", "Afghani", "AFN", "Af"}, {"Australia", "Australian dollar", "AUD", "$"}, {"Austria", "Euro", "EUR", "€"}, {"Bangladesh", "Bangladeshi taka", "BDT", "৳"},
            {"Belgium", "Euro", "EUR", "€"}, {"Brazil", "Brazilian real", "BRL", "R$"}, {"Bulgaria", "Bulgarian lev", "BGN", "лв"}, {"Canada", "Canadian dollar", "CAD", "$"},
            {"Cyprus", "Euro", "EUR", "€"}, {"China", "Chinese yuan", "CNY", "¥"}, {"Denmark", "Danish krone", "DKK", "kr"}, {"Estonia", "Euro", "EUR", "€"}, {"Finland", "Euro", "EUR", "€"},
            {"France", "Euro", "EUR", "€"}, {"Germany", "Euro", "EUR", "€"}, {"Greece", "Euro", "EUR", "€"}, {"Hong Kong", "Hong Kong dollar", "HKD", "$"}, {"Hungary", "Hungarian forint", "HUF", "ft"},
            {"India", "Indian rupee", "INR", "₹"}, {"Indonesia", "Indonesian rupiah", "IDR", "Rp"}, {"Ireland", "Euro", "EUR", "€"}, {"Israel", "Israeli shekel", "ILS", "₪"},
            {"Italy", "Euro", "EUR", "€"}, {"Japan", "Japanese yen", "JPY", "¥"}, {"Latvia", "Euro", "EUR", "€"}, {"Lithuania", "Euro", "EUR", "€"}, {"Luxembourg", "Euro", "EUR", "€"},
            {"Malaysia", "Malaysian ringgit", "MYR", "RM"}, {"Malta", "Euro", "EUR", "€"}, {"Netherland", "Euro", "EUR", "€"}, {"New Zealand", "New Zealand dollar", "NZD", "$"},
            {"Norway", "Norwegian krone", "NOK", "kr"}, {"Pakistan", "Pakistani rupee", "PKR", "Rs"}, {"Philippines", "Philippine peso", "PHP", "₱"}, {"Portugal", "Euro", "EUR", "€"},
            {"Russia", "Russian ruble", "RUB", "₽"}, {"Singapore", "Singapore dollar", "SGD", "$"}, {"Slovakia", "Euro", "EUR", "€"}, {"Slovenia", "Euro", "EUR", "€"},
            {"South Korea", "South Korean won", "KRW", "₩"}, {"Spain", "Euro", "EUR", "€"}, {"Sri Lanka", "Sri Lankan rupee", "LKR", "Rs"}, {"Sweden", "Swedish krona", "SEK", "kr"},
            {"Switzerland", "Swiss franc", "CHF", "CHF"}, {"Thailand", "Thai baht", "THB", "฿"}, {"Turkey", "Turkish lira", "TRY", "₺"}, {"UAE ", "Dirham", "AED", "د.إ"},
            {"Ukraine", "Ukrainian hryvna", "UAH", "₴"}, {"United Kingdom", "Pounds", "GBP", "£"}, {"USA", "US dollar", "USD", "$"}, {"Vietnam", "Vietnamese dong", "VND", "₫"}
    };

    public static final String[] MONTH_ARRAY = {
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };

    public static final String[] DIVISION_ARRAY = {"Barisal", "Chittagong", "Dhaka", "Khulna", "Mymensingh", "Rajshahi", "Rangpur", "Sylhet"};

    public static final String[][] CIRCLE_ARRAY = {
            {"Barisal", "Circle- B1", "Circle- B2", "Circle- B3"},
            {"Chittagong", "Circle- C1", "Circle- C2", "Circle- C3", "Circle- C4", "Circle- C5", "Circle- C6"},
            {"Dhaka", "Circle- D1", "Circle- D2", "Circle- D3", "Circle- D4", "Circle- D5", "Circle- D6"},
            {"Khulna", "Circle- K1", "Circle- K2", "Circle- K3", "Circle- K4", "Circle- K5", "Circle- K6"},
            {"Mymensingh", "Circle- M1", "Circle- M2", "Circle- M3", "Circle- M4", "Circle- M5", "Circle- M6"},
            {"Rajshahi", "Circle- RJ1", "Circle- RJ2", "Circle- RJ3", "Circle- RJ4", "Circle- RJ5", "Circle- RJ6"},
            {"Rangpur", "Circle- RA1", "Circle- RA2", "Circle- RA3", "Circle- RA4", "Circle- RA5", "Circle- RA6"},
            {"Sylhet", "Circle- S1", "Circle- S2", "Circle- S3", "Circle- S4", "Circle- S5", "Circle- S6"}
    };

    public static String[] populateCircleListByDivision(String divisionName){
        Set<String> circleNameList = new HashSet<>();

        for (String[] circleArr : CIRCLE_ARRAY) {

            if (circleArr[0].equals(divisionName)) {
                circleNameList.addAll(Arrays.asList(circleArr).subList(1, circleArr.length));
            }

        }

        List<String> sortedList = new ArrayList<>(circleNameList);
        Collections.sort(sortedList);

        String[] itemsArray = new String[sortedList.size()];
        return sortedList.toArray(itemsArray);
    }

    public static final String[][] SECTOR_ARRAY = {
            {"Circle- B1", "Sector- 101", "Sector- 102"},
            {"Circle- C1", "Sector- 201", "Sector- 202", "Sector- 203", "Sector- 204", "Sector- 205"},
            {"Circle- D1", "Sector- 301", "Sector- 302", "Sector- 303", "Sector- 304", "Sector- 305"},
            {"Circle- K1", "Sector- 401", "Sector- 402", "Sector- 403", "Sector- 404", "Sector- 405"},
            {"Circle- M1", "Sector- 501", "Sector- 502", "Sector- 503", "Sector- 504", "Sector- 505"},
            {"Circle- RJ1", "Sector- 601", "Sector- 602", "Sector- 603", "Sector- 604", "Sector- 605"},
            {"Circle- RA1", "Sector- 701", "Sector- 702", "Sector- 703", "Sector- 704", "Sector- 705"},
            {"Circle- S1", "Sector- 801", "Sector- 802", "Sector- 803", "Sector- 804", "Sector- 805"}
    };

    public static String[] populateSectorListByCircle(String circleName){
        Set<String> sectorNameList = new HashSet<>();

        for (String[] sectorArr : SECTOR_ARRAY) {

            if (sectorArr[0].equals(circleName)) {
                sectorNameList.addAll(Arrays.asList(sectorArr).subList(1, sectorArr.length));
            }

        }

        List<String> sortedList = new ArrayList<>(sectorNameList);
        Collections.sort(sortedList);

        String[] itemsArray = new String[sortedList.size()];
        return sortedList.toArray(itemsArray);

    }

    public static List<String> populateCountryANdCurrencyList(){
        Set<String> countryNameList = new HashSet<>();

        for(int i = 0; i < COUNTRY_INFO_ARRAY.length; i++) {
            String[] countryArr = COUNTRY_INFO_ARRAY[i];
            countryNameList.add(countryArr[0] + HASH_TAG_WITH_SPACE + countryArr[1] + CURRENCY_SPILTER +countryArr[3]);
        }

        List<String> sortedList = new ArrayList<>(countryNameList);
        Collections.sort(sortedList);

        return sortedList;
    }

    public static List<String> populateCountryList(){
        Set<String> countryNameList = new HashSet<>();

        for(int i = 0; i < COUNTRY_INFO_ARRAY.length; i++) {
            String[] countryArr = COUNTRY_INFO_ARRAY[i];
            countryNameList.add(countryArr[0]);
        }

        List<String> sortedList = new ArrayList<>(countryNameList);
        Collections.sort(sortedList);

        return sortedList;
    }

    public static List<String> populateCurrencyList(){
        Set<String> countryNameList = new HashSet<>();

        for(int i = 0; i < COUNTRY_INFO_ARRAY.length; i++) {
            String[] countryArr = COUNTRY_INFO_ARRAY[i];
            countryNameList.add(countryArr[1] + CURRENCY_SPILTER +countryArr[3]); //"Pounds | £"
        }

        List<String> sortedList = new ArrayList<>(countryNameList);
        Collections.sort(sortedList);

        return sortedList;
    }

    public static String getShortMonthNameFromMonthNumber(int month){
        if(month == 1) return "Jan";
        if(month == 2) return "Feb";
        if(month == 3) return "Mar";
        if(month == 4) return "Apr";
        if(month == 5) return "May";
        if(month == 6) return "Jun";
        if(month == 7) return "Jul";
        if(month == 8) return "Aug";
        if(month == 9) return "Sep";
        if(month == 10) return "Oct";
        if(month == 11) return "Nov";
        if(month == 12) return "Dec";
        else return "";
    }

    public static String getShortMonthNameFromNumber(String month){
        if(month.equalsIgnoreCase("01") || month.equalsIgnoreCase("1")) return "Jan";
        if(month.equalsIgnoreCase("02") || month.equalsIgnoreCase("2")) return "Feb";
        if(month.equalsIgnoreCase("03") || month.equalsIgnoreCase("3")) return "Mar";
        if(month.equalsIgnoreCase("04") || month.equalsIgnoreCase("4")) return "Apr";
        if(month.equalsIgnoreCase("05") || month.equalsIgnoreCase("5")) return "May";
        if(month.equalsIgnoreCase("06") || month.equalsIgnoreCase("6")) return "Jun";
        if(month.equalsIgnoreCase("07") || month.equalsIgnoreCase("7")) return "Jul";
        if(month.equalsIgnoreCase("08") || month.equalsIgnoreCase("8")) return "Aug";
        if(month.equalsIgnoreCase("09") || month.equalsIgnoreCase("9")) return "Sep";
        if(month.equalsIgnoreCase("10")) return "Oct";
        if(month.equalsIgnoreCase("11")) return "Nov";
        if(month.equalsIgnoreCase("12")) return "Dec";
        else return "";
    }

    public static String getFullMonthNameFromNumber(String month){
        if(month.equalsIgnoreCase("01") || month.equalsIgnoreCase("1")) return "January";
        if(month.equalsIgnoreCase("02") || month.equalsIgnoreCase("2")) return "February";
        if(month.equalsIgnoreCase("03") || month.equalsIgnoreCase("3")) return "March";
        if(month.equalsIgnoreCase("04") || month.equalsIgnoreCase("4")) return "April";
        if(month.equalsIgnoreCase("05") || month.equalsIgnoreCase("5")) return "May";
        if(month.equalsIgnoreCase("06") || month.equalsIgnoreCase("6")) return "June";
        if(month.equalsIgnoreCase("07") || month.equalsIgnoreCase("7")) return "July";
        if(month.equalsIgnoreCase("08") || month.equalsIgnoreCase("8")) return "August";
        if(month.equalsIgnoreCase("09") || month.equalsIgnoreCase("9")) return "September";
        if(month.equalsIgnoreCase("10")) return "October";
        if(month.equalsIgnoreCase("11")) return "November";
        if(month.equalsIgnoreCase("12")) return "December";
        else return "";
    }
}
