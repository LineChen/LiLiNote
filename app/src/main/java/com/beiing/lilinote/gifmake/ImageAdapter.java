package com.beiing.lilinote.gifmake;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beiing.baseframe.adapter.ViewHolder;
import com.beiing.baseframe.adapter.for_recyclerview.adapter.CommonAdapter;
import com.beiing.baseframe.adapter.for_recyclerview.support.ItemSupport;
import com.beiing.baseframe.supports.OnClickListener;
import com.beiing.lilinote.R;
import com.beiing.lilinote.bean.GifImage;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * Created by chenliu on 2016/7/1.<br/>
 * 描述：
 * </br>
 */
public class ImageAdapter extends CommonAdapter<GifImage>{

    public static final int MODE_COMMON = 0;
    public static final int MODE_DELETE = 1;
    private int mode;

    OnClickListener<GifImage> clickListener;

    public ImageAdapter(Context context, List<GifImage> datas) {
        super(context, datas, new ItemSupport<GifImage>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_gif_image;
            }
        });
    }

    @Override
    protected void bindItemView(final ViewHolder holder, final GifImage image) {
        ImageView view = holder.getView(R.id.image_view);
        int type = image.getType();
        if(type == GifImage.TYPE_IMAGE){
            Glide.with(mContext).load(new File(image.getPath())).into(view);
        } else if(type == GifImage.TYPE_ICON){
            view.setImageResource(R.mipmap.icon_plus);
        }

        ImageView icon = holder.getView(R.id.iv_delete);
        if(type == GifImage.TYPE_IMAGE){
            if(mode == MODE_COMMON){
                icon.setVisibility(View.GONE);
            } else if(mode == MODE_DELETE){
                icon.setVisibility(View.VISIBLE);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(clickListener != null){
                            clickListener.onClick(holder.getAdapterPosition(), R.id.iv_delete, image);
                        }
                    }
                });
            }
        }else {
            icon.setVisibility(View.GONE);
        }
    }

    public void setClickListener(OnClickListener<GifImage> clickListener) {
        this.clickListener = clickListener;
    }

    public void setMode(int mode) {
        this.mode = mode;
        notifyDataSetChanged();
    }

    public int getMode() {
        return mode;
    }
}






