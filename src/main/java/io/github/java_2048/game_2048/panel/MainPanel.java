package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.App;
import io.github.java_2048.game_2048.font.MainFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPanel extends AppPanel{

	public MainPanel(){
		setBackground();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createGlue());

		Dimension btnSize = new Dimension(150, 50);
		Font btnFont = MainFont.get().deriveFont(Font.BOLD, 30);

		JLabel title = new JLabel("2048");
		title.setFont(MainFont.get().deriveFont(Font.BOLD, 100));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setMaximumSize(new Dimension(253, 0));
		title.setForeground(new Color(118, 111, 101));
		add(title);

		add(Box.createVerticalStrut(30));

		JButton startBtn = new JButton("시작");
		startBtn.setMaximumSize(btnSize);
		startBtn.setFont(btnFont);
		startBtn.setForeground(Color.white);
		startBtn.setBackground(getBtnColor());
		startBtn.addActionListener(this::clickStartButton);
		startBtn.setAlignmentX(CENTER_ALIGNMENT);
		add(startBtn);

		add(Box.createVerticalStrut(10));

		JButton helpBtn = new JButton("도움말");
		helpBtn.setMaximumSize(btnSize);
		helpBtn.setFont(btnFont);
		helpBtn.setForeground(Color.white);
		helpBtn.setBackground(getBtnColor());
		helpBtn.addActionListener(this::clickHelpButton);
		helpBtn.setAlignmentX(CENTER_ALIGNMENT);
		add(helpBtn);

		add(Box.createVerticalGlue());
	}

	private void clickStartButton(ActionEvent event) {
		App.getInstance().changePanel(new GameSettingPanel());
	}

	private void clickHelpButton(ActionEvent event) {
		App.getInstance().changePanel(new HelpPanel());
	}
}
