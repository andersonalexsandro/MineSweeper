package br.com.andersonalexsandro.mf.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MineSweeperTest {

    int numberOfTest;
    MineSweeper mineSweeper;

    @BeforeEach
    void setUp() {
        mineSweeper = new MineSweeper(Difficulty.EASY);
        numberOfTest = 10;
    }

    @Test
    void genereteBombsTest(){
        int maxBombs = mineSweeper.getGameRules().getNumberOfBombs();
        int bombsCreateds = 0;
        Map<Coordinate, Field> map = mineSweeper.getBoard().getMap();
        for (Map.Entry<Coordinate, Field> entry : map.entrySet()) {
            Field field = entry.getValue();
            if(field.isMine()) bombsCreateds++;
        }
        assertEquals(maxBombs, bombsCreateds);
    }

    @Test
    void isGameWonTest(){
        checkAllNonMines();
        assertTrue(mineSweeper.isGameWon());
    }
    void checkAllNonMines(){
        for(Map.Entry<Coordinate, Field> entry : mineSweeper.board.getMap().entrySet()){
            Field field = entry.getValue();
            if(field.isMine()) mineSweeper.board.alternateFlagField(field.getCoordinate());
            if(!field.isMine() && !field.isChecked()) mineSweeper.board.checkField(field);
        }
    }
}