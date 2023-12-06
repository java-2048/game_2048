package io.github.java_2048.game_2048_test;

import io.github.java_2048.game_2048.App;
import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.panel.MainPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

	@BeforeAll
	static void init() {
		MainFont.load();
	}


	@DisplayName("메인 패널 테스트")
	@Test
	void testMainPanel() throws InterruptedException{
		App app = new App(new MainPanel());
		app.setLocationRelativeTo(null);
		app.setResizable(false);
		app.setVisible(true);
		Thread.sleep(1000);
		app.dispose();
	}
}
