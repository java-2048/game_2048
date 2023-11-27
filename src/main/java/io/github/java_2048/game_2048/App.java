package io.github.java_2048.game_2048;

import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.panel.AppPanel;
import io.github.java_2048.game_2048.panel.MainPanel;

import javax.swing.*;

public class App extends JFrame {

	private static App instance;

	public static App getInstance() {
		if(instance == null) {
			instance = new App();
		}
		return instance;
	}

	private App() {
		instance = this;
		setTitle("2048");
		setSize(700, 700);
		setContentPane(new MainPanel());
	}

	public void changePanel(AppPanel panel) {
		getContentPane().removeAll();
		setContentPane(panel);
		revalidate();
		repaint();
	}

	public static void main(String[] args){
		MainFont.load();
		App app = App.getInstance();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setLocationRelativeTo(null);
		app.setVisible(true);
		app.setResizable(false);
	}
}