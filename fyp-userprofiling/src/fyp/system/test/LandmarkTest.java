package fyp.system.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fyp.system.Landmark;

/**
 * 
 * @author 	Charith Arumapperuma
 * @date 	04/11/2015
 */
public class LandmarkTest {

	@Test
	public void testGetId() {
		Landmark landmark = new Landmark();
		assertEquals(-1, landmark.getId());
	}

	@Test
	public void testGetName() {
		Landmark landmark = new Landmark();
		assertEquals("No name", landmark.getName());
	}

	@Test
	public void testGetFeatures() {
		Landmark landmark = new Landmark();
		assertEquals(0, landmark.getFeatures().length);
	}

}
