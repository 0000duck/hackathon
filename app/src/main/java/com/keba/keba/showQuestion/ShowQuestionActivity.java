package com.keba.keba.showQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.keba.keba.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowQuestionActivity extends AppCompatActivity {

    public static final String KEY_QUESTION_ID = "taskId";

    @BindView(R.id.activity_showQuestion_title) TextView titleTextView;
    @BindView(R.id.activity_showQuestion_bodyText) TextView bodyTextView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showquestion);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        // get task id
        Intent intent = getIntent();
        int questionId = intent.getIntExtra(KEY_QUESTION_ID, 0);

        // update views
        updateViews();

    }


    @OnClick(R.id.activity_showQuestion_cancel)
    public void onClickCancelButton(View view) {
        this.finish();
    }


    @OnClick(R.id.activity_showQuestion_comment)
    public void onClickEditButton(View view) {
        //Intent intent = new Intent(MainActivity.class, AddActivity.class);
        // TODO task id muss übergeben werden
        this.finish();  // Does not actually destroy the object. So, the next line will be reached.
        //startActivity(intent);
    }

    private void updateViews() {

        // title
        //titleTextView.setText();


        // body
        //bodyTextView.setText();
    }


}
