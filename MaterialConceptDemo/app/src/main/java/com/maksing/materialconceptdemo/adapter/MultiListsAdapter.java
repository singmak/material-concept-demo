package com.maksing.materialconceptdemo.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.moviedbdomain.entity.ListItem;
import com.maksing.moviedbdomain.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 1/1/15.
 */
public class MultiListsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> mListItems = new ArrayList<>();

    private Callbacks mCallbacks;

    private SparseArray<SingleListAdapter> mListAdaptersMap = new SparseArray<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            return new HeroViewHolder(new Space(parent.getContext()));
        } else {
            return new ListViewHolder(inflater.inflate(R.layout.horizontal_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            ListViewHolder listViewHolder = (ListViewHolder)holder;

            SingleListAdapter adapter;
            if (mListAdaptersMap.get(position) != null) {
                adapter = mListAdaptersMap.get(position);
            } else {
                adapter = new SingleListAdapter(R.layout.fixed_size_movie_list_item);
                mListAdaptersMap.put(position, adapter);
                if (mCallbacks != null) {
                    mCallbacks.loadListAt(position);
                }
            }
            listViewHolder.bindList(adapter, mListItems.get(position));
        }
    }

    public void setCallbacks(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public void setListItems(List<ListItem> listItems) {
        mListItems = listItems;
        notifyDataSetChanged();
    }

    public void updateListAt(int row, List<Movie> movies) {
        mListAdaptersMap.get(row).setMovies(movies);
    }

     static class HeroViewHolder extends RecyclerView.ViewHolder {

        public HeroViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.list)
        RecyclerView mRecyclerView;
        @InjectView(R.id.title)
        TextView mTitle;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        public void bindList(SingleListAdapter adapter, ListItem listItem) {
            mRecyclerView.setAdapter(adapter);
            mTitle.setText(listItem.getTitle());
        }
    }

    public interface Callbacks {
        void loadListAt(int position); //async function to load list data.
    }
}
