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
import android.widget.VideoView;

import com.keba.keba.barcodeUtil.BarcodeIntentIntegrator;
import com.keba.keba.barcodeUtil.BarcodeIntentResult;
import com.keba.keba.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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