package printing;

public class PageSaveMode extends PrintMode{
	String renderPreview;

	@Override
	public void saveToner() {
		
	}

	@Override
	public void savePage() {
		int page=getNumber_of_pages();
		System.out.println(page+"is adjusted.");
	}

	@Override
	public void boost() {
		
	}

}
