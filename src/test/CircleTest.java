import org.junit.jupiter.api.Test;
import q1.Circle;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void fixBounds() {
        Circle circle = new Circle(10, 10, 4, Color.RED);
        circle.fixBounds(12, 12);
        assertEquals(7, circle.getX());
        assertEquals(7, circle.getY());
    }
}