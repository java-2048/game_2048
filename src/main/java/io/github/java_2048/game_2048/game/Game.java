package io.github.java_2048.game_2048.game;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	//빈 타일들 위치를 가져오기 위함
	private record EmptyTile(int x, int y) { }

	// board 초기화
	private final Tile[][] board = {
			{new Tile(), new Tile(), new Tile(), new Tile()},
			{new Tile(), new Tile(), new Tile(), new Tile()},
			{new Tile(), new Tile(), new Tile(), new Tile()},
			{new Tile(), new Tile(), new Tile(), new Tile()}
	};

	public static final int SIZE = 4;
	
	private final int goal;

	private int move = 0;

	private int score = 0;

	public Game(int goal) {
		this.goal = goal;
		// 2개를 가지고 시작하기 때문에 spawnTile을 2번 호출
		spawnTile();
		spawnTile();
	}

	// 이번 게임에 최종적으로 만들어야 할 타일 숫자를 가져옴
	public int getGoal(){
		return goal;
	}

	// 현재 점수를 가져옴
	public int getScore() {
		return score;
	}

	// 현 게임 보드를 가져옴
	public Tile[][] getBoard(){
		return board;
	}

	// 빈 타일 위치들을 알려줌
	private ArrayList<EmptyTile> getEmptyTiles(){
		ArrayList<EmptyTile> tiles = new ArrayList<>();
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				if(board[y][x].getNumber() == 0){
					tiles.add(new EmptyTile(x, y));
				}
			}
		}
		return tiles;
	}

	// 랜덤하게 타일을 생성함
	public void spawnTile() {
		ArrayList<EmptyTile> emptyTiles = getEmptyTiles();
		if(emptyTiles.isEmpty()) return;
		int index = (new Random()).nextInt(emptyTiles.size());
		EmptyTile tile = emptyTiles.get(index);
		board[tile.y][tile.x].create();
	}

	// 게임이 끝났는지 확인
	public GameResult isFinish(){
		GameResult result = new GameResult(false, goal, score, move);
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				int n = board[i][j].getNumber();
				if(n == goal){
					return new GameResult(true, goal, score, move);
				}
				if(result == null) continue;
				// 움직일 수 있음
				if(n == 0 || (j < SIZE - 1 && n == board[i][j + 1].getNumber()) || (i < SIZE - 1 && n == board[i + 1][j].getNumber())){
					result = null;
				}
			}
		}
		return result;
	}

	// direction 값에 따라 이동하고 이동되면 move 카운트
	public void moveTiles(MoveDirection direction){
		boolean isMoved = switch(direction){
			case UP -> moveUp();
			case DOWN -> moveDown();
			case LEFT -> moveLeft();
			case RIGHT -> moveRight();
		};
		if(isMoved) {
			move++;
			spawnTile();
		}
	}

	// 타일을 위로 이동
	private boolean moveUp(){
		boolean moved = false;

		for(int x = 0; x < SIZE; x++){
			for(int y = 1; y < SIZE; y++){
				if(!board[y][x].isEmpty()){
					int currentY = y;
					while(currentY > 0 && board[currentY - 1][x].isEmpty()){
						// 빈 타일이 있으면 위로 이동
						board[currentY - 1][x].create(board[currentY][x]);
						board[currentY][x].clear();
						currentY--;
						moved = true;
					}

					if(currentY > 0 && board[currentY - 1][x].equals(board[currentY][x])){
						// 위에 있는 타일과 값이 같으면 병합
						board[currentY - 1][x].twice();
						board[currentY][x].clear();
						score += board[currentY - 1][x].getNumber();
						moved = true;
					}
				}
			}
		}
		return moved;
	}

	// 타일을 아래로 이동
	private boolean moveDown(){
		boolean moved = false;

		for(int x = 0; x < SIZE; x++){
			for(int y = SIZE - 2; y >= 0; y--){
				if(!board[y][x].isEmpty()){
					int currentY = y;
					while(currentY < SIZE - 1 && board[currentY + 1][x].isEmpty()){
						// 빈 타일이 있을때 아래로 이동
						board[currentY + 1][x].create(board[currentY][x]);
						board[currentY][x].clear();
						currentY++;
						moved = true;
					}

					if(currentY < SIZE - 1 && board[currentY + 1][x].equals(board[currentY][x])){
						// 아래쪽에 있는 타일과 값이 같으면 병합
						board[currentY + 1][x].twice();
						board[currentY][x].clear();
						score += board[currentY + 1][x].getNumber();
						moved = true;
					}
				}
			}
		}
		return moved;
	}

	// 타일을 왼쪽으로 이동
	private boolean moveLeft(){
		boolean moved = false;

		for(int y = 0; y < SIZE; y++){
			for(int x = 1; x < SIZE; x++){
				if(!board[y][x].isEmpty()){
					int currentX = x;
					while(currentX > 0 && board[y][currentX - 1].isEmpty()){
						// 빈 타일이 있으면 왼쪽으로 이동
						board[y][currentX - 1].create(board[y][currentX]);
						board[y][currentX].clear();
						currentX--;
						moved = true;
					}

					if(currentX > 0 && board[y][currentX - 1].equals(board[y][currentX])){
						// 왼쪽에 있는 타일과 값이 같으면 병합
						board[y][currentX - 1].twice();
						board[y][currentX].clear();
						score += board[y][currentX - 1].getNumber();
						moved = true;
					}
				}
			}
		}
		return moved;
	}

	// 타일을 오른쪽으로 이동
	private boolean moveRight(){
		boolean moved = false;

		for(int y = 0; y < SIZE; y++){
			for(int x = SIZE - 2; x >= 0; x--){
				if(!board[y][x].isEmpty()){
					int currentX = x;
					while(currentX < SIZE - 1 && board[y][currentX + 1].isEmpty()){
						// 빈 타일이 있으면 오른쪽으로 이동
						board[y][currentX + 1].create(board[y][currentX]);
						board[y][currentX].clear();
						currentX++;
						moved = true;
					}

					if(currentX < SIZE - 1 && board[y][currentX + 1].equals(board[y][currentX])){
						// 오른쪽에 있는 타일과 값이 같으면 병합
						board[y][currentX + 1].twice();
						board[y][currentX].clear();
						score += board[y][currentX + 1].getNumber();
						moved = true;
					}
				}
			}
		}
		return moved;
	}
}
