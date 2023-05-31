package kMeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    private int k; // number of clusters
    private List<Point> points; // list of data points
    private List<Cluster> clusters; // list of clusters

    public KMeans(int k, List<Point> points) {
        this.k = k;
        this.points = points;
        clusters = new ArrayList<>();
        init();
    }

    // Initialize the clusters with random points
    private void init() {
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            Point center = points.get(random.nextInt(points.size()));
            clusters.add(new Cluster(i, center));
        }
    }

    // Assign each point to the nearest cluster
    private void assignPointsToClusters() {
        for (Point point : points) {
            Cluster nearest = null;
            double minDist = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double dist = point.distanceTo(cluster.getCenter());
                if (dist < minDist) {
                    minDist = dist;
                    nearest = cluster;
                }
            }
            nearest.addPoint(point);
        }
    }

    // Recalculate the centers of the clusters
    private void recalculateCenters() {
        for (Cluster cluster : clusters) {
            cluster.recalculateCenter();
        }
    }

    // Run the k-means algorithm
    public void run() {
        boolean converged = false;
        while (!converged) {
            // Clear the points from the clusters
            for (Cluster cluster : clusters) {
                cluster.clearPoints();
            }

            // Assign each point to the nearest cluster
            assignPointsToClusters();

            // Recalculate the centers of the clusters
            double maxShift = 0.0;
            for (Cluster cluster : clusters) {
                double shift = cluster.calculateShift();
                if (shift > maxShift) {
                    maxShift = shift;
                }
                recalculateCenters();
            }

            // Check if the clusters have converged
            if (maxShift < 0.001) {
                converged = true;
            }
        }
    }

    // Get the list of clusters
    public List<Cluster> getClusters() {
        return clusters;
    }
}
