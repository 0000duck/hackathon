package com.keba.keba.addQuestion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.keba.keba.backend.Backend;
import com.keba.keba.barcodeUtil.BarcodeIntentIntegrator;
import com.keba.keba.barcodeUtil.BarcodeIntentResult;
import com.keba.keba.R;
import com.keba.keba.data.Body;
import com.keba.keba.data.QR;
import com.keba.keba.data.Question;
import com.keba.keba.data.Tag;

import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *  Created by Sparrow on 10/3/2017.
 */
public class AddQuestionActivity extends AppCompatActivity {

    @BindView(R.id.activity_addquestion_save) Button saveButton;
    @BindView(R.id.activity_addquestion_edittitle) EditText questionTitleView;
    @BindView(R.id.activity_addquestion_editbody) EditText questionBodyView;

    @BindView(R.id.activity_addquestion_attached_qrImg) ImageView attachedQrImageView;
    @BindView(R.id.activity_addquestion_attached_qrText) TextView attachedQrTextView;
    @BindView(R.id.activity_addquestion_attached_pictureImg) ImageView attachedPictureImageView;
    @BindView(R.id.activity_addquestion_attached_pictureText) TextView attachedPictureTextView;
    @BindView(R.id.activity_addquestion_attached_videoImg) ImageView attachedVideoImageView;
    @BindView(R.id.activity_addquestion_attached_videoText) TextView attachedVideoTextView;

    private boolean qrAttached = false;
    private boolean pictureAttached = false;
    private boolean videoAttached = false;

    private String qrStr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        setupEditText();
        updateAttachViews();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    //////////////////////////
    // Edit Text
    /////////////////////////
    private void setupEditText() {
        questionTitleView.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override public void afterTextChanged(Editable s) {
                saveButton.setEnabled(s.length() > 0);
            }
        });
    }


    //////////////////////////
    // Action bar button
    /////////////////////////
    @OnClick(R.id.activity_addquestion_cancel)
    public void onClickCancelTask(View view) {
        this.finish();
    }


    @OnClick(R.id.activity_addquestion_barcode)
    public void onClickQrButton(View view) {
        BarcodeIntentIntegrator barcodeIntentIntegrator = new BarcodeIntentIntegrator(this);
        barcodeIntentIntegrator.initiateScan();
    }


    @OnClick(R.id.activity_addquestion_camera)
    public void onClickVideoButton(View view) {
//        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//        }
    }

    @OnClick(R.id.activity_addquestion_save)
    public void onClickSaveButton(View view) {

        String titleStr = questionTitleView.getText().toString();
        String bodyStr = questionBodyView.getText().toString();
        // qrStr

        // String id, List<Tag> tags, int votes, String title, Body body, String author, Date time, String langId, QR qr
        QR qr = null;
        if (qrAttached) {
            Gson gson = new GsonBuilder().create();
            qr = gson.fromJson(qrStr, QR.class);
        }
        Body b = new Body();
        b.mime = "text";
        b.content = bodyStr;

        Question question = new Question("ToTest", Arrays.<Tag>asList(), 0, titleStr, b, "User123", new Date(), "en", qr);
        Call<Question> questionCall = Backend.getInstance().newQuestion(question);
        questionCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                Toast.makeText(AddQuestionActivity.this, "Added question.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Toast.makeText(AddQuestionActivity.this, "Failed to add question.", Toast.LENGTH_SHORT).show();
            }
        });
        this.finish();
    }




    //////////////////////////
    // Helper
    /////////////////////////

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case BarcodeIntentIntegrator.REQUEST_CODE:
                BarcodeIntentResult scanResult = BarcodeIntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
                if (scanResult != null) {
                    if(!scanResult.getContents().equals("null")){

                        qrStr = scanResult.getContents();
                        qrAttached = true;
                        updateAttachViews();

                    }
                }
                break;
        }

    }



    private void updateAttachViews(){

        if(qrAttached) {
            attachedQrImageView.setVisibility(View.VISIBLE);
            attachedQrTextView.setVisibility(View.VISIBLE);
        } else {
            attachedQrImageView.setVisibility(View.GONE);
            attachedQrTextView.setVisibility(View.GONE);
        }

        if(pictureAttached) {
            attachedPictureImageView.setVisibility(View.VISIBLE);
            attachedPictureTextView.setVisibility(View.VISIBLE);
        } else {
            attachedPictureImageView.setVisibility(View.GONE);
            attachedPictureTextView.setVisibility(View.GONE);
        }

        if(videoAttached) {
            attachedVideoImageView.setVisibility(View.VISIBLE);
            attachedVideoTextView.setVisibility(View.VISIBLE);
        } else {
            attachedVideoImageView.setVisibility(View.GONE);
            attachedVideoTextView.setVisibility(View.GONE);
        }

    }

}