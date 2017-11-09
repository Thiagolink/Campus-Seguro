package com.example.android.campusseguro.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.campusseguro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Configuracao extends Fragment {


    public Configuracao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuracao, container, false);
    }

}
