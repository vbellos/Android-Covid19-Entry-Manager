package com.example.final_exam.Widgets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_exam.Activities.EditEntryActivity;
import com.example.final_exam.Activities.ViewCases;
import com.example.final_exam.Entry.Entry;
import com.example.final_exam.Entry.EntryManager;
import com.example.final_exam.R;

import java.util.List;

public class CasesRecyclerViewAdapter extends RecyclerView.Adapter<CasesRecyclerViewAdapter.ViewHolder> {

    private List<Entry> cases;
    private EntryManager entryManager = new EntryManager();
    private Entry current_entry;
    private Context context;

    public CasesRecyclerViewAdapter(List<Entry> entries,Context context)
    {
        this.context = context;
        cases = entries;
    }

    public Entry getCurrent_entry() {
        return current_entry;
    }

    public void setCurrent_entry(Entry current_entry) {
        this.current_entry = current_entry;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullname, amka,phone,location,cur_state;
        private Button remove,edit;


        public ViewHolder(final View view){
            super(view);
            fullname =(TextView) view.findViewById(R.id.caseview_fullname);
            amka =(TextView) view.findViewById(R.id.caseview_amka);
            phone =(TextView) view.findViewById(R.id.caseview_phone);
            location =(TextView) view.findViewById(R.id.caseview_location);
            cur_state =(TextView) view.findViewById(R.id.caseview_currentstate);

            edit = (Button) view.findViewById(R.id.caseview_editbutton) ;
            remove = (Button) view.findViewById(R.id.caseview_deletebutton);


        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.case_view_control,parent,false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Entry entry = cases.get(position);

        holder.cur_state.setText(entry.getCurrent_state(context));
        holder.fullname.setText(entry.getName());
        holder.phone.setText(entry.getPhone());
        holder.amka.setText(entry.getAmka());
        holder.location.setText(String.valueOf(entry.getLocationX()) + " " +String.valueOf(entry.getLocationY()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditEntryActivity.class);
                i.putExtra("uuid",entry.getUuid());
                i.putExtra("name",entry.getName());
                i.putExtra("amka",entry.getAmka());
                i.putExtra("phone",entry.getPhone());
                i.putExtra("state",entry.getCurrent_state());
                context.startActivity(i);
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entryManager.RemoveEntry(entry.getUuid());
            }
        });

    }



    @Override
    public int getItemCount() {
        return cases.size();
    }

}
