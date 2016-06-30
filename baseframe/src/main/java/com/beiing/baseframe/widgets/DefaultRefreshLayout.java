package com.beiing.baseframe.widgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.beiing.baseframe.R;

/**
 * Created by wei on 2016/3/30.
 */
public class DefaultRefreshLayout extends SwipeRefreshLayout {

    public DefaultRefreshLayout(Context context) {
        super(context);
        init();
    }

    public DefaultRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(R.color.md_light_blue_100,
                R.color.md_light_blue_900,
                R.color.md_light_blue_200,
                R.color.md_light_blue_800,
                R.color.md_light_blue_300,
                R.color.md_light_blue_700);
    }
}
