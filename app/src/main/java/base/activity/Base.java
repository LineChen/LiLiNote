package base.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.beiing.baseframe.supports.DefaultReceiver;
import com.beiing.lilinote.R;
import com.beiing.lilinote.constant.Constant;

import base.utils.ThemeUtils;

/**
 * Created by chenliu on 2016/7/8.<br/>
 * 描述：
 * </br>
 */
public abstract class Base extends AppCompatActivity{

    /**
     * overridePendingTransition mode
     */
    public enum TransitionMode {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        SCALE,
        FADE
    }

    private LocalBroadcastManager lbManager;
    private DefaultReceiver receiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startAnime();
        initTheme();
        super.onCreate(savedInstanceState);

        initIntentFilter();
        getIntentFilter();
        initReceiver();
    }

    /**
     * 初始化主题
     */
    private void initTheme() {
        ThemeUtils.Theme currentTheme = ThemeUtils.getCurrentTheme(this);
        ThemeUtils.changeTheme(this, currentTheme);
    }


    private void startAnime() {
        switch (getOverridePendingTransitionMode()) {
            case LEFT:
                overridePendingTransition(R.anim.left_in,R.anim.left_out);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            case TOP:
                overridePendingTransition(R.anim.top_in,R.anim.top_out);
                break;
            case BOTTOM:
                overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
                break;
            case SCALE:
                overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
                break;
            case FADE:
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
        }
    }

    /**
     * 默认是 FADE 动画
     * @return
     */
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }



    //=========================================广播处理=======================================
    /**
     * 初始化广播
     */
    private void initReceiver() {
        lbManager = LocalBroadcastManager.getInstance(this);
        receiver = new DefaultReceiver();
        lbManager.registerReceiver(receiver, intentFilter);
        receiver.setReceiver(new DefaultReceiver.ReceiverListener() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(openReceiver()){
                    onReceiver(context, intent);
                }
            }
        });
    }


    /**
     * 初始化 intentFilter
     * @return
     */
    private void initIntentFilter() {
        intentFilter = new IntentFilter();
    }

    /**
     * 获取 intentFilter
     */
    protected void getIntentFilter(){
        intentFilter.addAction(Constant.ACTION_CHANGE_THEME);
    }

    protected void onReceiver(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(Constant.ACTION_CHANGE_THEME)){
            reload();
        }
    }

    public void sendNotification(Intent intent){
        lbManager.sendBroadcast(intent);
    }


    //=========================================广播处理 end =======================================

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    /**
     * 是否接收广播
     * @return
     */
    protected abstract boolean openReceiver();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        lbManager.unregisterReceiver(receiver);
    }
}
