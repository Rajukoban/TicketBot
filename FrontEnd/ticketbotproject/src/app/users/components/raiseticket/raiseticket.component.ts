import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TicketPriority } from '../models/ticketPriority';
import { TicketStatus } from '../models/ticketStatus';
import { AuthService } from '../../../services/auth/auth.service';
import { AuthenticationService } from '../../../services/authentication/authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-raiseticket',
  templateUrl: './raiseticket.component.html',
  styleUrl: './raiseticket.component.css'
})
export class RaiseticketComponent {
  ticketForm!: FormGroup;
  userId!: number;

  priorities = Object.values(TicketPriority);
  statuses = Object.values(TicketStatus);

  constructor(private fb: FormBuilder, private ticketService: AuthService,private authService: AuthenticationService,private snackbar:MatSnackBar,private router:Router) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    this.ticketForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      priority: [TicketPriority.MEDIUM, Validators.required],
      status: [TicketStatus.OPEN, Validators.required],
      user: this.fb.group({
        userId: [this.userId], 
        name: [''],
        email: [''],
        role: [''],
        password:['']
      })
    });
    this.setUserDetails();
  }

  setUserDetails(): void {
    const user = this.authService.currentUserValue;
    if (user) {
      this.ticketForm.patchValue({
        user: {
          name: user.name,
          email: user.email,
          role: user.role,
          password:user.password
        }
      });
    }
  }

  onSubmit(): void {
    if (this.ticketForm.valid) {
      this.ticketService.createTicket(this.ticketForm.value).subscribe(
        (response) => {
          this.snackbar.open("Ticket Created Successfull",'Close',{duration:5000});
          this.router.navigateByUrl('users/dashboard');
        },
        (error) => {
          this.snackbar.open("Ticket not created",'Close',{duration:5000});
        }
      );
    }
  }

}
