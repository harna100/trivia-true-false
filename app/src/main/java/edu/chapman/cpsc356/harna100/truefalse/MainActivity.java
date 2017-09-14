package edu.chapman.cpsc356.harna100.truefalse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private QuestionModel currQuestion;
	private ArrayList<QuestionModel> questionsToUse;
	private int currQuestionPos;

	private Button btn_true;
	private Button btn_false;
	private Button btn_restart;
	private TextView tv_question;
	private TextView tv_result;
	private LinearLayout ll_quiz;
	private LinearLayout ll_restart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		questionsToUse = new ArrayList<>();
		questionsToUse.add(new QuestionModel("Is it a live band?", true));
		questionsToUse.add(new QuestionModel("Is it a live band? 1", false));
		questionsToUse.add(new QuestionModel("Is it a live band? 2", true));
		questionsToUse.add(new QuestionModel("Is it a live band? 3", false));
		questionsToUse.add(new QuestionModel("Is it a live band? 4", true));

		currQuestionPos = -1;
		setReferences();
		setButtonListeners();
		nextQuestion();

	}

	private void setButtonListeners() {
		setButtonFalseListener();
		setButtonTrueListener();
		setButtonRestartListener();
	}

	private void setButtonTrueListener() {
		btn_true.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkQuestion(currQuestion, true);
			}
		});
	}

	private void setButtonFalseListener() {
		btn_false.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				checkQuestion(currQuestion, false);
			}
		});
	}

	private void setButtonRestartListener() {
		btn_restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				restartQuestions();
			}
		});
	}

	private void restartQuestions() {
		currQuestionPos = -1;
		nextQuestion();
		refillQuestions();
	}

	private void checkQuestion(QuestionModel currQuestion, boolean userChoice) {
		if(userChoice == currQuestion.questionAnswer()){
			tv_result.setText(R.string.right);
		}
		else{
			tv_result.setText(R.string.wrong);
		}
		nextQuestion();
	}

	private void nextQuestion() {
		++currQuestionPos;
		if(currQuestionPos == questionsToUse.size()){
			outOfQuestions();
		}
		else{
			setNextQuestion();
		}
	}

	private void setNextQuestion() {
		currQuestion = questionsToUse.get(currQuestionPos);
		tv_question.setText(currQuestion.getQuestionText());
	}

	private void outOfQuestions() {
		ll_restart.setVisibility(View.VISIBLE);
		disableResponses();
	}


	private void refillQuestions(){
		ll_restart.setVisibility(View.GONE);
		enableResponses();
	}

	private void enableResponses() {
		btn_true.setEnabled(true);
		btn_false.setEnabled(true);
	}

	private void disableResponses() {
		btn_true.setEnabled(false);
		btn_false.setEnabled(false);
	}

	private void setReferences(){
		btn_true = (Button) findViewById(R.id.btn_true);
		btn_false = (Button) findViewById(R.id.btn_false);
		btn_restart = (Button) findViewById(R.id.btn_restart);
		tv_question = (TextView) findViewById(R.id.tv_question);
		tv_result = (TextView) findViewById(R.id.tv_result);
		ll_quiz = (LinearLayout) findViewById(R.id.ll_quiz_layout);
		ll_restart = (LinearLayout) findViewById(R.id.ll_restart_layout);
	}


}
