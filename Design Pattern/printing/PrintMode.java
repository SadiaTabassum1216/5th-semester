package printing;

public abstract class PrintMode {
	private int number_of_pages;
	private String page_size;
	private String orientation;
	private double color_intensity;
	private int cost_per_page;
	
	public int getNumber_of_pages() {
		return number_of_pages;
	}
	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public double getColor_intensity() {
		return color_intensity;
	}
	public void setColor_intensity(double color_intensity) {
		this.color_intensity = color_intensity;
	}
	public int getCost_per_page() {
		return cost_per_page;
	}
	public void setCost_per_page(int cost_per_page) {
		this.cost_per_page = cost_per_page;
	}
	
	public abstract void saveToner();
	public abstract void savePage();
	public abstract void boost();
	
}
