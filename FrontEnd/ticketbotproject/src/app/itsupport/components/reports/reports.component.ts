import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.css'
})
export class ReportsComponent {
  totalTickets: number = 0;
  solvedTickets: number = 0;
  updatedTickets: number = 0;

  private ticketURL="http://localhost:7027"

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchReportData();
  }

  fetchReportData() {
    this.http.get<any>(`${this.ticketURL}/tickets/report`).subscribe(data => {
      this.totalTickets = data.totalTickets;
      this.solvedTickets = data.solvedTickets;
      this.updatedTickets = data.updatedTickets;
    });
  }

  generateReport() {
    // Logic to generate a detailed report
    alert('Report generated successfully!');
  }

}
