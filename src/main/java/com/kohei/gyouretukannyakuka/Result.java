package com.kohei.gyouretukannyakuka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Result extends AppCompatActivity {

    TextView progress;
    TextView result;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //広告表示
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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