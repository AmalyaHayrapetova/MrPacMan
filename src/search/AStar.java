package search;

import Engine.GameGraph;
import Engine.Move;
import Engine.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.PriorityQueue;

public class AStar {
    public static final int LEFT_RIGHT_COST = 1;
    public static final int UP_DOWN_COST = 1;

    //Cells of our grid
    private N[] gameGraphs;

    //We define priority queues for open nodes.
    //Open Cells: the set of nodes to be evaluated
    //we put the nodes with the lowest cost in first
    private PriorityQueue<GameGraph> openNodes;
    //Closed Cells: the set of nodes already evaluated
    private boolean[][] closedCells;
    //Start cell
    private int startI, startJ;
    //End cell
    private int endI, endJ;


    /**
     * Create graph for search
     *
     * @param nodes
     */
    public void createGraph(Node[] nodes) {
        gameGraphs = new N[nodes.length];

        //create graph
        for (int i = 0; i < nodes.length; i++)
            gameGraphs[i] = new N(nodes[i].nodeIndex);

        //add neighbours
        for (int i = 0; i < nodes.length; i++) {
            EnumMap<Move, Integer> neighbours = nodes[i].neighbourhood;
            Move[] moves = Move.values();

            for (int j = 0; j < moves.length; j++)
                if (neighbours.containsKey(moves[j]))
                    gameGraphs[i].adj.add(new E(gameGraphs[neighbours.get(moves[j])], moves[j], 1));
        }
    }

    /**
     * Computing of A* path
     *
     * @param init
     * @param target
     * @param lastMoveMade
     * @param gameGraph
     * @return
     */
    public synchronized int[] computePathsAStar(int init, int target, Move lastMoveMade, GameGraph gameGraph) {
        N start = gameGraphs[init];
        N goal = gameGraphs[target];

        PriorityQueue<N> open = new PriorityQueue<N>();
        ArrayList<N> closed = new ArrayList<N>();

        start.gCost = 0;
        start.hCost = gameGraph.getManhattanDistance(start.index, goal.index);

        start.reached = lastMoveMade;

        open.add(start);

        while (!open.isEmpty()) {
            N currentNode = open.poll();
            closed.add(currentNode);

            if (currentNode.isEqual(goal))
                break;

            for (E next : currentNode.adj) {
                if (next.move != currentNode.reached.opposite()) {
                    double currentDistance = next.cost;

                    if (!open.contains(next.node) && !closed.contains(next.node)) {
                        next.node.gCost = currentDistance + currentNode.gCost;
                        next.node.hCost = gameGraph.getManhattanDistance(next.node.index, goal.index);
                        next.node.parent = currentNode;
                        next.node.reached = next.move;

                        open.add(next.node);
                    } else if (currentDistance + currentNode.gCost < next.node.gCost) {
                        next.node.gCost = currentDistance + currentNode.gCost;
                        next.node.parent = currentNode;

                        next.node.reached = next.move;

                        if (open.contains(next.node))
                            open.remove(next.node);

                        if (closed.contains(next.node))
                            closed.remove(next.node);

                        open.add(next.node);
                    }
                }
            }
        }

        return extractPath(goal);
    }

    public synchronized int[] computePathsAStar(int s, int t, GameGraph game) {
        return computePathsAStar(s, t, Move.NoMove, game);
    }


    /**
     * Extract
     *
     * @param goal
     * @return
     */
    private synchronized int[] extractPath(N goal) {
        ArrayList<Integer> route = new ArrayList<Integer>();
        N current = goal;
        route.add(current.index);

        while (current.parent != null) {
            route.add(current.parent.index);
            current = current.parent;
        }

        Collections.reverse(route);

        int[] routeArray = new int[route.size()];

        for (int i = 0; i < routeArray.length; i++)
            routeArray[i] = route.get(i);

        return routeArray;
    }

    /**
     * Reset game graph
     */
    public void resetGraph() {
        for (N node : gameGraphs) {
            node.gCost = 0;
            node.hCost = 0;
            node.parent = null;
            node.reached = null;
        }
    }

}


class N implements Comparable<N> {
    public N parent;
    public double gCost, hCost;
    public boolean visited = false;
    public ArrayList<E> adj;
    public int index;
    public Move reached = null;

    public N(int index) {
        adj = new ArrayList<E>();
        this.index = index;
    }

    public N(double gCost, double hCost) {
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public boolean isEqual(N another) {
        return index == another.index;
    }


    public int compareTo(N another) {
        if ((gCost + hCost) < (another.gCost + another.hCost))
            return -1;
        else if ((gCost + hCost) > (another.gCost + another.hCost))
            return 1;

        return 0;
    }

    public String toString() {
        return "" + index;
    }

}

class E {

    public N node;
    public Move move;
    public double cost;

    public E(N node, Move move, double cost) {
        this.node = node;
        this.move = move;
        this.cost = cost;
    }
}



