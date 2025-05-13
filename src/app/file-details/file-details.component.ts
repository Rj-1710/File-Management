import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FileDetailsService } from '../file-details.service';
import { FormsModule } from '@angular/forms';
import { StatusUpdateComponent } from '../status-update/status-update.component';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-file-details',
  templateUrl: './file-details.component.html',
  styleUrls: ['./file-details.component.css'],
  imports: [CommonModule, RouterModule, FormsModule, StatusUpdateComponent, MatSnackBarModule]
})
export class FileDetailsComponent {
  fileId: number = 0;
  fileData: any;

  constructor(private fileDetailsService: FileDetailsService, private snackBar: MatSnackBar) {}

  fetchFileDetails() {
    if (!this.fileId) {
      this.showPopup('please enter a valid File ID!', 'error');
      return;
    }

    this.fileDetailsService.getFileById(this.fileId).subscribe(
      data => this.fileData = data,
      error => this.showPopup('File not found!', 'error')
    );
  }

  showPopup(message: string, type: 'success' | 'error') {
    this.snackBar.open(message, 'Close', {
      duration: 3000, 
      panelClass: type === 'success' ? 'success-snackbar' : 'error-snackbar',
      verticalPosition: 'top', 
      horizontalPosition: 'center' 
    });
  }
}
