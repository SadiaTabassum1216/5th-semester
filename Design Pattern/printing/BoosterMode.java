package printing;

public class BoosterMode extends PrintMode{
	double intensityThreshold;

	public double getIntensityThreshold() {
		return intensityThreshold;
	}

	public void setIntensityThreshold(double intensityThreshold) {
		this.intensityThreshold = intensityThreshold;
	}

	@Override
	public void saveToner() {
		
	}

	@Override
	public void savePage() {
	
	}

	@Override
	public void boost() {
		double threshold=getIntensityThreshold();
		System.out.println("Color intensity is increased where threshold= "+threshold);
	}

}
