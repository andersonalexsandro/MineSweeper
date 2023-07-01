package br.com.andersonalexsandro.mf.model;

public class GameRules {

    private Difficulty difficulty;
    private Dimentions dimentions;
    private int numberOfBombs;

    public GameRules(Difficulty difficulty) {
        this.difficulty = difficulty;
        setAtributes();
    }

    private void setAtributes(){
        switch (difficulty){
            case EASY -> {
                dimentions = new Dimentions(9, 9);
                numberOfBombs = 10;
            }
            case MEDIUM -> {
                dimentions = new Dimentions(16, 16);
                numberOfBombs = 40;
            }
            case HARD -> {
                dimentions = new Dimentions(30, 30);
                numberOfBombs = 120;
            }
        }
    }

    public Dimentions getDimentios() {
        return dimentions;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }
}
