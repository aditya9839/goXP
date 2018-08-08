package com.example.acer.gooxpp.Activity;

import android.util.Log;

import com.example.acer.gooxpp.Adapter.User;
import com.example.acer.gooxpp.EndPoint.IUsersApi;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acer on 04-Aug-18.
 */

public class ApiManager {

    private static IUsersApi service;
    private static ApiManager apiManager;

    private static boolean shouldAddToken = false;

    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
//            okHttpClient.networkInterceptors().add(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request.Builder requestBuilder = chain.request().newBuilder();
//                    requestBuilder.header("Content-Type","application/json");
//                    requestBuilder.addHeader("Accept","application/json");
//                    return chain.proceed(requestBuilder.build());
//                }
//            });
            okHttpClient = okHttpClient.newBuilder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request.Builder requestBuilder = chain.request().newBuilder();
//                    requestBuilder.header("Content-Type","application/json");
//                                    if (RetrofitPost.token !=null){
//                                        requestBuilder.addHeader("Authorization","Bearer "+RetrofitPost.token);
//                                        Log.d("token is saved","");
//                                    }

                                    requestBuilder.addHeader("Accept","application/json");
                                    return chain.proceed(requestBuilder.build());
                                }
                            })
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private ApiManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.goxp.care/")
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IUsersApi.class);
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void createUser(User user, Callback<User> callback) {
        Call<User> userCall = service.createUser(user);
        userCall.enqueue(callback);
    }
}
