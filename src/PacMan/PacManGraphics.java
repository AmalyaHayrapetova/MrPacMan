package PacMan;

import Engine.GameObject;
import Engine.GraphicalObject;

import java.awt.*;

public class PacManGraphics implements GraphicalObject {
    @Override
    public void paint(Graphics g, GameObject object) {
        String resName = "";
        switch (object.getLastMove()){
            case LEFT:
                resName = "mspacman-left-normal.png";
                break;
            case RIGHT:
                resName = "mspacman-right-normal.png";
                break;
            case UP:
                resName = "mspacman-up-normal.png";
                break;
            case DOWN:
                resName = "mspacman-down-normal.png";
                break;
            default:
                resName = "mspacman-right-normal.png";
                break;
        }
        var image = object.getGame().getImageRepository().Get(resName);
        Point drawingPoint = object.getGame().getDrawingPoint(new Point(object.getPosition().x, object.getPosition().y));
        g.drawImage(image, drawingPoint.x,drawingPoint.y,null);
    }
}
