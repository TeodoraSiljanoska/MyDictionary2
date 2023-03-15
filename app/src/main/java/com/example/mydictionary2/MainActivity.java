package com.example.mydictionary2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Dictionary;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    Button firstButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstButton = findViewById(R.id.buttonFirst);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one= new Intent(MainActivity.this, Menu.class);
                startActivity(intent_one);
            }
    });
}
}