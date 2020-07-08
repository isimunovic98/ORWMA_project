package com.example.trackattendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterEdit extends RecyclerView.Adapter<SubjectViewEditHolder> {
    private List<String> subjectNames = new ArrayList<>();
    private EditorClickListener listener;

    public RecyclerAdapterEdit(EditorClickListener listener){this.listener = listener;}

    @NonNull
    @Override
    public SubjectViewEditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellViewEdit = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_subject_edit,parent,false);
        return new SubjectViewEditHolder(cellViewEdit, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewEditHolder holder, int position) {
        holder.setTvEditSubjectName(subjectNames.get(position));
    }

    @Override
    public int getItemCount() {
        return subjectNames.size();
    }

    public void addNewCell(String subjectName, int position){
        if(subjectNames.size() >= position) {
            subjectNames.add(subjectName);
            notifyItemInserted(position);
        }
    }

    public void removeCell(int position){
        if(subjectNames.size() > position) {
            subjectNames.remove(position);
            notifyItemRemoved(position);
        }
    }

    public String getName(int position){
        return subjectNames.get(position);
    }
}
