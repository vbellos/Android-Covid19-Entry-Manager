package com.example.final_exam.Stats;

import java.text.DecimalFormat;

public class Stats {
    private int recovered,ill,deceased;

    public Stats() {
        recovered = 0;
        ill = 0;
        deceased = 0;
    }

    public int getTotal()
    {
        return recovered + ill + deceased;
    }

    public float getPersentage_Recovered() { return (float)recovered/getTotal() *100;}
    public float getPersentage_Ill()
    {
        return ((float)ill/getTotal()) *100;
    }
    public float getPersentage_Deceased(){return ((float)deceased/getTotal()) *100;}


    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getIll() {
        return ill;
    }

    public void setIll(int ill) {
        this.ill = ill;
    }

    public int getDeceased() {
        return deceased;
    }

    public void setDeceased(int deceased) {
        this.deceased = deceased;
    }


}
