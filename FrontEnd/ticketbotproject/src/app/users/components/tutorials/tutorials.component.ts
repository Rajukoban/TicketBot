import { Component } from '@angular/core';
import { Tutorial } from '../models/tutorial';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tutorials',
  templateUrl: './tutorials.component.html',
  styleUrl: './tutorials.component.css'
})
export class TutorialsComponent {

  tutorials: Tutorial[] = [];
  displayCloumns:string[]=['id','title','description','filename','actions'];

  constructor(private tutorialService:AuthService,private router:Router) { }

  ngOnInit(): void {
    this.loadTutorials();
  }

  loadTutorials(){
    this.tutorialService.getAllTutorials().subscribe({
      next:(data:Tutorial[])=>{
        this.tutorials=data;
      },
      error:err=>{
        console.log("not fetcing tutorials");
      }
    })
  }

  view(id:number){
    this.router.navigate(['users/viewpdf',id]);
  }

}
