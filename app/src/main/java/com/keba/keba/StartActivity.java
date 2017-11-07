package com.keba.keba;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.keba.keba.addQuestion.AddQuestionActivity;
import com.keba.keba.questionList.Question;
import com.keba.keba.questionList.QuestionItemClickListener;
import com.keba.keba.questionList.QuestionRecyclerViewAdapter;
import com.keba.keba.showQuestion.ShowQuestionActivity;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        };
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.activity_start_add)
    public void onClickAddButton(View view) {
        Intent intent = new Intent(this, AddQuestionActivity.class);
        startActivity(intent);
    }


    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (recyclerViewLayoutManager != null) {
            outState.putParcelable(KEY_RECYCLER_VIEW_STATE, recyclerViewLayoutManager.onSaveInstanceState());
        }
    }

    @Override public void onItemClick(long id) {
        Intent intent = new Intent(this, ShowQuestionActivity.class);
        intent.putExtra(ShowQuestionActivity.KEY_QUESTION_ID, 0);
        startActivity(intent);
    }
}
