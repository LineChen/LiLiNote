package base.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;

import com.beiing.lilinote.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import base.utils.ThemeUtils;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by chenliu on 2016/6/30.<br/>
 * 描述：
 * </br>
 */
public abstract class BaseActivity extends AppCompatActivity implements SwipeBackActivityBase{
    private SwipeBackActivityHelper sbActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);

        initToolBar();

        initSwipBack();
    }

    private void initSwipBack() {
        sbActivityHelper = new SwipeBackActivityHelper(this);
        sbActivityHelper.onActivityCreate();
        setSwipeBackEnable(initSwipeBackEnable());
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        sbActivityHelper.onPostCreate();
    }

    //////////////////////////////////////滑动销毁Activity重写的方法////////////////////////////////
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return sbActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        sbActivityHelper.getSwipeBackLayout().scrollToFinishActivity();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 初始化ToolBar
     * @param toolbar
     */
    protected void initToolBar(Toolbar toolbar) {
        if (toolbar == null) return;
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 初始化主题
     */
    private void initTheme() {
        ThemeUtils.Theme currentTheme = ThemeUtils.getCurrentTheme(this);
        ThemeUtils.changeTheme(this, currentTheme);
    }

    /**
     * 获取主题颜色
     *
     * @return
     */
    private int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }


    /**
     * api大于19的时候，实现沉浸式状态栏
     */
    @TargetApi(19)
    protected void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getStatusBarColor());
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    //配合 初始化主题设置主体颜色
    protected int getStatusBarColor() {
        return getColorPrimary();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 设置是否滑动销毁Activity
     * @return
     */
    protected abstract boolean initSwipeBackEnable();

    /**
     * 初始化ToolBar
     */
    protected abstract void initToolBar();

    /**
     * 获取布局id
     * @return
     */
    protected abstract int getContentViewId();
}
