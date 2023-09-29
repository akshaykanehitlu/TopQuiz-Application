package akshaykanehitlu.topquiz.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import akshaykanehitlu.topquiz.database.SpellingsBank;
import akshaykanehitlu.topquiz.database.Subjects;
import akshaykanehitlu.topquiz.database.GeographyBank;
import akshaykanehitlu.topquiz.database.QuestionBank;
import akshaykanehitlu.topquiz.database.QuestionType;
import akshaykanehitlu.topquiz.database.AnimalsBank;

//Panel to display questions and answers
public class QuestionFrame extends JPanel {

	private JLabel questionNumberLabel;
	private JTextArea questionLabel;
	private JButton submitButton;
	private JButton nextButton;
	private JButton endButton;
	private JRadioButton selectedRadio;
	private JPanel innerPanel;
	private JPanel buttonPanel;
	private JPanel timerPanel;
	
	private Timer timer;
	private JLabel timeCountdownLabel;
	private JLabel imageQuestionLabel;
	private ScoreSummary summarySection;

	private ActionListener radioButtonEventHandler;
	private ScoreListener scoreListener;
	private SummaryListener summaryListener;

	private static int score=0;
	private static int attempted=0;
	private static int correctAnswers=0;
	private int timeTick=10;
	private boolean isSubmitted=false;

	private akshaykanehitlu.topquiz.database.Question newQuestion;
	protected QuestionBank qb;
	
	private static GeographyBank GeographyBank=new GeographyBank();
	private static AnimalsBank AnimalsBank=new AnimalsBank();
	private static akshaykanehitlu.topquiz.database.SpellingsBank SpellingsBank=new SpellingsBank();
	
	private String quizSubject;

	private void initValues()
	{
		GeographyBank=null;
		AnimalsBank=null;
		SpellingsBank=null;
		newQuestion=null;
		qb=null;
		attempted=0;
		score=0;
		correctAnswers=0;
		isSubmitted=false;
		
	}
	//create the panel
	public QuestionFrame(String quizSubject) {

		initValues();

		setPreferredSize(new Dimension(800, 700));

		//get question bank for the selected topic
		setQuizSubject(quizSubject);
		startQuiz();


		setAlignmentY(JComponent.CENTER_ALIGNMENT);

		//Timer panel
		timerPanel =new JPanel();
		timerPanel.setPreferredSize(new Dimension(800, 70));
		timerPanel.setAlignmentY(CENTER_ALIGNMENT);

		timeCountdownLabel =new JLabel();
		timeCountdownLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
		timeCountdownLabel.setAlignmentX(CENTER_ALIGNMENT);
		setTimerLabel(10);
		timerPanel.add(timeCountdownLabel);

		add(timerPanel,JComponent.CENTER_ALIGNMENT);
		innerPanel =new JPanel();
		createQuestionAndOptions();
		add(innerPanel, JComponent.CENTER_ALIGNMENT);
		createQuestionPanelButtons();

		startTimerAnimation();//start timer
	}

	public void setScoreListener(ScoreListener scoreListener) {
		this.scoreListener = scoreListener;
	}
	public void setSummaryListener(SummaryListener summaryListener) {
		this.summaryListener = summaryListener;
	}
	public void setQuizSubject(String quizSubject) {
		this.quizSubject = quizSubject;
	}

	public void setSummary(ScoreSummary summary) {
		this.summarySection = summary;
	}

	private void createQuestionAndOptions()
	{
		newQuestion=qb.getRandomQuestion();
		if(newQuestion==null)
		{
			isSubmitted=false;
			JOptionPane.showMessageDialog(null,
					"You have answered all the questions in this subject!",
					"TopQuiz",
					JOptionPane.INFORMATION_MESSAGE);

			endQuiz();
		}
		else{
			innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
			innerPanel.setSize(750, 400);

			JPanel numPane=new JPanel();
			questionNumberLabel =new JLabel("<html>Question "+(attempted)+" of 20<br/><br/></html>");
			questionNumberLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
			numPane.add(questionNumberLabel,JComponent.LEFT_ALIGNMENT);

			JPanel qTextPane=new JPanel();
			qTextPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			questionLabel =new JTextArea();
			questionLabel.setLineWrap(true);
			questionLabel.setWrapStyleWord(true);
			questionLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
			questionLabel.setText(newQuestion.getQuestionText());//question
			questionLabel.setEditable(false);
			questionLabel.setOpaque(false);
			questionLabel.setSize(750, 100);
			qTextPane.add(questionLabel);

			innerPanel.add(numPane);
			innerPanel.add(qTextPane);

			if(newQuestion.getQuestionType()==QuestionType.IMAGEQUESTION)
			{
				//set image for question
				JPanel imageQuestionPanel=new JPanel();
				imageQuestionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				ImageIcon questionIcon=new ImageIcon(Utils.getImagesAsBytes(newQuestion.getQuestionImage()));
				imageQuestionLabel =new JLabel(questionIcon,SwingConstants.CENTER);
				imageQuestionLabel.setPreferredSize(new Dimension(300, 250));

				imageQuestionPanel.add(imageQuestionLabel);
				innerPanel.add(imageQuestionPanel,Component.CENTER_ALIGNMENT);
			}
			ButtonGroup optionGroup=new ButtonGroup();
			radioButtonEventHandler = new RadioButtonEventHandler();

			JPanel optionPanel=new JPanel();
			optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

			optionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			for(String option :newQuestion.getOptions())
			{
				JRadioButton newOption=new JRadioButton(option);
				newOption.setActionCommand(option);
				newOption.addActionListener(radioButtonEventHandler);
				optionGroup.add(newOption);
				newOption.setOpaque(true);
				newOption.setBackground(Color.decode("#f4f2f0"));
				optionPanel.add(newOption);
			}
			innerPanel.add(optionPanel,Component.LEFT_ALIGNMENT);
		}
	}
	
	private void createQuestionPanelButtons() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		// button to submit the answer
		ImageIcon submitButtonIcon = new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/submit.png"));
		submitButton = new JButton(submitButtonIcon);
		submitButton.setContentAreaFilled(false);
		submitButton.setBorderPainted(false);
		submitButton.setToolTipText("Submit Quiz");
		submitButton.addActionListener(e -> {
			isSubmitted = true;
			if (selectedRadio != null) {
				stopTimerAnimation();

				submitButton.setVisible(false);
				nextButton.setVisible(true);

				if (selectedRadio.getActionCommand().equals(newQuestion.getAnswer())) {
					selectedRadio.setSize(selectedRadio.getParent().getWidth(), selectedRadio.getHeight());
					selectedRadio.setBackground(new Color(0x737b4c));
					score += 10;
					correctAnswers++;
					qb.incrementCorrectAnswers();

					scoreListener.scoreUpdated(score);
				} else {
					selectedRadio.setSize(selectedRadio.getParent().getWidth(), selectedRadio.getHeight());
					selectedRadio.setBackground(new Color(0xb30738));
				}

				// If 20 questions attempted, end quiz.
				if (attempted == 20) {
					JOptionPane.showMessageDialog(null, "Congratulations! You have completed the quiz.", "Quiz completed", JOptionPane.INFORMATION_MESSAGE);
					// Show summary panel
					endQuiz();
				}
			} else {
				isSubmitted = false;
				// print message to select an answer
				JOptionPane.showMessageDialog(null, "Kindly answer the question to proceed!", "Top Quiz - Alert", JOptionPane.ERROR_MESSAGE);
			}
		});

		// button to go to next question
		ImageIcon nextButtonIcon = new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/next.png"));
		nextButton = new JButton(nextButtonIcon);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setVisible(false);
		nextButton.addActionListener(e -> {
			isSubmitted = false;
			// end quiz if 20 questions are attempted
			if (attempted == 20) {
				JOptionPane.showMessageDialog(null, "Congratulations! You completed the quiz.", "Quiz over", JOptionPane.INFORMATION_MESSAGE);
				// show summary panel
				endQuiz();
			} else {
				attempted++;
				setQuestionBank();
				innerPanel.removeAll();
				selectedRadio = null;
				createQuestionAndOptions();
				setTimerLabel(10);
				submitButton.setVisible(true);
				nextButton.setVisible(false);

				Thread worker = new Thread(() -> {
					SwingUtilities.invokeLater(this::startTimerAnimation);
				});
				worker.start();
			}
		});

		buttonPanel.add(submitButton);
		buttonPanel.add(nextButton);

		JPanel endButtonPanel = new JPanel();
		endButtonPanel.setLayout(new BoxLayout(endButtonPanel, BoxLayout.X_AXIS));

		// End button to end quiz and display summary pane
		ImageIcon endButtonIcon = new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/end-quiz.png"));
		endButton = new JButton(endButtonIcon);
		endButton.setToolTipText("End Quiz");
		endButton.setContentAreaFilled(false);
		endButton.setBorderPainted(false);
		endButton.addActionListener(e -> {
			if (isSubmitted) {
				endQuiz();
			} else {
				JOptionPane.showMessageDialog(null, "Kindly answer the question before you submit!", "TopQuiz", JOptionPane.ERROR_MESSAGE);
			}
		});
		endButtonPanel.add(endButton);

		add(buttonPanel, JComponent.CENTER_ALIGNMENT);
		add(endButtonPanel, JComponent.RIGHT_ALIGNMENT);
	}
	
	//Implements ActionListener for RadioButtons
	class RadioButtonEventHandler implements ActionListener
	   {  
		//Saves the button that was selected
	      public void actionPerformed(ActionEvent event){
	    	  selectedRadio =(JRadioButton)event.getSource();
	      }
	   }
	
	
	//Set question bank for the selected subject
	private void setQuestionBank()
	{
		switch (quizSubject) {
		case "Geography":
			qb=GeographyBank;
			GeographyBank.incrementAttemptedQuestions();
			break;
		case "Animals":
			qb=AnimalsBank;
			AnimalsBank.incrementAttemptedQuestions();
			break;
		case "Spellings":
			qb=SpellingsBank;
			SpellingsBank.incrementAttemptedQuestions();
			break;
		default:
			break;
		}
	}
	
	//Set variables and initialize question bank
	private void startQuiz()
	{
		if(attempted==0)
		{
			attempted++;
			GeographyBank=new GeographyBank();
			AnimalsBank=new AnimalsBank();
			SpellingsBank=new SpellingsBank();
			
			setQuestionBank();
		}
	}
	
	//set style for timer label
	private void setTimerLabel(int remaining)
	{
		timeCountdownLabel.setForeground(new Color(115, 123, 76));
		timeCountdownLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
		timeCountdownLabel.setText("Time remaining: " + String.valueOf(remaining));
		timeCountdownLabel.setHorizontalAlignment(JLabel.LEFT);
	}
	
	//ActionListener for timer
	ActionListener timerListener = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
           setTimerLabel(timeTick);
           if(timeTick<6)
           {
        	   timeCountdownLabel.setForeground(new Color(134, 38, 51));
           }
           if(timeTick==0)
           {
        	   stopTimerAnimation();
        	   timeCountdownLabel.setText("Sorry! Your time is up.");
        	   timeCountdownLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
			   timeCountdownLabel.setForeground(new Color(134, 38, 51));

			   isSubmitted=true;
        	   submitButton.setVisible(false);
   		  	   nextButton.setVisible(true);
           }
           timeTick--;
        }
     };
     
    //start the timer animation
	void startTimerAnimation() {
        if (timer == null) {
           timer = new Timer(1000, timerListener);
           timer.start();  // start timer
           
        }
    }
	
	//Stop timer animation
    void stopTimerAnimation() {
    	timeTick=10;//reset timer

       if (timer != null) {
          timer.stop();   //stop timer
          timer = null;
       }
    }
    
    //End the quiz - calculate the results and show summary panel
    private void endQuiz()
    {
    	//calculate the total percentage score for each subject
    	Map<String,Double> percentScore=new HashMap<String,Double>();
    	if(GeographyBank.getAttemptedQuestions()>0)
    	{
    		percentScore.put(Subjects.GEOGRAPHY.toString(), GeographyBank.getScorePercentage());
    	}
    	if(AnimalsBank.getAttemptedQuestions()>0)
    	{
    		percentScore.put(Subjects.ANIMALS.toString(),AnimalsBank.getScorePercentage());
    	}
    	if(SpellingsBank.getAttemptedQuestions()>0)
    	{
    		percentScore.put(Subjects.SPELLINGS.toString(),SpellingsBank.getScorePercentage());
    	}
    	
    	//show summary panel
    	ScoreSummary scoreSummary=new ScoreSummary();
    	scoreSummary.setTotalQuestions(attempted);
    	scoreSummary.setCorrectAnswers(correctAnswers);
    	scoreSummary.setStatistics(percentScore);
		this.setSummary(scoreSummary);
    	//invoke listener method
    	summaryListener.quizEnded(scoreSummary);

		// create a new file for the user
		String fileName = MainFrame.getUserName();
		try {
			FileWriter writer = new FileWriter(fileName + ".txt");
			PrintWriter out = new PrintWriter(writer);

			out.println("User Name: " + fileName);

			// add the current date and time to the file
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = now.format(formatter);
			out.println("Time: " + formattedDateTime);
			out.println("");
			out.print("Subjects Chosen: ");

			int length = summarySection.getStatistics().keySet().size();
			int count = 1;
			for (String topic : summarySection.getStatistics().keySet()) {
				out.print(topic);
				if(count < length) {
					out.print(", ");
					count ++;
				}
			}
			out.println("");
			out.println("");
			out.println("Total Questions Attempted: " + summarySection.getTotalQuestions());
			out.println("Total Correct Answers: " + summarySection.getCorrectAnswers());
			out.println("Total Score: " + summarySection.getTotalScore());

			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }
}


