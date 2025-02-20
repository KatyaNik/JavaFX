package model;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public interface IBuilder {
    void buildTree(int deviation, AnchorPane anchorPane);
    void buildText(String text, AnchorPane anchorPane);
    void buildCircle(int deviation, AnchorPane anchorPane);
    Group getTree();

}
