package brickyard.tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import brickyard.tracker.R;
import brickyard.tracker.bean.RecordBean;

public class MonthlyTranReportAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    //private List<RecordBean> recordList;
    private Map<String, String> monthlyIncTransList;
    private Map<String, String> monthlyExpTransList;

    int totalData = 0;
    int incTransListSize = 0;
    int expTransListSize = 0;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public MonthlyTranReportAdapter(Context mContext, Map<String, String> incTransList, Map<String, String> expTransList) {
        context = mContext;
        inflater = LayoutInflater.from(context);
        monthlyIncTransList = incTransList;
        monthlyExpTransList = expTransList;

        incTransListSize = incTransList.size();
        expTransListSize = expTransList.size();

        if(incTransListSize > expTransListSize) {
            totalData = incTransListSize;
        } else {
            totalData = expTransListSize;
        }
    }

    public class ViewHolder {
        TextView counter;
        TextView year;
        TextView month;
        TextView incAmount;
        TextView expAmount;
    }

    @Override
    public int getCount() {
        return totalData;
    }

    @Override
    public RecordBean getItem(int position) {
        //return recordList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        try {
            final MonthlyTranReportAdapter.ViewHolder holder;
            if (view == null) {
                holder = new MonthlyTranReportAdapter.ViewHolder();
                view = inflater.inflate(R.layout.transaction_search_monthly_report_datalist, null);

                // Locate the TextViews in list_view_item.xml
                holder.counter = (TextView) view.findViewById(R.id.counter);
                holder.year = (TextView) view.findViewById(R.id.year);
                holder.month = (TextView) view.findViewById(R.id.month);
                holder.incAmount = (TextView) view.findViewById(R.id.incAmount);
                holder.expAmount = (TextView) view.findViewById(R.id.expAmount);

                view.setTag(holder);
            } else {
                holder = (MonthlyTranReportAdapter.ViewHolder) view.getTag();
            }

            Map<String, String> incTransMap = new HashMap<>();
            Map<String, String> expTransMap = new HashMap<>();

            if(incTransListSize > expTransListSize) {
                //incTransMap = monthlyIncTransList.get(position);
            } else {
                expTransMap.get(position);
            }

            /*String strDate = dateFormatter.format(new Date(record.getDate()).getTime());
            String dateArr[] = strDate.split("-");
            String year = dateArr[0];
            String month = Constant.getFullMonthNameFromNumber(dateArr[1]);*/

            //int year = record.getYear();
            //String month = Constant.getShortMonthNameFromNumber(String.valueOf(record.getMonth()));
            //String month = Constant.getShortMonthNameFromMonthNumber(record.getMonth());
            //int date = record.getDay();

            /*holder.counter.setText(String.valueOf(position + 1));
            holder.month.setText(month);
            holder.year.setText(String.valueOf(year));

            Double amount = Double.parseDouble(record.getAmount());

            if (record.getType() == Constant.TYPE_INC) {
                holder.incAmount.setText(String.format(Locale.US, "%.2f", (amount)));
                holder.expAmount.setText("0.00");
            } else if (record.getType() == Constant.TYPE_EXP) {
                holder.incAmount.setText("0.00");
                holder.expAmount.setText(String.format(Locale.US, "%.2f", (amount)));
            }*/
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

}
