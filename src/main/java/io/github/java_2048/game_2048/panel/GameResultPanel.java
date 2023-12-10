package io.github.java_2048.game_2048.panel;

import io.github.java_2048.game_2048.App;
import io.github.java_2048.game_2048.font.MainFont;
import io.github.java_2048.game_2048.game.GameResult;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class GameResultPanel extends AppPanel {

	public GameResultPanel(GameResult result){
		// panel 기본 세팅
		setBackground();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// 중앙에 두기 위함
		add(Box.createGlue());

		// 해당 패널에서 사용될 버튼 기본 값
		Dimension btnSize = new Dimension(150, 50);
		Font btnFont = MainFont.get().deriveFont(Font.BOLD, 30);

		int HighScore = getHighScore(result.score(), result.goal());

		if(result.isWin()){
			// 이겼을 때
			JLabel title = new JLabel(" WIN");
			title.setFont(MainFont.get().deriveFont(Font.BOLD, 100));
			title.setMaximumSize(new Dimension(253, 0));
			title.setForeground(new Color(118, 111, 101));
			title.setAlignmentX(CENTER_ALIGNMENT);
			add(title);

			add(Box.createVerticalStrut(70));
		}else{
			// 졌을 때
			JLabel title = new JLabel(" GAME OVER");
			//크기가 너무 크면 이상함
			title.setFont(MainFont.get().deriveFont(Font.BOLD, 80));
			//옆으로 크기를 약간 더 늘림
			title.setMaximumSize(new Dimension(500, 0));
			title.setForeground(new Color(118, 111, 101));
			title.setAlignmentX(CENTER_ALIGNMENT);
			add(title);

			add(Box.createVerticalStrut(50));
		}


		//게임 결과 창
		add(makeResultLabel(result, HighScore));

		add(Box.createVerticalStrut(150));


		// 메인 버튼 생성
		JButton startBtn = new JButton("MAIN");
		startBtn.setMaximumSize(btnSize);
		startBtn.setFont(btnFont);
		startBtn.setForeground(Color.white);
		startBtn.setBackground(getBtnColor());
		startBtn.addActionListener(e -> App.getInstance().changePanel(new MainPanel()));
		startBtn.setAlignmentX(CENTER_ALIGNMENT);
		add(startBtn);

		// 높이 10 간격 추가
		add(Box.createVerticalStrut(50));

	}

	// 게임 결과를 출력할 label를 만들어주는 메소드
	private JLabel makeResultLabel(GameResult result, int highScore){
		JLabel resultlabel = new JLabel();
		//고정폭 글꼴로 바꿔주기 위함. 결과창을 띄울 때 가변폭 글꼴이면 보기 안좋음.
		Font fixedWidthFont = new Font("Monospaced", Font.BOLD, 30);

		resultlabel.setText("<html>" +
				"DIFFICULT&nbsp;&nbsp;" + result.goal() + "<br>" +
				"SCORE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + result.score() + "<br>" +
				"HIGH SCORE&nbsp;&nbsp;" + highScore + "<br>" +
				"MOVE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + result.move() + "<br>" +
				"</html>");


		resultlabel.setFont(fixedWidthFont);
		resultlabel.setMaximumSize(new Dimension(500, 0));
		resultlabel.setForeground(new Color(118, 111, 101));
		resultlabel.setAlignmentX(CENTER_ALIGNMENT);
		resultlabel.setAlignmentY(CENTER_ALIGNMENT);
		resultlabel.setHorizontalAlignment(SwingConstants.CENTER);
		return resultlabel;
	}

	// 서버에 데이터를 보내고 최고 기록을 가져오는 메소드
	private int getHighScore(int score, int difficult){
		Socket socket = null;
		String highscore = "0";

		try{
			socket = new Socket();
			socket.connect(new InetSocketAddress("0.0.0.0", 8000), 2000);
			System.out.println("conneted server");
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out.println(difficult);
			out.println(score);
			System.out.println(score);

			highscore = in.readLine();
			System.out.println(highscore);

		}catch(IOException e){
			System.out.println("[disconnected]");
		}finally{
			try{
				if(socket != null && !socket.isClosed()){
					socket.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			System.out.println("[end of connect]");
		}
		return Integer.parseInt(highscore);
	}
}