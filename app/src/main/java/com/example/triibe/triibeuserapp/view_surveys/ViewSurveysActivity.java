package com.example.triibe.triibeuserapp.view_surveys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.triibe.triibeuserapp.R;
import com.example.triibe.triibeuserapp.data.SurveyDetails;
import com.example.triibe.triibeuserapp.takeSurvey.TakeSurveyActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewSurveysActivity extends AppCompatActivity implements ViewSurveysContract.View {

    private static final String TAG = "ViewSurveysActivity";

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.surveys_text_view)
    TextView mSurveysTextView;

    @BindView(R.id.view_surveys_recycler_view)
    RecyclerView mRecyclerView;

    private ViewSurveysContract.UserActionsListener mUserActionsListener;
    private SurveyAdapter mSurveyAdapter;
//    private ArrayList<String> mSurveys;
//    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_surveys);
        ButterKnife.bind(this);

        mUserActionsListener = new ViewSurveysPresenter(this);

        /*
        * Create test user and add a couple of survey ids.
        * */
//        mUser = new User();
//        ArrayList<String> surveyIds = new ArrayList<>();
//        surveyIds.add("1");
//        surveyIds.add("2");
//        mUser.setSurveyIds(surveyIds);

//        mSurveys = mUser.getSurveyIds();


        /*
        * RecyclerView
        * */
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSurveyAdapter = new SurveyAdapter(mUserActionsListener, new ArrayList<SurveyDetails>(0));
        mRecyclerView.setAdapter(mSurveyAdapter);

//        if (adapter.getItemCount() == 0) {
//            mSurveysTextView.setVisibility(View.VISIBLE);
//        }

        // Add test user data
//        addSurveyIdToUser();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mUserActionsListener.loadUser();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if (active) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSurveys(@NonNull ArrayList<SurveyDetails> surveys) {
        mSurveyAdapter.replaceData(surveys);

        if (surveys.size() == 0) {
            mSurveysTextView.setVisibility(View.VISIBLE);
        } else {
            mSurveysTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSurveyDetails(String surveyId) {
        Intent intent = new Intent(this, TakeSurveyActivity.class);
        intent.putExtra("surveyId", surveyId);
        startActivity(intent);
    }
}
