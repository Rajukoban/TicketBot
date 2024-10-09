import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PendingApprovalsComponent } from './components/pending-approvals/pending-approvals.component';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { ViewTicketComponent } from './components/view-ticket/view-ticket.component';
import { ApprovalTicketsComponent } from './components/approval-tickets/approval-tickets.component';
import { RejectTicketComponent } from './components/reject-ticket/reject-ticket.component'



@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    PendingApprovalsComponent,
    ViewTicketComponent,
    ApprovalTicketsComponent,
    RejectTicketComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    DemoAngularMaterial
  ]
})
export class AdminModule { }
