package br.com.andersonalexsandro.mf.model;

import java.util.Map;
import java.util.Random;

public class MineSweeper {

    Board board;
    int numberOfBombs;
    GameRules gameRules;
    Difficulty difficulty;

    public MineSweeper(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.gameRules = new GameRules(difficulty);
        this.board = new Board(gameRules.getDimentios());
        generateBombs();
    }

    public void generateBombs(){
        generateBombsAlgorithm();
        board.updateBombCounters();
    }

    private void generateBombsAlgorithm(){
        Random random = new Random();
        int width = board.getDimentions().getWidth();
        int height = board.getDimentions().getHeight();
        int maxBombs = gameRules.getNumberOfBombs();
        numberOfBombs = 0;
        while (numberOfBombs < maxBombs){
            Coordinate randomCoordinate = new Coordinate(random.nextInt(width), random.nextInt(height));
            Field randomField = board.getField(randomCoordinate);
            if(randomField.isMine()) continue;
            randomField.spawnMine();
            numberOfBombs++;
        }
    }

    public boolean isGameWon(){
        boolean won = true;
        for(Map.Entry<Coordinate, Field> fields : board.getMap().entrySet()){
            Field field = fields.getValue();
            if(field.isMine() && !field.isFlagged()){
                won = false;
                break;
            }
            if(!field.isMine() && !field.isChecked()){
                won = false;
                break;
            }
        }
        return won;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
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
