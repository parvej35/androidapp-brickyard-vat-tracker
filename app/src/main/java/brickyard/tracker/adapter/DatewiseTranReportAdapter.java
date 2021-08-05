package brickyard.tracker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import brickyard.tracker.R;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.Constant;

public class DatewiseTranReportAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<RecordBean> recordBeanList;
    private String curSymbol;

    //private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public DatewiseTranReportAdapter(Context mContext, List<RecordBean> dataList, String currencySymbol) {
        context = mContext;
        inflater = LayoutInflater.from(context);
        recordBeanList = dataList;
        curSymbol = currencySymbol;
    }

    public class ViewHolder {
        TextView counter;
        TextView date;
        TextView time;
        TextView amount;
        TextView category;
    }

    @Override
    public int getCount() {
        return recordBeanList.size();
    }

    @Override
    public RecordBean getItem(int position) {
        return recordBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        try {
            final DatewiseTranReportAdapter.ViewHolder holder;
            if (view == null) {
                holder = new DatewiseTranReportAdapter.ViewHolder();
                view = inflater.inflate(R.layout.transaction_search_datewise_report_datalist, null);

                // Locate the TextViews in list_view_item.xml
                holder.counter = (TextView) view.findViewById(R.id.counter);
                holder.date = (TextView) view.findViewById(R.id.date);
                holder.time = (TextView) view.findViewById(R.id.time);
                holder.amount = (TextView) view.findViewById(R.id.amount);
                holder.category = (TextView) view.findViewById(R.id.category);

                view.setTag(holder);
            } else {
                holder = (DatewiseTranReportAdapter.ViewHolder) view.getTag();
            }

            RecordBean recordBean = recordBeanList.get(position);

//            int year = recordBean.getYear();
//            String month = Constant.getShortMonthNameFromMonthNumber(recordBean.getMonth());
//            int day = recordBean.getDay();

            // Set the results into TextViews
            holder.counter.setText(String.valueOf(position + 1));
//            holder.date.setText(day +"-"+month+"-"+year);

//            String timeArr[] = recordBean.getTime().split(":");
//            int hour = Integer.parseInt(timeArr[0]);
//            String min = timeArr[1];

//            if(hour == 12) {
//                holder.time.setText("@ " +hour+":"+min +" pm");
//            } else if(hour > 12) {
//                hour = hour - 12;
//                holder.time.setText("@ " +hour+":"+min +" pm");
//            } else {
//                holder.time.setText("@ " + hour + ":" + min + " am");
//            }

//            Double amount = Double.parseDouble(recordBean.getAmount());
//            holder.amount.setText(curSymbol +" "+ String.format(Locale.US, "%.2f", (amount)));
//            if (recordBean.getType() == Constant.TYPE_INC) {
//                holder.amount.setTextColor(Color.parseColor("#00574b"));
//                holder.category.setText("(Inc) " + recordBean.getCategory());
//            } else if (recordBean.getType() == Constant.TYPE_EXP) {
//                holder.amount.setTextColor(Color.parseColor("#D81B60"));
//                holder.category.setText("(Exp) " + recordBean.getCategory());
//            }
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

}
