import { Component } from '@angular/core';
import { Approval } from '../model/approval';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reject-ticket',
  templateUrl: './reject-ticket.component.html',
  styleUrl: './reject-ticket.component.css'
})
export class RejectTicketComponent {

  tickets:Approval[]=[];
  constructor(private ticketService:AuthService,private router:Router){}
  ngOnInit(): void {
      this.loadApprovalTickets();
  }

  loadApprovalTickets(){
    this.ticketService.getRejectApprovalTickets().subscribe({
      next:(data:Approval[])=>{
        this.tickets=data;
      },
      error:(err)=>{
        console.log("not fetching approval tickets");
      }
    })
  }

  viewDetails(ticketId: number) {
    console.log("view tickets")
  }

  updateDetails(ticketId:number){
    console.log("update ticket")
  }

}
