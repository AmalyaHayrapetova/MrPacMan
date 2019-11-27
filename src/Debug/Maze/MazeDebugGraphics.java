package Debug.Maze;

import Engine.GameObject;
import Engine.GraphicalObject;
import Engine.Node;

import java.awt.*;

public class MazeDebugGraphics implements GraphicalObject {

    @Override
    public void paint(Graphics g, GameObject object) {
        for (Node node : object.getGame().getGraph().getNodes()) {
            Point drawingPoint = object.getGame().getDrawingPoint(new Point(node.x, node.y));
            g.drawRect(drawingPoint.x, drawingPoint.y, (int)(7*object.getGame().getScale()), (int)(7*object.getGame().getScale()));
        }
    }
}
