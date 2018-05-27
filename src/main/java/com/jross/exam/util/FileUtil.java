package com.jross.exam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Path path = null;
        try{
            path = Paths.get(FileUtil.class.getClassLoader()
                    .getResource(fileName).toURI());
        }catch (URISyntaxException e){
            LOGGER.warn("Cannot find file {}", fileName);
        }

        if(path != null){
            try(Stream<String> lines = Files.lines(path)){
                return lines.collect(Collectors.joining());
            }catch (IOException e){
                LOGGER.warn("Cannot read file {}", fileName);
            }
        }
        return null;
    }
}
