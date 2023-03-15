package com.example.mydictionary2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    Button kopce;
    HashMap<String, String> dictionary = new HashMap<String, String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("dictionary.txt")));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(":");
                dictionary.put(parts[0], parts[1]);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> wordsList = new ArrayList<String>(dictionary.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordsList);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = wordsList.get(position);
                String explanation = dictionary.get(word);
                Intent intent = new Intent(ListViewActivity.this, ExplanationActivity.class);
                intent.putExtra("word", word);
                intent.putExtra("explanation", explanation);
                startActivity(intent);
            }
        });

        kopce = findViewById(R.id.kopce);
        kopce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(ListViewActivity.this);
                dialog.setContentView(R.layout.dialog_box);
                final EditText wordEditText = dialog.findViewById(R.id.editTextWord);
                final EditText explanationEditText = dialog.findViewById(R.id.editTextDefinition);
                Button addButton = dialog.findViewById(R.id.buttonConfirm);
                addButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {

                        String newWord = wordEditText.getText().toString();
                        String newExplanation = explanationEditText.getText().toString();
                        dictionary.put(newWord, newExplanation);
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));
                            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                                writer.write(entry.getKey() + ":" + entry.getValue());
                                writer.newLine();
                            }
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
              });
                Button cancelButton = dialog.findViewById(R.id.buttonCancel);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });
    }

}