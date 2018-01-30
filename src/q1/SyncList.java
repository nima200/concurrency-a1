package q1;

import java.util.ArrayList;
import java.util.Stack;

public class SyncList {
    private Stack<Circle> data;

    public SyncList() {
        data = new Stack<>();
    }

    /*public synchronized boolean canAdd(int pId, Circle pCircle) {
        if (data.empty()) {
            data.push(pCircle);
            return true;
        }
        Circle lastCircle = data.peek();
        if (lastCircle.getThreadId() != pId &&
                !lastCircle.isCompleted() &&
                lastCircle.intersects(pCircle)) {
            return false;
        } else {
            data.push(pCircle);
            return true;
        }
    }*/

    public int size() {
        return data.size();
    }
}
