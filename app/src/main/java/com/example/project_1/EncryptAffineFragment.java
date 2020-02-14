package com.example.project_1;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class EncryptAffineFragment extends Fragment implements View.OnClickListener{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_encrypt_affine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.enc_affine).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Button enc_affine = getView().findViewById(R.id.enc_affine);


        enc_affine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText alpha = getView().findViewById(R.id.alpha);
                EditText beta = getView().findViewById(R.id.beta);
                EditText plaintext_affine = getView().findViewById(R.id.plaintext_affine);
                Button enc_affine = getView().findViewById(R.id.enc_affine);
                TextView cipher_affine = getView().findViewById(R.id.cipher_affine);


                //Input for alpha and beta
                int j = Integer.parseInt(alpha.getText().toString());
                int k = Integer.parseInt(beta.getText().toString());

                if (j == 1||j==3||j==5||j==7||j==9||j==11||j==15||j==17||j==19||j==21||j==23||j==25){
                    if(k >= 0 && k <= 25){
                        String plain_text1 = plaintext_affine.getText().toString();
                        String cipher_text = encryption(j,k,plain_text1).replace(",", "").replace("[", "").replace("]", "")
                                .replace("null", "").replace(" ", "").trim();
                        cipher_affine.setText(cipher_text);

                    } else {
                        Toast.makeText(getContext(), "beta must be greater than 0 and less than 25", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "alpha must be 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, or 25 ", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    static char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    // The encryption algorithm for the affine cipher
    // it takes 'a' and 'b' as integers and also takes 'plain_text' as a string
    static String encryption(int j , int k , String plain_text) {


        //changes 'plain_text' to lowercase and stores it back in the 'plain_text'
        plain_text = plain_text.toLowerCase();

        // creates a char array with the 'plain_text' string changed to a char
        char[] char_text = plain_text.toCharArray();

        // creates a char array with chars from 'char_text' array
        char[] cipher_text = char_text;

        // Initializes the int 'text_index' to 0
        int text_index = 0;

        // for loop that will loop while 'i' is less than the length of the alphabet array
        for (int i = 0; i < alphabet.length; i++) {

            //if statement that checks of the char in the 'char_text' at index 'text_indext' is equal the the char in the
            //'alphabet' at index 'i'
            if (char_text[text_index] == alphabet[i]) {

                // if statement that checks if 'text_index' is less than or equal to 'char_text' length
                //if the if statement is true it will initialize int 'x' and set it equal to 'i'
                // it will also initialize int 'y' and set it equal to 'a' * 'x' + 'b' mod(26)
                // it finally sets 'cipher_text' at index 'text_index' equal to 'alphabet' at index 'y'
                if (text_index <= char_text.length) {
                    int x = i;
                    int y = (j * x + k) % 26;
                    cipher_text[text_index] = alphabet[y];

                    //if statement that checks if 'text_index' is equal to 'char_text' length - 1
                    //if the if statement is true i = 'alphabet' length and 'text_index' is set equal to 'char_text' length
                    if (text_index == char_text.length - 1) {
                        i = alphabet.length;
                        text_index = char_text.length;
                    }

                    //if statement that checks if 'text_index' is less than 'char_text' length
                    //if the if statement is true then text_index is incremented and i = -1
                    if (text_index < char_text.length - 1) {
                        text_index++;
                        i = -1;
                    }
                }
            }

        }

        return Arrays.toString(cipher_text);

    }

}



