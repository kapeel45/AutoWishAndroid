package com.autowish.UIFragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autowish.AddAdActivity;
import com.autowish.R;
import com.autowish.adapters.recyclerAdapterHomeAds;
import com.autowish.adapters.recyclerAdapterMyAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAdsFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingbtn;

    public MyAdsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_ads, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recyclerAdapterMyAds(getActivity(), 10);
        recyclerView.setAdapter(mAdapter);

        floatingbtn=(FloatingActionButton)v.findViewById(R.id.floatingbtn);
        floatingbtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingbtn:
                Intent i = new Intent(getActivity(), AddAdActivity.class);
                startActivity(i);
            break;
        }
    }
}
