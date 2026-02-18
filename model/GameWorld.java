package model;

import java.util.*;

public class GameWorld {

    private List<Planet> planets;
    private int score;
    private int highestScore;
    private PhysicsEngine physics;
    private double spawnX;
    private Planet nextPlanet;
    private boolean gameOver;
    private boolean canDrop;

    public GameWorld() {
        planets = new ArrayList<>();
        score = 0;
        highestScore = 0;
        physics = new PhysicsEngine();
        spawnX = Boundary.getCentreWidth();
        gameOver = false;
        nextPlanet = new Planet(spawnX, Boundary.TOP, getRandomPlanetType());
        canDrop = true;
    }

    public void update() {
        if (isGameOver()) {
            return;
        }
        physics.applyPhysics(planets);
        handleMergePlanet();
        checkGameOver();
        updateScore();
        unlockDrop(); 
    }

    public void updateScore() {
        int tmp = 0;
        for (Planet p : planets) {
            tmp += p.getScore();
        }
        this.score = tmp;
        if (score >= highestScore) {
            highestScore = score;
        }
    }

    public void removePlanet() {
        if (!planets.isEmpty()) {
            planets.remove(planets.size() - 1);
        }
    }

    public List<Planet> getPlanets() {
        return this.planets;
    }

    public int getScore() {
        return this.score;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public PlanetType getRandomPlanetType() {
        Random random = new Random();
        PlanetType[] types = PlanetType.values();
        return types[random.nextInt(types.length - 1)];   //กำหนดค่าเอง
    }

    public void removePlanet(Planet planet) {
        planets.remove(planet);
    }

    public Planet spawnPlanet(double x, double y, PlanetType type) {
        Planet planet = new Planet(x, y, type);
        planets.add(planet);
        return planet;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public void moveSpawnPosition(double dx) {
        spawnX += dx;
        double radius = nextPlanet.getRadius();
        if (spawnX < radius) {
            spawnX = radius;
        }
        if (spawnX > Boundary.RIGHT - radius) {
            spawnX = Boundary.RIGHT - radius;
        }
        if (spawnX < Boundary.LEFT + radius) {
            spawnX = Boundary.LEFT + radius;
        }
        nextPlanet.setPosition(spawnX, Boundary.TOP - radius);
    }
    
    public void reset() {
        planets.clear();
        spawnX = Boundary.getCentreWidth();
        score = 0;
        gameOver = false;
    }

    public void checkGameOver() {
        for (Planet p : planets) {
            if (p.getY() - p.radius < Boundary.TOP) {
                gameOver = true;
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void handleMergePlanet() {
        for (int i = 0; i < planets.size(); i++) {
            for (int j = i+1; j < planets.size(); j++) {
                Planet p = planets.get(i);
                Planet q = planets.get(j);

                Planet newPlanet = physics.mergePlanet(p, q);
                if (newPlanet != null) {
                    planets.add(newPlanet);
                    planets.remove(p);
                    planets.remove(q);
                    return;
                }
            }
        }
    }

    public void dropPlanet() {
        if (!canDrop) return;

        nextPlanet.setPosition(spawnX, Boundary.TOP + nextPlanet.getRadius());
        planets.add(nextPlanet);
        canDrop = false;

        nextPlanet = new Planet(spawnX, Boundary.TOP, getRandomPlanetType());
    }

    public Planet getNextPlanet() {
        return nextPlanet;
    }

    private void unlockDrop() {
        if (planets.isEmpty()) return;

        Planet last = planets.get(planets.size() - 1);

        if (Math.abs(last.getVelocityY()) < 0.5) {
            canDrop = true;
        }
    }
}
