package detector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import converter.Body;
import converter.Document;
import converter.Element;

public class Detector {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	ArrayList<File> file;

	public Detector(ArrayList<File> imgs) throws IOException {
		file = imgs;
		detectDocs();
	}

	private void detectDocs() throws IOException {

		for (int i = 0; i < file.size(); i++) {
			// reading image stuff
			File f = file.get(i);
			String imgName = f.getAbsolutePath();
			Mat img = Imgcodecs.imread(imgName);
			if (img.empty())
				System.out.println("Shit");

			int rects = findWords(img);
			ArrayList<String> words = new ArrayList<>();
			for (int j = 0; j < rects; j++) {
				// words.add(TesseractExample.getWord(i + ".jpg"));

			}

		}

	}

	private int findWords(Mat img) {
		Mat rgb = new Mat(img.rows(),img.cols(), img.type());
		Imgproc.pyrDown(img, rgb);

		Mat small = new Mat();
		Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);

		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));

		Mat grad = new Mat();
		Imgproc.morphologyEx(rgb, grad, Imgproc.MORPH_GRADIENT, kernel);

		kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));

		Mat connected = new Mat();
		Imgproc.morphologyEx(grad, connected, Imgproc.MORPH_CLOSE, kernel);
		Imgproc.cvtColor(connected, connected, Imgproc.COLOR_RGB2GRAY);
		
		ArrayList<MatOfPoint> contours = new ArrayList<>();
		Mat heirarchy = new Mat();

		Imgproc.findContours(connected, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
		System.out.println(contours.size());
		Mat mask = Mat.zeros(grad.size(), CvType.CV_8U);
		ArrayList<Rect> rects = new ArrayList<>();
		ArrayList<String> words = new ArrayList<>();
		for (int i = 0; i < contours.size(); i++) {

			Rect rect = Imgproc.boundingRect(contours.get(i));

			int x = rect.x;
			int y = rect.y;
			int h = rect.height;
			int w = rect.width;
			
			for (int j = y, z = x; j < (y + h) && z < (x + w); j++, z++) {
				mask.put(j, z, 0);
			}
			Imgproc.drawContours(mask, contours, i, new Scalar(255, 255, 255), -1);
			Imgproc.rectangle(rgb, new Point(x, y), new Point(x + w - 1, y + h - 1), new Scalar(0, 255, 0));
			Imgcodecs.imwrite(i + ".jpg", new Mat(rgb, rect));

			words.add(TesseractExample.getWord(i + ".jpg"));

		}
		Element body = new Body("");
		String s = "";
		for (int i = 0; i < words.size(); i++) {
			s += words.get(i) + " ";
		}
		body.formatAndAppend(s);

		Document.writeDoc();

		Imgcodecs.imwrite("boxedWords.jpg", rgb);
		return contours.size();
	}


//	public static void main(String[] args) throws IOException {
//		ArrayList<File> list = new ArrayList<>();
//		list.add(new File("./datasets/text4.png"));
//		Detector d = new Detector(list);
//		System.out.println("Done");
//		// TesseractExample.getWord("./datasets/text2.jpg");
//	}

}

