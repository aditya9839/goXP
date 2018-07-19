package com.example.acer.gooxpp.Application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.example.acer.gooxpp.Constant.Constant;
import com.example.acer.gooxpp.EndPoint.EndPointInterface;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoXPApplication extends Application{


    private static GoXPApplication instance;


    public static GoXPApplication getInstance() {
        return instance;
    }

    public static void setInstance(GoXPApplication instance) {
        GoXPApplication.instance = instance;
    }


    private EndPointInterface endPoints;
   //private SessionUtility sessionUtility;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();

    }



//    public SessionUtility getSessionUtility() {
//        return sessionUtility;
//    }

    public EndPointInterface getEndPoints() {
        return endPoints;
    }

    public static GoXPApplication getApp() {
        return instance;
    }
    public void initRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

//        SSLContext context = null;
//        try {
//            CertificateFactory cf  = CertificateFactory.getInstance("X.509");
//            Certificate certificate = cf.generateCertificate(this.getResources().openRawResource(R.raw.aster));
//            String keyStoreType = KeyStore.getDefaultType();
//            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("ca", certificate);
//
//            // Create a TrustManager that trusts the CAs in our KeyStore
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            tmf.init(keyStore);
//            trustManagers = tmf.getTrustManagers();
//
////            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
//            context = SSLContext.getInstance("TLS");
//            context.init(null, tmf.getTrustManagers(), null);
//        }
//        catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException | KeyManagementException e) {
//            e.printStackTrace();
//        }
//        context = null;


        OkHttpClient.Builder httpClient = null;
//        if (context != null)
//        {
//            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
//            }
//            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
//            httpClient = new OkHttpClient.Builder().sslSocketFactory(context.getSocketFactory(), trustManager);
//        }
//        else
//        {
        httpClient = new OkHttpClient.Builder();
//        }
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request.Builder request = original.newBuilder();
                // uncomment code for access token
//                if (instance.getSessionUtility().getAuthenticationStatus()) {
//                    request.header("Authorization", "Bearer " + instance.getSessionUtility().getAuthTokenKey());
//                }
                request.header("Content-Type", "application/json");
                request.header("Accept", "application/json");
                Request request1 = request.build();
                return chain.proceed(request1);

            }
        });

        httpClient.connectTimeout(40, java.util.concurrent.TimeUnit.SECONDS);
        httpClient.readTimeout(40, java.util.concurrent.TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        endPoints = retrofit.create(EndPointInterface.class);
    }
}
