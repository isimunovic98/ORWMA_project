package com.example.trackattendance;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SubjectViewHomeHolder extends RecyclerView.ViewHolder{


    private TextView tvProgressText;
    private TextView tvSubjectName;
    private TextView tvAttendanceTracker;
    private ProgressBar progressBar;
    private ImageView positiveClick;
    private ImageView negativeClick;
    private ClickListener listener;

    public SubjectViewHomeHolder(@NonNull View itemView, final ClickListener listener) {
        super(itemView);
        this.listener = listener;
        tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
        tvAttendanceTracker = itemView.findViewById(R.id.tvAttendanceTracker);
        tvProgressText = itemView.findViewById(R.id.progressText);
        progressBar = itemView.findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setSecondaryProgress(100);
        progressBar.setMax(100);
        positiveClick = itemView.findViewById(R.id.ivCheckPositiveAttendance);
        negativeClick = itemView.findViewById(R.id.ivCheckNegativeAttendance);

        positiveClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onPositiveClick(position);
                    }
                }
            }
        });

        negativeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onNegativeClick(position);
                    }
                }
            }
        });
    }

    public void setTvSubjectName(String name){
        tvSubjectName.setText(name);
    }

    public void setTvAttendanceTracker(String attended, String total){
        tvAttendanceTracker.setText(attended + "/" + total);
    }

    public void setAttendancePercentage(int attendancePercentage){
        progressBar.setProgress(attendancePercentage);
    }

    public void setTvProgressText(int percentage){
        String str = percentage+"%";
        tvProgressText.setText(str);
    }
}
