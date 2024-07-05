package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ConfigReader {

	public static String getProperty(String propertyName) {
		Properties properties = new Properties();

		/*
		 * try { //FileInputStream fis = new
		 * FileInputStream(System.getProperty("user.dir")+
		 * "/src/test/resources/properties/config.properties"); FileInputStream fis =
		 * new FileInputStream("properties/config.properties"); properties.load(fis); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */
		
		//we need to avoid using '/src/test/resources/properties/config.properties' if we want to build a jar file out of this
		try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("properties/config.properties")) {
			if (inputStream == null) {
				System.out.println("Sorry, unable to find config.properties");
				return propertyName;
			}
			properties.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Access properties
		// String someProperty = properties.getProperty("url");
		// System.out.println(someProperty);

		return properties.getProperty(propertyName);
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * System.out.println(ConfigReader.getProperty("url"));
	 * System.out.println(System.getProperty("java.class.path")); String[]
	 * propertieStrings = System.getProperty("java.class.path").split(";"); for (int
	 * i = 0; i < propertieStrings.length; i++) {
	 * System.out.println(propertieStrings[i]); }
	 * 
	 * Properties properties = new Properties(); try (InputStream inputStream =
	 * ConfigReader.class.getClassLoader().getResourceAsStream(
	 * "properties/config.properties")) { if (inputStream == null) {
	 * System.out.println("Sorry, unable to find config.properties"); return; }
	 * properties.load(inputStream); } catch (IOException ex) {
	 * ex.printStackTrace(); }
	 * 
	 * // Access properties String someProperty = properties.getProperty("url");
	 * System.out.println(someProperty); }
	 */

}
