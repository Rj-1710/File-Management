import { Component } from '@angular/core';
import { FileSearchService } from '../file-search.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-file-search',
  templateUrl: './file-search.component.html',
  styleUrls: ['./file-search.component.css'],
  imports: [CommonModule, ReactiveFormsModule, MatSnackBarModule]
})
export class FileSearchComponent {
  searchForm: FormGroup;
  searchResults: any[] = [];

  constructor(private fileSearchService: FileSearchService, private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.searchForm = this.fb.group({
      filename: [''],
      status: ['']
    });
  }

  onSearch() {
    const criteria = this.searchForm.value;

    this.fileSearchService.searchFiles(criteria).subscribe(
      results => {
        if (results.length > 0) {
          this.searchResults = results;
        } else {
          this.searchResults = [];
          this.showPopup(' No files found!', 'error');
        }
      },
      error => this.showPopup(' Search failed!', 'error')
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
