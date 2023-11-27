package io.github.java_2048.game_2048.font;

import java.awt.*;
import java.io.InputStream;

public class MainFont {

	private static Font font;

	// Font파일을 읽고 Font 를 저장함
	public static void load() {
		InputStream fontStream = MainFont.class.getResourceAsStream("/NanumSquareRoundR.ttf");
		try{
			assert fontStream != null;
			font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
		}catch(Exception e){
			font = new Font("Arial", Font.PLAIN, 12);
		}
	}

	// 저장된 폰트를 가져옴
	public static Font get() {
		return font;
	}
}
