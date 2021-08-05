package brickyard.tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import brickyard.tracker.R;
import brickyard.tracker.bean.BrickyardBean;

public class BrickyardListAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<BrickyardBean> brickyardBeanList;
    List<BrickyardBean> tempBrickyardBeanList;

    public BrickyardListAdapter(Context context, List<BrickyardBean> brickyardBeanList) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);

        this.brickyardBeanList = brickyardBeanList;

        this.tempBrickyardBeanList = new ArrayList<BrickyardBean>();
        this.tempBrickyardBeanList.addAll(brickyardBeanList);
    }

    public class ViewHolder {
        TextView name, details;
    }

    @Override
    public int getCount() {
        return brickyardBeanList.size();
    }

    @Override
    public BrickyardBean getItem(int position) {
        return brickyardBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final BrickyardListAdapter.ViewHolder holder;
        if (view == null) {
            holder = new BrickyardListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.brickyard_list, null);

            // Locate the TextViews in list_view_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.details = (TextView) view.findViewById(R.id.details);
            view.setTag(holder);
        } else {
            holder = (BrickyardListAdapter.ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(brickyardBeanList.get(position).getName());
        holder.details.setText(brickyardBeanList.get(position).getDivision()+" - "+brickyardBeanList.get(position).getCircle()+" - "+brickyardBeanList.get(position).getSector());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        brickyardBeanList.clear();

        if (charText.length() == 0) {
            brickyardBeanList.addAll(tempBrickyardBeanList);
        } else {
            for (BrickyardBean brickyardBean : tempBrickyardBeanList) {
                if (brickyardBean.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    brickyardBeanList.add(brickyardBean);
                }
            }
        }
        notifyDataSetChanged();
    }

}
