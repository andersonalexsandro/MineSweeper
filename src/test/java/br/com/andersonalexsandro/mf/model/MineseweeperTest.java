package br.com.andersonalexsandro.mf.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MineseweeperTest {

    int numberOfTest;
    Mineseweeper mineseweeper;

    @BeforeEach
    void setUp() {
        mineseweeper = new Mineseweeper(Difficulty.EASY);
        numberOfTest = 10;
    }

    @Test
    void genereteBombsTest(){
        int maxBombs = mineseweeper.getGameRules().getNumberOfBombs();
        int bombsCreateds = 0;
        for(ArrayList<Field> column: mineseweeper.getBoard().getColumns()){
            for(Field field: column){
                if(field.hasbomb()) bombsCreateds++;
            }
        }
        assertEquals(maxBombs, bombsCreateds);
    }
}