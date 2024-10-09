import { Component } from '@angular/core';
import { MyTicket } from '../models/mytickets';
import { AuthService } from '../../../services/auth/auth.service';
import { UserstorageService } from '../../../services/storage/userstorage.service';
import { error } from 'console';
import { AuthenticationService } from '../../../services/authentication/authentication.service';
import { Ticket } from '../models/ticket';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { EditTicketComponent } from '../edit-ticket/edit-ticket.component';
import { DialogRef } from '@angular/cdk/dialog';

@Component({
  selector: 'app-mytickets',
  templateUrl: './mytickets.component.html',
  styleUrl: './mytickets.component.css'
})
export class MyticketsComponent {
  tickets: MyTicket[] = [];
  displayedColumns: string[] = ['ticketId','title','description','status','priority','createdDate','actions'];
  userid!: number;
  constructor(private ticketService: AuthService,private userStorage:UserstorageService,private authservice:AuthenticationService,private activeRoute:Router,private snackbar:MatSnackBar,private dialog:MatDialog) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets():any{
     const userid=parseInt(UserstorageService.getUserId());
    this.ticketService.getTicketByUserId(userid).subscribe(data=>{
      this.tickets=data;
    },error=>{console.log("tickets not found")});
  }

  editTicket(ticket?:MyTicket):void{
    const dialogRef=this.dialog.open(EditTicketComponent,{
      width:'450px',
      height:'500px',
      data:ticket?ticket:{title:'',description:'',status:'',priority:''}
    });
    dialogRef.afterClosed().subscribe(result=>{
      if(result){
        if(result.ticketId){
          this.ticketService.edit(result.ticketId,result).subscribe(()=>{
            this.loadTickets();
            this.snackbar.open("Ticket Updated Successfully",'Close',{duration:5000});
          });
        }else{
          this.ticketService.createTicket(result).subscribe(()=>{
            this.loadTickets();
            this.snackbar.open("Ticket Added Successfully",'Close',{duration:5000});
          })
        }
      }
    });
  }

  deleteTicket(ticketId:number){  
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
