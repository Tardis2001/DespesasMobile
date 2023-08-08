package com.matheus.crudapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name,id,description,amount,date;


    public MyAdapter(Context context, ArrayList name, ArrayList id, ArrayList description, ArrayList amount,ArrayList date) {
        this.context = context;
        this.name = name;
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public ArrayList getName() {
        return name;
    }

    public void setName(ArrayList name) {
        this.name = name;
    }

    public ArrayList getId() {
        return id;
    }

    public void setId(ArrayList id) {
        this.id = id;
    }

    public ArrayList getDescription() {
        return description;
    }

    public void setDescription(ArrayList description) {
        this.description = description;
    }

    public ArrayList getAmount() {
        return amount;
    }

    public void setAmount(ArrayList amount) {
        this.amount = amount;
    }

    public ArrayList getDate() {
        return date;
    }

    public void setDate(ArrayList date) {
        this.date = date;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.expenseentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.id.setText(String.valueOf(id.get(position)));
        holder.description.setText(String.valueOf(description.get(position)));
        holder.amount.setText(String.valueOf(amount.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,name,description,amount,date;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);
            id = ItemView.findViewById(R.id.textid);
            name = ItemView.findViewById(R.id.textname);
            description = ItemView.findViewById(R.id.textdescription);
            amount = ItemView.findViewById(R.id.textamount);
            date = ItemView.findViewById(R.id.textdate);
        }
    }
}
