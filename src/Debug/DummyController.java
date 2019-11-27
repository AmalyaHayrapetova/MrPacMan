package Debug;

import Engine.Controller;
import Engine.GameObject;
import Engine.Move;

public class DummyController extends Controller {

    @Override
    protected Move getNext(GameObject object) {
        return Move.NoMove;
    }
}
