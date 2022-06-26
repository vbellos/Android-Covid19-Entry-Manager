package com.example.final_exam.Entry;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EntryManager {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public EntryManager(){
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference(Entry.class.getSimpleName());
    }
    public void CreateEntry(String uuid,String name,String phone,String amka,int current_state,float timestamp,float locationX,float locationY){

        Entry e=new Entry(uuid,name,phone,amka,current_state,timestamp,locationX,locationY);
        myRef.child(uuid).setValue(e);
    }
    public void CreateEntry(Entry e){
        myRef.child(e.getUuid()).setValue(e);
    }
    public void RemoveEntry(String uuid){
       myRef.child(uuid).removeValue();
    }
    public void UpdateEntry(Entry e)
    {
        myRef.child(e.getUuid()).updateChildren(e.toMap());
    }
}
