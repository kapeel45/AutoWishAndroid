package com.autowish.UIFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.autowish.R;
import com.autowish.adapters.recyclerAdapterHomeAds;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout linear_bikes, linear_cars;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        linear_cars=(LinearLayout)v.findViewById(R.id.linear_cars);
        linear_cars.setOnClickListener(this);
        linear_bikes=(LinearLayout)v.findViewById(R.id.linear_bikes);
        linear_bikes.setOnClickListener(this);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recyclerAdapterHomeAds(getActivity(), 10);
        recyclerView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.linear_bikes:
                break;

            case R.id.linear_cars:
                break;
        }
    }
}
