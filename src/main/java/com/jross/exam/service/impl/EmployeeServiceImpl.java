package com.jross.exam.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jross.exam.entity.Employee;
import com.jross.exam.service.EmployeeService;
import com.jross.exam.util.JsonFetcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Employee Service
 * @author jpereira 05/16/2018
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Value("${app.file.employee.list}")
    private String sourceFile;

    @Override
    public List<Employee> findAll() {
        JsonNode employeeListJson = JsonFetcherUtil.fileToJson(sourceFile);

        List<Employee> employeeList = null;
        if(employeeListJson != null){
            try{
                ObjectMapper mapper = new ObjectMapper();
                TypeReference ref = new TypeReference<List<Employee>>(){};
                employeeList = mapper.readValue(employeeListJson.toString(), ref);
            }catch (IOException e){
                LOGGER.info("Error converting json to employee.");
            }
        }

        return employeeList;
    }
}
