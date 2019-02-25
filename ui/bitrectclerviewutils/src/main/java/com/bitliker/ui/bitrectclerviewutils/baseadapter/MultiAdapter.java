package com.bitliker.ui.bitrectclerviewutils.baseadapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * 多布局Adapter
 * ---------------
 * 单一数据类型
 */
public abstract class MultiAdapter<T> extends BaseRecyclerAdapter<T> {
    public MultiAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        int layoutId = getLayoutId(getData(position),position);
        return new SuperViewHolder(layoutId, getInflater().inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        bindData(getContext(), holder, items.get(position), holder.getLayoutId(), position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    //根据ViewType获取LayoutId
    public abstract int getLayoutId(T item,int position);


    /*绑定数据源*/
    public abstract void bindData(Context context, SuperViewHolder holder, T item, int layoutId, int position);

}
