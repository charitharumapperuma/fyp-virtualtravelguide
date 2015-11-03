package fyp.system;

/**
 * @author 	Charith Arumapperuma
 * @date 	04/11/2015
 * 
 * Landmark holds information about physical landmarks, i.e. buildings,
 * historical places, archiological destinations, etc. 
 * 
 */
public class Landmark {
	private int id;
	private String name;
	private int[] features;
	// TODO add other landmark parameters
	
	/**
	 * Creates a empty Lankdmark with meaningless data.
	 */
	public Landmark() {
		this.id = -1;
		this.name = "No name";
		this.features = new int[0];
	}
	
	/**
	 * Creates a new landmark with most basic information.
	 * @param id		unique identification number as stored in the database as int
	 * @param name		name of the landmark as String
	 * @param features	ids of the features that this landmark has as a int[]
	 */
	public Landmark(int id, String name, int[] features) {
		this.id = id;
		this.name = name;
		this.features = features;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the features
	 */
	public int[] getFeatures() {
		return features;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(int[] features) {
		this.features = features;
	}
	
}
