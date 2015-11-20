package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.model.ModelTemp;
import com.servercall.servercall.MyListener;

import java.util.ArrayList;

import testapplication.com.testapplication.ActivityHome;
import testapplication.com.testapplication.R;

/**
 * Created by Admin on 20-11-2015.
 */
public class AdapterStudent extends RecyclerView.Adapter<AdapterStudent.Holder>{
    ArrayList<ModelTemp> listRecords;
    Context mContext;

    public  AdapterStudent(Context mContext, ArrayList<ModelTemp> listRecords){
        this.listRecords=listRecords;
        this.mContext=mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_student,null);
        Holder holder=new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        ModelTemp record=listRecords.get(i);
        holder.txtFName.setText(record.getFname());
        holder.txtLName.setText(record.getLname());
        holder.txtAddress.setText(record.getRollno());
         holder.make(listener);
        //holder.txtFName.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {
        return listRecords.size();
    }

    class  Holder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtFName;
        public TextView txtLName;
        public TextView txtAddress;
        OnItemClickListener listener;
        View view;

        public Holder(View itemView) {
            super(itemView);
            view=itemView;
            txtFName=(TextView)itemView.findViewById(R.id.txtFirstName);
            txtLName=(TextView)itemView.findViewById(R.id.txtLastName);
            txtAddress=(TextView)itemView.findViewById(R.id.txtAddress);

        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(view,getPosition());
        }

        public void make(OnItemClickListener listener){
            this.listener=listener;
        }

    }

    OnItemClickListener listener=new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(mContext,""+position,Toast.LENGTH_LONG);
        }
    };


     public interface OnItemClickListener {
        public void onItemClick(View view , int position);

    }

}
