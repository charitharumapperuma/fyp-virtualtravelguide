import java.util.LinkedList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Point;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class AlgoTest {
	   public static void main( String[] args ){ 
	   
		   System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
     	   
		   FeatureDetector fd = FeatureDetector.create(FeatureDetector.FAST);
		   DescriptorExtractor fe = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		   DescriptorMatcher fm = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
		   
		   Mat Imagemat1 = new Mat();
		   Mat Imagemat2 = new Mat();
		   Mat mat1 = Highgui.imread("res/building1.jpg");
		   Imgproc.cvtColor(mat1, Imagemat1, Imgproc.COLOR_RGB2GRAY);
		   Mat mat2 = Highgui.imread("res/building2.jpg");
		   Imgproc.cvtColor(mat2, Imagemat2, Imgproc.COLOR_RGB2GRAY);
		   
           MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
           MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
		   Mat descriptors1 = new Mat();
		   Mat descriptors2 = new Mat();
		   
		   fd.detect(Imagemat1, keypoints1);
		   fd.detect(Imagemat2, keypoints2);
		   
		   fe.compute(Imagemat1, keypoints1, descriptors1);
		   fe.compute(Imagemat2, keypoints2, descriptors2);
		   
		   MatOfDMatch matches = new MatOfDMatch();
		   
		   fm.match(descriptors1, descriptors2, matches);
		   
		   
		   
		   
		// turn the matches to a list
			List<DMatch> matchesList = matches.toList();

			Double maxDist = 0.0; // keep track of max distance from the matches
			Double minDist = 100.0; // keep track of min distance from the matches

			// calculate max & min distances between keypoints
			for (int i = 0; i < keypoints1.rows(); i++) {
				Double dist = (double) matchesList.get(i).distance;
				if (dist < minDist)
					minDist = dist;
				if (dist > maxDist)
					maxDist = dist;
			}

			System.out.println("max dist: " + maxDist);
			System.out.println("min dist: " + minDist);

			// structure for the good matches
			LinkedList<DMatch> goodMatches = new LinkedList<DMatch>();

			// use only the good matches (i.e. whose distance is less than
			// 3*min_dist)
			for (int i = 0; i < descriptors1.rows(); i++) {
				if (matchesList.get(i).distance < 3 * minDist) {
					goodMatches.addLast(matchesList.get(i));
				}
			}

			System.out.println("\nNum. of good matches" + goodMatches.size());

			MatOfDMatch gm = new MatOfDMatch();
			gm.fromList(goodMatches);
		   
		   Mat outImg = new Mat();
		   
		   Features2d.drawMatches(Imagemat1, keypoints1, Imagemat2, keypoints2, gm, outImg);
		   
		   Highgui.imwrite("res/outImg.png", outImg);  
		   
		   
	   }
	}