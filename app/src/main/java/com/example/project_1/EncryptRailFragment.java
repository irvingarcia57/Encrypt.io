package com.example.project_1;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class EncryptRailFragment extends Fragment implements View.OnClickListener{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_encrypt_rail, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.enc_rail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //final EditText rail_n, plaintext_rail;
        //Button enc_rail;
        //final TextView cipher_rail;

        final EditText rail_n = getView().findViewById(R.id.rail_n);
        final EditText plaintext_rail = getView().findViewById(R.id.plaintext_rail);
        Button enc_rail = getView().findViewById(R.id.enc_rail);
        final TextView cipher_rail = getView().findViewById(R.id.cipher_rail);


        enc_rail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.parseInt(rail_n.getText().toString());
                if (k < 2) {
                    Toast.makeText(getContext(), "n must be greater than 1", Toast.LENGTH_SHORT).show();
                } else {
                    String plain_text = plaintext_rail.getText().toString();
                    String cipher_text = encryption(k, plain_text).replace(",", "").replace("[", "").replace("]", "")
                            .replace("null", "").replace(" ", "").trim();
                    cipher_rail.setText(cipher_text);

                }
            }
        });
    }
    static String encryption(int key, String text) {

        //initializes int 'row_inc' and sets it equal to 1
        int row_inc = 1;

        //sets array 'plain_text' equal to the string 'text' with the split command that splits making the characters that make it up into strings
        String plain_text[] = text.split("");

        //sets arraylist 'cipher' with length 'key' and 'text' length
        String cipher[][] = new String[key][text.length()];

        //for loop that will loop while 'col' is less than 'text' length
        for (int row = 0, col = 0; col < text.length(); col++) {

            //if statement that checks if 'row' + 'row_inc' is equal to 'cipher' length or equal to -1
            //if the if statement is true it will multiply 'row_inc' by -1
            if (row + row_inc == cipher.length || row + row_inc == -1) {
                row_inc *= -1;
            }

            // sets 'cipher' at index 'row' and 'col' equal to 'plain_text' at index 'col'
            cipher[row][col] = plain_text[col];

            // 'row' is set equal to 'row' + 'row_inc'
            row += row_inc;
        }

        //returns cipher converted to a string
        return Arrays.deepToString(cipher);
    }
}
