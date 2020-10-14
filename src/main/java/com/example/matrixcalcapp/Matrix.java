package com.example.matrixcalcapp;

import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Matrix implements Serializable {
    private String[][] matrix; //内部的には0行0列から始まっています 普通の行列式のように1行1列から始まってると考えて引数を渡すと配列エラーになります
    private int row; //行数
    private int column; //列数

    public int getRow() {
        return this.row;
    }
    public void setRow(int row) { this.row = row; }
    public int getColumn() {
        return this.column;
    }
    public void setColumn(int column) { this.column = column; }
    public String[][] getMatrix() { return this.matrix; }
    public void setData(int row, int column, String data) {
        this.matrix[row][column] = data;
    }

    Matrix(int r, int c){
        this.row = r;
        this.column = c;
        this.reset();
    }

    Matrix() { //行列初期化
        System.out.println("行列の型を指定してください");
        boolean err;
        do {		//整数以外をはじく
            System.out.print("行数：");
            String rowSize = new java.util.Scanner(System.in).nextLine();
            if(rowSize.matches("[1-9]")) {
                err = false;
                this.row = Integer.parseInt(rowSize);
            }else {
                err = true;
                System.out.println("行数は1桁の自然数で指定");
            }
        }while(err);
        do {		//整数以外をはじく
            System.out.print("列数：");
            String columnSize = new java.util.Scanner(System.in).nextLine();
            if(columnSize.matches("[1-9]")) {
                err = false;
                this.column = Integer.parseInt(columnSize);
            }else {
                err = true;
            }
            if(err) {
                System.out.println("行数は1桁の自然数で指定");
            }
        }while(err);

        //行列初期化
        this.reset();

        System.out.println(this.row + "x" + this.column + "型行列");
    }

    Matrix(int row, int column, int randomRange) { //デバッグ用自動行列生成
        this.row = row;
        this.column = column;
        this.matrix = new String[row][column];
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.column; c++) {
                int bunshi = new java.util.Random().nextInt(randomRange) - randomRange / 2;
                int bunbo = new java.util.Random().nextInt(randomRange) - randomRange / 2;
                if (bunbo == 0) {
                    bunbo = 1;
                }
                this.matrix[r][c] = Fraction.yakubun(Fraction.buildFraction(bunshi, bunbo));
            }
        }
    }

    //行列リセット
    public void reset() {
        this.matrix = new String[this.row][this.column];
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.column; c++) {
                this.matrix[r][c] = "";
            }
        }
    }

    //列内最大文字数走査
    public int detectColumnMax(int c) {	//c列の最大文字数を返します
        int max = 0;
        for(int i = 0; i < this.row; i ++) {
            if(max < this.matrix[i][c].length()) {
                max = this.matrix[i][c].length();
            }
        }
        return max;
    }


    //行列出力
    public void print(TextView tv) {
        //行列内の最長文字数を走査
//		int charCount = 0;
//		for (int r = 0; r < this.row; r++) {
//			for (int c = 0; c < this.column; c++) {
//				if (charCount < this.matrix[r][c].length()) {
//					charCount = this.matrix[r][c].length(); //最大を上書き
//				}
//			}
//		}

        //列数描画
        tv.append("      ");
        for (int column = 0; column < this.column; column++) {
            String columnS = "" + (column + 1);
            tv.append(columnS);
            for (int i = 0; i < detectColumnMax(column) + 4 - columnS.length(); i++) {
                tv.append(" ");
            }
        }
        tv.append("\n");

        //1番上の横棒描画
        tv.append("    +");
        for (int column = 0; column < this.getColumn(); column++) {
            for (int i = 0; i < detectColumnMax(column) + 3; i++) { //文字数に合わせてスペースを挿入し、列をそろえる
                tv.append("-");
            }
            tv.append("+");
        }
        tv.append("\n");

        //行列本体描画
        for (int row = 0; row < this.getRow(); row++) {

            //列数描画
            String rowS = "" + (row + 1);
            for (int i = 0; i < 3 - rowS.length(); i++) {
                tv.append(" ");
            }
            tv.append(row + 1 + " ");

            //要素と縦棒描画
            tv.append("| "); //1番左上の縦棒描画
            for (int column = 0; column < this.getColumn(); column++) {
                tv.append(this.getMatrix()[row][column]);
                for (int i = 0; i < detectColumnMax(column) + 2 - this.getMatrix()[row][column].length(); i++) { //(最大文字数+2-現在の要素の文字数)分スペースを挿入し、列をそろえる
                    tv.append(" ");
                }
                tv.append("| "); //左端の縦棒描画
            }

            tv.append("\n");

            //横棒描画
            tv.append("    +"); //1番左上の交点描画
            for (int column = 0; column < this.getColumn(); column++) {
                for (int i = 0; i < detectColumnMax(column) + 3; i++) { //文字数に合わせてスペースを挿入し、交点を縦棒の位置にそろえる
                    tv.append("-");
                }
                tv.append("+"); //交点描画
            }

            tv.append("\n");
        }
    }


    public String correctData(String data){
        if (data.equals("-")) { //-が入力されたとき-1に変換
            data = "-1";
        }else if(data.matches("-?0*/.*") || data.matches("")) {	//分子0の分数が入力される、又は何も入力されなかったとき0を入力
            data = "0";
        }else if(data.matches("-?0*[1-9]*/0*[1-9]*") || data.matches("-?0*[1-9]*")) {		// 004/05 -> 4/5 || -0005 -> -5
            data = Fraction.buildFraction(Fraction.getBunshi(data), Fraction.getBunbo(data));
        }

        return data;
    }

    public boolean inputErrorCheck(String data) { //row行column列に数値を代入する
        boolean err = false;

        if (data.equals("-")) { //-が入力されたとき-1に変換
            data = "-1";
        }else if(data.matches("-?0*/.*") || data.matches("")) {	//分子0の分数が入力される、又は何も入力されなかったとき0を入力
            data = "0";
        }else if(data.matches(".*/0*")) {	//分母0の時エラー
            err = true;
        }else if(data.matches("-?0*[1-9]*/0*[1-9]*") || data.matches("-?0*[1-9]*")) {		// 004/05 -> 4/5 || -0005 -> -5
            data = Fraction.buildFraction(Fraction.getBunshi(data), Fraction.getBunbo(data));
        }

        if (!data.matches("-?[0-9]*/?[0-9]*")){	//ハイフンが0か1回, スラッシュが0か1回この順番で現れるとき以外エラー
            err = true;
        }
        return err;
    }

    //row行をratio倍する
    public void rowBasicTransformation1_k(int row, String ratio) {
        outSideErrorCheck(row);
        for (int c = 0; c < this.column; c++) {
            this.matrix[row][c] = Fraction.kakeru(this.matrix[row][c], ratio);
        }
    }

    //row行を1/ratio倍する
    public void rowBasicTransformation1_w(int row, String ratio) {
        outSideErrorCheck(row);
        for (int c = 0; c < this.column; c++) {
            this.matrix[row][c] = Fraction.waru(this.matrix[row][c], ratio);
        }
    }

    //row1行をratio倍し、row2に加える
    public void rowBasicTransformation2_a(int row1, String ratio, int row2) {
        outSideErrorCheck(row1, row2);
        for (int c = 0; c < this.column; c++) {
            this.matrix[row2][c] = Fraction.tasu(this.matrix[row2][c], Fraction.kakeru(this.matrix[row1][c], ratio));
        }
    }

    //row1行をratio倍し、row2から引く
    public void rowBasicTransformation2_d(int row1, String ratio, int row2) {
        outSideErrorCheck(row1, row2);
        for (int c = 0; c < this.column; c++) {
            this.matrix[row2][c] = Fraction.hiku(this.matrix[row2][c], Fraction.kakeru(this.matrix[row1][c], ratio));
        }
    }

    //row1とrow2を入れ替える
    public void rowBasicTransformation3(int row1, int row2) {
        outSideErrorCheck(row1, row2);
        String[] temp = new String[this.column];
        for (int c = 0; c < this.column; c++) {
            temp[c] = this.matrix[row1][c]; //row1行をtempにコピー
        }
        for (int c = 0; c < this.column; c++) {
            this.matrix[row1][c] = this.matrix[row2][c]; //row2行をrow1行に上書きコピー
        }
        for (int c = 0; c < this.column; c++) {
            this.matrix[row2][c] = temp[c]; //tempをrow1行にコピー
        }
    }

    //行列簡約化-掃き出し法
    public void hakidashi(TextView tv) {

        //左下に0の坂を作る
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.column; c++) {
                boolean allZero = true;
                boolean validColumnOrder = true;

                //現在のc列がすべて0かどうかを確かめる
                for (int i = 0; i < this.row; i++) {
                    if (!this.matrix[i][c].equals("0")) {
                        allZero = false;
                    }
                }

                //現在のc列におけるr行以下において、0が非0要素よりも下の行になっているかどうか確かめる
                boolean isZero = false;
                for (int i = r; i < this.row; i++) {
                    if (isZero == true) {
                        if (this.matrix[i][c].equals("0")) { //文字列判定には""が必要なことを忘れていた
                            isZero = true;
                        } else {
                            isZero = false;
                            validColumnOrder = false; //isZeroがtrueからfalseに移り変わると、その列は正しく並んでいない ex)上から 4, 0, 2, 1
                            i = this.row; //判定終了
                        }
                    } else {
                        //						System.out.println("isZeroはfalseです");
                        if (this.matrix[i][c].equals("0")) {
                            isZero = true;
                        } else {
                            isZero = false;
                        }
                    }
                }

                //c列の一番上の行が0の時はその下の0でない行と入れ替える
                if (allZero == false && validColumnOrder == false && this.matrix[r][c].equals("0")) { //c列の下の行がすべて0でない場合 かつ 列が正しく並んでいない場合(0より下の行に非0がある)
                    for (int i = r + 1; i < this.row; i++) {
                        if (this.matrix[i][c].equals("0") == false) {

                            //デバッグ用
                            this.print(tv);
                            tv.append("\n0が下に来るように行を入れ替える | " + (r + 1) + "行 <-> " + (i + 1) + " 行\n\n");
                            //							System.out.println("現在は " + r + "行 " + c + "列 です\n");

                            rowBasicTransformation3(r, i); //i行c列が0でない数の時にi行とr行を入れ替える
                            i = this.row; //入れ替え終了
                        }
                    }
                }

                //r行目の先頭の値が1になるようにr行目全体をr行目の先頭の値で割り、r行目より下の行の先頭が0になるように引く
                if (!this.matrix[r][c].equals("0")) { //現在の要素が0でない
                    if (!this.matrix[r][c].equals("1")) {

                        //デバッグ用
                        this.print(tv);
                        tv.append("\n行の主成分を1にする | " + (r + 1) + "行 × " + Fraction.gyakusu(this.matrix[r][c]) + "\n\n");
                        //						System.out.println("現在は " + r + "行 " + c + "列 です\n");

                        rowBasicTransformation1_w(r, this.matrix[r][c]);
                    }
                    for (int i = 1; r + i < this.row; i++) { //最後の行でない場合のみ実行
                        if (!this.matrix[r + i][c].equals("0")) {

                            //デバッグ用
                            this.print(tv);
                            tv.append("\n主成分より下の行の成分を0にする | " + (r + 1 + i) + "行 - " + (r + 1) + "行 × " + this.matrix[r + i][c] + "\n\n");
                            //							System.out.println("現在は " + r + "行 " + c + "列 です\n");

                            rowBasicTransformation2_d(r, this.matrix[r + i][c], r + i);
                        }
                    }
                    c = this.column; //次の行に進む
                }
            }
        }

        //主成分ある列の上の成分をすべて0にする
        for (int r = this.row - 1; r > 0; r--) {
            for (int c = 0; c < this.column; c++) {
                if (!this.matrix[r][c].equals("0")) {
                    for (int i = 1; r - i >= 0; i++) {
                        if(!this.matrix[r - i][c].equals("0")) {

                            //デバッグ用
                            this.print(tv);
                            tv.append("\n主成分より上にある成分を0にする | " + ((r + 1) - i) + "行 - " + (r + 1) + "行 × " + this.matrix[r - i][c] + "\n\n");
                            //						System.out.println("現在は " + r + "行 " + c + "列 です\n");

                            rowBasicTransformation2_d(r, this.matrix[r - i][c], r - i);
                        }
                    }
                    c = this.column; //この行の捜査終了
                }
            }
        }
    }

    //行列外の行を指定していないかどうかチェックします
    public void outSideErrorCheck(int row1, int row2) {
        if (row1 < 0 || row1 >= this.row || row2 < 0 || row2 >= this.row) {
            throw new IllegalArgumentException("行列のサイズの外の行または列を指定してます"); //内部的には0行0列から始まるので1行1列から始まるとして引数を渡すとこのコンパイルエラーが出ます
        }
    }

    public void outSideErrorCheck(int row) {
        this.outSideErrorCheck(row, 0);
    }

    //ユーザが入力した行と列が行列のサイズ外を指定していないかどうかチェックします
    public boolean outSideEroorCheckInterface(int row, int column) {
        boolean err;
        if (row <= 0 || row > this.row || column <= 0 || column > this.column) { //ユーザの入力は1行1列から始まります
            err = true;
        } else {
            err = false;
        }
        return err; //エラーがあるときtrue, ないときfalseを返します
    }
}
