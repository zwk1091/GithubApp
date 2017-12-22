package com.example.wenkun.githubapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wenkun on 2017/12/21.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Github> list;
    private Context context;
    private LayoutInflater inflater;

    public CardAdapter(Context context, List<Github> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.github_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        Github itemEntity = list.get(position);

        holder1.name.setText(itemEntity.getLogin());
        holder1.blog.setText(itemEntity.getBlog());
        holder1.id.setText(Integer.toString(itemEntity.getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView blog;
        private TextView id;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.login);

            blog = (TextView) itemView.findViewById(R.id.blog);
            id=(TextView) itemView.findViewById(R.id.id);
        }
    }
}
