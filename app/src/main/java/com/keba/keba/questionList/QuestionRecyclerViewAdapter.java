package com.keba.keba.questionList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keba.keba.R;
import com.keba.keba.data.Question;
import com.keba.keba.data.Tag;

import java.util.ArrayList;
import java.util.List;


/**
 *  Created by Sparrow on 9/30/2017.
 */
public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionViewHolder>  {

    private Context context;
    private QuestionItemClickListener questionItemClickListener;
    private List<Question> questionList;

    public QuestionRecyclerViewAdapter(Context context, QuestionItemClickListener questionItemClickListener) {
        this.context = context;
        this.questionItemClickListener = questionItemClickListener;
    }


    //////////////////////////////
    // Cursor
    //////////////////////////////

    public void updateList(List<Question> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged(); // updates RecyclerView
    }

    @Override public int getItemCount() {
        return questionList.size();
    }


    //////////////////////////////
    // ViewHolder
    //////////////////////////////
    @Override public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewholder_question, parent, false);
        return new QuestionViewHolder(view, questionItemClickListener);
    }


    /**
     * Fills the Viewholder with the corresponding data.
     * @param holder current ViewHolder
     * @param position position of current ViewHolder
     */
    @Override public void onBindViewHolder(QuestionViewHolder holder, int position) {

        // move to cursor position and check if position exists.
        if (questionList.size() <= position){
            return; // cursor position does not exist
        }

        Question question = questionList.get(position);

        // tag text
        holder.scoreText.setText(String.valueOf(question.votes));
        holder.titleText.setText(question.title);
        holder.dateText.setText(question.time);
        holder.tagsText.setText(question.tags != null ? join(question.tags) : "");

        holder.itemView.setTag((long)position);

    }

    public String join(List<Tag> tags) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            builder.append(tag.key);

            if (i < tags.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

}
