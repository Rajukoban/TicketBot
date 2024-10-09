import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './users.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RaiseticketComponent } from './components/raiseticket/raiseticket.component';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MyticketsComponent } from './components/mytickets/mytickets.component';
import { TutorialsComponent } from './components/tutorials/tutorials.component';
import {MatListModule} from '@angular/material/list';
import { EditTicketComponent } from './components/edit-ticket/edit-ticket.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ApprovalTicketsComponent } from './components/approval-tickets/approval-tickets.component';
import { RejectTicketsComponent } from './components/reject-tickets/reject-tickets.component'
import { BaseChartDirective, NG_CHARTS_CONFIGURATION, provideCharts } from 'ng2-charts';
import { Chart } from 'chart.js';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { ViewpdfComponent } from './components/viewpdf/viewpdf.component';



@NgModule({
  declarations: [
    UsersComponent,
    DashboardComponent,
    RaiseticketComponent,
    MyticketsComponent,
    TutorialsComponent,
    EditTicketComponent,
    ProfileComponent,
    ApprovalTicketsComponent,
    RejectTicketsComponent,
    ViewpdfComponent,
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    DemoAngularMaterial,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatListModule,
    BaseChartDirective,
    NgxChartsModule
  ],
  providers:[
    provideCharts()
  ]
})
export class UsersModule { }
