package com.jross.exam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * File Utils
 * @author jpereira 05/16/2018
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * Read file and convert to string
     * @param fileName
     * @return
     */
    public static String readFile(String fileName){
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.joining());
        } catch (IOException e) {
            LOGGER.warn("Cannot read file {}", fileName);
        }

        return null;
    }
}
