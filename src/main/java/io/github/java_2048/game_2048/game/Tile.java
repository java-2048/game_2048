package io.github.java_2048.game_2048.game;

import io.github.java_2048.game_2048.font.MainFont;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

	private final JLabel text = new JLabel("");
	private int num;

	// 기본적인 생성자
	public Tile(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		text.setFont(MainFont.get().deriveFont(Font.PLAIN, 30));
		text.setAlignmentX(Box.CENTER_ALIGNMENT);
		add(text);
		add(Box.createVerticalGlue());
		setNumber(0);
	}

	// 0은 비어있는 타일이란 뜻이기 때문에 0인지 체크
	public boolean isEmpty(){
		return num == 0;
	}

	// 타일 숫자 가져오기
	public int getNumber(){
		return num;
	}

	// 숫자에 따라서 색이 지정되고 지정된 숫자외에 모든 숫자는 0으로 통일 됨
	private void setNumber(int num){
		Color color = switch(num){
			case 0 -> new Color(205, 192, 180);
			case 2 -> new Color(238, 228, 218);
			case 4 -> new Color(237, 224, 200);
			case 8 -> new Color(242, 177, 121);
			case 16 -> new Color(245, 149, 99);
			case 32 -> new Color(246, 124, 95);
			case 64 -> new Color(246, 94, 59);
			case 128 -> new Color(237, 207, 114);
			case 256 -> new Color(237, 204, 97);
			case 512 -> new Color(237, 200, 80);
			case 1024 -> new Color(237, 197, 63);
			case 2048 -> new Color(237, 194, 46);
			case 4096 -> new Color(173, 216, 230);
			case 8192 -> new Color(135, 206, 250);
			case 16384 -> new Color(100, 149, 237);
			case 32768 -> new Color(70, 130, 180);
			default -> Color.red;
		};
		text.setText(num == 0 ? "" : String.valueOf(num));
		this.setBackground(color);
		this.num = num;
	}

	// 타일정보 제거
	public void clear(){
		setNumber(0);
	}

	// 랜덤하게 2 또는 4로 만들어짐
	public void create(){
		create(Math.random() < 0.8 ? 2 : 4);
	}

	// 특정 타일 그대로 만듬
	public void create(Tile tile){
		create(tile.getNumber());
	}

	// 그냥 만들기용
	public void create(int num){
		setNumber(num);
	}

	// 두배 처리
	public void twice(){
		setNumber(num * 2);
	}

	// 비교적 편하게 비교하기 용도
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Tile){
			return ((Tile) obj).getNumber() == getNumber();
		}
		return false;
	}
}
