package com.jovialway.miprince;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;


    public RecyclerViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView poemName;
        TextView poemWriter;
        TextView poem;
        View parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.parentView = itemView;
            this.poemName = itemView.findViewById(R.id.poemNameTV);
            this.poemWriter = itemView.findViewById(R.id.poemWriterTV);
            this.poem = itemView.findViewById(R.id.poemDetailTV);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Model model = modellist.get(position);

        holder.poemName.setText(modellist.get(position).getPoemName());
        holder.poemWriter.setText(modellist.get(position).getPoemWriter());
        holder.poem.setText(modellist.get(position).getPoem());


        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, ViewActivity.class);
                    intent.putExtra("poemName", modellist.get(position).getPoemName()).toString();
                    intent.putExtra("writer", modellist.get(position).getPoemWriter()).toString();
                    intent.putExtra("poem", modellist.get(position).getPoem()).toString();

                    mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }


    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length() == 0) {
            modellist.addAll(arrayList);
        } else {
            for (Model model : arrayList) {
                if (model.getPoemName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    modellist.add(model);
                } else if (model.getPoemWriter().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    modellist.add(model);
                } else if (model.getPoem().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
