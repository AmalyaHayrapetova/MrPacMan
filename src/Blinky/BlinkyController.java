package Blinky;

import Engine.Controller;
import Engine.GameObject;
import Engine.Move;

public class BlinkyController extends Controller {

    static Move pref = Move.LEFT;

    @Override
    protected Move getNext(GameObject obj) {
        if (obj.getNode().neighbourhood.containsKey(pref)) return pref;
        else {
            pref = pref.opposite();
            return pref;
        }
    }
}



