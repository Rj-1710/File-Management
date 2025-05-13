import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-file-dashboard',
  templateUrl: './file-dashboard.component.html',
  styleUrls: ['./file-dashboard.component.css'],
  imports: [CommonModule,RouterModule]
})
export class FileDashboardComponent {
  constructor(private router: Router) {}

  goToCreateFile() {
    this.router.navigate(['/create-file']);
  }

  goToFileDetails(){
    this.router.navigate(['/file-details']);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}
