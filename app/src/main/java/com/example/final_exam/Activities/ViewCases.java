package com.example.final_exam.Activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.final_exam.Entry.EntryHelper;
import com.example.final_exam.Locale.BaseActivity;
import com.example.final_exam.Entry.Entry;
import com.example.final_exam.R;
import com.example.final_exam.Widgets.CasesRecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

public class ViewCases extends BaseActivity {

    private EntryHelper entryHelper;
    private SegmentedGroup segmentedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cases);

        entryHelper = new EntryHelper();
        entryHelper.setEntriesChangedEventListener(new EntryHelper.EntriesChangedEventListener() {
           @Override
           public void onEntriesChanged() {
               initRecycler();
           }
        });

        segmentedGroup = findViewById(R.id.view_cases_segmented);
        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                initRecycler();
            }
        });
        segmentedGroup.check(R.id.sc_button_all);
    }

    void initRecycler()
    {
        List<Entry> entries = new ArrayList<Entry>();
        if(segmentedGroup.getCheckedRadioButtonId() == R.id.sc_button_all){entries = entryHelper.getAll();}
        else if (segmentedGroup.getCheckedRadioButtonId() == R.id.sc_button_rec) {entries = entryHelper.getRecovered();}
        else if(segmentedGroup.getCheckedRadioButtonId() == R.id.sc_button_ill){entries = entryHelper.getIll();}
        else if (segmentedGroup.getCheckedRadioButtonId() == R.id.sc_button_dec){entries = entryHelper.getDeceased();}


        RecyclerView recyclerView = findViewById(R.id.cases_recyclerview);
        CasesRecyclerViewAdapter rva = new CasesRecyclerViewAdapter(entries,ViewCases.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rva);
    }
}