package com.example.final_exam.Locale;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
