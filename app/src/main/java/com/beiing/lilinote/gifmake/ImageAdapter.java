package com.beiing.lilinote.gifmake;

import android.content.Context;
import android.widget.ImageView;

import com.beiing.baseframe.adapter.ViewHolder;
import com.beiing.baseframe.adapter.for_recyclerview.adapter.CommonAdapter;
import com.beiing.baseframe.adapter.for_recyclerview.support.ItemSupport;
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
    public ImageAdapter(Context context, List<GifImage> datas) {
        super(context, datas, new ItemSupport<GifImage>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_gif_image;
            }
        });
    }

    @Override
    protected void bindItemView(ViewHolder holder, GifImage image) {
        ImageView view = holder.getView(R.id.image_view);
        if(image.getType() == GifImage.TYPE_IMAGE){
            Glide.with(mContext).load(new File(image.getPath())).into(view);
        } else if(image.getType() == GifImage.TYPE_ICON){
            view.setImageResource(R.mipmap.icon_plus);
        }
    }
}






