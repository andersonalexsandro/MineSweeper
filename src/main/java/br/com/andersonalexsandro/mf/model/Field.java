package br.com.andersonalexsandro.mf.model;

public class Field extends FieldState {

    FieldState fieldState;
    Coordinate coordinate;

    public Field(Coordinate coordinate){
        this.fieldState = new FieldState();
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
