import { Component } from '@angular/core';
import { ITTicket } from '../../../itsupport/components/Model/it-ticket';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { UserstorageService } from '../../../services/storage/userstorage.service';
import { ApprovalStatus } from '../model/approvalStatus';
import { SendApprovalTicket } from '../model/sendapproval';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-view-ticket',
  templateUrl: './view-ticket.component.html',
  styleUrl: './view-ticket.component.css'
})
export class ViewTicketComponent {

  ticket!: ITTicket ;

  constructor(private route: ActivatedRoute, private ticketService: AuthService,private user:UserstorageService,private snackbar:MatSnackBar,private router:Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = +params['id'];
      this.loadTicketDetails(id);
    });
  }

  loadTicketDetails(id: number) {
    this.ticketService.getTicketById(id).subscribe(
      (data) => {
        this.ticket = data;
      },
      (error) => {
        console.error('Error fetching ticket details', error);
      }
    );
  }

  approval(ticketId:number){
   const approval={
    approvalId:0,
    ticket:{ticketId:this.route.snapshot.params['id']},
    approvedBy:{userId:parseInt(UserstorageService.getUserId())},
    approvalStatus:ApprovalStatus.APPROVED,
   // approvalDate:new Date()
   };
   this.ticketService.sendApprovalTicket(approval).subscribe(data=>{
    this.snackbar.open("Ticket Approval Successfully",'Close',{duration:5000})
    this.router.navigate(['/admin/pending']);
   },error=>{
    this.snackbar.open("Not Approval Ticket",'Close',{duration:5000});
    this.router.navigate(['/admin/pending'])
   })
  }

  reject(ticketId:number){
    const approval={
      approvalId:0,
      ticket:{ticketId:this.route.snapshot.params['id']},
      approvedBy:{userId:parseInt(UserstorageService.getUserId())},
      approvalStatus:ApprovalStatus.REJECTED
    };
    this.ticketService.sendApprovalTicket(approval).subscribe(response=>{
      this.snackbar.open("Ticket Rejected Successfull",'Close',{duration:5000});
      this.router.navigate(['/admin/pending']);
    },error=>{
      this.snackbar.open("Ticket Rejected error",'ERROR',{duration:5000});
      this.router.navigate(['/admin/pending']);
    })
  }
}
