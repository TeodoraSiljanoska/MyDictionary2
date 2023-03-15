package com.example.mydictionary2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
public class ExplanationActivity extends AppCompatActivity {

    TextView wordTextView;
    TextView explanationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation);

        wordTextView = findViewById(R.id.word_text);
        explanationTextView = findViewById(R.id.explanation_text);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String word = extras.getString("word");
            String explanation = extras.getString("explanation");
            wordTextView.setText(word);
            explanationTextView.setText(explanation);
        }
    }
}
