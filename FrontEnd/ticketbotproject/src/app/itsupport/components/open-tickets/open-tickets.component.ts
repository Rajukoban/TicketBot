import { Component, OnInit } from '@angular/core';
import { ITTicket } from '../Model/it-ticket';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-open-tickets',
  templateUrl: './open-tickets.component.html',
  styleUrl: './open-tickets.component.css'
})
export class OpenTicketsComponent implements OnInit{
  openTickets:ITTicket[]=[];

  constructor(private ticketService:AuthService){}
  ngOnInit(): void {
    this.loadOpenTickets();
  }

  loadOpenTickets():void{
    this.ticketService.getOpenTickets().subscribe({
      next: (tickets)=>{
        this.openTickets=tickets;
      },error: (err)=>{
        console.log("error fetching open tickets");
      }
    })
  }



}
