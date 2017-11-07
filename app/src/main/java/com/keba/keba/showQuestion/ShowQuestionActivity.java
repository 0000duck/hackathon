package com.keba.keba.showQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keba.keba.R;
import com.keba.keba.data.Question;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowQuestionActivity extends AppCompatActivity {

    public static final String KEY_QUESTION_ID = "taskId";

    //@BindView(R.id.activity_showQuestion_title) TextView titleTextView;
    //@BindView(R.id.activity_showQuestion_bodyText) TextView bodyTextView;

    @BindView(R.id.activity_showQuestion_question) ViewGroup questionViewGroup;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showquestion);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        // get task id
        Intent intent = getIntent();
        Question question = (Question) intent.getSerializableExtra(KEY_QUESTION_ID);

        // update views
        updateQuestion(question);



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

    private void updateQuestion(Question question) {

        //questionViewGroup.findViewById(R.id.view_thumb_up);
        //questionViewGroup.findViewById(R.id.view_thumb_down);
        TextView scoreView = (TextView) questionViewGroup.findViewById(R.id.view_score);
        TextView titleView = (TextView) questionViewGroup.findViewById(R.id.view_title);
        TextView authorView = (TextView) questionViewGroup.findViewById(R.id.view_author);
        TextView dateView = (TextView) questionViewGroup.findViewById(R.id.view_date);
        TextView bodyView = (TextView) questionViewGroup.findViewById(R.id.view_body);

        scoreView.setText(question.votes+"");
        titleView.setText(question.title);
        authorView.setText(question.author);
        dateView.setText(question.time);
        bodyView.setText(question.body.content);


    }


}
