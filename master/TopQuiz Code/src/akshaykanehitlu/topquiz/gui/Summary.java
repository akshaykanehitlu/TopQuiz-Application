package akshaykanehitlu.topquiz.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import java.util.HashMap;

import javax.swing.JPanel;

public class Summary extends JPanel {

	//member controls
	private JLabel totalScoreLabel;
	private JLabel questionsAttemptedLabel;
	private JLabel correctAnswersLabel;
	private JLabel thankYouLabel;
	private JLabel infoLabel;

	private Map < String, Double > statistics = new HashMap < String, Double > (); //<SUBJECT,SCORE_PERCENT>

	public Summary(ScoreSummary summary) throws IOException {
		statistics = summary.getStatistics();

		setLayout(new BorderLayout());
		statistics = summary.getStatistics();

		setLayout(new BorderLayout());

		thankYouLabel = new JLabel("<html>Thank You for taking the quiz! Here is your score summary.<br/></html>", SwingConstants.CENTER);
		totalScoreLabel = new JLabel("Total Score: " + summary.getTotalScore());
		questionsAttemptedLabel = new JLabel("Questions Attempted: " + summary.getTotalQuestions());
		correctAnswersLabel = new JLabel("Correct Answers: " + summary.getCorrectAnswers());
		infoLabel = new JLabel();

		totalScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionsAttemptedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		correctAnswersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		thankYouLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		thankYouLabel.setForeground(new Color(134, 38, 51));
		totalScoreLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		totalScoreLabel.setForeground(new Color(3, 59, 76));
		questionsAttemptedLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		questionsAttemptedLabel.setForeground(new Color(3, 59, 76));
		correctAnswersLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		correctAnswersLabel.setForeground(new Color(3, 59, 76));
		infoLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		infoLabel.setForeground(new Color(134, 38, 51));

		if (summary.getTotalScore() == 0) {
			infoLabel.setText("<html>Sorry! The score is not sufficient.<br/>Please try again!</html>");
			infoLabel.setForeground(new Color(179, 7, 56));
		} else {
			infoLabel.setText("<html>Congratulations!</html>");
			infoLabel.setForeground(new Color(134, 38, 51));
		}
		JPanel completeScorePanel = new JPanel();
		completeScorePanel.setLayout(new BoxLayout(completeScorePanel, BoxLayout.Y_AXIS));
		completeScorePanel.setPreferredSize(new Dimension(800, 120));

		completeScorePanel.add(thankYouLabel);
		completeScorePanel.add(totalScoreLabel);
		completeScorePanel.add(questionsAttemptedLabel);
		completeScorePanel.add(correctAnswersLabel);
		completeScorePanel.add(infoLabel);

		//bar chart panel
		JPanel barGraphPanel = new JPanel(new BorderLayout());
		barGraphPanel.setPreferredSize(new Dimension(800, 400));

		final JFXPanel fxPanel = new JFXPanel();
		barGraphPanel.add(fxPanel, BorderLayout.CENTER);

		Platform.runLater(() -> {
			CategoryAxis xAxis = new CategoryAxis();
			NumberAxis yAxis = new NumberAxis();
			BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

			chart.setTitle("Performance chart");
			xAxis.setLabel("Topics");
			yAxis.setLabel("Score Percentage");

			XYChart.Series<String, Number> series = new XYChart.Series<>();
			for (String topic : summary.getStatistics().keySet()) {
				double score = summary.getStatistics().get(topic);
				XYChart.Data<String, Number> data = new XYChart.Data<>(topic, score);
				series.getData().add(data);
			}
			chart.getData().add(series);

			// Set bar color
			String barColor = "#811e2d";
			for (XYChart.Series<String, Number> s : chart.getData()) {
				for (XYChart.Data<String, Number> d : s.getData()) {
					Node bar = d.getNode();
					bar.setStyle("-fx-bar-fill: " + barColor + ";");
				}
			}

			Scene scene = new Scene(chart, 800, 400);
			fxPanel.setScene(scene);
			barGraphPanel.revalidate();
			barGraphPanel.repaint();
		});



		//add to layout
		add(completeScorePanel, BorderLayout.NORTH);
		add(barGraphPanel, BorderLayout.CENTER);

		//replay quiz button
		JPanel replayPanel = new JPanel();
		replayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		//button to replay quiz
		JButton replayButton = new JButton();
		replayButton.setIcon(new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/replay.png")));
		replayButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		replayButton.setHorizontalTextPosition(SwingConstants.CENTER);
		replayButton.setBorderPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setFont(new Font("Helvetica", Font.BOLD, 15));
		replayButton.setForeground(new Color(134, 38, 51));
		replayButton.addActionListener(e -> {
			Frame[] frames = JFrame.getFrames();
			for (Frame frame : frames) {
				frame.dispose();
			}
			new MainFrame();
		});
		replayPanel.add(replayButton);

		// exit button
		JButton exitQuizButton = new JButton();

		Image exitbuttonimage = ImageIO.read(Utils.getImagesAsStream("/Resources/Icons&Cliparts/exit.png"));
		exitQuizButton.setIcon(new ImageIcon(exitbuttonimage));
		exitQuizButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		exitQuizButton.setHorizontalTextPosition(SwingConstants.CENTER);
		exitQuizButton.setBorderPainted(false);
		exitQuizButton.setContentAreaFilled(false);
		exitQuizButton.setFont(new Font("Helvetica", Font.BOLD, 15));
		exitQuizButton.setForeground(new Color(134, 38, 51));
		exitQuizButton.addActionListener(e -> {
			System.exit(0); //end quiz application
		});
		replayPanel.add(exitQuizButton);

		// add replayPanel to layout
		add(replayPanel, BorderLayout.SOUTH);
	}
}