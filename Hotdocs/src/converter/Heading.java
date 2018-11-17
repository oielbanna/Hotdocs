package converter;
import java.io.FileInputStream;
import org.apache.poi.xwpf.usermodel.*;

public class Heading extends Element{
	public Heading(Document doc, String text) {
		super(doc,text);
	}
	
	public void formatAndAppend(String text) {
		try {
			XWPFParagraph heading = this.getDoc().getDocxFile().createParagraph();
			heading.setSpacingAfterLines(50);
			
			XWPFRun headingText = heading.createRun();
			headingText.setBold(true);
			headingText.setFontFamily("Times New Roman");
			headingText.setFontSize(13);
			headingText.setText(text);
		}
		catch(Exception e) {
			System.out.println("Could not open document");
		}
		
	}
}
