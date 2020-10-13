package com.example.matrixcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.disp);
        Matrix m = new Matrix(4, 5, 10);	//デバッグ用自動行列生成
        m.print(tv);
//        tv.append("\n\n");
//        m.hakidashi(tv);
//        m.print(tv);

        Button btnConfirm = findViewById(R.id.btnSubmitSize);
        Listener listener = new Listener();
        btnConfirm.setOnClickListener(listener);

    }

    private class Listener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            EditText row = findViewById(R.id.rowSize);
            EditText column = findViewById(R.id.columnSize);
            //EditTextの文字を読み取り1~15
            if (row.getText().toString().matches("[1-9]") && column.getText().toString().matches("[1-9]")){
                //インテントをnewする
                Intent intent = new Intent(MainActivity.this, InputMatrix.class);
                //新しいアクテビティに行列サイズ情報を送る
                intent.putExtra("row", Integer.parseInt(row.getText().toString()));
                intent.putExtra("column", Integer.parseInt(column.getText().toString()));
                //アクテビティ生成
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, R.string.err, Toast.LENGTH_LONG).show();
            }
        }
    }

}