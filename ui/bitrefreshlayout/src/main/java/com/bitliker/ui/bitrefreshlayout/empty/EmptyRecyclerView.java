package com.bitliker.ui.bitrefreshlayout.empty;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitliker.ui.bitrefreshlayout.R;


/**
 * Created by Bitlike on 2018/2/28.
 */

public class EmptyRecyclerView extends FrameLayout {
    private View emptyView;
    private RecyclerView mRecyclerView;
    private int emptyImage;
    private int emptyTextColor;
    private CharSequence emptyText;
    private OnRefreshListener mOnRefreshListener;

    public EmptyRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRecyclerView = new RecyclerView(context, attrs, defStyleAttr) {
            @Override
            public void setAdapter(Adapter adapter) {
                final Adapter oldAdapter = getAdapter();
                if (oldAdapter != null) {
                    oldAdapter.unregisterAdapterDataObserver(observer);
                }
                super.setAdapter(adapter);
                if (adapter != null) {
                    adapter.registerAdapterDataObserver(observer);
                }
                checkIfEmpty();
            }
        };


        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.EmptyRecyclerView);
        int emptyView = R.layout.common_empty_view;
        try {
            this.emptyText = array.getText(R.styleable.EmptyRecyclerView_emptyText);
            this.emptyTextColor = array.getColor(R.styleable.EmptyRecyclerView_emptyTextColor, -1);
            this.emptyImage = array.getResourceId(R.styleable.EmptyRecyclerView_emptyImage, R.drawable.ic_list_empty);
            emptyView = array.getResourceId(R.styleable.EmptyRecyclerView_emptyLayout, R.layout.common_empty_view);
        } finally {
            array.recycle();
        }

        initEmptyView(context, emptyView);
    }

    private void initEmptyView(Context context, int reId) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        emptyView = mInflater.inflate(reId, null);
        post(new Runnable() {
            @Override
            public void run() {
                addView(emptyView);
                addView(mRecyclerView);
                if (emptyView != null) {
                    emptyView.setVisibility(GONE);
                }
            }
        });
    }

    //设置没有内容时，提示用户的空布局
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        if (emptyView != null) {
            getEmptyView().setOnClickListener(mOnClickListener);
        }
        checkIfEmpty();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    private final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };


    private ImageView emptyImg;
    private TextView emptyTv;

    private void checkIfEmpty() {
        if (emptyView != null && mRecyclerView.getAdapter() != null) {
            final boolean emptyViewVisible =
                    mRecyclerView.getAdapter().getItemCount() == 0;
            if (emptyViewVisible) {
                mRecyclerView.setVisibility(GONE);
                emptyView.setVisibility(VISIBLE);

                if (emptyImg == null) {
                    emptyImg = emptyView.findViewById(R.id.emptyImg);
                    if (emptyImage > 0) {
                        emptyImg.setImageResource(emptyImage);
                    }
                }
                if (emptyTv == null) {
                    emptyTv = emptyView.findViewById(R.id.emptyTv);
                    if (!TextUtils.isEmpty(emptyText)) {
                        emptyTv.setText(emptyText);
                    }
                    if (this.emptyTextColor != -1) {
                        emptyTv.setTextColor(this.emptyTextColor);
                    }
                }
            } else {
                mRecyclerView.setVisibility(VISIBLE);
                emptyView.setVisibility(GONE);
            }


        }
    }

    public void setOnRefreshListener(OnRefreshListener mOnRefreshListener) {
        this.mOnRefreshListener = mOnRefreshListener;
        if (this.mOnRefreshListener != null && getEmptyView() != null) {
            getEmptyView().setOnClickListener(mOnClickListener);
        }
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == getEmptyView() && mOnRefreshListener != null) {
                mRecyclerView.setVisibility(VISIBLE);
                emptyView.setVisibility(GONE);
                mOnRefreshListener.clickRefresh();
            }
        }
    };

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        try {
            super.onRestoreInstanceState(state);
        } catch (Exception e) {
        }
        state = null;
    }

    public interface OnRefreshListener {
        void clickRefresh();
    }
}
