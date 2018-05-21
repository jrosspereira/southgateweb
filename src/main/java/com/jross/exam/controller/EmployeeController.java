package com.jross.exam.controller;

import com.jross.exam.entity.Employee;
import com.jross.exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Employee Controller
 * @author jpereira 05/16/2018
 */
@RestController
@CrossOrigin(origins = "${app.front-end.url}")
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Get employee list sort by field name
     * @param field
     * @return
     */
    @RequestMapping(value = "/sort/${field}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> employees(@PathVariable String field) {
        return employeeService.findAll(field);
    }

    /**
     * Get employee list by age between min and max
     * @param min
     * @param max
     * @return
     */
    @RequestMapping(value = "/findByAge", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> findByAge(@RequestParam Integer min, @RequestParam Integer max) {
        return employeeService.findByAge(min, max);
    }
}
