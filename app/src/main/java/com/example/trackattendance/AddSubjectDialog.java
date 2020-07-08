package com.example.trackattendance;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddSubjectDialog extends DialogFragment {
    DatabseHelper databseHelper;
    public interface OnAddSelected{
        void passStringToHomeFragment(String input);
    }
    public OnAddSelected onAddSelected;
    //widgets
    private EditText subjectName;
    private TextView actionAdd, actionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_add_subject, container,false);
        initializeUI(view);
        return view;
    }

    private void initializeUI(View view) {
        subjectName = view.findViewById(R.id.etSubjectName);
        actionAdd = view.findViewById(R.id.tvActionAdd);
        actionCancel = view.findViewById(R.id.tvActionCancel);
        databseHelper = DatabseHelper.getInstance(getActivity());

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = subjectName.getText().toString();

                if(!input.equals("")){
                    if(databseHelper.addData(input, 0, 0)) {
                        onAddSelected.passStringToHomeFragment(input);
                    }
                    else{
                        Toast.makeText(getActivity(),"Subject Already Exists", Toast.LENGTH_LONG).show();
                    }
                }
                getDialog().dismiss();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            onAddSelected = (OnAddSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.d(TAG, "onAttach: ClassCastException" +e.getMessage());
        }
    }

}
