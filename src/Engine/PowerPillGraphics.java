
package Engine;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PowerPillGraphics implements GraphicalObject {
    @Override
    public void paint(Graphics g, GameObject object) {
        Point point = object.getGame().getDrawingPoint(object.getPosition());
        g.setColor(Color.white);
        g.fillOval(point.x + 3,point.y + 3,8,8);
    }
}
