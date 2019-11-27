package Inky;

import Engine.GameObject;
import Engine.GraphicalObject;

import java.awt.*;

public class InkyGraphics implements GraphicalObject {

    @Override
    public void paint(Graphics g, GameObject object) {
        var image = object.getGame().getImageRepository().Get("inky-left-1.png");
        Point drawingPoint = object.getGame().getDrawingPoint(new Point(object.getPosition().x, object.getPosition().y));
        g.drawImage(image, drawingPoint.x,drawingPoint.y,null);

    }
}
