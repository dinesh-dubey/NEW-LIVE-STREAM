package com.liveStream;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class LoadProperty {
	private final static Logger LOGGER = Logger
			.getLogger(LoadProperty.class.getName());
	Properties prop = null;
	public LoadProperty() {
		if (prop == null)
			getPropertiesObj();
	}
	public String getProperty(String key) {
		String value = "";
		value = prop.getProperty(key);
		return value;
	}

	public Properties getPropertiesObj() {
		InputStream input = null;
		String key = "ABC";
		String value = "";
		String filename = "config.properties";
		input = getClass().getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			LOGGER.info("Sorry, unable to find " + filename);

		} else {
			try {
				prop = new Properties();
				prop.load(input);
			} catch (Exception e) {
				LOGGER.error("Ohh Something went wrong: "+e);
			}
			value = prop.getProperty(key);
			LOGGER.info("Key : " + key + ", Value : " + value);
		}
		return prop;
	}

}
