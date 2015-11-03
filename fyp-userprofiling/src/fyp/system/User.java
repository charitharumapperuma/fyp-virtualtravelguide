package fyp.system;

/**
 * 
 * @author Charith Arumapperuma
 * @date 04/11/2015
 * 
 *       Holds information about system user entities.
 */
public class User {
	private int id;
	private String name;
	private int[] preferences;

	/**
	 * Creates a new user with meaningless data.
	 */
	public User() {
		this.id = -1;
		this.name = "No name";
		this.preferences = new int[0];
	}

	/**
	 * Creates a new user with given data
	 * 
	 * @param id
	 *            unique user identification number as int
	 * @param name
	 *            name of the user as String
	 * @param preferences
	 *            list of travel preferences of the user as int[]
	 */
	public User(int id, String name, int[] preferences) {
		this.id = id;
		this.name = name;
		this.preferences = preferences;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the preferences
	 */
	public int[] getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences
	 *            the preferences to set
	 */
	public void setPreferences(int[] preferences) {
		this.preferences = preferences;
	}

}
