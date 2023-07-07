package br.com.andersonalexsandro.mf.model;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

public class EspecificsCoordinatesForTest {

    private int width;
    private int height;
    private Set<Coordinate> allCoordinates;
    private Set<Coordinate> internalCoordinates;
    private Set<Coordinate> corners;
    private Set<Coordinate> vertex;

    public EspecificsCoordinatesForTest(int width, int height){
        this.width = width;
        this.height = height;
        allCoordinates = everyPossibleCoordinate();
        internalCoordinates = everyPossibleCoordinate();
        internalCoordinates.removeAll(cornersCoordinate());
        vertex = vertexsCoordinate();
        corners = cornersCoordinate();
        corners.removeAll(vertexsCoordinate());
    }

    private Set<Coordinate> vertexsCoordinate(){
        Set<Coordinate> vertexCoordinate = new HashSet<>();
        for(int x = 0; x<width; x+=width-1) for(int y =0; y<height; y+=height-1) vertexCoordinate.add(new Coordinate(x,y));
        for(int y=0; y<width; y+=height-1)  for(int x = 0; x<width; x+=width-1) vertexCoordinate.add(new Coordinate(x,y));
        return vertexCoordinate;
    }

    private Set<Coordinate> cornersCoordinate(){
        Set<Coordinate> corners = new HashSet<>();
        for(int x = 0; x< width; x+=width-1) for (int y = 0; y < height; y++) corners.add(new Coordinate(x, y));
        for(int y = 0; y< height; y+=height -1) for(int x = 0; x< width; x++) corners.add(new Coordinate(x,y));
        return corners;
    }

    private Set<Coordinate> everyPossibleCoordinate(){
        Set<Coordinate> everyCoordinate = new HashSet<>();
        for(int x = 0; x< width; x++){
            for (int y = 0; y< height; y++){
                everyCoordinate.add(new Coordinate(x, y));
            }
        }
        return everyCoordinate;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Coordinate> getAllCoordinates() {
        return allCoordinates;
    }

    public void setAllCoordinates(Set<Coordinate> allCoordinates) {
        this.allCoordinates = allCoordinates;
    }

    public Set<Coordinate> getInternalCoordinates() {
        return internalCoordinates;
    }

    public void setInternalCoordinates(Set<Coordinate> internalCoordinates) {
        this.internalCoordinates = internalCoordinates;
    }

    public Set<Coordinate> getCorners() {
        return corners;
    }

    public void setCorners(Set<Coordinate> corners) {
        this.corners = corners;
    }

    public Set<Coordinate> getVertex() {
        return vertex;
    }

    public void setVertex(Set<Coordinate> vertex) {
        this.vertex = vertex;
    }
}
