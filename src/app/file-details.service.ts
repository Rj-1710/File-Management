import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileDetailsService {
  private baseUrl = 'http://localhost:8094/api/file-loads/file'; 

  constructor(private http: HttpClient) {}

  getFileById(fileId: number): Observable<any> {
    const token = localStorage.getItem('token'); 
    const headers = { Authorization: `Bearer ${token}` }; 

    return this.http.get<any>(`${this.baseUrl}/${fileId}`, { headers });
  }
}
