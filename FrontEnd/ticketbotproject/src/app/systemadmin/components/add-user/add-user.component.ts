import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';
import { UserRole } from '../model/userrole';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  signupForm!:FormGroup;

  hidePassword=true;

  roles=Object.values(UserRole); 

  constructor(private fb:FormBuilder,private snackbar:MatSnackBar,private authService:AuthService,private router:Router){}

  ngOnInit(): void {
    this.signupForm=this.fb.group({
        name:[null,Validators.required],
        email:[null,[Validators.required,Validators.email]],
      password:[null,Validators.required],
      confirmpassword:[null,Validators.required],
      role:[UserRole.PROJECT_TEAM_MEMBER,Validators.required]
    });
  }

  togglePasswordVisibility(){
    this.hidePassword=!this.hidePassword;
  }

  onSubmit(){
    const password=this.signupForm.get('password')?.value;
    const confirmpassword=this.signupForm.get('confirmpassword')?.value;

    if(password!==confirmpassword){
      this.snackbar.open('password do not match','Close',{duration:5000,panelClass:'error-snackbar'});
      return;
    }

    this.authService.register1(this.signupForm.value).subscribe(resp=>{
        this.snackbar.open('sign up sucess','Close',{duration:5000});
        this.router.navigateByUrl("/systemadmin/usermanage");
      },
      (error)=>{
        this.snackbar.open('sign up faild.please try agin','Close',{duration:5000,panelClass:'error-snackbar'});
        console.log(error);
      }
    )
  }

}
