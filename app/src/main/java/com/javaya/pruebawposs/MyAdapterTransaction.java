package com.javaya.pruebawposs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.javaya.pruebawposs.models.Transaction;
import com.javaya.pruebawposs.models.User;

import java.util.ArrayList;

public class MyAdapterTransaction extends RecyclerView.Adapter<MyAdapterTransaction.MyViewHolder>{

    Context context;
    ArrayList<Transaction> transactionList;

    public MyAdapterTransaction(ArrayList<Transaction> transactionList, Context context){
        this.transactionList = transactionList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_transaction, null, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterTransaction.MyViewHolder holder, int position) {

        holder.numberCard.setText(transactionList.get(position).getNumberCard());
        holder.value.setText(transactionList.get(position).getValue());
        if(transactionList.get(position).getTypeTransaction().equals("deposito")){
            Glide.with(context).load(R.drawable.icon_up).into(holder.iconStatus);
        }else{
            Glide.with(context).load(R.drawable.icon_down).into(holder.iconStatus);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView numberCard, value;
        private ImageView iconStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            numberCard = itemView.findViewById(R.id.numberCard);
            value = itemView.findViewById(R.id.value);
            iconStatus = itemView.findViewById(R.id.iconStatus);

        }

    }

}
