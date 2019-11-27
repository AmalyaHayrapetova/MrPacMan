package Engine;


import Engine.Controller;
import Engine.GameObject;
import Engine.Move;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomController extends Controller {
    private Set<Move> lastMoveSet = new HashSet<Move>();
    private Move lastMove = Move.NoMove;

    private Random rand ;

    public RandomController(int seed){
        rand = new Random(seed);
    }

    @Override
    protected Move getNext(GameObject obj) {
        var moves = obj.getNode().neighbourhood.keySet();
        if (!lastMoveSet.equals(moves)) {
            var movesArray = moves.toArray();
            var move =  (Move) movesArray[rand.nextInt(movesArray.length)];
            lastMove = move;
            lastMoveSet = moves;
            return move;
        }
         return lastMove;
    }
}