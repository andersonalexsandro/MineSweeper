package br.com.andersonalexsandro.mf.model;

import java.util.Random;

public class Mineseweeper {

    Board board;
    int bombsCreateds;
    GameRules gameRules;
    Difficulty difficulty;

    public Mineseweeper(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.gameRules = new GameRules(difficulty);
        this.board = new Board(gameRules.getDimentios());
        generateBobms();
    }

    public void generateBobms(){
        bombsCreateds = 0;
        int maxBoms = gameRules.getNumberOfBombs();
        Random random = new Random();

        int width = board.getDimentions().getWidth();
        int height = board.getDimentions().getHeight();
        while (bombsCreateds<maxBoms){
            Field fieldLocated = board.getColumns().get(random.nextInt(width)).get(random.nextInt(height));
            if(fieldLocated.hasbomb()) continue;
            else fieldLocated.setBomb(true);
            bombsCreateds++;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getBombsCreateds() {
        return bombsCreateds;
    }

    public void setBombsCreateds(int bombsCreateds) {
        this.bombsCreateds = bombsCreateds;
    }

    public GameRules getGameRules() {
        return gameRules;
    }

    public void setGameRules(GameRules gameRules) {
        this.gameRules = gameRules;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
