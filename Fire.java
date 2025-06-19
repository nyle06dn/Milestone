// Fire.java
// Description: A fire object that spreads to adjacent flammable objects.
import java.awt.*;

public class Fire extends GridObject {
    private int lifeSpan = 3; // Fire lasts for 3 ticks

    // Fire itself is not burnable
    public boolean isBurnable() { return false; }

    // Checks if fire is still active
    public boolean isBurning() { return lifeSpan > 0; }

    // Fire spreads to neighboring flammable objects based on wind and humidity
    public void step(Grid grid) {
        if (lifeSpan-- <= 0) return; // Fire dies after lifespan ends

        // Find current location of this fire object in the grid
        int x = -1, y = -1;
        outer:
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                if (grid.get(i, j) == this) {
                    x = i;
                    y = j;
                    break outer;
                }
            }
        }
        if (x == -1 || y == -1) return; // Location not found, abort

        // Get environmental conditions
        Direction wind = Grid.getWeather().getWind();
        double humidityFactor = Grid.getWeather().getChance();

        // Attempt to spread fire to each neighbor
        for (Direction dir : Direction.values()) {
            int nx = x, ny = y;
            switch (dir) {
                case NORTH: ny--; break;
                case SOUTH: ny++; break;
                case EAST:  nx++; break;
                case WEST:  nx--; break;
            }

            GridObject neighbor = grid.get(nx, ny);
            if (neighbor != null && neighbor.isBurnable()) {
                double baseChance = 0.3;
                if (dir == wind) baseChance += 0.2; // Wind boosts spread chance
                double finalChance = baseChance * humidityFactor;

                if (Math.random() < finalChance) {
                    grid.set(nx, ny, new Fire()); // Spread fire to neighbor
                }
            }
        }
    }

    // Returns color representation of fire
    public Color getColor() { return Color.RED; }

    // Returns symbol representing fire
    public String getSymbol() { return "\uD83D\uDD25"; } // 🔥
}
