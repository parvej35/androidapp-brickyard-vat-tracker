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
import brickyard.tracker.bean.CategoryBean;

public class CategoryAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<CategoryBean> categoryBeanList;
    List<CategoryBean> tempCategoryBeanList;

    public CategoryAdapter(Context context, List<CategoryBean> categoryBeanList) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);

        this.categoryBeanList = categoryBeanList;

        this.tempCategoryBeanList = new ArrayList<CategoryBean>();
        this.tempCategoryBeanList.addAll(categoryBeanList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return categoryBeanList.size();
    }

    @Override
    public CategoryBean getItem(int position) {
        return categoryBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.category_list, null);

            // Locate the TextViews in list_view_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(categoryBeanList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        categoryBeanList.clear();

        if (charText.length() == 0) {
            categoryBeanList.addAll(tempCategoryBeanList);
        } else {
            for (CategoryBean categoryBean : tempCategoryBeanList) {
                if (categoryBean.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    categoryBeanList.add(categoryBean);
                }
            }
        }
        notifyDataSetChanged();
    }

}
