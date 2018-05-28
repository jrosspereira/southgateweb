import { Component, OnInit, ViewChild } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from "../employee.service";
import { MatSort, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  sortBy: string;
  displayedColumns = ['name', 'joindate', 'age', 'company', 'salary'];
  dataSource: MatTableDataSource<Employee>;
  minAge: number;
  maxAge: number;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private employeeService : EmployeeService) { }

  applyFilter(){
    this.getEmployeesByAge(this.minAge, this.maxAge)
  }

  ngOnInit() {
    this.sortBy = "salary"; //default

    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService.getEmployees(this.sortBy)
      .subscribe(employees => {
        this.dataSource = new MatTableDataSource(employees);
        this.dataSource.sort = this.sort;
      });
  }

  getEmployeesByAge(min, max): void {
    this.employeeService.getEmployeesByAge(min, max)
      .subscribe(employees => {
        this.dataSource = new MatTableDataSource(employees);
        this.dataSource.sort = this.sort;
      });
  }

}
