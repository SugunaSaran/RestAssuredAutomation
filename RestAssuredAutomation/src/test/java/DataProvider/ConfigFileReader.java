package DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	  private final String propertyFilePath= "src/test/resources/config.properties";
		public ConfigFileReader(){
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(propertyFilePath));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Config.properties not found at " + propertyFilePath);
			}		
		}
		
		
	public String getBrowser() {		
			return properties.getProperty("browser");

		}
		
		public String getApplicationUrl() {
			String url = properties.getProperty("url");
			if(url != null) return url;
			else throw new RuntimeException("url not specified in the Configuration.properties file.");
		}

		public String getUserName() {
			return properties.getProperty("username");
		}
		public String getPassword() {
			return properties.getProperty("password");
		}
		public String getExcelPath() {
			return properties.getProperty("ExcelInputFile");
		}
		
}