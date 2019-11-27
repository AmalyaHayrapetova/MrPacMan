package Blinky;

import Engine.Game;
import Engine.GameObject;
import Engine.GraphicalObject;
import Engine.Move;

import java.awt.*;
import java.util.Stack;

public class BlinkyGraphics implements GraphicalObject {

    @Override
    public void paint(Graphics g, GameObject object) {
        var image = object.getGame().getImageRepository().Get("blinky-left-1.png");
        Point drawingPoint = object.getGame().getDrawingPoint(new Point(object.getPosition().x, object.getPosition().y));
        g.drawImage(image, drawingPoint.x,drawingPoint.y,null);

    }
}
