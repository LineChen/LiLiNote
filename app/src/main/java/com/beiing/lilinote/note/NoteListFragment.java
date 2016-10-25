package com.beiing.lilinote.note;


import android.support.v7.widget.RecyclerView;

import com.beiing.lilinote.R;

import base.fragment.BaseFragment;
import butterknife.Bind;

/**
 * Created by chenliu on 2016/7/8.<br/>
 * 描述：note 列表
 * </br>
 */
public class NoteListFragment extends BaseFragment {


    @Bind(R.id.rv_note)
    RecyclerView rvNote;

    public static NoteListFragment getInstatnce(){
        return new NoteListFragment();
    }

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentID() {
        return R.layout.fragment_note_list;
    }

}
