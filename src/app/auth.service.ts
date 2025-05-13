import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8094/auth';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<string> {
    return this.http.post<{ token: string }>(`${this.baseUrl}/login`, { username, password }).pipe(
      map(response => response.token), 
      tap(token => {
        console.log('Token received:', token);
        if (token) {
          localStorage.setItem('token', token); 
        } else {
          console.error('Login response does not contain a token.');
        }
      }),
      catchError(error => {
        console.error('Login failed:', error);
        return throwError(() => new Error('Login failed'));
      })
    );
  }
   
  register(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, { username, password }).pipe(
      tap(response => console.log('User registered successfully:', response)),
      catchError(error => {
        console.error('Registration failed:', error);
        return throwError(() => new Error('Registration failed'));
      })
    );
  }
}
