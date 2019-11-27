package Engine;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AStarController extends Controller {
    private Set<Move> lastMoveSet = new HashSet<Move>();
    private Move lastMove = Move.NoMove;
    private Random rand ;
//    AStar aStar;
//
//    public AStarController(){
//        aStar = new AStar();
//    }


    @Override
    protected Move getNext(GameObject object) {
//        var moves = object.getNode().neighbourhood.keySet();
//        while (!lastMoveSet.equals(moves)) {
//            var movesArray = moves.toArray();
//            var move =  (Move) movesArray[rand.nextInt(movesArray.length)]; //possible moves
//
//            for(int i = 0; i < movesArray.length; i++){
//
//                int[] p = aStar.computePathsAStar(i,13,object.getGraphicalPart());
//            }
//
//            lastMove = move;
//            lastMoveSet = moves;
//            return move;
//        }
         return lastMove;
    }
}
