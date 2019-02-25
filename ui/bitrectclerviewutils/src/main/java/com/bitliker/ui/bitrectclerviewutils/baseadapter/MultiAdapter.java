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

    private Map<Integer, Integer> layoutMap = new HashMap<>();

    public MultiAdapter(Context context, int[] layoutIds) {
        super(context);
        for (int i = 0; i < layoutIds.length; i++) {
            layoutMap.put(i, layoutIds[i]);
        }
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        int layoutId = getLayoutId(position);
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
    public abstract int getLayoutId(int position);


    /*绑定数据源*/
    public abstract void bindData(Context context, SuperViewHolder holder, T item, int layoutId, int position);

}
