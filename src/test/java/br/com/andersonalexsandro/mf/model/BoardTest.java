package br.com.andersonalexsandro.mf.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    int width;
    int height;
    Board board;
    int numberOfTest;
    EspecificsCoordinatesForTest coordinates;

    @BeforeEach
    void setUp() {
        numberOfTest = 10;
        height = 9;
        width = 9;
        board = new Board(new Dimentions(height, width));
        coordinates = new EspecificsCoordinatesForTest(width, height);
    }

    @Test
    void spwanBombTest(){
        for(Coordinate internalCoordiante: coordinates.getInternalCoordinates()){
            Set<Coordinate> surroundingCoordinates = board.surrounding(internalCoordiante);
            for(Coordinate coordinate: surroundingCoordinates){
                board.spawnMine(coordinate);
                assertTrue(board.getField(coordinate).isMine());
            }
        }
    }

    @Test
    void countBombsAroundIternalCoordinates(){
        for(Coordinate coordinate:  coordinates.getInternalCoordinates()){
            spwanBombsAround(coordinate);
            int numberOfBombs = board.countBombsAround(coordinate);
            assertEquals(8, numberOfBombs);
        }
    }

    @Test
    void countBombsAroundCorners(){
        for(Coordinate coordinate: coordinates.getCorners()){
            spwanBombsAround(coordinate);
            int numberOfBombs = board.countBombsAround(coordinate);
            assertEquals(5, numberOfBombs);
        }
    }

    @Test
    void countBombsAroundVertexs(){
        for(Coordinate coordinate: coordinates.getVertex()){
            spwanBombsAround(coordinate);
            int numberOfBombs = board.countBombsAround(coordinate);
            assertEquals(3, numberOfBombs);
        }
    }

    public Map<Coordinate, Field> spwanBombsAround(Coordinate coordinate){
        Set<Coordinate> surroundingCoordinates = board.surrounding(coordinate);
        for(Coordinate coordinates: surroundingCoordinates) {
            if(coordinates.getX()>width-1 || coordinates.getY()>height-1) continue;
            if(coordinate.equals(coordinates)) continue;
            board.getField(coordinates).spawnMine();
        }
        return board.getMap();
    }

    @Test
    void checkFildsAroundTest(){
        board.checkFildsAround(new Coordinate(0,0));
        for(Map.Entry<Coordinate, Field> filds: board.getMap().entrySet()){
            assertTrue(filds.getValue().isChecked());
        }
    }

    @Test
    void toStringTest(){
        for(Map.Entry<Coordinate, Field> filds: board.getMap().entrySet()) assertEquals(GameRules.getNotCheckedIcon(), filds.getValue().toString());

        board.checkFildsAround(new Coordinate(0,0));
        for(Map.Entry<Coordinate, Field> filds: board.getMap().entrySet()) assertEquals(GameRules.getDefultIcon(), filds.getValue().toString());

        spwanMineFullBoard();
        for(Map.Entry<Coordinate, Field> filds: board.getMap().entrySet()) assertEquals(GameRules.getMineIcon(), filds.getValue().toString());

    }

    void spwanMineFullBoard(){
        for(Map.Entry<Coordinate, Field> filds: board.getMap().entrySet()) board.spawnMine(filds.getValue().getCoordinate());
    }

    @Test
    void generateBoard() {
        Map<Coordinate, Field> columns = board.getMap();
        for(Map.Entry<Coordinate, Field> entry : columns.entrySet()){
            Field field = entry.getValue();
            assertEquals("fhe", fieldState(field));
        }
    }

    @Test
    void generateDefoultFieldTest() {
        for(Coordinate everyCoordinate:  coordinates.getAllCoordinates()){
            for(int i=0; i<numberOfTest; i++){
                assertEquals("fhe", fieldState(new Field(everyCoordinate)));
            }
        }

    }

    private String fieldState(Field field){
        StringBuilder stringBuilder = new StringBuilder();

        if(field.isFlagged()) stringBuilder.append("F");
        else stringBuilder.append("f");

        if(field.isMine()) stringBuilder.append("H");
        else stringBuilder.append("h");

        if (field.isExplosed()) stringBuilder.append("E");
        else stringBuilder.append("e");

        return stringBuilder.toString();
    }

    @Test
    void alternateFlagFiled(){
        for (Coordinate coordinate : coordinates.getAllCoordinates()){
            Field field = board.getField(coordinate);
            assertFalse(field.isFlagged());

            board.alternateFlagField(coordinate);
            assertTrue(field.isFlagged());

            board.alternateFlagField(coordinate);
            assertFalse(field.isFlagged());
        }
    }
}

