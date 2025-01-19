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

  router = inject(Router);

  url = 'http://localhost:8080/';
  constructor(private http: HttpClient, private dataService: DataService) { }

  login(credentials: LoginUser) {
    return this.http.post<AuthResponse>('http://localhost:8080/api/v1/auth/login', credentials, {
      withCredentials: true
    });
  }

  logout() {
    // Logout logic here
    this.http.post('http://localhost:8080/api/v1/auth/logout', {}, {
      withCredentials: true
    }).subscribe({
      next: () => {
        this.router.navigate(['/auth']);
      },
      error: e => console.log("Error: " + e)
    });
  }

  async checkAuthenticated(): Promise<boolean> {
    const authVerification = lastValueFrom(this.http.get<boolean>('http://localhost:8080/api/v1/auth/admin', {
      withCredentials: true
    }));

    return await authVerification.then(res => {
      if (res) {
        console.log("User is authenticated");
        return true;
      }
      console.log("User is not authenticated");
      return false;
    }).catch(() => {
      console.log("Error checking authentication");
      return false;
    })
  }
}
