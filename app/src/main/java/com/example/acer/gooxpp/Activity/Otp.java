package com.example.acer.gooxpp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.acer.gooxpp.R;

public class Otp extends Fragment {
    EditText me1,me2,me3,me4,me5,me6;
    private Activity parentActivity;
    Button eclosebuttonsignup,msubmit;


    public void referance(Activity activity){
        this.parentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp, container, false);
        eclosebuttonsignup = view.findViewById(R.id.closebuttonotp);
        msubmit = view.findViewById(R.id.submit);

        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });

        eclosebuttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MainActivity.hideKeyboard(view);
                //close keyboard
                InputMethodManager imm = (InputMethodManager)parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                parentActivity.onBackPressed();
            }
        });

        me1= view.findViewById(R.id.e1);
        me2= view.findViewById(R.id.e2);
        me3= view.findViewById(R.id.e3);
        me4= view.findViewById(R.id.e4);
        me5= view.findViewById(R.id.e5);
        me6= view.findViewById(R.id.e6);
        focus(me1,me2,me1);
        focus(me2,me3,me1);
        focus(me3,me4,me2);
        focus(me4,me5,me3);
        focus(me5,me6,me4);
        focus(me6,me6,me5);


        // Inflate the layout for this fragment
        return view;
    }
    public void focus(final EditText current,final EditText next,final EditText last){
        current.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(current.getText().toString().length()==1)     //size as per your requirement
                {
                    next.requestFocus();
                }
                if(current.getText().toString().length()==0)     //size as per your requirement
                {
                    last.requestFocus();
                }

            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
    }
}
