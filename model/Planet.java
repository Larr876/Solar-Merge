package model;

public class Planet {
    
    protected double positionX;
    protected double positionY;

    protected double radius;

    protected double velocityX;
    protected double velocityY;

    protected PlanetType type;
    protected int score;
    
    public Planet(double x, double y, PlanetType type) {
        this.positionX = x;
        this.positionY = y;
        this.type = type;

        this.radius = type.getRadius();   // เอาขนาดจาก PlanetType enum
        this.score = type.getScore();
        this.velocityX = 0;
        this.velocityY = 0;
    }
    
    public void move() {
        this.positionX += velocityX;
        this.positionY += velocityY;
    }

    public double getX() { return positionX; }
    public double getY() { return positionY; }
    public double getRadius() { return radius; }
    public PlanetType getType() { return type; }

    public void setVelocity(double vx, double vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }
    public double getVelocityX() {return velocityX; }
    public double getVelocityY() {return velocityY; }
    public int getScore() {return  score;}

    public void setPosition(double x, double y){
        this.positionX = x;
        this.positionY = y;
    }
}