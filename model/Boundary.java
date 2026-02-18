package model;

public class Boundary {
    public static final double TOP = 150;      //กำหนดค่าเอง
    public static final double FLOOR = 700;
    public static final double LEFT = 300;
    public static final double RIGHT = 1000;

    public static double getCentreWidth() {
        return (RIGHT + LEFT)/2;
    }
    public static boolean isOutOfBound(double x, double y) {
        return x > RIGHT || x < LEFT || y < TOP || y > FLOOR;
    }
}
