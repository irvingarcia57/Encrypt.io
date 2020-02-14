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


public class DecryptAffineFragment extends Fragment implements View.OnClickListener{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_decrypt_affine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.dec_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Button dec_button = getView().findViewById(R.id.dec_button);


        dec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText alpha_dec = getView().findViewById(R.id.alpha_dec);
                EditText beta_dec = getView().findViewById(R.id.beta_dec);
                EditText plaintext_dec = getView().findViewById(R.id.plaintext_dec);
                Button dec_button = getView().findViewById(R.id.dec_button);
                TextView dec_plaintext = getView().findViewById(R.id.dec_plaintext);


                //Input for alpha and beta
                int j = Integer.parseInt(alpha_dec.getText().toString());
                int k = Integer.parseInt(beta_dec.getText().toString());

                if (j == 1||j==3||j==5||j==7||j==9||j==11||j==15||j==17||j==19||j==21||j==23||j==25){
                    if(k >= 0 && k <= 25){
                        String plain_text1 = plaintext_dec.getText().toString();
                        String cipher_text = decryption(j,k,plain_text1).replace(",", "").replace("[", "").replace("]", "")
                                .replace("null", "").replace(" ", "").trim();
                        dec_plaintext.setText(cipher_text);

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
    static String decryption(int a, int b, String cipher_text) {

        //initializes int 'a_inverse' and sets it equal to 1
        int a_inverse = 1;

        //changes 'cipher_text' to lowercase and stores it back in the 'cipher_text'
        cipher_text = cipher_text.toLowerCase();

        // creates a char array with the 'cipher_text' string changed to a char
        char[] char_text = cipher_text.toCharArray();

        // creates a char array with chars from 'char_text' array
        char[] plain_text = char_text;

        // Initializes the int 'text_index' to 0
        int text_index = 0;

        // while loop that finds the inverse 'a' that will equal when multiplied with a and mod(26)
        while ((a * a_inverse) % 26 != 1) {
            a_inverse += 1;
        }

        // for loop that will loop while 'i' is less than the length of the alphabet array
        for (int i = 0; i < alphabet.length; i++) {

            ////if statement that checks of the char in the 'char_text' at index 'text_indext' is equal the the char in the
            //'alphabet' at index 'i'
            if (char_text[text_index] == alphabet[i]) {

                // if statement that checks if 'text_index' is less than or equal to 'char_text' length
                //if the if statement is true it will initialize int 'y' and set it equal to 'i'
                // it will also initialize int 'x' and set it equal to 'a_inverse' * 'y' -'b' mod(26)
                if (text_index <= char_text.length) {
                    int y = i;
                    int x = (a_inverse * (y - b)) % 26;

                    //if statement that checks if 'x' is less than 0
                    //if the if statement is true 26 will be added to 26
                    if (x < 0) {
                        x += 26;
                    }
                    // it finally sets 'plain_text' at index 'text_index' equal to 'alphabet' at index 'x'
                    plain_text[text_index] = alphabet[x];

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

        // returns 'plain_text' converted to a string
        return Arrays.toString(plain_text);

    }

}



