package com.example.trackattendance;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectsFragment extends Fragment implements EditorClickListener {
    private DatabseHelper databseHelper;
    private RecyclerView recycler;
    private RecyclerAdapterEdit adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_subjects, container,false);
        databseHelper = DatabseHelper.getInstance(getActivity());
        setupRecycler(view);
        setSubjects();
        return view;
    }

    private void setupRecycler(View view) {
        recycler = view.findViewById(R.id.recyclerView_edit);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerAdapterEdit(this);
        recycler.setAdapter(adapter);
    }

    public void setSubjects() {
        Cursor res = databseHelper.getAllData();
        while (res.moveToNext()) {
            adapter.addNewCell(res.getString(0), adapter.getItemCount());
        }
    }

    @Override
    public void onResetClick(int position) {
        databseHelper.resetSubjectAttendance(adapter.getName(position));
    }

    @Override
    public void onDeleteClick(int position) {
        databseHelper.deleteSubject(adapter.getName(position));
        adapter.removeCell(position);
    }
}
