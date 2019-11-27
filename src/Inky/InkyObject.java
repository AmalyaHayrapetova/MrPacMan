package Inky;

import Engine.*;

public class InkyObject extends GhostObject {
    private GraphicalObject graphicalObject;
    private Controller controller;
    public InkyObject() {
        graphicalObject = new InkyGraphics();
        controller = new RandomController(12334234);
    }

    @Override
    public GraphicalObject getGraphicalPart() {
        return graphicalObject;
    }

    @Override
    public Controller getController() {
        return controller;
    }
}
