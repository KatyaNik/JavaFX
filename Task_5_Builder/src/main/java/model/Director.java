package model;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class Director {
    public Group build(IBuilder builder, AnchorPane anchorPane, int deviation){
        builder.buildTree(deviation, anchorPane);      // Построение дерева с 5 уровнями и 6 ветвями на каждом уровне
        builder.buildCircle(deviation, anchorPane);     // Звезда на верхушке радиусом 25 пикселей
        builder.buildText("Новый год", anchorPane);   // Надпись "Новый год"
        return builder.getTree();
    }
}
