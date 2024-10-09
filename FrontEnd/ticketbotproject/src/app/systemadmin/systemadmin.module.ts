import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SystemadminRoutingModule } from './systemadmin-routing.module';
import { SystemadminComponent } from './systemadmin.component';
import { DashboradComponent } from './components/dashborad/dashborad.component';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AddUserComponent } from './components/add-user/add-user.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateUserComponent } from './components/update-user/update-user.component';


@NgModule({
  declarations: [
    SystemadminComponent,
    DashboradComponent,
    UserManagementComponent,
    AddUserComponent,
    UpdateUserComponent
  ],
  imports: [
    CommonModule,
    SystemadminRoutingModule,
    DemoAngularMaterial,
    MatToolbarModule,
    MatListModule,
    MatTableModule,
    MatButtonModule,
    MatSidenavModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ]
})
export class SystemadminModule { }
