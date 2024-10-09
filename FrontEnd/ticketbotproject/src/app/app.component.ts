import { Component, OnInit } from '@angular/core';
import { UserstorageService } from './services/storage/userstorage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'ticketbotproject';
  userId=parseInt(UserstorageService.getUserId());
  isProjectMemberLoggedIn:boolean=UserstorageService.isProjectMemberLoggedIN();
  isReportingLoggedIn:boolean=UserstorageService.isReportingManager();
  isITSupportTeamLoggedIn:boolean=UserstorageService.isITSupportTeamLoggedIN();
  isSystemAdminLogggedIn:boolean=UserstorageService.isSystemAdminLoggedIN();

  constructor(private router:Router){}

  ngOnInit():void{
    this.router.events.subscribe(event=>{
      this.isProjectMemberLoggedIn=UserstorageService.isProjectMemberLoggedIN();
      this.isReportingLoggedIn=UserstorageService.isReportingManager();
      this.isITSupportTeamLoggedIn=UserstorageService.isITSupportTeamLoggedIN();
      this.isSystemAdminLogggedIn=UserstorageService.isSystemAdminLoggedIN();
    })
  }

  logout(){
    UserstorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
