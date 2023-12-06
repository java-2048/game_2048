package io.github.java_2048.game_2048;

import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.panel.AppPanel;
import io.github.java_2048.game_2048.panel.MainPanel;

import javax.swing.*;

public class App extends JFrame {

	// 싱글톤
	private static App instance;

	// 싱글톤
	public static App getInstance() {
		if(instance == null) {
			instance = new App();
		}
		return instance;
	}

	public App(AppPanel panel) {
		setTitle("2048");
		setSize(700, 700);
		setContentPane(panel);
	}

	// 비공개 생성자
	// 싱글톤 패턴을 위해 비공개로 설정함
	private App() {
		this(new MainPanel());
	}

	// 패널 변경시 호출할 메소드
	// 기존 패널 요소들은 삭제하고 panel로 변경함
	public void changePanel(AppPanel panel) {
		getContentPane().removeAll();
		setContentPane(panel);
		revalidate();
		repaint();
	}

	// 실행 시킬 main 메소드
	public static void main(String[] args){
		MainFont.load();
		App app = App.getInstance();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setLocationRelativeTo(null);
		app.setResizable(false);
		app.setVisible(true);
	}
}