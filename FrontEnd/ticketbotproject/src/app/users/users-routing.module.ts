import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RaiseticketComponent } from './components/raiseticket/raiseticket.component';
import { DemoAngularMaterial } from '../DemoAngularMaterial';
import { MyticketsComponent } from './components/mytickets/mytickets.component';
import { TutorialsComponent } from './components/tutorials/tutorials.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ApprovalTicketsComponent } from './components/approval-tickets/approval-tickets.component';
import { RejectTicketsComponent } from './components/reject-tickets/reject-tickets.component';
import { ViewpdfComponent } from './components/viewpdf/viewpdf.component';


const routes: Routes = [
  { path: '', component: UsersComponent },
  {path:'dashboard',component:DashboardComponent},
  {path:'raiseticket',component:RaiseticketComponent},
  {path:'mytickets',component:MyticketsComponent},
  {path:'tutorial',component:TutorialsComponent},
  {path:'profile',component:ProfileComponent},
  {path:'approved',component:ApprovalTicketsComponent},
  {path:'rejected',component:RejectTicketsComponent},
  {path:'viewpdf/:id',component:ViewpdfComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
