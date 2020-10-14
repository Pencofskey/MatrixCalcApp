package com.example.matrixcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputMatrix extends AppCompatActivity {
    Matrix m;
    TextView rowSize;
    TextView columnSize;
    TextView disp;
    EditText rowCount;
    EditText columnCount;
    TextView inputData;

    String data;
    int row;
    int column;
    int rowMax;
    int columnMax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imput_matrix);
        rowSize = findViewById(R.id.rowSize);
        columnSize = findViewById(R.id.columnSize);
        disp = findViewById(R.id.disp);
        rowCount = findViewById(R.id.rowCount);
        columnCount = findViewById(R.id.columnCount);
        inputData = findViewById(R.id.inputData);

        Intent intent = getIntent();
        m = new Matrix(intent.getIntExtra("row", 0), intent.getIntExtra("column", 0));

        row = 1;
        column = 1;
        rowMax = intent.getIntExtra("row", 0);
        columnMax = intent.getIntExtra("column", 0);

        rowSize.setText("" + rowMax);
        columnSize.setText("" + columnMax);
        rowCount.setText("" + row);
        columnCount.setText("" + column);

        m.setData(row -1, column -1, "_");
        m.print(disp);

        Listener listener = new Listener();

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
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
        Button btnReset = findViewById(R.id.btnReset);
        Button btnKannyakuka = findViewById(R.id.btnKannyakuka);

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
        btnReset.setOnClickListener(listener);
        btnKannyakuka.setOnClickListener(listener);


        //行数の変更を検知
        rowCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                for(int r = 0; r < rowMax; r++){
                    for(int c = 0; c < columnMax; c++){
                        m.setData(r, c, m.getMatrix()[r][c].replace("_", ""));
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().matches("[1-9]")) {
                    if(Integer.parseInt(s.toString()) <= rowMax && Integer.parseInt(s.toString()) > 0){
                        row = Integer.parseInt(s.toString());
                        disp.setText("");
                        inputData.setText(m.getMatrix()[row - 1][column - 1].replace("_", ""));

                        String a = m.getMatrix()[row -1][column -1];
                        a.replace("_", "");
                        a += "_";

                        m.setData(row -1, column -1, a);
                        m.getMatrix()[row -1][column -1].replace("__", "_");
                        m.print(disp);
                    }
                }
            }
        });
        columnCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                for(int r = 0; r < rowMax; r++){        //いちいちすべてのデータを走査すると処理が遅すぎる
                    for(int c = 0; c < columnMax; c++){
                        m.setData(r, c, m.getMatrix()[r][c].replace("_", ""));
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().matches("[1-9]")){
                    if(Integer.parseInt(s.toString()) <= columnMax && Integer.parseInt(s.toString()) > 0) {
                        column = Integer.parseInt(s.toString());
                        disp.setText("");
                        inputData.setText(m.getMatrix()[row - 1][column - 1].replace("_", ""));

                        String a = m.getMatrix()[row -1][column -1];
                        a.replace("_", "");
                        a += "_";

                        m.setData(row -1, column -1, a);
                        m.getMatrix()[row -1][column -1].replace("__", "_");
                        m.print(disp);
                    }
                }
            }
        });


    }


    private class Listener implements View.OnClickListener{
        @Override
        public void onClick(View view){

            row = Integer.parseInt(rowCount.getText().toString());
            column = Integer.parseInt(columnCount.getText().toString());

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
                case R.id.btnReset:
                    //本当に初期化しますか？
                    m.reset();
                    disp.setText("");
                    m.print(disp);
                    break;


                case R.id.btnKannyakuka:
                    boolean noData = false;
                    for(int r = 0; r < Integer.parseInt(rowSize.getText().toString()); r++){
                        for (int c = 0; c < Integer.parseInt(columnSize.getText().toString()); c++){
                            if(m.getMatrix()[r][c].equals("")){
                                noData = true;
                            }
                            //アンダーバー削除
                            m.setData(r, c, m.getMatrix()[r][c].replace("_", ""));
                        }
                    }
                    if(noData){
                        Toast.makeText(InputMatrix.this, "未入力のデータがあります", Toast.LENGTH_SHORT).show();
                        break;
                    }else{
                        Intent intent = new Intent(InputMatrix.this, Result.class);
                        intent.putExtra("Matrix", m);
                        startActivity(intent);
                    }
                    break;


                case R.id.btnConfirm:
                    //修正指定行が行列のサイズ外の場合はサイズ内の値に置き換える
                    if(row > rowMax) {
                        row = rowMax;
                        rowCount.setText("" + row);
                        Toast.makeText(InputMatrix.this, "指定した行は行列の範囲外です", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(column > columnMax){
                        column = columnMax;
                        columnCount.setText("" + column);
                        Toast.makeText(InputMatrix.this, "指定した列は行列の範囲外です", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(row < 1){
                        row = 1;
                        rowCount.setText("" + row);
                        Toast.makeText(InputMatrix.this, "指定した行は行列の範囲外です", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(column < 1){
                        column = 1;
                        columnCount.setText("" + column);
                        Toast.makeText(InputMatrix.this, "指定した列は行列の範囲外です", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    //入力されたデータを読み込み
                    data = inputData.getText().toString();
                    //読み込んだデータに不備がある場合強制break
                    if(m.inputErrorCheck(data)){
                        Toast.makeText(InputMatrix.this, "入力に不備があります", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    // - を -1 に変換 0/2 を 0 に変換
                    data = m.correctData(data);
                    //ディスプレイリセット
                    disp.setText("");
                    //データを代入
                    m.setData(row - 1,  column - 1, data);

                    //確定ボタンを押すたび列が1増え、最後の列に達したら行を1増やし、行を1にする
                    if(row < rowMax){
                        if (column < columnMax){
                            column++;
                        }else{
                            column = 1;
                            row++;
                        }
                    }else if (column < columnMax){
                        column++;
                    }else{
                        Toast.makeText(InputMatrix.this, "入力終了", Toast.LENGTH_SHORT);
                        column = 1;
                        row = 1;
                    }

                    //現在の行と列の表示を更新
                    rowCount.setText(row + "");
                    columnCount.setText((column + ""));


                    //入力個所を明確にするために入力中のデータの末尾に_を追加
//                    String a = m.getMatrix()[row -1][column -1];
//                    a += "_";
//                    m.setData(row -1, column -1, a);
//                    m.print(disp);
                    //次のデータにアンダーバーを追加
//                    inputData.setText(m.getMatrix()[row-1][column-1].replace("_", ""));
                    break;
            }
        }
    }


}