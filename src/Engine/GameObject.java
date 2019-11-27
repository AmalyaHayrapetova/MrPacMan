package Engine;

import java.awt.*;
import java.util.Stack;

public abstract class GameObject {
    private Game game;
    private Move lastMove;
    private boolean alive;
    protected Node node;

    public GameObject(){
        this.lastMove = Move.NoMove;
        alive = true;
    }

    public Controller getController() { return null; }

    public abstract GraphicalObject getGraphicalPart();

    public void update() {
        Controller controller = getController();
        if(controller == null) return;
        Move move = controller.getNext(this);
        update(move);
    }

    private void update(Move move){
        try {
            Node currentNode = this.node;
            Move appropriateMove = getAppropriateMove(move, currentNode);
            if (appropriateMove == Move.NoMove) return;
            lastMove = appropriateMove;
            int moveNodeIndex = 0;
            moveNodeIndex = currentNode.neighbourhood.get(appropriateMove);
            node = getGame().getGraph().getNodes()[moveNodeIndex];
            onUpdate(appropriateMove);
        }
        catch(Exception e) {

        }
    }

    private Move getAppropriateMove(Move move, Node currentNode){
        if(!currentNode.neighbourhood.containsKey(move))
            move = lastMove;
        return move;
    }

    protected void onUpdate(Move move) { }

    public boolean alive(){
        return alive;
    }

    public void destroy(){
        alive = false;
    }

    public Game getGame(){
        return this.game;
    }

    public void setGame(Game value){
         this.game = value;
    }

    public Point getPosition(){
        return new Point(node.x,node.y);
    }

    public Node getNode(){
        return node;
    }

    public Move getLastMove(){
        return  lastMove;
    }

    public void render(Graphics g){
        getGraphicalPart().paint(g, this);
    }

}
