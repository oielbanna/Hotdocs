package converter;

import org.apache.poi.xwpf.usermodel.*;

public class Heading extends Element{
	public Heading(String text) {
		super(text);
	}
	
	public void formatAndAppend(String text) {
		try {
			XWPFParagraph heading = Document.getInstance().getDocxFile().createParagraph();
			heading.setSpacingAfterLines(50);
			heading.setSpacingBeforeLines(200);
			
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
