package com.example.bharat.hw4;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class enterValues extends AppCompatActivity {
DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_values);
        final Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String syn = "";
                String ant = "";
                EditText t1 = findViewById(R.id.synonym);
                EditText t2 = findViewById(R.id.antonym);
                syn = t1.getText().toString();
                ant = t2.getText().toString();
                Match m = new Match();
                m.setSynonym(syn);
                m.setAntonym(ant);

                helper.insertMatch(m);


                Intent i = new Intent(enterValues.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}
