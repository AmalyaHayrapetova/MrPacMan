package PacMan;

import Engine.*;

public class PacManObject extends GameObject {

    @Override
    public void setGame(Game value) {
        super.setGame(value);
        node = getGame().getGraph().getInitialPacManNode();
    }

    @Override
    public Controller getController() {
        return new HumanController();
    }

    @Override
    protected void onUpdate(Move move) {
        super.onUpdate(move);
        getGame().handlePacManPositionChange();
    }

    @Override
    public GraphicalObject getGraphicalPart() {
        return new PacManGraphics();
    }
}
