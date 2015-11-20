package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.model.ModelTemp;

import java.util.ArrayList;

import testapplication.com.testapplication.R;

/**
 * Created by Admin on 20-11-2015.
 */
public class AdapterBase extends BaseAdapter{
    ArrayList<ModelTemp> listRecords;
    Context mContext;
    public  AdapterBase(Context mContext,ArrayList<ModelTemp> listRecords){
        this.listRecords=listRecords;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return listRecords.size();
    }

    @Override
    public Object getItem(int position) {
        return listRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView= LayoutInflater.from(mContext).inflate(R.layout.item_student,null);
        ModelTemp record=listRecords.get(position);

         TextView txtFName;
         TextView txtLName;
         TextView txtAddress;

        txtFName=(TextView)itemView.findViewById(R.id.txtFirstName);
        txtLName=(TextView)itemView.findViewById(R.id.txtLastName);
        txtAddress=(TextView)itemView.findViewById(R.id.txtAddress);

        txtFName.setText(record.getFname());
        txtLName.setText(record.getLname());
        txtAddress.setText(record.getRollno());

        return itemView;
    }
}
