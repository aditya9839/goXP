package com.example.acer.gooxpp.Activity;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.gooxpp.Activity.Otp;
import com.example.acer.gooxpp.Adapter.User;
import com.example.acer.gooxpp.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpp extends Fragment {
    static  String token;
    android.support.v4.app.FragmentTransaction transaction;
    android.support.v4.app.FragmentManager fragmentManager;

    TextInputEditText esignupfullname;
    View inflatedView = null;
    Button eclosebuttonsignup,msignup1;
    TextInputEditText mfullname ,memail,mpassword,mconfirmpass;
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
        mfullname = inflatedView.findViewById(R.id.signupfullname);
        memail = inflatedView.findViewById(R.id.input_email);
        mpassword = inflatedView.findViewById(R.id.input_password);
        mconfirmpass = inflatedView.findViewById(R.id.match_pass);
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

                String name = mfullname.getText().toString();
                String email = memail.getText().toString();
                String pass = mpassword.getText().toString();
                String confirmpass = mconfirmpass.getText().toString();

                User user = new User(name,email,pass,confirmpass);
//                progressBar.setVisibility(View.VISIBLE);
                MainApplication.apiManager.createUser(user, new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
//                        progressBar.setVisibility(View.GONE);
                                User responseUser = response.body();
                                if (response.isSuccessful() && responseUser != null) {
                                    Log.d("BodyIs", "" + response.body().getMessage());
                                    Log.d("body", "" + response.body().isSuccess());
                                    //saving data to sharedpref
                                    token = response.body().getData().getToken();
                                    Log.d("Token", "" + token);
                                    sharedPrefData(token);
                                    Log.d("body", "" + response.body());
                                    Log.d("body", "" + response.isSuccessful());
                                    Log.d("body", "" + new Gson().toJson(response.body()));
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                        Log.d("body", "" + jsonObject);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

//                            Log.d("ERR",responseUser.getMessage());
                                    Log.d("error", "" + String.format("Response is %s", String.valueOf(response.code())));
                                    Log.d("error", "" + String.format("Response is %s", String.valueOf(response.message())));
//                            Log.d("error",""+String.format("Response is %s", ""+response.body().getMessage()));
                                    Toast.makeText(getContext(),
                                            String.format("Response is %s", String.valueOf(response.code()))
                                            , Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
//                        progressBar.setVisibility(View.GONE)
                                Log.d("errorFailure", t.getMessage());
                                Toast.makeText(getContext(),
                                        "Error is " + t.getMessage()
                                        , Toast.LENGTH_LONG).show();
                            }
                        });

                Otp otp = new Otp();
                otp.referance(getActivity());
                transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.parent_content, inviteAndEarn).addToBackStack(null).commit();
//                Otp otp = new Otp();
//                otp.referance(getActivity());
//                transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//                transaction.replace(R.id.parent_content, otp).addToBackStack(null).commit();
                transaction.replace(R.id.loginn, otp).commit();

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
    public void sharedPrefData(String token){
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences=getActivity().getSharedPreferences("file", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("token_key",token);
        editor.commit();
        String name=preferences.getString("token_key",null);
        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
    }


}
