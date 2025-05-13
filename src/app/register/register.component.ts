import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [CommonModule, ReactiveFormsModule,RouterModule, MatSnackBarModule]
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required], 
      password: ['', Validators.required]  
    });
  }

  onRegister() {
    if (this.registerForm.invalid) {
      this.showPopup(' Please fill in all fields!', 'error');
      this.registerForm.markAllAsTouched(); 
      return;
    }

    this.authService.register(this.registerForm.value.username, this.registerForm.value.password).subscribe(
      response => {
        this.showPopup('Registration successful! Redirecting...', 'success');
        setTimeout(() => this.router.navigate(['/']), 2000);
      },
      error => {
        this.showPopup(' Registration failed!', 'error');
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
