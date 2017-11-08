package com.keba.keba.questionList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keba.keba.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Created by Sparrow on 10/18/2017.
 */
public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.viewholder_question_score) TextView scoreText;
    @BindView(R.id.viewholder_question_title) TextView titleText;
    @BindView(R.id.viewholder_question_date) TextView dateText;
    @BindView(R.id.viewholder_question_tags) TextView tagsText;
    @BindView(R.id.viewholder_question_author) TextView authorText;
    @BindView(R.id.viewholder_question_avatar) ImageView avatarView;

    private QuestionItemClickListener questionItemClickListener;

    public QuestionViewHolder(View itemView, QuestionItemClickListener questionItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.questionItemClickListener = questionItemClickListener;
    }

    @Override
    public void onClick(View v) {
        questionItemClickListener.onItemClick((long)v.getTag());
    }

}