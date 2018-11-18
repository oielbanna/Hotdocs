package converter;

import org.apache.poi.xwpf.usermodel.*;

public class Body extends Element{
	
	public Body(String text) {
		super(text);
	}
	
	public void formatAndAppend(String text) {
		try {
			XWPFParagraph body = Document.getInstance().getDocxFile().createParagraph();
			body.setSpacingAfterLines(50);
			
			XWPFRun bodyText = body.createRun();
			bodyText.setFontFamily("Times New Roman");
			bodyText.setText(text);
		}
		catch(Exception e) {
			System.out.println("Could not open document");
		}
		
	}
}
