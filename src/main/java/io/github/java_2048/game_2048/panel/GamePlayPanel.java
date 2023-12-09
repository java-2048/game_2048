package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.App;
import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.game.Game;
import io.github.java_2048.game_2048.game.GameResult;
import io.github.java_2048.game_2048.game.MoveDirection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlayPanel extends AppPanel implements KeyListener {

	private final Game game;

	private final JTextField scoreFiled;

	private final JPanel gamePanel = new JPanel();

	public GamePlayPanel(Integer goal) {
		// game 하나 새로 생성
		this.game = new Game(goal);

		setBackground();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		scoreFiled = new JTextField(String.valueOf(game.getScore()));

		renderHead();

		add(Box.createVerticalStrut(20));

		renderBoard();

		// 리스너 등록
		App.getInstance().setFocusable(true);
		App.getInstance().requestFocus();
		App.getInstance().addKeyListener(this);
	}

	// 상단 랜더링
	private void renderHead() {
		JPanel headPanel = new JPanel();
		headPanel.setMaximumSize(new Dimension(700, 80));
		headPanel.setAlignmentY(Box.TOP_ALIGNMENT);
		headPanel.setBackground(getBackgroundColor());
		headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));

		headPanel.add(Box.createHorizontalStrut(20));

		// 목표 점수(난이도) 출력
		JLabel headLabel = new JLabel(String.valueOf(game.getGoal()));
		headLabel.setFont(MainFont.get().deriveFont(Font.BOLD, 70));
		headLabel.setForeground(new Color(118, 111, 101));
		headLabel.setAlignmentX(Box.LEFT_ALIGNMENT);
		headPanel.add(headLabel);

		headPanel.add(Box.createHorizontalStrut(100));

		// 스코어 라벨
		JLabel scoreLabel = new JLabel("SCORE: ");
		scoreLabel.setFont(MainFont.get().deriveFont(Font.BOLD, 40));
		headPanel.add(scoreLabel);

		// 스코어 출력
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

	// 보드 랜더링
	private void renderBoard(){
		gamePanel.setLayout(new GridLayout(4, 4, 10, 10));
		gamePanel.setBackground(new Color(189, 173, 160));
		gamePanel.setMaximumSize(new Dimension(500, 500));
		gamePanel.setBorder(new LineBorder(new Color(189, 173, 160), 10));

		game.getBoard().forEach((gamePanel::add));

		add(gamePanel);
	}

	@Override
	public void keyTyped(KeyEvent e){
		//NOPE
	}

	// 키 눌렀을 때 리스닝
	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP, KeyEvent.VK_W:
				game.moveTiles(MoveDirection.UP);
				break;
			case KeyEvent.VK_DOWN, KeyEvent.VK_S:
				game.moveTiles(MoveDirection.DOWN);
				break;
			case KeyEvent.VK_LEFT, KeyEvent.VK_A:
				game.moveTiles(MoveDirection.LEFT);
				break;
			case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
				game.moveTiles(MoveDirection.RIGHT);
				break;
			default:
				return;// 다른 키값은 그냥 리턴해서 메소드를 종료 시킴
		}
		gamePanel.repaint();
		scoreFiled.setText(String.valueOf(game.getScore()));

		if(game.getResult() != null){
			System.out.println(game.getResult());
			App.getInstance().removeKeyListener(this);
			App.getInstance().changePanel(new GameResultPanel(game.getResult(),game.getGoal()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		//NOPE
	}
}
