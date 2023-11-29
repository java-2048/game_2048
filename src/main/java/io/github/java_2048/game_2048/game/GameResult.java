package io.github.java_2048.game_2048.game;

public record GameResult(
		boolean isWin,
		int goal,
		int score,
		int move
) { }
