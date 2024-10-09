import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { ITTicket } from '../Model/it-ticket';

@Component({
  selector: 'app-view-ticket-details',
  templateUrl: './view-ticket-details.component.html',
  styleUrl: './view-ticket-details.component.css'
})
export class ViewTicketDetailsComponent {
  ticket: ITTicket | undefined;

  constructor(private route: ActivatedRoute, private ticketService: AuthService) { }

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

}
