import { Injectable } from '@angular/core';
import { json } from 'express';
import { Observable } from 'rxjs';

const TOKEN="ticketproject-tocket";
const USER='ticketproject-user';

@Injectable({
  providedIn: 'root'
})
export class UserstorageService {

  constructor() { }

  public saveToken(token:string):void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN,token);
  }

  public saveUser(user:any):void{
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER,JSON.stringify(user));
  }

  static getToken():any{
    return localStorage.getItem(TOKEN);
  }

  static getUser():any{
      if(typeof window !=='undefined'){
        const userdata =localStorage.getItem(USER);
        if(userdata!=null){
          return JSON.parse(userdata);
        }
      }
      return "";
  }

  static getUserId():string{
    const user=this.getUser();
    if(user==null){
      return '';
    }
    return user.userId;
  }

  static getUserRole():string{
    const user=this.getUser();
    if(user==null){
      return '';
    }
    return user.role;
  }

  static isReportingManager():boolean{
    if(this.getToken==null){
      return false;
    }
    const role:string=this.getUserRole();
    return role=='REPORTING_MANAGER';
  }

  static isProjectMemberLoggedIN():boolean{
    if(this.getToken==null){
      return false;
    }
    const role:string=this.getUserRole();
    return role=='PROJECT_TEAM_MEMBER';
  }

  static isITSupportTeamLoggedIN():boolean{
    if(this.getToken==null){
      return false;
    }
    const role:string=this.getUserRole();
    return role=='IT_SUPPORT_TEAM';
  }

  static isSystemAdminLoggedIN():boolean{
    if(this.getToken==null){
      return false;
    }
    const role:string=this.getUserRole();
    return role=='ADMIN';
  }

  static signOut():void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}
