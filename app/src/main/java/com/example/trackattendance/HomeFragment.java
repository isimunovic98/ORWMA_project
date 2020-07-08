package com.example.trackattendance;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements AddSubjectDialog.OnAddSelected, ClickListener, EditorClickListener{

    public void passStringToHomeFragment(String input) {
        String subjectName = input;
        adapter.addNewCell(subjectName, 0, 0, adapter.getItemCount());
        adapterEdit.addNewCell(subjectName, adapterEdit.getItemCount());
    }

    private DatabseHelper databseHelper;
    private RecyclerView recycler;
    private RecyclerAdapterHome adapter;
    private RecyclerAdapterEdit adapterEdit;

    private Button btnAddSubject;
    private TextView tvOverallAttendance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        databseHelper = DatabseHelper.getInstance(getActivity());
        setupAddSubjectButton(view);
        setupRecycler(view);
        setSubjects();
        return view;
    }

    private void setupAddSubjectButton(View view) {
        btnAddSubject = view.findViewById(R.id.btnAddSubject);
        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSubjectDialog dialog = new AddSubjectDialog();
                dialog.setTargetFragment(HomeFragment.this, 1);
                dialog.show(getFragmentManager(), "AddSubjectDialog");
            }
        });
    }

    private void setupRecycler(View view) {
        recycler = view.findViewById(R.id.recyclerView_home);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerAdapterHome(this);
        adapterEdit = new RecyclerAdapterEdit(this);
        recycler.setAdapter(adapter);
    }

    public void setSubjects() {
        Cursor res = databseHelper.getAllData();
        while (res.moveToNext()) {
            String attended = res.getString(1);
            String total = res.getString(2);
            adapter.addNewCell(res.getString(0), Integer.parseInt(attended), Integer.parseInt(total), adapter.getItemCount());
        }
    }

    @Override
    public void onPositiveClick(int position) {
        databseHelper.incrementAttended(adapter.getName(position));
        adapter.incrementPositive(position);
    }

    @Override
    public void onNegativeClick(int position) {
        databseHelper.incrementTotal(adapter.getName(position));
        adapter.incrementNegative(position);
    }

    @Override
    public void onResetClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }
}



