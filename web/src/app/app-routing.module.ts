import { NgModule } from '@angular/core';
import {EmployeesComponent} from "./employees/employees.component";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  { path: '', component: EmployeesComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
