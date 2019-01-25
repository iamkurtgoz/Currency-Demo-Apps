package com.iamkurtgoz.currencydemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.iamkurtgoz.currencydemo.currency.CurrencyAdapter;
import com.iamkurtgoz.currencydemo.currency.CurrencyModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //private String apiURL = "http://data.fixer.io/api/latest?access_key=dc03684b36ec78e7419f9c974cdb761e&format=1";
    private String apiURL = "http://data.fixer.io";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ArrayList<CurrencyModel> currencyModel;
    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        getValueForFixer();
    }

    private void init(){
        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.activity_main_progressBar);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        currencyModel = new ArrayList<>();
        currencyAdapter = new CurrencyAdapter(currencyModel);

        recyclerView.setAdapter(currencyAdapter);

    }

    private void getValueForFixer(){
        Call<ResponseBody> call = getApiClient().api("dc03684b36ec78e7419f9c974cdb761e", 1);
        setLoadingStatus(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONObject rates = jsonObject.getJSONObject("rates");

                    if (currencyModel.size() > 0){
                        currencyModel.clear();
                    }

                    currencyModel.add(new CurrencyModel(R.drawable.canada, "Kanada", rates.getString("CAD")));
                    currencyModel.add(new CurrencyModel(R.drawable.england, "İngiltere", rates.getString("GBP")));
                    currencyModel.add(new CurrencyModel(R.drawable.japon, "Japonya", rates.getString("JPY")));
                    currencyModel.add(new CurrencyModel(R.drawable.usd, "USD", rates.getString("USD")));
                    currencyModel.add(new CurrencyModel(R.drawable.turkey, "TÜRKİYE", rates.getString("TRY")));

                    currencyAdapter.notifyDataSetChanged();


                } catch (IOException e) {
                    e.printStackTrace();
                    customMessage("Hata",e.getLocalizedMessage(),"OK");
                    Log.d("MyLog",e.getLocalizedMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                    customMessage("Hata",e.getLocalizedMessage(),"OK");
                    Log.d("MyLog",e.getLocalizedMessage());
                }
                setLoadingStatus(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                customMessage("Hata",t.getLocalizedMessage(),"OK");
                Log.d("MyLog",t.getLocalizedMessage());
            }
        });
    }

    //Retrofit
    private Retrofit retrofit = null;
    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    public static synchronized OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    protected RetrofitInterface getApiClient() {
        return getRetrofit().create(RetrofitInterface.class);
    }

    //Messages Function
    void customMessage(String title, String message, String buttonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(message);
        builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    void setLoadingStatus(Boolean isLoading){
        if (isLoading) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
