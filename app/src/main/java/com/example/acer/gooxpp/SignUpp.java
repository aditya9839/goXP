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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpp extends Fragment {
    android.support.v4.app.FragmentTransaction transaction;
    android.support.v4.app.FragmentManager fragmentManager;

    TextInputEditText esignupfullname;
    View inflatedView = null;
    Button eclosebuttonsignup,msignup1;
    Activity parentActivity;

    public void referance(Activity activity){
        this.parentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_upp, container, false);
        this.inflatedView = view;
        eclosebuttonsignup = inflatedView.findViewById(R.id.closebuttonsignup);
        msignup1 = inflatedView.findViewById(R.id.signup1);
        fragmentManager = getActivity().getSupportFragmentManager();
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
        msignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Otp otp = new Otp();
                otp.referance(getActivity());
                transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                transaction.replace(R.id.parent_content, otp).addToBackStack(null).commit();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        esignupfullname = inflatedView.findViewById(R.id.signupfullname);
        esignupfullname.requestFocus();
        esignupfullname.setFocusableInTouchMode(true);
        new Handler().postDelayed(new Runnable(){
//
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(esignupfullname, InputMethodManager.SHOW_IMPLICIT);
            }
        },500);
    }

}
