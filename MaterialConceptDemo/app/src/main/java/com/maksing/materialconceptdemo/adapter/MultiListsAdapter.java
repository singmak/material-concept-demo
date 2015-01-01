package com.maksing.materialconceptdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 1/1/15.
 */
public class MultiListsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mQueries = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mQueries.size();
    }
}
