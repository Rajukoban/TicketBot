import { Component, Inject } from '@angular/core';
import { MatDialogRef,MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MyTicket } from '../models/mytickets';
import { TicketStatus } from '../models/ticketStatus';
import { Ticket } from '../models/ticket';

@Component({
  selector: 'app-edit-ticket',
  templateUrl: './edit-ticket.component.html',
  styleUrl: './edit-ticket.component.css'
})
export class EditTicketComponent {
  statuses = Object.values(TicketStatus);
  
  constructor(
    public dialogRef:MatDialogRef<EditTicketComponent>,
    @Inject(MAT_DIALOG_DATA) public data:MyTicket
  ){}

  onNoClick():void{
    this.dialogRef.close();
  }

}
