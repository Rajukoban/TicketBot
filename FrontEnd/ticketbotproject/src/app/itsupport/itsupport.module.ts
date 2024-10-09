import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ITSupportRoutingModule } from './itsupport-routing.module';
import { ITSupportComponent } from './itsupport.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AllTicketsComponent } from './components/all-tickets/all-tickets.component';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { OpenTicketsComponent } from './components/open-tickets/open-tickets.component';
import { PendingTicketsComponent } from './components/pending-tickets/pending-tickets.component';
import { ClosedTicketsComponent } from './components/closed-tickets/closed-tickets.component';
import { ViewTicketDetailsComponent } from './components/view-ticket-details/view-ticket-details.component';
import { UpdateTicketDetailsComponent } from './components/update-ticket-details/update-ticket-details.component';
import { MatChipsModule } from '@angular/material/chips';
import { ReportsComponent } from './components/reports/reports.component';
import { KnowledgeBaseComponent } from './components/knowledge-base/knowledge-base.component';
import { AdminToolComponent } from './components/admin-tool/admin-tool.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ITSupportComponent,
    DashboardComponent,
    AllTicketsComponent,
    OpenTicketsComponent,
    PendingTicketsComponent,
    ClosedTicketsComponent,
    ViewTicketDetailsComponent,
    UpdateTicketDetailsComponent,
    ReportsComponent,
    KnowledgeBaseComponent,
    AdminToolComponent,
    TutorialComponent
  ],
  imports: [
    CommonModule,
    ITSupportRoutingModule,
    DemoAngularMaterial,
    MatChipsModule,
    FormsModule
  ]
})
export class ITSupportModule { }
