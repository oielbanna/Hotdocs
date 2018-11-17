package converter;
import org.apache.poi.xwpf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;

public abstract class Element {
	private Document doc;
	private String text;
	
	public Element(Document doc, String text) {
		this.doc = doc;
		this.text = text;
	}
	
	
	public Document getDoc() {
		return doc;
	}


	public void setDoc(Document doc) {
		this.doc = doc;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public abstract void formatAndAppend(String s);
	
	
}
