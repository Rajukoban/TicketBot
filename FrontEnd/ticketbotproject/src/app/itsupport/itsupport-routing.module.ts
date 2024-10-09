import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ITSupportComponent } from './itsupport.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AllTicketsComponent } from './components/all-tickets/all-tickets.component';
import { OpenTicketsComponent } from './components/open-tickets/open-tickets.component';
import { PendingTicketsComponent } from './components/pending-tickets/pending-tickets.component';
import { ClosedTicketsComponent } from './components/closed-tickets/closed-tickets.component';
import { ViewTicketDetailsComponent } from './components/view-ticket-details/view-ticket-details.component';
import { UpdateTicketDetailsComponent } from './components/update-ticket-details/update-ticket-details.component';
import { ReportsComponent } from './components/reports/reports.component';
import { KnowledgeBaseComponent } from './components/knowledge-base/knowledge-base.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';

const routes: Routes = [
  { path: '', component: ITSupportComponent },
  {path:'dashboard',component:DashboardComponent},
  {path:'alltickets',component:AllTicketsComponent},
  {path:'opentickets',component:OpenTicketsComponent},
  {path:'pendingtickets',component:PendingTicketsComponent},
  {path:'closedtickets',component:ClosedTicketsComponent},
  {path:'ticket-details/:id',component:ViewTicketDetailsComponent},
  {path:'update-details/:id',component:UpdateTicketDetailsComponent},
  {path:'reports',component:ReportsComponent},
  {path:'knowledge',component:KnowledgeBaseComponent},
  {path:'tutorial',component:TutorialComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ITSupportRoutingModule { }
