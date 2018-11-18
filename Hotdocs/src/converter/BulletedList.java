package converter;
import java.util.ArrayList;
import org.apache.poi.xwpf.usermodel.*;

public class BulletedList extends Element{
	
	public BulletedList(ArrayList<String> points) {
		super(points);
	}
	
	public void formatAndAppend(String s) {
	
	}
	
	public void formatAndAppend(ArrayList<String> points) {
		try {
			XWPFParagraph bulletedList = Document.getInstance().getDocxFile().createParagraph();
			
			XWPFRun bullets = bulletedList.createRun();
			bullets.setFontFamily("Times New Roman");
			bullets.addTab();
	
			for (int i = 0; i < points.size(); i++) {
				String bullet = "• " + points.get(i);
				bullets.setText(bullet);
				if(i != points.size()-1) {
					bullets.addCarriageReturn();
					bullets.addTab();
				}
				else {
					continue;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Could not open document1");
		}
		
	}
}
