import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from "./employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeeApiUrl = "http://localhost:8080/api/employee"

  constructor(private http: HttpClient) { }

  /** GET employees from the server */
  getEmployees (sortField: string): Observable<Employee[]> {
    const url = this.employeeApiUrl + "/sort/" + sortField;

    return this.http.get<Employee[]>(url);
  }

  /** GET employees by age from the server */
  getEmployeesByAge (min: number, max: number): Observable<Employee[]> {
    const url = this.employeeApiUrl + "/findByAge?min=" + min + "&max=" + max;

    return this.http.get<Employee[]>(url);
      /*.pipe(
        tap(employees => console.log('fetched employees')),
        catchError(console.log('fail fetching employees'))
      );*/
  }
}
