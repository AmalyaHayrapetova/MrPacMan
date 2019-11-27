package Engine;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PillGraphics implements GraphicalObject {
    @Override
    public void paint(Graphics g, GameObject object) {
        Point point = object.getGame().getDrawingPoint(object.getPosition());
        g.setColor(Color.white);
        g.fillOval(point.x + 6,point.y + 6,3,3);
    }
}
