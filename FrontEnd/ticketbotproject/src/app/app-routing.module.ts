import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ForgotpasswordComponent } from './passwordchange/forgotpassword/forgotpassword.component';
import { ResetPasswordComponent } from './passwordchange/reset-password/reset-password.component';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'',component:LoginComponent},
  {path:'forgotpassword',component:ForgotpasswordComponent},
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) },
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: 'ITSupport', loadChildren: () => import('./itsupport/itsupport.module').then(m => m.ITSupportModule) },
  { path: 'systemadmin', loadChildren: () => import('./systemadmin/systemadmin.module').then(m => m.SystemadminModule) }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
