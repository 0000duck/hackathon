package com.keba.keba.questionList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keba.keba.R;

import java.util.ArrayList;


/**
 *  Created by Sparrow on 9/30/2017.
 */
public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionViewHolder>  {

    private Context context;
    private QuestionItemClickListener questionItemClickListener;
    private ArrayList<Question> questionList;

    public QuestionRecyclerViewAdapter(Context context, QuestionItemClickListener questionItemClickListener) {
        this.context = context;
        this.questionItemClickListener = questionItemClickListener;
    }


    //////////////////////////////
    // Cursor
    //////////////////////////////

    public void updateList(ArrayList<Question> questionList) {
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
        holder.scoreText.setText(String.valueOf(question.score));
        holder.titleText.setText(question.title);
        holder.dateText.setText(question.date);
        holder.tagsText.setText(question.tags);

        holder.itemView.setTag((long)position);

    }



}
