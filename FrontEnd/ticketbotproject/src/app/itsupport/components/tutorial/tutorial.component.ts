import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tutorial',
  templateUrl: './tutorial.component.html',
  styleUrl: './tutorial.component.css'
})
export class TutorialComponent {

  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  tutorial = {
    title: '',
    description: ''
  };

  constructor(private tutorialService: AuthService,private snackbar:MatSnackBar,private router:Router) { }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.progress = 0;

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        const formData: FormData = new FormData();
        formData.append('pdfFile', this.currentFile);
        formData.append('title', this.tutorial.title);
        formData.append('description', this.tutorial.description);

        this.tutorialService.uploadTutorial(formData).subscribe({
          next: (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              if (event.total) {
                this.progress = Math.round(100 * event.loaded / event.total);
              }
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
            }
            this.snackbar.open('File Uploaded Successfully','Close',{duration:5000});
            this.router.navigate(['/ITSupport/dashboard'])
          },
          error: (err: any) => {
            this.progress = 0;
            this.message = 'Could not upload the file!';
            console.log(err);
          }
        });
      }
    }
  }

}
