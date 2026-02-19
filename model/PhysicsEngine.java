package model;

import java.util.List;

public class PhysicsEngine {

    private final double gravity = 2;     //กำหนดค่าเอง
    private final double frictionX = 0.5;
    private final double frictionY = 0.7;

    public PhysicsEngine() {}

    public void applyPhysics(List<Planet> pla) {

        for (Planet p : pla) {

            double vx = p.getVelocityX() * this.frictionX;
            double vy = p.getVelocityY();

            boolean onFloor = (p.getY() + p.getRadius() >= Boundary.FLOOR - 0.5);

            if (!onFloor) {   // ใส่ gravity เฉพาะตอนยังลอยอยู่
                vy += this.gravity;
            } else {
                vy = 0;
            }
            vy *= this.frictionY;

            // 0.001 แก้ได้ตามความเหมาะสม เอาไว้กันไม่ให้มันเคลื่อนที่โดยไม่หยุด
            if (Math.abs(vx) < 0.2) {
                vx = 0;
            }
            if (onFloor && Math.abs(vy) < 0.2) {
                vy = 0;
            }
            p.setVelocity(vx, vy);
        }

        applySlide(pla);

        for (int i = 0; i < pla.size(); i++) {
            for (int j = i + 1; j < pla.size(); j++) {

                Planet p = pla.get(i);
                Planet q = pla.get(j);

                if (planetCollision(p, q)) {
                    resolveCollision(p, q);
                }
            }
        }
        for (Planet p : pla) {
            p.move();
            handleWallCollision(p);
        }
    }

    // กั้นการชนแล้วยุบเข้ากันเอง
    public void resolveCollision(Planet p, Planet q) {
        double dx = p.getX() - q.getX();
        double dy = p.getY() - q.getY();
        double dis = Math.sqrt((dx * dx) + (dy * dy));

        if (dis == 0) {
            return;
        }

        double overlap = ((p.getRadius() + q.getRadius()) - dis);
        if (overlap > 0.01) {
            double nx = dx / dis;
            double ny = dy / dis;

            p.setPosition(p.getX() + nx * overlap / 2, p.getY() + ny * overlap / 2);
            q.setPosition(q.getX() - nx * overlap / 2, q.getY() - ny * overlap / 2);

            double rvx = p.getVelocityX() - q.getVelocityX();
            double rvy = p.getVelocityY() - q.getVelocityY();
            double velInNormal = rvx * nx + rvy * ny;

            if (velInNormal > 0) {
                return;
            }

            // 0.3 แก้ได้ตามความเหมาะสม มันคือความเด้ง เยอะยิ่งเด้งมาก
            double restitution = 0.1;
            // mass เทียม
            double massP = p.getRadius() * p.getRadius();
            double massQ = q.getRadius() * q.getRadius();

            double j = -(1 + restitution) * velInNormal;
            j = j / (1 / massP + 1 / massQ);

            p.setVelocity(
                    p.getVelocityX() + (j * nx / massP),
                    p.getVelocityY() + (j * ny / massP));
            q.setVelocity(
                    q.getVelocityX() - (j * nx / massQ),
                    q.getVelocityY() - (j * ny / massQ));
        }
    }

    // เช็คว่าชนแล้วหรือยัง
    public boolean planetCollision(Planet p1, Planet p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        double r = p1.getRadius() + p2.getRadius();
        return ((r * r) >= (dx * dx + dy * dy));
    }

    // เช็คว่าออกขอบแล้วหรือยัง
    public boolean wallCollision(Planet p) {
        return (Boundary.isOutOfBound(p.getX(), p.getY()));
    }

    // เช็ตว่าอยู่ข้างบนไหม สำหรับ applySlide
    private boolean isOnTopOfPlanet(Planet p, Planet below) {
        return (p.getY() < below.getY() && planetCollision(p, below));
    }

    // การไหลเมื่อ drop ข้างบนดาวอื่นๆ
    private void applySlide(List<Planet> planet) {
        for (int i = 0; i < planet.size(); i++) {
            Planet p = planet.get(i);

            for (int j = 0; j < planet.size(); j++) {
                if (i == j)
                    continue;

                Planet below = planet.get(j);

                if (isOnTopOfPlanet(p, below)) {
                    double dx = p.getX() - below.getX();
                    double dy = p.getY() - below.getY();
                    double dis = Math.sqrt(dx * dx + dy * dy);

                    if (dis == 0)
                        continue;

                    double nx = dx / dis;
                    double ny = dy / dis;

                    double tx = -ny;
                    double ty = nx;

                    double tangentForce = (tx * 0.3) + (ty * this.gravity); //แก้ความแรงไหล

                    // 0.1 แก้ได้ตามความเหมาะสม มันคือแรงที่ต้องใช้เป็นอย่างน้อยในการขยับ Planet
                    double frictionThreshold = 0.2;
                    if (Math.abs(tangentForce) > frictionThreshold) {
                        // 0.5 แก้ได้ตามความเหมาะสม ลดความแรงของแรงไหล เพื่อไม่ให้เร่งเกินไป
                        double slideForce = tangentForce * 0.5;
                        p.setVelocity(
                                p.getVelocityX() + tx * slideForce,
                                p.getVelocityY() + ty * slideForce);
                    }
                    break;
                }
            }
        }
    }

    // กันการทะลุกำแพง
    public void handleWallCollision(Planet p) {
        // 0.3 ปรับแก้ได้ตามความเหมาะสม มันคือความเด้ง เยอะยิ่งเด้งมาก
        double restitution = 0.3;
        // Left side
        if (p.getX() - p.getRadius() <= Boundary.LEFT) {
            p.setVelocity(-p.getVelocityX() * restitution, p.getVelocityY());
            p.setPosition(Boundary.LEFT + p.getRadius(), p.getY());

            // Right side
        } if (p.getX() + p.getRadius() >= Boundary.RIGHT) {
            p.setVelocity(-p.getVelocityX() * restitution, p.getVelocityY());
            p.setPosition(Boundary.RIGHT - p.getRadius(), p.getY());

            // down side
        } if (p.getY() + p.getRadius() >= Boundary.FLOOR) {
            p.setVelocity(p.getVelocityX(), 0);
            p.setPosition(p.getX(), Boundary.FLOOR - p.getRadius());

            if (p.getVelocityY() > 0) {
                p.setVelocity(p.getVelocityX(), 0);
            }
        }
    }

    // return ดาวใหม่ที่รอเรียก spawn
    public Planet mergePlanet(Planet p1, Planet p2) {
        if (planetCollision(p1, p2) && p1.getType().getLevel() == p2.getType().getLevel() && p1.getType() != PlanetType.SUN) {

            double newX = (p1.getX() + p2.getX()) / 2;
            double newY = (p1.getY() + p2.getY()) / 2;
            PlanetType pType = p1.getType().upgrade();
            return (new Planet(newX, newY, pType));
        }
        return null;
    }
}
