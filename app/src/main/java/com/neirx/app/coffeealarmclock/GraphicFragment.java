package com.neirx.app.coffeealarmclock;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GraphicFragment extends Fragment {

    public static GraphicFragment newInstance() {
        GraphicFragment fragment = new GraphicFragment();
        return fragment;
    }

    public GraphicFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_graphic, container, false);

        return rootView;
    }
}
