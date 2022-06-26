package com.example.final_exam.Entry;

import android.content.Context;

import com.example.final_exam.R;

import java.util.HashMap;
import java.util.Map;

public class Entry {
    private String name;
    private String amka;
    private String phone;
    private int current_state;
    private float timestamp;
    private float locationX;
    private float locationY;



    private String uuid;

    public Entry(){
        //empty constructor for firebase
    }
    public Entry(String uuid,String name, String amka, String phone,int current_state, float timestamp, float locationX, float locationY) {
        this.uuid = uuid;
        this.name = name;
        this.amka = amka;
        this.phone = phone;
        this.current_state=current_state;
        this.timestamp = timestamp;
        this.locationX = locationX;
        this.locationY = locationY;

    }


    public Map<String,Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put("uuid",uuid);
        result.put("name",name);
        result.put("amka",amka);
        result.put("phone",phone);
        result.put("current_state",current_state);
        result.put("timestamp",timestamp);
        result.put("locationX",locationX);
        result.put("locationY",locationY);
        return result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCurrent_state() {

        return current_state;
    }
    public String getCurrent_state(Context context) {

        String cur_state;
        if(current_state==0)
        {
            cur_state = context.getResources().getString(R.string.recovered);
        }
        else if(current_state == 1)
        {
            cur_state = context.getResources().getString(R.string.ill);
        }
        else
        {
            cur_state = context.getResources().getString(R.string.deceased);
        }
        return cur_state;
    }

    public void setCurrent_state(int current_state) {
        this.current_state = current_state;
    }
    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }


}
