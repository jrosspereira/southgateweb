package com.jross.exam.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * Convert file to JSON
 * @author jpereira 05/16/2018
 */
public class JsonFetcherUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFetcherUtil.class);

    /**
     * Convert file to json node
     * @param fileName
     * @return
     */
    public static JsonNode fileToJson(String fileName) {
        String jsonString = FileUtil.readFile(fileName);

        return stringToJson(jsonString);
    }


    /**
     * Convert string to JsonNode
     * @param string
     * @return
     */
    public static JsonNode stringToJson(String string){
        try{
            if (string != null && !string.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readTree(string);
            }
        }catch (IOException e){
            LOGGER.warn("No valid JSON Found");
        }
        return null;
    }
}