package akshaykanehitlu.topquiz.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class QuizFrame extends JPanel {

	private SubjectSection quizSubjectPanel;
	private QuestionFrame quizQuestionPanel;
	private Summary quizSummaryPanel;
	private String quizSubject;
	public void setQuizSubject(String quizSubject) {
		this.quizSubject = quizSubject;
	}
	private void initValues() {
		quizSubjectPanel = null;
		quizQuestionPanel = null;
		quizSummaryPanel = null;
	}
	public QuizFrame(String subject) {
		initValues();
		setQuizSubject(subject);
		setPreferredSize(new Dimension(1400, 500));

		//score updation panel
		JPanel liveScorePane = new JPanel();
		JLabel lblScore = new JLabel("Score : 0", SwingConstants.LEFT);
		lblScore.setFont(new Font("Helvetica", Font.BOLD, 25));
		lblScore.setForeground(new Color(158, 27, 50));
		liveScorePane.add(lblScore, JComponent.LEFT_ALIGNMENT);

		//subject panel
		quizSubjectPanel = new SubjectSection(subject);

		//questions panel
		quizQuestionPanel = new QuestionFrame(quizSubject);

		// updating score and selecting subjects
		JPanel rightPane = new JPanel();
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
		rightPane.add(liveScorePane, Component.LEFT_ALIGNMENT);

		JPanel lowerPane = new JPanel();
		lowerPane.setLayout(new BoxLayout(lowerPane, BoxLayout.X_AXIS));
		lowerPane.add(quizSubjectPanel,Component.RIGHT_ALIGNMENT);
		lowerPane.add(quizQuestionPanel);
		lowerPane.add(rightPane);

		add(lowerPane);

		//listener for selecting subjects
		quizSubjectPanel.setSubjectListener(new SubjectListener() {
			@Override
			public void subjectChosen(String subject) {
				setQuizSubject(subject);
				quizQuestionPanel.setQuizSubject(subject);
			}
		});

		//listener for updating score
		quizQuestionPanel.setScoreListener(new ScoreListener() {
			@Override
			public void scoreUpdated(int score) {
				lblScore.setText("Score : " + score);
			}
		});

		//listener for ending the quiz
		quizQuestionPanel.setSummaryListener(new SummaryListener() {
			@Override
			public void quizEnded(ScoreSummary summary) {
				try {
					quizSummaryPanel = new Summary(summary);
					add(quizSummaryPanel);
					lowerPane.setVisible(false);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}