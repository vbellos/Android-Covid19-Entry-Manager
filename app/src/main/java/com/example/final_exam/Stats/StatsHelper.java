package com.example.final_exam.Stats;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.final_exam.Entry.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StatsHelper {
    private Stats stats = new Stats();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference mref = db.getReference(Entry.class.getSimpleName());
    private StatsChangedEventListener mListener;
    public interface StatsChangedEventListener
    {
        void onStatsChanged();
    }
    public void setStatsChangedEventListener(StatsChangedEventListener eventListener)
    {
        this.mListener = eventListener;
    }
    public void callListener()
    {
        if(mListener!=null)
        {
            mListener.onStatsChanged();
        }
    }


    public StatsHelper()
    {
        Query DBgetRec = mref.orderByChild("current_state").equalTo(0);
        Query DBgetIll = mref.orderByChild("current_state").equalTo(1);
        Query DBgetDead = mref.orderByChild("current_state").equalTo(2);
        DBgetRec.addValueEventListener(RecChanged);
        DBgetIll.addValueEventListener(IllChanged);
        DBgetDead.addValueEventListener(DeadChanged);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    ValueEventListener RecChanged = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            stats.setRecovered((int)snapshot.getChildrenCount());
            callListener();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    ValueEventListener IllChanged = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            stats.setIll((int)snapshot.getChildrenCount());
            callListener();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    ValueEventListener DeadChanged = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            stats.setDeceased((int)snapshot.getChildrenCount());
            callListener();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}
