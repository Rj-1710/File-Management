import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileCreateService {
  private baseUrl = 'http://localhost:8094/api/file-loads/create';

  constructor(private http: HttpClient) {}

  createFile(fileData: { filename: string; status: string; recordCount: number }): Observable<any> {
    const token = localStorage.getItem('token'); 

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`, 
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(this.baseUrl, fileData, { headers });
  }
}
