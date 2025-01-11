import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SsrCookieService } from 'ngx-cookie-service-ssr';
import { lastValueFrom } from 'rxjs';
import { AuthResponse, LoginUser } from '../../model/auth-response';
import { DataService } from '../data/data.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  cookieService: SsrCookieService = inject(SsrCookieService);


  url = 'http://localhost:8080/';
  constructor(private http: HttpClient, private dataService: DataService, private router: Router) { }

  login(credentials: LoginUser) {
    return this.http.post<AuthResponse>('http://localhost:8080/api/v1/auth/login', credentials, {
      withCredentials: true
    });
  }

  checkAuthenticated() {
    const authVerification = lastValueFrom(this.http.get<boolean>('http://localhost:8080/api/v1/auth/admin', {
      withCredentials: true
    }));

    authVerification.then(res => {
      if (res) {
        console.log("User is authenticated");
        return true;
      }
      console.log("User is not authenticated");
      this.router.navigate(['/auth/']);
      return false;
    }).catch(() => {
      console.log("Error checking authentication");
      this.router.navigate(['/auth/']);
      return false;
    })

  }
}
