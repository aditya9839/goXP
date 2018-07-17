package com.example.acer.gooxpp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookAnAppoinment extends AppCompatActivity {

    TextInputEditText mdate,mtime;
    TextInputLayout mdateLayout,mtimeLayout;
    Button mbook;
    TextView miUnderstand;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_an_appoinment);
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
//create a list of items for the spinner.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] items = getResources().getStringArray(R.array.gender);
//create an adapter to describe how the items are displayed, adapters are used in several places in android.

//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        mbook = (Button) findViewById(R.id.book);
        dialog= new Dialog(BookAnAppoinment.this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        dialog.setContentView(R.layout.booked_appointment_custom_dialog);

        mbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
//                Window window = dialog.getWindow();
//                window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            }
        });
        miUnderstand = dialog.findViewById(R.id.iUnderstand);
        miUnderstand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        mdateLayout = (TextInputLayout) findViewById(R.id.dateLayout);
        mtimeLayout = (TextInputLayout) findViewById(R.id.timeLayout);
        mdate = (TextInputEditText) findViewById(R.id.dateee);
        mtime = (TextInputEditText) findViewById(R.id.time);
        mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar ca = Calendar.getInstance();
                new DatePickerDialog(BookAnAppoinment.this, datelistener,
                        ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        mtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar ca = Calendar.getInstance();
                new TimePickerDialog(BookAnAppoinment.this,timeSetListener,ca.get(Calendar.HOUR_OF_DAY),ca.get(Calendar.MINUTE),true).show();

            }
        });
    }
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            mtime.setText(hourOfDay+" : "+minute);
        }
    };
    DatePickerDialog.OnDateSetListener datelistener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
            mdate.setText(dayOfmonth + "/" + (month + 1) + "/" + year);
        }
    };

}
