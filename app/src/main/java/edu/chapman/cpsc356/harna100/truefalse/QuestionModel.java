package edu.chapman.cpsc356.harna100.truefalse;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paul-HP on 9/13/2017.
 */

public class QuestionModel {
	private String questionText;
	private boolean questionAnswer;

	public QuestionModel(String questionText, boolean questionAnswer) {
		this.questionText = questionText;
		this.questionAnswer = questionAnswer;
	}

	public String getQuestionText() {
		return questionText;
	}

	public boolean questionAnswer() {
		return questionAnswer;
	}




	public static ArrayList<QuestionModel> ParseQuestions(String jsonString){
		Gson gson = new Gson();

		ArrayList tempArray = gson.fromJson(jsonString, ArrayList.class);

		ArrayList<QuestionModel> toReturn = new ArrayList<>();
		for (Object o : tempArray) {
			Map<String, Object> curr = (HashMap<String, Object>) o;
			toReturn.add(new QuestionModel((String)curr.get("question"), (boolean)curr.get("answer")));
		}

		return toReturn;
	}
}
