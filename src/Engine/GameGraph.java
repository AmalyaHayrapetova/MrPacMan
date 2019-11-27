package Engine;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// taken from MSPacMan sources
/*
 * Stores the actual mazes, each of which is simply a connected graph. The differences between the mazes are the connectivity
 * and the x,y coordinates (used for drawing or to compute the Euclidean distance. There are 3 built-in distance functions in
 * total: Euclidean, Manhattan and Dijkstra's shortest path distance. The latter is pre-computed and loaded, the others are
 * computed on the fly whenever getNextDir(-) is called.
 */
public final class GameGraph {
    private int[] shortestPathDistances, pillIndices, powerPillIndices, junctionIndices;    //Information for the controllers
    private int initialPacManNodeIndex, lairNodeIndex, initialGhostNodeIndex;                //Maze-specific information
    private Node[] graph;                                                                //The actual maze, stored as a graph (set of nodes)
    private String name;                                                                //Name of the Maze

    /*
     * Each maze is stored as a (connected) graph: all nodes have neighbours, stored in an array of length 4. The
     * index of the array associates the direction the neighbour is located at: '[up,right,down,left]'.
     * For instance, if node '9' has neighbours '[-1,12,-1,6]', you can reach node '12' by going right, and node
     * 6 by going left. The directions returned by the controllers should thus be in {0,1,2,3} and can be used
     * directly to determine the next node to go to.
     */
    public GameGraph(String nodesResourcePath) {
        loadNodes(nodesResourcePath);
    }

    public Node[] getNodes() {
        return graph;
    }

    public Node getInitialGhostNode() {
        return graph[initialGhostNodeIndex];
    }

    public Node getInitialPacManNode() {
        return graph[initialPacManNodeIndex];
    }

    public int[] getPillIndices() {
        return pillIndices;
    }

    public int[] getPowerPillIndices() {
        return powerPillIndices;
    }

    //Loads all the nodes from files and initialises all maze-specific information.
    private void loadNodes(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String input = br.readLine();

            //preamble
            String[] pr = input.split("\t");

            this.name = pr[0];
            this.initialPacManNodeIndex = Integer.parseInt(pr[1]);
            this.lairNodeIndex = Integer.parseInt(pr[2]);
            this.initialGhostNodeIndex = Integer.parseInt(pr[3]);
            this.graph = new Node[Integer.parseInt(pr[4])];
            this.pillIndices = new int[Integer.parseInt(pr[5])];
            this.powerPillIndices = new int[Integer.parseInt(pr[6])];
            this.junctionIndices = new int[Integer.parseInt(pr[7])];

            int nodeIndex = 0;
            int pillIndex = 0;
            int powerPillIndex = 0;
            int junctionIndex = 0;

            input = br.readLine();

            while (input != null) {
                String[] nd = input.split("\t");

                Node node = new Node(Integer.parseInt(nd[0]), Integer.parseInt(nd[1]), Integer.parseInt(nd[2]), Integer.parseInt(nd[7]), Integer.parseInt(nd[8]),
                        new int[]{Integer.parseInt(nd[3]), Integer.parseInt(nd[4]), Integer.parseInt(nd[5]), Integer.parseInt(nd[6])});

                graph[nodeIndex++] = node;

                if (node.pillIndex >= 0)
                    pillIndices[pillIndex++] = node.nodeIndex;
                else if (node.powerPillIndex >= 0)
                    powerPillIndices[powerPillIndex++] = node.nodeIndex;

                if (node.numNeighbouringNodes > 2)
                    junctionIndices[junctionIndex++] = node.nodeIndex;

                input = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Calculate Manhattan Distance
     * */
    public int getManhattanDistance(int fromNodeIndex, int toNodeIndex) {
        return (int) (Math.abs(graph[fromNodeIndex].x - graph[toNodeIndex].x) + Math.abs(graph[fromNodeIndex].y - graph[toNodeIndex].y));
    }




//    /*
//     * Loads the shortest path distances which have been pre-computed. The data contains the shortest distance from
//     * any node in the maze to any other node. Since the graph is symmetric, the symmetries have been removed to preserve
//     * memory and all distances are stored in a 1D array; they are looked-up using getDistance(-).
//     */
//    private void loadDistances(String fileName)
//    {
//        this.shortestPathDistances=new int[((graph.length*(graph.length-1))/2)+graph.length];
//
//        try
//        {
//            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(pathDistances+System.getProperty("file.separator")+fileName)));
//            String input=br.readLine();
//
//            int index=0;
//
//            while(input!=null)
//            {
//                shortestPathDistances[index++]=Integer.parseInt(input);
//                input=br.readLine();
//            }
//        }
//        catch(IOException ioe)
//        {
//            ioe.printStackTrace();
//        }
//    }
}