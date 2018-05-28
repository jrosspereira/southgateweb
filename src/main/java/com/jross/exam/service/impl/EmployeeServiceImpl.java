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
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Service
 * @author jpereira 05/16/2018
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Value("${app.file.employee.list}")
    private String sourceFile;

    /**
     * Find all Employee
     * @return
     */
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

    /**
     * Find all employee sort by field name
     * @param sortByField
     * @return
     */
    @Override
    public List<Employee> findAll(String sortByField) {
        List<Employee> employeeList = this.findAll();
        if(sortByField != null && !sortByField.isEmpty()){
            try{
                Field field = Employee.class.getDeclaredField(sortByField);
                field.setAccessible(true);
                return employeeList.stream()
                        .sorted((first, second) -> {
                            try {
                                if (String.class.isAssignableFrom(field.getType())) {
                                    String a = (String) field.get(first);
                                    String b = (String) field.get(second);

                                    return a.compareTo(b);
                                }else if(Double.class.isAssignableFrom(field.getType())){
                                    Double a = (Double) field.get(first);
                                    Double b = (Double) field.get(second);

                                    return a.compareTo(b);
                                }else{
                                    LOGGER.info("Field sort is not string or double, will use default sort");
                                }
                            } catch (IllegalAccessException e) {
                                LOGGER.warn("Error encountered in sorting by given field");
                            }

                            return 0;
                        })
                        .collect(Collectors.toList());

            }catch (NoSuchFieldException e){
                LOGGER.warn("Sort by field, not existing.");
            }
        }
        return employeeList;
    }

    /**
     * Find all employee by age
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Employee> findByAge(Integer min, Integer max) {
        List<Employee> employeeList = this.findAll();

        if(min != null && max != null){
            employeeList = employeeList
                    .stream()
                    .filter( e -> e.getAge() >= min && e.getAge() <= max)
                    .collect(Collectors.toList());
        }else if(max != null){
            employeeList = employeeList
                    .stream()
                    .filter( e -> e.getAge() <= max)
                    .collect(Collectors.toList());
        }else if(min != null){
            employeeList = employeeList
                    .stream()
                    .filter( e -> e.getAge() >= min)
                    .collect(Collectors.toList());
        }
        return employeeList
                .stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .collect(Collectors.toList());
    }
}
