package com.doive.nameless.litter_hydra.ui.news;

import android.os.Bundle;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.base.BaseFragment;

public class NewsFragment
        extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle       args     = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }


}
