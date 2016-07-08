package com.beiing.lilinote.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beiing.lilinote.R;

import base.fragment.BaseFragment;

/**
 * Created by chenliu on 2016/7/8.<br/>
 * 描述：note 列表
 * </br>
 */
public class NoteListFragment extends BaseFragment {


    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentID() {
        return R.layout.fragment_note_list;
    }

}
