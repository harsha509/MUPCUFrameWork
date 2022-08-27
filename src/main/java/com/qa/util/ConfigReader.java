package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    /**
     * This method is to load the properties from the config.properties file
     * @return properties object
     */
    public Properties init_prop()  {
        prop= new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
