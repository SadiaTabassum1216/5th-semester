package accuracy;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.awt.Color;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Train {

	public static double divide(double a, double b) {
		if (b == 0.0)
			return 0;
		else
			return a / b;
	}

	public void training(int start, int end) throws Exception {

		double skin_ratio;
		double nonskin_ratio;

		FileWriter writer = new FileWriter("E:\\Codes\\pixel_values.txt");

		double[][][] skin = new double[256][256][256];
		double[][][] nonskin = new double[256][256][256];
		double[][][] prob = new double[256][256][256];

		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				for (int k = 0; k < 256; k++) {
					skin[i][j][k] = 0;
					nonskin[i][j][k] = 0;
				}
			}
		}

//Reading the image
		String dir = "E:\\Codes\\Mask";
		String dir2 = "E:\\Codes\\ibtd";

		File directory = new File(dir);
		File[] fileList = directory.listFiles();

		File directory2 = new File(dir2);
		File[] fileList2 = directory2.listFiles();
		Arrays.sort(fileList);
		Arrays.sort(fileList2);

		for (int i = 0; i < fileList.length-1; i++) {
			if (i >= start && i <= end)
				continue;
			
			//System.out.println(i);

			BufferedImage img = ImageIO.read(fileList[i]);
			BufferedImage img2 = ImageIO.read(fileList2[i]);
			
			

			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {

					// Retrieving contents of a pixel
					int pixel = img.getRGB(x, y);
					int pixel2 = img2.getRGB(x, y);

					// Creating a Color object from pixel value
					Color color = new Color(pixel, true);
					Color color2 = new Color(pixel2, true);

					// Retrieving the R G B values
					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();

					int red2 = color2.getRed();
					int green2 = color2.getGreen();
					int blue2 = color2.getBlue();

					if (red > 240 && green > 240 && blue > 240)
						nonskin[red2][green2][blue2]++;

					else
						skin[red2][green2][blue2]++;

				}
			}

			// System.out.println("Image " + count);
		}

		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				for (int k = 0; k < 256; k++) {
					skin_ratio = skin[i][j][k];
					nonskin_ratio = nonskin[i][j][k];
					prob[i][j][k] = divide(skin_ratio, nonskin_ratio);
					writer.write(String.valueOf(prob[i][j][k]) + "\n");

				}
			}
		}

		writer.close();

		System.out.println("Training done...");
	}
}