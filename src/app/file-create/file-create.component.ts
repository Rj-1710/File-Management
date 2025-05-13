import { Component } from '@angular/core';
import { FileCreateService } from '../file-create.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-file-create',
  templateUrl: './file-create.component.html',
  styleUrls: ['./file-create.component.css'],
  imports: [CommonModule, ReactiveFormsModule, MatSnackBarModule]
})
export class FileCreateComponent {
  createFileForm: FormGroup;

  constructor(private fileCreateService: FileCreateService, private router: Router, private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.createFileForm = this.fb.group({
      filename: ['', Validators.required],
      status: ['',Validators.required],
      recordCount: [0,[Validators.required,Validators.min(1)]]
    });
  }

  onSubmit() {
    if (this.createFileForm.invalid) {
      this.showPopup(' Please fill in all fields!', 'error');
      return;
    }

    this.fileCreateService.createFile(this.createFileForm.value).subscribe(
      response => {
        this.showPopup(' File created successfully!', 'success');
        setTimeout(() => this.router.navigate(['/file-dashboard']), 2000);
      },
      error => {
        this.showPopup('File creation failed!', 'error');
      }
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
