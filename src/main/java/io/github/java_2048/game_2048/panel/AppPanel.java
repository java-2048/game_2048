package io.github.java_2048.game_2048.panel;

import javax.swing.JPanel;
import java.awt.Color;

public abstract class AppPanel extends JPanel {

	protected void setBackground(){
		setBackground(new Color(251, 249, 239));
	}

	protected Color getBackgroundColor() {
		return new Color(251, 249, 239);
	}

	protected Color getBtnColor() {
		return new Color(143, 122, 101);
	}
}
