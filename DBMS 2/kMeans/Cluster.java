package kMeans;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private int id;
    private Point center;
    private List<Point> points;

    public Cluster(int id, Point center) {
        this.id = id;
        this.center = center;
        points = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Point getCenter() {
        return center;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void clearPoints() {
        points.clear();
    }

    public void recalculateCenter() {
        double sumX = 0.0;
        double sumY = 0.0;
        int n = points.size();

        for (Point point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }

        center = new Point(sumX / n, sumY / n);
    }

    public double calculateShift() {
        Point oldCenter = center;
        recalculateCenter();
        return oldCenter.distanceTo(center);
    }
}
