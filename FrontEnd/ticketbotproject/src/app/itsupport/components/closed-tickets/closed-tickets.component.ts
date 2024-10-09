import { Component } from '@angular/core';
import { ITTicket } from '../Model/it-ticket';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-closed-tickets',
  templateUrl: './closed-tickets.component.html',
  styleUrl: './closed-tickets.component.css'
})
export class ClosedTicketsComponent {

  closedTickets:ITTicket[] = [];

  constructor(private ticketService: AuthService) {}

  ngOnInit(): void {
    this.fetchClosedTickets();
  }

  fetchClosedTickets(): void {
    this.ticketService.getClosedTickets().subscribe({
      next:(tickets:ITTicket[])=>{
        this.closedTickets=tickets;
      },
      error:(err)=>{
        console.log("closed Tickets not fetching");
      }
    })
  }

  viewDetails(ticketId:number){

  }
  updateDetails(ticketId:number){
    
  }

}
