package converter;
import java.util.ArrayList;

public abstract class Element {
	private String text;
	private ArrayList<String> points;
	
	public Element(String text) {
		this.text = text;
	}
	
	public Element(ArrayList<String> p) {
		this.points = new ArrayList<String>();
		points = p;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public abstract void formatAndAppend(String s);
	
	public void formatAndAppend(ArrayList<String> s) {
		
	}

	public ArrayList<String> getPoints() {
		return points;
	}
	
}
