import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { ITTicket } from '../Model/it-ticket';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-all-tickets',
  templateUrl: './all-tickets.component.html',
  styleUrl: './all-tickets.component.css'
})
export class AllTicketsComponent implements OnInit{
  tickets:ITTicket[]=[];
  displayedColumns: string[] = ['ticketId','title','status','priority','actions'];
  constructor(private ticketService:AuthService,private snackbar:MatSnackBar){}
  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets():void{
    this.ticketService.getAllItTickets().subscribe({
      next:(data:ITTicket[])=>{
        this.tickets=data;
      },
      error:(err)=>{
        console.log("Error fetching the data");
      }
    })
  }

  onDeleteTicket(ticketId: number): void {
    if (confirm('Are you sure you want to delete this ticket?')) {
      this.ticketService.deleteTicket(ticketId).subscribe({
        next: (response) => {
          this.tickets = this.tickets.filter(ticket => ticket.ticketId !== ticketId);
          this.snackbar.open('Ticket deleted successfully','Close',{duration:5000});
        },
        error: (err) => {
          console.error('Error deleting ticket', err);
          this.snackbar.open('Failed to delete the ticket','Closed',{duration:5000});
        }
      });
    }
  }
  

}
