import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlRedirectGuard implements CanActivate {

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    const shortenedUrl = route.paramMap.get('shortenedUrl');
    if (shortenedUrl) {
      // Redirige directamente al URL backend
      window.location.href = `http://13.60.214.218:8080/api/${shortenedUrl}`; //URL BACKEND
      return of(false); 
    }

    return of(false);
  }
}
