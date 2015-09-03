import java.util.ArrayList;
import java.util.Collections;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class cornerDetection {
	public static void main( String[] args ){ 
		   
		   System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
  	   
		   FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);
		   Mat Imagemat1 = Highgui.imread("res/index1.jpeg");
		   Mat mat = new Mat();
		   Imgproc.cvtColor(Imagemat1, mat, Imgproc.COLOR_RGB2GRAY);

           MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
           fd.detect(mat, keypoints1);
           
           Features2d.drawKeypoints(mat, keypoints1, mat);
           
           Highgui.imwrite("res/corner.png", mat);  
	}
}
