package br.com.andersonalexsandro.mf.view;

import br.com.andersonalexsandro.mf.model.Board;
import br.com.andersonalexsandro.mf.model.Coordinate;
import br.com.andersonalexsandro.mf.model.Dimentions;
import br.com.andersonalexsandro.mf.model.Field;

import java.util.Map;

public class BoardView {

    private Board board;
    private Field[][] fields;

    public BoardView(Board board) {
        this.board = board;
        fields = fillField();
    }

    private Field[][] fillField(){
        int rows = board.getDimentions().getHeight();
        int colmuns = board.getDimentions().getWidth();
        fields = new Field[rows][colmuns];
        Map<Coordinate, Field> map = board.getMap();
        for(int y=0; y<fields.length; y++){
            for(int x=0; x<fields[y].length; x++){
                fields[y][x] = board.getField(new Coordinate(x,y));
            }
        }
        return fields;
    }

    public void printFileds(){
        int y=0;
        int x=0;
        for(y=0; y<fields.length; y++){
            if(y+1<10) System.out.print((1+y)+"-  ");
            else System.out.print((1+y)+"- ");
            for(x=0; x<fields[y].length; x++){
                System.out.print(fields[y][x]+ " ");
            }
            System.out.println();
        }
        printEdge(x);
    }
    private void printEdge(int x){
        System.out.print("    ");
        char letter = 'A'-1;
        for(int i=0; i<x; i++){
            letter++;
            System.out.print((letter) + " ");
        }
    }

    public static void main(String[] args) {
        Board board1 = new Board(new Dimentions(10, 26));

        board1.checkField(new Coordinate(5,7));

        BoardView boardView = new BoardView(board1);
        boardView.printFileds();
    }

}
