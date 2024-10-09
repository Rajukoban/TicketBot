import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UserstorageService } from '../storage/userstorage.service';
import { environment } from '../../../environments/environment';
import { Ticket } from '../../users/components/models/ticket';
import { MyTicket } from '../../users/components/models/mytickets';
import { User } from '../../users/components/models/user1';
import { ITTicket } from '../../itsupport/components/Model/it-ticket';
import { Approval } from '../../admin/components/model/approval';
import { ApprovalTicketsComponent } from '../../admin/components/approval-tickets/approval-tickets.component';
import { ApprovalStatus } from '../../admin/components/model/approvalStatus';
import { SendApprovalTicket } from '../../admin/components/model/sendapproval';
import { ITTicketApproval } from '../../admin/components/model/itticketapproval';
import { TicketPriority } from '../../admin/components/model/ticketPriority';
import { Profile } from '../../users/components/models/profile';
import { UserDetails } from '../../systemadmin/components/model/user';
import { UserDetailsUpdate } from '../../systemadmin/components/model/userDetailsupdate';
import { Tutorial } from '../../users/components/models/tutorial';

const BaseURL="http://localhost:7026/jwt/";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

private ticketURL1="http://localhost:7027/tickets";
private BaseURL1="http://localhost:7026/jwt";
private approval="http://localhost:8086/approval";
private tutorialURL="http://localhost:7030/api/tutorials";

  constructor(private http:HttpClient,private userStorageService:UserstorageService) { }

  //User Services

//   register(signupRequest:any):Observable<any>{
//   return this.http.post(BaseURL+"sign-up",signupRequest);
//  }

 register1(signupRequest:any):Observable<any>{
  return this.http.post(BaseURL+"sign-up1",signupRequest);
 }

 login(username:string,password:string):any{
  const headers=new HttpHeaders().set('Content-Type','application/json');
  const body={username,password};
  return this.http.post(BaseURL+"authenticate",body,{headers,observe:'response'}).pipe(
    map((res)=>{
      const token=res.headers.get('authorization')?.substring(7);
      const user=res.body;
      if(token && user){
        this.userStorageService.saveToken(token);
        this.userStorageService.saveUser(user);
        return true;
      }
      return false;
    })
  )
 }

 getUser():Observable<User>{
    return this.http.get<User>(`${this.BaseURL1}`);
 }

 getByUserId(userId:number):Observable<Profile>{
  return this.http.get<Profile>(`${this.BaseURL1}/hii/${userId}`);
 }

 getByUserIDB(userId:number):Observable<User>{
  return this.http.get<User>(`${this.BaseURL1}/hii/${userId}`);
 }

 getAllUser():Observable<UserDetails[]>{
  return this.http.get<UserDetails[]>(`${this.BaseURL1}`);
 }

 deleteUserById(userId:number):Observable<any>{
  return this.http.delete(`${this.BaseURL1}/delete/${userId}`);
 }

 updateUserDetails(userId:number,user:UserDetails):Observable<UserDetailsUpdate>{
  return this.http.put<UserDetailsUpdate>(`${this.BaseURL1}/update/${userId}`,user);
 }

  
 //ticket services

 createTicket(ticket:Ticket):Observable<UserDetails>{
  return this.http.post<UserDetails>(`${this.ticketURL1}`,ticket);
 }

 getTicket(userId:number):Observable<MyTicket[]>{
  return this.http.get<MyTicket[]>(`${this.ticketURL1}/${userId}`);
 }
 getAllTickets():Observable<MyTicket[]>{
  return this.http.get<MyTicket[]>(`${this.ticketURL1}`);
 }
 getTicketByUserId(userId:number):Observable<MyTicket[]>{
    return this.http.get<MyTicket[]>(`${this.ticketURL1}/user/${userId}`);
 }

 getAllItTickets():Observable<ITTicket[]>{
  return this.http.get<ITTicket[]>(`${this.ticketURL1}`);
 }

 deleteTicket(ticketId: number): Observable<any> {
  return this.http.delete(`${this.ticketURL1}/${ticketId}`);
}

getOpenTickets():Observable<ITTicket[]>{
  return this.http.get<ITTicket[]>(`${this.ticketURL1}/open`);
}

getTicketById(id:number):Observable<ITTicket>{
  return this.http.get<ITTicket>(`${this.ticketURL1}/${id}`);
}

getClosedTickets():Observable<ITTicket[]>{
  return this.http.get<ITTicket[]>(`${this.ticketURL1}/closed`);
}

getPendingTickets():Observable<ITTicket[]>{
  return this.http.get<ITTicket[]>(`${this.ticketURL1}/pending`);
}

getPendingApprovalTicketse():Observable<ITTicket[]>{
  return this.http.get<ITTicket[]>(`${this.ticketURL1}/pendings`);
}

edit(ticketId:number,ticket:Ticket):Observable<MyTicket>{
  return this.http.put<MyTicket>(`${this.ticketURL1}/${ticketId}`,ticket);
}

findApprovedTicketByUserId(userId:number):Observable<MyTicket[]>{
  return this.http.get<MyTicket[]>(`${this.ticketURL1}/approved?userId=${userId}`);
}

findRejectedTicketByUserId(userId:number):Observable<MyTicket[]>{
  return this.http.get<MyTicket[]>(`${this.ticketURL1}/rejected?userId=${userId}`);
}
 
//Approval Service

getApprovalTickets():Observable<Approval[]>{
  return this.http.get<Approval[]>(`${this.approval}/status`);
}

getRejectApprovalTickets():Observable<Approval[]>{
  return this.http.get<Approval[]>(`${this.approval}/reject`)
}

sendApprovalTicket(approvalTicket:SendApprovalTicket):Observable<any>{
  return this.http.post(`${this.approval}/sendApproval`,approvalTicket);
}

getPendingApprovalTickets():Observable<ITTicketApproval[]>{
  return this.http.get<ITTicketApproval[]>(`${this.ticketURL1}`);
}

//bar-graph services

getApprovalStatusData(): Observable<any[]> {
  return this.http.get<any[]>(`${this.ticketURL1}/approval-status`);
}

getApprovalStatusData1(userId:number):Observable<any[]>{
  return this.http.get<any[]>(`${this.ticketURL1}/approval-status/${userId}`)
}

//get Tutorail Service
 getAllTutorials():Observable<Tutorial[]>{
  return this.http.get<Tutorial[]>(`${this.tutorialURL}/all`);
 }

 getPdfById(id: number): Observable<Blob> {
  return this.http.get(`${this.tutorialURL}/view/${id}`, { responseType: 'blob' });
}

uploadTutorial(formData: FormData): Observable<any> {
  return this.http.post(`${this.tutorialURL}/upload`, formData, {
    headers: new HttpHeaders({
      'enctype': 'multipart/form-data'
    }),
    responseType: 'text' as 'json'
  });
}

getTutorialById(id: number): Observable<any> {
  return this.http.get(`${this.tutorialURL}/${id}`);
}
}
