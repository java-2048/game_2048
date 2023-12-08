package io.github.java_2048.game_2048.game;

// 게임 종료 처리에 사용될 record class
public record GameResult(
		Boolean isWin, // 승리 여부
		Integer goal, // 목표 수치
		Integer score, // 점수
		Integer move // 이동 횟수
) { }
