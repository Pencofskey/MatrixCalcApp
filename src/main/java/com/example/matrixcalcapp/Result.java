package com.example.matrixcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {

    TextView progress;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Matrix m = (Matrix)intent.getSerializableExtra("Matrix");

//        for(int r = 0; r < m.getRow(); r++){
//            for (int c = 0; c < m.getColumn(); c++){
//                progress.append(m.getMatrix()[r][c] + "\n");
//            }
//        }

        progress = findViewById(R.id.progress);

        m.hakidashi(progress);
        m.print(progress);


        result = findViewById(R.id.result);
        m.print(result);
    }
}