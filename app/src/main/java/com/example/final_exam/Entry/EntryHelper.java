package com.example.final_exam.Entry;

import androidx.annotation.NonNull;

import com.example.final_exam.Stats.StatsHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EntryHelper {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference mref = db.getReference(Entry.class.getSimpleName());
    private EntriesChangedEventListener mListener;
    public interface EntriesChangedEventListener
    {
        void onEntriesChanged();
    }
    public void setEntriesChangedEventListener(EntriesChangedEventListener eventListener)
    {
        this.mListener = eventListener;
    }
    public void callListener()
    {
        if(mListener!=null)
        {
            mListener.onEntriesChanged();
        }
    }

    private List<Entry> entryList;
    private List<Entry> recovered, ill,deceased;

    public EntryHelper(){
        entryList = new ArrayList<Entry>();
        recovered  = new ArrayList<Entry>();
        ill  = new ArrayList<Entry>();
        deceased  = new ArrayList<Entry>();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clear();
                for (DataSnapshot sn : snapshot.getChildren()) {
                    Entry entry = sn.getValue(Entry.class);
                    add(entry);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void add(Entry e)
    {
        entryList.add(e);
        if(e.getCurrent_state() == 0){recovered.add(e);}
        else if(e.getCurrent_state() == 1){ill.add(e);}
        else if(e.getCurrent_state()==2){deceased.add(e);}
        callListener();
    }

    public void loadList(List<Entry> entries)
    {
        for (Entry e:entries) {
            this.add(e);
        }
        callListener();
    }

    public void clear()
    {
        entryList.clear();
        recovered.clear();
        ill.clear();
        deceased.clear();
        callListener();
    }


    public List<Entry> getAll()
    {
        return entryList;
    }
    public List<Entry> getRecovered()
    {
        return recovered;
    }
    public List<Entry> getIll()
    {
        return ill;
    }
    public List<Entry> getDeceased()
    {
        return deceased;
    }

}
