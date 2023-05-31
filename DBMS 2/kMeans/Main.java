package kMeans;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "E:\\Codes\\semester 5\\Java\\DBMS\\src\\kMeans\\data.csv";
        String line;
        List<Point> points = new ArrayList<>();

     // Read data from CSV file
		
		  try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			  while((line = br.readLine()) != null) {
				  String[] values = line.split(",");
				  double x = Double.parseDouble(values[0]);
				  double y =Double.parseDouble(values[1]);
				  Point point = new Point(x, y);
				  points.add(point); } }
		  catch (IOException e) {
			  e.printStackTrace(); }
		 

        
        // Run the k-means algorithm
        int k=3;
        KMeans kMeans = new KMeans(k, points);
        kMeans.run();
        
        List<Cluster> clusters = kMeans.getClusters();
        for (Cluster cluster : clusters) {
            System.out.println("Cluster centroid: (" + cluster.getCenter().getX() + ", " + cluster.getCenter().getY() + ")");
            System.out.println("Points:");
            for (Point point : cluster.getPoints()) {
                System.out.println("(" + point.getX() + ", " + point.getY() + ")");
            }
            System.out.println();
        }

		
    }
}