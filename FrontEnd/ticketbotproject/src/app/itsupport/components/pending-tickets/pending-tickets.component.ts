import { Component } from '@angular/core';
import { ITTicket } from '../Model/it-ticket';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pending-tickets',
  templateUrl: './pending-tickets.component.html',
  styleUrl: './pending-tickets.component.css'
}) 
export class PendingTicketsComponent {
  pendingTickets: ITTicket[] = [];

  constructor(private ticketService: AuthService,private router:Router) { }

  ngOnInit(): void {
    this.loadPendingTickets();
  }

  loadPendingTickets() {
    this.ticketService.getPendingTickets().subscribe(
      (data) => {
        this.pendingTickets = data;
      },
      (error) => {
        console.error('Error fetching pending tickets', error);
      }
    );
  }

  viewDetails(ticketId: number) {
    this.router.navigate(['ITSupport/ticket-details',ticketId]);
  }

  updateDetails(ticketId:number){
    this.router.navigate(['ITSupport/update-details',ticketId]);
  }

}
