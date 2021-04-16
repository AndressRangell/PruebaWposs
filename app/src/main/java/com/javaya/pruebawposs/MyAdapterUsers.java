package com.javaya.pruebawposs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.javaya.pruebawposs.models.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterUsers extends RecyclerView.Adapter<MyAdapterUsers.MyViewHolder> implements View.OnClickListener{

    Context context;
    ArrayList<User> userList;
    private View.OnClickListener listener;

    public MyAdapterUsers(ArrayList<User> userList, Context context){
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_users, null, false);

        view.setOnClickListener(this);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameUser.setText(userList.get(position).getName());
        holder.countUser.setText(userList.get(position).getCount());
        if(userList.get(position).getStatus().equals("habilitado")){
            Glide.with(context).load(R.drawable.positive).into(holder.iconStatus);
        }else{
            Glide.with(context).load(R.drawable.negative).into(holder.iconStatus);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nameUser, countUser;
        private ImageView iconStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameUser = itemView.findViewById(R.id.nameUser);
            countUser = itemView.findViewById(R.id.countUser);
            iconStatus = itemView.findViewById(R.id.iconStatus);

        }

    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

}
