package accuracy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.lang.Math;

import javax.imageio.ImageIO;

public class Test {

	double[][][] prob = new double[256][256][256];
	double TP;
	double total;
    double[][] confusionMatrix=new double[2][2];


	
	public double TestImage(int start, int end) throws Exception {
		TP=0;
		total=0;
				
		 for (int i = 0; i < 2; i++) {
	         for (int j = 0; j < 2; j++) {
				confusionMatrix[i][j]=0;
			}
	     }
		
		//Read file
		BufferedReader br = new BufferedReader(new FileReader("E:\\Codes\\pixel_values.txt"));
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				for (int k = 0; k < 256; k++) {
					prob[i][j][k] = Double.parseDouble(br.readLine());
				}
			}
		}

		Color white = new Color(255, 255, 255);
		
		//Retrieve test image

		String dir = "E:\\Codes\\ibtd";
		String dir2 = "E:\\Codes\\Mask";

		File directory = new File(dir);
		File[] fileList = directory.listFiles();

		File directory2 = new File(dir2);
		File[] fileList2 = directory2.listFiles();
		
		Arrays.sort(fileList);
		Arrays.sort(fileList2);

		for (int i = start; i <= end; i++) {
			//System.out.println(i);
			
			BufferedImage img = ImageIO.read(fileList[i]);
			BufferedImage img2 = ImageIO.read(fileList2[i]);
			

			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {	
					total++;
					
					//read an image
					int pixel = img.getRGB(x, y);
					Color color = new Color(pixel, true);
					
					//read RGB before masking
					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();
					
					//mask an image
					if (prob[red][green][blue] < .4) {
						img.setRGB(x, y, white.getRGB());
					}
					
					//read RGB after masking
					pixel = img.getRGB(x, y);
					color = new Color(pixel, true); //prediction
					
					int newRed = color.getRed();
					int newGreen = color.getGreen();
					int newBlue = color.getBlue();
					
					//read given masked image
					int pixel2 = img2.getRGB(x, y);
					Color color2 = new Color(pixel2, true); //actual

					int red2 = color2.getRed();
					int green2 = color2.getGreen();
					int blue2 = color2.getBlue();
					
					if(Math.abs(newRed-red2)<=3 && Math.abs(newGreen-green2)<=3 && Math.abs(newBlue-blue2)<=3) {
					//if(newRed==red2 && newGreen==green2 && newBlue==blue2) {
						TP++;
					}	
					
					if(color.equals(white) && color2.equals(white))
						confusionMatrix[0][0]++;
					else if(!color.equals(white) && color2.equals(white))
						confusionMatrix[0][1]++;
					else if(color.equals(white) && !color2.equals(white))
						confusionMatrix[1][0]++;
					else if(!color.equals(white) && !color2.equals(white))
						confusionMatrix[1][1]++;
					
				}
			}
		}
		System.out.println("Testing done...");
		br.close();
		
		double accuracy=(TP*100.0)/total;		
		double precision=confusionMatrix[0][0]/(confusionMatrix[0][0]+confusionMatrix[0][1]);
        double recall=confusionMatrix[0][0]/(confusionMatrix[0][0]+confusionMatrix[1][0]);
        double fScore=(2*precision*recall)/(precision+recall);
        
        System.out.printf("Accuracy: %.2f%%\n",accuracy);
        System.out.println("Precision: "+precision);
        System.out.println("Recall: "+recall);
        
        
		return fScore;
	}
}