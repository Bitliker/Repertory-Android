package com.bitliker.ui.bitrefreshlayout.simlpe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitliker.ui.bitrefreshlayout.BaseRefreshView;
import com.bitliker.ui.bitrefreshlayout.R;


/**
 * Created by Bitliker on 2017/9/18.
 */

public class SimpleRefreshHeader extends BaseRefreshView {
    private TextView statusTV;
    private ImageView statusImg;
    private RotateAnimation rotate;

    public SimpleRefreshHeader(Context context, LayoutInflater mInflater) {
        super(context, mInflater);
        mInflater.inflate(R.layout.refresh_header, this);
        statusImg = (ImageView) findViewById(R.id.statusImg);
        statusTV = (TextView) findViewById(R.id.statusTV);

    }

    private Animation getImgAnimation() {
        if (rotate == null) {
            rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            LinearInterpolator lin = new LinearInterpolator();
            rotate.setInterpolator(lin);
            rotate.setDuration(1000);//设置动画持续时间
            rotate.setRepeatCount(-1);//设置重复次数
            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            rotate.setStartOffset(10);//执行前的等待时间
        }
        return rotate;
    }

    @Override
    public void startAnim() {
        statusTV.setText(R.string.refreshing);
        statusImg.setImageResource(R.drawable.ic_baseutil_simple_load);
        statusImg.setAnimation(getImgAnimation());
    }

    @Override
    public void stopAnim() {
        statusImg.setAnimation(null);
        statusImg.setImageResource(R.drawable.ic_refresh_pull_down);
        statusTV.setText(R.string.pull_down_to_refresh);
    }

    @Override
    public void upStatus(int status) {
        statusImg.setAnimation(null);
        switch (status) {
            case TRY_REFRESH:
                statusTV.setText(R.string.pull_down_to_refresh);
                statusImg.setImageResource(R.drawable.ic_refresh_pull_down);
                break;
            case REFRESH:
                statusTV.setText(R.string.up_to_refresh);
                statusImg.setImageResource(R.drawable.ic_refresh_pull_up);
                break;
        }
    }

    private Animation getStatusAnimation() {
        if (rotate == null) {
            rotate = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            LinearInterpolator lin = new LinearInterpolator();
            rotate.setInterpolator(lin);
            rotate.setDuration(1000);//设置动画持续时间
            rotate.setRepeatCount(-1);//设置重复次数
            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            rotate.setStartOffset(10);//执行前的等待时间
        }
        return rotate;
    }

}
