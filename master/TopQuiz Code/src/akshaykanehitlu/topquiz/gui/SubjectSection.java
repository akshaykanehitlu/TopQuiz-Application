package akshaykanehitlu.topquiz.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class SubjectSection extends JPanel {
	private JRadioButton geographyRadioButton;
	private JRadioButton animalsRadioButton;
	private JRadioButton spellingsRadioButton;
	private ButtonGroup subjectGroupRadioButton;
	private RadioButtonEventHandler radioButtonEventHandler;
	private SubjectListener subjectListener;

	public void setSubjectListener(SubjectListener subjectListener) {
		this.subjectListener = subjectListener;
	}

	public SubjectSection() {
		radioButtonEventHandler = new RadioButtonEventHandler();
		geographyRadioButton = new JRadioButton("Geography");
		geographyRadioButton.setForeground(new Color(255, 255, 255));
		geographyRadioButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		geographyRadioButton.setOpaque(false);
		geographyRadioButton.setFocusPainted(false);
		geographyRadioButton.setBorderPainted(false);
		geographyRadioButton.setContentAreaFilled(false);
		geographyRadioButton.setActionCommand("Geography");
		geographyRadioButton.addActionListener(radioButtonEventHandler);

		animalsRadioButton = new JRadioButton("Animals");
		animalsRadioButton.setForeground(new Color(255, 255, 255));
		animalsRadioButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		animalsRadioButton.setOpaque(false);
		animalsRadioButton.setFocusPainted(false);
		animalsRadioButton.setBorderPainted(false);
		animalsRadioButton.setContentAreaFilled(false);
		animalsRadioButton.setActionCommand("Animals");
		animalsRadioButton.addActionListener(radioButtonEventHandler);

		spellingsRadioButton = new JRadioButton("Spellings");
		spellingsRadioButton.setForeground(new Color(255, 255, 255));
		spellingsRadioButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		spellingsRadioButton.setOpaque(false);
		spellingsRadioButton.setFocusPainted(false);
		spellingsRadioButton.setBorderPainted(false);
		spellingsRadioButton.setContentAreaFilled(false);
		spellingsRadioButton.setActionCommand("Spellings");
		spellingsRadioButton.addActionListener(radioButtonEventHandler);

		subjectGroupRadioButton = new ButtonGroup();
		subjectGroupRadioButton.add(geographyRadioButton);
		subjectGroupRadioButton.add(animalsRadioButton);
		subjectGroupRadioButton.add(spellingsRadioButton);

		JLabel welcomeLabel = new JLabel("<html><div style='text-align:center;'><u>Select a subject</u></div></html>", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		welcomeLabel.setForeground(new Color(234, 170, 0));

		JPanel subjectOutlinePanel = new JPanel();
		subjectOutlinePanel.setLayout(new GridBagLayout());
		subjectOutlinePanel.setPreferredSize(new Dimension(300, 250));
		subjectOutlinePanel.setBackground(new Color(134, 38, 51));

		geographyRadioButton.setForeground(new Color(234, 170, 0));
		geographyRadioButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		geographyRadioButton.setActionCommand("Geography");

		animalsRadioButton.setForeground(new Color(234, 170, 0));
		animalsRadioButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		animalsRadioButton.setActionCommand("Animals");

		spellingsRadioButton.setForeground(new Color(234, 170, 0));
		spellingsRadioButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		spellingsRadioButton.setActionCommand("Spellings");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 0, 5, 0);
		subjectOutlinePanel.add(welcomeLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 52, 5, 0);
		subjectOutlinePanel.add(geographyRadioButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 52, 5, 0);
		subjectOutlinePanel.add(animalsRadioButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 52, 10, 0);
		subjectOutlinePanel.add(spellingsRadioButton, gbc);

		add(subjectOutlinePanel);
	}

	public SubjectSection(String subject)
	{
		this();
		switch(subject)
		{
		case "Geography":
			geographyRadioButton.setSelected(true);
			break;
		case "Animals":
			animalsRadioButton.setSelected(true);
			break;
		case "Spellings":
			spellingsRadioButton.setSelected(true);
			break;
		default:
			break;
		}
	}

	 class RadioButtonEventHandler implements ActionListener
	   {  
	      public void actionPerformed(ActionEvent event)
	      {
	    	  JRadioButton rdo=(JRadioButton)event.getSource();
			  subjectListener.subjectChosen(rdo.getActionCommand());
	      }
	   }
}