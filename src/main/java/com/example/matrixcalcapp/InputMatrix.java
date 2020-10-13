package com.example.matrixcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputMatrix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imput_matrix);

        Intent intent = getIntent();

        TextView rowSize = findViewById(R.id.rowSize);
        TextView columnSize = findViewById(R.id.columnSize);
        TextView disp = findViewById(R.id.disp);
        EditText rowCount = findViewById(R.id.rowCount);
        EditText columnCount = findViewById(R.id.columnCount);

        rowSize.setText("" + intent.getIntExtra("row", 0));
        columnSize.setText("" + intent.getIntExtra("column", 0));
        rowCount.setText("1");
        columnCount.setText("1");

        Matrix m = new Matrix(intent.getIntExtra("row", 0), intent.getIntExtra("column", 0));

        m.print(disp);

        Listener listener = new Listener();


        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2= findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnSlash = findViewById(R.id.btnSlash);
        Button btnDash = findViewById(R.id.btnDash);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btnSlash.setOnClickListener(listener);
        btnDash.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);
        btnClear.setOnClickListener(listener);
        btnConfirm.setOnClickListener(listener);

    }


    private class Listener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            TextView inputData = findViewById(R.id.inputData);
            EditText rowCount = findViewById(R.id.rowCount);
            EditText columnCount = findViewById(R.id.columnCount);
            TextView rowSize = findViewById(R.id.rowSize);
            TextView columnSize = findViewById(R.id.columnSize);

            int id = view.getId();
            switch(id) {
                case R.id.btn0:
                    inputData.append("0");
                    break;
                case R.id.btn1:
                    inputData.append("1");
                    break;
                case R.id.btn2:
                    inputData.append("2");
                    break;
                case R.id.btn3:
                    inputData.append("3");
                    break;
                case R.id.btn4:
                    inputData.append("4");
                    break;
                case R.id.btn5:
                    inputData.append("5");
                    break;
                case R.id.btn6:
                    inputData.append("6");
                    break;
                case R.id.btn7:
                    inputData.append("7");
                    break;
                case R.id.btn8:
                    inputData.append("8");
                    break;
                case R.id.btn9:
                    inputData.append("9");
                    break;
                case R.id.btnSlash:
                    inputData.append("/");
                    break;
                case R.id.btnDash:
                    inputData.append("-");
                    break;
                case R.id.btnBack:
                    String s = inputData.getText().toString();
                    if (s.equals("")){
                        break;
                    }
                    s = s.substring(0, s.length()-1);
                    inputData.setText(s);
                    break;
                case R.id.btnClear:
                    inputData.setText("");
                    break;
                case R.id.btnConfirm:
                    inputData.setText("");
                    int row = Integer.parseInt(rowCount.getText().toString());
                    int column = Integer.parseInt(columnCount.getText().toString());
                    if(row < Integer.parseInt(rowSize.getText().toString())){
                        if (column <  Integer.parseInt(columnSize.getText().toString())){
                            column++;
                        }else{
                            column = 1;
                            column++;
                        }
                    }else{
                        Toast.makeText(InputMatrix.this, "入力終了", Toast.LENGTH_SHORT);
                        column = 1;
                        row = 1;
                    }
                    rowCount.setText(row + "");
                    columnCount.setText((column + ""));
                    break;
            }
        }
    }


}