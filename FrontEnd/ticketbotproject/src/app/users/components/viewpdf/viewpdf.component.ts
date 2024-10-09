import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-viewpdf',
  templateUrl: './viewpdf.component.html',
  styleUrl: './viewpdf.component.css'
})
export class ViewpdfComponent {
  pdfSrc: SafeResourceUrl | null = null;

  constructor(private pdfViewerService: AuthService, private sanitizer: DomSanitizer,private active:ActivatedRoute) { }

  ngOnInit(): void {
    const tutorialId = this.active.snapshot.params['id']; 
    this.loadPdf(tutorialId);
  }

  loadPdf(id: number): void {
    this.pdfViewerService.getPdfById(id).subscribe(
      (response) => {
        const file = new Blob([response], { type: 'application/pdf' });
        const fileURL = URL.createObjectURL(file);
        this.pdfSrc = this.sanitizer.bypassSecurityTrustResourceUrl(fileURL);
      },
      (error) => {
        console.error('Error fetching PDF:', error);
      }
    );
  }

}
