package com.example.project_1;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Decider_frag extends Fragment implements View.OnClickListener{

    NavController navController = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_decider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);
        view.findViewById(R.id.decider_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RadioButton encrypt_radio = getView().findViewById(R.id.encrypt_radio);
        RadioButton decrypt_radio = getView().findViewById(R.id.decrypt_radio);
        RadioButton affine_radio = getView().findViewById(R.id.affine_radio);
        RadioButton rail_radio = getView().findViewById(R.id.rail_radio);

        if(encrypt_radio.isChecked() && affine_radio.isChecked()){
            navController.navigate(R.id.action_decider_frag_to_encryptAffineFragment);
        }
        if(encrypt_radio.isChecked() && rail_radio.isChecked()){
            navController.navigate(R.id.action_decider_frag_to_encryptRailFragment);
        }
        if(decrypt_radio.isChecked() && affine_radio.isChecked()){
            navController.navigate(R.id.action_decider_frag_to_decryptAffineFragment);
        }
        if(decrypt_radio.isChecked() && rail_radio.isChecked()){
            navController.navigate(R.id.action_decider_frag_to_decryptRailFragment);
        }


    }
}
