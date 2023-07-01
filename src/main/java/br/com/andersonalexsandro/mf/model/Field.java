package br.com.andersonalexsandro.mf.model;

import br.com.andersonalexsandro.mf.model.interfaces.BombCounter;
import br.com.andersonalexsandro.mf.model.interfaces.Explosable;
import br.com.andersonalexsandro.mf.model.interfaces.Flaggable;

import java.util.Objects;

public class Field implements Flaggable, Explosable, BombCounter {
    private int bombsAround;
    private boolean flagged;
    private boolean bomb;
    Coordinate coordinate;

    public Field(boolean flagged, boolean bomb, Coordinate coordinate) {
        this.flagged = flagged;
        this.bomb = bomb;
        this.coordinate = coordinate;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean isFlagged() {
        return flagged;
    }

    @Override
    public boolean hasbomb() {
        return bomb;
    }

    @Override
    public boolean isExplosed() {
        return false;
    }

    @Override
    public int bombsAround() {
        return bombsAround;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return flagged == field.flagged && bomb == field.bomb && Objects.equals(coordinate, field.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flagged, bomb, coordinate);
    }

    @Override
    public String toString() {
        return "Field{" +
                "flagged=" + flagged +
                ", bomb=" + bomb +
                ", coordinate=" + coordinate +
                '}';
    }
}
