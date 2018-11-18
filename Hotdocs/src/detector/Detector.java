package detector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

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

//			BufferedImage imgBuffer = ImageIO.read(f);
//			BufferedImage imageCopy = new BufferedImage(imgBuffer.getWidth(), imgBuffer.getHeight(),
//					BufferedImage.TYPE_3BYTE_BGR);
//			imageCopy.getGraphics().drawImage(imgBuffer, 0, 0, null);
//			byte[] data = ((DataBufferByte) imageCopy.getRaster().getDataBuffer()).getData();
//			Mat img = new Mat(imgBuffer.getHeight(), imgBuffer.getWidth(), CvType.CV_8UC3);
//			img.put(0, 0, data);
			Mat img = Imgcodecs.imread(imgName);

			if (img.empty())
				System.out.println("Shit");

//			List<Rect> r = detectLetters(img);
//			for (int j = 0; j < r.size(); j++)
//				Imgproc.rectangle(img, r.get(i).br(), r.get(j).tl(), new Scalar(0, 255, 0), 3, 8, 0);
			Mat img2 = findWords(img);
			Imgcodecs.imwrite("abc1.png", img2);
			// Imgcodecs.imwrite("inpedfut.jpg", newImg);

		}

	}

	private Mat findWords(Mat img) {
		Mat rgb = new Mat(img.rows(),img.cols(), img.type());
		Imgproc.pyrDown(img, rgb);

		Mat small = new Mat();
		Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);

		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));

		Mat grad = new Mat();
		Imgproc.morphologyEx(rgb, grad, Imgproc.MORPH_GRADIENT, kernel);

		Mat bw = new Mat();
		Imgproc.threshold(grad, bw, 0, 255, Imgproc.THRESH_BINARY);

		kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));

		Mat connected = new Mat();
		Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, kernel);
		Imgproc.cvtColor(connected, connected, Imgproc.COLOR_RGB2GRAY);
		
		ArrayList<MatOfPoint> contours = new ArrayList<>();
		Mat heirarchy = new Mat();

		Imgproc.findContours(connected, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

		Mat mask = Mat.zeros(bw.size(), CvType.CV_8U);

		for (int i = 0; i < contours.size(); i++) {

			Rect rect = Imgproc.boundingRect(contours.get(i));
			System.out.println(rect);
			int x = rect.x;
			int y = rect.y;
			int h = rect.height;
			int w = rect.width;
			
			for (int j = y, z = x; j < (y + h) && z < (x + w); j++, z++) {
				mask.put(j, z, 0);
			}
			Imgproc.drawContours(mask, contours, i, new Scalar(255, 255, 255), -1);

			// float r = (Core.countNonZero(mask[y:y+h, x:x+w])) / (rect.width *
			// rect.height);
			
			if (w > 8 && h> 8)
				Imgproc.rectangle(rgb, new Point(x, y), new Point(x + w - 1, y + h - 1), new Scalar(0, 255, 0));
				//Imgproc.rectangle(rgb, (x, y), (x+w-1, y+h-1), (0, 255, 0), 2)
		}
		Imgcodecs.imwrite("s.png", small);
		return rgb;
	}

	// terrible function
	public List<Rect> detectLetters(Mat img) {
		List<Rect> boundRect = new ArrayList<>();

		Mat img_gray = new Mat(), img_sobel = new Mat(), img_threshold = new Mat(), element = new Mat();
		Imgproc.cvtColor(img, img_gray, Imgproc.COLOR_RGB2GRAY);
		Imgproc.Sobel(img_gray, img_sobel, CvType.CV_8U, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
		// at src, Mat dst, double thresh, double maxval, int type
		Imgproc.threshold(img_sobel, img_threshold, 0, 255, 8);
		element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 5));
		Imgproc.morphologyEx(img_threshold, img_threshold, Imgproc.MORPH_CLOSE, element);
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(img_threshold, contours, hierarchy, 0, 1);

		List<MatOfPoint> contours_poly = new ArrayList<MatOfPoint>(contours.size());

		for (int i = 0; i < contours.size(); i++) {

			MatOfPoint2f mMOP2f1 = new MatOfPoint2f();
			MatOfPoint2f mMOP2f2 = new MatOfPoint2f();

			contours.get(i).convertTo(mMOP2f1, CvType.CV_32FC2);
			Imgproc.approxPolyDP(mMOP2f1, mMOP2f2, 2, true);
			mMOP2f2.convertTo(contours.get(i), CvType.CV_32S);

			Rect appRect = Imgproc.boundingRect(contours.get(i));
			if (appRect.width > appRect.height) {
				boundRect.add(appRect);
			}
		}

		return boundRect;
	}

	private Mat filterImg(Mat img) {
		Mat destination = new Mat(img.rows(), img.cols(), img.type());
		Mat kernel = new Mat(3, 3, CvType.CV_32F) {
			{
				put(0, 0, -1);
				put(0, 1, 0);
				put(0, 2, 1);

				put(1, 0 - 2);
				put(1, 1, 0);
				put(1, 2, 2);

				put(2, 0, -1);
				put(2, 1, 0);
				put(2, 2, 1);
			}
		};
		Imgproc.filter2D(img, destination, -1, kernel);
		return destination;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<File> list = new ArrayList<>();
		list.add(new File("./datasets/8.jpg"));
		Detector d = new Detector(list);
	}

}

