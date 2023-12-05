package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.App;
import io.github.java_2048.game_2048.font.MainFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HelpPanel extends AppPanel {

	// 생성자
	public HelpPanel() {
		// panel 기본 세팅
		setBackground();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 돌아가기 버튼 추가
		JPanel panel = new JPanel();
		panel.setBackground(getBackgroundColor());
		Font btnFont = MainFont.get().deriveFont(Font.BOLD, 30);
		panel.setMaximumSize(new Dimension(700, 100));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton backButton = new JButton("< BACK");
		backButton.setMaximumSize(new Dimension(130, 50));
		backButton.setFont(btnFont);
		backButton.setForeground(getBtnColor());
		backButton.setBackground(getBtnColor());
		backButton.addActionListener(this::clickBackButton);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButton.setBorderPainted(false);
		backButton.setOpaque(false);
		panel.add(backButton);
		add(panel);

		// 규칙 안내 라벨
		JLabel ruleLabel = new JLabel("<html><div align='center'> GAME RULES<br><br><br>"
				+ "- You can move tiles using the WASD keys or arrow keys.<br><br>"
				+ "&nbsp;- When tiles with the same number touch, they merge into one.<br><br>"
				+ "&nbsp;&nbsp;- You lose the game if the board is full and no more moves are possible.<br><br>"
				+ "- Set your desired goal and start playing.</div></html>");
		ruleLabel.setFont(MainFont.get().deriveFont(Font.BOLD, 24));
		ruleLabel.setAlignmentX(CENTER_ALIGNMENT); // 텍스트 가운데 정렬
		ruleLabel.setForeground(new Color(118, 111, 101));

		// 규칙 안내 라벨 아래에 공간 추가
		add(Box.createVerticalStrut(60));
		add(ruleLabel);

		// 높이 30 간격 추가
		add(Box.createVerticalStrut(30));

		// 중앙 정렬을 위한 높이 조절
		add(Box.createVerticalGlue());
	}

	// 돌아가기 버튼 리스너
	private void clickBackButton(ActionEvent event) {
		App.getInstance().changePanel(new MainPanel());
	}
}
