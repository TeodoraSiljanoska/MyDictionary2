package com.example.mydictionary2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import java.util.HashMap;
import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;



public class DictionaryActivity extends AppCompatActivity {
    private HashMap<String, String> dictionary;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        dictionary = new HashMap<>();
        try {
            InputStream inputStream = getAssets().open("dictionary.txt");
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                dictionary.put(parts[0], parts[1]);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookup();
            }
        });



    }
    public void lookup() {
        EditText the_word = findViewById(R.id.the_word);
        String word = the_word.getText().toString();
        String defn = dictionary.get(word);
        if (defn != null) {
            TextView the_defn = findViewById(R.id.the_defn);
            the_defn.setText(defn);
        } else {
            Toast.makeText(this, "Zborot ne e pronajden!", Toast.LENGTH_SHORT).show();
            TextView not_found = findViewById(R.id.the_defn);
            not_found.setText("Zborot ne e pronajden!");
        }
    }

}