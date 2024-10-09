import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<any>;
  
  constructor() {
    //this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')!));
    this,this.currentUserSubject=new BehaviorSubject<any>(this.getUser()!);
  }

   getUser():any{
    if(typeof window !=='undefined'){
      const userdata =localStorage.getItem('ticketproject-user');
      if(userdata!=null){
        return JSON.parse(userdata);
      }
    }
    return "";
}

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  public getUserId(): number {
    return this.currentUserValue?.userId;
  }
}
