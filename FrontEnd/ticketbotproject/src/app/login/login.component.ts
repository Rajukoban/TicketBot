import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { MatFormFieldControl } from '@angular/material/form-field';
import { UserstorageService } from '../services/storage/userstorage.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm!:FormGroup;

  hidePassword=true;

  constructor(private fb:FormBuilder,private authservice:AuthService,private snackbar:MatSnackBar,private router:Router){}

  ngOnInit(){
    this.loginForm=this.fb.group({
      email:[null,[Validators.required]],
      password:[null,[Validators.required]],
    })
  }

  togglePasswordVisibility(){
    this.hidePassword=!this.hidePassword;
  }

  onSubmit():any{
    const username=this.loginForm.get('email')!.value;
    const password=this.loginForm.get('password')!.value;
    this.authservice.login(username,password).subscribe((res: any)=>{
      if(UserstorageService.isReportingManager()){
        this.router.navigateByUrl('admin/dashborad');
        this.snackbar.open('Login Success','Close',{duration:5000});
      }else if(UserstorageService.isProjectMemberLoggedIN()){
        this.router.navigateByUrl('users/dashboard');
        this.snackbar.open('Login Success','Close',{duration:5000})
      }else if(UserstorageService.isITSupportTeamLoggedIN()){
        this.router.navigateByUrl('ITSupport/dashboard');
        this.snackbar.open('Login Success','Close',{duration:5000});
      }else if(UserstorageService.isSystemAdminLoggedIN()){
        this.router.navigateByUrl('systemadmin/dashborad');
        this.snackbar.open("Login Success",'Close',{duration:5000});
      }
    },(error: any)=>{
      this.snackbar.open("Invalid username and password",'ERROR',{duration:5000});
    })
  }
}
