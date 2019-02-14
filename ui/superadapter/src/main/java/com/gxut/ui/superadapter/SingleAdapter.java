package com.gxut.ui.superadapter;

import android.content.Context;
import android.view.ViewGroup;

/**
 * 单布局的Adapter
 * ---------------
 * 数据类型是泛型
 */
public abstract class SingleAdapter<T> extends BaseRecyclerAdapter<T> {

    private int layoutId;

    public SingleAdapter(Context context, int layoutId) {
        super(context);
        this.layoutId = layoutId;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SuperViewHolder(layoutId,getInflater().inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        bindData(holder, items.get(position));
    }

    protected abstract void bindData(SuperViewHolder holder, T item);
}
