import { Component, OnInit } from '@angular/core';
import { MyTicket } from '../models/mytickets';
import { AuthService } from '../../../services/auth/auth.service';
import { UserstorageService } from '../../../services/storage/userstorage.service';
import { Approval } from '../../../admin/components/model/approval';

@Component({
  selector: 'app-approval-tickets',
  templateUrl: './approval-tickets.component.html',
  styleUrl: './approval-tickets.component.css'
})
export class ApprovalTicketsComponent implements OnInit {
  tickets:MyTicket[]=[];
  approvals:Approval[]=[];

  constructor(private ticketService:AuthService){}

  ngOnInit(): void {
    this.loadApprovedTicketsBasedUserId();
      
  }

  loadApprovedTicketsBasedUserId(){
    const userId=parseInt(UserstorageService.getUserId());
    this.ticketService.findApprovedTicketByUserId(userId).subscribe({
      next:(data:MyTicket[])=>{
        this.tickets=data;
      },
      error:(err)=>{
        console.log("Not Fetching Approval Tickets based on userID");
      }
    })

    this.ticketService.getApprovalTickets().subscribe({
      next:(data:Approval[])=>{
        this.approvals=data;
      },
      error:(err)=>{
        console.log("not fetching approval tickets");
      }
    })
  }
}
