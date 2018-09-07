package com.bitliker.core.bitutils.base.swipebacklayout.help;


import com.bitliker.core.bitutils.base.swipebacklayout.layout.SwipeBackLayout;

/**
 * @author Yrom
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    SwipeBackLayout getSwipeBackLayout();

    void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
   void scrollToFinishActivity();

}
