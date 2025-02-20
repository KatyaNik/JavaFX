package model;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SimpleBuilder implements IBuilder{
    private final Group treeGroup = new Group();
    private Font font = Font.font("Arial", 36);
    public void buildTree(int deviation, AnchorPane anchorPane){
        Polygon crown1 = new Polygon(
                anchorPane.getWidth()/6*deviation, anchorPane.getHeight()*0.1,//вершина треугольика
                anchorPane.getWidth()/3, anchorPane.getHeight()/6*2,
                anchorPane.getWidth()/3*2, anchorPane.getHeight()/6*2
        );

        Polygon crown2 = new Polygon(
                anchorPane.getWidth()/2, anchorPane.getHeight()/6*2,
                anchorPane.getWidth()/4, anchorPane.getHeight()/5*3,
                anchorPane.getWidth()/4*3, anchorPane.getHeight()/5*3
        );
        Polygon crown3 = new Polygon(
                anchorPane.getWidth()/2, anchorPane.getHeight()/5*3,
                anchorPane.getWidth()/6, anchorPane.getHeight()/8*7,
                anchorPane.getWidth()/6*5, anchorPane.getHeight()/8*7
        );
        crown1.setFill(Color.GREEN);
        crown2.setFill(Color.GREEN);
        crown3.setFill(Color.GREEN);
        treeGroup.getChildren().addAll( crown1, crown2, crown3);
    }
    public void buildText(String text, AnchorPane anchorPane){
        Text message = new Text(text);
        message.setFont(font);
        message.relocate(anchorPane.getWidth()/8*3, anchorPane.getHeight()/6*3); // Центрирование текста под ёлкой
        treeGroup.getChildren().add(message);}
    public void buildCircle(int deviation, AnchorPane anchorPane){
        Circle star = new Circle(25,Color.YELLOW);
        star.setCenterX(anchorPane.getWidth()/6*deviation);
        star.setCenterY(anchorPane.getHeight()*0.1);
        treeGroup.getChildren().add(star);
    }
    public Group getTree(){return treeGroup;}
}
