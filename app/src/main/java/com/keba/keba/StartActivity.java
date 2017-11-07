package com.keba.keba;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.keba.keba.AddQuestion.AddQuestionActivity;
import com.keba.keba.QuestionList.Question;
import com.keba.keba.QuestionList.QuestionItemClickListener;
import com.keba.keba.QuestionList.QuestionRecyclerViewAdapter;
import com.keba.keba.Video.AddVideoActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity implements QuestionItemClickListener {

    private static final String KEY_RECYCLER_VIEW_STATE = "recyclerViewState";

    private QuestionRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Question question = new Question();
        question.date = "26.9.2017";
        question.score = 15;
        question.tags = "Keba, Linz";
        question.title = "Will we win this contest?";

        ArrayList<Question> list = new ArrayList<>();
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);
        list.add(question);

        // set up ListAdapter and RecyclerView
        recyclerViewAdapter = new QuestionRecyclerViewAdapter(this,this);
        recyclerViewAdapter.updateList(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_start_listView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = recyclerView.getLayoutManager();


        // restore last position of the recycler view list
        Parcelable layoutManagerState = (savedInstanceState != null ? savedInstanceState.getParcelable(KEY_RECYCLER_VIEW_STATE) : null);
        if(layoutManagerState != null){
            recyclerViewLayoutManager.onRestoreInstanceState(layoutManagerState);
        }

    }

    @OnClick(R.id.activity_start_add)
    public void onClickAddButton(View view) {
        Intent intent = new Intent(this, AddVideoActivity.class);
        startActivity(intent);
    }


    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (recyclerViewLayoutManager != null) {
            outState.putParcelable(KEY_RECYCLER_VIEW_STATE, recyclerViewLayoutManager.onSaveInstanceState());
        }
    }

    @Override public void onItemClick(long id) {

    }
}
