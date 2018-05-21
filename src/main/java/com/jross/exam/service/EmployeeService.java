package com.jross.exam.service;

import com.jross.exam.entity.Employee;

import java.util.List;

/**
 * Employee Service Interface
 * @author jpereira 05/16/2018
 */
public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findAll(String sortByField);

    List<Employee> findByAge(Integer min, Integer max);
}
