package model;

import javafx.scene.paint.Color;

public class Memento {

    private Shape snape;
    private double x,y;
    private Color color;

//сделать снимок

    public Memento(Shape state){
        this.snape = state;
        x= snape.getX();
        y=snape.getY();
        color=(Color) snape.getColor();
    }

//вернуть первоначальный свойства
    public Shape getState() {
        snape.setPosition(x,y);
        snape.setColor(color);
        return snape;
    }
}
