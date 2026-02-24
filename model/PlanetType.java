package model;

public enum PlanetType {

    // radius รอแก้
    MERCURY(1, 30, 1),
    MARS(2, 35, 2),
    VENUS(3, 40, 4),
    EARTH(4, 45, 8),
    NEPTUNE(5, 55, 16),
    URANUS(6, 70, 32),
    SATURN(7, 80, 64),
    JUPITER(8, 100, 128),
    SUN(9, 130, 256);

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
    public boolean isSpawnable() {
        return this != SUN;
    }
}
