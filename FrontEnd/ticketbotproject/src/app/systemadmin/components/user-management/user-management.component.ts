import { Component } from '@angular/core';
import { UserDetails } from '../model/user';
import { AuthService } from '../../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrl: './user-management.component.css'
})
export class UserManagementComponent {
  userdata:UserDetails[]=[];
  displayedColumns:string[]=['userId','name','email','role','actions']

  constructor(private userService:AuthService,private snackbar:MatSnackBar,private router:Router){}

  ngOnInit():void{
    this.loadUsers();
  }

  loadUsers():void{
    this.userService.getAllUser().subscribe({
      next:(data:UserDetails[])=>{
        this.userdata=data;
      },
      error:(err)=>{
        console.log("not fetching user data");
      }
    })

  }
  deleteUser(userId: number):void {
    if(confirm("Are you sure you want to delete this User Details?")){
       this.userService.deleteUserById(userId).subscribe(res=>{
        this.router.navigate(['/systemadmin/usermanage']);
      this.snackbar.open('Employee Details Deleted Sucessfully.','Close',{duration:5000});
      },
       error=>{
         this.snackbar.open('Employee Details not Deleted.','ERROR',{duration:5000,panelClass:'err-snackbar'});
     })
   }
  }

  editUser(userId:number){
    this.router.navigate(['/systemadmin/updateuser',userId]);
  }

 


}
