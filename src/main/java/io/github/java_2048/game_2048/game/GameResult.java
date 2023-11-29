package io.github.java_2048.game_2048.game;

public record GameResult(
		boolean isWin, // 승리 여부
		int goal, // 목표 수치
		int score, // 점수
		int move // 이동 횟수
) { }
