import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from '../model/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserRole } from '../model/userrole';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserDetailsUpdate } from '../model/userDetailsupdate';
import { error } from 'console';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent {
  signupForm!:FormGroup;

  hidePassword=true;

  roles=Object.values(UserRole); 

  userData:UserDetailsUpdate=new UserDetailsUpdate();

  userId!:number;

  constructor(private fb:FormBuilder,private snackbar:MatSnackBar,private authService:AuthService,private router:Router,private active:ActivatedRoute){}

  ngOnInit(): void {
    this.signupForm=this.fb.group({
        name:[null,Validators.required],
        email:[null,[Validators.required,Validators.email]],
        password:[null,Validators.required],
        confirmpassword:[null,Validators.required],
        role:[UserRole.PROJECT_TEAM_MEMBER,Validators.required]
    });

    this.userId=this.active.snapshot.params['userId'];

    this.loadUser();
  }

  loadUser(){
    this.authService.getByUserIDB(this.userId).subscribe(res=>{
      this.userData=res;
    },error=>{
      console.log("update user fething error");
    }
  )
  }

  togglePasswordVisibility(){
    this.hidePassword=!this.hidePassword;
  }

  onEdit(){
    this.authService.updateUserDetails(this.userId,this.userData).subscribe(res=>{
      this.snackbar.open('User Details Updated Successfull.','Clode',{duration:5000});
      this.router.navigate(['/systemadmin/usermanage']);
    },error=>{
      this.snackbar.open("User Details not updated",'ERROR',{duration:5000});
    }
  )
  }
  

}
