package com.bitliker.ui.bitdialog.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.bitliker.ui.bitdialog.common.listener.WidgetListener;
import com.bitliker.ui.bitdialog.common.paramer.ListParameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private ListAdapter mListAdapter;
    private WidgetListener mWidgetListener;
    private AppCompatTextView sureTv;
    private AppCompatTextView selectAllTv;

    @Override
    public int getInflater() {
        return R.layout.view_dialog_list;
    }

    @Override
    public void initView(View view) {
        Log.i("gong", "initView");
        selectAllTv = view.findViewById(R.id.selectAllTv);
        sureTv = view.findViewById(R.id.sureTv);
        AppCompatTextView titleTv = view.findViewById(R.id.titleTv);
        ListView mListView = view.findViewById(R.id.mListView);
        AppCompatTextView cancelTv = view.findViewById(R.id.cancelTv);
        selectAllTv.setOnClickListener(this);
        Bundle args = getArguments();
        if (args != null) {
            //标题
            doCommonTitle(titleTv, args, this);
            //确定按钮
            doCommonSure(sureTv, args, this);
            //取消按钮
            doCommonCancel(cancelTv, args, this);

            ListParameter mListParameter = args.getParcelable(BitDialogConstants.LIST_PARAMETER);
            if (mListParameter != null) {
                //事件
                boolean isMulti = mListParameter.isMulti();
                if (isMulti) { //多选
                    selectAllTv.setVisibility(View.VISIBLE);
                    selectAllTv.setOnClickListener(this);
                } else {
                    selectAllTv.setVisibility(View.GONE);
                }
                mWidgetListener = mListParameter.getOnWidgetClickListener();
                doModels(mListView, isMulti, mListParameter);
            }
        }
    }


    /**
     * 填装对象
     *
     * @param mListView
     * @param isMulti
     * @param mListParameter
     */
    private void doModels(ListView mListView, final boolean isMulti, ListParameter mListParameter) {
        //对象
        List<BitDialogModel> models = mListParameter.getModels();
        if (models != null && models.size() > 0) {
            //处理item
            if (models != null && models.size() > 5) {
                ViewGroup.LayoutParams params = mListView.getLayoutParams();
                if (params != null) {
                    DisplayMetrics dm = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                    params.height = dm.heightPixels / 2;
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
                        } else if (sureTv.getVisibility() == View.VISIBLE) {
                            mListAdapter.updateSelect(position);
                            mListAdapter.notifyDataSetChanged();
                        } else {
                            if (mWidgetListener != null && mWidgetListener instanceof OnSelectListener) {
                                if (!((OnSelectListener) mWidgetListener).select(true, model)) {
                                    dismiss();
                                }
                            }
                        }
                    }
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancelTv) {
            dismiss();
        } else if (v.getId() == R.id.selectAllTv) {
            boolean selectAll = false;
            if (selectAllTv.getTag() == null) {
                selectAll = true;
                selectAllTv.setText(R.string.bit_dialog_noselect_all);
                selectAllTv.setTag("noselect");
            } else {
                selectAll = false;
                selectAllTv.setText(R.string.bit_dialog_select_all);
                selectAllTv.setTag(null);
            }
            if (mListAdapter != null) {
                mListAdapter.updateSelect(selectAll);
            }
        } else if (v.getId() == R.id.sureTv) {
            if (mWidgetListener != null && mListAdapter != null) {
                if (mWidgetListener instanceof OnMultiSelectListener) {
                    List<BitDialogModel> models = mListAdapter.getSelectModels();
                    if (!((OnMultiSelectListener) mWidgetListener).select(true, models)) {
                        dismiss();
                    }
                } else if (mWidgetListener instanceof OnSelectListener) {
                    BitDialogModel model = mListAdapter.getFirstSelectModel();
                    if (model != null) {
                        if (!((OnSelectListener) mWidgetListener).select(true, model)) {
                            dismiss();
                        }
                    }
                }
            }
        }
    }

    private class ListAdapter extends BaseAdapter {
        private List<BitDialogModel> models;
        private int lastSelectIndex;

        public ListAdapter(boolean isMulti, List<BitDialogModel> models) {
            this.lastSelectIndex = -1;
            this.models = models;
            if (!isMulti && models != null && models.size() > 0) {
                boolean hasSelect = false;
                for (BitDialogModel e : models) {
                    if (e.isSelected()) {
                        if (hasSelect) {
                            e.setSelected(false);
                        } else {
                            hasSelect = true;
                        }
                    }
                }
            }
        }

        private void updateSelect(boolean selectAll) {
            if (models != null) {
                for (BitDialogModel e : models) {
                    e.setSelected(selectAll);
                }
                notifyDataSetChanged();
            }
        }

        private void updateSelect(int selectIndex) {
            if (models != null) {
                if (lastSelectIndex >= 0 && lastSelectIndex < models.size()) {
                    models.get(lastSelectIndex).setSelected(false);
                }
                if (selectIndex >= 0 && selectIndex < models.size()) {
                    models.get(selectIndex).setSelected(true);
                }
            }
            this.lastSelectIndex = selectIndex;
        }

        private List<BitDialogModel> getSelectModels() {
            if (this.models == null || this.models.size() <= 0) {
                return new ArrayList<>();
            } else {
                List<BitDialogModel> selectModels = new ArrayList<>();
                for (BitDialogModel e : models) {
                    if (e.isSelected()) {
                        selectModels.add(e);
                    }
                }
                return selectModels;
            }
        }

        private BitDialogModel getFirstSelectModel() {
            if (this.models == null || this.models.size() <= 0) {
                return null;
            } else {
                for (BitDialogModel e : models) {
                    if (e.isSelected()) {
                        return e;
                    }
                }
                return null;
            }
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
                mViewHolder.contentTv.setText(model.getText());
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
