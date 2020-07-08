package com.example.trackattendance;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectViewEditHolder extends RecyclerView.ViewHolder {
    private TextView tvEditSubjectName;
    private ImageView ivDeleteSubject;
    private ImageView ivReset;
    private EditorClickListener listener;

    public SubjectViewEditHolder(@NonNull View itemView, final EditorClickListener listener) {
        super(itemView);
        this.listener = listener;
        tvEditSubjectName = itemView.findViewById(R.id.tvSubjectNameEdit);
        ivDeleteSubject = itemView.findViewById(R.id.ivDelete);
        ivReset = itemView.findViewById(R.id.ivReset);

        ivDeleteSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onDeleteClick(position);
                    }
                }
            }
        });

        ivReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onResetClick(position);
                    }
                }
            }
        });
    }
    public void setTvEditSubjectName(String name){
        tvEditSubjectName.setText(name);
    }
}
