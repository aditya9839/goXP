package com.example.acer.gooxpp.Activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.acer.gooxpp.Constant.Constant;
import com.example.acer.gooxpp.EndPoint.EndPointInterface;
import com.example.acer.gooxpp.Utility.ConnectionCheckUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity  extends AppCompatActivity{

    public ConnectionCheckUtility connectionCheckUtility;
    /// public SessionUtility sessionUtility;
    public ProgressDialog progressDialog;
    public EndPointInterface endPoints;
    public AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectionCheckUtility = new ConnectionCheckUtility(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // code for recovering from crash
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//                                                      public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
//                                                          Log.e("Error"+Thread.currentThread().getStackTrace()[2],paramThrowable.getLocalizedMessage());
//                                                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                                          intent.putExtra("crash", true);
//                                                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                                                                  | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                                                  | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                                          PendingIntent pendingIntent = PendingIntent.getActivity(PoliticianApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//                                                          AlarmManager mgr = (AlarmManager) PoliticianApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
//                                                          mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
//                                                          finish();
//                                                          System.exit(2);
//                                                      }
//                                                  }
//        );
//
    }


    public void showProgressDialog(String title, String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void fullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void dismissProgress() {
        if (progressDialog!=null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void showToastBase(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

//    void handleError(Response<?> response) {
//        ErrorModel errorModel = ErrorHandlingUtility.parseError(response);
//        if (errorModel != null && errorModel.getMessage() != null) {
//            showToastShort(errorModel.getMessage());
//        } else {
//            showToastLong("Something went wrong");
//        }
//    }
    public void showToastShort(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    boolean isShowing = false;
    public void showAlert(String title, String message) {
        AlertDialog dialog = null;
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                isShowing = false;
            }
        });
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    dialog.dismiss();
                    isShowing = false;

                } catch (Exception e) {
                    e.printStackTrace();
                    isShowing = false;
                }

            }
        });
        dialog = alertDialog.create();

        if (!isShowing && !connectionCheckUtility.isConnected()) {
            dialog.show();
            isShowing = true;
        }
    }

    public String parseDate(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";

        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
