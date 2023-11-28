package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameSettingPanel extends AppPanel{

	// 생성자
	public GameSettingPanel() {
		// 해당 패널 기본 설정
		setBackground();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 해당 패널에서 기본적으로 사용될 폰트
		Font font = MainFont.get().deriveFont(Font.BOLD, 30);

		// 뒤로가기 버튼 추가
		JPanel panel = new JPanel();
		panel.setBackground(getBackgroundColor());
		panel.setMaximumSize(new Dimension(700, 100));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton backBtn = new JButton("< 뒤로가기");
		backBtn.setMaximumSize(new Dimension(130, 50));
		backBtn.setFont(font);
		backBtn.setForeground(getBtnColor());
		backBtn.setBackground(getBtnColor());
		backBtn.addActionListener(this::clickBackButton);
		backBtn.setAlignmentX(LEFT_ALIGNMENT);
		backBtn.setBorderPainted(false);
		backBtn.setOpaque(false);
		panel.add(backBtn);
		add(panel);

		// 높이 100 간격
		add(Box.createVerticalStrut(100));

		// 난이도 선택 라벨
		JLabel label = new JLabel("난이도 선택");
		label.setFont(MainFont.get().deriveFont(Font.BOLD, 50));
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setMaximumSize(new Dimension(253, 50));
		label.setForeground(new Color(118, 111, 101));
		add(label);

		// 높이 30 간격 추가
		add(Box.createVerticalStrut(30));

		// 난이도 선택
		Integer[] choices = {32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
		JComboBox<Integer> dropdown = new JComboBox<>(choices);
		dropdown.setSelectedItem(2048);
		dropdown.setFont(font);
		dropdown.setMaximumSize(new Dimension(200, 50));
		dropdown.setBounds(80, 50, 140, 20);
		dropdown.setBackground(Color.white);
		add(dropdown);

		// 높이 15 간격 추가
		add(Box.createVerticalStrut(15));

		// 시작 버튼
		JButton startBtn = new JButton("시작");
		startBtn.setMaximumSize(new Dimension(150, 50));
		startBtn.setFont(font);
		startBtn.setForeground(Color.white);
		startBtn.setBackground(getBtnColor());
		startBtn.addActionListener((ActionEvent event) -> {
			clickStartButton((Integer) dropdown.getSelectedItem());
		});
		startBtn.setAlignmentX(CENTER_ALIGNMENT);
		add(startBtn);
	}

	// 뒤로가기 버튼 리스너
	private void clickBackButton(ActionEvent event) {
		App.getInstance().changePanel(new MainPanel());
	}

	// 시작 버튼 리스너
	private void clickStartButton(Integer difficulty) {
		App.getInstance().changePanel(new GamePlayPanel(difficulty));
	}
}
