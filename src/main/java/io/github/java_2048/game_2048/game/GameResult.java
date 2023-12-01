package io.github.java_2048.game_2048.game;

// 게임 종료 처리에 사용될 record class
public record GameResult(
		boolean isWin, // 승리 여부
		int goal, // 목표 수치
		int score, // 점수
		int move // 이동 횟수
) { }
