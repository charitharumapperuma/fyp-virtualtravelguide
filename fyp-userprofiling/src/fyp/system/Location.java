package fyp.system;

/**
 * @author Charith Arumapperuma
 * @date 10/11/2015
 * 
 *       Holds GPS location information.
 * 
 */
public class Location {
	private double latitude;
	private double longitude;
	private double altitude;

	/**
	 * Creates a new Location object with given parameters.
	 * @param latitude		coordinate latitude as double
	 * @param longitude		coordinate longitude as double
	 * @param altitude		coordinate altitude as double
	 */
	public Location(double latitude, double longitude, double altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude
	 *            the altitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

}
