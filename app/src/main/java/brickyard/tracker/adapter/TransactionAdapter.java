package brickyard.tracker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import brickyard.tracker.R;
import brickyard.tracker.bean.RecordBean;
import brickyard.tracker.util.Constant;

public class TransactionAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<RecordBean> recordBeanList;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


    public TransactionAdapter(Context mContext, List<RecordBean> dataList) {
        context = mContext;
        inflater = LayoutInflater.from(context);

        recordBeanList = dataList;
    }

    public class ViewHolder {
        TextView month;
        TextView date;
        TextView year;
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
            final TransactionAdapter.ViewHolder holder;
            if (view == null) {
                holder = new TransactionAdapter.ViewHolder();
                view = inflater.inflate(R.layout.transaction_list, null);

                // Locate the TextViews in list_view_item.xml
                holder.month = (TextView) view.findViewById(R.id.month);
                holder.date = (TextView) view.findViewById(R.id.date);
                holder.year = (TextView) view.findViewById(R.id.year);
                holder.amount = (TextView) view.findViewById(R.id.amount);
                holder.category = (TextView) view.findViewById(R.id.category);

                view.setTag(holder);
            } else {
                holder = (TransactionAdapter.ViewHolder) view.getTag();
            }


            RecordBean recordBean = recordBeanList.get(position);

            /*String strDate = dateFormatter.format(new Date(recordBean.getDate()).getTime());
            String dateArr[] = strDate.split("-");
            String day = dateArr[2];
            String month = dateArr[1];
            String year = dateArr[0];*/

//            int year = recordBean.getYear();
            //String month = Constant.getShortMonthNameFromNumber(String.valueOf(recordBean.getMonth()));
//            String month = Constant.getShortMonthNameFromMonthNumber(recordBean.getMonth());
//            int day = recordBean.getDay();

            // Set the results into TextViews
//            holder.month.setText(month);
//            holder.date.setText(String.valueOf(day));
//            holder.year.setText(String.valueOf(year));

//            holder.amount.setText("$ " + recordBean.getAmount());
//            if (recordBean.getType() == Constant.TYPE_INC) {
//                holder.amount.setTextColor(Color.parseColor("#008577"));
//            } else if (recordBean.getType() == Constant.TYPE_EXP) {
//                holder.amount.setTextColor(Color.parseColor("#D81B60"));
//            }

//            holder.category.setText(recordBean.getCategory());
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }
}