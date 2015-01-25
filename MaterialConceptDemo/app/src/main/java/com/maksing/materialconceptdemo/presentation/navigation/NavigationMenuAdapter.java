package com.maksing.materialconceptdemo.presentation.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.model.NavMenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 31/12/14.
 */
public class NavigationMenuAdapter extends RecyclerView.Adapter<NavigationMenuAdapter.NavMenuItemViewHolder> {
    private List<NavMenuItem> mNavItems = new ArrayList<>();

    private View.OnClickListener mOnClickListener;

    public NavigationMenuAdapter(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public NavMenuItemViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_list_item, parent, false);
        return new NavMenuItemViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(NavMenuItemViewHolder navMenuItemViewHolder, int pos) {
        navMenuItemViewHolder.bindNavItem(mNavItems.get(pos));
    }

    @Override
    public int getItemCount() {
        return mNavItems.size();
    }

    public void setNavMenuItems(List<NavMenuItem> navItems) {
        mNavItems = navItems;
        notifyDataSetChanged();
    }

    public static class NavMenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.title)
        TextView mTitle;
        private View mView;

        private View.OnClickListener mOnClickListener;

        public NavMenuItemViewHolder(View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mOnClickListener = onClickListener;
            itemView.setOnClickListener(this);
            mView = itemView;
        }

        public void bindNavItem(NavMenuItem navItem) {
            mTitle.setText(navItem.getTitle());
            mView.setSelected(navItem.isSelected());
            mView.setTag(navItem);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(v);
        }
    }
}
