import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from "../employee.service";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  sort: string;
  employees: Employee[];

  constructor(private employeeService : EmployeeService) { }

  ngOnInit() {
    this.sort = "salary"; //default
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService.getEmployees(this.sort)
      .subscribe(employees => {
        this.employees = employees;
        console.log(employees)
      });
  }

}
