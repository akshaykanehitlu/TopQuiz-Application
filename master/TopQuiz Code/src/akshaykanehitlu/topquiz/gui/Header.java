package akshaykanehitlu.topquiz.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Header extends JPanel {

	private JLabel headerTitle;
	private JLabel headerSubTitle;
	private JLabel headerBackground;
	private JLabel headerLogo;

	public Header() {

		setLayout(null);

		// set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width, 150));

		// load background image
		ImageIcon headerIcon = new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/headerpanel.png"));
		headerBackground = new JLabel(headerIcon);
		headerBackground.setBounds(0, 0, headerIcon.getIconWidth(), headerIcon.getIconHeight());

		ImageIcon icon = new ImageIcon(Utils.getImagesAsBytes("/Resources/Icons&Cliparts/topquiz-logo.png"));
		headerLogo = new JLabel(icon);
		headerLogo.setBounds(20, 20, icon.getIconWidth(), icon.getIconHeight());


		// add title and subtitle to layered pane
		headerTitle = new JLabel("TopQuiz", SwingConstants.CENTER);
		headerTitle.setToolTipText("Top Quiz - Challenge Yourself");
		headerTitle.setForeground(new Color(179, 7, 56));
		headerTitle.setFont(new Font("Helvetica", Font.BOLD, 40));
		headerTitle.setBounds(-20, 40, headerBackground.getWidth()-70, 40);
		headerTitle.setHorizontalAlignment(SwingConstants.CENTER);

		headerSubTitle = new JLabel("by Akshay S Kanehitlu - W1633152", SwingConstants.CENTER);
		headerSubTitle.setForeground(new Color(179, 7, 56));
		headerSubTitle.setFont(new Font("Helvetica", Font.BOLD, 15));
		headerSubTitle.setBounds(-20, 100, headerBackground.getWidth()-70, 20);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, headerBackground.getIcon().getIconWidth(), headerBackground.getIcon().getIconHeight());

		layeredPane.add(headerBackground, new Integer(0));
		layeredPane.add(headerTitle, new Integer(1));
		layeredPane.add(headerSubTitle, new Integer(1));
		layeredPane.add(headerLogo, new Integer(2));

		add(layeredPane);
	}
}