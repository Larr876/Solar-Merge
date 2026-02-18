package model;

public enum PlanetType {

    // radius รอแก้
    MERCURY(1, 20, 1),
    MARS(2, 25, 2),
    VENUS(3, 30, 4),
    EARTH(4, 35, 8),
    NEPTUNE(5, 40, 16),
    URANUS(6, 45, 32),
    SATURN(7, 55, 64),
    JUPITER(8, 70, 128),
    SUN(9, 100, 256);

    private final int level;
    private final int radius;
    private final int score;

    PlanetType(int level, int radius, int score) {
        this.level = level;
        this.radius = radius;
        this.score = score;
    }

    public int getLevel() {
        return this.level;
    }
    public int getRadius(){
        return this.radius;
    }
    public int getScore() {
        return this.score;
    }

    // upgrade Level
    public PlanetType upgrade() {
        PlanetType[] values = PlanetType.values();
        int nextIndex = this.ordinal() + 1;

        if (nextIndex >= values.length) {
            return this;
        }
        return values[nextIndex];
    }
}
