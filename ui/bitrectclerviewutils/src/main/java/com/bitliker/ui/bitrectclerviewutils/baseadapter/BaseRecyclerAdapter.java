package com.bitliker.ui.bitrectclerviewutils.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    public List<T> items = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void clearDatas() {
        int size = this.items.size();
        this.items.clear();
        notifyItemRangeRemoved(0, size);
    }


    public void setData(List<T> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return items;
    }

    public T getData(int position) {
        if (this.items != null && this.items.size() > position && position >= 0) {
            return this.items.get(position);
        }
        return null;
    }


    public void addData(T data, boolean notify) {
        addData(items.size(), data, notify);
    }

    public void addData(int position, T data, boolean notify) {
        this.items.add(position+1, data);
        if (notify) {
            notifyItemInserted(position+1);
        }
    }


    public void addData(List<T> list, boolean notify) {
       addData(this.items.size(),list,notify);
    }


    public void addData(int startPosition, List<T> list, boolean notify) {
        this.items.addAll(startPosition+1, list);
        if (notify) {
            notifyItemRangeInserted(startPosition+1, list.size());
        }
    }


    public void updateData(int position, T list,boolean notify) {
        this.items.set(position, list);
        if (notify){
            notifyItemChanged(position);
        }
    }

    public T removeData(int position) {
        T t = this.items.remove(position);
        notifyItemRemoved(position);
        return t;
    }

}
