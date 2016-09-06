package com.shafagh.myfitnesshealthapplication.serachItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.shafagh.myfitnesshealthapplication.R;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ItemAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(ItemInfo object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ItemHolder itemHolder;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            itemHolder = new ItemHolder();
            itemHolder.txtName = (TextView)row.findViewById(R.id.name);
            itemHolder.txtUnit = (TextView)row.findViewById(R.id.unit);
            itemHolder.txtCalories = (TextView)row.findViewById(R.id.calories);
            row.setTag(itemHolder);
        }
        else
        {
            itemHolder =(ItemHolder) row.getTag();
        }
        ItemInfo itemInfo =(ItemInfo) this.getItem(position);
        itemHolder.txtName.setText(itemInfo.getItemName());
        itemHolder.txtUnit.setText(itemInfo.getItemUnit());
        itemHolder.txtCalories.setText(itemInfo.getItemCalories());
        return row;
    }

    static class ItemHolder{
        TextView txtName,txtUnit,txtCalories;
    }
}
