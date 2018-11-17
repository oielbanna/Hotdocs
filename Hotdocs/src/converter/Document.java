package converter;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Document{
	private String docName;
	private XWPFDocument docxFile;
	private FileOutputStream stream;
	
	
	public Document(String name) {
		this.docName = name;
		this.docxFile = new XWPFDocument();
		try{
			stream = new FileOutputStream(new File(docName));
		}
		catch(Exception e) {
			System.out.println("Can't create word doc.");
		}
	}

	

	public XWPFDocument getDocxFile() {
		return docxFile;
	}



	public void setDocxFile(XWPFDocument docxFile) {
		this.docxFile = docxFile;
	}



	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public void writeDoc() {
		try {
			this.docxFile.write(stream);
			stream.close();
		}
		catch(Exception e) {
			System.out.println("Could not write to word doc file");
		}
	}
}
