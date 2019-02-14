package com.gxut.ui.superadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder基类
 */
public class SuperViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private int layoutId;//用于保存当前布局id
    public SuperViewHolder(int layoutId,View view) {
        super(view);
        this.layoutId=layoutId;
        this.views = new SparseArray<>();
    }

    public int getLayoutId() {
        return layoutId;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }
}