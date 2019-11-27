package Blinky;

import Engine.*;

public class BlinkyObject extends GhostObject {
    private GraphicalObject graphicalObject;
    private Controller controller;
    public BlinkyObject() {
        graphicalObject = new BlinkyGraphics();
        controller = new RandomController(800);
    }

    @Override
    public GraphicalObject getGraphicalPart() {
        return graphicalObject;
    }

    @Override
    public Controller getController() { return controller; }
}
