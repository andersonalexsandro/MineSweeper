package br.com.andersonalexsandro.mf.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;
    int numberOfTest;

    @BeforeEach
    void setUp() {
        numberOfTest = 10;
        board = new Board(new Dimentions(9, 9));
    }

    @Test
    void sizeEqualsDimentions(){
        assertEquals(board.getColumns().size(), board.getDimentions().getWidth());
        assertEquals(board.getColumns().get(0).size(), board.getDimentions().getHeight());

    }

    @Test
    void generateBoard() {
        ArrayList<ArrayList<Field>> columnsGereted = board.generateBoard();
        for(ArrayList<Field> column: columnsGereted){
            for (Field field: column){
                assertEquals("fhe", fieldState(field));
            }
        }
    }

    @Test
    void generateColumn() {
        for (int i = 0; i<board.getDimentions().getHeight(); i++){
            assertEquals("fhe", fieldState(board.generateColumn().get(i)));
        }
    }

    @Test
    void generateDefoultFieldTest() {

        Coordinate coordinate = new Coordinate(0,0);

        for(int i=0; i<numberOfTest; i++){
            assertEquals("fhe", fieldState(board.generateDefoultField(coordinate)));
        }
    }

    @Test
    void setBombTest(){
        Coordinate[] surroundingCoordinates = surroundingCoordinates();

        for(Coordinate coordinate: surroundingCoordinates){
            board.spawnBomb(coordinate);
            assertTrue(board.getField(coordinate).hasbomb());
        }
    }

    @Test
    void countBombsAround(){
        Coordinate[] surroundingCoordinates = surroundingCoordinates(5);

        for(Coordinate coordinate: surroundingCoordinates){
            board.spawnBomb(coordinate);
        }

        int numberOfBOmbs= board.countBombsAroundField(board.getField(new Coordinate(5, 5)));

        assertEquals(9, numberOfBOmbs);

    }

    public Coordinate[]surroundingCoordinates(int position){

        int xFieldCoordinate = position;
        int yFieldCoordinate = position;

        Coordinate[] surroundingCoordinates = {
                new Coordinate(xFieldCoordinate + 1, yFieldCoordinate),
                new Coordinate(xFieldCoordinate - 1, yFieldCoordinate),
                new Coordinate(xFieldCoordinate, yFieldCoordinate - 1),
                new Coordinate(xFieldCoordinate, yFieldCoordinate + 1),
                new Coordinate(xFieldCoordinate + 1, yFieldCoordinate + 1),
                new Coordinate(xFieldCoordinate - 1, yFieldCoordinate + 1),
                new Coordinate(xFieldCoordinate + 1, yFieldCoordinate - 1),
                new Coordinate(xFieldCoordinate - 1, yFieldCoordinate - 1)
        };
        return surroundingCoordinates;
    }

    public Coordinate[]surroundingCoordinates(){
        Random random = new Random();
        int xFieldCoordinate = random.nextInt(board.getDimentions().getWidth());
        int yFieldCoordinate = random.nextInt(board.getDimentions().getHeight());

        Coordinate[] surroundingCoordinates = {
                new Coordinate(xFieldCoordinate + 1, yFieldCoordinate),
                new Coordinate(xFieldCoordinate - 1, yFieldCoordinate),
                new Coordinate(xFieldCoordinate, yFieldCoordinate - 1),
                new Coordinate(xFieldCoordinate, yFieldCoordinate + 1),
                new Coordinate(xFieldCoordinate + 1, yFieldCoordinate + 1),
                new Coordinate(xFieldCoordinate - 1, yFieldCoordinate + 1),
                new Coordinate(xFieldCoordinate + 1, yFieldCoordinate - 1),
                new Coordinate(xFieldCoordinate - 1, yFieldCoordinate - 1)
        };
        return surroundingCoordinates;
    }

    private String fieldState(Field field){
        StringBuilder stringBuilder = new StringBuilder();

        if(field.isFlagged()) stringBuilder.append("F");
        else stringBuilder.append("f");

        if(field.hasbomb()) stringBuilder.append("H");
        else stringBuilder.append("h");

        if (field.isExplosed()) stringBuilder.append("E");
        else stringBuilder.append("e");

        return stringBuilder.toString();
    }

}

