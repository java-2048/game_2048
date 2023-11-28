package io.github.java_2048.game_2048.game;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	private static class Tile {

		private final int x;

		private final int y;

		public Tile(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static final int SIZE = 4;

	private final int[][] board = new int[SIZE][SIZE];

	private int score = 0;

	private final int goal;

	public Game(int goal) {
		this.goal = goal;
		// 2개를 가지고 시작하기 때문에 spawnTile을 2번 호출
		spawnTile();
		spawnTile();
	}

	public int getGoal(){
		return goal;
	}

	public int getScore() {
		return score;
	}

	public int[][] getBoard(){
		return board;
	}

	private boolean hasEmptyTile() {
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				if(board[y][x] != 0)
					return true;
			}
		}
		return false;
	}

	private ArrayList<Tile> getEmptyTiles() {
		ArrayList<Tile> tiles = new ArrayList<>();
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				if(board[y][x] == 0){
					tiles.add(new Tile(x, y));
				}
			}
		}
		return tiles;
	}

	public void spawnTile() {
		ArrayList<Tile> emptyTiles = getEmptyTiles();
		int index = (new Random()).nextInt(emptyTiles.size());
		Tile tile = emptyTiles.get(index);
		board[tile.y][tile.x] = Math.random() < 0.9 ? 2 : 4;
	}
}
