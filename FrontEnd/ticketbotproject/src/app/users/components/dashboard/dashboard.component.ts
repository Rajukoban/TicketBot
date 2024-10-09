import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { ChartData, ChartOptions } from 'chart.js';
import { isPlatformBrowser } from '@angular/common';
import { Color } from '@swimlane/ngx-charts';
import { UserstorageService } from '../../../services/storage/userstorage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  public barChartData: any[] = []
  userId!:number;
  constructor(private ticketService: AuthService) { }

  ngOnInit(): void {
    this.userId=Number(UserstorageService.getUserId());
    this.loadApprovalData();
  }

  loadApprovalData(): void {
    if(isNaN(this.userId)){
      console.error("Invalid userId",this.userId);
      return
    }
    this.ticketService.getApprovalStatusData1(this.userId).subscribe(data => {
      const approved = data.filter(t => t.approvalStatus === 'APPROVED').length;
      const rejected = data.filter(t => t.approvalStatus === 'REJECTED').length;
      const pending = data.filter(t => t.approvalStatus === 'PENDING').length;

      this.barChartData = [
        {
          "name": "Approved",
          "value": approved
        },
        {
          "name": "Rejected",
          "value": rejected
        },
        {
          "name": "Pending",
          "value": pending
        }
      ];
    });
  }
  

}
