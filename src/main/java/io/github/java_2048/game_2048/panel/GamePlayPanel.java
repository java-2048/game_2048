package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.game.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePlayPanel extends AppPanel {

	private final Game game;

	private final JPanel gamePanel;

	public GamePlayPanel(Integer goal) {
		this.game = new Game(goal);

		setBackground();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		renderHead();

		add(Box.createVerticalStrut(20));

		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(4, 4, 10, 10));
		gamePanel.setBackground(new Color(189, 173, 160));
		gamePanel.setMaximumSize(new Dimension(500, 500));
		gamePanel.setBorder(new LineBorder(new Color(189, 173, 160), 10));
		add(gamePanel);

		renderBoard();
	}

	private void renderHead() {
		JPanel headPanel = new JPanel();
		headPanel.setMaximumSize(new Dimension(700, 80));
		headPanel.setAlignmentY(Box.TOP_ALIGNMENT);
		headPanel.setBackground(getBackgroundColor());
		headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));

		headPanel.add(Box.createHorizontalStrut(20));

		JLabel headLabel = new JLabel(String.valueOf(game.getGoal()));
		headLabel.setFont(MainFont.get().deriveFont(Font.BOLD, 70));
		headLabel.setForeground(new Color(118, 111, 101));
		headLabel.setAlignmentX(Box.LEFT_ALIGNMENT);
		headPanel.add(headLabel);

		headPanel.add(Box.createHorizontalStrut(200));

		JLabel scoreLabel = new JLabel("점수: ");
		scoreLabel.setFont(MainFont.get().deriveFont(Font.BOLD, 40));
		headPanel.add(scoreLabel);

		JTextField scoreFiled = new JTextField(String.valueOf(game.getScore()));
		scoreFiled.setEditable(false);
		scoreFiled.setFont(MainFont.get().deriveFont(Font.PLAIN, 40));
		scoreFiled.setForeground(Color.white);
		scoreFiled.setBackground(new Color(189, 173, 160));
		scoreFiled.setMaximumSize(new Dimension(180, 45));
		scoreFiled.setHorizontalAlignment(JTextField.CENTER);
		scoreFiled.setBorder(null);
		headPanel.add(scoreFiled);

		add(headPanel);
	}

	public void renderBoard() {
		int[][] board = game.getBoard();
		for(int y = 0; y < Game.SIZE; y++){
			for(int x = 0; x < Game.SIZE; x++){
				switch(board[y][x]) {
					default:
						gamePanel.add(createTile("", new Color(205, 192, 180)));
				}
			}
		}
	}

	private JPanel createTile(String text, Color color) {
		JPanel tile = new JPanel();
		tile.setBackground(color);
		tile.add(new JLabel(text));
		return tile;
	}
}
