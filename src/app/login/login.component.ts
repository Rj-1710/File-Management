import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, ReactiveFormsModule, RouterModule, MatSnackBarModule]
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched(); 
      return;
    }
    const { username, password } = this.loginForm.value;

    this.authService.login(username, password).subscribe(
      token => {
        localStorage.setItem('token', token);
        this.showPopup('Login successful! Redirecting...', 'success');

        setTimeout(() => {
          this.router.navigate(['/file-dashboard']); 
        }, 3000);
      },
      error => {
        this.showPopup(' Invalid Credentilals! Please try again.', 'error');
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
