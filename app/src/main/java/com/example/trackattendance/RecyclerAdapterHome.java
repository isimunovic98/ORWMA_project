package com.example.trackattendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterHome extends RecyclerView.Adapter<SubjectViewHomeHolder> {

    private List<Subject> subjects = new ArrayList<>();
    private ClickListener listener;

    public RecyclerAdapterHome(ClickListener listener){this.listener = listener;}

    @NonNull
    @Override
    public SubjectViewHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_subject,parent,false);
        return new SubjectViewHomeHolder(cellView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHomeHolder holder, int position) {
        int percentage;
        holder.setTvSubjectName(subjects.get(position).getName());
        holder.setTvAttendanceTracker(String.valueOf(subjects.get(position).getAttended()), (String.valueOf(subjects.get(position).getTotal())));
        if(subjects.get(position).getTotal() == Integer.parseInt("0")){
            percentage = 0;
            holder.setAttendancePercentage(0);
        }
        else {
            percentage = (subjects.get(position).getAttended()*100) / subjects.get(position).getTotal();
            holder.setAttendancePercentage(percentage);
        }
        holder.setTvProgressText(percentage);

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void addNewCell(String subjectName, Integer attended, Integer total, int position){
        if(subjects.size() >= position) {
            subjects.add(new Subject(subjectName, attended, total));
            notifyItemInserted(position);
        }
    }

    public void incrementPositive(int position){
        subjects.get(position).incrementAttended();
        subjects.get(position).incrementTotal();
        notifyItemChanged(position);
    }
    public void incrementNegative(int position){
        subjects.get(position).incrementTotal();
        notifyItemChanged(position);
    }


    public String getName(int position){
        return subjects.get(position).getName();
    }

    public void removeCell(int position){
        if(subjects.size() > position) {
            subjects.remove(position);
            notifyItemRemoved(position);
        }
    }
}