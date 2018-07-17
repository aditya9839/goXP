package com.example.acer.gooxpp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class Login extends Fragment {

    Button eclosebuttonlogin;
    TextInputEditText eloginemail5;
    View inflatedView = null;
     Activity parentActivity;

    public void referance(Activity activity){
        this.parentActivity = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        eloginemail5 = inflatedView.findViewById(R.id.loginemail5);
//        eloginemail5.setKeyListener(originalKeyListener);
        eloginemail5.requestFocus();
        eloginemail5.setFocusableInTouchMode(true);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(eloginemail5, InputMethodManager.SHOW_IMPLICIT);
            }
        },500);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        this.inflatedView = view;
        eclosebuttonlogin = inflatedView.findViewById(R.id.closebuttonlogin);

        eclosebuttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                parentActivity.onBackPressed();

            }
        });

        return view;
    }


}
