import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { StatusUpdateService } from '../status-update.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-status-update',
  templateUrl: './status-update.component.html',
  styleUrls: ['./status-update.component.css'],
  imports: [CommonModule, ReactiveFormsModule, MatSnackBarModule]
})
export class StatusUpdateComponent {
  @Input() fileId!: number; 
  statusForm: FormGroup;

  constructor(private statusUpdateService: StatusUpdateService, private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.statusForm = this.fb.group({
      status: [''] 
    });
  }

  onUpdateStatus() {
    const newStatus = this.statusForm.get('status')?.value;

    if (!newStatus) {
      this.showPopup('Please select a status!', 'error');
      return;
    }

    this.statusUpdateService.updateFileStatus(this.fileId, newStatus).subscribe(
      response => this.showPopup('Status updated successfully!', 'success'),
      error => this.showPopup('Failed to update status!', 'error')
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
