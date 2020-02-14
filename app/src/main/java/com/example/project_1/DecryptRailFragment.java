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


public class DecryptRailFragment extends Fragment implements View.OnClickListener{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_decrypt_rail, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final EditText rail_n = getView().findViewById(R.id.rail_n);
        final EditText editText2 = getView().findViewById(R.id.editText2);
        Button button1 = getView().findViewById(R.id.button1);
        final TextView textView3 = getView().findViewById(R.id.textView3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.parseInt(rail_n.getText().toString());
                if (k < 2) {
                    Toast.makeText(getContext(), "n must be greater than 1", Toast.LENGTH_SHORT).show();
                } else {
                    String plain_text = editText2.getText().toString();
                    String cipher_text = decryption(k, plain_text).replace(",", "").replace("[", "").replace("]", "")
                            .replace("null", "").replace(" ", "").trim();
                    textView3.setText(cipher_text);

                }
            }
        });
    }
    static String decryption(int key, String text) {

        //initializes int 'row_inc' and sets it equal to 1
        int row_inc = 1;

        //initializes int text_pos and sets it equal to 0
        int text_pos = 0;

        //sets array 'encrypted_text' equal to the string 'text' with the split command that splits making the characters that make it up into strings
        String[] encrypted_text = text.split("");

        //sets array 'plain_text' equal to the string 'text' with the split command that splits making the characters that make it up into strings
        String[] plain_text = text.split("");

        //sets arraylist 'deciphering' with length 'key' and 'text' length
        String deciphering[][] = new String[key][text.length()];

        //for loop that will loop while 'i' is less than 'plain_text' length
        //it will loop and replace all strings in 'plain_text' with '*'
        for (int i = 0; i < plain_text.length; i++) {
            plain_text[i] = "*";
        }

        //for loop that will loop while 'col' is less than 'text' length
        for (int row = 0, col = 0; col < text.length(); col++) {

            //if statement that checks if 'row' + 'row_inc' is equal to 'deciphering' length or equal to -1
            //if the if statement is true it will multiply 'row_inc' by -1
            if (row + row_inc == deciphering.length || row + row_inc == -1) {
                row_inc *= -1;
            }

            // sets 'deciphering' at index 'row' and 'col' equal to 'plain_text' at index 'col'
            deciphering[row][col] = plain_text[col];

            // 'row' is set equal to 'row' + 'row_inc'
            row += row_inc;
        }

        //for loop that will loop while 'row' is less than 'key'
        for (int row = 0; row < key; row++) {

            //for loop that will loop while 'col' is less than 'text' length
            for (int col = 0; col < text.length(); col++) {

                //if statement that checks if '*' is present in 'deciphering' at index [row][col]
                //if the if statement is true then 'deciphering' at index [row][col] equals 'encrypted_text' at index [text_pos]
                //'text_pos' is then increased by one
                if (deciphering[row][col] == "*") {
                    deciphering[row][col] = encrypted_text[text_pos];
                    text_pos += 1;
                }
            }

        }

        //for loop that will loop while 'col' is less than 'text' length
        for (int row = 0, col = 0; col < text.length(); col++) {

            //if statement that checks if 'row' + 'row_inc' equals 'deciphering' length or equals - 1
            //if the statement is true 'row_inc' is multiplied by -1
            if (row + row_inc == deciphering.length || row + row_inc == -1) {
                row_inc *= -1;
            }

            //'plain_text' at index [col] is set equal to 'deciphering'at index [row][col]
            plain_text[col] = deciphering[row][col];

            // 'row' is set equal to 'row' + 'row_inc'
            row += row_inc;
        }

        //returns plain_text converted to a string
        return Arrays.deepToString(plain_text);
    }
}
