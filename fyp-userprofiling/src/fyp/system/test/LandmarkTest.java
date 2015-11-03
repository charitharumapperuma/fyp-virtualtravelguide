package fyp.system.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fyp.system.Landmark;

/**
 * 
 * @author Charith Arumapperuma
 * @date 04/11/2015
 */
public class LandmarkTest {

	@Test
	public void testGetIdWithConstructor1() {
		Landmark landmark = new Landmark();
		assertEquals(-1, landmark.getId());
	}

	@Test
	public void testGetNameWithConstructor1() {
		Landmark landmark = new Landmark();
		assertEquals("No name", landmark.getName());
	}

	@Test
	public void testGetFeaturesWithConstructor1() {
		Landmark landmark = new Landmark();
		assertEquals(0, landmark.getFeatures().length);
	}

	@Test
	public void testGetIdWithConstructor2() {
		Landmark landmark = new Landmark(101, "Sigiriya", new int[]{1, 2, 3, 4});
		assertEquals(101, landmark.getId());
	}

	@Test
	public void testGetNameWithConstructor2() {
		Landmark landmark = new Landmark(101, "Sigiriya", new int[]{1, 2, 3, 4});
		assertEquals("Sigiriya", landmark.getName());
	}

	@Test
	public void testGetFeaturesWithConstructor2() {
		Landmark landmark = new Landmark(101, "Sigiriya", new int[]{1, 2, 3, 4});
		assertEquals(4, landmark.getFeatures().length);
	}

}
