// Description: A non-burnable terrain object representing water.
import java.awt.*;

public class Water extends Terrain {
    public boolean isBurnable() { return false; }
    public Color getColor() { return Color.BLUE; }
    public String getSymbol() { return "\uD83D\uDCA7"; } // 💧
}
