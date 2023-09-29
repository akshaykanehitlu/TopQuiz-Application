package akshaykanehitlu.topquiz.gui;

import java.util.HashMap;
import java.util.Map;

public class ScoreSummary {

	private int totalQuestions;
	private int correctAnswers;
	private Map<String, Double> statistics = new HashMap<String, Double>();
	public final int SCORE_VALUE = 10; //10 marks for correct answer
	public int getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public int getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	public Map<String, Double> getStatistics() {
		return statistics;
	}
	public void setStatistics(Map<String, Double> statistics) {
		this.statistics = statistics;
	}
	public int getTotalScore() {
		return correctAnswers * SCORE_VALUE;
	}
}