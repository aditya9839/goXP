package com.example.acer.gooxpp.Activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.gooxpp.Adapter.DoctorAdapter;
import com.example.acer.gooxpp.Model.Doctors;
import com.example.acer.gooxpp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorList extends Fragment {

    RecyclerView recyclerView;
    Button mcalldoctor;
    View inflatedView = null;
//    MainActivity mainActivity;
    List<Doctors> doctorsList;

    public DoctorList() {

        // Required empty public constructor
    }

    public void alert(View v){
        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
        AlertDialog ab;
        ab = new AlertDialog.Builder(v.getContext()).create();
//        Toast.makeText(getContext(), "asdg", Toast.LENGTH_SHORT).show();

        ab.setTitle("Call Option");
        ab.setMessage("");
        ab.setCancelable(false);
        ab.setButton(AlertDialog.BUTTON_POSITIVE, "Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        ab.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        ab.show();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_doctor_list, container, false);
       View v1 = inflater.inflate(R.layout.card2, container, false);
        this.inflatedView = v;


        doctorsList = new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        Log.d("asd","asd"+recyclerView);

        Toast.makeText(getActivity(), ""+recyclerView+"  "+mcalldoctor, Toast.LENGTH_SHORT).show();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        doctorsList = new ArrayList<>();
        //adding some items to our list
        doctorsList.add(
                new Doctors(
                        1,
                        "Dr. Shrey Dhawan",
                        "MDS",
                        4.3,
                        500,
                        R.drawable.images));
        doctorsList.add(
                new Doctors(
                        1,
                        "Dr. Jimmy Page",
                        "MBBS",
                        3.7,
                        300,
                        R.drawable.doctorimage));
        doctorsList.add(
                new Doctors(
                        1,
                        "Dr. Russel Brown",
                        "MDS",
                        4.2,
                        400,
                        R.drawable.doc3));

        //creating recyclerview adapter
        DoctorAdapter adapter = new DoctorAdapter(getActivity(), doctorsList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

//                mcalldoctor =(Button) recyclerView.findViewById(R.id.callDoctor);
//
//        mcalldoctor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            alert(view);
//            }
//        });
        return v;
    }

    public void ButtonClick(View view){

    }

}
