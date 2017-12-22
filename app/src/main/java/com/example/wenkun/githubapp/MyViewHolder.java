package com.example.wenkun.githubapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wenkun on 2017/12/21.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    public MyViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mConvertView=itemView;
        mViews=new SparseArray<View>();
    }

    public <T extends View> T getView(int viewId) {
        View view=mViews.get(viewId);
        if(view==null) {
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    public static MyViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        MyViewHolder holder =new MyViewHolder(context,itemView,parent);
        return holder;
    }
}