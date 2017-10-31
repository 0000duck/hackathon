package com.keba.keba;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.keba.keba.BarcodeUtil.BarcodeIntentIntegrator;
import com.keba.keba.BarcodeUtil.BarcodeIntentResult;
import com.keba.keba.Data.SearchRequest;
import com.keba.keba.Data.Test;
import com.keba.keba.Data.Test2;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_contentText) TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickQrButton();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int ping = 0;

    public void onClickQrButton() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<SearchRequest> stringCall = retrofitInterface.test2(new SearchRequest("What do you know about alarm 500?", ping));
        stringCall.enqueue(new Callback<SearchRequest>() {
            @Override
            public void onResponse(Call<SearchRequest> call, Response<SearchRequest> response) {
               // try {
                   SearchRequest data = response.body();
                    System.out.println(data);
                    ping = data.pong;
                    contentText.setText(data.toString());
                    //contentText.setText(data.Hello);
             //   } catch (IOException ie) {
              //      ie.printStackTrace();
               // }
            }

            @Override
            public void onFailure(Call<SearchRequest> call, Throwable t) {
                contentText.setText("Ohh, no!!");
            }
        });

       // BarcodeIntentIntegrator barcodeIntentIntegrator = new BarcodeIntentIntegrator(this);
       // barcodeIntentIntegrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        BarcodeIntentResult scanResult = BarcodeIntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String content = scanResult.getContents();
            contentText.setText(content);
        }
    }
}
