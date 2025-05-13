import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileSearchService {
  private baseUrl = 'http://localhost:8094/api/file-loads/files'; 

  constructor(private http: HttpClient) {}

  searchFiles(criteria: { filename: string; status: string }): Observable<any[]> {
    const token = localStorage.getItem('token'); 

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`, 
      'Content-Type': 'application/json' 
    });

    return this.http.post<any[]>(this.baseUrl, criteria, { headers }); 
  }
}
