package Engine;

public abstract class GhostObject extends GameObject {

    @Override
    public void setGame(Game value) {
        super.setGame(value);
        node = getGame().getGraph().getInitialGhostNode();
    }
}
