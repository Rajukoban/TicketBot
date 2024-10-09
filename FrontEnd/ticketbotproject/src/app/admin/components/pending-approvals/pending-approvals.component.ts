import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ITTicket } from '../../../itsupport/components/Model/it-ticket';
import { ITTicketApproval } from '../model/itticketapproval';



@Component({
  selector: 'app-pending-approvals',
  templateUrl: './pending-approvals.component.html',
  styleUrl: './pending-approvals.component.css'
})
export class PendingApprovalsComponent implements OnInit{
  tickets:ITTicket[]=[];
  pendingTickets:ITTicketApproval[]=[];
  displayedColumns: string[] = ['ticketId','title','status','priority','actions'];
  constructor(private ticketService:AuthService,private snackbar:MatSnackBar,private rouetr:Router){}
  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets():void{
    this.ticketService.getPendingApprovalTicketse().subscribe({
      next:(data:ITTicket[])=>{
        this.tickets = data;
      },
      error:(err)=>{
        console.log("Error fetching the data");
      }
    })
  }


  viewDetails(ticketId:number){
    this.rouetr.navigate(['admin/ticket-details',ticketId]);
  }

}
