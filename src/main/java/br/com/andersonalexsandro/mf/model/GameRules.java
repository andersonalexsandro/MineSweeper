package br.com.andersonalexsandro.mf.model;

public class GameRules {

    private Difficulty difficulty;
    private Dimentions dimentions;
    private int numberOfBombs;
    private static String mineIcon = "☣";
    private static String flagIcon = "⚐";
    private static String notCheckedIcon = "■";
    private static String defultIcon = "-";

    public GameRules(Difficulty difficulty) {
        this.difficulty = difficulty;
        setAtributes();
    }

    private void setAtributes(){
        switch (difficulty){
            case EASY -> {
                dimentions = new Dimentions(5, 5);
                numberOfBombs = 3;
            }
            case MEDIUM -> {
                dimentions = new Dimentions(8, 8);
                numberOfBombs = 6;
            }
            case HARD -> {
                dimentions = new Dimentions(9, 9);
                numberOfBombs = 12;
            }
        }
    }

    public static String getDefultIcon() {
        return defultIcon;
    }

    public static void setDefultIcon(String defultIcon) {
        GameRules.defultIcon = defultIcon;
    }

    public Dimentions getDimentios() {
        return dimentions;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public static String getMineIcon() {
        return mineIcon;
    }

    public static void setMineIcon(String mineIcon) {
        GameRules.mineIcon = mineIcon;
    }

    public static String getFlagIcon() {
        return flagIcon;
    }

    public static void setFlagIcon(String flagIcon) {
        GameRules.flagIcon = flagIcon;
    }

    public static String getNotCheckedIcon() {
        return notCheckedIcon;
    }

    public static void setNotCheckedIcon(String notCheckedIcon) {
        GameRules.notCheckedIcon = notCheckedIcon;
    }
}
