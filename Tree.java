// Description: A burnable terrain object that represents a tree in the forest.
import java.awt.*;
public class Tree extends Terrain {
    public boolean isBurnable() { return true; }
    public void step(Grid grid) {}
    public Color getColor() { return new Color(34,139,34); }
    public String getSymbol() { return "\uD83C\uDF32"; } // 🌲
}
