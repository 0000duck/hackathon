package com.keba.keba.showQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.keba.keba.R;
import com.keba.keba.backend.Backend;
import com.keba.keba.data.Question;
import com.keba.keba.util.AvatarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowQuestionActivity extends AppCompatActivity {

    public static final String KEY_QUESTION_ID = "taskId";

    @BindView(R.id.activity_showQuestion_question) ViewGroup questionViewGroup;
    @BindView(R.id.activity_showQuestion_answer) ViewGroup answerViewGroup;

    private ImageView thumpUpView;
    private ImageView thumpDownView;
    private TextView scoreView;
    private TextView titleView;
    private TextView authorView;
    private ImageView avatarView;
    private TextView dateView;
    private TextView bodyView;

    private ImageView answer1_thumpUpView;
    private ImageView answer1_thumpDownView;
    private TextView  answer1_scoreView;
    private TextView  answer1_authorView;
    private ImageView answer1_avatarView;
    private TextView  answer1_dateView;
    private TextView  answer1_bodyView;

    private RelativeLayout answer1_videoLayout;
    private VideoView answer1_video;

    private LinearLayout qrResponseView;

    private boolean firstRun = true;


    private Question question;

    private int deltaThump = 0;
    private int answer1_deltaThump = 0;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showquestion);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        //setDragEdge(SwipeBackLayout.DragEdge.LEFT);

        thumpUpView = (ImageView) questionViewGroup.findViewById(R.id.view_thumb_up);
        thumpDownView = (ImageView) questionViewGroup.findViewById(R.id.view_thumb_down);
        scoreView = (TextView) questionViewGroup.findViewById(R.id.view_score);

        titleView = (TextView) questionViewGroup.findViewById(R.id.view_title);
        authorView = (TextView) questionViewGroup.findViewById(R.id.view_author);
        avatarView = (ImageView) questionViewGroup.findViewById(R.id.view_avatar);
        dateView = (TextView) questionViewGroup.findViewById(R.id.view_date);
        bodyView = (TextView) questionViewGroup.findViewById(R.id.view_body);

        qrResponseView = (LinearLayout) questionViewGroup.findViewById(R.id.view_qr);

        answer1_thumpUpView = (ImageView) answerViewGroup.findViewById(R.id.view_thumb_up);
        answer1_thumpDownView = (ImageView) answerViewGroup.findViewById(R.id.view_thumb_down);
        answer1_scoreView = (TextView) answerViewGroup.findViewById(R.id.view_score);
        answer1_authorView = (TextView) answerViewGroup.findViewById(R.id.view_author);
        answer1_avatarView = (ImageView) answerViewGroup.findViewById(R.id.view_avatar);
        answer1_dateView = (TextView) answerViewGroup.findViewById(R.id.view_date);
        answer1_bodyView = (TextView) answerViewGroup.findViewById(R.id.view_body);
        answer1_videoLayout = (RelativeLayout) answerViewGroup.findViewById(R.id.view_video_bgd);
        answer1_video = (VideoView) answerViewGroup.findViewById(R.id.view_video);

        // get task id
        Intent intent = getIntent();
        question = (Question) intent.getSerializableExtra(KEY_QUESTION_ID);


        if(question.qr.alarm.id.equals("EnergyMeter1.erResponseTimeout")) {
            qrResponseView.setVisibility(View.VISIBLE);
        } else {
            qrResponseView.setVisibility(View.GONE);
        }


        thumpUpView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                voteUp();
            }
        });
        thumpDownView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                voteDown();
            }
        });

        answer1_thumpUpView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                answer1_voteUp();
            }
        });
        answer1_thumpDownView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                answer1_voteDown();
            }
        });


        // update views
        updateQuestion();
        updateAnswer();

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

        // update avatar
        AvatarHelper.addAvatar(avatarView, question.author);


    }


    private void updateAnswer() {

        if(question.answers == null || question.answers.size() == 0) {
            answerViewGroup.setVisibility(View.GONE);
            return;
        }


        answerViewGroup.setVisibility(View.VISIBLE);

        answer1_authorView.setText(question.answers.get(0).author);
        answer1_dateView.setText(question.answers.get(0).time);


        // update avatar
        AvatarHelper.addAvatar(answer1_avatarView, question.answers.get(0).author);


        answer1_scoreView.setText(question.answers.get(0).votes+answer1_deltaThump+"");
        if(question.answers.get(0).votes+answer1_deltaThump == 0) {
            answer1_scoreView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
            answer1_scoreView.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        } else if(question.answers.get(0).votes+answer1_deltaThump < 0) {
            answer1_scoreView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
            answer1_scoreView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        } else {
            answer1_scoreView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            answer1_scoreView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        }



        if(firstRun) {
            if(!question.answers.get(0).body.mime.equals("video")) {
                answer1_videoLayout.setVisibility(View.GONE);
                answer1_bodyView.setText(question.answers.get(0).body.content);
            } else {
                answer1_bodyView.setText("");

                answer1_videoLayout.setVisibility(View.VISIBLE);

                answer1_video.setVideoPath("http://" + Backend.IP + question.answers.get(0).body.content);

                //MediaController mediaController = (MediaController) answerViewGroup.findViewById(R.id.view_mediaController);

                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(answer1_video);
                answer1_video.setMediaController(mediaController);

                answer1_video.start();
            }
            firstRun = false;
        }





    }

    private void answer1_voteUp() {

        if(answer1_deltaThump == 1) {
            answer1_deltaThump = 0;
            answer1_thumpUpView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        } else {
            answer1_deltaThump = 1;
            answer1_thumpUpView.setColorFilter(ContextCompat.getColor(this, R.color.colorGreen));
            answer1_thumpDownView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        }
        updateAnswer();
    }

    private void answer1_voteDown() {

        if(answer1_deltaThump == -1) {
            answer1_deltaThump = 0;
            answer1_thumpDownView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        } else {
            answer1_deltaThump = -1;
            answer1_thumpDownView.setColorFilter(ContextCompat.getColor(this, R.color.colorRed));
            answer1_thumpUpView.setColorFilter(ContextCompat.getColor(this, R.color.colorGrey));
        }
        updateAnswer();
    }


}
