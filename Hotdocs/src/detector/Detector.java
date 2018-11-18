package detector;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
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
			String imgName = f.getName();
			BufferedImage imgBuffer = ImageIO.read(f);
			BufferedImage imageCopy = new BufferedImage(imgBuffer.getWidth(), imgBuffer.getHeight(),
					BufferedImage.TYPE_3BYTE_BGR);
			imageCopy.getGraphics().drawImage(imgBuffer, 0, 0, null);
			byte[] data = ((DataBufferByte) imageCopy.getRaster().getDataBuffer()).getData();
			Mat img = new Mat(imgBuffer.getHeight(), imgBuffer.getWidth(), CvType.CV_8UC3);

			img.put(0, 0, data);
			Mat imfg = filterImg(img);
			Imgcodecs.imwrite("input.jpg", img);

		}

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
		list.add(new File("./datasets/digits.png"));
		Detector d = new Detector(list);
	}

}

