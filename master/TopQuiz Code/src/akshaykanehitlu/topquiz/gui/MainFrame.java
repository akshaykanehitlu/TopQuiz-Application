package akshaykanehitlu.topquiz.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

//Main frame for the TopQuiz application

public class MainFrame extends JFrame {

	private JLabel mainFrameErrorLabel;
	private static String userName;
	private static Set<String> subjectsChosen;
	private static String subjectChosen;
	private Header mainFrameHeaderPanel;
	private SubjectSection mainFrameSubjectPanel;
	private QuizFrame mainFrameQuizPanel;
	private Container mainFrameContentPanel;

	private void initValues() {
		mainFrameHeaderPanel = null;
		mainFrameSubjectPanel = null;
		mainFrameQuizPanel = null;
		mainFrameContentPanel = null;
		userName = null;
		subjectsChosen = new HashSet<>();
		subjectChosen = null;
	}

	//create the main quiz frame panel
	public MainFrame() {
		super("TOP QUIZ");
		initValues();
		//get content panel
		mainFrameContentPanel = getContentPane();
		mainFrameContentPanel.setBackground(Color.decode("#f4f2f0")); // set background to HEX #f4f2f0

		//FRAME properties
		//set default size and minimum size
		Dimension screenSize = new Dimension(1400, 900);
		setSize(screenSize.width, screenSize.height);
		setMinimumSize(new Dimension(1000, 600));
		setBackground(Color.decode("#f4f2f0")); // set background to HEX #f4f2f0
		//center the frame on screen
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Set default background color for all panels
		UIManager.put("Panel.background", Color.decode("#f4f2f0"));

		mainFrameHeaderPanel = new Header();

		//set layout and add components
		mainFrameContentPanel.setLayout(new BorderLayout());
		mainFrameContentPanel.add(mainFrameHeaderPanel, BorderLayout.NORTH);
		createWelcomePanel();
	}

	/*Creates the welcome panel*/
	private void createWelcomePanel() {
		JPanel mainFrameWelcomePanel = new JPanel();
		mainFrameWelcomePanel.setLayout(new BoxLayout(mainFrameWelcomePanel,BoxLayout.Y_AXIS));

		// Add a label with the welcome message
		JLabel mainFrameWelcomeLabel = new JLabel("Welcome to Top Quiz!", SwingConstants.CENTER);
		mainFrameWelcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		mainFrameWelcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainFrameWelcomePanel.add(mainFrameWelcomeLabel);
		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// create panel for image
		JPanel welcomeImagePanel = new JPanel(new BorderLayout());
		welcomeImagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ImageIcon imageIcon = new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/readyforquiz.jpeg")); // Replace with your image file path
		JLabel imageLabel = new JLabel(imageIcon);
		welcomeImagePanel.add(imageLabel, BorderLayout.CENTER);
		mainFrameWelcomePanel.add(welcomeImagePanel);
		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 30)));

		// create panel for user name input
		JPanel welcomeNamePanel = new JPanel();
		welcomeNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel welcomeNameLabel = new JLabel("Please Enter Your Name:", SwingConstants.CENTER);
		welcomeNameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
		welcomeNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeNamePanel.add(welcomeNameLabel);
		JTextField welcomeNameText = new JTextField();
		welcomeNameText.setFont(new Font("Helvetica", Font.PLAIN, 14));
		welcomeNameText.setMaximumSize(new Dimension(200, 30));
		welcomeNameText.setPreferredSize(new Dimension(200, 30));
		welcomeNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		welcomeNamePanel.add(welcomeNameText);
		mainFrameWelcomePanel.add(welcomeNamePanel);

		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// add a button to enter the quiz
		JPanel enterQuizPanel = new JPanel();
		enterQuizPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton enterQuizButton = new JButton();
		enterQuizButton.setBorderPainted(false);
		enterQuizButton.setContentAreaFilled(false);


		//enterQuizButton.setIcon(new ImageIcon());
		enterQuizButton.setIcon(new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/enter-quiz.png")));
		enterQuizButton.setToolTipText("Enter Quiz");
		enterQuizButton.setPreferredSize(new Dimension(140, 45));
		enterQuizPanel.add(enterQuizButton);
		mainFrameWelcomePanel.add(enterQuizPanel);


		// add subject selection panel
		mainFrameSubjectPanel = new SubjectSection();
		mainFrameSubjectPanel.setSubjectListener(new SubjectListener() {
			@Override
			public void subjectChosen(String subject) {
				// update the subject chosen for quiz
				subjectChosen = subject;
				subjectsChosen.add(subject);
			}
		});

		mainFrameErrorLabel = new JLabel("", SwingConstants.CENTER);

		// add start button
		JPanel quizStartPanel = new JPanel();
		quizStartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton startQuizButton = new JButton();
		startQuizButton.setBorderPainted(false);
		startQuizButton.setContentAreaFilled(false);
		startQuizButton.setIcon(new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/start-quiz.png")));
		startQuizButton.setToolTipText("Start Quiz");
		startQuizButton.setPreferredSize(new Dimension(137, 45));
		quizStartPanel.add(startQuizButton);
		startQuizButton.setVisible(false);

		//Enter Quiz button click event
		enterQuizButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check if username field is empty
				String username = welcomeNameText.getText().trim();
				if (username.isEmpty()) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please enter a username!");
					return;
				}
				// hide enterQuizPanel, show subjectPane
				enterQuizPanel.setVisible(false);
				welcomeImagePanel.setVisible(false);
				mainFrameSubjectPanel.setVisible(true);
				welcomeNamePanel.setVisible(false); // hide the name input panel
				mainFrameWelcomeLabel.setVisible(false);
				startQuizButton.setVisible(true);
			}
		});

		//Start button click event
		startQuizButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userName = welcomeNameText.getText().trim();
				if (userName.isEmpty()) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please enter a username!");
					return;
				}

				if (subjectChosen == null) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please choose a subject!");
					return;
				}
				subjectsChosen.add(subjectChosen);

				// hide the welcome panel and show the quiz panel
				mainFrameContentPanel.remove(mainFrameWelcomePanel);
				mainFrameQuizPanel = new QuizFrame(subjectChosen);
				mainFrameContentPanel.add(mainFrameQuizPanel, BorderLayout.CENTER);
				mainFrameContentPanel.validate();
				mainFrameContentPanel.repaint();
			}
		});

		// add components to welcome panel
		mainFrameWelcomePanel.add(enterQuizPanel);
		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainFrameWelcomePanel.add(mainFrameSubjectPanel);
		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainFrameWelcomePanel.add(quizStartPanel);
		mainFrameWelcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainFrameWelcomePanel.add(mainFrameErrorLabel);
		mainFrameWelcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// initially hide the subject selection panel
		mainFrameSubjectPanel.setVisible(false);

		// add welcome panel to content pane
		mainFrameContentPanel.add(mainFrameWelcomePanel, BorderLayout.CENTER);
	}

	public static String getUserName() {
		return userName;
	}

}