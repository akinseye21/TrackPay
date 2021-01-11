package com.example.ndif_yemmanuel.trackpay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by NDIF_YEMMANUEL on 10/20/2020.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {



    private Context context;
    private ArrayList<GroupRow> parentRowList;
    private ArrayList<GroupRow> originalList;

    public ExpandableListAdapter(Context context,
                                 ArrayList<GroupRow> originalList) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return parentRowList.get(i).getChildList().size();
    }

    @Override
    public Object getGroup(int i) {
        return parentRowList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return parentRowList.get(i).getChildList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupRow groupRow = (GroupRow) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView rl_number = convertView.findViewById(R.id.rl_num);
        TextView descriptio = convertView.findViewById(R.id.description);
        Button btnchange = convertView.findViewById(R.id.btnchange);

        if (isExpanded){
            btnchange.setText("-");
        }else{
            btnchange.setText("+");
        }

        rl_number.setText(groupRow.getRl_num().trim());
        descriptio.setText(groupRow.getDescription().trim());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemRow itemRow = (ItemRow) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        final TextView amount = convertView.findViewById(R.id.amount);
        final TextView date = convertView.findViewById(R.id.date);
        final TextView type = convertView.findViewById(R.id.type);
        final TextView status = convertView.findViewById(R.id.status);

        amount.setText(itemRow.getAmount().trim());
        date.setText(itemRow.getDate().trim());
        type.setText(itemRow.getType().trim());
        status.setText(itemRow.getStatus().trim());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
