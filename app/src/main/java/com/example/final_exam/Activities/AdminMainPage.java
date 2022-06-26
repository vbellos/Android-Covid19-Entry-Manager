package com.example.final_exam.Activities;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.final_exam.Locale.BaseActivity;
import com.example.final_exam.R;
import com.example.final_exam.Stats.StatsHelper;
import com.google.firebase.auth.FirebaseAuth;


import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;


public class AdminMainPage extends BaseActivity {

    private StatsHelper statsHelper = new StatsHelper();
    private PieChart pieChart;
    private TextView rectxt, deadtxt, illtxt ,totaltxt;
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

         pieChart = findViewById(R.id.piechart);
         rectxt = findViewById(R.id.stats_rec);
         deadtxt = findViewById(R.id.stats_dec);
         illtxt = findViewById(R.id.stats_ill);
         totaltxt = findViewById(R.id.stats_total);

        statsHelper.setStatsChangedEventListener(new StatsHelper.StatsChangedEventListener() {
            @Override
            public void onStatsChanged() {
                initStats();
            }
        });
    }


    public void addCase(View view)
    {
        Intent i = new Intent(this,CreateEntry.class);
        startActivity(i);
    }

    public void showCases(View view)
    {
        Intent i = new Intent(this,ViewCases.class);
        startActivity(i);
    }
    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this,StartActivity.class);
        finish();
        startActivity(i);
    }

    public void initStats()
    {

        pieChart.clearChart();
        pieChart.addPieSlice(
                new PieModel(
                        getResources().getString(R.string.recovered),
                        statsHelper.getStats().getRecovered(),
                        Color.parseColor("#2ba14b")));
        pieChart.addPieSlice(
                new PieModel(
                        getResources().getString(R.string.ill),
                        statsHelper.getStats().getIll(),
                        Color.parseColor("#2b4fa1")));
        pieChart.addPieSlice(
                new PieModel(
                        getResources().getString(R.string.deceased),
                        statsHelper.getStats().getDeceased(),
                        Color.parseColor("#83848f")));
        pieChart.startAnimation();

        illtxt.setText(df.format(statsHelper.getStats().getPersentage_Ill()) +"% ("+statsHelper.getStats().getIll()+")");
        deadtxt.setText(df.format(statsHelper.getStats().getPersentage_Deceased()) +"% ("+statsHelper.getStats().getDeceased()+")");
        rectxt.setText(df.format(statsHelper.getStats().getPersentage_Recovered()) +"% ("+statsHelper.getStats().getRecovered()+")");
        totaltxt.setText(String.valueOf(statsHelper.getStats().getTotal()));
    }

}