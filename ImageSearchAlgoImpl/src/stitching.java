import java.util.LinkedList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class stitching {

	static Mat image1;
	static Mat image2;

	static FeatureDetector fd;
	static DescriptorExtractor fe;
	static DescriptorMatcher fm;

	public static void initialise() {
		fd = FeatureDetector.create(FeatureDetector.BRISK);
		fe = DescriptorExtractor.create(DescriptorExtractor.SURF);
		fm = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);

		// images
		image1 = Highgui.imread("res/building1.jpg");
		image2 = Highgui.imread("res/building2.jpg");

		// structures for the keypoints from the 2 images
		MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
		MatOfKeyPoint keypoints2 = new MatOfKeyPoint();

		// structures for the computed descriptors
		Mat descriptors1 = new Mat();
		Mat descriptors2 = new Mat();

		// structure for the matches
		MatOfDMatch matches = new MatOfDMatch();

		// getting the keypoints
		fd.detect(image1, keypoints1);
		fd.detect(image1, keypoints2);

		// getting the descriptors from the keypoints
		fe.compute(image1, keypoints1, descriptors1);
		fe.compute(image2, keypoints2, descriptors2);

		// getting the matches the 2 sets of descriptors
		fm.match(descriptors2, descriptors1, matches);

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

		// structures to hold points of the good matches (coordinates)
		LinkedList<Point> objList = new LinkedList<Point>(); // image1
		LinkedList<Point> sceneList = new LinkedList<Point>(); // image 2

		List<KeyPoint> keypoints_objectList = keypoints1.toList();
		List<KeyPoint> keypoints_sceneList = keypoints2.toList();

		// putting the points of the good matches into above structures
		for (int i = 0; i < goodMatches.size(); i++) {
			objList.addLast(keypoints_objectList.get(goodMatches.get(i).queryIdx).pt);
			sceneList
					.addLast(keypoints_sceneList.get(goodMatches.get(i).trainIdx).pt);
		}

		System.out.println("\nNum. of good matches" + goodMatches.size());

		MatOfDMatch gm = new MatOfDMatch();
		gm.fromList(goodMatches);

		// converting the points into the appropriate data structure
		MatOfPoint2f obj = new MatOfPoint2f();
		obj.fromList(objList);

		MatOfPoint2f scene = new MatOfPoint2f();
		scene.fromList(sceneList);

		// finding the homography matrix
		Mat H = Calib3d.findHomography(obj, scene);

		// LinkedList<Point> cornerList = new LinkedList<Point>();
		Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);
		Mat scene_corners = new Mat(4, 1, CvType.CV_32FC2);

		obj_corners.put(0, 0, new double[] { 0, 0 });
		obj_corners.put(1, 0, new double[] { image1.cols(), 0 });
		obj_corners.put(2, 0, new double[] { image1.cols(), image1.rows() });
		obj_corners.put(3, 0, new double[] { 0, image1.rows() });

		Core.perspectiveTransform(obj_corners, scene_corners, H);

		
		// structure to hold the result of the homography matrix
		Mat result = new Mat();

		// size of the new image - i.e. image 1 + image 2
		Size s = new Size(image1.cols() + image2.cols(), image1.rows());

		// using the homography matrix to warp the two images
		Imgproc.warpPerspective(image1, result, H, s);
		int i = image1.cols();
		Mat m = new Mat(result, new Rect(i, 0, image2.cols(), image2.rows()));

		image2.copyTo(m);

		Mat img_mat = new Mat();

		Features2d.drawMatches(image1, keypoints1, image2, keypoints2, gm,
				img_mat, new Scalar(254, 0, 0), new Scalar(254, 0, 0),
				new MatOfByte(), 2);

		// creating the output file
		boolean imageStitched = Highgui.imwrite("res/imageStitched.jpg", result);
		boolean imageMatched = Highgui.imwrite("res/imageMatched.jpg", img_mat);
	}

	public static void main(String args[]) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		initialise();
	}
}
