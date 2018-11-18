package detector;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample {
	public static String getWord(String path) {

		File imageFile = new File(path);
		ITesseract instance = new Tesseract(); // JNA Interface Mapping

		try {
//			String fontPath = "./tessdata";
//			instance.setDatapath(fontPath);
			// instance.setDatapath("C:/bin/Tess4J");

			String result = instance.doOCR(imageFile);

			System.out.println("Tess" + result);
			return result;
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		return "";
	}
}
