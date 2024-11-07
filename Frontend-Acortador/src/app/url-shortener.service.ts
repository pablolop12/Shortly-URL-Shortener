// url-shortener.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlShortenerService {
  private apiUrl = 'http://13.60.214.218:8080/api';

  constructor(private http: HttpClient) {}

  shortenUrl(data: { originalUrl: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/shorten`, data);
  }

  getOriginalUrl(shortenedUrl: string): Observable<string> {
    return this.http.get<string>(`http://13.60.214.218:8080/api/${shortenedUrl}`);
  }
  
}
