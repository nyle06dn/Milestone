// Description: A burnable terrain object representing a flower.

import java.awt.*;

public class Flower extends Terrain {
    public boolean isBurnable() { return true; }
    public void step(Grid grid) {}
    public Color getColor() { return Color.PINK; }
    public String getSymbol() { return "\uD83C\uDF38"; } // 🌸
}
