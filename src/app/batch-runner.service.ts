import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BatchService {
  private batchUrl = 'http://localhost:8094/api/batch/run'; 

  constructor(private http: HttpClient) {}

  runBatchJob(): Observable<string> {
    const token = localStorage.getItem('token'); 
    const headers = token ? new HttpHeaders({ Authorization: `Bearer ${token}` }) : new HttpHeaders();
  
    return this.http.get(this.batchUrl, { headers, responseType: 'text' }) 
      .pipe(
        catchError(this.handleError)
      );
  }
  

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    
    if (error.status === 0) {
      errorMessage = ' Cannot connect to the server!';
    } else if (error.status === 403) {
      errorMessage = ' Access denied! Please check your authentication.';
    } else if (error.status === 404) {
      errorMessage = ' Batch job endpoint not found!';
    } else if (error.status >= 500) {
      errorMessage = ' Server error occurred while starting batch job!';
    }

    console.error(`Batch job error [${error.status}]:`, error.message);
    return throwError(() => new Error(errorMessage));
  }
}
