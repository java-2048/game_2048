package io.github.java_2048.game_2048.panel;

import javax.swing.JPanel;
import java.awt.Color;

public abstract class AppPanel extends JPanel implements Base{

	// 기본 배경 색으로 배경지정
	public void setBackground(){
		setBackground(new Color(251, 249, 239));
	}

	// 기본 배경 색을 가져옴
	public Color getBackgroundColor() {
		return new Color(251, 249, 239);
	}

	// 기본 버튼 색을 가져옴
	public Color getBtnColor() {
		return new Color(143, 122, 101);
	}
}
