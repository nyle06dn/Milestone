// GridObject.java
import java.awt.*;

public abstract class GridObject {
    public abstract boolean isBurnable();
    public abstract void step(Grid grid);
    public abstract Color getColor();
    public abstract String getSymbol();
}
