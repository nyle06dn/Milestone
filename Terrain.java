// Terrain.java
// Description: Abstract class for all terrain types, extends GridObject.
public abstract class Terrain extends GridObject {
    // Default step behavior: terrain objects do not change by default
    @Override
    public void step(Grid grid) {}
}
