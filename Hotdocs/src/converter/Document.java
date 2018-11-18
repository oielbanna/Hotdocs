package converter;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Document{
	private String docName;
	private XWPFDocument docxFile;
	private FileOutputStream stream;
	
	private static Document doc = new Document();
	
	private Document() {
		// Scanner in = new Scanner(System.in);
		// System.out.println("What do you want to name the word document? Please
		// include the .docx file extension.");
		this.docName = "doc.docx";
		this.docxFile = new XWPFDocument();
		
		try{
			stream = new FileOutputStream(new File(docName));
		}
		catch(Exception e) {
			System.out.println("Can't create word doc.");
		}
		// in.close();
	}
	
	public static Document getInstance() {
		return doc;
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

	public static void setDocName(String docName) {
		doc.docName = docName;
	}
	
	public static void writeDoc() {
		try {
			doc.docxFile.write(doc.stream);
			doc.stream.close();
		}
		catch(Exception e) {
			System.out.println("Could not write to word doc file");
		}
	}
}
