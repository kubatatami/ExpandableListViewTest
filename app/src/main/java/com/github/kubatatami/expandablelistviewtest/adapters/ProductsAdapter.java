package com.github.kubatatami.expandablelistviewtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.github.kubatatami.expandablelistviewtest.R;
import com.github.kubatatami.expandablelistviewtest.models.Data;
import com.github.kubatatami.expandablelistviewtest.models.Group;
import com.github.kubatatami.expandablelistviewtest.models.Item;

/**
 * Created by Kuba on 19/11/15.
 */
public class ProductsAdapter extends BaseExpandableListAdapter {

    private Data data;

    private LayoutInflater inflater;

    public ProductsAdapter(Context context, Data data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.getGroups().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getItems().size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return data.getGroups().get(groupPosition);
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getItems().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_group, parent, false);
            holder = new GroupHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.item_product_name);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        Group group = getGroup(groupPosition);
        holder.nameView.setText(group.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_product, parent, false);
            holder = new ItemHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.item_group_name);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }
        Item item = getChild(groupPosition, childPosition);
        holder.nameView.setText(item.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupHolder {

        private TextView nameView;
    }

    private static class ItemHolder {

        private TextView nameView;
    }
}
