package com.keba.keba.showQuestion;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keba.keba.R;
import com.keba.keba.data.Question;
import com.keba.keba.swipeBack.SwipeBackActivity;
import com.keba.keba.swipeBack.SwipeBackLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowQuestionActivity extends SwipeBackActivity {

    public static final String KEY_QUESTION_ID = "taskId";

    @BindView(R.id.activity_showQuestion_question) ViewGroup questionViewGroup;

    private ImageView thumpUpView;
    private ImageView thumpDownView;
    private TextView scoreView;
    private Question question;

    private int deltaThump = 0;



    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showquestion);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);

        //View view = findViewById(R.id.activity_showQuestion_layout);
        //view.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));

        // get task id
        Intent intent = getIntent();
        question = (Question) intent.getSerializableExtra(KEY_QUESTION_ID);



        thumpUpView = (ImageView) questionViewGroup.findViewById(R.id.view_thumb_up);
        thumpUpView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                voteUp();
            }
        });
        thumpDownView = (ImageView) questionViewGroup.findViewById(R.id.view_thumb_down);
        thumpDownView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                voteDown();
            }
        });

        scoreView = (TextView) questionViewGroup.findViewById(R.id.view_score);

        // update views
        updateQuestion();

    }

    private void voteUp() {

        if(deltaThump == 1) {
            deltaThump = 0;
            thumpUpView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        } else {
            deltaThump = 1;
            thumpUpView.setColorFilter(ContextCompat.getColor(this, R.color.colorGreen));
            thumpDownView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        }
        updateQuestion();
    }

    private void voteDown() {

        if(deltaThump == -1) {
            deltaThump = 0;
            thumpDownView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        } else {
            deltaThump = -1;
            thumpDownView.setColorFilter(ContextCompat.getColor(this, R.color.colorRed));
            thumpUpView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        }
        updateQuestion();
    }


    @OnClick(R.id.activity_showQuestion_cancel)
    public void onClickCancelButton(View view) {
        this.finish();
    }


    @OnClick(R.id.activity_showQuestion_comment)
    public void onClickCommentButton(View view) {
        //Intent intent = new Intent(MainActivity.class, AddActivity.class);
        // TODO task id muss übergeben werden
        this.finish();  // Does not actually destroy the object. So, the next line will be reached.
        //startActivity(intent);
    }

    private void updateQuestion() {

        TextView titleView = (TextView) questionViewGroup.findViewById(R.id.view_title);
        TextView authorView = (TextView) questionViewGroup.findViewById(R.id.view_author);
        TextView dateView = (TextView) questionViewGroup.findViewById(R.id.view_date);
        TextView bodyView = (TextView) questionViewGroup.findViewById(R.id.view_body);

        scoreView.setText(question.votes+deltaThump+"");
        if(question.votes+deltaThump == 0) {
            scoreView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
            scoreView.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        } else if(question.votes+deltaThump < 0) {
            scoreView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
            scoreView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        } else {
            scoreView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            scoreView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        }



        titleView.setText(question.title);
        authorView.setText(question.author);
        dateView.setText(question.time);
        bodyView.setText(question.body.content);


    }


}
