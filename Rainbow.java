// Description: A non-burnable terrain object representing a rainbow.
import java.awt.*;

public class Rainbow extends Terrain {
    public boolean isBurnable() { return false; }
    public void step(Grid grid) {}
    public Color getColor() { return Color.MAGENTA; }
    public String getSymbol() { return "\uD83C\uDF08"; } // 🌈
}
