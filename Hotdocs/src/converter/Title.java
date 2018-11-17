package converter;
import java.io.FileInputStream;

import org.apache.poi.xwpf.usermodel.*;

public class Title extends Element{
	public Title(Document doc, String text) {
		super(doc,text);
	}
	
	public void formatAndAppend(String text) {
		try {
			//XWPFDocument doc = new XWPFDocument(new FileInputStream(this.getDoc().getDocName()));
			
			XWPFParagraph title = this.getDoc().getDocxFile().createParagraph();
			title.setAlignment(ParagraphAlignment.CENTER);
			title.setSpacingAfterLines(150);
			
			XWPFRun titleText = title.createRun();
			titleText.setBold(true);
			titleText.setFontFamily("Times New Roman");
			titleText.setFontSize(28);
			titleText.setText(text);
			
		}
		catch(Exception e) {
			System.out.println("Could not open document");
		}
		
	}
}
