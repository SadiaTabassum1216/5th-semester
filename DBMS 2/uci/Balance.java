package uci;

public class Balance {
	@Override
	public String toString() {
		return "Balance [result=" + result + ", leftWeight=" + leftWeight + ", leftDistance=" + leftDistance
				+ ", rightWeight=" + rightWeight + ", rightDistance=" + rightDistance + "]";
	}

	public String result;
	public int leftWeight;
	public int leftDistance;
	public int rightWeight;
	public int rightDistance;
	
	public Balance(String result, int leftWeight, int leftDistance, int rightWeight, int rightDistance) {
		this.result = result;
		this.leftWeight = leftWeight;
		this.leftDistance = leftDistance;
		this.rightWeight = rightWeight;
		this.rightDistance = rightDistance;
	}
	

}