package br.com.andersonalexsandro.mf.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Board {

    private Dimentions dimentions;
    private ArrayList<ArrayList<Field>> columns = new ArrayList<>();

    public Board(Dimentions dimentions) {
        this.dimentions = dimentions;
        generateBoard();
    }

    public ArrayList<ArrayList<Field>> generateBoard(){
        for(int i=0; i< dimentions.getWidth(); i++){
            columns.add(generateColumn());
        }
        return columns;
    }

    public ArrayList<Field> generateColumn(){
        ArrayList<Field> column = new ArrayList<>();
        for(int i = 0; i<dimentions.getHeight(); i++){
            column.add(generateDefoultField(new Coordinate(columns.size(), i)));
        }
        return column;
    }

    public Field generateDefoultField(Coordinate coordinate){
        return new Field(false, false, coordinate);
    }

    public Dimentions getDimentions() {
        return dimentions;
    }

    public ArrayList<ArrayList<Field>> getColumns() {
        return columns;
    }

    public int countBombsAroundField(Field field) {
        Coordinate fieldCoordinate = field.getCoordinate();
        int xFieldCoordinate = fieldCoordinate.getX();
        int yFieldCoordinate = fieldCoordinate.getY();

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
        int bombs = 0;
        for (Coordinate coordinate : surroundingCoordinates) {
            if (coordinate.equals(fieldCoordinate)) {
                bombs++;
            }
        }

        return bombs;
    }

    public void spawnBomb(Coordinate coordinate){
        getField(coordinate).setBomb(true);
    }

    public Field getField(Coordinate coordinate){
        for(ArrayList<Field> column: columns){
            for(Field field: column){
                if(field.coordinate.equals(coordinate)) return field;
            }
        }
        throw new NoSuchElementException("Field not found!");
    }
}
