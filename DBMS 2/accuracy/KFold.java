package accuracy;

import java.io.File;

public class KFold {

	public void fold(int numOfFold) throws Exception {
		int k = numOfFold;
		int total;
		int each;
		double fscore;

		String dir = "E:\\Codes\\ibtd";
		File directory = new File(dir);
		File[] fileList = directory.listFiles();
		total = fileList.length-1;

		System.out.println("Total entry: "+ total);

		each = total / k;

		int i;
		int start = 0;
		int end = -1;
		for (i = 0; i < k; i++) {
			System.out.println("\nFold "+(i+1)+":");
			start = end + 1;
			end = start + each-1;

			if(end>fileList.length)
				end=fileList.length-1;
			
			System.out.println("Start: "+start+"\tEnd: "+end);

			Train train = new Train();
			train.training(start, end);

			Test test = new Test();
			fscore = test.TestImage(start, end);

			System.out.println("F score: " + fscore);
		}

	}

}
