package q1;

import java.util.concurrent.atomic.AtomicBoolean;

public class Lock {
    private AtomicBoolean flag[] = {new AtomicBoolean(), new AtomicBoolean()};
    private volatile int turn = 0;

    public void flagUp(int id) {
        flag[id].set(true);
    }

    public void flagDown(int id) {
        flag[id].set(false);
    }

    public void setTurn(int id) {
        turn = id;
    }

    public boolean busy(int id) {
        return turn == id && flag[1 - id].get();
    }
}
