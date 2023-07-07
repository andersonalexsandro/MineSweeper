package br.com.andersonalexsandro.mf.model;

import br.com.andersonalexsandro.mf.exceptions.ExplosedRuntimeException;
import br.com.andersonalexsandro.mf.model.interfaces.Explosable;
import br.com.andersonalexsandro.mf.view.BoardView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

    private Dimentions dimentions;
    private Map<Coordinate, Field> map = new HashMap<>();
    private BoardView boardView;

    public Board(Dimentions dimentions) {
        this.dimentions = dimentions;
        generateBoard();
        boardView = new BoardView(this);
    }

    public void generateBoard(){
        generateBoardAlgorithm();
        updateBombCounters();
    }

    public Map<Coordinate, Field> generateBoardAlgorithm(){
        for(int x=0; x< dimentions.getWidth(); x++){
            for(int y=0; y<dimentions.getHeight(); y++){
                Coordinate fieldCoordinate = new Coordinate(x, y);
                map.put(fieldCoordinate, new Field(fieldCoordinate));
            }
        }
        return map;
    }

    public int countBombsAround(Field field) {
        Coordinate fieldCoordinate = field.getCoordinate();
        Set<Coordinate> surroundingCoordinates = surrounding(fieldCoordinate);
        int bombs = 0;
        for (Coordinate coordinate : surroundingCoordinates) {
           if(isOutOfBounds(coordinate)) continue;
            Field localizetedField = getField(coordinate);
            if (localizetedField.isMine()) bombs++;
        }
        return bombs;
    }

    public int countBombsAround(Coordinate fieldCoordinate) {
        Set<Coordinate> surroundingCoordinates = surrounding(fieldCoordinate);
        int bombs = 0;
        for (Coordinate coordinate : surroundingCoordinates) {
            if(isOutOfBounds(coordinate)) continue;
            if(fieldCoordinate.equals(coordinate)) continue;
            Explosable localizetedField = getField(coordinate);
            if (localizetedField.isMine()) bombs++;
        }
        return bombs;
    }

    public boolean isOutOfBounds(Coordinate coordinate){
        if(coordinate.getX() > dimentions.getWidth()-1 || coordinate.getY()>dimentions.getHeight()-1) return true;
        return false;
    }

    public Set<Coordinate> surrounding(Coordinate coordinate) {
        int xFieldCoordinate = coordinate.getX();
        int yFieldCoordinate = coordinate.getY();

        Set<Coordinate> surroundingCoordinates = new HashSet<>();
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate + 1, yFieldCoordinate));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate - 1, yFieldCoordinate));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate, yFieldCoordinate - 1));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate, yFieldCoordinate + 1));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate + 1, yFieldCoordinate + 1));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate - 1, yFieldCoordinate + 1));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate + 1, yFieldCoordinate - 1));
        surroundingCoordinates.add(new Coordinate(xFieldCoordinate - 1, yFieldCoordinate - 1));

        return surroundingCoordinates;
    }

    public void spawnMine(Coordinate coordinate){
        getField(coordinate).spawnMine();
        updateBombCounters();
    }

    public void alternateFlagField(Coordinate coordinate){
        Field field = getField(coordinate);
        if(field.isFlagged()){
            field.disFlag();
            return;
        }
        if(!field.isFlagged()) field.flag();
    }

    public void checkField(Coordinate coordinate){
        checkField(getField(coordinate));
    }

    public void checkField(Field field){
        if(field.isChecked()) checkFildsAround(field);
        if(!field.isFlagged()) field.check();
        if(countBombsAround(field) ==0) checkFildsAround(field);
    }

    protected void checkFildsAround(Field field){
        checkFildsAround(field.getCoordinate());
    }

    protected void checkFildsAround(Coordinate coordinate){
        Set<Coordinate> surroundingCoordinates = surrounding(coordinate);
        for(Coordinate coordinates: surroundingCoordinates){
            if(isOutOfBounds(coordinates)) continue;
            Field fieldLocated = getField(coordinates);
            if(!fieldLocated.isChecked()){
                checkField(fieldLocated);
            }
        }
    }

    public void updateBombCounters(){
        for(Map.Entry<Coordinate, Field> entry: map.entrySet()){
            Field field = entry.getValue();
            int numberOfBombs = countBombsAround(field);
            field.setNumberOfMinesAround(numberOfBombs);
        }
    }

    public void checlAllFields(){
        for(Map.Entry<Coordinate, Field> field : map.entrySet()){
            Field fieldLocated = field.getValue();
            try {
               fieldLocated.check();
            }
            catch (ExplosedRuntimeException e){}
        }
    }

    public Field getField(Coordinate coordinate){
        return map.get(coordinate);
    }

    public Dimentions getDimentions() {
        return dimentions;
    }

    public Map<Coordinate, Field> getMap() {
        return map;
    }

    public BoardView getBoardView() {
        return boardView;
    }
}
