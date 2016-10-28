package com.liveStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperty {

	
	Properties prop =null;
	
	public LoadProperty() {
		// TODO Auto-generated constructor stub
	if(prop==null)
		getPropertiesObj();
	
	
	}
	
	
	public String getProperty(String key)
	{
		String value="";
		
		value=prop.getProperty(key);
		
		return value;
		
		
	}
	
	public  Properties getPropertiesObj()
	{
		
		InputStream input = null;

		String key = "ABC";
		String value="";

			String filename = "config.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				
			}else{
				
			try {
				prop=new Properties();
				prop.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
				value = prop.getProperty(key);
				System.out.println("Key : " + key + ", Value : " + value);
		
			}
			
			
				return prop;
	}
	
	
	
}
