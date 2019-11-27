import Blinky.BlinkyObject;
import Engine.Game;
import Engine.GameComponent;
import Engine.Maze;
import Inky.InkyObject;
import PacMan.PacManObject;

public class Main {

    public static void main(String[] args) {
        var game = new Game(2, Maze.C);
        game.addGhost(new BlinkyObject());
//        game.addGhost(new InkyObject());
        game.addPacMan(new PacManObject());
        var gameUI = new GameComponent(game);
        //game.addObject(new MazeDebug());
        gameUI.start();
    }
}
