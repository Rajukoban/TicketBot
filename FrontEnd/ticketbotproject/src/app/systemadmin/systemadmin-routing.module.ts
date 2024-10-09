import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SystemadminComponent } from './systemadmin.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { DashboradComponent } from './components/dashborad/dashborad.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';


const routes: Routes = [
  { path: '', component: SystemadminComponent },
  {path:'dashborad',component:DashboradComponent},
  {path:'usermanage',component:UserManagementComponent},
  {path:'signup',component:AddUserComponent},
  {path:'updateuser/:userId',component:UpdateUserComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemadminRoutingModule { }
