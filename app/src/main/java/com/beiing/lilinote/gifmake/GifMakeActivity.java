package com.beiing.lilinote.gifmake;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.beiing.baseframe.adapter.for_recyclerview.support.OnItemClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.GifImage;
import com.beiing.lilinote.constant.Constant;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.activity.BaseActivity;
import base.image_selector.MultiImageSelector;
import butterknife.Bind;
import butterknife.OnClick;

public class GifMakeActivity extends BaseActivity  implements IGifMakeView{


    public static final String TAG = "GifMakeActivity";
    public static final int START_ALBUM_CODE = 0x21;


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.grid_view)
    RecyclerView gridView;
    @Bind(R.id.tv_generate)
    TextView generate;
    @Bind(R.id.clear)
    TextView clear;

    private ImageAdapter adapter;

    private GifMakePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initPresenter() {
        presenter = new GifMakePresenter(this, this);
    }

    @Override
    protected void initData() {
        gridView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new ImageAdapter(this, presenter.getGifImages());
        gridView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener<GifImage>() {
            @Override
            public void onItemClick(@NonNull ViewGroup parent, @NonNull View view, GifImage gifImage, int position) {
                if(gifImage.getType() == GifImage.TYPE_ICON){
                    MultiImageSelector.create()
                            .showCamera(true) // show camera or not. true by default
                            .count(9) // max select image size, 9 by default. used width #.multi()
                            .multi() // multi mode, default mode;
                            .start(GifMakeActivity.this, Constant.REQUEST_CODE_SELECT_IMAGE);
                }
            }

            @Override
            public boolean onItemLongClick(@NonNull ViewGroup parent, @NonNull View view, GifImage gifImage, int position) {
                return false;
            }
        });
    }


    @Override
    protected boolean initSwipeBackEnable() {
        return true;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar(toolbar);
        toolbar.setTitle(R.string.gif_make);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_gifmake;
    }

    @OnClick(value = {R.id.tv_generate, R.id.clear})
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_generate://生成gif图
                Toast.makeText(GifMakeActivity.this, "开始生成Gif图", Toast.LENGTH_SHORT).show();
                presenter.createGif(50);
                break;
            case R.id.clear:
                presenter.getGifImages().clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.REQUEST_CODE_SELECT_IMAGE){
            if(resultCode == RESULT_OK){
                List<String> paths = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                presenter.solveImages(paths);
                Log.e("==", "paths:" + paths.size());
            }
        }
    }

    @Override
    public void finishPaths() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void finishCreate(boolean b) {
        if(b){
            Toast.makeText(this, "生成成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "生成失败", Toast.LENGTH_SHORT).show();
        }
    }
}
