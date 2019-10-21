package com.autowish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autowish.R;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerAdapterHomeAds extends RecyclerView.Adapter<recyclerAdapterHomeAds.MyViewHolder> {

    int count;
    Context context;

    public recyclerAdapterHomeAds(Context context, int count) {
        this.count = count;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_ads, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
//        holder.text_customers.setText(arrayListShops.get(position).getRoute_name());
//
//        holder.linear_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, CustomerDetailActivity.class);
//                context.startActivity(i);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_customers;
        LinearLayout linear_main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            text_customers = (TextView) itemView.findViewById(R.id.text_customers);
//            linear_main = (LinearLayout) itemView.findViewById(R.id.linear_main);
        }
    }
}
