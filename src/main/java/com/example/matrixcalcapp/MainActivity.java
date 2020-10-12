package com.example.matrixcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.disp);

        Matrix m = new Matrix(4, 5, 40);	//デバッグ用自動行列生成
        m.print(tv);
        tv.append("\n\n");
        m.hakidashi(tv);
        m.print(tv);

    }
}