import { Component } from '@angular/core';
import { BatchService } from '../batch-runner.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-batch-runner',
  templateUrl: './batch-runner.component.html',
  styleUrls: ['./batch-runner.component.css']
})
export class BatchRunnerComponent {
  constructor(private batchService: BatchService, private snackBar: MatSnackBar) {}

  runBatchJob() {
    this.batchService.runBatchJob().subscribe(
      response => this.showPopup(' Batch job started successfully!', 'success'),
      error => this.showPopup(error.message, 'error') 
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
