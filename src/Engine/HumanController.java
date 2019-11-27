package Engine;

import java.awt.event.KeyEvent;

public class HumanController extends Controller {

    public Move getNext(GameObject object)
    {
        switch(object.getGame().getKeyBoardInput().getKey())
        {
            case KeyEvent.VK_UP: 	return Move.UP;
            case KeyEvent.VK_RIGHT: return Move.RIGHT;
            case KeyEvent.VK_DOWN: 	return Move.DOWN;
            case KeyEvent.VK_LEFT: 	return Move.LEFT;
            default: 				return Move.NoMove;
        }
    }
}
