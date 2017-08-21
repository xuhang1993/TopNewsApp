package com.xu.topnews.module.person.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xu.appbaseui.fragment.XuBaseFragment;
import com.xu.topnews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends XuBaseFragment {


    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    protected void iniView(View mView) {

    }

    @Override
    protected int getmContentId() {
        return 0;
    }

}
