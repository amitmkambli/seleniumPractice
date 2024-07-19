package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class ConfigReader {
	
	private static String DEFAULT_PROPERTIES = "properties/config.properties";

	public static String getProperty1(String propertyName) {
		Properties properties = new Properties();

		/*
		 * try { //FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties"); 
		 * FileInputStream fis = new FileInputStream("properties/config.properties"); 
		 * properties.load(fis); }
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
		// System.out.println(properties.getProperty("url"));
		return properties.getProperty(propertyName);
	}
	
	public static String getProperty(String propertyName){
        Properties properties = new Properties();
        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        }catch (Exception e){
            System.out.println("unable to read the property file {}".concat(DEFAULT_PROPERTIES) + e);
        }
        return properties.getProperty(propertyName);
    }

}
