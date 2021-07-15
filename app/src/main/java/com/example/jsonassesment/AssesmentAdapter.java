package com.example.jsonassesment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AssesmentAdapter extends RecyclerView.Adapter<AssesmentViewHolder> {

    ArrayList<Model> list;


    public AssesmentAdapter() {
        list = new ArrayList<>();

    }

    public void setList(ArrayList<Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AssesmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recylcer,parent,false);
        return new AssesmentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AssesmentViewHolder holder, int position) {

        Model model = list.get(position);
        Picasso.get().load(model.image).fit().into(holder.imageView);
        holder.name.setText(model.getName());
        holder.slug.setText(model.getSlug());
        holder.dash.setText(model.getDash());
        holder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
