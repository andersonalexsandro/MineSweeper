package br.com.andersonalexsandro.mf.aplication;

import br.com.andersonalexsandro.mf.exceptions.ExplosedRuntimeException;
import br.com.andersonalexsandro.mf.exceptions.WinRunTimeException;
import br.com.andersonalexsandro.mf.model.Coordinate;
import br.com.andersonalexsandro.mf.model.Difficulty;
import br.com.andersonalexsandro.mf.model.MineSweeper;

import java.util.*;

public class MineSweeperAplication {
    private static Scanner input = new Scanner(System.in);
    private static MineSweeper mineSweeper;

    public static void main(String[] args) {
        play();
    }

    public static void play() {
        System.out.println("Welcome to Anderson Minesweeper!");
        int option = 0;
        boolean validOption = false;
        while (!validOption){
            System.out.println("Choose difficulty");
            printDifficultyOptions();
            option = intInput();
            try{
                mineSweeper = newMineSweeperByOptionsCase(option);
                validOption = true;
            }
            catch (IllegalArgumentException e){
                System.out.println("Option not found");
            }
        }

        printInstructions();

        boolean finshGame = false;
        while (!finshGame){
                printBoardView();
                System.out.println();
                System.out.print("Instruction: ");
            try {
                doInstruction(nextLineInput());
                if(mineSweeper.isGameWon()) throw new WinRunTimeException("You win!");
                System.out.println();
            }
            catch (IllegalArgumentException e){
                System.out.println("Invalid argument!");
            }
            catch(WinRunTimeException e){
                System.out.println("You win!");
                printPlayAgain();
                if(playAgain(nextInput())){
                    mineSweeper = newMineSweeperByOptionsCase(option);
                    continue;
                }
            }
            catch(ExplosedRuntimeException e){
                System.out.println("You lost! mine explosed");
                mineSweeper.getBoard().checlAllFields();
                printBoardView();
                printPlayAgain();
                if(playAgain(nextInput())){
                    mineSweeper = newMineSweeperByOptionsCase(option);
                    continue;
                }
                finshGame = true;
            }
        }
    }

    public static void printPlayAgain(){
        System.out.println("\nDo you want to play again? Y/n");
    }

    public static boolean playAgain(String answer){
        if(answer.equalsIgnoreCase("n")) return false;
        return true;
    }

    public static void printBoardView(){
        mineSweeper.getBoard().getBoardView().printFileds();
    }

    public static void printDifficultyOptions(){
        System.out.println("1- Easy" +
                "\n2-Medium" +
                "\n3-Hard");
    }

    public static int intInput(){
        int interger = 0;
        boolean validAnswer = false;
        while (!validAnswer){
            try {
                interger = input.nextInt();
                validAnswer = true;
            }catch (InputMismatchException e){
                System.out.println("Wrong input!");
                input.next();
            }
        }
        input.nextLine();
        return interger;
    }

    public static MineSweeper newMineSweeperByOptionsCase(int option){

        final int EASY = 1;
        final int MEDIUM = 2;
        final int HARD = 3;

        switch (option){
            case (EASY) -> {
                return new MineSweeper(Difficulty.EASY);
            }
            case (MEDIUM) ->{
                return new MineSweeper(Difficulty.MEDIUM);
            }
            case (HARD) -> {
                return new MineSweeper(Difficulty.HARD);
            }
        }
        throw new IllegalArgumentException("Option not found");
    }

    public static void printInstructions(){
        System.out.println("To play you will need to digit the line number, the column letter and the action. all between comma");
        System.out.println("an operation is indicate by F to flag or disflag, C to click on field");
        System.out.println("as these exemple: 2,B,F or 2,B,C");
        System.out.println("Good luck!");
    }

    public static void doInstruction(String instruction){
        ArrayList<String> instructions = spliString(instruction.toUpperCase());
        if(instructions.size()<3) throw new IllegalArgumentException();
        String action = instructions.get(2);
        int line =  Integer.parseInt(instructions.get(0)) -1;
        int column = instructions.get(1).charAt(0) -65;
        Coordinate coordinate = new Coordinate(column, line);

        final String CLICK = "C";
        final String Flag = "F";

        switch (action.toUpperCase()){
            case (CLICK) -> mineSweeper.getBoard().checkField(coordinate);
            case (Flag) -> mineSweeper.getBoard().alternateFlagField(coordinate);
        }
    }

    public static ArrayList<String> spliString(String string) {
        return new ArrayList<>(Arrays.asList(string.split(",")));
    }

    public static String nextInput(){
        String string = input.nextLine();
        List<String> list = Arrays.asList(string.split(" "));
        return list.get(0);
    }

    public static String nextLineInput(){
        String string = input.nextLine();
        return string;
    }
}
