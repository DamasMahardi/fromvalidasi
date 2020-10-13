package com.damas.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.damas.myapplication.Model.User;
import com.damas.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultFragment extends Fragment {
    private View view;

    @BindView(R.id.tv_resultNim)
    TextView rNim;
    @BindView(R.id.tv_resultNama)
    TextView rNama;
    @BindView(R.id.tv_resultTempat)
    TextView rTempat;
    @BindView(R.id.tv_resultTanggal)
    TextView rTanggal;

    //buttton
    @BindView(R.id.btn_Exit)
    Button rbtnExit;



    public ResultFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result,container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        Viewundle();
        onClick();

        return view;
    }

    private void onClick() {
        rbtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });

    }

    private void Viewundle() {
        User user = (User)getArguments().getSerializable("user");
        rNim.setText(user.getNim());
        rNama.setText(user.getNama());
        rTempat.setText(user.getTempat());
        rTanggal.setText(user.getTanggal());

    }
    private void closeFragment(){
        First_Fragment firstFragment = new First_Fragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frameActivity, firstFragment).commit();
    }

}