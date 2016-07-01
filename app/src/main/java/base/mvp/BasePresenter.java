package base.mvp;

import android.content.Context;

public abstract class BasePresenter<V extends IBaseView> {

    protected V mView;

    protected Context mContext;

    public BasePresenter(Context mContext, V mView) {
        this.mView = mView;
        this.mContext = mContext;
    }

}
