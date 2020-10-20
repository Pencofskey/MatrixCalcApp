package com.kohei.gyouretukannyakuka;

public class Fraction {

    //String型の分数bの分母を取得します
    public static long getBunbo(String b) {
        long bunbo = 0;
        String[] bunboS = b.split("/");
        if(bunboS.length == 2) {	//スラッシュある時
            bunbo = Long.parseLong(bunboS[1]);
        }else if (bunboS.length == 1) {		//スラッシュないとき
            bunbo = 1;
        }else if (bunboS.equals("0")) {
            throw new IllegalArgumentException("分母に0が代入");
        }
        return bunbo;
    }

    //String型の分数bの分子を取得します
    //整数を取得する場合もこのメソッドを使用してください
    public static long getBunshi(String b) {
        long bunshi = 0;
        String[] bunshiS = b.split("/");
        if(bunshiS.length == 2) {		//スラッシュある時
            bunshi = Long.parseLong(bunshiS[0]);
        }else if (bunshiS.length == 1) {		//スラッシュないとき
            bunshi = Long.parseLong(b);
        }
        return bunshi;
    }

    //2つの整数を読み込み、String型の分数を返します
    public static String buildFraction(long bunshi, long bunbo) {
        StringBuilder bunsu = new StringBuilder();
        if (bunbo == 1 || bunbo == -1) { //分母が1の時に分子だけを返す
            bunsu.append(bunshi);
        } else if (bunbo == 0) { //分母ゼロ例外処理
            throw new IllegalArgumentException("分母に0が代入");
        } else if (bunshi == 0) { //分子0の場合整数の0を代入
            bunsu.append("0");
        } else if (bunbo < 0){	//分母が負の場合分子分母両方に-1をかける
            bunshi *= -1;
            bunbo *= -1;
            bunsu.append(bunshi);
            bunsu.append("/");
            bunsu.append(bunbo);
        } else {
            bunsu.append(bunshi);
            bunsu.append("/");
            bunsu.append(bunbo);
        }
        return bunsu.toString();
    }

    //約分されたbを返します
    public static String yakubun(String b) {
        long bunshi = getBunshi(b);
        if (bunshi < 0) {
            bunshi *= -1;
        }
        if (bunshi == 0 || bunshi == 1) {
            return b;
        }

        //最大公約数 ユークリッド互除法
        long bunbo = getBunbo(b);
        long saidaikouyakusuu = 1;
        long d = Math.max(bunshi, getBunbo(b));
        long s = Math.min(bunshi, getBunbo(b));
        long r = d % s;
        for (int i = 1; r != 0;) {
            d = s;
            s = r;
            r = d % r;
        }
        saidaikouyakusuu = s;
        return buildFraction((getBunshi(b) / saidaikouyakusuu), (getBunbo(b) / saidaikouyakusuu));
    }

    //b2に通分されたb1を返します どちらかに0が代入された場合はb1をそのまま返します
    public static String tsubun(String b1, String b2) {
        StringBuilder bunsu = new StringBuilder();
        if(getBunshi(b1) == 0 || getBunshi(b2) == 0) {
            bunsu.append(b1);
        }else {
            //最小公倍数の初期化 最小公倍数=a*b/最大公約数
            long saishoukoubaisu = 1;
            long d = Math.max(getBunbo(b1), getBunbo(b2));
            long s = Math.min(getBunbo(b1), getBunbo(b2));
            long r = d % s;
            for (long i = 1; r != 0;) {
                d = s;
                s = r;
                r = d % r;
            }
            saishoukoubaisu = getBunbo(b1) * getBunbo(b2) / s;
            bunsu.append(buildFraction((getBunshi(b1) * saishoukoubaisu) / getBunbo(b1), (getBunbo(b1) * saishoukoubaisu / getBunbo(b1))));
        }
        return bunsu.toString();
    }

    //b1 * b2 を返します
    public static String kakeru(String b1, String b2) {
        return yakubun(buildFraction(getBunshi(b1) * getBunshi(b2), getBunbo(b1) * getBunbo(b2)));
    }

    //b1 * (1/b2) を返します
    public static String waru(String b1, String b2) {
        return yakubun(kakeru(b1, gyakusu(b2)));
    }

    // 1/b を返します
    public static String gyakusu(String b) {
        long pn = 1;
        if (getBunshi(b) < 0) {
            pn = -1;
        }
        return yakubun(buildFraction(pn * getBunbo(b), pn * getBunshi(b)));
    }

    //b1+b2 を返します
    public static String tasu(String b1, String b2) {
        StringBuilder bunsu = new StringBuilder();
        if(b1.equals("0") || b2.equals("0")) {	//0から引く場合の処理
            bunsu.append(yakubun(buildFraction(getBunshi(b1) + getBunshi(b2), getBunbo(b1) * getBunbo(b2))));
        }else {
            bunsu.append(yakubun(
                    buildFraction(
                            getBunshi(tsubun(b1, b2)) + getBunshi(tsubun(b2, b1)),
                            getBunbo(tsubun(b1, b2))
                    )
            ));
        }
        return bunsu.toString();
    }

    //b1-b2 を返します
    public static String hiku(String b1, String b2) {
        StringBuilder bunsu = new StringBuilder();
        if (b1.equals("0") || b2.equals("0")) {	//0から引く場合の処理
            bunsu.append(yakubun(buildFraction(getBunshi(b1) - getBunshi(b2), getBunbo(b1) * getBunbo(b2))));
        }else {
            bunsu.append(yakubun(buildFraction(getBunshi(tsubun(b1, b2)) - getBunshi(tsubun(b2, b1)), getBunbo(tsubun(b1, b2)))));
        }
        return bunsu.toString();
    }
}
