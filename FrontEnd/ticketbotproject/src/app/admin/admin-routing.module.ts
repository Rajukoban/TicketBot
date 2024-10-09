import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PendingApprovalsComponent } from './components/pending-approvals/pending-approvals.component';
import { ViewTicketComponent } from './components/view-ticket/view-ticket.component';
import { ApprovalTicketsComponent } from './components/approval-tickets/approval-tickets.component';
import { RejectTicketComponent } from './components/reject-ticket/reject-ticket.component';

const routes: Routes = [
  {path:'',component:AdminComponent},
  {path:'dashborad',component:DashboardComponent},
  {path:'pending',component:PendingApprovalsComponent},
  {path:'ticket-details/:id',component:ViewTicketComponent},
  {path:'approval',component:ApprovalTicketsComponent},
  {path:'reject',component:RejectTicketComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
