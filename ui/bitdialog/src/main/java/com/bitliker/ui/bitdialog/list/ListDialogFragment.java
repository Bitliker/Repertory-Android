package com.bitliker.ui.bitdialog.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitliker.ui.bitdialog.R;
import com.bitliker.ui.bitdialog.common.BaseDialogFragment;
import com.bitliker.ui.bitdialog.common.BitDialogConstants;
import com.bitliker.ui.bitdialog.common.listener.OnMultiSelectListener;
import com.bitliker.ui.bitdialog.common.listener.OnSelectListener;
import com.bitliker.ui.bitdialog.common.listener.PromptWidgetListener;
import com.bitliker.ui.bitdialog.common.paramer.WidgetParameter;

import java.io.Serializable;
import java.util.List;

public class ListDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private ListAdapter mListAdapter;
    private Serializable listener;

    @Override
    public int getInflater() {
        return R.layout.view_dialog_list;
    }

    @Override
    public void initView(View view, WidgetParameter mTitleWidgetParameter) {
        AppCompatTextView selectAllTv = (AppCompatTextView) view.findViewById(R.id.selectAllTv);
        AppCompatTextView sureTv = (AppCompatTextView) view.findViewById(R.id.sureTv);
        AppCompatTextView titleTv = (AppCompatTextView) view.findViewById(R.id.titleTv);
        ListView mListView = (ListView) view.findViewById(R.id.mListView);
        AppCompatTextView cancelTv = (AppCompatTextView) view.findViewById(R.id.cancelTv);
        paramer2Text(titleTv, mTitleWidgetParameter, this, false);
        Bundle args = getArguments();
        if (args != null) {
            //事件
            listener = args.getSerializable(BitDialogConstants.LIST_LISTENER);
            final boolean isMulti = listener != null && listener instanceof OnMultiSelectListener;
            if (isMulti) { //多选
                selectAllTv.setVisibility(View.VISIBLE);
                selectAllTv.setOnClickListener(this);
            } else {
                selectAllTv.setVisibility(View.GONE);
            }
            //确定按钮
            if (args.getBoolean(BitDialogConstants.POSITIVE_SHOW_ABLE, true)) {
                sureTv.setVisibility(View.VISIBLE);
                WidgetParameter mPositiveWidgetParameter = args.getParcelable(BitDialogConstants.POSITIVE_PARAMER);
                if (mPositiveWidgetParameter != null) {
                    paramer2Text(sureTv, mPositiveWidgetParameter, this, true);
                } else {
                    sureTv.setOnClickListener(this);
                }
            } else {//单选
                sureTv.setVisibility(View.GONE);
            }
            //取消按钮
            if (args.getBoolean(BitDialogConstants.NEGATIVE_SHOW_ABLE, true)) {
                WidgetParameter mNegativeWidgetParameter = args.getParcelable(BitDialogConstants.NEGATIVE_PARAMER);
                if (mNegativeWidgetParameter != null) {
                    paramer2Text(cancelTv, mNegativeWidgetParameter, this, true);
                } else {
                    cancelTv.setOnClickListener(this);
                }
            } else {
                cancelTv.setVisibility(View.GONE);
            }
            //对象
            List<BitDialogModel> models = args.getParcelableArrayList(BitDialogConstants.LIST_MODELS);
            if (models != null && models.size() > 0) {
                //处理item
                if (models != null && models.size() > 5) {
                    ViewGroup.LayoutParams params = mListView.getLayoutParams();
                    if (params != null) {
                        DisplayMetrics dm = new DisplayMetrics();
                        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
                        int ori = mConfiguration.orientation; //获取屏幕方向
                        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
                            //横屏
                            params.height = (dm.widthPixels * 2) / 3;
                        } else {
                            params.height = dm.heightPixels / 2;
                        }
                        mListView.setLayoutParams(params);
                    }
                }
                mListAdapter = new ListAdapter(isMulti, models);
                mListView.setAdapter(mListAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object item = mListAdapter.getItem(position);
                        if (item != null && item instanceof BitDialogModel) {
                            BitDialogModel model = (BitDialogModel) item;
                            if (isMulti) {
                                model.setSelected(!model.isSelected());
                                mListAdapter.notifyDataSetChanged();
                            } else {
                                if (listener != null && listener instanceof OnSelectListener) {
                                    if (!((OnSelectListener) listener).select(true, model)) {
                                        dismiss();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }


    @Override
    public void onClick(View v) {
        PromptWidgetListener mPromptWidgetListener = null;
        Object object = v.getTag();
        int id = v.getId();
        if (object != null && object instanceof PromptWidgetListener) {
            mPromptWidgetListener = (PromptWidgetListener) object;
        }
        if (id == R.id.titleTv) {
            if (mPromptWidgetListener != null) {
                mPromptWidgetListener.onClick(v);
            }
        } else if (R.id.sureTv == id || R.id.cancelTv == id) {
            if (mPromptWidgetListener != null) {
                if (!mPromptWidgetListener.onClick(v)) {
                    dismiss();
                }
            } else {
                dismiss();
            }
        }
    }


    private class ListAdapter extends BaseAdapter {
        private boolean isMulti;
        private List<BitDialogModel> models;

        public ListAdapter(boolean isMulti, List<BitDialogModel> models) {
            this.isMulti = isMulti;
            this.models = models;
        }

        @Override
        public int getCount() {
            return models == null ? 0 : models.size();
        }

        @Override
        public Object getItem(int position) {
            return models.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder = null;
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_dialog, null);
                mViewHolder.contentTv = (AppCompatTextView) convertView.findViewById(R.id.contentTv);
                mViewHolder.selectIv = (AppCompatImageView) convertView.findViewById(R.id.selectIv);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            Object item = getItem(position);
            if (item != null && item instanceof BitDialogModel) {
                BitDialogModel model = (BitDialogModel) item;
                mViewHolder.contentTv.setText(model.getShowValues());
                mViewHolder.selectIv.setVisibility(model.isSelected() ? View.VISIBLE : View.GONE);
            } else {
                mViewHolder.selectIv.setVisibility(View.GONE);
            }
            return convertView;
        }

        private class ViewHolder {
            TextView contentTv = null;
            ImageView selectIv = null;
        }

    }
}
