package com.example.jsonassesment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AssesmentViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name;
    TextView slug;
    TextView dash;
    TextView date;
    public AssesmentViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image1);
        name = itemView.findViewById(R.id.name) ;
        slug =itemView.findViewById(R.id.slug);
        dash = itemView.findViewById(R.id.dash);
        date = itemView.findViewById(R.id.date);

    }
}
