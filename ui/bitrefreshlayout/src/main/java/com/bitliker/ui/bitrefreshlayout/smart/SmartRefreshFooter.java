package com.bitliker.ui.bitrefreshlayout.smart;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitliker.ui.bitrefreshlayout.BaseRefreshView;
import com.bitliker.ui.bitrefreshlayout.R;


/**
 * Created by Bitliker on 2017/9/18.
 */

public class SmartRefreshFooter extends BaseRefreshView {
    EatBeanLoadingView ghostLoadingView;
    TextView statusTV;
    private final ImageView statusImg;

    public SmartRefreshFooter(Context context, LayoutInflater mInflater) {
        super(context, mInflater);
        mInflater.inflate(R.layout.refresh_smart_footer, this);
        ghostLoadingView = (EatBeanLoadingView) findViewById(R.id.srl_elv_pull_up);
        statusTV = (TextView) findViewById(R.id.statusTV);
        statusImg = (ImageView) findViewById(R.id.statusImg);
    }

    @Override
    public void startAnim() {
        statusTV.setVisibility(GONE);
        statusTV.setText(R.string.pull_up_to_load);
        statusImg.setImageResource(R.drawable.ic_refresh_pull_up);
        statusImg.setVisibility(GONE);
        ghostLoadingView.setVisibility(VISIBLE);
        ghostLoadingView.startAnim();
    }

    @Override
    public void stopAnim() {
        ghostLoadingView.stopAnim();
        statusTV.setVisibility(VISIBLE);
        statusImg.setVisibility(VISIBLE);
        ghostLoadingView.setVisibility(GONE);

    }


    @Override
    public void upStatus(int status) {
        switch (status) {
            case TRY_LOAD_MORE:
                statusTV.setText(R.string.pull_up_to_load);
                statusImg.setImageResource(R.drawable.ic_refresh_pull_up);
                break;
            case LOAD_MORE:
                statusTV.setText(R.string.up_to_load);
                statusImg.setImageResource(R.drawable.ic_refresh_pull_down);
                break;
        }
    }
}
