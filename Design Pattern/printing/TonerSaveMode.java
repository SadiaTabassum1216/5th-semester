package printing;

public class TonerSaveMode extends PrintMode {
	String tonerSavingLevel;

	@Override
	public void saveToner() {
		if(tonerSavingLevel.equals("high"))
			setHigh();
		else if(tonerSavingLevel.equals("high"))
			setMedium();
		else if(tonerSavingLevel.equals("high")){
			setLow();
				
		}
		
	}

	private void setLow() {
		System.out.println("Toner saving level:Low. Color intensity is reduced ");
	}

	private void setMedium() {
		System.out.println("Toner saving level:Medium. Color intensity is reduced ");
		
	}

	private void setHigh() {
		System.out.println("Toner saving level:High. Color intensity is reduced ");
		
	}

	@Override
	public void savePage() {
		
	}

	@Override
	public void boost() {
		
	}

}
