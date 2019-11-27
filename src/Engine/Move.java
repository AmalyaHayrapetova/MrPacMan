package Engine;

public enum Move
{
    UP 		{ public Move opposite(){return Move.DOWN;		};},
    RIGHT 	{ public Move opposite(){return Move.LEFT;		};},
    DOWN 	{ public Move opposite(){return Move.UP;		};},
    LEFT 	{ public Move opposite(){return Move.RIGHT;		};},
    NoMove	{ public Move opposite(){return Move.NoMove;	};};

    public abstract Move opposite();
};