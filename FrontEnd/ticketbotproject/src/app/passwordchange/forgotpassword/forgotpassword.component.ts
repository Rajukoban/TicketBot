import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrl: './forgotpassword.component.css'
})
export class ForgotpasswordComponent {

  email: string = '';

  private baseURL="http://localhost:7026/jwt"

  constructor(private http: HttpClient, private snackBar: MatSnackBar,private route:Router) {}

  onSubmit() {
    this.http.post(`${this.baseURL}/forgot-password`, { email: this.email })
      .subscribe(
        response => {
          this.snackBar.open('Password reset link sent to your email.', 'Close', { duration: 5000 });
          this.route.navigate(['/login']);
        },
        error => {
          this.snackBar.open('Password reset link sent to your email.', 'Close', { duration: 5000 });
        }
      );
  }

}
