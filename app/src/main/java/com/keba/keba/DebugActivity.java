package com.keba.keba;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.keba.keba.backend.Backend;
import com.keba.keba.barcodeUtil.BarcodeIntentIntegrator;
import com.keba.keba.barcodeUtil.BarcodeIntentResult;
import com.keba.keba.data.Alarm;
import com.keba.keba.data.QR;
import com.keba.keba.data.Answer;
import com.keba.keba.data.Body;
import com.keba.keba.data.Question;
import com.keba.keba.data.Tag;
import com.keba.keba.data.request.AlarmRequest;
import com.keba.keba.data.request.NewAnswerRequest;
import com.keba.keba.data.response.SearchResponse;
import com.keba.keba.util.DateConverter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DebugActivity extends AppCompatActivity {

    @BindView(R.id.main_contentText) TextView contentText;
    @BindView(R.id.main_qrCode) TextView qrCode;
    @BindView(R.id.button_createQuestion) Button createQuestion;
    @BindView(R.id.button_currentDate) Button currentDate;
    @BindView(R.id.button_postAnswer) Button postAnswer;
    @BindView(R.id.editText_questionId) EditText questionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_main);
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

        createQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCreateQuestion();
            }
        });

        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCurrentDate();
            }
        });

        postAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPostAnswer();
            }


        });
    }

    private Callback<ResponseBody> standardResponseHandler = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                String data = response.body() != null ? response.body().string() : "<None>";
                Log.i("MainActivity", data);
                contentText.setText(data);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("MainActivity", e.getMessage());
                contentText.setText(e.getMessage());
            }

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
            Log.i("MainActivity", "Failed to call; message was: " + t.getMessage());
            contentText.setText(t.getMessage());
        }
    };

    private void onPostAnswer() {
        Answer answer = new Answer();
        answer.title = "Known issue";
        answer.time = DateConverter.current();
        answer.langId = "en";
        answer.author = "Bernhard5001";
        Body b = new Body();
        answer.body = b;
        b.content = "This is a known issue in the latest KePlast i5000 version. Please enter a bug into the KEBA database.";
        b.mime = "text";
        NewAnswerRequest request = new NewAnswerRequest(questionId.getText().toString(), answer);
        Call<ResponseBody> responseBodyCall = Backend.getInstance().newAnswer(request);
        responseBodyCall.enqueue(standardResponseHandler);
    }

    private void onClickCurrentDate() {
        currentDate.setText(DateConverter.current());
    }



    private void onClickCreateQuestion() {
        Body b = new Body();
        b.mime = "text";
        //b.content = "I really don't know how to start the machine. Which buttons do I need to press in order to start it? " +
        //        "I mean this is not really easy to understand. Please help me!";

        //b.content = "I've just encountered this alarm but I really don't know what to do. I tried the solution provided by the manufacturer but it still doesn't work. I must have missed something. Can someone please help me?";
        b.content = "Why does my machine say I am running a test version?";


        QR qr = new QR();
        qr.alarm = new Alarm();
        qr.alarm.id = "ExceptionInterpretation1.erNoFinalVersion";
        qr.alarm.category = "INFO";
        qr.alarm.text = "Test version of firmware package is running on CPU";
        qr.alarm.time = "2017-11-06 03:23";

        //String title = "How can I fix the alarm EnergyMeter1.erResponseTimeout - manufacturer solution doesn't help!";
        String title = "Testversion running?";
        Question q = new Question("SetByServer", Arrays.asList(new Tag("KePlast i5000"), new Tag("KePlast i8000")), 0, title, b, "Fabian123", new Date(), "en", qr);
        //Call<ResponseBody> responseBodyCall = Backend.getInstance().newQuestionResponseBody(q);
        //responseBodyCall.enqueue(standardResponseHandler);

        Call<Question> responseBodyCall = Backend.getInstance().newQuestion(q);
        responseBodyCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response.body() != null) {
                    Log.i("DebugActivity", response.body().toString());
                    questionId.setText(response.body().id);
                } else {
                    questionId.setText("Failed to create question.");
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                t.printStackTrace();
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
        BarcodeIntentIntegrator barcodeIntentIntegrator = new BarcodeIntentIntegrator(this);
        barcodeIntentIntegrator.initiateScan();



        //Call<SearchRequest> stringCall = retrofitInterface.test2(new SearchRequest("What do you know about alarm 500?", ping));
//        stringCall.enqueue(new Callback<SearchRequest>() {
//            @Override
//            public void onResponse(Call<SearchRequest> call, Response<SearchRequest> response) {
//               // try {
//                   SearchRequest data = response.body();
//                    System.out.println(data);
//                    ping = data.pong;
//                    contentText.setText(data.toString());
//                    //contentText.setText(data.Hello);
//             //   } catch (IOException ie) {
//              //      ie.printStackTrace();
//               // }
//            }
//
//            @Override
//            public void onFailure(Call<SearchRequest> call, Throwable t) {
//                contentText.setText("Ohh, no!!");
//            }
//        });

       // BarcodeIntentIntegrator barcodeIntentIntegrator = new BarcodeIntentIntegrator(this);
       // barcodeIntentIntegrator.initiateScan();
    }

    public void sendAlarmRequest(QR alarm) {

        AlarmRequest request = new AlarmRequest("en-US", alarm);
        Log.i("MainActivity", "Sending request\n" + request.toString());
        //Call<ResponseBody> responseBodyCall = Backend.getInstance().queryByQRResponseBody(request);
        //responseBodyCall.enqueue(standardResponseHandler);

        Call<SearchResponse> responseBodyCall = Backend.getInstance().queryByQR(request);
        responseBodyCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                String data = response.body().toString();
                Log.i("MainActivity", data);
                contentText.setText(data);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        BarcodeIntentResult scanResult = BarcodeIntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String content = scanResult.getContents();
            Gson gson = new GsonBuilder().create();
            QR alarmWrapper = gson.fromJson(content, QR.class);
            qrCode.setText(content);
            Log.i("MainActivity", alarmWrapper.alarm != null ?  alarmWrapper.alarm.toString() : "null");
            if (alarmWrapper.alarm != null) {
                sendAlarmRequest(alarmWrapper);
            }
        } else {
            qrCode.setText("No QR code.");
        }
    }
}
