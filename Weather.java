public class Weather {
    private Direction wind = Direction.NORTH; // Default wind direction
    private int humidity = 30; // Humidity level (0–100), affects fire spread probability

    // Sets the wind direction
    public void setWind(Direction d) { this.wind = d; }

    // Sets the humidity level
    public void setHumidity(int level) { this.humidity = level; }

    // Returns the modified chance of fire spread based on humidity
    public double getChance() { return Math.max(0.1, 1.0 - (humidity / 100.0)); }

    // Returns the current wind direction
    public Direction getWind() { return wind; }
}
