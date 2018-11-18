package detector;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Detector {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	ArrayList<File> file;

	public Detector(ArrayList<File> imgs) {
		file = imgs;
		detectDocs();
	}

	private void detectDocs() {

		for (int i = 0; i < file.size(); i++) {
			File f = file.get(i);
			String imgName = f.getName();
			Mat img = Imgcodecs.imread(imgName + "");
			System.out.println();
		}

	}

	private File filterImg(File file) {

		return null;
	}

	public static void main(String[] args) {
		ArrayList<File> list = new ArrayList<>();
		list.add(new File("./datasets/digits.png"));
		Detector d = new Detector(list);
	}

	public BufferedImage imshow(Mat m) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
	        Mat m2 = new Mat();
	        Imgproc.cvtColor(m,m2,Imgproc.COLOR_BGR2RGB);
	        type = BufferedImage.TYPE_3BYTE_BGR;
	        m = m2;
	    }
	    byte [] b = new byte[m.channels()*m.cols()*m.rows()];
	    m.get(0,0,b); // get all the pixels
	    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	    image.getRaster().setDataElements(0, 0, m.cols(),m.rows(), b);
	    return image;
	}
}

