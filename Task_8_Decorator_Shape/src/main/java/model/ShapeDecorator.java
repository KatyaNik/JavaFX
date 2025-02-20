package model;

public class ShapeDecorator implements IShape {
    protected IShape decoratedShape;

    public ShapeDecorator(IShape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}
