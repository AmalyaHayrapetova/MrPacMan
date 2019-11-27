package Debug.Maze;

import Debug.DummyController;
import Engine.Controller;
import Engine.GameObject;
import Engine.GraphicalObject;
import Engine.Move;

public class MazeDebug extends GameObject {

    @Override
    public Controller getController() {
        return new DummyController();
    }

    @Override
    public GraphicalObject getGraphicalPart() {
        return new MazeDebugGraphics();
    }

    @Override
    protected void onUpdate(Move move) {

    }
}
