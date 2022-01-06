package com.example.desafioquality.helper;

import com.example.desafioquality.entity.District;
import com.example.desafioquality.entity.Property;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;


public abstract class DatabaseHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final String PATH_PROPERTIES = "properties.json";
    private static final String PATH_DISTRICTS = "districts.json";

    public static List<Property> getDatabaseProperty() {
        try {
            File file = new File(PATH_PROPERTIES);
            FileInputStream fileInputStream = new FileInputStream(file);
            return Arrays.asList(objectMapper.readValue(fileInputStream, Property[].class));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static List<District> getDatabaseDistrict(){
        try {
            File file = new File(PATH_DISTRICTS);
            FileInputStream fileInputStream = new FileInputStream(file);
            return Arrays.asList(objectMapper.readValue(fileInputStream, District[].class));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String getDatabasePathProperties() { return PATH_PROPERTIES; }

    public static String getDatabasePathDistricts(){
        return PATH_DISTRICTS;
    }
}
