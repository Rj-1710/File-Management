import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatusUpdateService {
  private baseUrl = 'http://localhost:8094/api/file-loads/update'; 

  constructor(private http: HttpClient) {}

  updateFileStatus(fileId: number, newStatus: string): Observable<any> {
    const token = localStorage.getItem('token'); 
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`, 
      'Content-Type': 'application/json'
    });

    return this.http.put<any>(`${this.baseUrl}/${fileId}?status=${newStatus}`, null, { headers });
  }
}
