package Engine;

import java.awt.*;

public class PillObject extends GameObject {
    public boolean isPowerPill;
    public PillObject(Node node, boolean isPowerPill){
        this.node = node;
        this.isPowerPill = isPowerPill;
    }

    @Override
    public GraphicalObject getGraphicalPart() {
        if(isPowerPill)
            return new PowerPillGraphics();
        else
            return new PillGraphics();
    }

}
