package com.example.acer.gooxpp.Activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.acer.gooxpp.Adapter.PlaceAutocompleteAdapter;
import com.example.acer.gooxpp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;

public class SearchActivity extends FragmentActivity implements PlaceAutocompleteAdapter.PlaceAutoCompleteInterface, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,View.OnClickListener {
    Context mContext;
    GoogleApiClient mGoogleApiClient;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction transaction;

    ScrollView myour_scrollview;
    Button msearchactivity;

    LinearLayout mParent, mdocTypeLayout;
    RelativeLayout mtest;
    private RecyclerView mRecyclerView;
    LinearLayoutManager llm;
    PlaceAutocompleteAdapter mAdapter;
    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(23.63936, 68.14712), new LatLng(28.20453, 97.34466));
    AutoCompleteTextView actv;

    EditText mSearchEdittext;
    ImageView mClear;
    String[] fruits = {"Cardiologist", "Dermatologist", "Neurosurgeon", "Ophthalmologist", "Pediatrician", "Pediatrician", "Dentist", "Pear"};

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().setBackgroundDrawableResource(R.drawable.backtest);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mContext = SearchActivity.this;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits);

        actv = (AutoCompleteTextView) findViewById(R.id.docSpecilization);
        msearchactivity = findViewById(R.id.searchdoctors);
        msearchactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorList doctorList = new DoctorList();
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.test2, doctorList).commit();
            }
        });

        actv.setThreshold(2);//will start working from first character
        actv.setAdapter(adapter);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();

        initViews();
    }

    private void initViews(){
        mRecyclerView = (RecyclerView)findViewById(R.id.list_search);
        mRecyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);

        myour_scrollview = findViewById(R.id.scrollView1);
        mtest = findViewById(R.id.test2);
        mdocTypeLayout = findViewById(R.id.docTypeLayout);

        mSearchEdittext = (EditText)findViewById(R.id.search_et);
        mClear = (ImageView)findViewById(R.id.clear);
        mClear.setOnClickListener(this);


        actv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                getWindow().setBackgroundDrawableResource(R.drawable.dashboardblur);
                ValueAnimator realSmoothScrollAnimation =
                        ValueAnimator.ofInt(myour_scrollview.getScrollY(),420);
                realSmoothScrollAnimation.setDuration(500);
                realSmoothScrollAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        int scrollTo = (Integer) animation.getAnimatedValue();
                        myour_scrollview.scrollTo(0, scrollTo);
                        new Handler().postDelayed(new Runnable(){
                            //
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(actv, InputMethodManager.SHOW_IMPLICIT);
                            }
                        },500);
                    }
                });

                realSmoothScrollAnimation.start();

//                new CountDownTimer(2000, 500) {
//
//                    public void onTick(long millisUntilFinished) {
//
//                        myour_scrollview.scrollTo(0 , (int) ((int) 2000 - millisUntilFinished));
//                    }
//
//                    public void onFinish() {
//                    }
//
//                }.start();
//                }

                return false;
            }
        });

        mAdapter = new PlaceAutocompleteAdapter(this, R.layout.view_placesearch,
                mGoogleApiClient, BOUNDS_INDIA, null);

//        mAdapter
//        mRecyclerView.setAdapter(mAdapter);


        mSearchEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setBackgroundDrawableResource(R.drawable.dashboardblur);
//                mdocTypeLayout.setVisibility(View.GONE);
                mRecyclerView.setAdapter(mAdapter);
//
            }
        });
        mSearchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mRecyclerView.setVisibility(View.VISIBLE);

                    mClear.setVisibility(View.VISIBLE);
                    if (mAdapter != null) {
                        mRecyclerView.setAdapter(mAdapter);
//                        PlaceAutocompleteAdapter.PlaceAutocomplete sw = mAdapter.getItem(0);
//                        Log.d("place",sw.toString());
                    }
                } else {
                    mClear.setVisibility(View.GONE);
//                    if (mSavedAdapter != null && mSavedAddressList.size() > 0) {
//                        mRecyclerView.setAdapter(mSavedAdapter);
//                    }
                }
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {
                    mAdapter.getFilter().filter(s.toString());
                } else if (!mGoogleApiClient.isConnected()) {
//                    Toast.makeText(getApplicationContext(), Constants.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                    Log.e("", "NOT CONNECTED");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == mClear){
            mSearchEdittext.setText("");
            if(mAdapter!=null){
                mAdapter.clearList();
            }

        }
    }
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onPlaceClick(final ArrayList<PlaceAutocompleteAdapter.PlaceAutocomplete> mResultList, int position) {
        if(mResultList!=null){
            try {
                final String placeId = String.valueOf(mResultList.get(position).placeId);
                Log.d("asd","asdahsdam"+placeId);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if(places.getCount()==1){
                            //Do the things here on Click.....
                            Intent data = new Intent();
                            data.putExtra("lat",String.valueOf(places.get(0).getLatLng().latitude));
                            Log.d("sad","asvd"+places.get(0).getLatLng().latitude);

                            data.putExtra("lng", String.valueOf(places.get(0).getLatLng().longitude));
                            setResult(SearchActivity.RESULT_OK, data);
//                            mdocTypeLayout.setVisibility(View.VISIBLE);
                            //name of place
                            PlaceAutocompleteAdapter.PlaceAutocomplete s = mResultList.get(0);

                            //setting name of place to edittext

                            mSearchEdittext.setText(s.toString());
                            mRecyclerView.setVisibility(View.GONE);
                            hideKeyboard(SearchActivity.this);
//                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            catch (Exception e){

            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("abc","abcd");
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onBackPressed() {
        getWindow().setBackgroundDrawableResource(R.drawable.backgroundsearchactivity);
        super.onBackPressed();

    }
    public static void hideKeyboard(FragmentActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}