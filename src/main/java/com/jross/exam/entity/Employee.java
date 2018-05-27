package com.jross.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Employee Class
 * @author jpereira 05/16/2018
 */
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 2496674687959533223L;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date joindate;

    private Integer age;

    private String company;

    private String email;

    private String phone;

    private Double salary;

    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(joindate, employee.joindate) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(company, employee.company) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, joindate, age, company, email, phone, salary, address);
    }
}
