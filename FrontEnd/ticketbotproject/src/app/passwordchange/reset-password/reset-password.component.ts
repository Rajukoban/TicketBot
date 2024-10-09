import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UserstorageService } from '../../services/storage/userstorage.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {

  password: string = '';
  token: string | null = '';
  hidePassword=true;

  isProjectMemberLoggedIn:boolean=UserstorageService.isProjectMemberLoggedIN();

  private baseURl="http://localhost:7026/jwt";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.token = this.route.snapshot.queryParamMap.get('token');
    this.router.events.subscribe(event=>{
      this.isProjectMemberLoggedIn=UserstorageService.isProjectMemberLoggedIN();
    })
  }

  togglePasswordVisibility(){
    this.hidePassword=!this.hidePassword;
  }

  onSubmit() {
    if (this.token) {
      this.http.post(`${this.baseURl}/reset-password`, { token: this.token, password: this.password })
        .subscribe(
          response => {
            this.snackBar.open('Password reset successfully.', 'Close', { duration: 5000 });
            this.router.navigate(['/login']);
          },
          error => {
            this.snackBar.open('Password reset successfully.', 'Close', { duration: 5000 });
            this.router.navigate(['/login']);
          }
        );
    }
  }


}
