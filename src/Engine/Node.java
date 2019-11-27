package Engine;

import java.util.EnumMap;

// taken from MSPacMan sources
/*
 * The class is a data structure used to represent a node in the graph. Each maze is a set of connected nodes and
 * each node has some adjacent nodes that correspond to the enumeration Move. Each node stores all the information
 * required to check and update the current state of the game.
 */
public final class Node
{
    public final int x,y,nodeIndex,pillIndex,powerPillIndex,numNeighbouringNodes;
    public final EnumMap<Move, Integer> neighbourhood = new EnumMap<Move, Integer>(Move.class);
    public EnumMap<Move,Move[]> allPossibleMoves=new EnumMap<Move,Move[]>(Move.class);
    public EnumMap<Move,int[]> allNeighbouringNodes=new EnumMap<Move,int[]>(Move.class);
    public EnumMap<Move,EnumMap<Move, Integer>> allNeighbourhoods=new EnumMap<Move,EnumMap<Move, Integer>>(Move.class);

    /*
     * Instantiates a new node.
     */
    public Node(int nodeIndex,int x,int y,int pillIndex,int powerPillIndex,int[] _neighbourhood)
    {
        this.nodeIndex=nodeIndex;
        this.x=x;
        this.y=y;
        this.pillIndex=pillIndex;
        this.powerPillIndex=powerPillIndex;

        Move[] Moves=Move.values();

        for(int i=0;i<_neighbourhood.length;i++)
            if(_neighbourhood[i]!=-1)
                neighbourhood.put(Moves[i],_neighbourhood[i]);

        numNeighbouringNodes=neighbourhood.size();

        for(int i=0;i<Moves.length;i++)
            if(neighbourhood.containsKey(Moves[i]))
            {
                EnumMap<Move, Integer> tmp=new EnumMap<Move, Integer>(neighbourhood);
                tmp.remove(Moves[i]);
                allNeighbourhoods.put(Moves[i].opposite(),tmp);
            }


        int[] neighbouringNodes=new int[numNeighbouringNodes];
        Move[] possibleMoves=new Move[numNeighbouringNodes];

        int index=0;

        for(int i=0;i<Moves.length;i++)
            if(neighbourhood.containsKey(Moves[i]))
            {
                neighbouringNodes[index]=neighbourhood.get(Moves[i]);
                possibleMoves[index]=Moves[i];
                index++;
            }

        for(int i=0;i<Moves.length;i++)//check all Moves
        {
            if(neighbourhood.containsKey(Moves[i].opposite()))//Move is part of neighbourhood
            {
                int[] tmpNeighbouringNodes=new int[numNeighbouringNodes-1];
                Move[] tmpPossibleMoves=new Move[numNeighbouringNodes-1];

                index=0;

                for(int j=0;j<Moves.length;j++)//add all Moves to neighbourhood except the one above
                {
                    if(Moves[j]!=Moves[i].opposite() && neighbourhood.containsKey(Moves[j]))
                    {
                        tmpNeighbouringNodes[index]=neighbourhood.get(Moves[j]);
                        tmpPossibleMoves[index]=Moves[j];
                        index++;
                    }
                }

                allNeighbouringNodes.put(Moves[i],tmpNeighbouringNodes);
                allPossibleMoves.put(Moves[i],tmpPossibleMoves);
            }
        }

        allNeighbouringNodes.put(Move.NoMove,neighbouringNodes);
        allPossibleMoves.put(Move.NoMove,possibleMoves);
    }



}