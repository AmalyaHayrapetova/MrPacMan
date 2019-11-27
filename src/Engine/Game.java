package Engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private CachedImageRepository images;
    private GameGraph gameGraph;
    private Maze maze;
    private List<GameObject> ghosts;
    private GameObject pacMan;
    private float scale;
    private boolean gameEnded;
    private KeyBoardInput keyBoardInput;
    public List<GameObject> objects  = new ArrayList<>();

    public Game(float scale, Maze maze){
        this.ghosts = new ArrayList<>();
        this.images = new CachedImageRepository(this);
        this.gameGraph = new GameGraph(getResourcePath(maze.getLoaderFile()));
        this.maze = maze;
        this.scale = scale;
        this.keyBoardInput = new KeyBoardInput();

        for (int pillIndex : getGraph().getPillIndices())
            addObject(new PillObject(getGraph().getNodes()[pillIndex], false));
        for (int pillIndex : getGraph().getPowerPillIndices())
            addObject(new PillObject(getGraph().getNodes()[pillIndex], true));
    }

    public boolean isGameEnded(){
        return gameEnded;
    }

    public void handlePacManPositionChange(){
        int pacManIndex = pacMan.getNode().nodeIndex;
        for (var obj : objects) {
            if(obj.getNode().nodeIndex == pacManIndex)
            {
                if(obj instanceof PillObject){
                    PillObject pill = (PillObject)obj;

                    if(pill.isPowerPill)
                        eatPowerPill(pill);
                    else
                        eatPill(pill);
                    checkGameEnded();
                }
            }
        }
    }

    private void checkGameEnded() {
        if(objects.stream().filter(o -> o instanceof PillObject && o.alive()).count() == 0) gameEnded = true;
    }

    private void eatPowerPill(PillObject obj) {
        destroyObject(obj);
    }

    private void eatPill(PillObject obj) {
        destroyObject(obj);
    }

    public void addObject(GameObject obj){
        this.objects.add(obj);
        obj.setGame(this);
    }

    public void addGhost(GameObject obj){
        addObject(obj);
        ghosts.add(obj);
    }

    public void addPacMan(GameObject obj){
        addObject(obj);
        setPacMan(obj);
    }

    public void destroyObject(GameObject obj){
        obj.destroy();
    }

    public void setPacMan(GameObject obj){
        pacMan = obj;
    }

    public GameObject getPacMan(){
        return  pacMan;
    }

    public List<GameObject> getGhosts(){
        return  ghosts;
    }

    public KeyBoardInput getKeyBoardInput(){
        return keyBoardInput;
    }

    public Point getDrawingPoint(Point p){
        return new Point((int)(this.scale*(p.x - 2)), (int)(this.scale*(p.y - 2)));
    }

    public CachedImageRepository getImageRepository(){
        return this.images;
    }

    public GameGraph getGraph(){
        return this.gameGraph;
    }

    public Maze getMaze(){
        return this.maze;
    }

    public float getScale(){
        return this.scale;
    }

    public String getResourcePath(String name){
        return getClass().getResource("/resources/" + name).getPath();
    }


}
