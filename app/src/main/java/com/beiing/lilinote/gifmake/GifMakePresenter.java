package com.beiing.lilinote.gifmake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.beiing.baseframe.utils.ImageUtil;
import com.beiing.lilinote.bean.GifImage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.mvp.BasePresenter;
import base.utils.GifMakeUtil;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by chenliu on 2016/7/1.<br/>
 * 描述：
 * </br>
 */
public class GifMakePresenter extends BasePresenter<IGifMakeView>{
    int MAX_COUNT = 16;

    List<GifImage> gifImages;

    public GifMakePresenter(Context mContext, IGifMakeView mView) {
        super(mContext, mView);
    }

    public List<GifImage> getGifImages() {
        if(gifImages == null){
            gifImages = new ArrayList<>();
            GifImage gif = new GifImage();
            gif.setType(GifImage.TYPE_ICON);
            gifImages.add(gif);
        }
        return gifImages;
    }

    public void solveImages(List<String> paths) {
        int size = gifImages.size() - 1;
        int count = MAX_COUNT - size;
        int sizeP = paths.size();
        GifImage gif = null;
        if(count < sizeP){
            for (int i = 0; i < count; i++) {
                gif = new GifImage();
                gif.setPath(paths.get(i));
                gifImages.add(gif);
            }
        } else {
            for (int i = 0; i < sizeP; i++) {
                gif = new GifImage();
                gif.setPath(paths.get(i));
                gifImages.add(gif);
            }
        }
        mView.finishPaths();
    }


    private List<String> getPaths(){
        List<String> paths = new ArrayList<>();
        int size = gifImages.size();
        for (int i = 1; i < size; i++) {
            paths.add(gifImages.get(i).getPath());
        }
        return paths;
    }


    /**
     * 生成gif图
     */
    public void createGif(final int fps) {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    GifMakeUtil.createGif(getPaths(), fps, 500, 500);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.finishCreate(false);
            }

            @Override
            public void onNext(String s) {
                mView.finishCreate(true);
            }
        });
    }


}
