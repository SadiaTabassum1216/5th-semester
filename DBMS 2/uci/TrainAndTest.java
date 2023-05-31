package uci;

import java.util.ArrayList;

public class TrainAndTest {

	public int total;
	int totalBalanced=0,totalLeft=0,totalRight=0;
	double mean[][]= new double[3][4];
	double SD[][]= new double[3][4];
	double probability[][]= new double[3][4];
	
	public String max(double a, double b, double c) {
		if(a>=b && a>=c)
			return "B";
		else if(b>a && b>c)
			return "L";
		else
			return "R";
	}
	
	public void training(int start, int end, ArrayList<Balance> list) throws Exception {

		total=list.size();
		
//		for(Balance bal:list) {
//			System.out.println(bal.toString());
//		}
	
		  for (int i = 0; i < 2; i++) {
		         for (int j = 0; j < 2; j++) {
					mean[i][j]=0;
					SD[i][j]=0;
				}
		     }
		
		for (int i = 0; i < list.size(); i++) {
			if (i >= start && i <= end)
				continue;
			
			Balance b=list.get(i);
			
			//sum
			if(b.result.equals("B")) {
				totalBalanced++;
				mean[0][0]+=b.leftWeight;
				mean[0][1]+=b.leftDistance;
				mean[0][2]+=b.rightWeight;
				mean[0][3]+=b.rightDistance;
			}
				
			else if(b.result.equals("L")) {
				totalLeft++;
				mean[1][0]+=b.leftWeight;
				mean[1][1]+=b.leftDistance;
				mean[1][2]+=b.rightWeight;
				mean[1][3]+=b.rightDistance;
			}
				
			else if(b.result.equals("R")) {
				totalRight++;
				mean[2][0]+=b.leftWeight;
				mean[2][1]+=b.leftDistance;
				mean[2][2]+=b.rightWeight;
				mean[2][3]+=b.rightDistance;
			}
						
		}
		
		//mean
		for(int j=0;j<4;j++) {
			mean[0][j]/=totalBalanced;
			mean[1][j]/=totalLeft;
			mean[2][j]/=totalRight;
		}
		
		//standard deviation
		for (int i = 0; i < list.size(); i++) {
			if (i >= start && i <= end)
				continue;
			
			Balance b=list.get(i);
			
			if(b.result.equals("B")) {
				SD[0][0]+=Math.pow(b.leftWeight-mean[0][0], 2);
				SD[0][1]+=Math.pow(b.leftDistance-mean[0][1], 2);
				SD[0][2]+=Math.pow(b.rightWeight-mean[0][2], 2);
				SD[0][3]+=Math.pow(b.rightDistance-mean[0][3], 2);
			}
			
			else if(b.result.equals("L")) {
				SD[1][0]+=Math.pow(b.leftWeight-mean[1][0], 2);
				SD[1][1]+=Math.pow(b.leftDistance-mean[1][1], 2);
				SD[1][2]+=Math.pow(b.rightWeight-mean[1][2], 2);
				SD[1][3]+=Math.pow(b.rightDistance-mean[1][3], 2);
			}
			
			else if(b.result.equals("R")) {
				SD[2][0]+=Math.pow(b.leftWeight-mean[2][0], 2);
				SD[2][1]+=Math.pow(b.leftDistance-mean[2][1], 2);
				SD[2][2]+=Math.pow(b.rightWeight-mean[2][2], 2);
				SD[2][3]+=Math.pow(b.rightDistance-mean[2][3], 2);
			}
			
			}
		
		for(int j=0;j<4;j++) {
			SD[0][j]/=(totalBalanced-1);
			SD[0][j]=Math.pow(SD[0][j], 0.5);
			
			SD[1][j]/=(totalLeft-1);
			SD[1][j]=Math.pow(SD[1][j], 0.5);
			
			SD[2][j]/=(totalRight-1);
			SD[1][j]=Math.pow(SD[1][j], 0.5);
			
		}
			

	}



	public double test(int start, int end, ArrayList<Balance> list) throws Exception {
	  //  double[][] confusionMatrix=new double[2][2];
	    double probB,probL,probR;
	    String prediction;
	    double cal;
	    double correct=0, wrong=0;
//	    System.out.println(total);
//	    System.out.println(totalLeft);
//	    System.out.println(totalRight);
//	    System.out.println(totalBalanced);
	    
//	    for (int i = 0; i < 2; i++) {
//	         for (int j = 0; j < 2; j++) {
//				confusionMatrix[i][j]=0;
//			}
//	     }
	    
	    for (int i = start; i <= end; i++) {
	    	Balance b=list.get(i);
	    	
	    	//balanced
	    		//left weight
	    		probability[0][0]=1/(2*3.1416);
	    		probability[0][0]=Math.pow(probability[0][0], 0.5);
	    		probability[0][0]/=SD[0][0];
	    		cal=(b.leftWeight-mean[0][0]);
	    		cal=cal/SD[0][0];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[0][0]*=cal;
	    		
	    		//left distance
	    		probability[0][1]=1/(2*3.1416);
	    		probability[0][1]=Math.pow(probability[0][1], 0.5);
	    		probability[0][1]/=SD[0][1];
	    		cal=(b.leftDistance-mean[0][1]);
	    		cal=cal/SD[0][1];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[0][1]*=cal;
	    		
	    		//right weight
	    		probability[0][2]=1/(2*3.1416);
	    		probability[0][2]=Math.pow(probability[0][2], 0.5);
	    		probability[0][2]/=SD[0][2];
	    		cal=(b.rightWeight-mean[0][2]);
	    		cal=cal/SD[0][2];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[0][2]*=cal;
	    		
	    		//right distance
	    		probability[0][3]=1/(2*3.1416);
	    		probability[0][3]=Math.pow(probability[0][3], 0.5);
	    		probability[0][3]/=SD[0][3];
	    		cal=(b.rightDistance-mean[0][3]);
	    		cal=cal/SD[0][3];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[0][3]*=cal;
	    		
	    		probB=(probability[0][0]*probability[0][1]*probability[0][2]*probability[0][3]*totalBalanced)/total;
	    		
	    		
	    	//left
	    		//left weight
	    		probability[1][0]=1/(2*3.1416);
	    		probability[1][0]=Math.pow(probability[1][0], 0.5);
	    		probability[1][0]/=SD[1][0];
	    		cal=(b.leftWeight-mean[1][0]);
	    		cal=cal/SD[1][0];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[1][0]*=cal;
	    		
	    		//left distance
	    		probability[1][1]=1/(2*3.1416);
	    		probability[1][1]=Math.pow(probability[1][1], 0.5);
	    		probability[1][1]/=SD[1][1];
	    		cal=(b.leftDistance-mean[1][1]);
	    		cal=cal/SD[1][1];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[1][1]*=cal;
	    		
	    		//right weight
	    		probability[1][2]=1/(2*3.1416);
	    		probability[1][2]=Math.pow(probability[1][2], 0.5);
	    		probability[1][2]/=SD[1][2];
	    		cal=(b.rightWeight-mean[1][2]);
	    		cal=cal/SD[1][2];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[1][2]*=cal;
	    		
	    		//right distance
	    		probability[1][3]=1/(2*3.1416);
	    		probability[1][3]=Math.pow(probability[1][3], 0.5);
	    		probability[1][3]/=SD[1][3];
	    		cal=(b.rightDistance-mean[1][3]);
	    		cal=cal/SD[1][3];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[1][3]*=cal;
	    		
	    		probL=(probability[1][0]*probability[1][1]*probability[1][2]*probability[1][3]*totalLeft)/total;
	    		
	    		
	    	//Right
	    		//left weight
	    		probability[2][0]=1/(2*3.1416);
	    		probability[2][0]=Math.pow(probability[2][0], 0.5);
	    		probability[2][0]/=SD[2][0];
	    		cal=(b.leftWeight-mean[2][0]);
	    		cal=cal/SD[2][0];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[2][0]*=cal;
	    		
	    		//left distance
	    		probability[2][1]=1/(2*3.1416);
	    		probability[2][1]=Math.pow(probability[2][1], 0.5);
	    		probability[2][1]/=SD[2][1];
	    		cal=(b.leftDistance-mean[2][1]);
	    		cal=cal/SD[2][1];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[2][1]*=cal;
	    		
	    		//right weight
	    		probability[2][2]=1/(2*3.1416);
	    		probability[2][2]=Math.pow(probability[2][2], 0.5);
	    		probability[2][2]/=SD[2][2];
	    		cal=(b.rightWeight-mean[2][2]);
	    		cal=cal/SD[2][2];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[2][2]*=cal;
	    		
	    		//right distance
	    		probability[2][3]=1/(2*3.1416);
	    		probability[2][3]=Math.pow(probability[2][3], 0.5);
	    		probability[2][3]/=SD[2][3];
	    		cal=(b.rightDistance-mean[2][3]);
	    		cal=cal/SD[2][3];
	    		cal=cal*cal;
	    		cal=-0.5*cal;
	    		cal=Math.pow(2.718, cal);
	    		probability[2][3]*=cal;
	    		
	    		probR=(probability[2][0]*probability[2][1]*probability[2][2]*probability[2][3]*totalRight)/total;
	    		
	    		prediction=max(probB,probL,probR);
	    		//System.out.println(prediction+"\t"+b.result);
	    		
	    		if(prediction.equals(b.result))
	    			correct++;
	    		else
	    			wrong++;    	
	    	
	    }
	 //   System.out.println(correct+"\t"+wrong);
	    
	    double accuracy=correct/(correct+wrong);
	    
		return accuracy;
	
	}
}
