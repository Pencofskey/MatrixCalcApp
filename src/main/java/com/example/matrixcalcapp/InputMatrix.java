package com.example.matrixcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InputMatrix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imput_matrix);


        Intent intent = getIntent();
        int rowSize = intent.getIntExtra("row", 0);
        int columnSize = intent.getIntExtra("column", 0);

        TextView disp = findViewById(R.id.disp);
        disp.append("wｗ" + rowSize);
        disp.append("\n" + columnSize);

        Matrix m = new Matrix(rowSize, columnSize);
        m.print(disp);
    }
}