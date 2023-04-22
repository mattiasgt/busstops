package com.assignment.busstops;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler {
    static Properties properties;
    static final String PROPS_FILE = "classpath:application.properties";

    static {
            properties = new Properties();
            try {
                File file = ResourceUtils.getFile(PROPS_FILE);
                InputStream in = new FileInputStream(file);
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static String getProperty(String property) {
        if(property != null) {
            return properties.getProperty(property);
        } else {
            return null;
        }
    }
}